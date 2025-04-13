import {
  clickToShowDatePicker,
  defaultDateSelected,
} from "/js/admin/datePickerEvents.js";

// Hàm thiết lập các sự kiện cho thẻ input date khi thêm hoặc cập nhật
export function inputDateEvents() {
  // - Khi thêm
  if (document.getElementById("add-user-birthday")) {
    clickToShowDatePicker("add-user-birthday");
    defaultDateSelected("add-user-birthday");
  }
  // - Khi cập nhật
  if (document.getElementById("update-user-birthday")) {
    clickToShowDatePicker("update-user-birthday");
    defaultDateSelected("update-user-birthday");
  }
}
