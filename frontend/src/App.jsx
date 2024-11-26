
import { useState } from 'react'
import React, {useEffect, useContext} from 'react';
import {BrowserRouter as Router, Routes, Route} from "react-router-dom";
import './App.css'
import Signin from './components/Auth/signin';
import SignupType from './components/Auth/signupType';

function App() {
  return (
    <>
      <Router>
        <Routes>
          <Route path="/signupType" element={<SignupType />} />
          <Route path='/signin' element={<Signin/>} />
        </Routes>
      </Router>
    </>
  )
}

export default App
