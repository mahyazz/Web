// import React, { useEffect, useState } from 'react';
// import { Button, Container, Table } from 'reactstrap';
// import AppNavbar from './AppNavbar';

// const Countries = () => {

//   const [countries, setCountries] = useState([]);

//   useEffect(() => {
//     fetch('http://localhost:8082/api/countries')
//       .then(res => res.json())
//       .then(result => {
//         console.log(result)
//         setCountries(result);
//       })
//   }, []);

//   const countriesTable = (countries.countries)?.map(country => {
//     return <tr key={country.name}>
//       <td>{country.name}</td>
//     </tr>
//   });

//   return (
//     <div>
//         <AppNavbar/>
//       <Container className="table-container">
//         <h3>User List</h3>
//         <Table className="table">
//           <thead>
//           <tr>
//             <th width="20%">Name</th>
//           </tr>
//           </thead>
//           <tbody>
//           {countriesTable}
//           </tbody>
//         </Table>
//       </Container>
//     </div>
//   );
// };

// export default Countries;