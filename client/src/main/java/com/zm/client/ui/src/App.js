import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import './App.css';
import Home from './Home';
import Admin from './Admin';
import UserTable from './UserTable';
import UserLogin from './UserLogin';
import UserRegister from './UserRegister';
import ApiTokens from './ApiTokens';
import CreateToken from './CreateToken';
import Countries from './Countries';

const App = () => {
  return (
    <Router>
      <Routes>
        <Route exact path="/" element={<Home/>}/>
        <Route path='/admin/users' exact={true} element={<UserTable/>}/>
        <Route path='/admin' element={<Admin/>}/>
        <Route path='/user/register' element={<UserRegister/>}/>
        <Route path='/user/login' element={<UserLogin/>}/>
        <Route path='/user/api-tokens' element={<ApiTokens/>}/>
        <Route path='/user/api-tokens/new' element={<CreateToken/>}/>
        <Route path='/countries' element={<Countries/>}/>
      </Routes>
    </Router>
  )
}

export default App;