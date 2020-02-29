
package com.joham.onlineshopping.service.Users;

import java.util.List;
import java.util.Optional;

import com.joham.onlineshopping.modal.User;


public interface UserService {

	User saveUser(User user);

	List<User> showUser();

	void deleteById(Integer userId);

	Optional<User> findByUserName(String username);



}