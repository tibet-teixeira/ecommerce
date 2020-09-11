package model.customer;

/**
 *
 * @author Tibet
 */
public class CustomerModel {

    public Customer get(String login) throws Exception {
        if (login == null || login.trim().length() == 0) {
            return null;
        }
        CustomerDAO customerDAO = new CustomerDAO();
        return customerDAO.get(login);
    }
    
    public Customer get(int id) throws Exception {
        CustomerDAO customerDAO = new CustomerDAO();
        return customerDAO.get(id);
    }

    public boolean identifier(String login, String password) throws Exception {
        if (login == null 
                || login.trim().length() == 0 
                || password == null 
                || password.trim().length() == 0) {
            return false;
        }
        Customer customer = get(login);
        return (customer.getPassword().equals(password));
    }

    public void insert(Customer customer) throws Exception {
        if (customer == null 
                || customer.getName() == null 
                || customer.getEmail() == null 
                || customer.getLogin() == null 
                || customer.getPassword() == null 
                || customer.getStreet() == null 
                || customer.getHouseNumber() == null 
                || customer.getCity() == null 
                || customer.getState() == null 
                || customer.getCep() == null 
                || customer.getName().trim().length() == 0
                || customer.getEmail().trim().length() == 0
                || customer.getLogin().trim().length() == 0
                || customer.getPassword().trim().length() == 0
                || customer.getStreet().trim().length() == 0 
                || customer.getHouseNumber().trim().length() == 0 
                || customer.getCity().trim().length() == 0
                || customer.getState().trim().length() == 0
                || customer.getCep().trim().length() == 0) {
            throw new Exception("Não foram informados todos os campos obrigatórios");
        }
        
        CustomerDAO customerDAO = new CustomerDAO();
        customerDAO.insert(customer);
    }
}
