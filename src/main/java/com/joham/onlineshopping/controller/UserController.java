package com.joham.onlineshopping.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joham.onlineshopping.modal.ApiResponse;
import com.joham.onlineshopping.modal.User;
import com.joham.onlineshopping.service.Users.UsersService;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
 @RequestMapping("/users/")
public class UserController {

    @Autowired
    private ConsumerTokenServices consumerTokenServices;
    
    @GetMapping(value="logout")
    public void logout(@RequestParam("token") String token){
        consumerTokenServices.revokeToken(token);
    }

    @Autowired
    UsersService userService;

    @GetMapping(value="admin/getAllUser")
    public List<User> getAllUser() {
        return userService.getAllUser();
    }
    
    @GetMapping(value="getUserById")
    public User getUserById(@RequestParam Integer id) {
        return userService.getUserById();
    }
    
    @PostMapping(value="admin/saveOrEditUser")
    public ApiResponse<User> saveUser(@RequestBody User user){
        return new ApiResponse<>(HttpStatus.OK.value(), "User saved successfully.",userService.saveOrEditUser(user));
    }
 
    @DeleteMapping(value="admin/deleteUserById/{uId}")
    public String deleteUserById(@PathVariable("uId") Integer uuId) {
        
        return userService.deleteUserById();
    }
}
