package com.yogesh.electronicStore.serviceImpl;

import com.yogesh.electronicStore.exception.ResourceNotFoundException;
import com.yogesh.electronicStore.model.Product;
import com.yogesh.electronicStore.myConfig.AppConstant;
import com.yogesh.electronicStore.payloads.ProductDto;
import com.yogesh.electronicStore.repository.ProductRepo;
import com.yogesh.electronicStore.response.ProductResponse;
import com.yogesh.electronicStore.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private ModelMapper mapper;
    @Override
    public ProductDto createProduct(ProductDto productDto) {

        log.info("Initiating dao step to save product");

        Product product = this.mapper.map(productDto, Product.class);

        product.setIsactive(AppConstant.YES);

        Product save = this.productRepo.save(product);

        log.info("Completion dao step to save category");

        return this.mapper.map(save, ProductDto.class);
    }

    @Override
    public ProductDto update(ProductDto productDto, Long productId) {

        log.info("Initiating dao step to update product{}",productId);

        Product product = this.productRepo.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException(AppConstant.PRODUCT_NOT_FOUND));

        product.setTitle(productDto.getTitle());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setDiscountedPrice(productDto.getDiscountedPrice());
        product.setQuantity(productDto.getQuantity());
        product.setAddedDate(productDto.getAddedDate());

        Product update = this.productRepo.save(product);

        log.info("Completion dao step to update product{}",productId);

        return this.mapper.map(update, ProductDto.class);
    }

    @Override
    public ProductDto getById(Long productId) {

        log.info("Initiating dao step to get product{}",productId);

        Product getProduct = this.productRepo.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException(AppConstant.PRODUCT_NOT_FOUND));

        log.info("Completion dao step to get product{}",productId);

        return this.mapper.map(getProduct,ProductDto.class);
    }

    @Override
    public ProductResponse getAllProduct(int pageNumber, int pageSize, String sortBy, String sortDir) {

        log.info("Initiating dao step to get all product");



        log.info("Completion dao step to get all product");
        return null;
    }

    @Override
    public void deleteById(Long productId) {

        log.info("Initiating dao step to delete product{}",productId);

        Product deleteProduct = this.productRepo.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException(AppConstant.PRODUCT_NOT_FOUND));

        deleteProduct.setIsactive(AppConstant.NO);

        this.productRepo.save(deleteProduct);

        log.info("Completion dao step to delete product{}",productId);

    }

    @Override
    public ProductDto searchByTitle(String title) {

        log.info("Initiating dao step to search product{}",title);

        Product product = this.productRepo.searchByTitle(title)
                .orElseThrow(() -> new ResourceNotFoundException(AppConstant.PRODUCT_NOT_FOUND));

        log.info("Completion dao step to search product{}",title);

        return this.mapper.map(product, ProductDto.class);
    }
}
