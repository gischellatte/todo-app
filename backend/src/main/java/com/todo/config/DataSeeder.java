package com.todo.config; 
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

        Category categ1 = new Category();
        categ1.setCategoryName("Others1");
        categoryRepository.save(categ1);
        System.out.println("Categories seeded.");
      }
      
      if(this.taskRepository.count() == 0){

        Task task1 = new Task();

        task1.setTaskName("Make a prototype using A4 paper");
        task1.setCategory(categoryRepository.findById(12).orElseThrow(()->new IllegalArgumentException("No related category available")));
        task1.setDeadline("15 Mar 2026");
        task1.setMakeArchived(false);
        taskRepository.save(task1);

        Task task2 = new Task();
        task2.setTaskName("Make a prototype using Figma");
        task2.setCategory(categoryRepository.findById(12).orElseThrow(()->new IllegalArgumentException("No related category available")));
        task2.setDeadline("25 Mar 2026");
        task2.setMakeArchived(false);
        taskRepository.save(task2);

        Task task3 = new Task();
        task3.setTaskName("Upgrade Everything to Javascript Es6");
        task3.setCategory(categoryRepository.findById(1).orElseThrow(()->new IllegalArgumentException("No related category available")));
        task3.setDeadline("03 Apr 2026");
        task3.setMakeArchived(false);
        taskRepository.save(task3);

        System.out.println("New tasks seeded.");
      } 
    }
}

