import axios from "axios";

const apiUrl = "http://localhost:8080/api/user";

export const registerLocalUser = async (user) => {
  try {
    const response = await axios.post(`${apiUrl}/register`, user);
    return response.data;
  } catch (error) {
    throw error;
  }
};

export const loginWithSocialMedia = async (token, provider) => {
  try {
    const response = await axios.post(`${apiUrl}/login/${provider}`, token);
    return response.data;
  } catch (error) {
    throw error;
  }
};
export const loginLocalUser = async (credentials) => {
  try {
    const response = await axios.post(`${apiUrl}/login`, credentials);
    return response.data;
  } catch (error) {
    throw error;
  }
};
