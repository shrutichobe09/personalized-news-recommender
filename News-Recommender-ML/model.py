from pydantic import BaseModel
from typing import List

class Article(BaseModel):
    title: str
    description: str
    url: str
    category: str

class RecommendationRequest(BaseModel):
    interests: List[str]
   
