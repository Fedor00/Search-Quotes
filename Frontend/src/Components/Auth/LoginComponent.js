import React, { useContext, useState } from "react";
import AuthForm from "./LoginForm";
import { useNavigate } from "react-router-dom";
import {
  loginLocalUser,
  loginWithSocialMedia,
} from "../../Services/userService";
import AuthContext from "../../Context/AuthContext";
import { Alert } from "react-bootstrap";

function LoginComponent() {
  const { setIsLoggedIn } = useContext(AuthContext);
  const [failedLogin, setFailedLogin] = useState();
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const navigate = useNavigate();
  const showAlert = failedLogin ? (
    <Alert
      variant="danger"
      onClose={() => setFailedLogin(undefined)}
      dismissible
    >
      {failedLogin}
    </Alert>
  ) : null;
  const handleLogin = async (token, provider) => {
    try {
      const response = await loginWithSocialMedia(token, provider);
      localStorage.setItem("jwt", response);
      setIsLoggedIn(true);
      navigate("/home");
      console.log(response);
    } catch (error) {
      console.error("Error:", error);
      setFailedLogin(error.response.data);
    }
  };
  const handleSimpleFormLogin = async (e) => {
    e.preventDefault();
    console.log(email + " " + password);
    try {
      const credentials = { email, password };
      const response = await loginLocalUser(credentials);

      console.log("User logged in successfully", response);
      localStorage.setItem("jwt", response);
      setIsLoggedIn(true);
      navigate("/home");
    } catch (error) {
      console.error("Error:", error);
      setFailedLogin(error.response.data);
    }
  };

  const handleGoogleLogin = (response) => {
    if (response) {
      handleLogin(response.credential, "google");
    }
  };

  const handleFacebookLogin = (response) => {
    if (response) {
      handleLogin(response.authResponse.accessToken, "facebook");
    }
  };

  return (
    <AuthForm
      onSubmit={handleSimpleFormLogin}
      email={email}
      setEmail={setEmail}
      password={password}
      setPassword={setPassword}
      handleGoogleLogin={handleGoogleLogin}
      handleFacebookLogin={handleFacebookLogin}
      failedLogin={failedLogin}
    />
  );
}

export default LoginComponent;
