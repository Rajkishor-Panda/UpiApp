import React, { useState } from "react";
import api from "../api";

function TransactionHistory() {
  const [mobile, setMobile] = useState("");
  const [transactions, setTransactions] = useState([]);

  const fetchTransactions = async () => {
    try {
      const res = await api.get(`/transactions/${mobile}`);
      setTransactions(res.data);
    } catch (err) {
      alert("Error fetching transactions: " + err.message);
    }
  };

  return (
    <div>
      <h3>Transaction History</h3>
      <input
        value={mobile}
        onChange={(e) => setMobile(e.target.value)}
        placeholder="Enter Mobile"
      />
      <button onClick={fetchTransactions}>Fetch</button>

      <ul>
        {transactions.map((tx, i) => (
          <li key={i}>
            {tx.fromMobile} → {tx.toMobile} : ₹{tx.amount}
          </li>
        ))}
      </ul>
    </div>
  );
}

export default TransactionHistory;
