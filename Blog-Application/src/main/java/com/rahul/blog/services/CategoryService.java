package com.rahul.blog.services;

import java.util.List;


import com.rahul.blog.payloads.CategoryDto;

public interface CategoryService {
	
	//create
	CategoryDto createCategory(CategoryDto categoryDto);
	//update
	CategoryDto updateCategory(CategoryDto categoryDto, Long catId);
	//getId
	CategoryDto getCategoryId(Long catId);
	//getAll
	List<CategoryDto> getCategoryAll();
	//delete
	void deleteCategory(Long catId);

}
