import { selectEvent03 } from "/js/admin/selectEvents.js";

// Các biến giữ đối tượng cho việc lọc "Loại món ăn"
const filterButton = document.querySelector(
  ".main__filter button#filter-button-category"
);
const findInput = document.querySelector(".main__filter input#find-input");
const sortSelect = document.querySelector(".main__filter input#sort-select");
const statusSelect = document.querySelector(
  ".main__filter input#status-select"
);

// Thiệt lập việc lọc thông tin cho nút "Lọc"
export function filterEvents() {
  // Kiểm tra trong Session Storage có tồn tại dữ liệu đã lưu không ?
  if (sessionStorage.length >= 1) {
    // Cập nhật lại dữ liệu cho các thẻ input - select
    findInput.value = sessionStorage.getItem("find") || "";
    sortSelect.value = sessionStorage.getItem("sort") || "";
    statusSelect.value = sessionStorage.getItem("status") || "";

    // Cập nhật lại định dạng cho các thẻ input - select
    if (sortSelect.value) {
      selectEvent03(
        ".main__filter input#sort-select + ul li",
        sortSelect.value
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
  filterButton.addEventListener("click", (e) => {
    // Loại bỏ giá trị mặc định
    e.preventDefault();

    // Biến chứa đường dẫn url của trang (có query param)
    const currentUrl = new URL(String(window.location.href).split("?")[0]);
    const searchParams = currentUrl.searchParams;

    // Lấy dữ liệu từ các thẻ input - select
    const findValue = String(findInput.value).trim();
    const sortValue = String(sortSelect.value).trim();
    const statusValue = String(statusSelect.value).trim();

    // Thêm vào Session Storage
    sessionStorage.setItem("find", findValue);
    sessionStorage.setItem("sort", sortValue);
    sessionStorage.setItem("status", statusValue);

    // Thêm vào đường dẫn url
    searchParams.set("page", "1");
    if (findValue) {
      searchParams.set("id", findValue);
    }
    if (sortValue) {
      searchParams.set("sort", sortValue);
    }
    if (statusValue) {
      searchParams.set("status", statusValue);
    }

    // Điều hướng đến đường dẫn url mới
    window.location.href = currentUrl.href;
  });
}
