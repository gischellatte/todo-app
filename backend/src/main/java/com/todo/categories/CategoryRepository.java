package com.todo.categories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.todo.entity.Categories;


public interface CategoryRepository extends JpaRepository<Categories, Integer>{
    @Query(""" 
    SELECT c
    FROM Categories c
    """)
    List<Categories> findAllCategories();
}
