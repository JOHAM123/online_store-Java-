package com.joham.onlineshopping.controller;

import java.util.List;

import com.joham.onlineshopping.modal.ApiResponse;
import com.joham.onlineshopping.modal.Category;
import com.joham.onlineshopping.service.Category.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path= "/category/")
public class CategoryController {


     @Autowired 
     CategoryService categoryService;
     
    @GetMapping(path="getAllCategory", produces = "application/json")
    public List<Category> getAllCategory() {
        return categoryService.getAllCategory();
    }

     
    @PostMapping(value="admin/saveOrEditProduct")
    public ApiResponse<Category> saveOrEditCategory(@RequestBody Category category){
        return new ApiResponse<>(HttpStatus.OK.value(), "Product saved successfully.",categoryService.saveOrEditCategory(category));
    }
 
}