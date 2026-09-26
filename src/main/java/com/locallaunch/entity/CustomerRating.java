package com.locallaunch.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name="customer_ratings")
public class CustomerRating {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message="Customer name is required")
    private String customerName;
    @Min(1) @Max(5)
    private int rating;
    @Column(columnDefinition="TEXT")
    private String comment;
    @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="product_id", nullable=false)
    private Product product;

    public CustomerRating() {}
    public Long getId(){return id;} public void setId(Long v){id=v;}
    public String getCustomerName(){return customerName;} public void setCustomerName(String v){customerName=v;}
    public int getRating(){return rating;} public void setRating(int v){rating=v;}
    public String getComment(){return comment;} public void setComment(String v){comment=v;}
    public Product getProduct(){return product;} public void setProduct(Product v){product=v;}
}
