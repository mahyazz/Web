import React, { useState } from 'react';
import { Container } from 'reactstrap';
import { useNavigate, Link } from 'react-router-dom';
import AppNavbar from './AppNavbar';

function Admin() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();
    // try {
    //   const response = await fetch('http://localhost:3002/user/authenticate', {
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
    //     navigate('/user/api-tokens');
    //   } else if(resStatus === 403){
    //     alert('403 Forbidden: Invalid User');
    //     throw new Error('Forbidden: Invalid User');
    //   }
    // } catch (error) {
    //   console.error('Error:', error);
    //   alert('Error occurred while sending request');
    // }
    navigate('/user/api-tokens');
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
          <p>Don't have an account? <Link to="/user/register">Register here!</Link></p>
        </form>
      </Container>
    </div>
  );
}

export default Admin;
