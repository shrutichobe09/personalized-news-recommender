import axios from "axios";

const springApi = axios.create({
  baseURL: "http://localhost:8080/api/news",
});

export const fetchArticles = (category = "general") => {
  return springApi.get(`/fetch?category=${category}`);
};
