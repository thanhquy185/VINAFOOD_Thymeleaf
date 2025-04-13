import { vietnamMoneyFormat } from "/js/common/otherEvents.js";

export function formatValue() {
  // Định dạng giá bán các món ăn ở trang Sản phẩm
  const pPriceList = document.querySelectorAll("p.client-product__item-price");
  if (pPriceList) {
    pPriceList.forEach((p) => {
      p.textContent = `${vietnamMoneyFormat(Number(p.textContent.trim()))} VNĐ`;
    });
  }

  // Định dạng mô tả món ăn ở trang Chi tiết món ăn
  const pDetailDesc = document.querySelector(
    ".client-detail-product__info p.description"
  );
  if (pDetailDesc) {
    pDetailDesc.textContent = `“${pDetailDesc.textContent.trim()}”`;
  }

  // Định dạng giá bán món ăn ở trang Chi tiết món ăn
  const pDetailPrice = document.querySelector("p.client-detail-product__price");
  if (pDetailPrice) {
    pDetailPrice.textContent = `${vietnamMoneyFormat(
      Number(pDetailPrice.textContent.trim())
    )} VNĐ`;
  }
}
