import { useState } from "react";
import React, { useEffect, useContext } from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import "./App.css";
import Signin from "./components/Auth/Signin";
import SignupType from "./components/Auth/SignupType";
import { useAuthContext } from "./Context/AuthContext";
import { Toaster } from "react-hot-toast";

function App() {
  const { authUser, token } = useAuthContext();
  console.log("authUser", authUser);
  console.log("token", token);
  return (
    <>
      <Toaster position="top-center" reverseOrder={false} />
      <Router>
        <Routes>
          {/* <Route path="/" element={< />} /> */}
          <Route path="/signupType" element={<SignupType />} />
          <Route path="/signin" element={<Signin />} />
        </Routes>
      </Router>
    </>
  );
}

export default App;
