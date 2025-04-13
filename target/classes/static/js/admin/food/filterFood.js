import { selectEvent03 } from "/js/admin/selectEvents.js";

// Các biến giữ đối tượng cho việc lọc "Món ăn"
const filterButton = document.querySelector(
  ".main__filter button#filter-button-food"
);
const findInput = document.querySelector(".main__filter input#find-input");
const sortSelect = document.querySelector(".main__filter input#sort-select");
const categorySelect = document.querySelector(
  ".main__filter input#category-select"
);
const statusSelect = document.querySelector(
  ".main__filter input#status-select"
);

// Thiệt lập việc lọc thông tin cho nút "Lọc"
export function filterEvents() {
  if (
    filterButton &&
    findInput &&
    sortSelect &&
    categorySelect &&
    statusSelect
  ) {
    // Kiểm tra trong Session Storage có tồn tại dữ liệu đã lưu không ?
    if (sessionStorage.length >= 1) {
      // Cập nhật lại dữ liệu cho các thẻ input - select
      findInput.value = sessionStorage.getItem("find") || "";
      sortSelect.value = sessionStorage.getItem("sort") || "";
      categorySelect.value = sessionStorage.getItem("category") || "";
      statusSelect.value = sessionStorage.getItem("status") || "";

      // Cập nhật lại định dạng cho các thẻ input - select
      if (sortSelect.value) {
        selectEvent03(
          ".main__filter input#sort-select + ul li",
          sortSelect.value
        );
      }
      if (categorySelect.value) {
        selectEvent03(
          ".main__filter input#category-select + ul li",
          categorySelect.value
        );
      }
      if (statusSelect.value) {
        selectEvent03(
          ".main__filter input#status-select + ul li",
          statusSelect.value
        );
      }
    }

    // Thiệt lập sự kiện cho nút
    filterButton.addEventListener("click", function () {
      // Biến chứa đường dẫn url của trang (có query param)
      const currentUrl = new URL(String(window.location.href).split("?")[0]);
      const searchParams = currentUrl.searchParams;

      // Lấy dữ liệu từ các thẻ input - select
      const findValue = String(findInput.value).trim();
      const sortValue = String(sortSelect.value).trim();
      const categoryValue = String(categorySelect.value).trim();
      const statusValue = String(statusSelect.value).trim();

      // Thêm vào Session Storage
      sessionStorage.setItem("find", findValue);
      sessionStorage.setItem("sort", sortValue);
      sessionStorage.setItem("category", categoryValue);
      sessionStorage.setItem("status", statusValue);

      // Thêm vào đường dẫn url
      searchParams.set("page", "1");
      if (findValue) {
        searchParams.set("id", findValue);
        searchParams.set("name", findValue);
      }
      if (sortValue) {
        searchParams.set("sort", sortValue);
      }
      if (categoryValue) {
        searchParams.set("category", categoryValue);
      }
      if (statusValue) {
        searchParams.set("status", statusValue);
      }

      // Điều hướng đến đường dẫn url mới
      window.location.href = currentUrl.href;
    });
  }
}
