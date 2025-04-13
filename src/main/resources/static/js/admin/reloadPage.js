import {
  selectEvent01,
  selectEvent02,
  selectEvent04,
} from "/js/admin/selectEvents.js";
import { dashboardShopEvents } from "/js/admin/dashboard_shop/dashboardShopEvents.js";
import { orderEvents } from "/js/admin/order/orderEvents.js";
import { userEvents } from "/js/admin/user/userEvents.js";
import { filterEvents } from "/js/admin/category/filterCategory.js";
import { foodEvents } from "/js/admin/food/foodEvents.js";

// Biến chứa các mục ở sidebar
const menuInSideBar = document.querySelectorAll(
  "#admin-sidebar .sidebar__action"
);

window.addEventListener("load", function () {
  // Nếu người dùng nhập tay, phải tự cập nhật ... tương ứng
  console.log(window.location.href);

  // Lấy ra tên của trang hiện tại
  const pageName = window.location.pathname.split("/")[2];

  // Biến chứa đối tượng tiêu đề trang hiện tại
  const titlePage = document.getElementById("title-page");

  // Thêm và xoá class "active" khỏi các nút tương ứng ở sidebar
  menuInSideBar.forEach((item) => {
    if (String(item.href).includes(pageName)) {
      item.parentElement.classList.add("active");
    } else {
      item.parentElement.classList.remove("active");
    }
  });

  // Các sự kiện chung
  // -
  selectEvent01();
  // -
  const selectElement = document.querySelectorAll(
    ".main-crud__form-group > select"
  );
  selectElement.forEach((select) => {
    selectEvent02(select);
  });

  // Đảm bảo là không phải nhấn từ sidebar
  if (pageName === "dashboard-shop") {
    titlePage.textContent = "Thông kê cửa hàng";
    selectEvent04();
    dashboardShopEvents();
  } else if (pageName === "dashboard-order") {
  } else if (pageName === "orders") {
    titlePage.textContent = "Đơn hàng";
    orderEvents();
  } else if (pageName === "users") {
    titlePage.textContent = "Người dùng";
    userEvents();
  } else if (pageName === "categories") {
    titlePage.textContent = "Loại món ăn";
    filterEvents();
  } else if (pageName === "foods") {
    titlePage.textContent = "Món ăn";
    foodEvents();
  }
});
