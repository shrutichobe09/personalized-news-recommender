import axios from "axios";

const springApi = axios.create({
  baseURL: "/api/news", // Use Ingress path instead of localhost
});

export const fetchArticles = (category = "general") => {
  return springApi.get(`/fetch?category=${category}`);
};
