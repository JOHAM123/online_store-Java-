
package com.joham.onlineshopping.dao;

import com.joham.onlineshopping.modal.ProdSpecsDetails;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
@Repository
public interface ProdSpecsDetailsDao extends CrudRepository<ProdSpecsDetails, Integer> {

    @Transactional
    @Query(value="Delete from PROD_SPECS_DETAIL where PRODUCT_ID = :prodId " ,nativeQuery = true)
	void deleteByProdId(@Param("prodId") Integer prodId);

}