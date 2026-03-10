import React, { useState } from "react";
import classes from '../styles/App.module.scss';

function CategoryInput({addCategory}) {

  const [category, setCategory] = useState();
  const handleAddCategory = () => {
    if(category !== ""){
      addCategory(category);
      setCategory("");
    }
  }

  return (
    <>
    <form className={classes.form__addCateg}>
      <input type="text" value={category} placeholder="Add a new category" onChange={(e)=> setCategory(e.target.value)}/>
      <button onClick = {handleAddCategory}>Add Category</button>
    </form>
    </>
  )
}


export default CategoryInput;
