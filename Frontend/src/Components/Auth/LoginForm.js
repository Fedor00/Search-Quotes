import React, { useState } from "react";

import "../../Assets/Css/LoginForm.css";
import { GoogleLogin, GoogleOAuthProvider } from "@react-oauth/google";
import { FacebookProvider, LoginButton } from "react-facebook";
import { Alert } from "react-bootstrap";
const LoginForm = ({
  onSubmit,
  email,
  setEmail,
  password,
  setPassword,
  handleGoogleLogin,
  handleFacebookLogin,
  failedLogin,
}) => {
  const [errorMessage, setErrorMessage] = useState("");

  const validateForm = () => {
    if (!email || !password) {
      setErrorMessage("All fields are required.");
      return false;
    }

    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailRegex.test(email)) {
      setErrorMessage("Email is not valid.");
      return false;
    }

    if (password.length < 6) {
      setErrorMessage("Password must be at least 6 characters.");
      return false;
    }
    setErrorMessage("");
    return true;
  };
  const handleSubmit = (e) => {
    e.preventDefault();
    if (validateForm()) {
      onSubmit(e);
    }
  };
  return (
    <div className="cover">
      <form className="login-form" onSubmit={handleSubmit}>
        <h1 className="login-form_title">Login</h1>
        {errorMessage && (
          <Alert variant="danger" className="mb-3">
            {errorMessage}
          </Alert>
        )}
        {failedLogin && (
          <Alert variant="danger" className="mb-3">
            {failedLogin}
          </Alert>
        )}
        <div className="login-form_input-group">
          <input
            type="email"
            placeholder="Email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            className="login-form_input"
          />
        </div>

        <div className="login-form_input-group">
          <input
            type="password"
            placeholder="Password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            className="login-form_input"
          />
        </div>

        <div className="login-form_button-container">
          <button className="login-form_button" type="submit">
            Login
          </button>
        </div>
        <p className="login-form_alt-text">Or login With</p>

        <div className="login-form_alt-options">
          <div>
            <GoogleOAuthProvider clientId="838631330699-jidlog0ed7oteglrjf2pk20m5nvqj6c8.apps.googleusercontent.com">
              <GoogleLogin
                onSuccess={handleGoogleLogin}
                onError={handleGoogleLogin}
                theme="filled_blue"
              />
            </GoogleOAuthProvider>
          </div>
          <div>
            <FacebookProvider appId="231619739525229">
              <LoginButton
                // @ts-ignore
                className="fb-login-button"
                scope="email"
                onSuccess={handleFacebookLogin}
                onError={handleFacebookLogin}
              >
                <img
                  src="https://upload.wikimedia.org/wikipedia/commons/thumb/5/51/Facebook_f_logo_%282019%29.svg/320px-Facebook_f_logo_%282019%29.svg.png"
                  alt="Facebook
                 logo"
                />
                <span>Register with Facebook</span>
              </LoginButton>
            </FacebookProvider>
          </div>
        </div>
      </form>
    </div>
  );
};

export default LoginForm;
