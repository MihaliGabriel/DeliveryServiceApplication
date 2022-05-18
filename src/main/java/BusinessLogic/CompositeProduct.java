package BusinessLogic;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Reprezinta produsele compuse ca si meniurile.
 * Extinde clasa MenuItem
 * Este alcatuita din mai multe produse de baza.
 */
public class CompositeProduct extends MenuItem{
    ArrayList<MenuItem> products;
    private static final long serialversionUID =
            129348939L;
    public CompositeProduct(String title, Double rating, int calories, int protein, int fat, int sodium) {
        super(title, rating, calories, protein, fat, sodium, 0);
    }
    public void setProducts(ArrayList<MenuItem> prod) {
        this.products = prod;
    }
    public int computePrice() {
        int sumPrice = 0;
        for(MenuItem product : products) {
            sumPrice = sumPrice + product.getPrice();
        }
        setPrice(sumPrice);
        return sumPrice;
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
