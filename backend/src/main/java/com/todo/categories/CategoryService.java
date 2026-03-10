package com.todo.categories;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.todo.dtos.UpdateCategoryDto;
import com.todo.entity.Category;
import com.todo.tasks.TaskRepository;

@Service
public class CategoryService {
     private final TaskRepository taskRepository;
     private final CategoryRepository categoryRepository;

    public CategoryService(TaskRepository taskRepository, CategoryRepository categoryRepository){
       this.taskRepository = taskRepository;
       this.categoryRepository = categoryRepository;
    }

    public Category createCategory(Category category){
        return categoryRepository.save(category);
    }
    
    public Category getById(Integer id){
        return categoryRepository.findById(id)
        .orElseThrow(()-> new IllegalArgumentException("Can't find the related category.")) ;
    }

    public List<Category> getAllCategories(){
        return this.categoryRepository.findAll();
    }

    @Transactional
    public boolean deleteByCategory(Integer id) {
        if(!categoryRepository.existsById(id)){
            return false;
        }

        taskRepository.deleteByCategoryId(id);
        categoryRepository.deleteById(id);
        return true;
    }

    public Category updateCategory(Integer id, UpdateCategoryDto dto){
        Category searchedCategory = categoryRepository.findById(id)
        .orElseThrow();
        searchedCategory.setCategoryName(dto.getCategoryName());
        return categoryRepository.save(searchedCategory);
    }

}

