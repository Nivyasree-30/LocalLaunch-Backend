package com.locallaunch.exception;
import org.springframework.http.*; import org.springframework.web.bind.*; import org.springframework.web.bind.annotation.*; import java.util.*;
@RestControllerAdvice public class GlobalExceptionHandler {
 @ExceptionHandler(MethodArgumentNotValidException.class) public ResponseEntity<Map<String,Object>> validation(MethodArgumentNotValidException e){Map<String,String> errors=new HashMap<>();e.getBindingResult().getFieldErrors().forEach(x->errors.put(x.getField(),x.getDefaultMessage()));return ResponseEntity.badRequest().body(Map.of("status",400,"message","Validation failed","errors",errors));}
 @ExceptionHandler(ResourceNotFoundException.class) public ResponseEntity<Map<String,Object>> notFound(ResourceNotFoundException e){return ResponseEntity.status(404).body(Map.of("status",404,"message",e.getMessage()));}
 @ExceptionHandler(IllegalArgumentException.class) public ResponseEntity<Map<String,Object>> bad(IllegalArgumentException e){return ResponseEntity.badRequest().body(Map.of("status",400,"message",e.getMessage()));}
 @ExceptionHandler(Exception.class) public ResponseEntity<Map<String,Object>> general(Exception e){e.printStackTrace();return ResponseEntity.status(500).body(Map.of("status",500,"message","An unexpected error occurred"));}
}
