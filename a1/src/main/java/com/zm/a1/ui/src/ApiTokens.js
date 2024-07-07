import React, { useEffect, useState } from 'react';
import { Button, Container, Table } from 'reactstrap';
import AppNavbar from './AppNavbar';
import { Link } from 'react-router-dom';

const ApiTokens = () => {

  const [tokens, setTokens] = useState([]);

  useEffect(() => {
    fetch('http://localhost:3002/user/api-tokens')
      .then(res => res.json())
      .then(result => {
        console.log(result)
        setTokens(result);
      })
  }, []);

  const TokenTable = (tokens.tokens)?.map(token => {
    return <tr key={token.name}>
      <td>{token.name}</td>
      <td>{token.token}</td>
      <td>{token.expire_date}</td>
      <td>
        <Button size="sm" color="primary" tag={Link} to={"/user/api-tokens"}>Deactivate</Button>
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
      </Container>
    </div>
  );
};

export default ApiTokens;