package com.todo.categories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.todo.entity.Category;


public interface CategoryRepository extends JpaRepository<Category, Integer>{
   //findAll is one of the names from Java Spring convention. findAllCategories() (the method we previously use) is a custom method name and we need a @query
    List<Category> findAll();
}
