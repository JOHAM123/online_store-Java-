
package com.joham.onlineshopping.service.Category;

import java.lang.reflect.Field;
import java.util.List;

import com.joham.onlineshopping.modal.Category;


public interface CategoryService {


	List<Category> getAllCategory();




	List<Category> showCategory();
	Category saveOrEditCategory(Category category);

	List<Category> showProductByCategory(Integer catId);

	void deleteCat(Integer catId);

	// Field[]  getColumns();


}