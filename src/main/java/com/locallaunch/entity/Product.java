package com.locallaunch.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "products")
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Product name is required")
    private String productName;
    @DecimalMin(value = "0.01", message = "Price must be greater than zero")
    private double price;
    @NotBlank(message = "Product description is required")
    @Column(columnDefinition = "TEXT")
    private String description;
    private String imageUrl;
    private boolean available;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "business_id")
    private Business business;

    public Product() {}
    public Long getId(){return id;} public void setId(Long v){id=v;}
    public String getProductName(){return productName;} public void setProductName(String v){productName=v;}
    public double getPrice(){return price;} public void setPrice(double v){price=v;}
    public String getDescription(){return description;} public void setDescription(String v){description=v;}
    public String getImageUrl(){return imageUrl;} public void setImageUrl(String v){imageUrl=v;}
    public boolean isAvailable(){return available;} public void setAvailable(boolean v){available=v;}
    public Business getBusiness(){return business;} public void setBusiness(Business v){business=v;}
}
