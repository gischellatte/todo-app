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
import com.todo.entity.Categories;
import com.todo.entity.Tasks;
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
//Postman URL: http://localhost:8080/todos and GET /todos?category={}
@GetMapping 
public ResponseEntity<List<Tasks>> retrieveTask(@RequestParam(value="category", required = false) Integer categId) {
//List<Tasks> returns all tasks in 1 response
//Tasks can only return 1 task
    List<Tasks> tasks = taskService.getAllTasks(categId);
    return ResponseEntity.ok(tasks);
}

@GetMapping ("/archived")
public ResponseEntity<List<Tasks>> getArchivedTaks(){
    return ResponseEntity.ok(repository.findByMakeArchivedTrue());
}

// POST /todos
@PostMapping
public ResponseEntity<Tasks> addTasks(@Valid @RequestBody PostTasksDto postTasksDto) {

        Tasks createdTasks = taskService.createTasks(postTasksDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTasks);
    }

//GET /todos?category={id}
//In a Spring Boot app, the controller should call the service, not the repository directly (best practice)

// @GetMapping
// public ResponseEntity<List<Tasks>> retrieveTaskCateg(@RequestParam(required = false) Integer categId) {
// //List <Tasks> findByCategoryId(Integer categoryId); 
//     List<Tasks> tasksCateg = taskService.getAllTasks(categId);
//     return ResponseEntity.ok(tasksCateg);
// }


//DELETE /todos/:id
@DeleteMapping("/{id}")
public ResponseEntity<Tasks> archiveTask(@PathVariable Integer id) {
    boolean sentToArchive = taskService.archiveTask(id);
    // a task is successfully archived
    if(sentToArchive){
        Tasks archivedTask = taskService.getTaskById(id);
        return ResponseEntity.ok(archivedTask);
        }
    else {
        return ResponseEntity.notFound().build();
    }    
  }
 
  // PUT /todos/:id
  @PutMapping("/{id}")
   public ResponseEntity<Tasks> fullyUpdateTask(@PathVariable Integer id, @Valid @RequestBody UpdateTaskDto updateTaskDto){
    Tasks fullyUpdateTask = taskService.updateTask(id,  updateTaskDto);
    return ResponseEntity.ok(fullyUpdateTask);
   }

   // Post /id/duplicate - Duplicate
    @PostMapping("/{id}/duplicate")
    public Tasks duplicateTask(@PathVariable Integer id) {

        Tasks original = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Todo not found"));

        Tasks copy = new Tasks();

        copy.setTaskName(original.getTaskName());
        copy.setCategoryId(original.getCategoryId());
        copy.setDeadline(original.getDeadline());
        copy.setMakeArchived(original.getMakeArchived());

        // DO NOT SET ID
        // Database will generate new ID
        return repository.save(copy);
        
    } 
}
