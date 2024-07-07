import React, { useEffect, useState } from 'react';
import { Button, Container, Table } from 'reactstrap';
import AppNavbar from './AppNavbar';

const UserTable = () => {

  const [users, setUsers] = useState([]);

  const changeStatus = async (username) => {
    const url = 'http://localhost:3002/admin/users';
    const data = {
      username: username
    };

    try {
      const response = await fetch(url, {
        method: 'PUT',
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
    fetch('http://localhost:3002/admin/users')
      .then(res => res.json())
      .then(result => {
        console.log(result)
        setUsers(result);
      })
  }, []);

  const UserTable = (users.users)?.map(user => {
    const date = new Date(user.registration_date);
    return <tr key={user.username}>
      <td>{user.username}</td>
      <td>{`${date.toLocaleDateString()}, ${date.toLocaleTimeString()}`}</td>
      <td>
        <Button className='table-button' onClick={() => changeStatus(user.username)}>{user.is_active ? "Active" : "Inactive"}</Button>
      </td>
    </tr>
  });

  return (
    <div>
        <AppNavbar/>
      <Container className="table-container">
        <h3>User List</h3>
        <Table className="table">
          <thead>
          <tr>
            <th width="20%">Username</th>
            <th width="20%">Registration Date</th>
            <th width="10%">Status</th>
          </tr>
          </thead>
          <tbody>
          {UserTable}
          </tbody>
        </Table>
      </Container>
    </div>
  );
};

export default UserTable;