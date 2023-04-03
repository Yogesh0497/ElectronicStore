package com.yogesh.electronicStore.repository;

import com.yogesh.electronicStore.model.Product;
import com.yogesh.electronicStore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {

    Optional<Product> searchByTitle(String title);
}
