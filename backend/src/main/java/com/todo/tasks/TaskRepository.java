package com.todo.tasks;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.todo.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Integer>{
    List <Task> findByCategoryIdAndMakeArchivedFalse(Integer categoryId); 

    List <Task> findByMakeArchivedFalse();

    List <Task> findByMakeArchivedTrue();
   
    void deleteByCategoryId(Integer categoryId);
}

