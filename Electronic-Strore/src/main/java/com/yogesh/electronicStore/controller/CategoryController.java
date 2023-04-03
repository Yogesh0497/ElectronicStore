package com.yogesh.electronicStore.controller;

import com.yogesh.electronicStore.myConfig.AppConstant;
import com.yogesh.electronicStore.response.ApiResponse;
import com.yogesh.electronicStore.payloads.CategoryDto;
import com.yogesh.electronicStore.response.CategoryResponse;
import com.yogesh.electronicStore.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/category")
public class CategoryController {

    Logger logger = LoggerFactory.getLogger(CategoryController.class);
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/create")
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto) {

        logger.info("Initiating request for create a category");

        CategoryDto categoryDto1 = this.categoryService.create(categoryDto);

        logger.info("Completion request for create a category");

        return new ResponseEntity<>(categoryDto1, HttpStatus.CREATED);


    }

    @PutMapping("/update/{categoryId}")
    public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto, @PathVariable Long categoryId) {

        logger.info("Initiating request for update category");

        CategoryDto update = this.categoryService.update(categoryDto, categoryId);

        logger.info("Completion request for update category");

        return new ResponseEntity<>(update, HttpStatus.OK);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable Long categoryId) {

        logger.info("Initiating request for get category");

        CategoryDto byCategoryId = this.categoryService.getByCategoryId(categoryId);

        logger.info("Completion request for get category");

        return new ResponseEntity<>(byCategoryId, HttpStatus.OK);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Long categoryId) {

        logger.info("Initiating request for delete category");

        this.categoryService.deleteByCategoryId(categoryId);

        logger.info("Completion request for delete category");

        return new ResponseEntity<ApiResponse>(new ApiResponse(AppConstant.CATEGORY_DELETE, true), HttpStatus.OK);
    }
    @GetMapping("/getAllCategory")
    public ResponseEntity<CategoryResponse> getAllCategory(
            @RequestParam(value = "pageNumber", defaultValue = AppConstant.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = AppConstant.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstant.SORTED_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstant.SORT_DIR, required = false) String sortDir)
    {
        CategoryResponse categories = this.categoryService.getAllCategories(pageSize, pageNumber, sortBy, sortDir);

        return new ResponseEntity<CategoryResponse>(categories,HttpStatus.OK);
    }

}





















