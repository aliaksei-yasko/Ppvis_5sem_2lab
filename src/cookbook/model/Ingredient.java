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

    private String name = "";
    private String quantity = "";
    private String dimension = "";

    public Ingredient() {
    }

    public Ingredient(String name, String quantity, String dimension) {
        this.name = name;
        this.quantity = quantity;
        this.dimension = dimension;
    }

    public String getDimension() {
        return dimension;
    }

    public void setDimension(String dimension) {
         if (dimension == null) {
            return;
        }
        this.dimension = dimension;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
         if (name == null) {
            return;
        }
        this.name = name;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
         if (quantity == null) {
            return;
        }
        this.quantity = quantity;
    }
}
