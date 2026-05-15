import React, { useState } from "react";
import classes from '../styles/App.module.scss';


function TaskInput({categories, addTask}) {
 
  const [taskName, setTaskName] = useState(""); 

  const [category, setCategory] = useState(categories.length > 0 ? categories[0].categoryId:"");
  const [deadline, setDeadline] = useState("");


  const formatDate = (dateString) => {
    const date = new Date (dateString);
    const options = {year: '2-digit', month: 'short', day:'2-digit'};
    return Intl.DateTimeFormat('en-GB', options).format(date)
  }

  const handleAddTask = () => {
    if(taskName!=="" && categories)
    { console.log('Deadline before sending:', deadline);
      addTask(taskName, category, deadline);
      setTaskName("");
      setDeadline("");
    }
  };

    
      //Date blocker (for the deadline) - based on AEST time (Canberra Melbourne & Sydney)
     const todayDate = new Date();
     const sydTodayDate = new Date(todayDate.getTime() - todayDate.getTimezoneOffset() * 6000).toISOString().split("T")[0];
   

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
    <input type="date" placeholder="" value ={deadline ||''} onChange={(e) => setDeadline(e.target.value)} min={sydTodayDate}/>
    
    <button onClick={handleAddTask}>Add Task</button>
      
  </div>

  <div className={classes.todo__formattedDeadline}>Deadline: {deadline? formatDate(deadline) : ""} </div>
  </> )
}

export default TaskInput;

