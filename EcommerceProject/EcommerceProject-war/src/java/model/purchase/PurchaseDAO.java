package model.purchase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.customer.Customer;

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

        String sqlQuery = "";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

        int result = preparedStatement.executeUpdate();
        preparedStatement.close();
        closeConnection(connection);

        if (result != 1) {
            throw new Exception("Não foi possível inserir esta compra");
        }
    }

    public void update(Purchase purchase, int id) throws Exception {
        Connection connection = getConnection();

        String sqlQuery = "";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

        int result = preparedStatement.executeUpdate();
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

        String sqlQuery = "";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            purchase = new Purchase();
            
        }

        resultSet.close();
        preparedStatement.close();
        closeConnection(connection);

        if (purchase == null) {
            throw new Exception("Não foi possível obter esta compra");
        }

        return purchase;
    }

    public Purchase get(Customer customer) throws Exception {
        Purchase purchase = null;
        Connection connection = getConnection();

        String sqlQuery = "";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            purchase = new Purchase();
            
        }
        resultSet.close();
        preparedStatement.close();

        closeConnection(connection);

        if (purchase == null) {
            throw new Exception("Não foi possível obter esta compra");
        }

        return purchase;
    }

    public List<Purchase> getAll() throws Exception {
        List<Purchase> purchases = new ArrayList<Purchase>();
        Connection connection = getConnection();

        String sqlQuery = "";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Purchase purchase = new Purchase();
            

            purchases.add(purchase);
        }
        resultSet.close();
        preparedStatement.close();

        closeConnection(connection);

        if (purchases.isEmpty()) {
            throw new Exception("Não foi possível obter uma compra");
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
