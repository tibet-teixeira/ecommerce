package model.purchase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.util.Pair;
import model.customer.Customer;
import model.customer.CustomerModel;
import model.product.Product;
import model.product.ProductModel;

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

        Connection connection = getConnection();

        String sqlQuery = "INSERT INTO compra (id, data_hora, id_cliente) "
                + "VALUES (?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
        preparedStatement.setInt(1, id + 1);
        preparedStatement.setString(2, purchase.getDate());
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

        for (Pair<Product, Integer> numberProducts : purchase.getNumberProducts()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            Product product = numberProducts.getKey();
            int quantity = numberProducts.getValue();

            preparedStatement.setInt(1, product.getId());
            preparedStatement.setInt(2, purchase.getId());
            preparedStatement.setInt(3, quantity);

            product.setQuantity(product.getQuantity() - quantity);

            new ProductModel().update(product, product.getId());

            int result = preparedStatement.executeUpdate();

            if (result != 1) {
                preparedStatement.close();
                closeConnection(connection);
                throw new Exception("Não foi possível associar o produto à categoria selecionada");
            }
        }
    }

    public void update(Purchase purchase, int id) throws Exception {
        Connection connection = getConnection();

        Purchase oldPurchase = get(id);

        String sqlQuery = "UPDATE compra SET data_hora = ?, id_cliente = ? WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
        preparedStatement.setString(1, purchase.getDate());
        preparedStatement.setInt(2, purchase.getCustomer().getId());
        preparedStatement.setInt(3, id);

        int result = preparedStatement.executeUpdate();

        ProductModel productModel = new ProductModel();
        Product product;
        Product oldProduct;

        for (Pair<Product, Integer> numberProducts : purchase.getNumberProducts()) {
            for (Pair<Product, Integer> oldNumberProducts : oldPurchase.getNumberProducts()) {
                product = numberProducts.getKey();
                oldProduct = oldNumberProducts.getKey();

                if (product == oldProduct) {
                    product.setQuantity(product.getQuantity() - (numberProducts.getValue() - oldNumberProducts.getValue()));
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
            purchase.setDate(resultSet.getString("data_hora"));
            purchase.setCustomer(new CustomerModel().get(resultSet.getInt("id_cliente")));
        }

        if (purchase == null) {
            resultSet.close();
            preparedStatement.close();
            closeConnection(connection);

            throw new Exception("Não foi possível obter esta compra");
        }

        List<Pair<Product, Integer>> numberProducts = new ArrayList<>();

        sqlQuery = "SELECT id_produto, id_compra, quantidade_compra"
                + "FROM compra_produto "
                + "INNER JOIN produto as prod ON (prod.id = id_produto) "
                + "WHERE id_compra = ?";
        preparedStatement = connection.prepareStatement(sqlQuery);
        preparedStatement.setInt(1, id);

        resultSet = preparedStatement.executeQuery();
        ProductModel productModel = new ProductModel();
        Product product = new Product();
        int quantity;
        while (resultSet.next()) {
            product = productModel.get(resultSet.getInt("id_produto"));
            quantity = resultSet.getInt("quantidade_compra");
            numberProducts.add(new Pair<Product, Integer>(product, quantity));
        }

        purchase.setNumberProducts(numberProducts);

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

            throw new Exception("Este usuário não realizou nenhuma compra");
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

        String sqlQuery = "SELECT count(id) as number FROM compra";
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