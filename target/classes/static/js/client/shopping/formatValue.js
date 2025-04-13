import { vietnamMoneyFormat, formatDate } from "/js/common/otherEvents.js";

export function formatValue() {
  // Định dạng giá món ăn ở trang Giỏ hàng
  const pCartPriceList = document.querySelectorAll(
    "p.client-shopping__item-catepri"
  );
  if (pCartPriceList) {
    pCartPriceList.forEach((p) => {
      let strings = p.textContent.split(" / ");
      p.textContent = `${strings[0]} / ${vietnamMoneyFormat(
        Number(strings[1])
      )}`;
    });
  }

  // Định dạng tổng giá một món ăn ở trang Giỏ hàng
  const inputCartTotalPriceList = document.querySelectorAll(
    "input.client-shopping__item-price"
  );
  if (inputCartTotalPriceList) {
    inputCartTotalPriceList.forEach((input) => {
      input.value = `${vietnamMoneyFormat(Number(input.value.trim()))}`;
    });
  }

  // Định dạng tổng thanh toán ở trang Chi tiết đơn hàng
  const pTotalPrice = document.querySelector("p.client-shopping__total b");
  if (pTotalPrice) {
    pTotalPrice.textContent = `${vietnamMoneyFormat(
      Number(pTotalPrice.textContent.trim())
    )} VNĐ`;
  }

  // Định dạng tổng thanh toán đơn hàng ở trang Giỏ hàng
  const pOrderPriceList = document.querySelectorAll(
    "p.client-shopping__order-total-price span"
  );
  if (pOrderPriceList) {
    pOrderPriceList.forEach((p) => {
      p.textContent = `${vietnamMoneyFormat(Number(p.textContent.trim()))} VNĐ`;
    });
  }

  // Định dạng tổng thanh toán ở trang Chi tiết đơn hàng
  const spanPrice = document.querySelector(
    ".client-handle-order__row p.price span"
  );
  if (spanPrice) {
    spanPrice.textContent = `${vietnamMoneyFormat(
      Number(spanPrice.textContent.trim())
    )} VNĐ`;
  }

  // Định dạng giá bán các món ăn ở trang Chi tiết đơn hàng
  const tdPriceList = document.querySelectorAll(
    ".client-handle-order__details td.price"
  );
  if (tdPriceList) {
    tdPriceList.forEach((td) => {
      td.textContent = `${vietnamMoneyFormat(Number(td.textContent.trim()))}`;
    });
  }

  // Thay đổi định dạng của thời gian tạo đơn ở trang Giỏ hàng
  const pDateList = document.querySelectorAll(
    "p.client-shopping__order-datetime span"
  );
  pDateList.forEach((p) => {
    p.textContent = `${formatDate(p.textContent)}`;
  });

  // Định dạng thời gian tạo đơn ở trang Chi tiết đơn hàng
  const spanTimeCreate = document.querySelector(
    ".client-handle-order__row p.time span"
  );
  if (spanTimeCreate) {
    spanTimeCreate.textContent = `${formatDate(spanTimeCreate.textContent)}`;
  }
}
