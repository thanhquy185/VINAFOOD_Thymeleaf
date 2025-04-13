import { showToast } from "/js/common/showToast.js";

const { jsPDF } = window.jspdf;

export function printOrderTicket() {
  // Gán sự kiện in phiếu khi nhấn nút
  const printButton = document.getElementById("print-ticket-button");
  if (printButton) {
    printButton.addEventListener("click", () => {
      // Định dạng chuỗi ngày
      // - Ngày hiện tại
      const today = new Date();
      const day = today.getDate();
      const month = today.getMonth() + 1;
      const year = today.getFullYear();
      // - Định dạng
      const formattedDate = `${day <= 9 ? "0" + day : day}${
        month <= 9 ? "0" + month : month
      }${year}`;

      // In phiếu
      const element = document.getElementById("content-print");
      console.log(element);
      const options = {
        margin: 5,
        filename: `${formattedDate}_PHDONHANG.pdf`,
        image: { type: "jpeg", quality: 0.98 },
        html2canvas: { scale: 2 }, // Tăng độ phân giải
        jsPDF: { unit: "mm", format: "a3", orientation: "portrait" },
      };

      html2canvas(element, { scale: 2 }).then((canvas) => {
        const imgData = canvas.toDataURL("image/jpeg", 1.0);
        const pdf = new jsPDF("p", "mm", "a4");

        const margin = 4;

        const imgProps = pdf.getImageProperties(imgData);
        const pdfWidth = pdf.internal.pageSize.getWidth() - margin * 2;
        const pdfHeight = (imgProps.height * pdfWidth) / imgProps.width;

        pdf.addImage(imgData, "JPEG", margin, margin, pdfWidth, pdfHeight);
        pdf.save(`${formattedDate}_PHDONHANG.pdf`);
      });

      // Thông báo in thành công
      showToast("success", "In phiếu thành công", 1.2, -75, -68);
    });
  }
}
