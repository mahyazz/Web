import React, { useState } from 'react';
import { Container, Table, Button } from 'reactstrap';
import AppNavbar from './AppNavbar';

const Countries = () => {

  const [countries, setCountries] = useState([]);
  const [isModalOpen, setIsModalOpen] = useState(true);
  const [apiKey, setApiKey] = useState('');

  const toggleModal = () => setIsModalOpen(!isModalOpen);

  const handleApiKeyChange = (event) => setApiKey(event.target.value);

  const handleApiKeySubmit = () => {
    if (apiKey.trim() === '') {
      alert('Please enter a valid API key.');
      return;
    }
    toggleModal();
    fetchCountries(apiKey);
  };

  const showData = async (name, key) => {
    try {
      const response = await fetch(`http://localhost:8082/api/countries/${name}`, {
        method: 'GET',
        headers: {
          'Content-Type': 'application/json',
          'X-Api-Key': key
        }
      });

      if (!response.ok) {
        throw new Error('Network response was not ok');
      }

      const responseData = await response.json();
      alert(`${name} Data: ${JSON.stringify(responseData, null, 2)}`);
    } catch (error) {
      console.error('Error:', error);
      alert('Error occurred while sending request');
    }
  };

  const showWeather = async (name, key) => {
    try {
      const response = await fetch(`http://localhost:8082/api/countries/${name}/weather`, {
        method: 'GET',
        headers: {
          'Content-Type': 'application/json',
          'X-Api-Key': key
        }
      });

      if (!response.ok) {
        throw new Error('Network response was not ok');
      }

      const responseData = await response.json();
      alert(`${name} Weather: ${JSON.stringify(responseData, null, 2)}`);
    } catch (error) {
      console.error('Error:', error);
      alert('Error occurred while sending request');
    }
  };

  const fetchCountries = (key) => {
    fetch('http://localhost:8082/api/countries', {
      method: 'GET',
      headers: {
        'Content-Type': 'application/json',
        'X-Api-Key': key
      }
    })
      .then(res => {
        if (res.status === 403) {
          alert('403 Forbidden: Invalid API Key');
          throw new Error('Forbidden: Invalid API Key');
        }
        return res.json();
      })
      .then(result => {
        console.log(result);
        setCountries(result);
      })
      .catch(error => console.error('Error fetching countries:', error));
  };

  const countriesTable = (countries.countries)?.map(country => {
    return <tr key={country.name}>
      <td>{country.name}</td>
      <td>
        <Button className='table-button' onClick={() => showData(country.name, apiKey)}>Data</Button>
      </td>
      <td>
        <Button className='table-button' onClick={() => showWeather(country.name, apiKey)}>Weather</Button>
      </td>
    </tr>
  });

  return (
    <div>
        <AppNavbar/>
      <Container className="table-container">
        <h3>Countries</h3>
        <Table className="table">
          <thead>
          <tr>
            <th width="20%"></th>
            <th width="20%"></th>
            <th width="20%"></th>
          </tr>
          </thead>
          <tbody>
          {countriesTable}
          </tbody>
        </Table>
      </Container>

      {isModalOpen && (
        <div className="modal-overlay">
          <div className="modal-content">
            <h3>Enter API Key</h3>
            <input
              type="text"
              value={apiKey}
              onChange={handleApiKeyChange}
              className="modal-input"
              placeholder="Enter your API key"
            />
            <button className="modal-button" onClick={handleApiKeySubmit}>Submit</button>
            <button className="modal-button" onClick={toggleModal}>Cancel</button>
          </div>
        </div>
      )}
    </div>
  );
};

export default Countries;