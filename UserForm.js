import React, { useState } from "react";
import api from "../api";

function UserForm() {
  const [form, setForm] = useState({ mobile: "", name: "", pin: "", balance: 0 });

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await api.post("/register", form);
      alert("User created successfully!");
    } catch (err) {
      alert("Error creating user: " + err.message);
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <h3>Create User</h3>
      <input name="mobile" placeholder="Mobile" onChange={handleChange} />
      <input name="name" placeholder="Name" onChange={handleChange} />
      <input name="pin" placeholder="PIN" type="password" onChange={handleChange} />
      <input name="balance" placeholder="Balance" type="number" onChange={handleChange} />
      <button type="submit">Create</button>
    </form>
  );
}

export default UserForm;
