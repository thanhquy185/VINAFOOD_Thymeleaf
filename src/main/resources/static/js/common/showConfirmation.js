export function showConfirmation(content) {
  // Tạo confirmation dialog
  const confirmation = document.createElement("dialog");
  confirmation.classList.add("confirmation");
  confirmation.innerHTML = `
        <div class="confirmation__info">
            <img src="/assets/images/others/question-confirmation.png" alt="confirmation-icon" class="confirmation__image" />
            <p class="confirmation__content">${content}</p>
        </div>
        <div class="confirmation__buttons">
            <button id="confirmation-accept-button" class="confirmation__button btn">Xác nhận</button>
            <button id="confirmation-close-button" class="confirmation__button btn close">Đóng</button>
        </div>
    `;

  // Thêm vào thẻ body và hiển thị
  document.body.appendChild(confirmation);
  confirmation.showModal();

  // Dùng Promise để chờ giá trị từ nút bấm
  return new Promise((resolve) => {
    // Gán sự kiện cho nút "Xác nhận"
    document
      .getElementById("confirmation-accept-button")
      .addEventListener("click", (e) => {
        // Loại bỏ giá trị mặc định
        e.preventDefault();

        //
        confirmation.remove();
        resolve(true);
      });

    // Gán sự kiện cho nút "Đóng"
    document
      .getElementById("confirmation-close-button")
      .addEventListener("click", (e) => {
        // Loại bỏ giá trị mặc định
        e.preventDefault();

        //
        confirmation.remove();
        resolve(false);
      });
  });
}
