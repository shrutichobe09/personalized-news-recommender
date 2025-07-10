import axios from "axios";

const fastApi = axios.create({
  baseURL: "/ml", // ✅ Correct prefix to reach FastAPI service
});

export const fetchRecommendations = (payload) => {
  return fastApi.post("/recommend", payload);  // Full path: /ml/recommend
};
