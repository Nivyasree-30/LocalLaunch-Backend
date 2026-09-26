package com.locallaunch.repository;
import com.locallaunch.entity.BusinessUpdate; import org.springframework.data.jpa.repository.JpaRepository; import java.util.List;
public interface BusinessUpdateRepository extends JpaRepository<BusinessUpdate,Long>{ List<BusinessUpdate> findByBusinessIdOrderByCreatedAtDesc(Long businessId); }
