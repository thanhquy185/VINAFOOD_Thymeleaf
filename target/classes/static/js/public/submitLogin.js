import { changeFormatWhenValidateForm } from "/js/common/otherEvents.js";
import { showToast } from "/js/common/showToast.js";

// Hàm submit form Đăng nhập
export function submitLogin() {
  document
    .getElementById("login-form")
    .addEventListener("submit", async (e) => {
      // Ngăn không cho gửi request mặc định
      e.preventDefault();

      // Lấy giá trị từ các thẻ input
      const username = document.getElementById("login-username").value || null;
      const password = document.getElementById("login-password").value || null;

      // Gọi API
      const response = await fetch("/api/auth/login", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ username, password }),
      });

      // Chờ đợi server gửi kết quả
      if (response.ok) {
        showToast("success", "Đăng nhập thành công !", 1.2);
        // setTimeout(() => {
        //   window.location.reload();
        // }, 1700);

        const result = await response.json();
        console.log(result);
        sessionStorage.setItem("accessToken", result.data.access_token);
        changeFormatWhenValidateForm(["username", "password"], []);
      } else {
        showToast("error", "Đăng nhập thất bại !", 1.2);
        const errors = await response.json();
        console.log(errors);
        changeFormatWhenValidateForm(["username", "password"], errors);
      }
    });
}
