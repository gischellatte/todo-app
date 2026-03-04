package com.todo.dtos;

import jakarta.validation.constraints.NotBlank;

public class UpdateCategoryDto {


    @NotBlank (message = "Categoty name should not be blank.")
    private String categoryName;



    public String getCategoryName(){
        return categoryName;
    }

    public void setCategoryName(String categoryName){
        this.categoryName = categoryName;
    }
    
}
