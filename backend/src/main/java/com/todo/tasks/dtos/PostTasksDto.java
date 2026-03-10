package com.todo.tasks.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class PostTasksDto {

    @NotBlank (message = "The Task name cant be blank")
    String taskName;

    @NotNull (message = "Category ID cant be blank") 
    private Integer categoryId;
    private String deadline;
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

    public String getDeadline(){
        return deadline;
    }

    public void setDeadline(String deadline){
        this.deadline = deadline;
    }    

    public boolean getMakeArchived() {
        return makeArchived; 
    }

    public void setMakeArchived(boolean makeArchived) {
        this.makeArchived = makeArchived; 
    }

}

