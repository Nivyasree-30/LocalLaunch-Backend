package com.locallaunch.service;

import com.locallaunch.dto.DashboardDTO;
import com.locallaunch.entity.User;
import com.locallaunch.repository.BusinessRepository;
import com.locallaunch.repository.ProductRepository;
import com.locallaunch.repository.ContactEnquiryRepository;
import com.locallaunch.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class DashboardService {

    private final BusinessRepository businessRepository;
    private final ProductRepository productRepository;
    private final ContactEnquiryRepository enquiryRepository;
    private final UserRepository userRepository;

    public DashboardService(
            BusinessRepository businessRepository,
            ProductRepository productRepository,
            ContactEnquiryRepository enquiryRepository,
            UserRepository userRepository) {

        this.businessRepository = businessRepository;
        this.productRepository = productRepository;
        this.enquiryRepository = enquiryRepository;
        this.userRepository = userRepository;
    }

    public DashboardDTO getDashboard() {

        String email = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        Long ownerId = user.getId();

        long businessCount =
                businessRepository.countByOwnerId(ownerId);

        long productCount =
                productRepository.countByBusinessOwnerId(ownerId);

        long enquiryCount =
                enquiryRepository.countByBusinessOwnerId(ownerId);

        return new DashboardDTO(
                businessCount,
                productCount,
                enquiryCount
        );
    }
}