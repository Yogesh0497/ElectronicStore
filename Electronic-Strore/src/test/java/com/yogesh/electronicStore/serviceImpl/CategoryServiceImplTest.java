package com.yogesh.electronicStore.serviceImpl;

import com.yogesh.electronicStore.BaseTest;
import com.yogesh.electronicStore.exception.ResourceNotFoundException;
import com.yogesh.electronicStore.model.Category;
import com.yogesh.electronicStore.model.User;
import com.yogesh.electronicStore.payloads.CategoryDto;
import com.yogesh.electronicStore.payloads.UserDto;
import com.yogesh.electronicStore.repository.CategoryRepo;

import com.yogesh.electronicStore.response.CategoryResponse;
import com.yogesh.electronicStore.response.UserPageableResponse;
import com.yogesh.electronicStore.service.CategoryService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CategoryServiceImplTest extends BaseTest {

    @MockBean
    private CategoryRepo categoryRepo;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ModelMapper mapper;

    Category category;

    Category category1;

    List<Category> categories;

    CategoryDto categoryDto;

    @BeforeEach
    public void init() {
        categoryDto = CategoryDto.builder().title("Mobile").description("Mobile available in 10% discount")
                .coverImage("abcdef.png").build();

        category = Category.builder().title("Mobile").description("Mobile available in 10% discount")
                .coverImage("abcdef.png").build();

//        category = Category.builder().title("Laptop").description("laptop available in 20% discount")
//                .coverImage("abcd.png").build();

        category1 = Category.builder().title("VideoGame").description("VideoGame available in 15% discount")
                .coverImage("cdef.png").build();

        categories = new ArrayList<>();
        categories.add(category);
        categories.add(category1);
    }

    @Test
    void saveCategory() {
//        Arrange
        Mockito.when(categoryRepo.save(Mockito.any())).thenReturn(category);
//        Act
        CategoryDto category2 = categoryService.create(categoryDto);
//        Assert
        Assertions.assertNotNull(category2);
        Assertions.assertEquals(categoryDto.getTitle(), category2.getTitle());
    }


    @Test
    void updateCategory() {
        Long id = 10L;
        //Arrange
        Mockito.when(categoryRepo.findById(id)).thenReturn(Optional.of(category));
        Mockito.when(categoryRepo.save(Mockito.any())).thenReturn(category);
        //Act
        CategoryDto updateCategory = categoryService.update(categoryDto, id);
        //Assert
        Assertions.assertNotNull(updateCategory);
        Assertions.assertEquals(categoryDto.getTitle(), updateCategory.getTitle());
        Assertions.assertThrows(ResourceNotFoundException.class, () -> categoryService.update(categoryDto, 111L));
    }
    // //   if we use soft delete then we use code as bellow

    @Test
    void getByCategoryId() {

        Long id = 10l;
//        Arrange
        Mockito.when(categoryRepo.findById(id)).thenReturn(Optional.of(category));
//        Act
        CategoryDto categoryDto1 = categoryService.getByCategoryId(id);
//        Assert
        Assertions.assertNotNull(categoryDto1);
    }

    @Test
    void getAllCategories() {

        //Arrange
        List<Category>categoryList = Arrays.asList(category,category1);
        Page<Category> page = new PageImpl<>(categoryList);
        Mockito.when(categoryRepo.findAll((Pageable)Mockito.any())).thenReturn(page);
        //Act
        CategoryResponse allUser = categoryService.getAllCategories(1,2,"title","asc");
//        $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
        //Assert
        Assertions.assertEquals(2, allUser.getContent().size());
    }

    @Test
    void deleteByCategoryId() {

        Long id = 10l;
        //Arrange
        Mockito.when(categoryRepo.findById(id)).thenReturn(Optional.of(category));
        //Act
        categoryService.deleteByCategoryId(id);
        //Assert
        Assertions.assertEquals("No", category.getIsactive());
        Assertions.assertThrows(ResourceNotFoundException.class, ()-> categoryService.deleteByCategoryId(111l));
    }
}