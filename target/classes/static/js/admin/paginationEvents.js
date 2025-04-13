import { userEvents } from "/js/admin/user/userEvents.js";
import { selectEvent01 } from "/js/admin/selectEvents.js";

// Biến chứa các mục ở sidebar
const menuInSideBar = document.querySelectorAll(
  "#admin-sidebar .sidebar__action"
);

// Biến chứa các nút ở pagination
const buttonsInPagination = document.querySelectorAll(
  ".main__pagination .main__pagination-button"
);

// Hàm kiểm tra đường dẫn của thẻ a được nhấn có trùng với đường dẫn hiện tại
window.addEventListener("load", function () {
  buttonsInPagination.forEach((item) => {
    if (item.href === window.location.href) {
      // Lấy ra tên của trang hiện tại
      const pageValue = window.location.pathname.replace("/admin/", "");

      // Thêm và xoá class "active" khỏi các nút tương ứng ở sidebar
      menuInSideBar.forEach((item) => {
        if (String(item.href).includes(pageValue)) {
          item.parentElement.classList.add("active");
        } else {
          item.parentElement.classList.remove("active");
        }
      });

      // Các sự kiện chung
      selectEvent01();

      // Đảm bảo là không phải nhấn từ sidebar
      if (window.location.href.includes("?page=")) {
        if (pageValue === "home") {
        } else if (pageValue === "dashboard_order") {
        } else if (pageValue === "orders") {
        } else if (pageValue === "users") {
          userEvents();
        } else if (pageValue === "suppliers") {
        } else if (pageValue === "input_tickets") {
        } else if (pageValue === "foods") {
        } else if (pageValue === "categories") {
          // lockCategory();
        }
      }
    }
  });
});
