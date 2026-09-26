package com.locallaunch.controller;
import com.locallaunch.dto.PublicWebsiteDTO; import com.locallaunch.entity.Business; import com.locallaunch.service.BusinessService; import com.locallaunch.exception.ResourceNotFoundException; import jakarta.validation.Valid; import org.springframework.http.ResponseEntity; import org.springframework.web.bind.annotation.*; import java.util.List;
@RestController @RequestMapping("/api/businesses") public class BusinessController {
 private final BusinessService service; public BusinessController(BusinessService s){service=s;}
 @PostMapping public Business create(@Valid @RequestBody Business b){return service.saveBusiness(b);}
 @GetMapping("/{id}") public Business get(@PathVariable Long id){return service.getMyBusinessById(id).orElseThrow(()->new ResourceNotFoundException("Business not found or access denied"));}
 @GetMapping("/my-businesses") public List<Business> mine(){return service.getMyBusinesses();}
 @PutMapping("/{id}") public Business update(@PathVariable Long id,@Valid @RequestBody Business b){return service.updateBusiness(id,b);}
 @DeleteMapping("/{id}") public ResponseEntity<String> delete(@PathVariable Long id){service.deleteBusiness(id);return ResponseEntity.ok("Business deleted successfully");}
 @PostMapping("/{id}/images") public Business addImage(@PathVariable Long id,@RequestParam("imageUrl") String imageUrl){return service.addBusinessImage(id,imageUrl);}
 @DeleteMapping("/{id}/images/{index}") public Business removeImage(@PathVariable Long id,@PathVariable int index){return service.removeBusinessImage(id,index);}
 @PostMapping("/{id}/publish") public Business publish(@PathVariable Long id){return service.publish(id);}
 @GetMapping("/public/slug/{slug}") public ResponseEntity<PublicWebsiteDTO> publicBySlug(@PathVariable String slug){return service.getPublicWebsiteBySlug(slug).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());}
 @GetMapping("/public/{id}") public ResponseEntity<PublicWebsiteDTO> publicById(@PathVariable Long id){return service.getPublicWebsite(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());}
}
