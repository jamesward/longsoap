package models;

import java.util.ArrayList;
import java.util.List;

public class Cart {

    private List<CartItem> items = new ArrayList<CartItem>();

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    public Double total() {
        Double total = 0.0;

        for (CartItem cartItem : items) {
            total += (cartItem.getPrice() * cartItem.getQuantity());
        }

        return total;
    }

}
