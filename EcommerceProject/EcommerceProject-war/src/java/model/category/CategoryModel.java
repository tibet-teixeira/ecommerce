package model.category;

import java.util.List;

/**
 *
 * @author Tibet
 */
public class CategoryModel {

    public Category get(int id) throws Exception {
        CategoryDAO categoryDAO = new CategoryDAO();
        return categoryDAO.get(id);
    }
    
    public Category get(String descricao) throws Exception {
        CategoryDAO categoryDAO = new CategoryDAO();
        return categoryDAO.get(descricao);
    }
    
    public List<Category> getAll() throws Exception {
        CategoryDAO categoryDAO = new CategoryDAO();
        return categoryDAO.getAll();
    }

    public void insert(Category category) throws Exception {
        if (category == null
                || category.getDescription() == null
                || category.getDescription().trim().length() == 0) {
            throw new Exception("Não foram informados todos os campos obrigatórios");
        }

        CategoryDAO categoryDAO = new CategoryDAO();
        categoryDAO.insert(category);
    }
}
