window.addEventListener("DOMContentLoaded", async function () {
  const uid = localStorage.getItem("uid");
  const role = localStorage.getItem("role");

  if (role !== "teacher") {
    window.location.href = "/";
    return;
  }

  const createBtn = document.querySelector(".ctaBtn");
  createBtn.addEventListener("click", async function () {
    const title = document.querySelector("#course-title").value;
    const subject = document.querySelector("#course-subject").value;
    const room = document.querySelector("#course-room").value;

    if (!title || !subject || !room) {
      alert("Please fill in all fields");
      return;
    }

    const courseData = {
      title: title,
      subject: subject,
      room: room,
    };

    try {
      const response = await fetch(`/api/v1/course?userId=${uid}`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(courseData),
      });

      if (response.status === 201) {
        alert("Course created successfully");
        window.location.href = "/teacher/dashboard";
      } else {
        throw new Error(`Failed to create course. Status: ${response.status}`);
      }
    } catch (error) {
      console.error("Error:", error);
      alert("An error occurred. Please try again later.");
    }
  });
});
