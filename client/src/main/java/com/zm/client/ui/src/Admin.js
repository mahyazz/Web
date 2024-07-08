import React, { useState } from 'react';
import { Container } from 'reactstrap';
import { useNavigate } from 'react-router-dom';
import AppNavbar from './AppNavbar';

function Admin() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();
    // try {
    //   const response = await fetch('http://localhost:3002/admin/authenticate', {
    //     method: 'GET',
    //     headers: {
    //       'Content-Type': 'application/json'
    //     },
    //     body: {
    //       username: username,
    //       password: password
    //     }
    //   })

    //   if (!response.ok) {
    //     throw new Error('Network response was not ok');
    //   }

    //   const resStatus = await response.status();
    //   if (resStatus === 200) {
    //     navigate('/admin/users');
    //   } else if(resStatus === 403){
    //     alert('403 Forbidden: Invalid Admin');
    //     throw new Error('Forbidden: Invalid Admin');
    //   }
    // } catch (error) {
    //   console.error('Error:', error);
    //   alert('Error occurred while sending request');
    // }
    navigate('/admin/users');
  };

  return (
    <div>
      <AppNavbar />
      <Container className='login-container'>
        <form onSubmit={handleSubmit} className="login-form">
          <div className="login-form-group">
            <label htmlFor="username">Username</label>
            <input
              type="text"
              id="username"
              value={username}
              onChange={(e) => setUsername(e.target.value)}
              className="login-form-control"
            />
          </div>
          <div className="login-form-group">
            <label htmlFor="password">Password</label>
            <input
              type="password"
              id="password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              className="login-form-control"
            />
          </div>
          <button type="submit" className="login-button">Login</button>
        </form>
      </Container>
    </div>
  );
}

export default Admin;
