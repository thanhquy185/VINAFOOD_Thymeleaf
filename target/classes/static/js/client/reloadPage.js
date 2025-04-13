import { productEvents } from "/js/client/product/productEvents.js";
import { infoEvents } from "/js/client/info/infoEvents.js";
import { shoppingEvents } from "/js/client/shopping/shoppingEvents.js";
import { paymentEvents } from "/js/client/payment/paymentEvents.js";

//
window.addEventListener("load", function () {
  // Lấy ra tên của trang hiện tại
  const pageName = window.location.pathname.split("/")[2];

  // Sự kiện chung
  window.addEventListener("scroll", function () {
    if (window.scrollY > 0) {
      document.querySelector(".client-header").classList.add("scroll");
    } else {
      document.querySelector(".client-header").classList.remove("scroll");
    }
  });

  // Cập nhật sự kiện cho từng trang tương ứng theo tên
  if (pageName === "product") {
    productEvents();
  } else if (pageName === "info") {
    infoEvents();
  } else if (pageName === "shopping") {
    shoppingEvents();
  } else if (pageName === "payment") {
    paymentEvents();
  }
});
