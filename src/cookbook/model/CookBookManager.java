/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cookbook.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 *
 * @author Admin
 */
public class CookBookManager {

    private CategoryManager categoryManager = new CategoryManager();
    private RecipeManager recipeManager = new RecipeManager();

    public CookBookManager() {
        try {
            Connection connection = this.getConnection();
            Statement statement = connection.createStatement();

            String sql = "select * from recipe";
            ResultSet resultRecipes = statement.executeQuery(sql);

            while (resultRecipes.next()) {
                Recipe recipe = new Recipe();
                recipe.setName(resultRecipes.getString("recipe"));
                recipe.setNumber(resultRecipes.getInt("id_recipe"));
                recipe.setDescription(resultRecipes.getString("text"));

                String sqlAdvice = "select * from advice where id_recipe = ?";
                PreparedStatement pStateAdvice = connection.prepareStatement(sqlAdvice);
                pStateAdvice.setInt(1, recipe.getNumber());
                ResultSet resultAdvice = pStateAdvice.executeQuery();

                while (resultAdvice.next()) {
                    Advice advice = new Advice();
                    advice.setDescription(resultAdvice.getString("advice"));
                    recipe.addAdvice(advice);
                }

                String sqlIngredient = "select * from ingredient where id_recipe = ?";
                PreparedStatement pStateIngredient = connection.prepareStatement(sqlIngredient);
                pStateIngredient.setInt(1, recipe.getNumber());
                ResultSet resultIngredient = pStateIngredient.executeQuery();

                while (resultIngredient.next()) {
                    Ingredient ingredient = new Ingredient();
                    ingredient.setName(resultIngredient.getString("ingredient"));
                    ingredient.setDimension(resultIngredient.getString("dimension"));
                    ingredient.setQuantity(resultIngredient.getString("quantity"));
                    recipe.addIngridient(ingredient);
                }

                String sqlCategory = "select * from category where id_recipe = ?";
                PreparedStatement pStateCategory = connection.prepareStatement(sqlCategory);
                pStateCategory.setInt(1, recipe.getNumber());
                ResultSet resultCategory = pStateCategory.executeQuery();

                if (resultCategory.next()) {
                    Category category = new Category();
                    category.setName(resultCategory.getString("category"));
                    recipe.setCategory(category);
                }

                recipeManager.addRecipe(recipe);
            }

            String sqlCat = "select * from category";
            Statement statCat = connection.createStatement();
            ResultSet resultCat = statCat.executeQuery(sqlCat);

            while (resultCat.next()) {
                Category category = new Category();
                category.setName(resultCat.getString("category"));
                categoryManager.addCategory(category);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public Connection getConnection() throws SQLException {
        String username = "alex";
        String password = "alex";
        String url = "jdbc:derby://localhost:1527/CookBook";

        return DriverManager.getConnection(url, username, password);
    }

    public void addNewCategory(Category category) {
        categoryManager.addCategory(category);
    }

    public void addNewRecipe(Recipe recipe) {
        recipeManager.addRecipe(recipe);
    }

    public List<Recipe> getRecipesByIngredient(String name) {
        return recipeManager.getRecipesByIngredient(name);
    }

    public Recipe getRecipeByName(String name) {
        return recipeManager.getRecipeByName(name);
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