
package com.joham.onlineshopping.dao;

import java.util.List;

import com.joham.onlineshopping.modal.Product;
import com.joham.onlineshopping.modal.FetchProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Pageable;

@Repository
@Transactional
public interface ProductDao extends JpaRepository<Product, Integer> {

	// @Override
	// @Query("select product.name,product. from Product product ")
	// List<Product> findAll();

	
	@Query(value = "SELECT p.* FROM PRODUCT p where p.CATEGORY_ID = :catId AND p.UNIT_PRICE BETWEEN :min AND :max ", nativeQuery = true)
	List<Product> getProductWithoutFeature( @Param("catId") Integer catId, @Param("min") Integer min,@Param("max") Integer max,Pageable pageable);
	
	
	@Query(value = "SELECT p.* FROM PRODUCT p inner join PROD_SPECS_DETAIL c  on "
	+"p.id = c.PRODUCT_ID and p.CATEGORY_ID = :catId AND p.UNIT_PRICE  BETWEEN :min AND :max "
	+"AND c.FEATURE_ID in ( :featureId ) and c.ID  in ( :podSpecDtlId ) ", nativeQuery = true)
	List<Product> getProductWithFeature( @Param("catId") Integer catId, @Param("min") Integer min,@Param("max") Integer max,@Param("featureId") List<Integer> featureId,@Param("podSpecDtlId") List<Integer> podSpecDtlId,Pageable pageable);


}