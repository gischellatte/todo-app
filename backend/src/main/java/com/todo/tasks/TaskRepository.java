package com.todo.tasks;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.todo.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Integer>{
    List <Task> findByCategoryIdAndMakeArchivedFalse(Integer categoryId); 

    List <Task> findByMakeArchivedFalse();

    List <Task> findByMakeArchivedTrue();
   
     Optional <Task> findByTaskName(String taskName);

    void deleteByCategoryId(Integer categoryId);
}

