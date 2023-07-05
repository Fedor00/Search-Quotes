
import React from "react";
import { Nav } from "react-bootstrap";
import { LinkContainer } from "react-router-bootstrap";
import NavDropdown from 'react-bootstrap/NavDropdown';
import Dropdown from 'react-bootstrap/Dropdown';
const HandleMenuItems = ({ item }) => {
  return item.submenu ? (
    <NavDropdown id="dropdown-basic-button" title={item.label} className="custom-dropdown">
      {item.submenu.map((submenu, index) => (
        <LinkContainer key={index} to={submenu.path}>
          <Dropdown.Item>
            {submenu.label}
          </Dropdown.Item>
        </LinkContainer>
      ))}
    </NavDropdown>
  ) : (
    <LinkContainer to={item.path}>
      <Nav.Link>{item.label}</Nav.Link>
    </LinkContainer>
  );
};
export default HandleMenuItems;