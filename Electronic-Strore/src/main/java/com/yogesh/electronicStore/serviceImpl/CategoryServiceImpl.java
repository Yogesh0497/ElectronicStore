package com.yogesh.electronicStore.serviceImpl;

import com.yogesh.electronicStore.exception.ResourceNotFoundException;
import com.yogesh.electronicStore.model.Category;
import com.yogesh.electronicStore.myConfig.AppConstant;
import com.yogesh.electronicStore.payloads.CategoryDto;
import com.yogesh.electronicStore.payloads.CategoryResponse;
import com.yogesh.electronicStore.repository.CategoryRepo;
import com.yogesh.electronicStore.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);
    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private ModelMapper mapper;
    @Override
    public CategoryDto create(CategoryDto categoryDto) {

        logger.info("Initiating step to save category");

        Category category = this.mapper.map(categoryDto, Category.class);

        category.setIsactive(AppConstant.YES);

        Category save = this.categoryRepo.save(category);

        logger.info("Completion step to save category");

        return this.mapper.map(save, CategoryDto.class);
    }

    @Override
    public CategoryDto update(CategoryDto categoryDto, Long categoryId) {

        logger.info("Initiating step to update category");

        Category category = this.categoryRepo.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException(AppConstant.CATEGORY_NOT_FOUND));

        category.setTitle(categoryDto.getTitle());

        category.setDescription(categoryDto.getDescription());

        category.setCoverImage(categoryDto.getCoverImage());

        Category update = this.categoryRepo.save(category);

        logger.info("Completion step to update category");

        return this.mapper.map(update, CategoryDto.class);
    }

    @Override
    public CategoryDto getByCategoryId(Long categoryId) {

        logger.info("Initiating step to get category");

        Category getCategory = this.categoryRepo.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException(AppConstant.CATEGORY_NOT_FOUND));

        logger.info("Completion step to get category");

        return this.mapper.map(getCategory, CategoryDto.class);
    }

    //Get all category
    @Override
    public CategoryResponse getCategories(int pageSize, int pageNumber, String sortBy, String sortDir) {

        logger.info("Initiating Dao call for get all category");

        Sort sort=(sortDir.equalsIgnoreCase("asc"))?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();

        PageRequest page = PageRequest.of(pageSize, pageNumber, sort);

        Page<Category> all = this.categoryRepo.findAll(page);

        List<Category> content = all.getContent();

        List<CategoryDto> collect = content.stream().map((cat) -> this.mapper.map(cat, CategoryDto.class))
                .collect(Collectors.toList());

        CategoryResponse categoryResponse = new CategoryResponse();

        categoryResponse.setContent(collect);

        categoryResponse.setPageSize(all.getSize());

        categoryResponse.setPageNumber(all.getNumber());

        categoryResponse.setTotalElement(all.getTotalElements());

        categoryResponse.setTotalPage(all.getTotalPages());

        categoryResponse.setLastPage(all.isLast());

        logger.info("Completed Dao call for get all category");

        return categoryResponse;
    }

    @Override
    public void deleteByCategoryId(Long categoryId) {

        logger.info("Initiating step to delete category");

        Category deleteCategory = this.categoryRepo.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException(AppConstant.CATEGORY_NOT_FOUND));

        deleteCategory.setIsactive(AppConstant.NO);

        this.categoryRepo.save(deleteCategory);

        logger.info("Completion step to delete category");

    }


}
