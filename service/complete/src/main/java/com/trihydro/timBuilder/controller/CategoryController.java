package com.trihydro.timBuilder.controller;

import org.springframework.web.bind.annotation.RestController;
import com.trihydro.timBuilder.dao.CategoryService;
import com.trihydro.timBuilder.model.Category;

import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.beans.factory.annotation.Autowired;

@CrossOrigin
@RestController
public class CategoryController {

	private final CategoryService categoryService;
	
	@Autowired
	CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	// select all ITIS codes
	@RequestMapping(value="/categories",method = RequestMethod.GET,headers="Accept=application/json")
  	public List<Category> selectAllCategories() { 
   		List<Category> categories = categoryService.selectAll();
   		return categories;
  	}
}
