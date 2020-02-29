package com.joham.onlineshopping.service.Users;

import com.joham.onlineshopping.modal.User;
import java.util.List;


public interface UsersService {

    public List<User> getAllUser();
    public User getUserById();
    public User saveOrEditUser(User user);
    public String deleteUserById();
}