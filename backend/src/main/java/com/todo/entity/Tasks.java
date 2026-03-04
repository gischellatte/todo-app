package com.todo.entity;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Access; 
import jakarta.persistence.AccessType;

@Entity
@Table (name = "tasks")
@Access(AccessType.FIELD)
public class Tasks {
    @Id 
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "taskid")
    private Integer id;

    @Column (name = "task_name", nullable = false) //DB level constraint
    private String taskName;

    //@ManyToOne //@ManyToOne means 1 categ can have many tasks. @OneToMany means one task can have many categs, which is incorrect
    @Column(name = "category_id")//to correspond to the col title in the DB
    private Integer categoryId;

    @Column(name = "deadline")
    private String deadline;
    
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

    //add another one for category_id
    public Integer getCategoryId(){
       return categoryId; 
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline=deadline;
    }

    public boolean getMakeArchived() {
        return makeArchived;
    }

    public void setMakeArchived(boolean makeArchived) {
        this.makeArchived = makeArchived;
    }

}

