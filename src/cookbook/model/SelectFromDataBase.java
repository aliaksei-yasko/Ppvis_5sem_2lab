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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class SelectFromDataBase {


    public static Connection getConnection() throws SQLException{
        String username = "alex";
        String password = "alex";
        String url = "jdbc:derby://localhost:1527/CookBook";

        return DriverManager.getConnection(url, username, password);
    }

    public static List<Recipe> SelectAllRecipe(){
        List<Recipe> list = new ArrayList<Recipe>();
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();

            String sql = "select * from recipe";
            ResultSet resultRecipes = statement.executeQuery(sql);

            while(resultRecipes.next()) {
                Recipe recipe = new Recipe();
                recipe.setName(resultRecipes.getString("recipe"));
                recipe.setNumber(resultRecipes.getInt("id_recipe"));
                recipe.setDescription(resultRecipes.getString("text"));

                String sqlAdvice = "select * from advice where id_recipe = ?";
                PreparedStatement pStateAdvice = connection.prepareStatement(sqlAdvice);
                pStateAdvice.setInt(1, recipe.getNumber());
                ResultSet resultAdvice = pStateAdvice.executeQuery();

                while(resultAdvice.next()) {
                    Advice advice = new Advice();
                    advice.setDescription(resultAdvice.getString("advice"));
                    recipe.addAdvice(advice);
                }

                String sqlIngredient = "select * from ingredient where id_recipe = ?";
                PreparedStatement pStateIngredient = connection.prepareStatement(sqlIngredient);
                pStateIngredient.setInt(1, recipe.getNumber());
                ResultSet resultIngredient = pStateIngredient.executeQuery();

                while(resultIngredient.next()) {
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

                if(resultCategory.next()) {
                    Category category = new Category();
                    category.setName(resultCategory.getString("category"));
                    recipe.setCategory(category);
                }
                list.add(recipe);
            }

            return list;
        } catch (SQLException ex) {
                ex.printStackTrace();
                return null;
        }
    }

    public static List<Category> SelectAllCategory(){
        try {
            String sqlCat = "select * from category";
            Connection connection = getConnection();
            Statement statCat = connection.createStatement();
            ResultSet resultCat = statCat.executeQuery(sqlCat);
            List<Category> list = new ArrayList<Category>();

            while (resultCat.next()) {
                Category category = new Category();
                category.setName(resultCat.getString("category"));
                list.add(category);
            }

            return list;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
