package com.todo.entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table (name = "categories")
public class Categories {

    @Column (name = "CategoryID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Without this, the ID would be null or manually set, leading to errors on INSERT (duplicate or NULL primary key). Necessary for for auto-increment fields like 'CategoryID' or 'TaskID'
    private Integer categoryId;

    @Column (name = "category_name")
    private String categoryName;

    public Integer getCategoryId(){
        return categoryId;
    }
    public void setCategoryId(Integer categoryId){
        this.categoryId = categoryId;
    }

    public String getCategoryName(){
        return categoryName;
    }

    public void setCategoryName(String categoryName){
        this.categoryName = categoryName;
    }
}
