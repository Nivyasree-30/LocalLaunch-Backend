package com.locallaunch.repository;

import com.locallaunch.entity.Business;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BusinessRepository
        extends JpaRepository<Business, Long> {
    List<Business> findByOwnerId(Long ownerId);

    Optional<Business> findByIdAndOwnerId(
            Long businessId,
            Long ownerId
    );
    Optional<Business> findBySlug(String slug);
    boolean existsBySlug(String slug);
    long countByOwnerId(Long ownerId);
}