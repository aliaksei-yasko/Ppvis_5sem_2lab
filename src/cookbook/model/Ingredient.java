/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cookbook.model;

/**
 *
 * @author Admin
 */
public class Ingredient {
    String name = "";
    String quantity = "";
    String dimension = "";;

    public Ingredient() {

    }

    public String getDimension() {
        return dimension;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
