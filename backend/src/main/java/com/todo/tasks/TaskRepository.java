package com.todo.tasks;
//import com.todo.Task; from the related entity

//import org.springframework.scheduling.config.Task;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.todo.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Integer>{


    Page<Task> findByTaskName(String name, Pageable pageable);
    
    List <Task> findByCategoryIdAndMakeArchivedFalse(Integer categoryId); 
  
    List <Task> findByMakeArchivedFalse();

    List <Task> findByMakeArchivedTrue();
   
    Optional <Task> findByTaskName(String taskName);

    void deleteByCategoryId(Integer categoryId);
}

