# PromptVerse - Real-Time Party Music and Games App

**PromptVerse** is a mobile app designed to enhance the party experience by integrating music voting, fun party games, and social interaction. The app allows users to vote for songs, participate in party games like Dumb Charades, and chat in real time—all while providing a seamless experience with real-time updates.

## **Purpose**

To demonstrate advanced backend and mobile development skills, integrating real-time features and external APIs.

## **Core Features**

1. **Music Voting System**:

   - Users vote for songs from YouTube.
   - Real-time updates on the most-voted song.

2. **Basic Party Games**:

   - Dumb Charades
   - Beer Bottle Round Table

3. **Party Management**:

   - Create and join parties using a unique code.
   - Host controls for managing the party.

4. **Social Features**:

   - Group chat for attendees.

5. **Real-time Updates**:
   - Use WebSockets for song voting and game updates.

## **Tech Stack**

### **Frontend**

- **Technology**: Kotlin
- **Features**:
  - Material Design for UI.
  - ViewModel and LiveData for state management.

### **Backend**

- **Technology**: Spring Boot
- **Features**:
  - RESTful APIs for core functionality.
  - WebSocket for real-time voting and game updates.

### **Database**

- **Relational Database**: MySQL or PostgreSQL.
- **Caching**: Redis for real-time voting data.

### **Third-party API**

- **YouTube Data API**: Fetch songs and metadata.

## **Core Modules for Backend**

### 1. **User Management**

- User registration and login.
- Simple role-based access (Host and Attendee).

### 2. **Party Management**

- Create and join parties using unique codes.
- Host controls to manage the session.

### 3. **Music Management**

- Song search using YouTube API.
- Song queue management and voting.
- Fetch the most-voted song in real-time.

### 4. **Game Management**

- Trivia questions and Guess the Song logic.
- Host starts and manages games.

### 5. **Real-time Communication**

- WebSocket for live voting and game updates.

## **Outcome**

- A fully functional app showcasing:
  - Real-time application development.
  - Backend-frontend integration.
  - Third-party API usage.
- A strong addition to your portfolio and resume.
