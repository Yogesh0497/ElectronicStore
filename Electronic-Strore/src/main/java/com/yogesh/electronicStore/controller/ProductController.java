package com.yogesh.electronicStore.controller;


import com.yogesh.electronicStore.myConfig.AppConstant;
import com.yogesh.electronicStore.payloads.ProductDto;
import com.yogesh.electronicStore.response.ApiResponse;
import com.yogesh.electronicStore.response.ProductResponse;
import com.yogesh.electronicStore.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
@Slf4j
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;
    @PostMapping("/create")
    public ResponseEntity<ProductDto> createProduct(@Valid @RequestBody ProductDto productDto){

        log.info("Initiating request for create a product");

        ProductDto product = this.productService.createProduct(productDto);

        log.info("Completion request for create a product");

        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<ProductDto> updateProduct(@Valid @RequestBody ProductDto productDto, @PathVariable Long productId){

        log.info("Initiating request for update a product");

        ProductDto update = this.productService.update(productDto,productId);

        log.info("Completion request for update a product");

        return new ResponseEntity<>(update, HttpStatus.CREATED);
    }

    @GetMapping("/getById/{productId}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable Long productId){

        log.info("Initiating request for get a product");

        ProductDto get = this.productService.getById(productId);

        log.info("Completion request for get a product");

        return new ResponseEntity<>(get, HttpStatus.OK);
    }

    @GetMapping("/getByTitle/{title}")
    public ResponseEntity<ProductDto> searchProduct(@PathVariable String title){

        log.info("Initiating request for search a product");

        ProductDto search = this.productService.searchByTitle(title);

        log.info("Completion request for search a product");

        return new ResponseEntity<>(search, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<ProductResponse> getAllProduct(
            @RequestParam(value = "pageNumber", defaultValue = AppConstant.PAGE_NUMBER, required = false)int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = AppConstant.PAGE_SIZE, required = false)int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstant.SORTING_BY, required = false)String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstant.SORT_DIR, required = false)String sortDir)
    {
        log.info("Initiating request for get all product");

        ProductResponse allProduct = this.productService.getAllProduct(pageNumber, pageSize, sortBy, sortDir);

        log.info("Completion request for get all product");

        return new ResponseEntity<>(allProduct, HttpStatus.FOUND);
    }
,
    @DeleteMapping("/deleteById/{productId}")

    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable Long productId){

        log.info("Initiating request for delete a product");

        this.productService.deleteById(productId);

        log.info("Completion request for delete a product");

        return new ResponseEntity<ApiResponse>(new ApiResponse(AppConstant.PRODUCT_DELETE, true), HttpStatus.OK);
    }

}
