import { selectEvent03 } from "/js/admin/selectEvents.js";
import { updateAddressHaveValueSelect } from "/js/admin/order/updateAddressSelect.js";

// Các biến giữ đối tượng cho việc lọc "Loại món ăn"
const filterButton = document.querySelector(
  ".main__filter button#filter-button-order"
);
const resetButton = document.querySelector(
  ".main__filter button#reset-button-order"
);
const findInput = document.querySelector(".main__filter input#find-input");
const sortSelect = document.querySelector(".main__filter input#sort-select");
const dateCreateSelect = document.querySelector(
  ".main__filter input#date-select"
);
const provinceSelect = document.querySelector(
  ".main__filter input#province-select"
);
const districtSelect = document.querySelector(
  ".main__filter input#district-select"
);
const statusSelect = document.querySelector(
  ".main__filter input#status-select"
);

// Thiệt lập việc lọc thông tin cho nút "Lọc"
export function filterEvents() {
  if (
    filterButton &&
    resetButton &&
    findInput &&
    sortSelect &&
    dateCreateSelect &&
    provinceSelect &&
    districtSelect &&
    statusSelect
  ) {
    if (sessionStorage.length >= 1) {
      // Kiểm tra trong Session Storage có tồn tại dữ liệu đã lưu không ?
      // Cập nhật lại dữ liệu cho các thẻ input - select
      findInput.value = sessionStorage.getItem("find") || "";
      sortSelect.value = sessionStorage.getItem("sort") || "";
      dateCreateSelect.value = sessionStorage.getItem("dateCreate") || "";
      provinceSelect.value = sessionStorage.getItem("province") || "";
      districtSelect.value = sessionStorage.getItem("district") || "";
      statusSelect.value = sessionStorage.getItem("status") || "";

      // Cập nhật lại định dạng cho các thẻ input - select
      if (sortSelect.value) {
        selectEvent03(
          ".main__filter input#sort-select + ul li",
          sortSelect.value
        );
      }
      if (provinceSelect.value) {
        selectEvent03(
          ".main__filter input#province-select + ul li",
          provinceSelect.value
        );
      }
      if (districtSelect.value) {
        selectEvent03(
          ".main__filter input#district-select + ul li",
          districtSelect.value
        );
      }
      if (statusSelect.value) {
        selectEvent03(
          ".main__filter input#status-select + ul li",
          statusSelect.value
        );
      }

      // ...
      if (dateCreateSelect.value) {
        document
          .querySelector(
            ".form-group.filter:has(input[type='date']) label:nth-of-type(2)"
          )
          .classList.add("hidden");
      }
      if (provinceSelect.value || districtSelect.value) {
        updateAddressHaveValueSelect(
          provinceSelect.value,
          districtSelect.value
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
      const dateCreateValue = String(dateCreateSelect.value).trim();
      const provinceValue = String(provinceSelect.value).trim();
      const districtValue = String(districtSelect.value).trim();
      const statusValue = String(statusSelect.value).trim();

      // Thêm vào Session Storage
      sessionStorage.setItem("find", findValue);
      sessionStorage.setItem("sort", sortValue);
      sessionStorage.setItem("dateCreate", dateCreateValue);
      sessionStorage.setItem("province", provinceValue);
      sessionStorage.setItem("district", districtValue);
      sessionStorage.setItem("status", statusValue);

      // Thêm vào đường dẫn url
      searchParams.set("page", "1");
      if (findValue) {
        searchParams.set("id", findValue);
      }
      if (sortValue) {
        searchParams.set("sort", sortValue);
      }
      if (dateCreateValue) {
        searchParams.set("dateCreate", dateCreateValue);
      }
      if (provinceValue) {
        searchParams.set("province", provinceValue);
      }
      if (districtValue) {
        searchParams.set("district", districtValue);
      }
      if (statusValue) {
        searchParams.set("status", statusValue);
      }

      // Điều hướng đến đường dẫn url mới
      window.location.href = currentUrl.href;
    });
    resetButton.addEventListener("click", (e) => {
      // Loại bỏ giá trị mặc định
      e.preventDefault();

      // Xoá khỏi Session Storage
      sessionStorage.removeItem("find");
      sessionStorage.removeItem("sort");
      sessionStorage.removeItem("dateCreate");
      sessionStorage.removeItem("province");
      sessionStorage.removeItem("district");
      sessionStorage.removeItem("status");

      // Điều hướng đến đường dẫn url không có queryParam
      window.location.href = new URL(
        String(window.location.href).split("?")[0]
      );
    });
  }
}
