package com.locallaunch.dto;
import jakarta.validation.constraints.Max; import jakarta.validation.constraints.Min; import jakarta.validation.constraints.NotBlank;
public class RatingRequest { @NotBlank private String customerName; @Min(1) @Max(5) private int rating; private String comment; public String getCustomerName(){return customerName;} public void setCustomerName(String v){customerName=v;} public int getRating(){return rating;} public void setRating(int v){rating=v;} public String getComment(){return comment;} public void setComment(String v){comment=v;} }
