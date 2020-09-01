package model.product;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.category.Category;

/**
 *
 * @author Tibet
 */
public class ProductDAO {

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

    public void insert(Product product) throws Exception {
        int id = getLastId();

        Connection connection = getConnection();

        String sqlQuery = "INSERT INTO produto (id, descricao, preco, quantidade, foto) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
        preparedStatement.setInt(1, id + 1);
        preparedStatement.setString(2, product.getDescription());
        preparedStatement.setDouble(3, product.getPrice());
        preparedStatement.setInt(4, product.getQuantity());
        preparedStatement.setString(5, product.getPicture());

        insertProductCategory(product, connection);

        int result = preparedStatement.executeUpdate();
        preparedStatement.close();
        closeConnection(connection);

        if (result != 1) {
            throw new Exception("Não foi possível inserir este produto");
        }
    }

    private void insertProductCategory(Product product, Connection connection) throws Exception {
        String sqlQuery = "INSERT INTO produto_categoria (id_produto, id_categoria) VALUES (?, ?)";

        for (Category category : product.getCategories()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, product.getId());
            preparedStatement.setInt(2, category.getId());

            int result = preparedStatement.executeUpdate();

            if (result != 1) {
                throw new Exception("Não foi possível associar o produto à categoria selecionada");
            }
        }
    }

    public void update(Product product, int id) throws Exception {
        Connection connection = getConnection();

        String sqlQuery = "";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);


        int result = preparedStatement.executeUpdate();
        preparedStatement.close();
        closeConnection(connection);

        if (result != 1) {
            throw new Exception("Não foi possível atualizar este produto");
        }
    }

    public void delete(int id) throws Exception {
        Connection connection = getConnection();

        String sqlQuery = "DELETE FROM produto WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
        preparedStatement.setInt(1, id);

        int result = preparedStatement.executeUpdate();
        preparedStatement.close();
        closeConnection(connection);

        if (result != 1) {
            throw new Exception("Não foi possível remover este produto");
        }
    }

    public Product get(int id) throws Exception {
        Product product = null;
        Connection connection = getConnection();

        String sqlQuery = "";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
        preparedStatement.setInt(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            product = new Product();

        }

        resultSet.close();
        preparedStatement.close();
        closeConnection(connection);

        if (product == null) {
            throw new Exception("Não foi possível obter este produto");
        }

        return product;
    }

    public Product get(String login) throws Exception {
        Product product = null;
        Connection connection = getConnection();

        String sqlQuery = "";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            product = new Product();

        }
        resultSet.close();
        preparedStatement.close();

        closeConnection(connection);

        if (product == null) {
            throw new Exception("Não foi possível obter este produto");
        }

        return product;
    }

    public List<Product> getAll() throws Exception {
        List<Product> products = new ArrayList<Product>();
        Connection connection = getConnection();

        String sqlQuery = "";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Product product = new Product();

            products.add(product);
        }
        resultSet.close();
        preparedStatement.close();

        closeConnection(connection);

        if (products.isEmpty()) {
            throw new Exception("Não foi possível obter um produto");
        }

        return products;
    }

    private int getLastId() throws Exception {
        Connection connection = getConnection();
        int numberProducts = 0;

        String sqlQuery = "SELECT count(id) as number FROM produto";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            numberProducts = Integer.parseInt(resultSet.getString("number"));
        }
        resultSet.close();
        preparedStatement.close();

        closeConnection(connection);

        return numberProducts;
    }
}
