import { filterEvents } from "/js/admin/user/filterUser.js";
import { previewImage } from "/js/admin/user/previewImage.js";
import { createValidAddress } from "/js/admin/user/createValidAddress.js";
import { inputDateEvents } from "/js/admin/user/inputDateEvents.js";

// Hàm tổng hợp các sự kiện của mục "Người dùng"
export function userEvents() {
  filterEvents();
  previewImage();
  createValidAddress();
  inputDateEvents();
}
