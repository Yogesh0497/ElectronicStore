package com.yogesh.electronicStore.service;

import com.yogesh.electronicStore.payloads.ProductDto;
import com.yogesh.electronicStore.response.ProductResponse;

import java.util.List;

public interface ProductService {
    //Create
    ProductDto createProduct(ProductDto productDto);

    //Update
    ProductDto update(ProductDto productDto, Long productId);

    //Get By I'd
    ProductDto getById(Long productId);

    //GetAll
    ProductResponse getAllProduct(int pageNumber, int pageSize, String sortBy, String sortDir);

    //Delete By Id
    void deleteById(Long productId);

    ProductDto searchByTitle(String title);

}
