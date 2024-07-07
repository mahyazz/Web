import React, { useEffect, useState } from 'react';
import { Button, Container, Table } from 'reactstrap';
import AppNavbar from './AppNavbar';
import { Link } from 'react-router-dom';

const ApiTokens = () => {

  const [tokens, setTokens] = useState([]);

  const deleteToken = async (tokenName) => {
    const url = 'http://localhost:3002/user/api-tokens';
    const data = {
      name: tokenName
    };
    console.log(data);

    try {
      const response = await fetch(url, {
        method: 'DELETE',
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

  useEffect(() => {
    fetch('http://localhost:3002/user/api-tokens')
      .then(res => res.json())
      .then(result => {
        console.log(result)
        setTokens(result);
      })
  }, []);

  const TokenTable = (tokens.tokens)?.map(token => {
    const date = new Date(token.expire_date);
    return <tr key={token.name}>
      <td>{token.name}</td>
      <td>{token.token}</td>
      <td>{`${date.toLocaleDateString()}, ${date.toLocaleTimeString()}`}</td>
      <td>
        <Button className='table-button' onClick={() => deleteToken(token.name)}>Delete</Button>
      </td>
    </tr>
  });

  return (
    <div>
        <AppNavbar/>
      <Container className="table-container">
        <h3>Api Tokens</h3>
        <Table className="table">
          <thead>
          <tr>
            <th width="20%">Name</th>
            <th width="20%">Token</th>
            <th width="20%">Expiration Date</th>
            <th width="10%"></th>
          </tr>
          </thead>
          <tbody>
          {TokenTable}
          </tbody>
        </Table>
        <Button className="login-button" tag={Link} to={"/user/api-tokens/new"}>Create Token</Button>
      </Container>
    </div>
  );
};

export default ApiTokens;