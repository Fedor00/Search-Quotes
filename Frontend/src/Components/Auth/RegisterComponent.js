import React, { useEffect, useState } from "react";
import "../../Assets/Css/LoginForm.css";
import RegisterForm from "./RegisterForm";
import { useNavigate } from "react-router-dom";
import {
  registerLocalUser,
  loginWithSocialMedia,
} from "../../Services/userService";

function RegisterComponent() {
  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const navigate = useNavigate();

  const handleSocialRegister = async (token, provider) => {
    try {
      const response = await loginWithSocialMedia(token, provider);
      localStorage.setItem("jwt", response);
      navigate("/login");
    } catch (error) {
      console.error("Error sending Register data to backend:", error);
    }
  };

  const handleSimpleFormRegister = async (e) => {
    e.preventDefault();
    try {
      let provider = "local";
      const user = { name, email, password, provider };
      const data = await registerLocalUser(user);

      console.log("User registered successfully", data);
      navigate("/login");
    } catch (error) {
      console.error("Error during user registration:", error);
    }
  };

  const handleGoogleRegister = (response) => {
    if (response) {
      handleSocialRegister(response.credential, "google");
    }
  };

  const handleFacebookRegister = (response) => {
    if (response) {
      console.log(response.authResponse.accessToken);
      handleSocialRegister(response.authResponse.accessToken, "facebook");
    }
  };

  return (
    <RegisterForm
      onSubmit={handleSimpleFormRegister}
      email={email}
      setEmail={setEmail}
      name={name}
      setName={setName}
      password={password}
      setPassword={setPassword}
      handleGoogleRegister={handleGoogleRegister}
      handleFacebookRegister={handleFacebookRegister}
    />
  );
}

export default RegisterComponent;
