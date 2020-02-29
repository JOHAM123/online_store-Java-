
package com.joham.onlineshopping.service.Address;

import com.joham.onlineshopping.dao.AddressDao;
import com.joham.onlineshopping.dao.UserDao;
import com.joham.onlineshopping.modal.Address;
import com.joham.onlineshopping.modal.User;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {

	
    // @Autowired
	// PasswordEncoder passwordEncoder;

	@Autowired
	AddressDao addressDao;

	@Autowired
	UserDao userDao;
	
	// @Override
	// public void saveAddressAndUser(Address address) {

	// 	User user = address.getUser();
	// 	user.setPassword(passwordEncoder.encode(user.getPassword()));
	// 	address.setUser(user);
	// 	addressDao.save(address);
	// }

	@Override
	public void saveAddress(Address address, String username) {
		User user = userDao.findByUsername(username).orElse(new User());
		address.setUser(user);			
		addressDao.save(address);

	}




}