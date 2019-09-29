package com.example.remake_spring.repos;

import com.example.remake_spring.domain.ProductEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepo extends CrudRepository<ProductEntity, Long> {

    List<ProductEntity> findByName(String name);
}
