package com.todo.categories;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.todo.entity.Category;
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

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService, CategoryRepository categoryRepository){
        this.categoryService = categoryService;
    }

   @GetMapping()
   public ResponseEntity<List<Category>> findAll(){
    List<Category> taskCategories = this.categoryService.getAllCategories();
    return ResponseEntity.ok(taskCategories);
   }

   @GetMapping("/{id}")
   public ResponseEntity<Category> getById(@PathVariable Integer id){
    Category categService = categoryService.getById(id);
    return ResponseEntity.ok(categService);
   }

   //DELETE /categories/:id
    @DeleteMapping("/{id}")
   public ResponseEntity<Category> deleteCategory(@PathVariable Integer id){
    if(!categoryService.deleteByCategory(id)){
        return ResponseEntity.notFound().build();
    }
    return ResponseEntity.noContent().build();
   }
   
   //POST /categories
   @PostMapping
   public ResponseEntity<Category> createCategory(@Valid @RequestBody Category category){
    Category createdCategory = categoryService.createCategory(category);
    return ResponseEntity.status(HttpStatus.CREATED).body(createdCategory);
   }

   // PUT /categories/{id}
   @PutMapping("/{id}")
   public ResponseEntity<Category> fullyUpdateCategory(@PathVariable Integer id, @Valid @RequestBody UpdateCategoryDto dto){
    Category updatedCategory = categoryService.updateCategory(id, dto);
    return ResponseEntity.ok(updatedCategory);
   }
}
