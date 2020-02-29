package com.joham.onlineshopping.service.Users;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.joham.onlineshopping.dao.UserDao;
import com.joham.onlineshopping.modal.Address;
import com.joham.onlineshopping.modal.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService, UserService {

  int PASSWORD_LENGTH = 10;

  @Autowired
  UserDao userDao;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    User user = userDao.findByUsername(username).orElse(null);
    UserBuilder builder = null;
    if (user != null) {

      builder = org.springframework.security.core.userdetails.User.withUsername(username);
      builder.disabled(!user.getActive());
      builder.password(user.getPassword());
      builder.roles(user.getRole());
    } else {
      throw new UsernameNotFoundException("User not found.");
    }
    return builder.build();
  }

  @Autowired
  PasswordEncoder passwordEncoder;

  @Override
  public User saveUser(User user) {

    if (user.getPassword().length() < PASSWORD_LENGTH) {
      user.setPassword(passwordEncoder.encode(user.getPassword()));
    }

    if (!user.getAddress().isEmpty()) {

      List<Address> address =  user.getAddress()
      .parallelStream()
      .map(addr -> { addr.setUser(user); return addr;})
      .collect(Collectors.toList());

      user.setAddress(address);
    }

    userDao.save(user);
 return user;
  }


  @Override
  public List<User> showUser() {
    return  (List<User>) userDao.findAll();
  }

  @Override
  public Optional<User> findByUserName(String username) {
    return userDao.findByUsername(username);
  }

  @Override
  public void deleteById(Integer userId) {
    userDao.deleteById(userId);
  }



}