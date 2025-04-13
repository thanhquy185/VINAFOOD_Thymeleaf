import { formatValue } from "/js/admin/order/formatValue.js";
import { filterEvents } from "/js/admin/order/filterOrder.js";
import { updateAddressSelect } from "/js/admin/order/updateAddressSelect.js";
// import { updateOrderData } from "/js/admin/order/updateOrder.js";
import { printOrderTicket } from "/js/admin/order/printOrder.js";

export function orderEvents() {
  //
  const dateCreateSelect = document.getElementById("date-select");
  if (dateCreateSelect) {
    dateCreateSelect.addEventListener("click", (e) => {
      e.target.showPicker();
    });
    dateCreateSelect.addEventListener("change", (e) => {
      const labelFake = document.querySelector(
        ".form-group.filter:has(input[type='date']) label:nth-of-type(2)"
      );
      if (e.target.value == "") {
        labelFake.classList.remove("hidden");
      } else {
        labelFake.classList.add("hidden");
      }
    });
  }

  formatValue();
  updateAddressSelect();
  filterEvents();
  printOrderTicket();
}
