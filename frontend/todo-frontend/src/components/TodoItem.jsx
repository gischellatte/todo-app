import React, { useState, useEffect } from "react";
import classes from '../styles/App.module.scss';

function TodoItem({taskData}) {

  const {taskName, deadline, category, categoryName,  id, updateTask, deleteTask, duplicateTask, allCategories ,  updateTaskCategory} = taskData;
  const [editedTask, setEditedTask] = useState(taskName);
  const [taskCategory, setTaskCategory] = useState(category);

  useEffect(() => {
    setTaskCategory(category);
  }, [category]);
  
  const handleUpdate = () => {
    if(editedTask !== "" && editedTask != null) {
    updateTask(id, editedTask);
    }

    if(taskCategory!==category){
      updateTaskCategory(id, taskCategory);
    }    
  };

  let handleCategoryChange = (e) =>{
    const newCategId = e.target.value;
    setTaskCategory(newCategId); 
  }


  return (
    <div className={classes.form__taskAmend}>

      <input value={editedTask} type="text" onChange = {(e) => setEditedTask(e.target.value)}/>

      <button className={classes.button__updateTask} 
      onClick={handleUpdate}>Update</button>
      <button className={classes.button__duplicateTask} onClick={()=>duplicateTask(id)}>Duplicate</button>
      <button className = {classes.button_deleteTask} 
      onClick={() => deleteTask(id)}>Delete</button>
      <div className = {classes.dropDown__categ}>
        <select value={taskCategory} onChange={handleCategoryChange}>
        {allCategories.map((categ) => (
          <option key={categ.categoryId} value={categ.categoryId}>{categ.categoryName}</option>
        ))}
        </select>
       
      </div>
      
      
      <div className={classes[`form__taskAmend-deadline`]}>
        <b>Deadline: {deadline}</b>
      </div>
    </div>
  );
}


export default TodoItem;
