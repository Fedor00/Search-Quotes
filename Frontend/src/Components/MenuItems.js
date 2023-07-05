import React, { useContext } from "react";
import AuthContext from "../Context/AuthContext";

const MenuItems = () => {
  const { isLoggedIn } = useContext(AuthContext);

  const items = [
    {
      label: "Home",
      path: "/",
    },
    {
      label: "Services",
      path: "/services",
    },
    {
      label: "About",
      path: "/about",
    },
    {
      label: "Options",
      path: "",
      submenu: [
        !isLoggedIn && {
          label: "Login",
          path: "/login",
        },
        isLoggedIn && {
          label: "Logout",
          path: "/logout",
        },
        isLoggedIn && {
          label: "Profile",
          path: "/profile",
        },
        !isLoggedIn && {
          label: "Register",
          path: "/register",
        },
      ].filter(Boolean),
    },
  ];

  return items;
};

export default MenuItems;
