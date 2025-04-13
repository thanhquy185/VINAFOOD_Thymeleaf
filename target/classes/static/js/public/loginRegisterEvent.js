import { showPassword } from "/js/public/showPassword.js";
import { submitLogin } from "/js/public/submitLogin.js";

//
window.addEventListener("load", (e) => {
  // Loại bỏ giá trị mặc định
  e.preventDefault();

  // Lấy ra tên của trang hiện tại
  const pageValue = window.location.pathname.replace("/", "");

  //

  //
  if (pageValue === "login") {
    showPassword();
    submitLogin();
  } else if (pageValue === "register") {
    console.log(123);
  }
});
