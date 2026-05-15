package com.todo.tasks.service;
import org.junit.jupiter.api.Test;

import com.todo.tasks.TaskRepository;
import com.todo.tasks.TaskService;
import com.todo.tasks.dtos.PostTasksDto;
import com.todo.entity.Task;

import com.todo.categories.CategoryRepository;
import com.todo.entity.Category;

import org.mockito.Mock;
import org.mockito.InjectMocks;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class) 

class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;
    @Mock
    private CategoryRepository categoryRepository;


    @InjectMocks
    private TaskService taskService;


    @Test
    public void testingGetAllTasks_categId() {
        when(taskRepository.findByMakeArchivedFalse()).thenReturn(List.of(new Task()));
        List<Task> tasks = taskService.getAllTasks(null);
        assertNotNull(tasks);
        verify(taskRepository, times(1)).findByMakeArchivedFalse();
    }

    @Test
    public void testingGetArchivedTasks() {
     when(taskRepository.findByMakeArchivedTrue()).thenReturn(List.of(new Task()));
     List<Task> archivedTasks = taskService.getArchivedTasks();
     assertNotNull(archivedTasks);
     verify(taskRepository, times(1)).findByMakeArchivedTrue();
    }

    @Test
    public void testingCreateTask(){

    PostTasksDto postTasksDto1 =  new PostTasksDto(); 
    postTasksDto1.setTaskName("Testing createTask()"); 
    postTasksDto1.setDeadline(formatToLocalDate ("1 Aug 2026"));
    postTasksDto1.setCategoryId(1); 
    postTasksDto1.setMakeArchived(false);


    Category mockCategory = new Category();
    mockCategory.setCategoryId(1); 
    mockCategory.setCategoryName("JavaScript");

    Task mockTask = new Task();
    mockTask.setTaskName(postTasksDto1.getTaskName());
    mockTask.setDeadline(postTasksDto1.getDeadline());
    mockTask.setCategory(mockCategory);
    mockTask.setMakeArchived(postTasksDto1.getMakeArchived());
    

    when(categoryRepository.findById(1)).thenReturn(Optional.of(mockCategory));
    when(taskRepository.save(any(Task.class))).thenReturn(mockTask); 
    taskService.createTask(postTasksDto1);

    verify(taskRepository, times(1)).save(any(Task.class)); 
    assertEquals("JavaScript", mockTask.getCategory().getCategoryName());
    }

   LocalDate formatToLocalDate (String dateStr) {
      DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
      return LocalDate.parse(dateStr, timeFormatter);
    }


}
