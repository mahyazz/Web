import React, { useState } from 'react';
import { Container } from 'reactstrap';
import { useNavigate } from 'react-router-dom';
import AppNavbar from './AppNavbar';

function Admin() {
  const [username, setUsername] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();
    // try {
    //   const response = await fetch('http://localhost:3002/user/register', {
    //   method: 'POST',
    //   headers: {
    //     'Content-Type': 'application/json'
    //   },
    //   body: {
    //     username: username,
    //     email: email,
    //     password: password
    //   }
    // })

    // if (!response.ok) {
    //   throw new Error('Network response was not ok');
    // }

    // const resStatus = await response.status();
    // if (resStatus === 200) {
    //   navigate('/user/api-tokens');
    // }
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
            <label htmlFor="email">Email</label>
            <input
              type="text"
              id="email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
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
          <button type="submit" className="login-button">Sign Up</button>
        </form>
      </Container>
    </div>
  );
}

export default Admin;
