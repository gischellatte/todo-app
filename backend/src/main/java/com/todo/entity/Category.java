package com.todo.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import jakarta.persistence.OneToMany;
import java.util.List;

import com.todo.entity.Task;

@Entity
@Table (name = "categories")
public class Category {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CategoryID")
    private Integer id;

    @Column (name = "category_name")
    private String categoryName;

    @OneToMany (mappedBy = "category")
    @JsonIgnore
    private List<Task> tasks;

    public Category(){}

    public Integer getCategoryId(){
        return id;
    }
    public void setCategoryId(Integer id){
        this.id = id;
    }

    public String getCategoryName(){
        return categoryName;
    }

    public void setCategoryName(String categoryName){
        this.categoryName = categoryName;
    }

    public List<Task> getTasks(){
        return tasks;
    }

    public void setTasks(List<Task> tasks){
        this.tasks = tasks;
    }
}

