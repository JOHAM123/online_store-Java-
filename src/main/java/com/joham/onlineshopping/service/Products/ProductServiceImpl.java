
package com.joham.onlineshopping.service.Products;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import com.joham.onlineshopping.dao.ProductDao;
import com.joham.onlineshopping.modal.FetchProduct;
import com.joham.onlineshopping.modal.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

	private static List<Product> productList = new ArrayList<>();
	
	static {

		Product product;
		product = new Product();

		product.setId(1);
		product.setName("Product 1");
		product.setUnitPrice(432.0);
		
		productList.add(product);

		product = new Product();
		product.setId(2);
		product.setName("Product 2");
		product.setUnitPrice(432.0);
		
		productList.add(product);

	}
	
	@Autowired
	ProductDao productDao;
	

	@Override
	public Optional<Product> getProductById(Integer id) {
	
		return productDao.findById(id);
	}

	@Override
	public String saveOrEditProduct(Product product) {

		try {
			
			productDao.save(product);

		} catch (Exception e) {
		
			return "Failed "+ e; 
		}
	return "success";
	}

	@Override
	public String deleteProductById(Integer id) {
	
	List<Product> productList = productDao.findAll();

		for(Product product: productList) {

			if(product.getId() == id) {

				productDao.deleteById(id);
				return "success";
			}
		}
	return "Product Not Exist for " + id;
	}

	@Override
	public List<Product> getAllProduct() {
		return productList;

	}


}