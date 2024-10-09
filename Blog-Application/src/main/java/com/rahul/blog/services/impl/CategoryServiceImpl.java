package com.rahul.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rahul.blog.entities.Category;
import com.rahul.blog.exceptions.ResourceNotFoundExceptions;
import com.rahul.blog.payloads.CategoryDto;
import com.rahul.blog.repositories.CategoryRepo;
import com.rahul.blog.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category category = this.modelMapper.map(categoryDto, Category.class);
		Category addCategory = this.categoryRepo.save(category);
		return this.modelMapper.map(addCategory, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Long catId) {
		Category category = this.categoryRepo.findById(catId).orElseThrow(() -> new ResourceNotFoundExceptions("Category", "id", catId));
		category.setCategoryTitle(categoryDto.getCategoryTitle());
		category.setCategoryDescription(categoryDto.getCategoryDescription());
		Category updateCat = this.categoryRepo.save(category);
		return this.modelMapper.map(updateCat, CategoryDto.class);
	}

	@Override
	public CategoryDto getCategoryId(Long catId) {
		Category category = this.categoryRepo.findById(catId).orElseThrow(() -> new ResourceNotFoundExceptions("Category", "id", catId));
		return this.modelMapper.map(category, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getCategoryAll() {
		List<Category> categoryList = this.categoryRepo.findAll();
		List<CategoryDto> categoryDtoList = categoryList.stream()
				.map(category -> this.modelMapper.map(category, CategoryDto.class)).collect(Collectors.toList());
		return categoryDtoList;
	}

	@Override
	public void deleteCategory(Long catId) {
		Category category = this.categoryRepo.findById(catId).orElseThrow(() -> new ResourceNotFoundExceptions("Category", "id", catId));
		this.categoryRepo.delete(category);
	}

}
