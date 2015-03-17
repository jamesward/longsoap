package services.cart;

import models.Cart;
import models.CartItem;

import javax.jws.WebService;
import java.util.ArrayList;

@WebService(endpointInterface = "services.cart.CartService")
public class CartServiceImpl implements CartService {

    @Override
    public Cart get() {
        CartItem item1 = new CartItem();
        item1.setName("Widget");
        item1.setPrice(2.39);
        item1.setQuantity(1);

        CartItem item2 = new CartItem();
        item1.setName("Foo");
        item1.setPrice(5.99);
        item1.setQuantity(2);

        Cart cart = new Cart();
        cart.setItems(new ArrayList<CartItem>());
        cart.getItems().add(item1);
        cart.getItems().add(item2);

        return cart;
    }

    @Override
    public void add(CartItem item) {
        // nop
    }

}