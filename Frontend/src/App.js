// App.js
import "./App.css";
import React, { useContext } from "react";
import "./App.css";
import NavBar from "./Components/NavBar";
import RoutesComponent from "./Components/RoutesComponent";
import AuthContext from "./Context/AuthContext";
import "react-toastify/dist/ReactToastify.css";
import { ToastContainer } from "react-toastify";

function App() {
  const { isLoggedIn } = useContext(AuthContext);
  return (
    <div className="background-image">
      <NavBar />
      <RoutesComponent></RoutesComponent>
      <ToastContainer />
    </div>
  );
}

export default App;
