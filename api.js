import axios from "axios";

// Change baseURL if your backend runs on a different port
const api = axios.create({
  baseURL: "http://localhost:8080/api", 
});

export const createUser = (user) =>
  api.post("/register", user);

export const sendMoney = (transaction) =>
  api.post("/send", transaction);

export const getTransactions = (mobile) =>
  api.get(`/transactions/${mobile}`);
