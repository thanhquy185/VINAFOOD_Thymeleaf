// Thiết lập sự kiện ẩn - hiện mật khẩu
export function showPassword() {
  document
    .querySelector(".form__form-group:has(input#login-password) i")
    .addEventListener("click", (e) => {
      // Loại bỏ giá trị mặc định
      e.preventDefault();

      // Thay đổi theo trạng thái của icon "con mắt"
      if (e.target.getAttribute("data-status") === "show") {
        e.target.classList.remove("fa-eye");
        e.target.classList.add("fa-eye-slash");
        e.target.setAttribute("data-status", "hidden");
        document
          .querySelector(".form__form-group input#login-password")
          .setAttribute("type", "password");
      } else {
        e.target.classList.remove("fa-eye-slash");
        e.target.classList.add("fa-eye");
        e.target.setAttribute("data-status", "show");
        document
          .querySelector(".form__form-group input#login-password")
          .setAttribute("type", "text");
      }
    });
}
