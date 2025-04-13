import { updateDashboardTable } from "/js/admin/dashboard_shop/updateDashboardTable.js";
import { printDashboardTicket } from "/js/admin/dashboard_shop/printDashboard.js";

//
export function dashboardShopEvents() {
  updateDashboardTable();
  printDashboardTicket();
}
