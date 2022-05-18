package BusinessLogic;

import java.util.Objects;

/**
 * Clasa care reprezinta produsele de baza.
 * Extinde clasa MenuItem
 * Face override la metoda computePrice()
 */
public class BaseProduct extends MenuItem{
    private static final long serialversionUID =
            129348939L;
    public BaseProduct(String title, Double rating, int calories, int protein, int fat, int sodium, int price) {
        super(title, rating, calories, protein, fat, sodium, price);
    }
    public BaseProduct() {
    }

    @Override
    public int computePrice() {
        return getPrice();
    }
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
    public int hashCode() {
        return Objects.hash(getTitle());
    }
}
