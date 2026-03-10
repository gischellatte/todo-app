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
public class Task {

    @Id 
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "taskid")//must match the mysql
    private Integer id;

    @Column (name = "task_name", nullable = false) //DB level constraint
    private String taskName;

    @ManyToOne //@ManyToOne means 1 categ can have many tasks. @OneToMany means one task can have many categs, which is incorrect. Both must be connected to an object data type, not to the primitive ones like Integer or String
    @JoinColumn(name = "category_id")//to correspond to the col title in the DB
    //private Integer categoryId;
    private Category category;

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

    public Category getCategory(){
        return category;
    }
    public void setCategory(Category category){
        this.category = category;
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

