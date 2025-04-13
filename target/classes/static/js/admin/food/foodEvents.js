import { filterEvents } from "/js/admin/food/filterFood.js";
import { previewImage } from "/js/admin/food/previewImage.js";

// Hàm tổng hợp các sự kiện của mục "Người dùng"
export function foodEvents() {
  filterEvents();
  previewImage();
}
