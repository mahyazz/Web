import React, { useState } from 'react';
import { Container } from 'reactstrap';
import { useNavigate } from 'react-router-dom';
import AppNavbar from './AppNavbar';

function Admin() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const navigate = useNavigate();

  const handleSubmit = (e) => {
    e.preventDefault();
    // Login Logic
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
