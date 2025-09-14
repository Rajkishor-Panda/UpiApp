import React, { useState } from "react";
import api from "../api";

function TransactionForm() {
  const [form, setForm] = useState({ fromMobile: "", toMobile: "", amount: 0, pin: "" });

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await api.post("/send", form);
      alert("Transaction successful!");
    } catch (err) {
      alert("Error in transaction: " + err.message);
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <h3>Send Money</h3>
      <input name="fromMobile" placeholder="From Mobile" onChange={handleChange} />
      <input name="toMobile" placeholder="To Mobile" onChange={handleChange} />
      <input name="amount" placeholder="Amount" type="number" onChange={handleChange} />
      <input name="pin" placeholder="PIN" type="password" onChange={handleChange} />
      <button type="submit">Send</button>
    </form>
  );
}

export default TransactionForm;
