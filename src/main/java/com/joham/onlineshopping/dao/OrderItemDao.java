
package com.joham.onlineshopping.dao;

import com.joham.onlineshopping.modal.OrderItem;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface OrderItemDao extends CrudRepository<OrderItem, Integer> {


}