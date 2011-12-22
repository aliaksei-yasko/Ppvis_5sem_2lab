/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cookbook.model;

import java.util.List;

/**
 *
 * @author Admin
 */
public class CookBookManager {
    private CategoryManager categoryManager = new CategoryManager();
    private RecipeManager recipeManager = new RecipeManager();

    public CookBookManager() {

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
