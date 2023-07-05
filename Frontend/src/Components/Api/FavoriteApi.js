import axios from "axios";

const API_BASE_URL = "http://localhost:8080/api/favorite-quotes";

export const fetchFavoriteQuotes = async (page, limit) => {
  try {
    const response = await axios.get(`${API_BASE_URL}/favorite-quotes`, {
      params: {
        pageNo: page,
        pageSize: limit,
      },
      headers: {
        Authorization: `${getAuthToken()}`,
      },
    });
    console.log(response.data);
    return response.data;
  } catch (error) {
    throw error;
  }
};
export const addFavoriteQuote = async (id) => {
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
    console.log(response.data);
    return response.data;
  } catch (error) {
    throw error;
  }
};

export const deleteFavoriteQuote = async (quote) => {
  try {
    const response = await axios.delete(`${API_BASE_URL}/delete/${quote.id}`);
  } catch (error) {
    throw error;
  }
};

const getAuthToken = () => localStorage.getItem("jwt");
