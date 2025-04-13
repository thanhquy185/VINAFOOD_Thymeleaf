import { selectEvent03 } from "/js/admin/selectEvents.js";

//
export function menuFormat() {
  const listFormItem = document.querySelectorAll(
    ".client-product__filter-item"
  );

  listFormItem.forEach((formItem) => {
    if (formItem.childElementCount - 2 >= 5) {
      // Ẩn các mục vượt quá mức tổi thiểu
      for (let i = 6; i < formItem.childElementCount - 1; i++) {
        formItem.children.item(i).classList.add("hidden");
      }

      // Nếu tổng số mục vượt quá mức tổi thiểu sẽ hiển thị với chuỗi html mặc định
      const showListHiddenButton = formItem.children.item(
        formItem.childElementCount - 1
      );
      showListHiddenButton.innerHTML = `
            Hiển thị ${
              formItem.childElementCount - 1 - 6
            } mục còn lại <i class="fa-solid fa-chevron-down"></i>
        `;
      showListHiddenButton.computedStyleMap().get("display").value == "none"
        ? showListHiddenButton.classList.remove("hidden")
        : "";

      // Gán sự kiện nhấn cho nút
      showListHiddenButton.addEventListener("click", (e) => {
        // Loại bỏ giá trị mặc định
        e.preventDefault();

        // Hiện các mục vượt quá mức tổi thiểu
        for (let i = 6; i < formItem.childElementCount - 1; i++) {
          formItem.children.item(i).classList.toggle("hidden");
        }

        // Kiểm tra các mục "vượt quá" để thay đổi nội dung tương ứng cho nút
        if (
          formItem.children.item(6).computedStyleMap().get("display") == "none"
        ) {
          showListHiddenButton.innerHTML = `
            Hiển thị ${
              formItem.childElementCount - 1 - 6
            } mục còn lại <i class="fa-solid fa-chevron-down"></i>
        `;
        } else {
          showListHiddenButton.innerHTML = `
            Ẩn ${
              formItem.childElementCount - 1 - 6
            } mục cuối cùng <i class="fa-solid fa-chevron-up"></i>
        `;
        }
      });
    }
  });
}

// Các biến giữ đối tượng cho việc lọc "Món ăn"
const filterButton = document.getElementById("product-filter-button");
const resetButton = document.getElementById("product-reset-button");
const findInput = document.getElementById("product-find-input");
const sortRadios = document.querySelectorAll("input[name='sort']");
const categoryCheckboxs = document.querySelectorAll("input[name='category']");
const statusRadios = document.querySelectorAll("input[name='status']");
let findValue, sortValue, categoryValue, statusValue;

export function filterEvents() {
  if (
    filterButton &&
    resetButton &&
    findInput &&
    sortRadios &&
    categoryCheckboxs &&
    statusRadios
  ) {
    // Kiểm tra trong Session Storage có tồn tại dữ liệu đã lưu không ?
    if (sessionStorage.length >= 1) {
      // Cập nhật lại dữ liệu cho các thẻ input
      findValue = sessionStorage.getItem("find") || "";
      sortValue = sessionStorage.getItem("sort") || "";
      categoryValue = sessionStorage.getItem("category") || "";
      statusValue = sessionStorage.getItem("status") || "";

      // Cập nhật lại các input
      if (findValue) {
        findInput.value = findValue;
      }
      if (sortValue) {
        console.log(sortValue);
        for (let i = 0; i < sortRadios.length; i++) {
          if (sortRadios.item(i).value == sortValue) {
            sortRadios.item(i).checked = true;
            break;
          }
        }
      }
      if (categoryValue) {
        // const listCategory = categoryValue.split(",");
        for (let i = 0; i < categoryCheckboxs.length; i++) {
          if (categoryValue.includes(categoryCheckboxs.item(i).value)) {
            categoryCheckboxs.item(i).checked = true;
          }
        }
      }
      if (statusValue) {
        for (let i = 0; i < statusRadios.length; i++) {
          if (statusRadios.item(i).value == statusValue) {
            statusRadios.item(i).checked = true;
            break;
          }
        }
      }
    }

    // Thiết lập sự kiện cho các nút
    filterButton.addEventListener("click", function () {
      // Cập nhật lại các biến chữa giá trị
      findValue = "";
      sortValue = "";
      categoryValue = "";
      statusValue = "";

      // Biến chứa đường dẫn url của trang (có query param)
      const currentUrl = new URL(String(window.location.href).split("?")[0]);
      const searchParams = currentUrl.searchParams;

      // Gán dữ liệu từ các thẻ input
      // - Tên món ăn
      findValue = findInput.value;
      // - Sắp xếp
      for (let i = 0; i < sortRadios.length; i++) {
        if (sortRadios.item(i).checked) {
          sortValue = sortRadios.item(i).value;
          break;
        }
      }
      // - Danh mục
      for (let i = 0; i < categoryCheckboxs.length; i++) {
        if (categoryCheckboxs.item(i).checked) {
          categoryValue += categoryCheckboxs.item(i).value + ",";
        }
      }
      categoryValue = categoryValue.slice(0, categoryValue.length - 1);
      // - Trạng thái
      for (let i = 0; i < statusRadios.length; i++) {
        if (statusRadios.item(i).checked) {
          statusValue = statusRadios.item(i).value;
          break;
        }
      }

      // Cập nhật lại Session Storage
      sessionStorage.removeItem("find");
      sessionStorage.removeItem("sort");
      sessionStorage.removeItem("category");
      sessionStorage.removeItem("status");

      // Thêm vào Session Storage
      sessionStorage.setItem("find", findValue);
      sessionStorage.setItem("sort", sortValue);
      sessionStorage.setItem("category", categoryValue);
      sessionStorage.setItem("status", statusValue);

      // Thêm vào đường dẫn url
      searchParams.set("page", "1");
      if (findValue) {
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
    resetButton.addEventListener("click", function () {
      // Cập nhật lại các input
      sortRadios.forEach((sort) => {
        sort.checked = false;
      });
      categoryCheckboxs.forEach((category) => {
        category.checked = false;
      });
      statusRadios.forEach((status) => {
        status.checked = false;
      });

      // Xoá khỏi Session Storage
      sessionStorage.removeItem("find");
      sessionStorage.removeItem("sort");
      sessionStorage.removeItem("category");
      sessionStorage.removeItem("status");

      // Điều hướng đến đường dẫn url không có queryParam
      window.location.href = new URL(
        String(window.location.href).split("?")[0]
      );
    });
  }
}
