/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cookbook.model;

/**
 *
 * @author Admin
 */
public class Advice {

    private String description = "";

    public Advice() {
    }

    public Advice(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
