import React, { useState } from "react";
import classes from '../styles/App.module.scss';


function TaskInput({categories, addTask}) {
 
  const [taskName, setTaskName] = useState(""); 
  //just in case: array is empty or not loaded yet (categories[0] is empty), it will safely fall back to ""
  const [category, setCategory] = useState(categories[0]||"");
  const [deadline, setDeadline] = useState("");


  const handleAddTask = () => {
    if(taskName!=="" && categories)
    {
      addTask(taskName, category, deadline);
      setTaskName("");
    }
  };

  return ( <>
  <div className={classes.form__taskManagement}>
    <input type="text" value={taskName} onChange={(e) => setTaskName(e.target.value)} placeholder="Enter task name"
    />
    <select onChange={(e)=>setCategory(e.target.value)} value={category}>
      {
        categories.map((categ)=>(<option key={categ.categoryId} value={categ.categoryId}>
          {categ.categoryName}
        </option>))
      }
    </select>
    <input type="text" placeholder="Due date (e.g: 01 Aug 2027)" value ={deadline} onChange={(e) => setDeadline(e.target.value)}></input>
    <button onClick={handleAddTask}>Add Task</button>
  </div>
  </> )
}

export default TaskInput;


