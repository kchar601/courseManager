window.addEventListener("DOMContentLoaded", async function () {
  const uid = localStorage.getItem("uid");
  const role = localStorage.getItem("role");

  if (role !== "student") {
    window.location.href = "/";
    return;
  }

  const response = await fetch(`/api/v1/course/user/${uid}`, {
    method: "GET",
    headers: {
      "Content-Type": "application/json",
    },
  });

  if (!response.ok) {
    throw new Error(`HTTP error! Status: ${response.status}`);
  }

  const data = await response.json();

  const courseList = document.querySelector("#course-list");

  data.forEach((course) => {
    const anchor = document.createElement("a");
    anchor.href = `courseDetail?courseId=${course.courseId}`;

    const li = document.createElement("li");
    li.textContent = course.title + " - Room:" + course.room;
    anchor.appendChild(li);
    courseList.appendChild(anchor);
  });
});
