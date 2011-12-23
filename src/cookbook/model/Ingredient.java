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
