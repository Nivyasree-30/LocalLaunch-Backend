package com.locallaunch.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
@Table(name="business_updates")
public class BusinessUpdate {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message="Update title is required") private String title;
    @NotBlank(message="Update content is required") @Column(columnDefinition="TEXT") private String content;
    private String imageUrl;
    private LocalDateTime createdAt;
    @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="business_id", nullable=false) private Business business;
    @PrePersist void onCreate(){createdAt=LocalDateTime.now();}
    public BusinessUpdate(){}
    public Long getId(){return id;} public void setId(Long v){id=v;}
    public String getTitle(){return title;} public void setTitle(String v){title=v;}
    public String getContent(){return content;} public void setContent(String v){content=v;}
    public String getImageUrl(){return imageUrl;} public void setImageUrl(String v){imageUrl=v;}
    public LocalDateTime getCreatedAt(){return createdAt;} public void setCreatedAt(LocalDateTime v){createdAt=v;}
    public Business getBusiness(){return business;} public void setBusiness(Business v){business=v;}
}
