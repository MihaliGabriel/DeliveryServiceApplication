package BusinessLogic;

import java.io.Serializable;
import java.util.Objects;

/**
 * Clasa de tip abstract, este Serializable.
 * Este clasa cea mai de baza a produselor.
 * Este parintele claselor BaseProduct si CompositeProduct.
 * Face override la hashCode() si equals().
 */
public abstract class MenuItem implements Serializable {
    private static final long serialversionUID =
            129348939L;
    private String title;
    private Double rating;
    private int calories;
    private int protein;
    private int fat;
    private int sodium;
    private int price;

    public MenuItem() {

    }
    public MenuItem(String title, Double rating, int calories, int protein, int fat, int sodium, int price) {
        this.title = title;
        this.rating = rating;
        this.calories = calories;
        this.protein = protein;
        this.fat = fat;
        this.sodium = sodium;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public int getProtein() {
        return protein;
    }

    public void setProtein(int protein) {
        this.protein = protein;
    }

    public int getFat() {
        return fat;
    }

    public void setFat(int fat) {
        this.fat = fat;
    }

    public int getSodium() {
        return sodium;
    }

    public void setSodium(int sodium) {
        this.sodium = sodium;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public abstract int computePrice();

    @Override
    public boolean equals(Object other) {
        boolean result;
        if((other == null) || (getClass() != other.getClass())){
            result = false;
        }
        else {
            MenuItem otherItem = (MenuItem)other;
            result = getTitle().equals(otherItem.getTitle());
        }
        return result;
    }
    @Override
    public String toString() {
        return
                title;
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }
}
