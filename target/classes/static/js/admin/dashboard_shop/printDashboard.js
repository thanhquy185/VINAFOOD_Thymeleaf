export function printDashboardTicket() {
  const printButton = document.getElementById("print-button-dashboard_shop");
  printButton.addEventListener("click", (e) => {
    // Loại bỏ giá trị mặc định
    e.preventDefault();

    // Lấy ra ngày hiện tại
    const today = new Date();
    const day = today.getDate();
    const month = today.getMonth() + 1;
    const year = today.getFullYear();

    //
    const typeValueSelected = document.querySelector(
      ".main__filter input#type-select"
    );
    const headDashboardTable = document.querySelector(
      ".main__data > .main__table.dashboard > thead"
    );
    const bodyDashboardTable = document.querySelector(
      ".main__data > .main__table.dashboard > tbody"
    );
    const footDashboardTable = document.querySelector(
      ".main__data > .main__table.dashboard > tfoot"
    );
    const totalDashboardTable = document.querySelector(
      ".main__data > .main__total > span"
    );
    const dateStartDashboardTable = document.querySelector(
      ".main__data > .main__table.dashboard > tbody > tr:first-of-type > td:nth-of-type(2)"
    );
    const dateEndDashboardTable = document.querySelector(
      ".main__data > .main__table.dashboard > tbody > tr:last-of-type > td:nth-of-type(3)"
    );

    if (
      headDashboardTable &&
      bodyDashboardTable &&
      footDashboardTable &&
      totalDashboardTable &&
      dateStartDashboardTable &&
      dateEndDashboardTable
    ) {
      // Thêm class active thể hiện là nút được nhấn (vì dialog còn hiện)
      printButton.classList.add("active");

      // Tạo một dialog để thêm một người dùng
      const printDialog = document.createElement("dialog");
      // - Định dạng dialog
      printDialog.classList.add("ticket");
      printDialog.classList.add("print-dashboard_shop");
      printDialog.style.width = "90%";
      // - Ghi nội dung dialog
      printDialog.innerHTML = `
            <button id="close-ticket-button" class="dialog__close">
              <i class="fa-solid fa-xmark"></i>
            </button>
            <div id="content-print" class="ticket__content">
              <header class="ticket__header">
                  <img src="/assets/images/others/brand-image.png" alt="Logo Web" class="ticket__logo">
                  <div class="ticket__contact">
                      <p>Cửa hàng bán đồ ăn VINA FOOD</p>
                      <p>273 An Đ. Vương, Phường 2, Quận 5, Hồ Chí Minh 700000</p>
                      <p>123456789 - 0987654321</p>
                      <p>vinafood@gmail.com.vn</p>
                  </div>
              </header>
              <main class="ticket__body">
                  <h1 class="ticket__title">${String(
                    typeValueSelected.value
                  ).toUpperCase()}</h1>
                  <p class="ticket__date">Từ ngày: <span class="date-start">${
                    dateStartDashboardTable.innerHTML
                  }</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Đến ngày: <span class="date-end">${
        dateEndDashboardTable.innerHTML
      }</span></p>
                  <table class="ticket__table">
                      <thead>${headDashboardTable.innerHTML}</thead>
                      <tbody>${bodyDashboardTable.innerHTML}</tbody>
                      <tfoot>${footDashboardTable.innerHTML}</tfoot>
                  </table>
                  <p class="ticket__total"><strong>Viết bằng chữ:</strong> <span>${
                    totalDashboardTable.innerHTML
                  }</span></p>
              </main>
              <footer class="ticket__footer">
                  <p class="ticket_customer">
                      Ngày ${day} tháng ${
        month <= 9 ? "0" + month : month
      } năm ${year}
                      <b>Nhân viên lập phiếu</b>
                      (Ký tên, ghi rõ họ tên)
                  </p>
                  <p class="ticket_customer">
                      Ngày ${day} tháng ${
        month <= 9 ? "0" + month : month
      } năm ${year}
                      <b>Thủ quỹ</b>
                      (Ký tên, ghi rõ họ tên)
                  </p>
                  <p class="ticket_customer">
                      Ngày ${day} tháng ${
        month <= 9 ? "0" + month : month
      } năm ${year}
                      <b>Kế toán trưởng</b>
                      (Ký tên, ghi rõ họ tên)
                  </p>
                  <p class="ticket_customer">
                      Ngày ${day} tháng ${
        month <= 9 ? "0" + month : month
      } năm ${year}
                      <b>Giám đốc</b>
                      (Ký tên, ghi rõ họ tên)
                  </p>
              </footer>
            </div>
            <button id="print-ticket-button" class="ticket__print-btn"><i class="fa-solid fa-file-arrow-down"></i>&nbsp;&nbsp;Tải xuống phiếu</button>
          `;

      // Thêm vào body
      document.body.appendChild(printDialog);

      // Hiển thị printDialog
      printDialog.showModal();

      // Gán sự kiện cho nút "Đóng" dialog
      document
        .getElementById("close-ticket-button")
        .addEventListener("click", () => {
          // Xoá dialog
          printDialog.remove();

          // Xoá class active thể hiện là nút không được nhấn (vì dialog không còn hiện)
          printButton.classList.remove("active");
        });

      // Gán sự kiện in phiếu khi nhấn nút
      document
        .getElementById("print-ticket-button")
        .addEventListener("click", () => {
          // Định dạng chuỗi ngày
          const formattedDate = `${day}${
            month <= 9 ? "0" + month : month
          }${year}`;

          // In phiếu
          const element = document.getElementById("content-print");
          const options = {
            margin: 5,
            filename: `${formattedDate}_PHTHONGKE.pdf`,
            image: { type: "jpeg", quality: 0.98 },
            html2canvas: { scale: 2 }, // Tăng độ phân giải
            jsPDF: { unit: "mm", format: "a3", orientation: "portrait" },
          };
          html2pdf().set(options).from(element).save();
        });
    }
  });
}
