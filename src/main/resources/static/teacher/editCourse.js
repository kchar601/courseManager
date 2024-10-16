window.addEventListener("DOMContentLoaded", function () {
  const uid = localStorage.getItem("uid");
  const role = localStorage.getItem("role");

  if (role !== "teacher") {
    window.location.href = "/";
    return;
  }

  const urlParams = new URLSearchParams(window.location.search);
  const courseId = urlParams.get("courseId");

  if (!courseId) {
    alert("Course ID not found in URL");
    return;
  }

  fetch(`/api/v1/course/${courseId}`)
    .then((response) => response.json())
    .then((data) => {
      if (data.error) {
        throw new Error(data.error);
      }

      const title = document.querySelector("#course-title");
      const subject = document.querySelector("#course-subject");
      const room = document.querySelector("#course-room");

      title.value = data.title || "";
      subject.value = data.subject || "";
      room.value = data.room || "";
    })
    .catch((error) => {
      console.error("Error:", error);
      alert("An error occurred. Please try again later.");
    });

  const updateBtn = document.querySelector("#updateBtn");
  updateBtn.addEventListener("click", async function () {
    const title = document.querySelector("#course-title").value;
    const subject = document.querySelector("#course-subject").value;
    const room = parseInt(document.querySelector("#course-room").value);

    if (!title || !subject || isNaN(room)) {
      alert("Please fill in all fields");
      return;
    }

    const courseData = {
      title: title,
      subject: subject,
      room: room,
    };

    try {
      const response = await fetch(`/api/v1/course/${courseId}`, {
        method: "PUT",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(courseData),
      });

      if (response.status === 200) {
        alert("Course updated successfully");
        window.location.href = "/teacher/dashboard";
      } else {
        throw new Error(`Failed to update course. Status: ${response.status}`);
      }
    } catch (error) {
      console.error("Error:", error);
      alert("An error occurred. Please try again later.");
    }
  });

  const deleteBtn = document.querySelector("#deleteBtn");
  deleteBtn.addEventListener("click", async function () {
    if (!confirm("Are you sure you want to delete this course?")) {
      return;
    }

    try {
      const response = await fetch(`/api/v1/course/${courseId}`, {
        method: "DELETE",
      });

      if (response.status === 200) {
        alert("Course deleted successfully");
        window.location.href = "/teacher/dashboard";
      } else {
        throw new Error(`Failed to delete course. Status: ${response.status}`);
      }
    } catch (error) {
      console.error("Error:", error);
      alert("An error occurred. Please try again later.");
    }
  });
});
