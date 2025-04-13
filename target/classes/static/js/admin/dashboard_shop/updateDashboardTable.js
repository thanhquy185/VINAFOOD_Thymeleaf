import {
  vietnamMoneyFormat,
  numberToVietnamWords,
  getWeeksInMonth,
  getMonthsInYear,
} from "/js/common/otherEvents.js";

// Biến chứa các đối tượng bảng Thống kê doanh thu
let data = [];
let headTable = null;
let bodyTable = null;
let footTable = null;
let footColumn2Table = null;
let footColumn3Table = null;
let footColumn4Table = null;
let totalTextTable = null;

export function updateDashboardTable() {
  // Cập nhật lại biến sau khi đã đè html
  headTable = document.querySelector(
    ".main__data > .main__table.dashboard > thead"
  );
  bodyTable = document.querySelector(
    ".main__data > .main__table.dashboard > tbody"
  );
  footTable = document.querySelector(
    ".main__data > .main__table.dashboard > tfoot"
  );
  totalTextTable = document.querySelector(".main__data > .main__total > span");

  // Biến chứa đối tượng là nút "Xem"
  const seeButton = document.getElementById("see-button-dashboard_shop");
  seeButton.addEventListener("click", (e) => {
    // Loại bỏ giá trị mặc định
    e.preventDefault();

    // Lấy ra giá trị được chọn để cập nhật bảng
    const typeValueSelected = document.querySelector(
      ".main__filter input#type-select"
    ).value;
    const timelineValueSelected = document.querySelector(
      ".main__filter input#timeline-select"
    ).value;
    const timelineDetailValueSelected = document.querySelector(
      ".main__filter input#timeline-detail-select"
    ).value;

    // Nếu 1 trong 3 rỗng thì không thể tạo bảng
    if (
      typeValueSelected &&
      timelineValueSelected &&
      timelineDetailValueSelected
    ) {
      renderDashboardTable(
        typeValueSelected,
        timelineValueSelected,
        timelineDetailValueSelected
      );
    } else {
      headTable.innerHTML = `<tr><th width="100%">${
        typeValueSelected ? typeValueSelected : "Thống kê cửa hàng"
      }</th></tr>`;
      bodyTable.innerHTML = ``;
      footTable.innerHTML = ``;
      totalTextTable.innerHTML = `Đang cập nhật`;
    }
  });
}

