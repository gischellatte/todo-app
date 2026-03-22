package com.todo.tasks;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import com.todo.tasks.dtos.*;
import com.todo.entity.Category;
import com.todo.entity.Task;
import jakarta.validation.Valid;
import java.util.List;


import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;


@CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RestController
@RequestMapping("/todos")
public class TaskController {
 private final TaskService taskService;
 private final TaskRepository repository;

 public TaskController(TaskService taskService, TaskRepository repository){
    this.taskService = taskService;
    this.repository = repository;
 }
//GET /todos 
@GetMapping 
public ResponseEntity<List<Task>> retrieveTask(@RequestParam(value="category", required = false) Integer categId) {

    List<Task> tasks = taskService.getAllTasks(categId);
    return ResponseEntity.ok(tasks);
}

@GetMapping ("/archived")
public ResponseEntity<List<Task>> getArchivedTaks(){
    List<Task> archivedTasks = taskService.getArchivedTasks();
    return ResponseEntity.ok(archivedTasks);
}

// POST /todos
@PostMapping
public ResponseEntity<Task> addTask(@Valid @RequestBody PostTasksDto postTasksDto) {

        Task createdTask = taskService.createTask(postTasksDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
    }

//DELETE /todos/:id
@DeleteMapping("/{id}")
public ResponseEntity<Task> archiveTask(@PathVariable Integer id) {
    boolean sentToArchive = taskService.archiveTask(id);

    if(sentToArchive){
        Task archivedTask = taskService.getTaskById(id);
        return ResponseEntity.ok(archivedTask);
        }
    else {
        return ResponseEntity.notFound().build();
    }    
  }
 
  // PUT /todos/:id
  @PutMapping("/{id}")
   public ResponseEntity<Task> fullyUpdateTask(@PathVariable Integer id, @Valid @RequestBody UpdateTaskDto updateTaskDto){
    Task fullyUpdateTask = taskService.updateTask(id,  updateTaskDto);
    return ResponseEntity.ok(fullyUpdateTask);
   }

   // Post /id/duplicate - Duplicate
    @PostMapping("/{id}/duplicate")
    public ResponseEntity<Task> duplicateTask(@PathVariable Integer id) {

        Task copiedTask = taskService.duplicateTask(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(copiedTask);
        
    } 
}


