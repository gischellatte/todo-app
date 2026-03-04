package com.todo.tasks;

import com.todo.entity.Tasks;
import com.todo.entity.Categories;
import com.todo.tasks.TaskRepository;
import com.todo.tasks.dtos.PostTasksDto;
import com.todo.tasks.dtos.UpdateTaskDto;
import com.todo.categories.CategoryRepository;

import java.util.List;
import java.util.Optional;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;



@Service
public class TaskService {

    //Declaring dependencies to fetch, save, update, or delete tasks and categories from the database
    //These dependencies represent access handlers (injected to the service class)
    private final TaskRepository taskRepository;
    private final CategoryRepository categoryRepository;

    public TaskService(TaskRepository taskRepository, CategoryRepository categoryRepository){
    this.taskRepository = taskRepository;
    this.categoryRepository = categoryRepository;
    }

    public List<Tasks> getAllTasks(Integer categoryId){
        //Primitive int cant do !=null
        if(categoryId != null){
            return taskRepository.findByCategoryIdNotArchived(categoryId);
        }
        return taskRepository.findByMakeArchivedFalse();
    }

    public boolean archiveTask(Integer taskId){
        //Optional is a safety net in case your search returns a null. It's possible that your search returns a null. If you dont use Optional, you need to do a manual check

        Optional<Tasks> electiveTasks = taskRepository.findById(taskId);
        if (electiveTasks.isPresent()){
            Tasks task1 = electiveTasks.get();
            task1.setMakeArchived(true);
            taskRepository.save(task1);
            return true;
        }
        return false;
    }


    public Tasks getTaskById(Integer id){
        //findById(id) is automatically inherited from JpaRepository
        //findById method returns an Optional<T> to safely handle cases if the entity is not found
        return taskRepository.findById(id).orElse(null);
    }

    public Tasks createTasks(PostTasksDto postTasksDto){
        //based on the entity
        Categories categories = categoryRepository.findById(postTasksDto.getCategoryId())
        .orElseThrow(() -> new IllegalArgumentException("Not a valid argument search input."));

            //Make a new task entity
        Tasks task1 =  new Tasks();
        task1.setTaskName(postTasksDto.getTaskName());
        task1.setDeadline(postTasksDto.getDeadline());
        task1.setCategoryId(postTasksDto.getCategoryId());
        task1.setMakeArchived(postTasksDto.getMakeArchived());

        return taskRepository.save(task1);
    }

    public Tasks updateTask(Integer id, UpdateTaskDto dto){
        Tasks task2 = taskRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Not a valid category"));
        task2.setTaskName(dto.getTaskName());
        task2.setDeadline(dto.getDeadline());
        task2.setCategoryId(dto.getCategoryId());
        task2.setMakeArchived(dto.getMakeArchived());

        return taskRepository.save(task2);
    }
    
}