// Hàm cập nhật lại dữ liệu cho bảng Thống kê doanh thu
export function renderDashboardTable(
  typeValueSelected,
  timelineValueSelected,
  timelineDetailValueSelected
) {
  console.log("123" + typeValueSelected + "123");
  // Biến chứa thông tin về thời gian sẽ lọc
  let year = 0,
    month = 0;
  if (timelineValueSelected === "Lọc theo năm") {
    // Năm yyyy
    year = timelineDetailValueSelected.slice(4);
  } else {
    // Tháng mm/yyyy
    let times = timelineDetailValueSelected.slice(6).split("/");
    year = times[1];
    month = times[0];
  }

  // Cập nhật lại các cột đầu bảng
  // if(typeValueSelected === "Thống kê Lợi nhuận") {

  // } else if(typeValueSelected === "Thống kê Doanh thu") {

  // }
  if (typeValueSelected === "Thống kê Lợi nhuận") {
    headTable.innerHTML = `
          <tr>
              <th width="10%">${month !== 0 ? "Tuần" : "Tháng"}</th>
              <th width="12%">Từ ngày</th>
              <th width="12%">Đến ngày</th>
              <th width="20%">Doanh thu (VNĐ)</th>
              <th width="20%">Tiền vốn (VNĐ)</th>
              <th width="26%">Lợi nhuận (VNĐ)</th>
          </tr>
        `;
  } else if (typeValueSelected === "Thống kê Doanh thu") {
    headTable.innerHTML = `
          <tr>
              <th width="10%">${month !== 0 ? "Tuần" : "Tháng"}</th>
              <th width="12%">Từ ngày</th>
              <th width="12%">Đến ngày</th>
              <th width="20%">Số đơn hàng</th>
              <th width="20%">Tổng số món ăn bán</th>
              <th width="26%">Doanh thu (VNĐ)</th>
          </tr>
        `;
  } else if (typeValueSelected === "Thống kê Phiếu nhập") {
    headTable.innerHTML = `
          <tr>
              <th width="10%">${month !== 0 ? "Tuần" : "Tháng"}</th>
              <th width="12%">Từ ngày</th>
              <th width="12%">Đến ngày</th>
              <th width="20%">Số phiếu nhập</th>
              <th width="20%">Tổng số món ăn nhập</th>
              <th width="26%">Tiền vốn (VNĐ)</th>
          </tr>
        `;
  }
  // Cập nhật lại các cột cuối bảng
  footTable.innerHTML = `
          <tr class="total">
              <td colspan="3">TỔNG:</td>
              <td class=${
                typeValueSelected === "Thống kê Lợi nhuận" ? "right" : ""
              }>0</td>
              <td class=${
                typeValueSelected === "Thống kê Lợi nhuận" ? "right" : ""
              }>0</td>
              <td class=${
                typeValueSelected === "Thống kê Lợi nhuận" ||
                typeValueSelected === "Thống kê Doanh thu" ||
                typeValueSelected === "Thống kê Phiếu nhập"
                  ? "right"
                  : ""
              }>0</tde>
          </tr>
        `;
  // Cập nhật lại các biến vì đã ấn "Xem" thành công
  footColumn2Table = document.querySelector(
    ".main__data > .main__table.dashboard > tfoot > tr > td:nth-of-type(2)"
  );
  footColumn3Table = document.querySelector(
    ".main__data > .main__table.dashboard > tfoot > tr > td:nth-of-type(3)"
  );
  footColumn4Table = document.querySelector(
    ".main__data > .main__table.dashboard > tfoot > tr > td:nth-of-type(4)"
  );

  // Cập nhật lại dữ liệu cho bảng (Cần xử lý truy vấn dữ liệu chỗ này để tính toán)
  data = [];
  let timeline =
    month !== 0 ? getWeeksInMonth(year, month) : getMonthsInYear(year);
  timeline.forEach((time) => {
    data.push({
      time: time.week ? time.week : time.month,
      start: time.start,
      end: time.end,
      orderNumbers: 0, // Vì chưa xủ lý truy vấn nên mặc định là 0
      bookNumbers: 0, // Vì chưa xủ lý truy vấn nên mặc định là 0
    });
  });

  // Biến tính tổng cho các ô dữ liệu cuối
  let orderNumbers = 0,
    bookNumbers = 0,
    total = 0;
  // Duyệt qua từng dữ liệu rồi gán (cập nhật lại các ô dữ liệu giữa bảng)
  bodyTable.innerHTML = data
    .map((row) => {
      orderNumbers += row.orderNumbers;
      bookNumbers += row.bookNumbers;
      total += row.orderNumbers * row.bookNumbers;

      return `
                  <tr>
                      <td>${row.time}</td>
                      <td>${row.start}</td>
                      <td>${row.end}</td>
                      <td class=${
                        typeValueSelected === "Thống kê Lợi nhuận"
                          ? "right"
                          : ""
                      }>${row.orderNumbers}</td>
                      <td class=${
                        typeValueSelected === "Thống kê Lợi nhuận"
                          ? "right"
                          : ""
                      }>${row.bookNumbers}</td>
                      <td class=${
                        typeValueSelected === "Thống kê Lợi nhuận" ||
                        typeValueSelected === "Thống kê Doanh thu" ||
                        typeValueSelected === "Thống kê Phiếu nhập"
                          ? "right"
                          : ""
                      }>${vietnamMoneyFormat(
        row.orderNumbers * row.bookNumbers
      )}</td>
                  </tr>
              `;
    })
    .join("");

  // Tính lại dữ liệu các dòng cuối
  footColumn2Table.textContent = vietnamMoneyFormat(orderNumbers);
  footColumn3Table.textContent = vietnamMoneyFormat(bookNumbers);
  footColumn4Table.textContent = vietnamMoneyFormat(total);
  totalTextTable.textContent = numberToVietnamWords(total);
}
