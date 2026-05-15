import React, { useState, useEffect } from "react";
import classes from '../styles/App.module.scss';

function CategoryInput({addCategory, updateCategName}) {

  //To add new category
  const [category, setCategory] = useState("");

  //To display all categories
  const [fullCategories, setFullCategories] = useState([]);
  const [isOpen, setIsOpen] = useState(false);
 
  //To update category names
  const [editingId, setEditingId] = useState (null);
  const [tempName, setTempName] = useState({});
  

  useEffect(() => { 
    const fetchAllCateg = async () => { 
    try { const categResponse = await fetch ('http://localhost:8080/categories'); 
      if(!categResponse.ok){ 
        throw new Error("Cannot fetch categories."); 
      } 
      const categData = await categResponse.json(); 
      setFullCategories(categData); 
    } 
    catch (error)
    {console.log("Cannot list all categories: " + error); 
    }
   }
    fetchAllCateg();
  }, []);
   

  const handleAddCategory = (e) => {
    e.preventDefault();
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
    <div>
       <button onClick={()=>setIsOpen(true)}>Manage categories</button>
        {isOpen && (
         <>
           <h3>All categories</h3>
           <div>
              {fullCategories.map((eachCategory) => (
                <div key={eachCategory.categoryId}>
      
                  {
                  editingId === eachCategory.categoryId ? (
                    <div>
                      <input
                
                        value={tempName[eachCategory.categoryId] || ""}
                        
                        onChange={(e) => setTempName((prev) => ({
                            ...prev,
                            [eachCategory.categoryId]: e.target.value,
                          }))}          />

                      <button onClick={()=>{
                       
                        updateCategName(eachCategory.categoryId, tempName[eachCategory.categoryId]);
                        
                        setFullCategories((prev) => (
                            prev.map (
                              categBucket => categBucket.categoryId === eachCategory.categoryId ?
                              {
                                ...categBucket, categoryName: tempName[eachCategory.categoryId]
                              }: categBucket)
                          ));
                           
                        setEditingId(null);
                        setTempName((prev) => {
                          const copy = {...prev};
                          delete copy [eachCategory.categoryId]; 
                          return copy;
                        })  
                      }}
                   >
                        Save
                      </button>

                      <button onClick={()=>{
                        setEditingId(null);
                        setTempName({});
                      }}>
                        Cancel
                      </button>
                    </div>
                  ) : (
                    <div>
                      <span>{eachCategory.categoryName}</span>
                      <button onClick={()=>{
                        setEditingId(eachCategory.categoryId);
                        
                        setTempName((prev) => ({...prev, [eachCategory.categoryId]:eachCategory.categoryName}));
                      }}>Edit</button>
                    </div>
                  )}
                   </div>))}
           </div>
         <button onClick={()=>setIsOpen(false)}>Close</button>
       </>
      )}
    </div>
    </>
  )
}


export default CategoryInput;
