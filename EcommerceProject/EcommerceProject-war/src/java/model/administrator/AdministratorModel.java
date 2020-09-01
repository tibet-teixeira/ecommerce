package model.administrator;

/**
 *
 * @author Tibet
 */
public class AdministratorModel {

    public Administrator get(String login) throws Exception {
        if (login == null || login.trim().length() == 0) {
            return null;
        }
        AdministratorDAO administratorDAO = new AdministratorDAO();
        return administratorDAO.get(login);
    }

    public boolean identifier(String login, String password) throws Exception {
        if (login == null 
                || login.trim().length() == 0 
                || password == null 
                || password.trim().length() == 0) {
            return false;
        }
        Administrator administrator = get(login);
        return (administrator.getPassword().equals(password));
    }
}
