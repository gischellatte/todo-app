package com.todo.entity;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;

import jakarta.persistence.Access; 
import jakarta.persistence.AccessType;

@Entity
@Table (name = "tasks")
@Access(AccessType.FIELD)
public class Task {

    @Id 
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "taskid")
    private Integer id;

    @Column (name = "task_name", nullable = false)
    private String taskName;

    @ManyToOne
    @JoinColumn(name = "category_id")

    private Category category;

    private LocalDate deadline;
    
    @Column(name = "make_archived")
    private boolean makeArchived;
    
    public Integer getId(){
       return id; 
    }

    public void setId(Integer id){
      this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Category getCategory(){
        return category;
    }
    public void setCategory(Category category){
        this.category = category;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline=deadline;
    }

    public boolean getMakeArchived() {
        return makeArchived;
    }

    public void setMakeArchived(boolean makeArchived) {
        this.makeArchived = makeArchived;
    }

}


