// LogoutComponent.js
import React, { useEffect, useContext } from "react";
import { useNavigate } from "react-router-dom";
import AuthContext from "../../Context/AuthContext";
function LogoutComponent() {
  const navigate = useNavigate();
  const { setIsLoggedIn } = useContext(AuthContext);

  useEffect(() => {
    localStorage.removeItem("jwt");
    setIsLoggedIn(false);
    navigate("/login");
  }, [setIsLoggedIn, navigate]);

  return null;
}

export default LogoutComponent;
