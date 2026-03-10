import React, { useState, useEffect } from "react";
import TaskInput from "../../../todo-frontend/src/components/TaskInput";
import CategoryInput from "../../../todo-frontend/src/components/CategoryInput";
import TodoItem from "../../../todo-frontend/src/components/TodoItem";
import classes from "../styles/App.module.scss";

function TodoList() {

  const [categories, setCategories] = useState([]);
  const [todo, setTodo] = useState([]);
  const [archived, setArchived] = useState([]);
  
  // Fetch categories from the backend
  useEffect(() => {

    const fetchAllData = async () => {
      try {
        const categResponse = await fetch ('http://localhost:8080/categories');
      
        if(!categResponse.ok){
          throw new Error("Cannot fetch categories.");
        }
        //json() runs on response objects (returned by a fetch())
        const categData =  await categResponse.json();
        setCategories(categData);
      }
      catch (error){
        console.log("Error in fetching categories: " + error);
      }
      try{
        let activeTaskResponse = await fetch('http://localhost:8080/todos');
        let archivedTaskResponse = await fetch('http://localhost:8080/todos/archived');

        if(!activeTaskResponse.ok || !archivedTaskResponse.ok){
          throw new Error("Cannot fetch tasks.");
        }

        const allActiveTasks = await activeTaskResponse.json();
        const allArchivedTasks = await archivedTaskResponse.json();
        setTodo(allActiveTasks);
        setArchived(allArchivedTasks);

      }
      catch (error) {
        console.log("Error in fetching todos: "+ error);
      }
    }

     fetchAllData();     
  }, []); // Empty dependency array ensures the API call is made once when the component mounts


  //For the post method - add a new category
  const addCategory = (category) => {
     fetch('http://localhost:8080/categories', {
      method: "POST",
      headers: {
        "Content-type": "application/json",
      },
      body: JSON.stringify({ categoryName: category })
    })
    .then((response) => response.json())

    //addedCategory is the result from the backend after the POST requests to make a new category
    .then((addedCategory) => setCategories([...categories, addedCategory]))
    .catch((error) => console.log("Cannot a new category. "+ error))
  };

  //for the post methods - add a new task using an old category (no need to refetch the categories)
  const addTask = (taskName, category, deadline) => {
    fetch('http://localhost:8080/todos', {
      method: "POST",
      headers: {
        "Content-type": "application/json",
      },
      //recheck
      body: JSON.stringify({
        taskName: taskName,
        categoryId: category, 
        deadline,
        makeArchived: false,
      })
    })
    .then((response) => response.json())
    .then((addedTask) => setTodo([...todo, addedTask]))
    .catch((error) => console.log("Cannot a new task. "+ error))
  };

  //update task - name
  const updateTask = (id, updatedTask) => {
    const taskToUpdate = todo.find(task => task.id ===id);
    fetch(`http://localhost:8080/todos/${id}`, {
      method: "PUT",
      headers: {
        "Content-type": "application/json",
      },
      body: JSON.stringify({
        id: taskToUpdate.id,
        taskName: updatedTask,
        categoryId: taskToUpdate.category?.categoryId,
        deadline: taskToUpdate.deadline,
        makeArchived:taskToUpdate.makeArchived
      })
    })
    .then((response) => response.json())
    .then(() => {
    setTodo(prev =>
      prev.map(task =>
        task.id === id
          ? { ...task, taskName: updatedTask } 
          : task
      )
    );
    })
    .catch((error) => console.log("Failed to update the task. "+ error))
  };

    //update category - name
  const updateTaskCategory = (taskId, newCategId) => {
    //look for the right object using find() through taskId
    const taskToUpdate = todo.find(task => task.id === taskId);
    fetch(`http://localhost:8080/todos/${taskId}`, {
      method: "PUT",
      headers: {
        "Content-type": "application/json",
      },
      body: JSON.stringify({
        id: taskToUpdate.id,
        taskName: taskToUpdate.taskName,
        categoryId: newCategId,
        deadline: taskToUpdate.deadline,
        makeArchived: taskToUpdate.makeArchived
      })
    })
    .then((response) => response.json())
    .then((updatedTask) => {
    setTodo(prev =>
      prev.map(task =>
        task.id === taskId? updatedTask : task
        )
      );
    })
    .catch((error) => console.log("Failed to update the category. "+ error))
  };

  const deleteTask = (id) => {
   fetch(`http://localhost:8080/todos/${id}`, {
      method: "DELETE"
    })
    .then((response) => response.json())
    .then((archivedTask) => setTodo(prev => prev.filter(task => task.id!=id)))
    .catch((error) => console.log("Failed to archive the task. "+ error))
  };

  const duplicateTask = (id) =>{
    fetch(`http://localhost:8080/todos/${id}/duplicate`, {
      method: "POST"
    })
    .then((response) => response.json())
    .then((newTask) => setTodo(prev => [...prev, newTask]))
    .catch((error) => console.log("Failed to duplicate the task. "+ error))
  }
console.log(todo);
  return (
   <div className="todo-list">
      <h2>Task Categories</h2>
      <CategoryInput addCategory={addCategory}/>

      <h2>Tasks</h2>
      <TaskInput addTask={addTask} categories={categories}/>

      {todo.map((todoTask) => (
              
           <TodoItem
           key={todoTask.id} taskData={{id: todoTask.id, taskName: todoTask.taskName, category:todoTask.category?.categoryId, 
            categoryName: todoTask.category?.categoryName, allCategories:categories, deadline: todoTask.deadline, deleteTask:deleteTask, updateTask:updateTask, duplicateTask:duplicateTask, updateTaskCategory: updateTaskCategory}}/>
      ))}

      <h2>Archived Tasks</h2> 
        
      {archived.map(task => ( <div className={classes.list__archivedTask} key={task.id}> 
        <div>{task.taskName} </div>

        <span className={classes.list__archivedTask}>Deadline: {task.deadline}</span>
      </div> ))}

     
    </div>
    
  );
}

export default TodoList;