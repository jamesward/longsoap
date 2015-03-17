package services.cart;

import models.Cart;
import models.CartItem;

import javax.jws.WebService;

@WebService
public interface CartService {

    public Cart get();

    public void add(CartItem item);

}
