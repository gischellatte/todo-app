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

**MVP (Minimum Viable Product)**
- Add new tasks
- Edit existing tasks
- Remove tasks
- Check task details and deadlines

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
