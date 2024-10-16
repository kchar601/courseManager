const uid = localStorage.getItem("uid");

window.addEventListener("DOMContentLoaded", async function () {
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

    document.querySelector("#user-fname").value = user.fname;
    document.querySelector("#user-lname").value = user.lname;
    document.querySelector("#user-email").value = user.email;

    const updateBtn = document.querySelector(".ctaBtn");
    updateBtn.addEventListener("click", async () => {
      const fName = document.querySelector("#user-fname").value;
      const lName = document.querySelector("#user-lname").value;
      const email = document.querySelector("#user-email").value;

      const queryParams = new URLSearchParams({
        fName: fName,
        lName: lName,
        email: email,
      });

      const response = await fetch(
        `/api/v1/user/${uid}?${queryParams.toString()}`,
        {
          method: "PUT",
          headers: {
            "Content-Type": "application/json",
          },
        }
      );

      if (!response.ok) {
        throw new Error(`Update failed! Status: ${response.status}`);
      }

      alert("Profile updated successfully!");
	  window.location.reload();
    });
  } catch (error) {
    console.error(error);
  }
});
