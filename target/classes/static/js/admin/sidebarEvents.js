import { selectEvent01, selectEvent04 } from "/js/admin/selectEvents.js";
import { dashboardShopEvents } from "/js/admin/dashboard_shop/dashboardShopEvents.js";
import { orderEvents } from "/js/admin/order/orderEvents.js";
import { userEvents } from "/js/admin/user/userEvents.js";
import { filterEvents } from "/js/admin/category/filterCategory.js";
import { foodEvents } from "/js/admin/food/foodEvents.js";

// Biến chứa các mục ở sidebar
const menuInSideBar = document.querySelectorAll(
  "#admin-sidebar .sidebar__action"
);

// Hàm kiểm tra đường dẫn của thẻ a được nhấn có trùng với đường dẫn hiện tại
window.addEventListener("load", function () {
  menuInSideBar.forEach((item, i) => {
    if (item.href === window.location.href) {
      // Xoá hết dữ liệu hiện tại trong Session Storage
      sessionStorage.clear();

      // Cuộn về đầu trang
      // window.scrollTo(0, 0);

      // Biến chứa đối tượng tiêu đề trang hiện tại
      const titlePage = document.getElementById("title-page");

      // Thêm class "active" vào nút được nhấn
      item.parentElement.classList.add("active");

      // Xoá class "active" khỏi các nút không được nhấn
      menuInSideBar.forEach((otherItem, j) => {
        if (i != j) {
          otherItem.parentElement.classList.remove("active");
        }
      });

      // Các sự kiện chung
      selectEvent01();

      // Lấy ra tên của trang hiện tại
      const pageName = window.location.pathname.split("/")[2];
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
    }
  });
});
