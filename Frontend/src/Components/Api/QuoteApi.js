import axios from "axios";

const API_BASE_URL = "http://localhost:8080/api/quote";

export const fetchDailyQuote = async () => {
  try {
    const response = await axios.get(`${API_BASE_URL}/daily`);
    console.log(response.data);
    return response.data;
  } catch (error) {
    throw error;
  }
};

// Function to get token from localStorage
const getAuthToken = () => localStorage.getItem("jwt");

export const fetchPersonalQuotes = async (page, limit) => {
  try {
    const response = await axios.get(`${API_BASE_URL}/personal-quotes`, {
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

export const fetchQuotesByKeyword = async (keywords, page, limit) => {
  try {
    const response = await axios.get(`${API_BASE_URL}/pages-by-keywords`, {
      params: {
        keywords: keywords,
        pageNo: page,
        pageSize: limit,
      },
    });
    console.log(response.data);
    return response.data;
  } catch (error) {
    throw error;
  }
};
export const fetchQuotesSortedByLikes = async (page, limit) => {
  try {
    const response = await axios.get(
      `${API_BASE_URL}/pages-by-author-ascending`,
      {
        params: {
          pageNo: page,
          pageSize: limit,
        },
      }
    );
    console.log(response.data);
    return response.data;
  } catch (error) {
    throw error;
  }
};
export const fetchQuotesByAuthor = async (author, page, limit) => {
  try {
    const response = await axios.get(`${API_BASE_URL}/pages-by-author`, {
      params: {
        keywords: author,
        pageNo: page,
        pageSize: limit,
      },
    });
    return response.data;
  } catch (error) {
    throw error;
  }
};

export const updatePersonalQuote = async (quote) => {
  try {
    const response = await axios.post(`${API_BASE_URL}/update`, quote);
  } catch (error) {
    throw error;
  }
};

export const deletePersonalQuote = async (quote) => {
  try {
    const response = await axios.delete(`${API_BASE_URL}/delete/${quote.id}`);
  } catch (error) {
    throw error;
  }
};

export const addPersonalQuote = async (quote) => {
  try {
    const response = await axios.post(
      `${API_BASE_URL}/add-personal-quote`,
      quote,
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
