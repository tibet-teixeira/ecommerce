package model.purchase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.customer.Customer;
import model.customer.CustomerModel;
import model.product.Product;
import model.product.ProductModel;
import model.bag.ShoppingBagItem;

/**
 *
 * @author Tibet
 */
public class PurchaseDAO {

    private Connection getConnection() throws Exception {
        try {
            Class.forName("org.postgresql.Driver");
            String database = "jdbc:postgresql://localhost:5432/ecommerce";
            String userDB = "postgres";
            String passwordDB = "brasil158";
            Connection connection = DriverManager.getConnection(database, userDB, passwordDB);

            return connection;
        } catch (ClassNotFoundException ex) {
            throw new Exception("Driver JDBC não encontrado");
        } catch (SQLException ex) {
            throw new Exception("Não foi possível obter um conexão com o SGBD");
        }
    }

    private void closeConnection(Connection connection) throws Exception {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    public void insert(Purchase purchase) throws Exception {
        int id = getLastId();
        purchase.setId(id + 1);
        Connection connection = getConnection();
        
        java.sql.Timestamp dateSql = new java.sql.Timestamp(purchase.getDate().getTime());

        String sqlQuery = "INSERT INTO compra (id, data_hora, id_cliente) "
                + "VALUES (?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
        preparedStatement.setInt(1, purchase.getId());
        preparedStatement.setTimestamp(2, dateSql);
        preparedStatement.setInt(3, purchase.getCustomer().getId());

        int result = preparedStatement.executeUpdate();

        insertProductPurchase(purchase, connection);

        preparedStatement.close();
        closeConnection(connection);

        if (result != 1) {
            throw new Exception("Não foi possível inserir esta compra");
        }
    }

    private void insertProductPurchase(Purchase purchase, Connection connection) throws Exception {
        String sqlQuery = "INSERT INTO compra_produto (id_produto, id_compra, quantidade_compra) "
                + "VALUES (?, ?, ?)";

        for (ShoppingBagItem numberProducts : purchase.getShoppingBagItems()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            Product product = numberProducts.getProduct();
            int quantity = numberProducts.getQuantity();

            preparedStatement.setInt(1, product.getId());
            preparedStatement.setInt(2, purchase.getId());
            preparedStatement.setInt(3, quantity);

            product.setQuantity(product.getQuantity() - quantity);

            new ProductModel().update(product, product.getId());

            int result = preparedStatement.executeUpdate();

            if (result != 1) {
                preparedStatement.close();
                closeConnection(connection);
                throw new Exception("Não foi possível associar o produto à compra selecionada");
            }
        }
    }

    public void update(Purchase purchase, int id) throws Exception {
        Connection connection = getConnection();

        Purchase oldPurchase = get(id);
        java.sql.Timestamp dateSql = new java.sql.Timestamp(purchase.getDate().getTime());

        String sqlQuery = "UPDATE compra SET data_hora = ?, id_cliente = ? WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
        preparedStatement.setTimestamp(1, dateSql);
        preparedStatement.setInt(2, purchase.getCustomer().getId());
        preparedStatement.setInt(3, id);

        int result = preparedStatement.executeUpdate();

        ProductModel productModel = new ProductModel();
        Product product;
        Product oldProduct;

        for (ShoppingBagItem numberProducts : purchase.getShoppingBagItems()) {
            for (ShoppingBagItem oldNumberProducts : oldPurchase.getShoppingBagItems()) {
                product = numberProducts.getProduct();
                oldProduct = oldNumberProducts.getProduct();

                if (product == oldProduct) {
                    product.setQuantity(product.getQuantity() - (numberProducts.getQuantity()- oldNumberProducts.getQuantity()));
                    productModel.update(product, product.getId());
                }
            }
        }

        preparedStatement.close();
        closeConnection(connection);

        if (result != 1) {
            throw new Exception("Não foi possível atualizar esta compra");
        }
    }

    public void delete(int id) throws Exception {
        Connection connection = getConnection();

        String sqlQuery = "DELETE FROM compra WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
        preparedStatement.setInt(1, id);

        int result = preparedStatement.executeUpdate();
        preparedStatement.close();
        closeConnection(connection);

        if (result != 1) {
            throw new Exception("Não foi possível remover esta compra");
        }
    }

    public Purchase get(int id) throws Exception {
        Purchase purchase = null;
        Connection connection = getConnection();

        String sqlQuery = "SELECT id, data_hora, id_cliente FROM compra WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
        preparedStatement.setInt(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            purchase = new Purchase();
            purchase.setId(resultSet.getInt("id"));
            purchase.setDate(new Date(resultSet.getTimestamp("data_hora").getTime()));
            purchase.setCustomer(new CustomerModel().get(resultSet.getInt("id_cliente")));
        }

        if (purchase == null) {
            resultSet.close();
            preparedStatement.close();
            closeConnection(connection);

            throw new Exception("Não foi possível obter esta compra");
        }

        List<ShoppingBagItem> numberProducts = new ArrayList<>();

        sqlQuery = "SELECT cp.id_produto, cp.id_compra, cp.quantidade_compra "
                + "FROM compra_produto as cp "
                + "INNER JOIN produto as prod ON (prod.id = cp.id_produto) "
                + "WHERE cp.id_compra = ?";
        preparedStatement = connection.prepareStatement(sqlQuery);
        preparedStatement.setInt(1, id);

        resultSet = preparedStatement.executeQuery();
        ProductModel productModel = new ProductModel();
        Product product = new Product();
        int quantity;
        while (resultSet.next()) {
            product = productModel.get(resultSet.getInt("id_produto"));
            quantity = resultSet.getInt("quantidade_compra");
            numberProducts.add(new ShoppingBagItem(product, quantity));
        }

        purchase.setShoppingBagItems(numberProducts);

        return purchase;
    }

    public List<Purchase> get(Customer customer) throws Exception {
        List<Purchase> purchases = new ArrayList<>();
        List<Integer> purchaseIds = new ArrayList<>();

        Connection connection = getConnection();

        String sqlQuery = "SELECT id, data_hora, id_cliente "
                + "FROM compra WHERE id_cliente = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
        preparedStatement.setInt(1, customer.getId());

        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            purchaseIds.add(resultSet.getInt("id"));
        }

        if (purchaseIds.isEmpty()) {
            resultSet.close();
            preparedStatement.close();
            closeConnection(connection);
            
            return null;
//            throw new Exception("Este usuário não realizou nenhuma compra");
        }

        for (int idPurchase : purchaseIds) {
            purchases.add(get(idPurchase));
        }

        return purchases;
    }

    public List<Purchase> getAll() throws Exception {
        List<Purchase> purchases = new ArrayList<>();
        List<Integer> purchaseIds = new ArrayList<>();

        Connection connection = getConnection();

        String sqlQuery = "SELECT id, data_hora, id_cliente FROM compra";

        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            purchaseIds.add(resultSet.getInt("id"));
        }

        if (purchaseIds.isEmpty()) {
            resultSet.close();
            preparedStatement.close();
            closeConnection(connection);

            throw new Exception("Não existe compras");
        }

        for (int idPurchase : purchaseIds) {
            purchases.add(get(idPurchase));
        }

        return purchases;
    }

    private int getLastId() throws Exception {
        Connection connection = getConnection();
        int numberPurchases = 0;

        String sqlQuery = "SELECT max(id) as number FROM compra";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            numberPurchases = Integer.parseInt(resultSet.getString("number"));
        }
        resultSet.close();
        preparedStatement.close();

        closeConnection(connection);

        return numberPurchases;
    }
}
