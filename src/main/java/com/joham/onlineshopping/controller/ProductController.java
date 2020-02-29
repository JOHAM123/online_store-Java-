package com.joham.onlineshopping.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joham.onlineshopping.modal.ApiResponse;
import com.joham.onlineshopping.modal.Category;
import com.joham.onlineshopping.modal.FetchProduct;
import com.joham.onlineshopping.dao.ProductDao;
import com.joham.onlineshopping.modal.Product;
import com.joham.onlineshopping.service.Category.CategoryService;
import com.joham.onlineshopping.service.Products.ProductService;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(path= "/product/")
public class ProductController {

    @Autowired
    ProductService productService;
    
    @Autowired
    CategoryService categoryService;
    
    
    @GetMapping(path="getAllProduct", produces = "application/json")
    public List<Product> getAllProduct() {
        return productService.getAllProduct();
    }


    
    @GetMapping(path="getProductById/{id}")
    
    public @ResponseBody Product getProductById(@PathVariable("id") Integer id) {

    return productService.getProductById(id).orElse(null);
    }

    @PostMapping(value="admin/saveOrEditProduct")
    public ApiResponse<Product> saveOrEditProduct(@RequestBody Product product){
        return new ApiResponse<>(HttpStatus.OK.value(), "Product saved successfully.",productService.saveOrEditProduct(product));
    }
 
    
    
    // @PostMapping(path="admin/saveOrEditProduct")
    // public @ResponseBody String saveOrEditProduct(@ModelAttribute("Product") Product product) {
    
    //     return productService.saveOrEditProduct(product);
    // }
    
    @DeleteMapping(path="admin/deleteProductById/{uId}")
    public @ResponseBody String deleteProductById(@PathVariable("uId") Integer uId) {
        
		return productService.deleteProductById(uId);
    }
}