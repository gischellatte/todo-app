import React, { useState, useEffect } from "react";
import classes from '../styles/App.module.scss';

function TodoItem({taskData}) {

  const {taskName, deadline, category, categoryName,  id, updateTask, deleteTask, duplicateTask, allCategories ,  updateTaskCategory} = taskData;
  const [editedTask, setEditedTask] = useState(taskName);
  const [taskCategory, setTaskCategory] = useState(category);

  // Sync taskCategory state with category if it changes
  useEffect(() => {
    setTaskCategory(category); // This ensures that the category is always updated
  }, [category]);
  
  const handleUpdate = () => {
    if(editedTask !== "" && editedTask != null) {
    updateTask(id, editedTask);
    }
  };

  let handleCategoryChange = (e) =>{
    const newCategId = e.target.value;
    setTaskCategory(newCategId);
    
 
  }

  const handleUpdateCategory = () =>{
    if(taskCategory!==category){
      updateTaskCategory(id, taskCategory);
    }
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
        <button className={classes.button__confirmCateg} onClick={handleUpdateCategory}>Confirm Category</button>
      </div>
      
      
      <div className={classes[`form__taskAmend-deadline`]}>
        <b>Deadline: {deadline}</b>
      </div>
    </div>
  );
}

export default TodoItem;