import React, { useState } from "react";
import {
  ThemeProvider, createTheme, CssBaseline,
  Container, Typography, Box, Button,
  TextField, Table, TableBody, TableCell,
  TableContainer, TableHead, TableRow, Paper
} from "@mui/material";
import { Brightness4, Brightness7 } from "@mui/icons-material";
import { createUser, sendMoney, getTransactions } from "./api";

export default function App() {
  const [darkMode, setDarkMode] = useState(false);
  const [transactions, setTransactions] = useState([]);
  const [user, setUser] = useState({ mobile: "", name: "", pin: "", balance: "" });
  const [txn, setTxn] = useState({ fromMobile: "", toMobile: "", amount: "", pin: "" });
  const [searchMobile, setSearchMobile] = useState("");

  const theme = createTheme({ palette: { mode: darkMode ? "dark" : "light" } });

  const handleCreateUser = async () => {
    try {
      await createUser(user);
      alert("User created successfully!");
      setUser({ mobile: "", name: "", pin: "", balance: "" });
    } catch (err) {
      alert("Error creating user: " + err.message);
    }
  };

  const handleSendMoney = async () => {
    try {
      await sendMoney(txn);
      alert("Transaction successful!");
      setTxn({ fromMobile: "", toMobile: "", amount: "", pin: "" });
    } catch (err) {
      alert("Error sending money: " + err.message);
    }
  };

  const handleFetchTransactions = async () => {
    try {
      const res = await getTransactions(searchMobile);
      setTransactions(res.data);
    } catch (err) {
      alert("Error fetching transactions: " + err.message);
    }
  };

  return (
    <ThemeProvider theme={theme}>
      <CssBaseline />
      <Container maxWidth="md" sx={{ mt: 4 }}>
        <Box display="flex" justifyContent="space-between" alignItems="center">
          <Typography variant="h4" fontWeight="bold">Mock UPI App</Typography>
          <Button
            startIcon={darkMode ? <Brightness7 /> : <Brightness4 />}
            onClick={() => setDarkMode(!darkMode)}
          >
            {darkMode ? "Light Mode" : "Dark Mode"}
          </Button>
        </Box>

        {/* Create User */}
        <Box mt={4}>
          <Typography variant="h6">Create User</Typography>
          <Box display="flex" gap={2} mt={2}>
            <TextField label="Mobile" value={user.mobile} onChange={e => setUser({ ...user, mobile: e.target.value })} />
            <TextField label="Name" value={user.name} onChange={e => setUser({ ...user, name: e.target.value })} />
            <TextField label="PIN" type="password" value={user.pin} onChange={e => setUser({ ...user, pin: e.target.value })} />
            <TextField label="Balance" type="number" value={user.balance} onChange={e => setUser({ ...user, balance: e.target.value })} />
            <Button variant="contained" onClick={handleCreateUser}>Create</Button>
          </Box>
        </Box>

        {/* Send Money */}
        <Box mt={4}>
          <Typography variant="h6">Send Money</Typography>
          <Box display="flex" gap={2} mt={2}>
            <TextField label="From Mobile" value={txn.fromMobile} onChange={e => setTxn({ ...txn, fromMobile: e.target.value })} />
            <TextField label="To Mobile" value={txn.toMobile} onChange={e => setTxn({ ...txn, toMobile: e.target.value })} />
            <TextField label="Amount" type="number" value={txn.amount} onChange={e => setTxn({ ...txn, amount: e.target.value })} />
            <TextField label="PIN" type="password" value={txn.pin} onChange={e => setTxn({ ...txn, pin: e.target.value })} />
            <Button variant="contained" color="success" onClick={handleSendMoney}>Send</Button>
          </Box>
        </Box>

        {/* Transaction History */}
        <Box mt={4}>
          <Typography variant="h6">Transaction History</Typography>
          <Box display="flex" gap={2} mt={2}>
            <TextField label="Enter Mobile" value={searchMobile} onChange={e => setSearchMobile(e.target.value)} />
            <Button variant="outlined" onClick={handleFetchTransactions}>Fetch</Button>
          </Box>

          <TableContainer component={Paper} sx={{ mt: 2 }}>
            <Table>
              <TableHead>
                <TableRow>
                  <TableCell>From</TableCell>
                  <TableCell>To</TableCell>
                  <TableCell>Amount</TableCell>
                  <TableCell>Timestamp</TableCell>
                </TableRow>
              </TableHead>
              <TableBody>
                {transactions.map((t, i) => (
                  <TableRow key={i}>
                    <TableCell>{t.fromMobile}</TableCell>
                    <TableCell>{t.toMobile}</TableCell>
                    <TableCell>{t.amount}</TableCell>
                    <TableCell>{t.timestamp}</TableCell>
                  </TableRow>
                ))}
              </TableBody>
            </Table>
          </TableContainer>
        </Box>
      </Container>
    </ThemeProvider>
  );
}
