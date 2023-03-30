package com.yogesh.electronicStore.service;

import com.yogesh.electronicStore.payloads.CategoryDto;
import com.yogesh.electronicStore.response.CategoryResponse;


public interface CategoryService {

     //Create
     CategoryDto create(CategoryDto categoryDto);

     //Update
     CategoryDto update(CategoryDto categoryDto, Long categoryId);

     //Get
     CategoryDto getByCategoryId(Long categoryId);

     //GetAll
     //PageableResponse<CategoryDto> getAllCategory(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);
     CategoryResponse getCategories(int pageSize, int pageNumber, String sortBy, String sortDir);
     //Delete
     void deleteByCategoryId(Long categoryId);


}
