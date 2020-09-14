package model.administrator;

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
public class AdministratorDAO {

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

    public void delete(int id) throws Exception {
        Connection connection = getConnection();

        String sqlQuery = "DELETE FROM administrador WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
        preparedStatement.setInt(1, id);

        int result = preparedStatement.executeUpdate();
        preparedStatement.close();
        closeConnection(connection);

        if (result != 1) {
            throw new Exception("Não foi possível remover este administrador");
        }
    }

    public void update(Administrator administrator, int id) throws Exception {
        Connection connection = getConnection();

        String sqlQuery = "UPDATE administrador SET nome = ?, email = ?, login = ?, senha = ? WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
        preparedStatement.setString(1, administrator.getName());
        preparedStatement.setString(2, administrator.getEmail());
        preparedStatement.setString(3, administrator.getLogin());
        preparedStatement.setString(4, administrator.getPassword());
        preparedStatement.setInt(5, id);

        int result = preparedStatement.executeUpdate();
        preparedStatement.close();
        closeConnection(connection);

        if (result != 1) {
            throw new Exception("Não foi possível atualizar este administrador");
        }
    }

    public Administrator get(int id) throws Exception {
        Administrator administrator = null;
        Connection connection = getConnection();

        String sqlQuery = "SELECT id, nome, email, login, senha FROM administrador WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
        preparedStatement.setInt(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            administrator = new Administrator();
            administrator.setId(resultSet.getInt("id"));
            administrator.setName(resultSet.getString("nome"));
            administrator.setEmail(resultSet.getString("email"));
            administrator.setLogin(resultSet.getString("login"));
            administrator.setPassword(resultSet.getString("senha"));
        }

        resultSet.close();
        preparedStatement.close();
        closeConnection(connection);

        if (administrator == null) {
            throw new Exception("Não foi possível obter este administrador");
        }

        return administrator;
    }

    public Administrator get(String login) throws Exception {
        Administrator administrator = null;
        Connection connection = getConnection();

        String sqlQuery = "SELECT id, nome, email, login, senha FROM administrador WHERE login = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
        preparedStatement.setString(1, login);

        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            administrator = new Administrator();
            administrator.setId(resultSet.getInt("id"));
            administrator.setName(resultSet.getString("nome"));
            administrator.setEmail(resultSet.getString("email"));
            administrator.setLogin(resultSet.getString("login"));
            administrator.setPassword(resultSet.getString("senha"));
        }
        resultSet.close();
        preparedStatement.close();

        closeConnection(connection);

        if (administrator == null) {
            throw new Exception("Não foi possível obter este administrador");
        }

        return administrator;
    }

    public List<Administrator> getAll() throws Exception {
        List<Administrator> administrators = new ArrayList<Administrator>();
        Connection connection = getConnection();

        String sqlQuery = "SELECT id, nome, email, login, senha FROM administrador";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Administrator administrator = new Administrator();
            administrator.setId(resultSet.getInt("id"));
            administrator.setName(resultSet.getString("nome"));
            administrator.setEmail(resultSet.getString("email"));
            administrator.setLogin(resultSet.getString("login"));
            administrator.setPassword(resultSet.getString("senha"));

            administrators.add(administrator);
        }
        resultSet.close();
        preparedStatement.close();

        closeConnection(connection);

        if (administrators.isEmpty()) {
            throw new Exception("Não foi possível obter um administrator");
        }

        return administrators;
    }
}
