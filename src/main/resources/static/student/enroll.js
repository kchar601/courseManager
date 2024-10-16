const uid = localStorage.getItem("uid");

window.addEventListener("DOMContentLoaded", async function () {
  const role = localStorage.getItem("role");

  if (role !== "student") {
    window.location.href = "/";
    return;
  }

  try {
    const userResponse = await fetch(`/api/v1/user/${uid}`, {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
      },
    });

    if (!userResponse.ok) {
      throw new Error(`User fetch failed! Status: ${userResponse.status}`);
    }

    const user = await userResponse.json();

    const courseResponse = await fetch(`/api/v1/course`, {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
      },
    });

    if (!courseResponse.ok) {
      throw new Error(`HTTP error! Status: ${courseResponse.status}`);
    }

    const courses = await courseResponse.json();

    const courseList = document.querySelector("#course-list");

    courses.forEach((course) => {
      const enrollBtn = document.createElement("button");
      enrollBtn.textContent = "Enroll";
      enrollBtn.classList.add("btnHover", "enrollBtn");

      enrollBtn.addEventListener("click", async () => {
        const enrollResponse = await fetch(`/api/v1/enrollment`, {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify({
            userId: user.userId,
            courseId: course.courseId,
          }),
        });

        if (!enrollResponse.ok) {
          throw new Error(
            `Enrollment failed! :( Status: ${enrollResponse.status}`
          );
        }

        alert("Enrolled successfully!");
        window.location.reload();
      });

      const li = document.createElement("li");
      li.dataset.courseId = course.courseId;
      li.textContent = `${course.title} - Room: ${course.room}`;
      li.appendChild(enrollBtn);

      courseList.appendChild(li);
    });

    // Call checkForEnrollments after rendering all courses
    checkForEnrollments();
    
  } catch (error) {
    console.error("Error:", error);
  }
});

async function checkForEnrollments() {
  console.log("checking for enrollments");
  const response = await fetch(`/api/v1/enrollment/user/${uid}`, {
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

  data.forEach((enrollment) => {
    const dupeSearch = document.querySelector(
      `li[data-course-id="${enrollment.course.courseId}"]`
    );
    if (dupeSearch) {
      const btn = dupeSearch.querySelector("button.enrollBtn");
      btn.disabled = true;
      btn.textContent = "Enrolled";
      btn.classList.remove("btnHover");

      // Add drop button only once
      if (!dupeSearch.querySelector("button.dropBtn")) {
        addDropButton(dupeSearch);
      }
    }
  });
}

function addDropButton(dupeSearch) {
  const dropBtn = document.createElement("button");
  dropBtn.textContent = "Drop";
  dropBtn.classList.add("ctaBtn");
  const courseId = dupeSearch.dataset.courseId;

  dropBtn.addEventListener("click", async () => {
    try {
      const response = await fetch(`/api/v1/enrollment?userId=${uid}&courseId=${courseId}`, {
        method: "DELETE",
      });
  
      if (!response.ok) {
        throw new Error(`Deletion failed! :( Status: ${response.status}`);
      }
  
      alert("Unenrolled successfully!");
      window.location.reload();
    } catch (error) {
      console.error("Error:", error);
    }
  });

  console.log("adding drop button");

  dupeSearch.append(dropBtn);
}
