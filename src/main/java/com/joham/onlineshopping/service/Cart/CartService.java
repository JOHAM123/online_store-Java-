
package com.joham.onlineshopping.service.Cart;


import java.util.Optional;

import com.joham.onlineshopping.modal.Cart;


public interface CartService {

	void saveCart(Integer prodId,String usename,Integer quantity);

	Optional<Cart> showCart(String username);

	void deleteById(Integer userId);

	void deleteCartItems(Integer cartId, Integer cartdtlId, Double total);

	Optional<Cart> getCartCount(String name);


}