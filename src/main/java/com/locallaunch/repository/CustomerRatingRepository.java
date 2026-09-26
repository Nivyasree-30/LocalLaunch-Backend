package com.locallaunch.repository;
import com.locallaunch.entity.CustomerRating; import org.springframework.data.jpa.repository.JpaRepository; import java.util.List;
public interface CustomerRatingRepository extends JpaRepository<CustomerRating,Long>{ List<CustomerRating> findByProductId(Long productId); }
