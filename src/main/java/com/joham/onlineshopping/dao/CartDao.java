
package com.joham.onlineshopping.dao;

import java.util.List;
import java.util.Optional;

import com.joham.onlineshopping.modal.Cart;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CartDao extends CrudRepository<Cart , Integer >{
	@Transactional
	@Query("from Cart where user.username = ?1 ")
	Optional<Cart> findByUserName(String username);

	@Query(value = " Select c.*, cd.ID as cartdtlId , cd.QUANTITY , "
	+"cd.TOTAL , cd.UNIT_PRICE , cd.PRODUCT_ID , " 
	+"p.PRODUCT_NAME,p.IMAGE_URL1 " 
	+"from  CART c " 
	+"left join CART_DETAILS cd on cd.CART_ID = c.CART_ID  " 
	+"left join PRODUCT p on cd.PRODUCT_ID = p.ID  " 
	+"where c.USER_ID in (select  USER_ID  from  USER  where UPPER(username) = UPPER( ?1 )) " , nativeQuery = true)
	List<Object[]>  getCartDetailsList(String username);

}