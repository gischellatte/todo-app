package com.todo.tasks.dtos;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class PostTasksDto {

    @NotBlank (message = "The Task name cant be blank")
    String taskName;

    @NotNull (message = "Category ID cant be blank") //@NotBlank cant be applied to Integers
    private Integer categoryId;
    private LocalDate deadline;
    private boolean makeArchived;

    public String getTaskName(){
        return taskName;
    }

     public void setTaskName(String taskName){
        this.taskName = taskName;
    }

    public Integer getCategoryId(){
        return categoryId;
    }

    public void setCategoryId(Integer categoryId){
        this.categoryId = categoryId;
    }

    public LocalDate getDeadline(){
        return deadline;
    }

    public void setDeadline(LocalDate deadline){
        this.deadline = deadline;
    }    

    public boolean getMakeArchived() {
        return makeArchived; 
    }

    public void setMakeArchived(boolean makeArchived) {
        this.makeArchived = makeArchived; 
    }

}
