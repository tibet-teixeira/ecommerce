package model.product;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.category.Category;
import model.category.CategoryModel;

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
        product.setId(id + 1);
        Connection connection = getConnection();

        String sqlQuery = "INSERT INTO produto (id, descricao, preco, quantidade, foto) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
        preparedStatement.setInt(1, product.getId());
        preparedStatement.setString(2, product.getDescription());
        preparedStatement.setDouble(3, product.getPrice());
        preparedStatement.setInt(4, product.getQuantity());
        preparedStatement.setString(5, product.getPicture());

        int result = preparedStatement.executeUpdate();

        insertProductCategory(product, connection);

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
                preparedStatement.close();
                closeConnection(connection);
                throw new Exception("Não foi possível associar o produto à categoria selecionada");
            }
        }
    }

    public void update(Product product, int id) throws Exception {
        Connection connection = getConnection();

        String sqlQuery = "UPDATE produto SET descricao = ?, preco = ?, quantidade = ?, foto = ? WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
        preparedStatement.setString(1, product.getDescription());
        preparedStatement.setDouble(2, product.getPrice());
        preparedStatement.setInt(3, product.getQuantity());
        preparedStatement.setString(4, product.getPicture());
        preparedStatement.setInt(5, id);

        updateProductCategory(product, connection);

        int result = preparedStatement.executeUpdate();
        preparedStatement.close();
        closeConnection(connection);

        if (result != 1) {
            throw new Exception("Não foi possível atualizar este produto");
        }
    }

    private void updateProductCategory(Product product, Connection connection) throws Exception {
        deleteAllCategoriesFromProduct(product, connection);
        insertProductCategory(product, connection);
    }

    private void deleteAllCategoriesFromProduct(Product product, Connection connection) throws Exception {
        String sqlQuery = "DELETE FROM produto_categoria WHERE id_produto = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
        preparedStatement.setInt(1, product.getId());

        preparedStatement.executeUpdate();
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

        String sqlQuery = "SELECT id, descricao, preco, quantidade, foto FROM produto WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
        preparedStatement.setInt(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            product = new Product();
            product.setId(resultSet.getInt("id"));
            product.setDescription(resultSet.getString("descricao"));
            product.setPrice(resultSet.getDouble("preco"));
            product.setQuantity(resultSet.getInt("quantidade"));
            product.setPicture(resultSet.getString("foto"));
        }

        if (product == null) {
            resultSet.close();
            preparedStatement.close();
            closeConnection(connection);

            throw new Exception("Não foi possível obter este produto");
        }

        List<Category> categories = new ArrayList<>();;

        sqlQuery = "SELECT id_produto, id_categoria, cat.descricao as descricao "
                + "FROM produto_categoria "
                + "INNER JOIN categoria as cat ON (cat.id = id_categoria) "
                + "WHERE id_produto = ?";
        preparedStatement = connection.prepareStatement(sqlQuery);
        preparedStatement.setInt(1, id);

        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Category category = new Category();
            category.setId(resultSet.getInt("id_categoria"));
            category.setDescription(resultSet.getString("descricao"));

            categories.add(category);
        }

        resultSet.close();
        preparedStatement.close();
        closeConnection(connection);

        product.setCategories(categories);

        return product;
    }

    public Product get(String description) throws Exception {
        Product product = null;
        Connection connection = getConnection();

        String sqlQuery = "SELECT id, descricao, preco, quantidade, foto "
                + "FROM produto "
                + "WHERE descricao LIKE ?";

        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
        preparedStatement.setString(1, description);

        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            product = new Product();
            product.setId(resultSet.getInt("id"));
            product.setDescription(resultSet.getString("descricao"));
            product.setPrice(resultSet.getDouble("preco"));
            product.setQuantity(resultSet.getInt("quantidade"));
            product.setPicture(resultSet.getString("foto"));
        }

        if (product == null) {
            resultSet.close();
            preparedStatement.close();
            closeConnection(connection);

            throw new Exception("Não foi possível obter este produto");
        }

        List<Category> categories = new ArrayList<>();

        sqlQuery = "SELECT id_produto, id_categoria, cat.descricao as descricao "
                + "FROM produto_categoria "
                + "INNER JOIN categoria as cat ON (cat.id = id_categoria) "
                + "WHERE id_produto = ?";

        preparedStatement = connection.prepareStatement(sqlQuery);
        preparedStatement.setInt(1, product.getId());

        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Category category = new Category();
            category.setId(resultSet.getInt("id_categoria"));
            category.setDescription(resultSet.getString("descricao"));

            categories.add(category);
        }

        product.setCategories(categories);

        return product;
    }

    public List<Product> get(Category category) throws Exception {;
        List<Product> products = new ArrayList<>();
        List<Integer> productIds = new ArrayList<>();

        Connection connection = getConnection();

        String sqlQuery = "SELECT id_produto, id_categoria "
                + "FROM produto_categoria "
                + "INNER JOIN categoria as cat ON (cat.id = id_categoria) "
                + "WHERE cat.descricao LIKE ?";

        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
        preparedStatement.setString(1, category.getDescription());

        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            productIds.add(resultSet.getInt("id_produto"));
        }

        if (productIds.isEmpty()) {
            resultSet.close();
            preparedStatement.close();
            closeConnection(connection);

            throw new Exception("Não existe produto com esta categoria");
        }

        for (int idProduct : productIds) {
            products.add(get(idProduct));
        }

        return products;
    }

    public List<Product> getAll() throws Exception {
        List<Product> products = new ArrayList<Product>();
        List<Integer> productIds = new ArrayList<>();
        Connection connection = getConnection();

        String sqlQuery = "SELECT id, descricao, preco, quantidade, foto FROM produto";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            productIds.add(resultSet.getInt("id"));
        }

        if (productIds.isEmpty()) {
            resultSet.close();
            preparedStatement.close();
            closeConnection(connection);

            throw new Exception("Não foi possível obter um produto");
        }

        for (int idProduct : productIds) {
            products.add(get(idProduct));
        }

        return products;
    }

    public List<Product> getAllIndex() throws Exception {
        List<Product> products = new ArrayList<Product>();
        List<Integer> productIds = new ArrayList<>();
        Connection connection = getConnection();

        String sqlQuery = "SELECT id, descricao, preco, quantidade, foto "
                + "FROM produto "
                + "WHERE quantidade > 0";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            productIds.add(resultSet.getInt("id"));
        }

        if (productIds.isEmpty()) {
            resultSet.close();
            preparedStatement.close();
            closeConnection(connection);

            throw new Exception("Não foi possível obter um produto");
        }

        for (int idProduct : productIds) {
            products.add(get(idProduct));
        }

        return products;
    }

    private int getLastId() throws Exception {
        Connection connection = getConnection();
        int numberProducts = 0;

        String sqlQuery = "SELECT max(id) as number FROM produto";
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
