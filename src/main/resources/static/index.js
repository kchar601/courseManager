async function attemptLogin() {
  const email = document.querySelector("#email").value;
  const password = document.querySelector("#password").value;

  try {
    const response = await fetch(`/api/v1/user/login`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        email: email,
      }),
    });

    if (!response.ok) {
      throw new Error(`HTTP error! Status: ${response.status}`);
    }

    const data = await response.json();

    localStorage.setItem("uid", data.userId);
    localStorage.setItem("role", data.role);

    window.location.href = `/${data.role}/dashboard`;
  } catch (error) {
    console.error("Error:", error);
    alert("Login failed. Please check your credentials.");
  }
}
