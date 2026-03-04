import React, { useState } from "react";
import classes from '../styles/App.module.scss';

function TodoItem({task, category, id, updateTask, deleteTask, duplicateTask, allCategories = [], deadline, updateTaskCategory}) {

  const [editedTask, setEditedTask] = useState(task);
  const [taskCategory, setTaskCategory] = useState(category);

  const handleUpdate = () => {
    if(editedTask !== "" && editedTask != null) {
    updateTask(id, editedTask);
    }
  };

  let handleCategoryChange = (e) =>{
    const newCategId = e.target.value;
    setTaskCategory(newCategId);
    updateTaskCategory(id, newCategId);

  }

  
  return (
    <div className={classes.form__taskAmend}>

      <input value={editedTask} type="text" onChange = {(e) => setEditedTask(e.target.value)}/>

      
      <button className={classes.button__updateTask} 
      onClick={handleUpdate}>Update</button>
      <button className={classes.button__duplicateTask} onClick={()=>duplicateTask(id)}>Duplicate</button>
      <select value={taskCategory} onChange={handleCategoryChange}>
        {allCategories.map((categ) => (
          <option key={categ.categoryId} value={categ.categoryId}>{categ.categoryName}</option>
        ))}
      </select>
      <button className = {classes.button_deleteTask} 
      onClick={() => deleteTask(id)}>Delete</button>
      <div className={classes[`form__taskAmend-deadline`]}>
        <b>Deadline: {deadline}</b>
      </div>
    </div>
  );
}

export default TodoItem;