package com.todo.tasks;
//import com.todo.Task; from the related entity

//import org.springframework.scheduling.config.Task;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.todo.entity.Tasks;
//JpaRepository provides standards for CRUD 
public interface TaskRepository extends JpaRepository<Tasks, Integer>{
    //<class that represents the data you want to persist in your database, type of the ID field in the entity class>

    //The new keyword is only for instantiating an object in Java. We are deleting here, so no need to add the 'new' keyword.
    //Distinct helps to remove duplicate
     //Add queries (Flow: DB->Repo)
    @Query("""
        SELECT t
        FROM Tasks t
        WHERE t.categoryId = :categoryId And t.makeArchived = false
        """)
    List <Tasks> findByCategoryIdNotArchived(Integer categoryId); 

    List<Tasks> findByMakeArchivedFalse();

    List<Tasks> findByMakeArchivedTrue();
   
    void deleteByCategoryId(Integer categoryId);
}
