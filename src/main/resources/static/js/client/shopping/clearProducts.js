import { showToast } from "/js/common/showToast.js";
import { showConfirmation } from "/js/common/showConfirmation.js";

//
export function clearProducts() {
  // Nút xoá tất cả món ăn trong giỏ hàng
  const clearButton = document.getElementById("shopping-clear-btn");

  if (clearButton) {
    // Gán sự kiện cho nút
    clearButton.addEventListener("click", async function () {
      // Thêm class "active" thể hiện là nút đang được nhấn
      clearButton.classList.add("active");

      // Tạo confirmation để xác nhận lựa chọn
      const confirmValue = await showConfirmation("Xoá tất cả món ăn ?");
      if (confirmValue) {
        showToast("success", "Xoá tất cả thành công !", 1.2, -75, -65);
      }

      // Xoá class "active" thể hiện là nút không còn được nhấn
      clearButton.classList.remove("active");
    });
  }
}
