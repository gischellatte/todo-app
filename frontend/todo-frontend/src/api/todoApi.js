export async function fetchCategories() {
    const categResponse = await fetch ('http://localhost:8080/categories');
    if(!categResponse.ok){
        throw new Error("Cannot fetch categories.");
    }
    return response.json();
}

export async function  fetchActiveTodos() {
    
}