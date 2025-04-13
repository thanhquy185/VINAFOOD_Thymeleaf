import { formatValue } from "/js/client/payment/formatValue.js";
import { showAddressSelectDialog } from "/js/common/updateAddressSelect.js";

//
export function paymentEvents() {
  //
  formatValue();

  // Thiết lập sự kiện "thay đổi" địa chỉ
  showAddressSelectDialog("receiver-address", "create-address");
}
