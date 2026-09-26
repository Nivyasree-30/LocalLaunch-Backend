package com.locallaunch.dto;
import java.util.List;
public class PublicWebsiteDTO { private PublicBusinessDTO business; private List<PublicProductDTO> products; private List<PublicUpdateDTO> updates; public PublicBusinessDTO getBusiness(){return business;} public void setBusiness(PublicBusinessDTO v){business=v;} public List<PublicProductDTO> getProducts(){return products;} public void setProducts(List<PublicProductDTO> v){products=v;} public List<PublicUpdateDTO> getUpdates(){return updates;} public void setUpdates(List<PublicUpdateDTO> v){updates=v;} }
