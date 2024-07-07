import React, { useState } from 'react';
import { Container } from 'reactstrap';
import { useNavigate, Link } from 'react-router-dom';
import AppNavbar from './AppNavbar';

function Admin() {
  const [username, setUsername] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const navigate = useNavigate();

  const handleSubmit = (e) => {
    e.preventDefault();
    // Login Logic
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
