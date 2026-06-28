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
//JpaRepository provides standards for CRUD 
//<class that represents the data you want to persist in your database, type of the ID field in the entity class>
public interface TaskRepository extends JpaRepository<Task, Integer>{

    //follow the entity name (in this case, task name)
    Page<Task> findByTaskName(String name, Pageable pageable);
    //The new keyword is only for instantiating an object in Java. We are deleting here, so no need to add the 'new' keyword.
    //Distinct helps to remove duplicate
    //Add queries (Flow: DB->Repo)
    //Follow the Spring Data JPA naming convention
    List <Task> findByCategoryIdAndMakeArchivedFalse(Integer categoryId); 

    //for simple queries (No group by, having, etc), better use methods than @query    
    List <Task> findByMakeArchivedFalse();

    List <Task> findByMakeArchivedTrue();
   
    Optional <Task> findByTaskName(String taskName);

    void deleteByCategoryId(Integer categoryId);
}

