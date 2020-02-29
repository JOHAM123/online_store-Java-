package com.joham.onlineshopping.service.Users;

import java.util.List;

import com.joham.onlineshopping.dao.UserDao;
import com.joham.onlineshopping.modal.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    UserDao userDao;
     
    @Override
    public List<User> getAllUser() {
        return null;
    }

    @Override
    public User getUserById() {
        return null;
    }

    @Override
    public User saveOrEditUser(User user) {
        return userDao.save(user);
    }

    @Override
    public String deleteUserById() {
        return null;
    }


}