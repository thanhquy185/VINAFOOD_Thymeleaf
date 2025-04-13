import { vietnamMoneyFormat } from "/js/common/otherEvents.js";

//
export function formatValue() {
  // Định dạng giá bán ban đầu các món ăn ở trang Thanh toán
  const pPriceList = document.querySelectorAll(
    "p.client-payment-orders__details"
  );
  if (pPriceList) {
    pPriceList.forEach((p) => {
      const strings = p.textContent.split(" / ");
      p.textContent = `${strings[0]} / ${vietnamMoneyFormat(
        Number(strings[1])
      )}`;
    });
  }

  // Định dạng tổng giá bán một món ăn ở trang Thanh toán
  const pTotalPriceList = document.querySelectorAll(
    "p.client-payment-orders__total-price-product"
  );
  if (pTotalPriceList) {
    pTotalPriceList.forEach((p) => {
      p.textContent = `${vietnamMoneyFormat(Number(p.textContent.trim()))} VNĐ`;
    });
  }

  // Định dạng giá tạm tính ở trang Chi tiết món ăn
  const spanTempPrice = document.querySelector(
    "p.client-payment-orders__temp-price span"
  );
  if (spanTempPrice) {
    spanTempPrice.textContent = `${vietnamMoneyFormat(
      Number(spanTempPrice.textContent.trim())
    )} VNĐ`;
  }

  // Định dạng tổng tiền thanh toán ở trang Chi tiết món ăn
  const spanTotalPrice = document.querySelector(
    "p.client-payment-orders__total-price span"
  );
  if (spanTotalPrice) {
    spanTotalPrice.innerHTML = `${vietnamMoneyFormat(
      Number(spanTotalPrice.textContent.trim())
    )} <u>VNĐ</u>`;
  }
}
