window.addEventListener("DOMContentLoaded", async function () {
  const uid = localStorage.getItem("uid");
  const role = localStorage.getItem("role");

  if (role !== "student") {
    window.location.href = "/";
    return;
  }

  const urlParams = new URLSearchParams(window.location.search);
  const courseId = urlParams.get("courseId");

  const response = await fetch(`/api/v1/course/${courseId}`, {
    method: "GET",
    headers: {
      "Content-Type": "application/json",
    },
  });

  if (!response.ok) {
    throw new Error(`HTTP error! Status: ${response.status}`);
  }

  const data = await response.json();

  console.log(data);

  document.querySelector("title").textContent = data.title;

  const courseList = document.querySelector("#course-detail");
  document.querySelector("#course-title").textContent = data.title;
  document.querySelector("#course-subject").textContent = data.subject;
  document.querySelector("#course-room").textContent = data.room;
  document.querySelector("#course-instructor").textContent =
    data.teacher.fname + " " + data.teacher.lname;
  console.log(data.teacher);
});
