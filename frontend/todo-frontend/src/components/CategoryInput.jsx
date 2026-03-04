import React, { useState } from "react";
import classes from '../styles/App.module.scss';

function CategoryInput({addCategory}) {

  const [category, setCategory] = useState();
  const handleAddCategory = () => {
    if(category !== ""){
      addCategory(category);
      //clear the state after adding a new category, prevents duplicate submissions if the user accidentally executes the function more than 1x 
      setCategory("");
    }
  }

  return (
    <>
    {/*onSubmit function */}
    <form className={classes.form__addCateg}>
      <input type="text" value={category} placeholder="Add a new category" onChange={(e)=> setCategory(e.target.value)}/>
      <button onClick = {handleAddCategory}>Add Category</button>
    </form>
    </>
  )
}

export default CategoryInput;