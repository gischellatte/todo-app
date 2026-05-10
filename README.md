# Todo-app

**Demo & Snippets**

<img width="608" height="633" alt="image" src="https://github.com/user-attachments/assets/d370567b-c368-4df6-b340-4f819258923d" />


🔗 GitHub Repository:
https://github.com/gischellatte/todo-app

**Requirements / Purpose**

This is a full-stack Todo App project. The app is designed to:
- Allow users to view, create, update and delete tasks via front-end and backend.
  Users can soft-delete (archive) tasks via frontend and hard-delete via backend (e.g. via MySQL or Postman)
- Integrate backend and frontend systems.
- Allow users to manage their tasks through different screen sizes.

MVP (Minimum Viable Product):
- Add new tasks
- Edit existing tasks
- Remove tasks
- Check task details and deadlines

**Build Steps** 

Frontend
- Navigate to the todo-frontend folder using your terminal.
  - cd todo-frontend
- (After installing dependencies) Execute npm run dev in the employee-frontend folder.
  - npm run dev
- NB: The frontend can only run on http://localhost:5173

Backend 
- Navigate to the backend folder using your terminal.
  - cd backend
- (After installing the dependencies) Run mvn spring-boot:run in the demo folder.
  - mvn spring-boot:run
- NB: The server can only run in 1 terminal.

**Design Goals / Approach**
- I utilised Google Gemini to create the hi-fi prototype of the app. So I have a better visual direction and user experience before proceeding with the coding phase.
- The screen is responsive and has been tested across various sizes and devices. I used SCSS to configure the colours and dimensions.
- The layout has been enhanced with the dark mode settings in Google Chrome, ensuring convenience and accessibility for users who prefer dark themes. 

**Features**

Frontend:
- Add a new task, incuding:
  - task category
  - task deadline
- Update existing tasks
- Archive a task
- List down all current and archived tasks
- datepicker feature for easier date selection.

 Backend:
 - Add a new task, incuding:
    - task category
    - task deadline
- Update existing tasks
- Archive a task
- Remove a task permanently
- Manage task categories:
- Update categories
- Delete categories permanently

**Known issues**
- The app had 2 separate buttons to update category and task. This could have been merged into 1 button (updated).
- Users cannot undo archived tasks.

**Future Goals**

- Introduce more TypeScript coverage.
-  Allow users to unarchive tasks via frontend UI.
-  Allow users to update deadline dates.
-  Add urgency reminder (e.g. if deadline is overdue, change the date font colour to red).
-  Add more test functions.

**What did you struggle with?**
- When selecting the task deadline, users had to manually type the day, month and year. After updating the datepicker, users simply click on the deadline.
- I had to manually update on the deadlines through MySQL because the older dates were typed in a different format.
- After I updated the backend part of the code, sometimes the amendments did not propagate to the frontend.

**Further details, related projects, reimplementations**
- No, but I plan to update the project based on the feedback provided by the coaches.
