# Course Manager Application
## This is a full-stack web application designed to manage student enrollment in courses, primarily focused on student and teacher interactions. The platform allows teachers to create and manage courses, while students can view, enroll, and drop courses. Both students and teachers have distinct dashboards and capabilities.

### Key Features:
- User Authentication: Email-based login system. Once logged in, users are assigned roles (student or teacher) to access their respective functionalities.
- Course Management for Teachers: Teachers can create, update, and delete courses, as well as view students enrolled in their courses.
- Student Enrollment: Students can browse available courses, enroll in them, and drop courses if necessary.
- Role-Based Access: While the system identifies user roles (student or teacher), role access is managed primarily through frontend navigation and basic session checks.
- Frontend: Built with vanilla JavaScript and fetch API for communication with the backend.
- Backend: RESTful API using Spring Boot and PostgreSQL for data persistence.

### Endpoints Overview:
- User Management: Login, retrieve, and update user profiles.
- Course Management: Create, update, and delete courses, view courses by teacher or student.
- Enrollment Management: Enroll in or drop courses, check enrollments by student.

### Getting Started:
- Clone the repository: git clone https://github.com/kchar601/courseManager
- Configure your database in the application.properties file.
- Run the Spring Boot application.
- Visit the homepage and sign in as a teacher or student to explore features.

### Signin Details:
#### Password Authentication is not currently enabled
#### Student
- john@gmail.com
#### Instructor / Teacher
- charltonkeith8@gmail.com
##### Feel free to explore and contribute!
