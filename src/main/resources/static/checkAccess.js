window.addEventListener("DOMContentLoaded", function () {
  const uid = localStorage.getItem("uid");
  const role = localStorage.getItem("role");

  if (!uid || !role) {
    window.location.href = "/";
  } else {
    console.log("User is authenticated with role:", role);
  }

  const conditionalNav = document.querySelector("#conditionalNav");
  if (conditionalNav) {
    if (role === "student") {
      conditionalNav.innerHTML = `
		  <ul>
		    <li><h1>Course Manager</h1></li>
		    <li><a href="/student/dashboard">Dashboard</a></li>
		    <li><a href="/student/enroll">Enroll</a></li>
		    <li><a href="../profile">Profile</a></li>
		    <li><a onclick="signOut()">Log Out</a></li>
		  </ul>
		`;
    } else if (role === "teacher") {
      conditionalNav.innerHTML = `
		  <ul>
		    <li><h1>Course Manager</h1></li>
		    <li><a href="/teacher/dashboard">Dashboard</a></li>
		    <li><a href="/teacher/newCourse">New Course</a></li>
		    <li><a href="../profile">Profile</a></li>
		    <li><a onclick="signOut()">Log Out</a></li>
		  </ul>
		`;
    }
  }
});

function signOut() {
  localStorage.removeItem("uid");
  localStorage.removeItem("role");
  window.location.href = "/";
}
