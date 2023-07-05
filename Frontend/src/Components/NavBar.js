import React, { useContext } from "react";
import { Navbar, Nav } from "react-bootstrap";
import "../Assets/Css/NavBar.css";
import AuthContext from "../Context/AuthContext";
import MenuItems from "./MenuItems";
import HandleMenuItems from "./HandleMenuItems";

const CustomNavBar = () => {
  const { isLoggedIn } = useContext(AuthContext);
  const menuItems = MenuItems();

  return (
    <Navbar expand="lg" className="my-custom-navbar">
      <Navbar.Brand href="/">Quotes</Navbar.Brand>
      <Navbar.Toggle aria-controls="basic-navbar-nav" />
      <Navbar.Collapse id="basic-navbar-nav">
        <Nav className="mr-auto">
          {menuItems.map((item, index) => (
            <HandleMenuItems key={index} item={item} />
          ))}
        </Nav>
      </Navbar.Collapse>
    </Navbar>
  );
};

export default CustomNavBar;
