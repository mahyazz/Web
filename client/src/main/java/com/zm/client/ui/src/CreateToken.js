import React, { useState } from 'react';
import { Container } from 'reactstrap';
import DatePicker from 'react-datepicker';
import 'react-datepicker/dist/react-datepicker.css';
import { useNavigate } from 'react-router-dom';
import AppNavbar from './AppNavbar';

function CreateTokens() {
  const [name, setName] = useState('');
  const [expiry, setExpiry] = useState('');
  const navigate = useNavigate();

  const handleSubmit = (e) => {
    e.preventDefault();
    createToken();
    navigate('/user/api-tokens');
  };

  const createToken = async () => {
    const url = 'http://localhost:3002/user/api-tokens';
    const data = {
      name: name,
      expire_date: expiry
    };
    console.log(data);

    try {
      const response = await fetch(url, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(data),
      });

      if (!response.ok) {
        throw new Error('Network response was not ok');
      }

      const responseData = await response.json();
      alert(`Response received: ${JSON.stringify(responseData)}`);
    } catch (error) {
      console.error('Error:', error);
      alert('Error occurred while sending request');
    }
  };

  return (
    <div>
      <AppNavbar />
      <Container className='login-container'>
        <form onSubmit={handleSubmit} className="login-form">
          <div className="login-form-group">
            <label htmlFor="name">Name</label>
            <input
              type="text"
              id="name"
              value={name}
              onChange={(e) => setName(e.target.value)}
              className="login-form-control"
            />
          </div>
          <div className="login-form-group">
          <label for="datetime">Expiration Date</label>
          <DatePicker
            id="datetime"
            selected={expiry}
            onChange={setExpiry}
            showTimeSelect
            timeFormat="HH:mm"
            timeIntervals={15}
            dateFormat="yyyy-MM-dd HH:mm"
            timeCaption="Time"
            className="login-form-control"
          />
          </div>
          <button type="submit" className="login-button">Create</button>
        </form>
      </Container>
    </div>
  );
}

export default CreateTokens;
