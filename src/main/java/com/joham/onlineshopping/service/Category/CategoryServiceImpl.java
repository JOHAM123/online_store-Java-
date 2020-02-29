
package com.joham.onlineshopping.service.Category;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.joham.onlineshopping.dao.CategoryDao;
import com.joham.onlineshopping.dao.ProductDao;
import com.joham.onlineshopping.modal.Category;
import com.joham.onlineshopping.util.FileUpload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryDao categoryDao;

	@Autowired
	ProductDao productDao;

	@Autowired
	FileUpload fileUpload;

	@Override
	public Category saveOrEditCategory(Category category) {
		// if(!category.getFile().getOriginalFilename().isEmpty() ) {
		// String imageUrl = fileUpload.uploadFile(category.getFile());
		// category.setImageUrl(imageUrl);
		// }
		return categoryDao.save(category);
	}

	@Override
	public List<Category> showProductByCategory(Integer catId) {
		List<Category> category = new ArrayList<>();
		if (catId == null) {
			category = (List<Category>) categoryDao.findAll();
		} else {
			category.add(categoryDao.findById(catId).get());
		}
		return category;
	}

	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public CategoryServiceImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<Category> showCategory() {

		final String sql = "Select id,name,description,image_url from category";
		List<Category> categoryList = jdbcTemplate.query(sql, (result, i) -> {
			Category category = new Category();
			category.setId(result.getInt("id"));
			category.setName(result.getString("name"));
			category.setDescription(result.getString("description"));
			category.setImageUrl(result.getString("image_url"));

			return category;
		});

		return categoryList;

	}

	@Override
	public void deleteCat(Integer catId) {
		categoryDao.deleteById(catId);
	}

	@Override
	public List<Category> getAllCategory() {
		List<Object[]> categoryListObject = categoryDao.getAllCategory();

		List<Category> categoryList = categoryListObject.stream().map(CategoryServiceImpl::setCategory)
				.collect(Collectors.toList());

		return categoryList;
	}

	public static Category setCategory(Object[] object) {

		Category category = new Category();
		category.setId((Integer) object[0]);
		category.setName((String) object[1]);
		category.setDescription((String) object[2]);
		category.setImageUrl((String) object[3]);
		return category;
	}

	// @Override
	// public Field[] getColumns() {
	
	// 	Field[] str =  Category.class.getDeclaredFields();
	// 	// String[] str =  [
	// 	// 	{ "title" : "ID", field: "id" ,editable : "false"},
	// 	// 	{ "title": "Name", "field": "name" },
	// 	// 	{ "title": "DESCRIPTION", "field": "description" }]
	// 	return str;
	// }
}
