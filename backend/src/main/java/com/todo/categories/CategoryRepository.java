package com.todo.categories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.todo.entity.Category;
import java.util.Optional;


public interface CategoryRepository extends JpaRepository<Category, Integer>{
    List<Category> findAll();

    //if it returns a Category with the specified name, we will get Optional.of(category). If not, it will return Optional.empty
    Optional<Category> findByCategoryName (String categoryName);
}

