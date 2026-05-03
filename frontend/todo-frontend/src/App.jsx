import React from "react";
import TodoList from "./components/TodoList";
import "./styles/App.css";

function App() {
  return (
    <div className="App">
      <h1>Task Management App</h1>
      <TodoList />
    </div>
  );
}

export default App;
