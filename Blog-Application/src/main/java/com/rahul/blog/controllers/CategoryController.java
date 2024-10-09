package com.rahul.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rahul.blog.payloads.ApiResponse;
import com.rahul.blog.payloads.CategoryDto;
import com.rahul.blog.services.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categorys")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	//create
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto) {
		CategoryDto saveCategory = this.categoryService.createCategory(categoryDto);
		return new ResponseEntity<CategoryDto>(saveCategory, HttpStatus.CREATED);
	}
	
	//update
	@PutMapping("/{catId}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto, @PathVariable Long catId) {
		CategoryDto updateCategory = this.categoryService.updateCategory(categoryDto, catId);
		return new ResponseEntity<CategoryDto>(updateCategory, HttpStatus.OK);
	}
	
	//delete
	@DeleteMapping("/{catId}")
	public ResponseEntity<ApiResponse> DeleteCategoryDto(@PathVariable Long catId) {
		this.categoryService.deleteCategory(catId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Category delete successfully !!", true), HttpStatus.OK);
	}
	
	//GetCategoryId
	@GetMapping("/{catId}")
	public ResponseEntity<CategoryDto> getCategoryId(@PathVariable Long catId) {
		CategoryDto categoryDto = this.categoryService.getCategoryId(catId);
		return new ResponseEntity<CategoryDto>(categoryDto, HttpStatus.OK);
	}
	
	//GetAllCategory
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getAllCategory() {
		List<CategoryDto> categoryDtoList = this.categoryService.getCategoryAll();
		return ResponseEntity.ok(categoryDtoList);
	}

}
