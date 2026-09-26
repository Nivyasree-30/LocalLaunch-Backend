package com.locallaunch.dto;
import jakarta.validation.constraints.NotBlank;
public class BusinessUpdateRequest { @NotBlank private String title; @NotBlank private String content; private String imageUrl; public String getTitle(){return title;} public void setTitle(String v){title=v;} public String getContent(){return content;} public void setContent(String v){content=v;} public String getImageUrl(){return imageUrl;} public void setImageUrl(String v){imageUrl=v;} }
