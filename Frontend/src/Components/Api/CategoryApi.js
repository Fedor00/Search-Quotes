import axios from "axios";

const API_BASE_URL = "http://localhost:8080/api/category/all-filtered";

export const fetchCategoriesByKeyword = async (keyword) => {
  try {
    const response = await axios.get(`${API_BASE_URL}?keyword=${keyword}`);
    console.log(response);
    return response.data;
  } catch (error) {
    console.error("Error:", error);
  }
};
