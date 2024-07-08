import { Navbar, NavbarBrand } from 'reactstrap';
import { Link } from 'react-router-dom';

const AppNavbar = () => {

  return (
    <Navbar className="navbar">
      <NavbarBrand tag={Link} to="/">Home</NavbarBrand>
      <NavbarBrand tag={Link} to="/countries">Country List</NavbarBrand>
    </Navbar>
  );
};

export default AppNavbar;