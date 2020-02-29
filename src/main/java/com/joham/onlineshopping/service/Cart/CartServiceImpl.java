
package com.joham.onlineshopping.service.Cart;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.joham.onlineshopping.dao.CartDao;
import com.joham.onlineshopping.dao.CartDetailsDao;
import com.joham.onlineshopping.dao.ProductDao;
import com.joham.onlineshopping.dao.UserDao;
import com.joham.onlineshopping.modal.Cart;
import com.joham.onlineshopping.modal.CartDetails;
import com.joham.onlineshopping.modal.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	CartDao cartDao;

	@Autowired
	CartDetailsDao cartDetailsDao;

	@Autowired
	ProductDao prodcuctDao;

	@Autowired
	UserDao userDao;

	@Override
	public Optional<Cart> showCart(String username) {
		
		
		return  cartDao.findByUserName(username);
	}

	public Cart getCartList( String username) {

		List<Object[]> cartList = cartDao.getCartDetailsList(username);
		Cart cart = new Cart();

		List<CartDetails> cartDetailsList = new ArrayList<>();
		
		
		cartList.parallelStream().forEach(cartItemObject -> {

			CartDetails	cartDetails = new CartDetails();
			cartDetails.setId((Integer) cartItemObject[5]);
			cartDetails.setQuantity((Integer) cartItemObject[6]);
			cartDetails.setTotal((Double) cartItemObject[7]);
			cartDetails.setUnitPrice((Double) cartItemObject[8]);
			
			Product product = new Product();
			product.setId((Integer) cartItemObject[9]);
			product.setImageUrl1((String) cartItemObject[11]);
			product.setName((String) cartItemObject[10]);

			cartDetails.setProduct(product);

			cartDetailsList.add(cartDetails); 

			});

			 cart.setCartdetails(cartDetailsList);
			 cart.setCreatedDate((Timestamp) cartList.get(0)[1]);
			 cart.setId((Integer) cartList.get(0)[0]);
			 cart.setItemCount((Integer) cartList.get(0)[3]);
			 cart.setGrandTotal((Double) cartList.get(0)[2]);
		return cart;
	}

	@Override
	public void saveCart(Integer prodId, String username,Integer quantity) {

		Timestamp createdDate = new Timestamp(System.currentTimeMillis());
		Product prod = prodcuctDao.findById(prodId).orElse(new Product());
		Double Total = quantity * prod.getUnitPrice();

		Cart cart = cartDao.findByUserName(username).orElse(null);
		List<CartDetails> cartdetailsList;
	

		if(cart == null) {

			cart  = new Cart(); 
			cart.setCreatedDate(createdDate);
			cart.setGrandTotal(Total);
			cart.setUser(userDao.findByUsername(username).orElse(null));
			cartdetailsList  = new ArrayList<>();
		} else {
			cart.setGrandTotal(Total + cart.getGrandTotal());
			cartdetailsList  = cart.getCartdetails();
		}

		int isProdExist=0;
		for(CartDetails product : cartdetailsList) {

			if(product.getProduct().getId() == prodId) {
				product.setQuantity( product.getQuantity() + quantity);
				isProdExist=1;	
			    break;
			}
		}
	
		if(isProdExist==0)
		{

			CartDetails cartdetails = new CartDetails();
			cartdetails.setCart(cart);
			cartdetails.setCreatedDate(createdDate);
			cartdetails.setProduct(prod);
			cartdetails.setQuantity(quantity);
			cartdetails.setUnitPrice(prod.getUnitPrice());
			cartdetails.setTotal(Total);
			cartdetailsList.add(cartdetails);
		}

		cart.setCartdetails(cartdetailsList);
		cart.setItemCount(cartdetailsList.size());
		cartDao.save(cart);
	}


	@Override
	public void deleteById(Integer cartId) {
		cartDao.deleteById(cartId);
	}


	@Override
	public void deleteCartItems(Integer cartId, Integer cartdtlId, Double total) {
		Cart cart = cartDao.findById(cartId).get();
		Integer itemCount = cart.getItemCount();
		
		if(itemCount > 1 ) {
			cart.setGrandTotal(cart.getGrandTotal() - total);
			cart.setItemCount(itemCount - 1);
			cartDao.save(cart);
			cartDetailsDao.deleteById(cartdtlId);
		}  else {
			cartDao.deleteById(cartId);
		}


	}

	@Override
	public Optional<Cart> getCartCount(String name) {
				return Optional.of(getCartList(name));
	}


}