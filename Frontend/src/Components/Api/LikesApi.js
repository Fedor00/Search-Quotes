import axios from "axios";

const API_BASE_URL = "http://localhost:8080/api/likes";

export const addLikeQuote = async (id) => {
  try {
    const response = await axios.post(
      `${API_BASE_URL}/add/${id}`,
      {},
      {
        headers: {
          Authorization: `${getAuthToken()}`,
        },
      }
    );
    return response.data;
  } catch (error) {
    throw error;
  }
};
const getAuthToken = () => localStorage.getItem("jwt");
