
package com.joham.onlineshopping.service.Products;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.joham.onlineshopping.modal.FetchProduct;
import com.joham.onlineshopping.modal.FilterCriteria;
import com.joham.onlineshopping.modal.Product;

import java.util.Optional;


public interface ProductService {

	List<Product> getAllProduct();
	Optional<Product> getProductById(Integer id);

	String saveOrEditProduct(Product product);

	String deleteProductById(Integer id);




	// void saveProduct(Product prod, HttpServletRequest request,String username);

	// List<Product> showProduct();

	// void deleteById(Integer userId);

	// Product showProductById(Integer prodId);

	// List<Product> showProducts(FilterCriteria filter, Integer catId);

	// List<Product> showProductByBestSeller();

}