package com.todo.categories;

import java.util.List;

import javax.xml.crypto.Data;

import org.springframework.stereotype.Service;

import com.todo.tasks.TaskRepository;

import jakarta.persistence.criteria.CriteriaBuilder.In;

import com.todo.categories.CategoryRepository;
import org.springframework.transaction.annotation.Transactional;

import com.todo.entity.Categories;
import com.todo.dtos.UpdateCategoryDto;

@Service
public class CategoryService {
     private final TaskRepository taskRepository;
     private final CategoryRepository categoryRepository;

    public CategoryService(TaskRepository taskRepository, CategoryRepository categoryRepository){
       this.taskRepository = taskRepository;
       this.categoryRepository = categoryRepository;
    }

    public Categories createCategories(Categories category){
        //pass the category objects from the controller
        return categoryRepository.save(category);
    }
    

    public List<Categories> getAllCategories(){
        return this.categoryRepository.findAllCategories();
    }

//     In Spring Data JPA:

// Repository write operations (save, delete, deleteById) require a transactional context.

// By default, @Repository methods are transactional only if the method is called inside a transaction.

// If your CategoryService.deleteCategory method does not have @Transactional, Spring may try to call deleteById outside a transaction → this error.
// @Transactional tells Spring:

// “Start a database transaction for this method.
// All database changes inside this method are part of the same transaction.”

// So now deleteByCategoryId and deleteById happen safely within one transaction, and the EntityManager is available.
//If you combine findById + deleteById in one service method without @Transactional, Spring cannot guarantee a single transaction for both operations. That’s what causes your error.
    @Transactional
    public boolean deleteByCategory(Integer id) {
        if(!categoryRepository.existsById(id)){
            return false;
        }

        taskRepository.deleteByCategoryId(id);
        categoryRepository.deleteById(id);
        return true;
    }

    public Categories updateCategory(Integer id, UpdateCategoryDto dto){
        Categories searchedCategory = categoryRepository.findById(id)
        .orElseThrow();
        searchedCategory.setCategoryName(dto.getCategoryName());
        return categoryRepository.save(searchedCategory);
    }

}
