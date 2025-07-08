
import axios from "axios";

const fastApi = axios.create({
  baseURL: "http://localhost:8000", 
});

export const fetchRecommendations = (payload) => {
  return fastApi.post("/recommend", payload);
};
