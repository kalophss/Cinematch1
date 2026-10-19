# CineMatch

CineMatch is a movie web app I built with Spring Boot. You can browse and search movies
using data from TMDB,save a watchlist,rate movies etc., and play a short movie quiz
where the questions are generated with Google's Gemini API. There's also an actor-matching
feature that [what it does, in one sentence].

I started it as a university project to get more comfortable with
Spring Boot, JPA and working with external APIs.

## What it does

- Search movies and see details (cast, rating, overview) from TMDB
- Watchlist,favourites,user accounts 
- Movie quiz with AI-generated questions (Gemini)
- Actor match:Using a picture of you ,HuggingFace api tries to match it with a celebrity actor

## Built with

- Java 17, Spring Boot 4 (Web, Data JPA, Thymeleaf)
- PostgreSQL
- TMDB API, Gemini API
- Maven

## Running it locally

You'll need JDK 17+, Maven and a running PostgreSQL server.

1. Create an empty database:
```sql
   CREATE DATABASE cinematch;
```
   The tables are created automatically on first run.

2. Get API keys from [TMDB](https://www.themoviedb.org/settings/api) and
   [Google AI Studio](https://aistudio.google.com/app/apikey).

3. Set these environment variables (in IntelliJ: Run → Edit Configurations → Environment variables):
DB_PASSWORD=your_postgres_password
TMDB_API_KEY=your_tmdb_key
GEMINI_API_KEY=your_gemini_key

4.Run:mvn spring-boot:run and open http://localhost:8888
   and open http://localhost:8888

The database URL and username are in `src/main/resources/application.properties`
if you need to change them.



## Things I'd like to add
 -login with Google
 -better quiz scoring
 -deploying it online


