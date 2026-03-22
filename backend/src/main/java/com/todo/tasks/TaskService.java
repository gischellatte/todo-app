package com.todo.tasks;

import com.todo.entity.Task;
import com.todo.entity.Category;
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

    public List<Task> getAllTasks(Integer categoryId){
        //Primitive int cant do !=null
        if(categoryId != null){
            return taskRepository.findByCategoryIdAndMakeArchivedFalse(categoryId);
        }
        return taskRepository.findByMakeArchivedFalse();
    }

    public List<Task> getArchivedTasks(){
        return taskRepository.findByMakeArchivedTrue();
    }

    public boolean archiveTask(Integer taskId){
        //Optional is a safety net in case your search returns a null. It's possible that your search returns a null. If you dont use Optional, you need to do a manual check

        Optional<Task> electiveTasks = taskRepository.findById(taskId);
        if (electiveTasks.isPresent()){
            Task task1 = electiveTasks.get();
            task1.setMakeArchived(true);
            taskRepository.save(task1);
            return true;
        }
        return false;
    }


    public Task getTaskById(Integer id){
        //findById(id) is automatically inherited from JpaRepository
        //findById method returns an Optional<T> to safely handle cases if the entity is not found
        return taskRepository.findById(id).orElse(null);
    }

    public Task createTask(PostTasksDto postTasksDto){
        //based on the entity
        Category category = categoryRepository.findById(postTasksDto.getCategoryId())
        .orElseThrow(() -> new IllegalArgumentException("Not a valid argument search input."));

            //Make a new task entity
        Task task1 =  new Task();
        task1.setTaskName(postTasksDto.getTaskName());
        task1.setDeadline(postTasksDto.getDeadline());
        task1.setCategory(category);
        task1.setMakeArchived(postTasksDto.getMakeArchived());

        return taskRepository.save(task1);
    }

    public Task duplicateTask(Integer id){
        Task original = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        Task copy = new Task();

        copy.setTaskName(original.getTaskName());
        copy.setCategory(original.getCategory());
        copy.setDeadline(original.getDeadline());
        copy.setMakeArchived(original.getMakeArchived());

        // DO NOT SET ID
        // Database will generate new ID
        return taskRepository.save(copy);

    }

    public Task updateTask(Integer id, UpdateTaskDto dto){
        Task task2 = taskRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Not a valid task"));
        task2.setTaskName(dto.getTaskName());
        task2.setDeadline(dto.getDeadline());
      
        Category category = categoryRepository.findById(dto.getCategoryId())
        .orElseThrow(()-> new IllegalArgumentException("Wrong Category ID"));

        task2.setCategory(category);
        
        task2.setMakeArchived(dto.getMakeArchived());

        return taskRepository.save(task2);
    }
    
}
