package model.customer;

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
public class CustomerDAO {

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

    public void insert(Customer customer) throws Exception {
        int id = getLastId();
        
        Connection connection = getConnection();

        String sqlQuery = "INSERT INTO cliente (id, nome, email, login, senha, rua, numero, cidade, estado, cep) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
        preparedStatement.setInt(1, id + 1);
        preparedStatement.setString(2, customer.getName());
        preparedStatement.setString(3, customer.getEmail());
        preparedStatement.setString(4, customer.getLogin());
        preparedStatement.setString(5, customer.getPassword());
        preparedStatement.setString(6, customer.getStreet());
        preparedStatement.setString(7, customer.getHouseNumber());
        preparedStatement.setString(8, customer.getCity());
        preparedStatement.setString(9, customer.getState());
        preparedStatement.setString(10, customer.getCep());

        int result = preparedStatement.executeUpdate();
        preparedStatement.close();
        closeConnection(connection);

        if (result != 1) {
            throw new Exception("Não foi possível inserir este cliente");
        }
    }

    public void update(Customer customer, int id) throws Exception {
        Connection connection = getConnection();

        String sqlQuery = "UPDATE cliente SET nome = ?, email = ?, login = ?, senha = ?, rua = ?, numero = ?, cidade = ?, estado = ?, cep = ? WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
        preparedStatement.setString(1, customer.getName());
        preparedStatement.setString(2, customer.getEmail());
        preparedStatement.setString(3, customer.getLogin());
        preparedStatement.setString(4, customer.getPassword());
        preparedStatement.setString(5, customer.getStreet());
        preparedStatement.setString(6, customer.getHouseNumber());
        preparedStatement.setString(7, customer.getCity());
        preparedStatement.setString(8, customer.getState());
        preparedStatement.setString(9, customer.getCep());
        preparedStatement.setInt(10, id);

        int result = preparedStatement.executeUpdate();
        preparedStatement.close();
        closeConnection(connection);

        if (result != 1) {
            throw new Exception("Não foi possível atualizar este cliente");
        }
    }

    public void delete(int id) throws Exception {
        Connection connection = getConnection();

        String sqlQuery = "DELETE FROM cliente WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
        preparedStatement.setInt(1, id);

        int result = preparedStatement.executeUpdate();
        preparedStatement.close();
        closeConnection(connection);

        if (result != 1) {
            throw new Exception("Não foi possível remover este cliente");
        }
    }

    public Customer get(int id) throws Exception {
        Customer customer = null;
        Connection connection = getConnection();

        String sqlQuery = "SELECT id, nome, email, login, senha, rua, numero, cidade, estado, cep FROM cliente WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
        preparedStatement.setInt(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            customer = new Customer();
            customer.setId(resultSet.getInt("id"));
            customer.setName(resultSet.getString("nome"));
            customer.setEmail(resultSet.getString("email"));
            customer.setLogin(resultSet.getString("login"));
            customer.setPassword(resultSet.getString("senha"));
            customer.setStreet(resultSet.getString("rua"));
            customer.setHouseNumber(resultSet.getString("numero"));
            customer.setCity(resultSet.getString("cidade"));
            customer.setState(resultSet.getString("estado"));
            customer.setCep(resultSet.getString("cep"));
        }

        resultSet.close();
        preparedStatement.close();
        closeConnection(connection);

        if (customer == null) {
            throw new Exception("Não foi possível obter este cliente");
        }

        return customer;
    }

    public Customer get(String login) throws Exception {
        Customer customer = null;
        Connection connection = getConnection();

        String sqlQuery = "SELECT id, nome, email, login, senha, rua, numero, cidade, estado, cep FROM cliente WHERE login = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
        preparedStatement.setString(1, login);

        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            customer = new Customer();
            customer.setId(resultSet.getInt("id"));
            customer.setName(resultSet.getString("nome"));
            customer.setEmail(resultSet.getString("email"));
            customer.setLogin(resultSet.getString("login"));
            customer.setPassword(resultSet.getString("senha"));
            customer.setStreet(resultSet.getString("rua"));
            customer.setHouseNumber(resultSet.getString("numero"));
            customer.setCity(resultSet.getString("cidade"));
            customer.setState(resultSet.getString("estado"));
            customer.setCep(resultSet.getString("cep"));
        }
        resultSet.close();
        preparedStatement.close();

        closeConnection(connection);

        if (customer == null) {
            throw new Exception("Não foi possível obter este cliente");
        }

        return customer;
    }

    public List<Customer> getAll() throws Exception {
        List<Customer> customers = new ArrayList<Customer>();
        Connection connection = getConnection();

        String sqlQuery = "SELECT id, nome, email, login, senha, rua, numero, cidade, estado, cep FROM cliente";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Customer customer = new Customer();
            customer.setId(resultSet.getInt("id"));
            customer.setName(resultSet.getString("nome"));
            customer.setEmail(resultSet.getString("email"));
            customer.setLogin(resultSet.getString("login"));
            customer.setPassword(resultSet.getString("senha"));
            customer.setStreet(resultSet.getString("rua"));
            customer.setHouseNumber(resultSet.getString("numero"));
            customer.setCity(resultSet.getString("cidade"));
            customer.setState(resultSet.getString("estado"));
            customer.setCep(resultSet.getString("cep"));

            customers.add(customer);
        }
        resultSet.close();
        preparedStatement.close();

        closeConnection(connection);

        if (customers.isEmpty()) {
            throw new Exception("Não foi possível obter um cliente");
        }

        return customers;
    }

    private int getLastId() throws Exception {
        Connection connection = getConnection();
        int numberCustomers = 0;
        
        String sqlQuery = "SELECT max(id) as number FROM cliente";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            numberCustomers = Integer.parseInt(resultSet.getString("number"));
        }
        resultSet.close();
        preparedStatement.close();

        closeConnection(connection);

        return numberCustomers;
    }
}
