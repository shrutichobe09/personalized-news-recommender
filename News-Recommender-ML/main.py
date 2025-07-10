from fastapi import FastAPI
from model import RecommendationRequest
from recommender import recommend_articles
from fastapi.middleware.cors import CORSMiddleware
import requests

app = FastAPI()

# CORS middleware
app.add_middleware(
    CORSMiddleware,
    allow_origins=["*"],
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)

# Backend service URL
SPRING_API_URL = "http://news-backend:8080/api/news/fetch?category=general"

@app.post("/recommend")
def recommend(request: RecommendationRequest):
    print("Received interests:", request.interests)
    try:
        print("Calling:", SPRING_API_URL)
        response = requests.get(SPRING_API_URL)
        print("Spring API status:", response.status_code)

        if response.status_code != 200:
            raise Exception(f"Spring API error: {response.text}")

        articles = response.json()
        print("Fetched articles:", len(articles))

        user_text = " ".join(request.interests * 5)
        user_text += " " + " ".join([a["title"] + " " + a["description"] for a in articles])
        recommended = recommend_articles(user_text, articles)

        return {"recommendations": recommended}
    except Exception as e:
        print("ERROR in recommendation:", str(e))
        return {"error": str(e)}

# ✅ Add this route
@app.get("/")
def root():
    return {"message": "ML service running"}
