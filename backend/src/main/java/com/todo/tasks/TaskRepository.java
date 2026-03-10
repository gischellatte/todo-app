package com.todo.tasks;
//import com.todo.Task; from the related entity

//import org.springframework.scheduling.config.Task;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.todo.entity.Task;
//JpaRepository provides standards for CRUD 
public interface TaskRepository extends JpaRepository<Task, Integer>{
    //<class that represents the data you want to persist in your database, type of the ID field in the entity class>

    //The new keyword is only for instantiating an object in Java. We are deleting here, so no need to add the 'new' keyword.
    //Distinct helps to remove duplicate
     //Add queries (Flow: DB->Repo)
    //Follow the Spring Data JPA naming convention
    List <Task> findByCategoryIdAndMakeArchivedFalse(Integer categoryId); 

    //for simple queries (No group by, having, etc), better use methods than @query
    List <Task> findByMakeArchivedFalse();

    List <Task> findByMakeArchivedTrue();
   
    void deleteByCategoryId(Integer categoryId);
}
