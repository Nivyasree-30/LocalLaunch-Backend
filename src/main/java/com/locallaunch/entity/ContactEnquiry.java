package com.locallaunch.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "contact_enquiries")
public class ContactEnquiry {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private EnquiryStatus status = EnquiryStatus.NEW;

    @NotBlank(message = "Customer name is required")
    private String customerName;
    @NotBlank(message = "Customer email is required")
    @Email(message = "Enter a valid email")
    private String customerEmail;
    @NotBlank(message = "Mobile number is required")
    private String mobileNumber;
    @NotBlank(message = "Message is required")
    @Column(columnDefinition = "TEXT")
    private String message;
    private Long productId;
    private String productName;
    private Double productPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "business_id", nullable = false)
    private Business business;

    public ContactEnquiry() {}
    public Long getId(){return id;} public void setId(Long v){id=v;}
    public EnquiryStatus getStatus(){return status;} public void setStatus(EnquiryStatus v){status=v;}
    public String getCustomerName(){return customerName;} public void setCustomerName(String v){customerName=v;}
    public String getCustomerEmail(){return customerEmail;} public void setCustomerEmail(String v){customerEmail=v;}
    public String getMobileNumber(){return mobileNumber;} public void setMobileNumber(String v){mobileNumber=v;}
    public String getMessage(){return message;} public void setMessage(String v){message=v;}
    public Long getProductId(){return productId;} public void setProductId(Long v){productId=v;}
    public String getProductName(){return productName;} public void setProductName(String v){productName=v;}
    public Double getProductPrice(){return productPrice;} public void setProductPrice(Double v){productPrice=v;}
    public Business getBusiness(){return business;} public void setBusiness(Business v){business=v;}
}
