import axios from "axios";


const BASE_URL = "http://10.190.67.164:8080/tasks";
export const loginUser = (data) => axios.post("http://10.190.67.164:8080/users/login", data);
export const getAllTasks = () => axios.get(BASE_URL);
export const createTask = (userId,task) => axios.post(`${BASE_URL}/${userId}`, task);
export const deleteTask = (id) => axios.delete(`${BASE_URL}/${id}`);
export const updateTask = (id, task) =>axios.put(`${BASE_URL}/${id}`, task);
export const getTaskById = (id) => axios.get(`${BASE_URL}/${id}`);
export const searchTaskByTitle = (title) => axios.get(`${BASE_URL}/search/${title}`);

