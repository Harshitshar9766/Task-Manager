import React, { useEffect, useState } from "react";
import "./App.css";
import {
  getAllTasks,
  createTask,
  deleteTask,
  updateTask,
  getTaskById,
  searchTaskByTitle,
  loginUser
} from "./service/Api";

function App() {

const [user, setUser] = useState(null);
const [email, setEmail] = useState("");
const [password, setPassword] = useState("");


  const [tasks, setTasks] = useState([]);
  const [title, setTitle] = useState("");
const [editingId, setEditingId] = useState(null);
const [description, setDescription] = useState("");
const [searchId, setSearchId] = useState("");
const [searchTitle, setSearchTitle] = useState("");

const [dueDate, setDueDate] = useState("");
const [completed, setCompleted] = useState(false);

  useEffect(() => {
    loadTasks();
  }, []);

  const loadTasks = () => {
    getAllTasks()
      .then((res) => setTasks(res.data))
      .catch((err) => console.log(err));
  };

  const handleAdd = () => {
  if (!title) return;

  const taskData = {
    title,
    description,
    dueDate,
    completed
  };

  if (editingId) {
    updateTask(editingId, taskData).then(() => {
      setEditingId(null);
      resetForm();
      loadTasks();
    });
  } else {
    createTask(user.id,taskData).then(() => {
    resetForm();
      loadTasks();
    });
  }
};


const resetForm = () => {
     setTitle("");
           setDescription("");
             setDueDate("");
  setCompleted(false);
}

const handleLogin = () => {
  loginUser({ email, password })
    .then((res) => {
      setUser(res.data);  
    })
    .catch(() => {
      alert("Invalid credentials");
    });
};

  const handleDelete = (id) => {
    deleteTask(id).then(() => loadTasks());
  };

const handleSearchById = () => {
  if (!searchId) return;

  getTaskById(searchId)
    .then((res) => setTasks([res.data])) 
    .catch(() => alert("Task not found"));
};

const handleSearchByTitle = () => {
  if (!searchTitle) return;

  searchTaskByTitle(searchTitle)
    .then((res) => setTasks(res.data))
    .catch(() => alert("No task found"));
};





  return (
    <div className="container">
      <h2>Task Manager</h2>
      {!user ? (
  <div className="container">
    <h2>Login</h2>

    <input
      type="email"
      placeholder="Enter email"
      value={email}
      onChange={(e) => setEmail(e.target.value)}
    />

    <input
      type="password"
      placeholder="Enter password"
      value={password}
      onChange={(e) => setPassword(e.target.value)}
    />

    <button onClick={handleLogin}>Login</button>
  </div>
) : (
<>
  <div className="search-box">
 
  <div className="search-row">
    <input
      type="number"
      placeholder="Search by ID"
      value={searchId}
      onChange={(e) => setSearchId(e.target.value)}
    />
    <button onClick={handleSearchById}>Search ID</button>
  </div>

  
  <div className="search-row">
    <input
      type="text"
      placeholder="Search by Title"
      value={searchTitle}
      onChange={(e) => setSearchTitle(e.target.value)}
    />
    <button onClick={handleSearchByTitle}>Search Title</button>
  </div>


  <div className="reset-row" >
    <button onClick={() => { loadTasks();
       resetForm(); 
       setSearchId("");
        setSearchTitle("");
         }}>Reset</button>
  </div>

</div>


<div className="input-group">
  <input
    type="text"
    placeholder="Enter title"
    value={title}
    onChange={(e) => setTitle(e.target.value)}
  />

  <input
    type="text"
    placeholder="Enter description"
    value={description}
    onChange={(e) => setDescription(e.target.value)}
  />


<input
type="date"
value={dueDate}
onChange={(e) => setDueDate(e.target.value)}
/>


<label style={{display: "flex", alignItems:"center", gap:"5px" }}>
<input
type="checkbox"
checked={completed}
onChange={(e) => setCompleted(e.target.checked)}
/>
Completed
</label>

  <button className="add-btn" onClick={handleAdd}>
    {editingId ? "Update" : "Add"}
  </button>
</div>

      <ul className="task-list">
        {tasks.map((task) => (
        <li className="task-item" key={task.id}>
  <div>
    <strong>{task.title}</strong>
    <p>{task.description}</p>
    <p>Due Date:{task.dueDate}</p>
    <p>Status: {task.completed ? "Completed" : "Pending"}</p>
  </div>

  <div>
    <button
      className="delete-btn"
      onClick={() => handleDelete(task.id)}
    >
      Delete
    </button>

    <button onClick={() => {
      setEditingId(task.id);
      setTitle(task.title);
      setDescription(task.description);
    }}>
      Edit
    </button>
  </div>
</li>
        ))}
      </ul>
      </> 
)}
    </div>
  );
}

export default App;