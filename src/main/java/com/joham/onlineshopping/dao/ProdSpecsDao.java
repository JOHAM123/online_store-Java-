
package com.joham.onlineshopping.dao;

import java.util.List;

import com.joham.onlineshopping.modal.ProdSpecs;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
@Repository
@Transactional
public interface ProdSpecsDao extends CrudRepository<ProdSpecs, Integer> {

	@Modifying
	@Query(value = "select id,SPECS_NAME  From PROD_SPECS where category_id = :catId ", nativeQuery = true)
	List<Object[]> findByCategory(@Param("catId") Integer catId);
	
	@Query(value = "Select * from (SELECT p.ID as SPECS_ID, p.SPECS_NAME , f.FEATURE_VALUE , f.PRODUCT_ID , p.CATEGORY_ID , f.ID from PROD_SPECS p "  
	+"left join PROD_SPECS_DETAIL f on f.FEATURE_ID = p.id) where  CATEGORY_ID = :catId", nativeQuery = true)
	List<Object[]> shopFilters(@Param("catId") Integer catId);


}