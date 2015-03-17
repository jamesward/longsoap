package services.cart;

import models.Cart;
import models.CartItem;

import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;

@WebService(endpointInterface = "services.cart.CartService")
public class CartServiceImpl implements CartService {

    private Cart cart = new Cart();

    @Override
    public Cart get() {
        return cart;
    }

    @Override
    public void add(CartItem item) {
        cart.getItems().add(item);
    }

}