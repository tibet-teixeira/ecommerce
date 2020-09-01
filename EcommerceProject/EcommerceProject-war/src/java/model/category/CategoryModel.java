package model.category;

/**
 *
 * @author Tibet
 */
public class CategoryModel {

    public Category get(String descricao) throws Exception {
        CategoryDAO categoryDAO = new CategoryDAO();
        return categoryDAO.get(descricao);
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
