package com.todo.config; 
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;

import com.todo.categories.CategoryRepository;
import com.todo.entity.Category;
import com.todo.entity.Task;
import com.todo.tasks.TaskRepository;

import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public class DataSeeder implements CommandLineRunner{
    
    private TaskRepository taskRepository;
    private CategoryRepository categoryRepository;

    public DataSeeder(TaskRepository taskRepository, CategoryRepository categoryRepository){
     this.taskRepository = taskRepository;
     this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) throws Exception {

      if(this.categoryRepository.count() == 0){
      //Create a category object
        Category categ1 = new Category();
      //decide the category name
        categ1.setCategoryName("UX Design");
        categoryRepository.save(categ1);

        Category categ1 = new Category();
        categ1.setCategoryName("UX Design");
        categoryRepository.save(categ1);
        System.out.println("Categories seeded.");
      }
      
      if(this.taskRepository.count() == 0){

        Category uxDesignCategory = categoryRepository.findByCategoryName("UX Design").orElseThrow(()->new IllegalArgumentException("UX Design category not available"));

          // Create and save new tasks
          createTaskIfnotExists("Make a prototype using A4 paper", uxDesignCategory, "15 Mar 2026");
          createTaskIfnotExists("Make a prototype using Figma", uxDesignCategory, "25 Mar 2026");
          createTaskIfnotExists("Upgrade Everything to Javascript Es6", uxDesignCategory, "03 Apr 2026");
        }
      } 

      private void createTaskIfnotExists(String taskName, Category categ, String deadline)
      {
        if(taskRepository.findByTaskName(taskName).isEmpty()){
       //create tasks 
        Task task1 = new Task();
       //decide the task name
        task1.setTaskName(taskName);
        task1.setCategory(categ);
        task1.setDeadline(formatToLocalDate(deadline));
        task1.setMakeArchived(false);
        taskRepository.save(task1);

        System.out.println("New tasks seeded.");
        }

        else {
          System.out.println(taskName + "exists");
        }
      
    }

    private LocalDate formatToLocalDate (String dateStr) {
      DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
      return LocalDate.parse(dateStr, timeFormatter);
    }
  
}


