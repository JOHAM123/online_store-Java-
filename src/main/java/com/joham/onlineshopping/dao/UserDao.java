
package com.joham.onlineshopping.dao;

import java.util.Optional;

import com.joham.onlineshopping.modal.User;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface UserDao extends CrudRepository<User , Integer >{

    @Query("FROM User where UPPER(username) = UPPER( ?1 )")
    Optional<User> findByUsername(String username);


}