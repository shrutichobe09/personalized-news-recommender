from fastapi import FastAPI
from model import RecommendationRequest
from recommender import recommend_articles
from fastapi.middleware.cors import CORSMiddleware
import requests

app = FastAPI()

app.add_middleware(
    CORSMiddleware,
    allow_origins=["http://localhost:3000"],
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)

SPRING_API_URL = "http://news-backend:8080/api/news/fetch?category=general"  

@app.post("/recommend")
def recommend(request: RecommendationRequest):
    print("Received interests:", request.interests)

    try:
       
        response = requests.get(SPRING_API_URL)
        articles = response.json()
        print("Fetched articles:", len(articles))

        user_text = " ".join(request.interests * 5)  
        user_text += " " + " ".join(
    [a["title"] + " " + a["description"] for a in articles]
)

        recommended = recommend_articles(user_text, articles)
        return {"recommendations": recommended}

    except Exception as e:
        print("ERROR in recommendation:", str(e))
        return {"error": str(e)}

