
package com.joham.onlineshopping.dao;

import java.util.List;

import com.joham.onlineshopping.modal.Reviews;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ReviewsDao extends CrudRepository<Reviews , Integer >{

	List<Reviews> findByProdId(Integer prodId);



}