package com.locallaunch.repository;

import com.locallaunch.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByBusinessId(Long businessId);
    Optional<Product> findByIdAndBusinessOwnerId(
            Long productId,
            Long ownerId
    );
    long countByBusinessOwnerId(Long ownerId);
}