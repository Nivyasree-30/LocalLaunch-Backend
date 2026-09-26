package com.locallaunch.service;

import com.locallaunch.dto.*;
import com.locallaunch.entity.Business;
import com.locallaunch.entity.Product;
import com.locallaunch.entity.User;
import com.locallaunch.exception.ResourceNotFoundException;
import com.locallaunch.repository.BusinessRepository;
import com.locallaunch.repository.BusinessUpdateRepository;
import com.locallaunch.repository.CustomerRatingRepository;
import com.locallaunch.repository.ProductRepository;
import com.locallaunch.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BusinessService {
    private final BusinessRepository businessRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final CustomerRatingRepository ratingRepository;
    private final BusinessUpdateRepository updateRepository;

    public BusinessService(BusinessRepository businessRepository, ProductRepository productRepository,
                           UserRepository userRepository, CustomerRatingRepository ratingRepository,
                           BusinessUpdateRepository updateRepository) {
        this.businessRepository = businessRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.ratingRepository = ratingRepository;
        this.updateRepository = updateRepository;
    }

    public Business saveBusiness(Business business) {
        User owner = getLoggedInUser();
        business.setOwner(owner);
        business.setSlug(generateUniqueSlug(business.getBusinessName()));
        if (business.getBusinessImages() == null) business.setBusinessImages(new ArrayList<>());
        if (business.getBusinessImages().size() > 15) throw new IllegalArgumentException("Maximum 15 business images are allowed");
        if (business.getBusinessImages().size() > 0) business.setBusinessImage(business.getBusinessImages().get(0));
        business.setPublished(false);
        return businessRepository.save(business);
    }

    public Business updateBusiness(Long id, Business updated) {
        Business existing = getOwnedBusiness(id);
        existing.setBusinessName(updated.getBusinessName()); existing.setCategory(updated.getCategory());
        existing.setLocation(updated.getLocation()); existing.setDescription(updated.getDescription());
        existing.setContactNumber(updated.getContactNumber());
        if (updated.getBusinessImages()!=null) {
            if (updated.getBusinessImages().size()>15) throw new IllegalArgumentException("Maximum 15 business images are allowed");
            existing.setBusinessImages(updated.getBusinessImages());
            existing.setBusinessImage(updated.getBusinessImages().isEmpty()?null:updated.getBusinessImages().get(0));
        } else existing.setBusinessImage(updated.getBusinessImage());
        return businessRepository.save(existing);
    }

    public Business addBusinessImage(Long id, String imageUrl) {
        if (imageUrl == null || imageUrl.isBlank()) throw new IllegalArgumentException("Image URL is required");
        Business business=getOwnedBusiness(id); List<String> images=new ArrayList<>(business.getBusinessImages());
        if(images.size()>=15) throw new IllegalArgumentException("Maximum 15 business images are allowed");
        images.add(imageUrl.trim()); business.setBusinessImages(images); if(business.getBusinessImage()==null) business.setBusinessImage(imageUrl.trim()); business.setPublished(false); return businessRepository.save(business);
    }
    public Business removeBusinessImage(Long id,int index){ Business b=getOwnedBusiness(id); List<String> images=new ArrayList<>(b.getBusinessImages()); if(index<0||index>=images.size()) throw new IllegalArgumentException("Invalid image number"); images.remove(index); b.setBusinessImages(images); b.setBusinessImage(images.isEmpty()?null:images.get(0)); b.setPublished(false); return businessRepository.save(b); }
    public Business publish(Long id){ Business b=getOwnedBusiness(id); if(b.getBusinessImages()==null||b.getBusinessImages().size()<5) throw new IllegalArgumentException("Add at least 5 business images before creating the website"); b.setPublished(true); return businessRepository.save(b); }

    public Optional<Business> getMyBusinessById(Long id){return Optional.ofNullable(getOwnedBusiness(id));}
    public List<Business> getMyBusinesses(){return businessRepository.findByOwnerId(getLoggedInUser().getId());}

    public Optional<PublicWebsiteDTO> getPublicWebsite(Long id){
        return businessRepository.findById(id).filter(Business::isPublished).map(this::toPublicWebsite);
    }
    public Optional<PublicWebsiteDTO> getPublicWebsiteBySlug(String slug){return businessRepository.findBySlug(slug).filter(Business::isPublished).map(this::toPublicWebsite);}

    private PublicWebsiteDTO toPublicWebsite(Business business){
        PublicBusinessDTO b=new PublicBusinessDTO(); b.setId(business.getId()); b.setBusinessName(business.getBusinessName()); b.setCategory(business.getCategory()); b.setLocation(business.getLocation()); b.setDescription(business.getDescription()); b.setContactNumber(business.getContactNumber()); b.setBusinessImage(business.getBusinessImage()); b.setBusinessImages(business.getBusinessImages()); b.setPublished(business.isPublished());
        List<PublicProductDTO> products=productRepository.findByBusinessId(business.getId()).stream().map(p->{ PublicProductDTO d=new PublicProductDTO(); d.setId(p.getId()); d.setProductName(p.getProductName()); d.setPrice(p.getPrice()); d.setDescription(p.getDescription()); d.setImageUrl(p.getImageUrl()); d.setAvailable(p.isAvailable()); List<com.locallaunch.entity.CustomerRating> rs=ratingRepository.findByProductId(p.getId()); d.setRatingCount(rs.size()); d.setAverageRating(rs.stream().mapToInt(com.locallaunch.entity.CustomerRating::getRating).average().orElse(0)); return d; }).toList();
        List<PublicUpdateDTO> updates=updateRepository.findByBusinessIdOrderByCreatedAtDesc(business.getId()).stream().map(u->new PublicUpdateDTO(u.getId(),u.getTitle(),u.getContent(),u.getImageUrl(),u.getCreatedAt())).toList();
        PublicWebsiteDTO out=new PublicWebsiteDTO(); out.setBusiness(b); out.setProducts(products); out.setUpdates(updates); return out;
    }

    public void deleteBusiness(Long id){Business b=getOwnedBusiness(id); productRepository.deleteAll(productRepository.findByBusinessId(id)); businessRepository.delete(b);}

    private Business getOwnedBusiness(Long id){return businessRepository.findByIdAndOwnerId(id,getLoggedInUser().getId()).orElseThrow(()->new ResourceNotFoundException("Business not found or access denied"));}
    private User getLoggedInUser(){String email=SecurityContextHolder.getContext().getAuthentication().getName(); return userRepository.findByEmail(email).orElseThrow(()->new ResourceNotFoundException("User not found"));}
    private String generateUniqueSlug(String name){String base=name.toLowerCase().trim().replaceAll("[^a-z0-9]+","-").replaceAll("^-|-$",""); String slug=base; int count=2; while(businessRepository.existsBySlug(slug)){slug=base+"-"+count++;} return slug;}
}
