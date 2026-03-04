package com.todo.categories;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.todo.entity.Categories;
import com.todo.categories.CategoryService;
import com.todo.dtos.UpdateCategoryDto;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.validation.Valid;
import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;



@CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RestController
@RequestMapping("/categories")

public class CategoryController {

    private final CategoryRepository categoryRepository;
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService, CategoryRepository categoryRepository){
        this.categoryService = categoryService;
        this.categoryRepository = categoryRepository;
    }

   @GetMapping()
   //use <List<Categories>> to show all categories because analysing DTOs take longer time. 
   public ResponseEntity<List<Categories>> findAll(){
    List<Categories> taskCategories = this.categoryService.getAllCategories();
    return ResponseEntity.ok(taskCategories);
   }
   @GetMapping("/{id}")
   public ResponseEntity<Categories> getById(@PathVariable Integer id){
    return categoryRepository.findById(id).map(ResponseEntity::ok)
    .orElse(ResponseEntity.notFound().build());
   }

   //DELETE /categories/:id
    @DeleteMapping("/{id}")
   public ResponseEntity<Categories> deleteCategory(@PathVariable Integer id){
    if(!categoryService.deleteByCategory(id)){
        return ResponseEntity.notFound().build();
    }
    return ResponseEntity.noContent().build(); 
   }
   //POST /categories
   @PostMapping
   public ResponseEntity<Categories> createCategory(@Valid @RequestBody Categories category){
    Categories createdCategories = categoryService.createCategories(category);
    return ResponseEntity.status(HttpStatus.CREATED).body(createdCategories);
   }

   // PUT /categories/{id}
   @PutMapping("/{id}")
   public ResponseEntity<Categories> fullyUpdateCategory(@PathVariable Integer id, @Valid @RequestBody UpdateCategoryDto dto){
    Categories updatedCategory = categoryService.updateCategory(id, dto);
    return ResponseEntity.ok(updatedCategory);
   }
}
