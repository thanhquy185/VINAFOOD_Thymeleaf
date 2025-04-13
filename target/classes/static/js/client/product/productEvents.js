import { menuFormat, filterEvents } from "/js/client/product/menuFormat.js";
import { formatValue } from "/js/client/product/formatValue.js";

// Tổng hợp lại các hàm sự kiện
export function productEvents() {
  menuFormat();
  filterEvents();
  formatValue();
}
