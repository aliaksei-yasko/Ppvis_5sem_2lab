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
public class Recipe {

    private String name = "";
    private String description = "";
    private int number = -1;
    private List<Ingredient> ingridients = new ArrayList<Ingredient>();
    private List<Advice> advices = new ArrayList<Advice>();
    private boolean choosen = false;
    private Category category = null;

    public Recipe() {
    }

    public boolean isChoosen() {
        return choosen;
    }

    public void setChoosen(boolean chosen) {
        this.choosen = chosen;
    }

    boolean isContainsName(String str) {
        if (name.indexOf(str) == -1) {
            return false;
        } else {
            return true;
        }
    }

    boolean isContainsIngridient(String name) {

        for (Ingredient current : ingridients) {
            if (current.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public List<Advice> getAdvices() {
        return advices;
    }

    public void addAdvice(Advice advice) {
        this.advices.add(advice);
    }

    public List<Ingredient> getIngridients() {
        return ingridients;
    }

    public void addIngridient(Ingredient ingridient) {
        this.ingridients.add(ingridient);
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
