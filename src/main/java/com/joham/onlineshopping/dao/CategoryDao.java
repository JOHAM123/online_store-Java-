
package com.joham.onlineshopping.dao;

import java.util.List;

import javax.transaction.Transactional;

import com.joham.onlineshopping.modal.Category;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public interface CategoryDao extends CrudRepository<Category , Integer >{

	@Query(value = "Select id,name,description,IMAGE_URL from category", nativeQuery = true)
	List<Object[]> getAllCategory();


}