import { showToast } from "/js/common/showToast.js";
import { showConfirmation } from "/js/common/showConfirmation.js";

//
export function cancelOrder() {
  // Danh sách các nút chi tiết đơn hàng
  const detailButtons = document.querySelectorAll(
    "button.client-shopping__order-button.cancel"
  );

  // Gán sự kiện cho từng nút
  detailButtons.forEach((button) => {
    button.addEventListener("click", async function () {
      // Mã đơn hàng từ nơi nút được nhấn
      const idOrderSelected =
        button.parentElement.parentElement.getAttribute("data-order");

      // Tạo confirmation để xác nhận lựa chọn
      const confirmValue = await showConfirmation("Huỷ đơn hàng này ?");
      if (confirmValue) {
        showToast("success", "Huỷ đơn hàng thành công !", 1.2, -75, -63);
      }
    });
  });
}
