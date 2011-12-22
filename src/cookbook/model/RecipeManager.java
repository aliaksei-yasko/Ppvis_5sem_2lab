/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cookbook.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author SeeDuke
 */
public class RecipeManager {

    private List<Recipe> recipes = new ArrayList<Recipe>();

    public RecipeManager() {
    }

    public boolean addRecipe(Recipe recipe) {
        return recipes.add(recipe);
    }

    public boolean deleteRecipe(String name) {
        return recipes.remove(this.getRecipeByName(name));
    }

    public List<Recipe> getAllRecipes() {
        return recipes;
    }

    public Recipe getRecipeByName(String name) {
        for (Recipe x : recipes) {
            if (x.getName().equals(name)) {
                return x;
            }
        }
        return null;
    }

    public List<Recipe> getRecipesByIngredient(String ingredient) {
        List<Recipe> matched = new ArrayList<Recipe>();
        for (Recipe x : recipes) {
            if (x.isContainsIngridient(ingredient)) {
                matched.add(x);
            }
        }
        return matched;
    }

    public List<Recipe> getChosenRecipes() {
        List<Recipe> chosen = new ArrayList<Recipe>();
        for (Recipe x : recipes) {
            if (x.isChoosen()) {
                chosen.add(x);
            }
        }
        return chosen;
    }

    public boolean deleteFromChoosen(String name) {
        if (this.getRecipeByName(name) != null) {
            this.getRecipeByName(name).setChoosen(false);
            return true;
        }
        return false;
    }
}
