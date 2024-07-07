import React, { useEffect, useState } from 'react';
import { Button, Container, Table } from 'reactstrap';
import AppNavbar from './AppNavbar';
import { Link } from 'react-router-dom';

const UserTable = () => {

  const [users, setUsers] = useState([]);
//   const [loading, setLoading] = useState(false);

  useEffect(() => {
    fetch('http://localhost:3002/admin/users')
      .then(res => res.json())
      .then(result => {
        console.log(result)
        setUsers(result);
      })
  }, []);

  const UserTable = (users.users)?.map(user => {
    return <tr key={user.username}>
      <td style={{whiteSpace: 'nowrap'}}>{user.username}</td>
      <td>{user.registration_date}</td>
      <td>
        <Button size="sm" color="primary" tag={Link} to={"/users/admin"}>{user.is_active ? "Active" : "Inactive"}</Button>
      </td>
    </tr>
  });

  return (
    <div>
        {/* <AppNavbar/> */}
      <Container className="container">
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