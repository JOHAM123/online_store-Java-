
package com.joham.onlineshopping.service.OrderItems;

import com.joham.onlineshopping.modal.Address;
import com.joham.onlineshopping.modal.OrderItem;

public interface OrderItemService {

	OrderItem getOrderItems(Address address,String username); 

}