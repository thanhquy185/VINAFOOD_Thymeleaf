import { showAddressSelectDialog } from "/js/common/updateAddressSelect.js";

// Hàm tạo một dialog cho phép tạo 1 địa chỉ hợp lệ khi thêm hoặc cập nhật
export function createValidAddress() {
  // - Khi thêm
  if (document.getElementById("add-user-address")) {
    showAddressSelectDialog("add-user-address", "create-address");
  }
  // - Khi cập nhật
  if (document.getElementById("update-user-address")) {
    showAddressSelectDialog("update-user-address", "create-address");
  }
}
