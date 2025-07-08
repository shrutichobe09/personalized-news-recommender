import React from "react";

const NewsCard = ({ article }) => {
  return (
    <div className="card mb-3 shadow-sm">
      <div className="card-body">
        <h5 className="card-title">{article.title}</h5>
        <p className="card-text">{article.description}</p>
        {article.url && (
          <a
            href={article.url}
            rel="noopener noreferrer"
            className="btn btn-sm btn-outline-primary"
          >
            🔗 Read More
          </a>
        )}
        <span className="badge bg-secondary ms-2">{article.category}</span>
      </div>
    </div>
  );
};

export default NewsCard;
