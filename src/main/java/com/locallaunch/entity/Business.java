package com.locallaunch.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "businesses")
public class Business {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Business name is required")
    private String businessName;
    @NotBlank(message = "Category is required")
    private String category;
    @NotBlank(message = "Location is required")
    private String location;
    @NotBlank(message = "Description is required")
    @Column(columnDefinition = "TEXT")
    private String description;
    @NotBlank(message = "Contact number is required")
    @Pattern(regexp = "^[0-9]{10}$", message = "Contact number must contain 10 digits")
    private String contactNumber;
    private String businessImage;

    @ElementCollection
    @CollectionTable(name = "business_images", joinColumns = @JoinColumn(name = "business_id"))
    @Column(name = "image_url", length = 1000)
    private List<String> businessImages = new ArrayList<>();

    @Column(unique = true, nullable = false)
    private String slug;
    private boolean published = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private User owner;

    public Business() {}
    public Long getId(){return id;} public void setId(Long id){this.id=id;}
    public String getBusinessName(){return businessName;} public void setBusinessName(String v){businessName=v;}
    public String getCategory(){return category;} public void setCategory(String v){category=v;}
    public String getLocation(){return location;} public void setLocation(String v){location=v;}
    public String getDescription(){return description;} public void setDescription(String v){description=v;}
    public String getContactNumber(){return contactNumber;} public void setContactNumber(String v){contactNumber=v;}
    public String getBusinessImage(){return businessImage;} public void setBusinessImage(String v){businessImage=v;}
    public List<String> getBusinessImages(){return businessImages;} public void setBusinessImages(List<String> v){businessImages=v == null ? new ArrayList<>() : v;}
    public String getSlug(){return slug;} public void setSlug(String v){slug=v;}
    public boolean isPublished(){return published;} public void setPublished(boolean v){published=v;}
    public User getOwner(){return owner;} public void setOwner(User v){owner=v;}
}
