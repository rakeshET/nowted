# Nowted - The Note Taking App

## Objective
Design a note-taking application where users can create, view, edit, and delete notes.

## Specifications
- Allow users to write new notes with a title and content.
- Display a list of notes in a sidebar or main area.
- Provide full-view, edit, and delete functionalities.

## Team Composition
You have to work as a team of two:
- React Frontend Developer
- Java SpringBoot Backend Developer

## Task
Come up with a complete solution including frontend app and backend APIs for the task. Design the API endpoints needed to make the app work and develop the UI.

## Frontend
The frontend of the application is built using React. It includes the following features:

- A form to create new notes. The form includes fields for the note's title and content.
- A sidebar or main area that displays a list of all notes. Each note in the list shows the note's title.
- When a note in the list is clicked, the application shows the full view of the note, including the note's title, content, and created date.
- In the full view of a note, there are options to edit or delete the note.

## Backend
The backend of the application is built using Java Spring Boot. It includes the following features:

Here are the API endpoints for the note-taking application:

- **Create a new folder**: A `POST` endpoint at `/v1/folders`.
- **Get all folders**: A `GET` endpoint at `/v1/folders`.
- **Get a specific folder by ID**: A `GET` endpoint at `/v1/folders/{id}`.

- **Create a new note**: A `POST` endpoint at `/v1/notes`.
- **Get all notes**: A `GET` endpoint at `/v1/notes`.
- **Get a specific note by ID**: A `GET` endpoint at `/v1/notes/{id}`.
- **Update a specific note by ID**: A `PUT` endpoint at `/v1/notes/{id}`.
- **Delete a specific note by ID**: A `DELETE` endpoint at `/v1/notes/{id}`.

- **Get all notes in a specific folder**: A `GET` endpoint at `/v1/folders/{id}/notes`.
- **Create a new note in a specific folder**: A `POST` endpoint at `/v1/folders/{id}/notes`.

- **Get all favorite notes**: A `GET` endpoint at `/v1/notes/favorites`.
- **Add a note to favorites**: A `PUT` endpoint at `/v1/notes/{id}/favorite`.
- **Remove a note from favorites**: A `DELETE` endpoint at `/v1/notes/{id}/favorite`.

- **Get all archived notes**: A `GET` endpoint at `/v1/notes/archived`.
- **Archive a note**: A `PUT` endpoint at `/v1/notes/{id}/archive`.
- **Unarchive a note**: A `DELETE` endpoint at `/v1/notes/{id}/archive`.
- **Create a backup**: A `POST` endpoint at `/v1/backup`.
- **gET a backup**: A `POST` endpoint at `/v1/backup`.
- 


## Integration
Once the frontend and backend are developed, they are integrated together. The frontend makes HTTP requests to the backend's API endpoints to create, retrieve, update, and delete notes. The backend responds to these requests with the appropriate data.

## Testing
After the integration, perform integration testing to ensure that the frontend and backend work together correctly. Test each feature of the application, including creating, viewing, editing, and deleting notes.

## Code Quality
Ensure front end uses Redux/RTK with proper state management and ensure front end code quality. Ensure the API meets coding standards.

This is a high-level overview of how the application works. For more detailed information, please refer to the source code and comments.