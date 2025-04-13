import {
  vietnamMoneyFormat,
  numberToVietnamWords,
  formatDate,
} from "/js/common/otherEvents.js";

export function formatValue() {
  // Thay đổi định dạng của ngày tạo đơn
  const tdDateList = document.querySelectorAll("td.date");
  if (tdDateList) {
    tdDateList.forEach((td) => {
      td.textContent = `${formatDate(td.textContent)}`;
    });
  }

  // Thay đổi định dạng của địa chỉ giao hàng
  // const tdAddressList = document.querySelectorAll("td.address");
  // tdAddressList.forEach((td) => {
  //   const address = td.textContent.trim();
  //   const parts = address.split(",");

  //   // Kiểm tra nếu địa chỉ có đủ phần
  //   td.innerHTML = `Địa chỉ không hợp lệ`;
  //   if (parts.length >= 3) {
  //     let houseNumberStreet = parts[0].trim();
  //     let district;
  //     if (
  //       parts[1].trim().startsWith("Quận ") ||
  //       parts[1].trim().startsWith("Huyện ")
  //     ) {
  //       district = parts[1].trim().substring(parts[1].trim().indexOf(" ") + 1);
  //     } else if (parts[1].trim().startsWith("Thành phố ")) {
  //       district = parts[1].trim().substring(9 + 1);
  //     }
  //     let city;
  //     if (parts[2].trim().startsWith("Thủ đô ")) {
  //       city = parts[2].trim().substring(6 + 1);
  //     } else if (parts[2].trim().startsWith("Tỉnh ")) {
  //       city = parts[2].trim().substring(parts[2].trim().indexOf(" ") + 1);
  //     } else if (parts[2].trim().startsWith("Thành phố ")) {
  //       city = parts[2].trim().substring(9 + 1);
  //     }
  //     td.innerHTML = `${district} - ${city}`;
  //   }
  // });

  // Thay đổi định dạng của tổng thanh toán khi cập nhật
  const inputTotalPrice = document.getElementById("update-order-total-price");
  if (inputTotalPrice) {
    const totalPrice = Number(inputTotalPrice.value);
    inputTotalPrice.value = `${vietnamMoneyFormat(
      totalPrice
    )} (${numberToVietnamWords(totalPrice)})`;
  }

  // Thay đổi định dạng của tổng thanh toán khi in phiếu
  const printOrderTotalPrice = document.getElementById(
    "print-order-total-price"
  );
  if (printOrderTotalPrice) {
    const totalPrice = Number(printOrderTotalPrice.textContent);
    printOrderTotalPrice.textContent = `${vietnamMoneyFormat(
      totalPrice
    )} (${numberToVietnamWords(totalPrice)})`;
  }

  // Thay đổi định dạng của các ô mang giá trị là "tiền"
  const tdPriceList = document.querySelectorAll("td.price");
  if (tdPriceList) {
    tdPriceList.forEach((td) => {
      td.textContent = `${vietnamMoneyFormat(Number(td.textContent.trim()))}`;
    });
  }
}
