
package com.joham.onlineshopping.service.Cart;

import com.joham.onlineshopping.dao.CartDetailsDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartDetailsServiceImpl implements CartDetailsService {

	@Autowired
	CartDetailsDao cartDetailsDao;


	@Override
	public void deleteById(Integer cartId) {
		cartDetailsDao.deleteById(cartId);
	}

}