package model.category;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tibet
 */
public class CategoryDAO {

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

    public void insert(Category category) throws Exception {
        int id = getLastId();
        
        Connection connection = getConnection();

        String sqlQuery = "INSERT INTO categoria (id, descricao) VALUES (?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
        preparedStatement.setInt(1, id + 1);
        preparedStatement.setString(2, category.getDescription());

        int result = preparedStatement.executeUpdate();
        preparedStatement.close();
        closeConnection(connection);

        if (result != 1) {
            throw new Exception("Não foi possível inserir esta categoria");
        }
    }

    public void update(Category category, int id) throws Exception {
        Connection connection = getConnection();

        String sqlQuery = "UPDATE categoria SET descricao = ? WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
        preparedStatement.setString(1, category.getDescription());
        preparedStatement.setInt(2, id);

        int result = preparedStatement.executeUpdate();
        preparedStatement.close();
        closeConnection(connection);

        if (result != 1) {
            throw new Exception("Não foi possível atualizar esta categoria");
        }
    }

    public void delete(int id) throws Exception {
        Connection connection = getConnection();

        String sqlQuery = "DELETE FROM categoria WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
        preparedStatement.setInt(1, id);

        int result = preparedStatement.executeUpdate();
        preparedStatement.close();
        closeConnection(connection);

        if (result != 1) {
            throw new Exception("Não foi possível remover esta categoria");
        }
    }

    public Category get(int id) throws Exception {
        Category category = null;
        Connection connection = getConnection();

        String sqlQuery = "SELECT id, descricao FROM categoria WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
        preparedStatement.setInt(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            category = new Category();
            category.setId(resultSet.getInt("id"));
            category.setDescription(resultSet.getString("descricao"));
        }

        resultSet.close();
        preparedStatement.close();
        closeConnection(connection);

        if (category == null) {
            throw new Exception("Não foi possível obter esta categoria");
        }

        return category;
    }
    
    public Category get(String description) throws Exception {
        Category category = null;
        Connection connection = getConnection();

        String sqlQuery = "SELECT id, descricao FROM categoria WHERE descricao = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
        preparedStatement.setString(1, description);

        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            category = new Category();
            category.setId(resultSet.getInt("id"));
            category.setDescription(resultSet.getString("descricao"));
        }

        resultSet.close();
        preparedStatement.close();
        closeConnection(connection);

        if (category == null) {
            throw new Exception("Não foi possível obter esta categoria");
        }

        return category;
    }

    public List<Category> getAll() throws Exception {
        List<Category> categories = new ArrayList<Category>();
        Connection connection = getConnection();

        String sqlQuery = "SELECT id, descricao FROM categoria";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Category category = new Category();
            category.setId(resultSet.getInt("id"));
            category.setDescription(resultSet.getString("descricao"));
            
            categories.add(category);
        }
        resultSet.close();
        preparedStatement.close();

        closeConnection(connection);

        if (categories.isEmpty()) {
            throw new Exception("Não foi possível obter uma categoria");
        }

        return categories;
    }

    private int getLastId() throws Exception {
        Connection connection = getConnection();
        int numberCategorys = 0;
        
        String sqlQuery = "SELECT max(id) as number FROM categoria";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            numberCategorys = Integer.parseInt(resultSet.getString("number"));
        }
        resultSet.close();
        preparedStatement.close();

        closeConnection(connection);

        return numberCategorys;
    }
}
