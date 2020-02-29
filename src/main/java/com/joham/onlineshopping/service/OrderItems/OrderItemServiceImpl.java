
package com.joham.onlineshopping.service.OrderItems;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.joham.onlineshopping.dao.AddressDao;
import com.joham.onlineshopping.dao.CartDao;
import com.joham.onlineshopping.dao.OrderItemDao;
import com.joham.onlineshopping.dao.ProductDao;
import com.joham.onlineshopping.dao.UserDao;
import com.joham.onlineshopping.modal.Address;
import com.joham.onlineshopping.modal.Cart;
import com.joham.onlineshopping.modal.CartDetails;
import com.joham.onlineshopping.modal.OrderDetail;
import com.joham.onlineshopping.modal.OrderItem;
import com.joham.onlineshopping.modal.Product;
import com.joham.onlineshopping.modal.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderItemServiceImpl implements OrderItemService {

	@Autowired
	UserDao userDao;

	@Autowired
	AddressDao addressDao;

	@Autowired
	CartDao cartDao;

	@Autowired
	OrderItemDao orderItemDao;

	@Autowired
	ProductDao productDao;
	
	@Override
	public OrderItem getOrderItems(Address address, String username) {

		User user = userDao.findByUsername(username).orElse(null);
		address.setUser(user);			
		Address adr = addressDao.save(address);
		Cart cart = cartDao.findByUserName(username).orElse(new Cart());
		
		Timestamp createdDate = new Timestamp(System.currentTimeMillis());
		OrderItem orderItem = new OrderItem();
		List<OrderDetail> orderDetailList = new ArrayList<>();
		OrderDetail orderDetail = new OrderDetail();
		List<Product> productList = new ArrayList<>();
		
		
		orderItem.setUser(user);
		orderItem.setGrandTotal(cart.getGrandTotal());
		orderItem.setItemCount(cart.getItemCount());
		orderItem.setCreatedDate(createdDate);
		orderItem.setOrderdetail(orderDetailList);
		
		for(CartDetails cartDetails: cart.getCartdetails()) {
		
			orderDetail = new OrderDetail();
			orderDetail.setAddressId(adr.getId());
			orderDetail.setCreatedDate(createdDate);
			orderDetail.setProduct(cartDetails.getProduct());
			orderDetail.setQuantity(cartDetails.getQuantity());
			orderDetail.setTotal(cartDetails.getTotal());
			orderDetail.setUnitPrice(cartDetails.getUnitPrice());
			orderDetail.setOrderItems(orderItem);
			orderDetailList.add(orderDetail);
	
			Product product = productDao.findById(cartDetails.getProduct().getId()).get();
			product.setQuantity(product.getQuantity() - cartDetails.getQuantity());
			productList.add(product);

		}
		

	

		orderItemDao.save(orderItem);

		cartDao.delete(cart);
		productDao.saveAll(productList);
		return orderItem;
	}


}