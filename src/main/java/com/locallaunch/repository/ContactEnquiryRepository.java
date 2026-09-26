package com.locallaunch.repository;

import com.locallaunch.entity.ContactEnquiry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ContactEnquiryRepository
        extends JpaRepository<ContactEnquiry, Long> {

    List<ContactEnquiry> findByBusinessId(Long businessId);

    List<ContactEnquiry> findByBusinessIdAndBusinessOwnerId(
            Long businessId,
            Long ownerId
    );

    Optional<ContactEnquiry> findByIdAndBusinessOwnerId(
            Long enquiryId,
            Long ownerId
    );
    long countByBusinessOwnerId(Long ownerId);
}