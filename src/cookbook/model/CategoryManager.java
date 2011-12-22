/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cookbook.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class CategoryManager {
    List<Category> categores = new ArrayList<Category>();

    public CategoryManager() {

    }

    public void addCategory(Category category) {
        this.categores.add(category);
    }

    public Category getCategoryByName(String name) {
        for (Category current : categores) {
            if (current.getName().equals(name)) {
                return current;
            }
        }

        return null;
    }

    public List<Category> getAllCategory() {
        return this.categores;
    }
}
