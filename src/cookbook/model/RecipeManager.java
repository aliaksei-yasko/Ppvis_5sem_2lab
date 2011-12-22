/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cookbook.model;

import java.util.List;

/**
 *
 * @author SeeDuke
 */
public class RecipeManager {

    private List<Recipe> recipes = null;

    public RecipeManager() {
    }

    public boolean addRecipe(Recipe recipe) {
        return recipes.add(recipe);
    }

    public boolean deleteRecipe(Recipe recipe) {
        return recipes.remove(recipe);
    }

    public Recipe getRecipeByName(String name) {
        for (Recipe x : recipes) {
            if (x.getName() == name) {
                return x;
            }
        }
        return null;
    }
}
