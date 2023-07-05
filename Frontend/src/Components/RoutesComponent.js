import React, { useContext } from "react";
import { Route, Navigate, Routes } from "react-router-dom";
import LoginComponent from "./Auth/LoginComponent";
import RegisterComponent from "./Auth/RegisterComponent";
import LogoutComponent from "./Auth/Logout";
import SearchQuotesComponent from "./SearchQuotes/SearchQuotesComponent";
import AuthContext from "../Context/AuthContext";
import UserProfile from "./Profile/UserProfile";
function RoutesComponent() {
  const { isLoggedIn } = useContext(AuthContext);

  return (
    <Routes>
      <Route
        path="/"
        element={
          isLoggedIn ? <Navigate to="/home" /> : <Navigate to="/login" />
        }
      />
      <Route path="/register" element={<RegisterComponent />} />
      <Route
        path="/login"
        element={isLoggedIn ? <Navigate to="/" /> : <LoginComponent />}
      />
      <Route path="/home" element={isLoggedIn && <SearchQuotesComponent />} />
      <Route path="/logout" element={<LogoutComponent />} />
      <Route path="/profile" element={<UserProfile />} />
    </Routes>
  );
}

export default RoutesComponent;
