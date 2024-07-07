import React from 'react';
import './App.css';
import Home from './Home';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import UserTable from './UserTable';

const App = () => {
  return (
    <Router>
      <Routes>
        <Route exact path="/" element={<Home/>}/>
        <Route path='/admin/users' exact={true} element={<UserTable/>}/>
      </Routes>
    </Router>
  )
}

export default App;