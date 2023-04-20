package com.yogesh.electronicStore.serviceImpl;

import com.yogesh.electronicStore.BaseTest;
import com.yogesh.electronicStore.exception.ResourceNotFoundException;
import com.yogesh.electronicStore.model.Product;
import com.yogesh.electronicStore.model.User;
import com.yogesh.electronicStore.payloads.ProductDto;
import com.yogesh.electronicStore.payloads.UserDto;
import com.yogesh.electronicStore.repository.ProductRepo;
import com.yogesh.electronicStore.repository.UserRepo;
import com.yogesh.electronicStore.response.ProductResponse;
import com.yogesh.electronicStore.response.UserPageableResponse;
import com.yogesh.electronicStore.service.ProductService;
import com.yogesh.electronicStore.service.UserService;
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

class ProductServiceImplTest extends BaseTest {

    @MockBean
    private ProductRepo productRepo;

    @Autowired
    private ProductService productService;

    @Autowired
    private ModelMapper mapper;

    Product product;

    Product product1;

    List<Product> products;

    ProductDto productDto;

    @BeforeEach
    public void init() {
        productDto = ProductDto.builder().title("Mobile").description("Mobile available at 20% discount").price(10000).discountedPrice(9500)
                .build();

        product = Product.builder().title("Mobile").description("Mobile available at 20% discount").price(10000).discountedPrice(9500)
                .build();

        product1 = Product.builder().title("laptop").description("Laptop available at 20% discount").price(50000).discountedPrice(40000)
                .build();

        products = new ArrayList<>();
        products.add(product);
        products.add(product1);
    }


// //   If we are hard delete user then we can use code as bellow

//    @Test
//    void deleteUser() {
//        Long id = 10l;
//        //Arrange
//        Mockito.when(userRepository.findById(id)).thenReturn(Optional.of(user));
//        //Act
//        userService.deleteUser(id);
//        //Assert
//        Mockito.verify(userRepository, Mockito.times(1)).delete(user);
//        Assertions.assertThrows(ResourceNotFoundException.class, () -> userService.deleteUser(111l));
//    }


    // //   if we use soft delete then we use code as bellow

    @Test
    void createProduct() {

        //        Arrange
        Mockito.when(productRepo.save(Mockito.any())).thenReturn(product);
//        Act
        ProductDto user1 = productService.createProduct(productDto);
//        Assert
        Assertions.assertNotNull(user1);
        Assertions.assertEquals(productDto.getTitle(), user1.getTitle());
    }

    @Test
    void update() {

        Long id = 10L;
        //Arrange
        Mockito.when(productRepo.findById(id)).thenReturn(Optional.of(product));
        Mockito.when(productRepo.save(Mockito.any())).thenReturn(product);
        //Act
        ProductDto updateProduct = productService.update(productDto, id);
        //Assert
        Assertions.assertNotNull(updateProduct);
        Assertions.assertEquals(productDto.getTitle(), updateProduct.getTitle());
        Assertions.assertThrows(ResourceNotFoundException.class, () -> productService.update(productDto, 111L));
    }

    @Test
    void getById() {

        Long id = 10l;
//        Arrange
        Mockito.when(productRepo.findById(id)).thenReturn(Optional.of(product));
//        Act
        ProductDto productDto1 = productService.getById(id);
//        Assert
        Assertions.assertNotNull(productDto1);
    }

    @Test
    void getAllProduct() {

        //Arrange
        List<Product>productList = Arrays.asList(product,product1);
        Page<Product> page = new PageImpl<>(productList);
        Mockito.when(productRepo.findAll((Pageable)Mockito.any())).thenReturn(page);
        //Act
        ProductResponse allProduct = productService.getAllProduct(1,2,"title","asc");
//        $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
        //Assert
        Assertions.assertEquals(2, allProduct.getContent().size());
    }

    @Test
    void deleteById() {

        Long id = 10l;
        //Arrange
        Mockito.when(productRepo.findById(id)).thenReturn(Optional.of(product));
        //Act
        productService.deleteById(id);
        //Assert
        Assertions.assertEquals("No", product.getIsactive());
        Assertions.assertThrows(ResourceNotFoundException.class, ()-> productService.deleteById(111l));
    }

    @Test
    void searchByTitle() {
    }
}