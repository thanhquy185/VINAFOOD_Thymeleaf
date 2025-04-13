//
export function order() {
  // Nút đặt hàng trong giỏ hàng
  const orderButton = document.getElementById("shopping-order-btn");

  if (orderButton) {
    // Gán sự kiện cho nút
    orderButton.addEventListener("click", async function () {
      // Thêm class "active" thể hiện là nút đang được nhấn
      orderButton.classList.add("active");

      // Tạo dialog thể hiện đặt đơn hàng
      const detailDialog = document.createElement("dialog");
      detailDialog.classList.add("dialog");
      detailDialog.classList.add("client-order");
      detailDialog.style.width = "72%";
      detailDialog.innerHTML = `
      <h1 class="dialog__title">Đặt đơn hàng</h1>
      <button id="close-type-button" class="dialog__close">
        <i class="fa-solid fa-xmark"></i>
      </button>
      <div class="dialog__line"></div>
      <div class="dialog-client-order__info">
          <div class="dialog-client-order__info-row">
              <p class="id"><b>Mã đơn hàng:</b> #1</p>
              <p class="price"><b>Tổng thanh toán:</b> 2.000.000 VNĐ</p>
          </div>
          <div class="dialog-client-order__info-row">
              <p class="date"><b>Thời gian đặt hàng:</b> yyyy-mm-dd hh:mm:ss</p>
              <p class="method-pay"><b>Phương thức thanh toán:</b> Thanh toán tiền mặt</p>
          </div>
          <div class="dialog-client-order__info-row">
              <p class="address-to-ship"><b>Địa chỉ giao hàng:</b> 123, Thành phố Hồ Chí Minh</p>
          </div>
          <div class="dialog-client-order__table">
              <p class="detail-text"><b>Chi tiết đơn hàng:</b></p>
              <table class="detail-table">
                  <thead>
                      <tr>
                          <th width="12%">Hình ảnh</th>
                          <th width="40%">Tên món ăn</th>
                          <th width="14%">Giá bán (VNĐ)</th>
                          <th width="14%">Số lượng</th>
                          <th width="20%">Thành tiền (VNĐ)</th>
                      </tr>
                  </thead>
                  <tbody>
                      <tr>
                          <td><img src="/assets/images/foods/1743412734102-food-10.jpg" alt="" /></td>
                          <td class="left">Lẩu hải sản chua cay</td>
                          <td>220.000</td>
                          <td>4</td>
                          <td class="right">880.000</td>
                      </tr>
                  </tbody>
              </table>
          </div>
          <button class="dialog-client-order__button btn">Xác nhận</button>
      </div>
    `;

      document.body.appendChild(detailDialog);

      detailDialog.showModal();

      // Gán sự kiện cho nút "Đóng" dialog
      document
        .getElementById("close-type-button")
        .addEventListener("click", () => {
          // Xoá dialog
          detailDialog.remove();

          // Xoá class "active" thể hiện là nút không còn được nhấn
          orderButton.classList.remove("active");
        });
    });
  }
}
