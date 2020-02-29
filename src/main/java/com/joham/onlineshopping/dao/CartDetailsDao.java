
package com.joham.onlineshopping.dao;

import com.joham.onlineshopping.modal.CartDetails;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
@Repository
public interface CartDetailsDao extends CrudRepository<CartDetails , Integer >{

    @Transactional
    @Modifying
    @Query("Delete from CartDetails c where c.id =?1")
    void deleteById(Integer cartId);

}