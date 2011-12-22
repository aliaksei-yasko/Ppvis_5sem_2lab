/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cookbook.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;


/**
 *
 * @author Admin
 */
public class CookBookManager {
    private CategoryManager categoryManager = new CategoryManager();
    private RecipeManager recipeManager = new RecipeManager();

    public CookBookManager() {
        List<Recipe> recipes = SelectFromDataBase.SelectAllRecipe();
        if (recipes != null) {
            for (Recipe current : recipes) {
                recipeManager.addRecipe(current);
            }
        }

        List<Category> categorys = SelectFromDataBase.SelectAllCategory();
        if (categorys != null) {
            for (Category current : categorys) {
                categoryManager.addCategory(current);
            }
        }
    }

    public Connection getConnection() throws SQLException{
        String username = "alex";
        String password = "alex";
        String url = "jdbc:derby://localhost:1527/CookBook";

        return DriverManager.getConnection(url, username, password);
    }

    public void addNewCategory(Category category){
        categoryManager.addCategory(category);
    }

    public void addNewRecipe(Recipe recipe) {
        recipeManager.addRecipe(recipe);
    }

    public List<Recipe> getRecipesByIngredient(String name) {
        return recipeManager.getRecipesByIngredient(name);
    }

    public List<Category> getAllCategory() {
        return categoryManager.getAllCategory();
    }

    public Category getCategoryByName(String name) {
        return categoryManager.getCategoryByName(name);
    }

    public List<Recipe> getAllRecipes() {
        return recipeManager.getAllRecipes();
    }

    public boolean deleteRecipe(String name) {
        return recipeManager.deleteRecipe(name);
    }

    public boolean deleteFromChoosen(String name) {
        return recipeManager.deleteFromChoosen(name);
    }

    public void getAllChoosenRecipe() {

    }
}