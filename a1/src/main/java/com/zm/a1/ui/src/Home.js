import React from 'react';
import AppNavbar from './AppNavbar';
import { Link } from 'react-router-dom';
import { Button, Container } from 'reactstrap';

const Home = () => {
  return (
    <div>
      <AppNavbar/>
      <Container className='home-container'>
        <Button className='home-button'><Link to="/admin">Admin</Link></Button>
        <Button className='home-button'><Link to="/user/login">User</Link></Button>
      </Container>
    </div>
  );
}

export default Home;