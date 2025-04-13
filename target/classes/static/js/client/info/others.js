import { showAddressSelectDialog } from "/js/common/updateAddressSelect.js";

//
export function others() {
  // Thay đổi ảnh đại diện
  const inputFile = document.getElementById("info-image");
  if (inputFile) {
    inputFile.addEventListener("change", (e) => {
      // Loại bỏ giá trị mặc định
      e.preventDefault();

      //
      const file = e.target.files[0];
      if (file) {
        const reader = new FileReader();
        reader.onload = function (e) {
          document.querySelector(".client-info__form-group.avatar img").src =
            e.target.result;
        };
        reader.readAsDataURL(file);
      }
    });
  }

  // Thiết lập các sự kiện liên quan đến thẻ input date
  document
    .querySelector(".client-info__form-group input#info-date")
    .addEventListener("change", (e) => {
      // Loại bỏ giá trị mặc định
      e.preventDefault();

      if (!e.target.value) {
        e.target.classList.remove("hasValidDate");
      } else {
        e.target.classList.add("hasValidDate");
      }
    });

  document
    .querySelector(".client-info__form-group input#info-date")
    .addEventListener("click", function (e) {
      // Loại bỏ giá trị mặc định
      e.preventDefault();

      e.target.showPicker();
    });

  // Thiết lập các sự kiện liên quan đến thẻ select
  document
    .querySelector(".client-info__form-group select#info-gender")
    .addEventListener("change", function (e) {
      // - Loại bỏ giá trị mặc định
      e.preventDefault();

      // - Thay đổi khi chọn mục
      if (e.target.value !== "") {
        e.target.classList.add("changed");
      } else {
        e.target.classList.remove("changed");
      }
    });

  // Thiết lập sự kiện "tạo" địa chỉ
  showAddressSelectDialog("info-address", "create-address");
}
