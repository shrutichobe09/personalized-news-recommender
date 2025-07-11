import React, { useState } from "react";
import { fetchArticles } from "../api/springApi";
import { fetchRecommendations } from "../api/fastApi";
import Loader from "../components/Loader";
import NewsCard from "../components/NewsCard";
import "animate.css";

const CATEGORIES = [
  { key: "general", label: "General", icon: "🌍" },
  { key: "health", label: "Health", icon: "💊" },
  { key: "sports", label: "Sports", icon: "🏅" },
  { key: "technology", label: "Technology", icon: "💻" },
  { key: "business", label: "Business", icon: "💼" },
  { key: "entertainment", label: "Entertainment", icon: "🎬" },
  { key: "science", label: "Science", icon: "🔬" },
];

const Recommendations = () => {
  const [interests, setInterests] = useState("");
  const [category, setCategory] = useState("general");
  const [recommended, setRecommended] = useState([]);
  const [categoryArticles, setCategoryArticles] = useState([]);
  const [loading, setLoading] = useState(false);

  const handleRecommend = async () => {
    setLoading(true);
    setRecommended([]);

    const interestsArray = interests
      .split(",")
      .map((i) => i.trim().toLowerCase())
      .filter((i) => i !== "");

    try {
      const springRes = await fetchArticles(category);

      if (!springRes.data || springRes.data.length === 0) {
        console.warn("No articles returned from Spring Boot.");
        setLoading(false);
        return;
      }

      const recentArticles = springRes.data.slice(0, 10);
      setCategoryArticles(recentArticles);

      const payload = {
        interests: interestsArray,
        recentArticles: recentArticles,
      };

      // ✅ Handle 404 or other errors gracefully
      const fastRes = await fetchRecommendations(payload).catch((err) => {
        console.warn("Handled error from FastAPI:", err.message);
        return { data: { recommendations: [] } };
      });

      if (fastRes.data.recommendations && fastRes.data.recommendations.length > 0) {
        setRecommended(fastRes.data.recommendations);
      } else {
        console.warn("No recommendations received.");
      }
    } catch (error) {
      console.error("Unexpected error during recommendation:", error);
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="container mt-5 animate__animated animate__fadeIn">
      <div className="text-center mb-4">
        <h2 className="fw-bold">🧠Personalized News Recommender</h2>
        <p className="text-muted">Stay updated with articles tailored to your interests</p>
      
      </div>

      <div className="row g-4 align-items-end">
        <div className="col-md-6">
          <label className="form-label fw-semibold">Select Category:</label>
          <select
            className="form-select shadow-sm"
            value={category}
            onChange={(e) => setCategory(e.target.value)}
          >
            {CATEGORIES.map((cat) => (
              <option key={cat.key} value={cat.key}>
                {cat.icon} {cat.label}
              </option>
            ))}
          </select>
        </div>

        <div className="col-md-6">
          <label className="form-label fw-semibold">Enter Interests (comma separated):</label>
          <input
            type="text"
            className="form-control shadow-sm"
            placeholder="e.g. cricket, economy"
            value={interests}
            onChange={(e) => setInterests(e.target.value)}
          />
        </div>
      </div>

      <div className="text-center mt-4">
        <button className="btn btn-lg btn-primary px-5 py-2" onClick={handleRecommend}>
          Recommend
        </button>
      </div>

      {loading && <Loader />}

      {!loading && categoryArticles.length > 0 && (
        <div className="mt-5">
          <h4 className="mb-3">📚 Top {category.charAt(0).toUpperCase() + category.slice(1)} News</h4>
          <div className="row row-cols-1 row-cols-md-2 g-4">
            {categoryArticles.map((a, i) => (
              <div key={i} className="col animate__animated animate__fadeInUp">
                <NewsCard article={a} />
              </div>
            ))}
          </div>
        </div>
      )}

      {!loading && recommended.length > 0 && (
        <div className="mt-5">
          <h4 className="mb-3">🎯 Recommended News</h4>
          <div className="row row-cols-1 row-cols-md-2 g-4">
            {recommended.map((a, i) => (
              <div key={i} className="col animate__animated animate__fadeInUp animate__delay-1s">
                <NewsCard article={a} />
              </div>
            ))}
          </div>
        </div>
      )}
    </div>
  );
};

export default Recommendations;
