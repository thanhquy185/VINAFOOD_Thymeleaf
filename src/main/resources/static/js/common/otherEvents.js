// Hàm thay đổi định dạng khi thẻ giá trị của thẻ sai định dạng
export function changeFormatWhenValidateForm(fields, errors) {
  fields.forEach((field) => {
    //
    document.getElementById(`error-${field}`).innerText = "";
    document.getElementById(`error-${field}`).style.opacity = "0";
    // document.getElementById(`error-${field}`).style.display = "none";
    document.querySelector(
      `:where(.dialog__form-group, .form__form-group):has(#error-${field}) label`
    ).style.color = "black";
    if (
      document.querySelector(
        `:where(.dialog__form-group, .form__form-group):has(#error-${field}) label > span`
      )
    ) {
      document.querySelector(
        `:where(.dialog__form-group, .form__form-group):has(#error-${field}) label > span`
      ).style.color = "red";
    }
    if (
      document.querySelector(
        `:where(.dialog__form-group, .form__form-group):has(#error-${field}) i.error-icon`
      )
    ) {
      document.querySelector(
        `:where(.dialog__form-group, .form__form-group):has(#error-${field}) i.error-icon`
      ).style.opacity = "0";
    }
    if (
      document.querySelector(
        `:where(.dialog__form-group, .form__form-group):has(#error-${field}) i.icon`
      )
    ) {
      document.querySelector(
        `:where(.dialog__form-group, .form__form-group):has(#error-${field}) i.icon`
      ).style.opacity = "1";
    }
    document.querySelector(
      `:where(.dialog__form-group, .form__form-group):has(#error-${field}) :where(input, select)`
    ).style.borderColor = "black";

    //
    for (let i = 0; i < errors.length; i++) {
      if (field === errors[i].field) {
        document.getElementById(`error-${errors[i].field}`).innerText =
          errors[i].defaultMessage;
        // document.getElementById(`error-${errors[i].field}`).style.display =
        //   "block";
        document.getElementById(`error-${errors[i].field}`).style.opacity = "1";
        document.querySelector(
          `:where(.dialog__form-group, .form__form-group):has(#error-${errors[i].field}) label`
        ).style.color = "red";
        if (
          document.querySelector(
            `:where(.dialog__form-group, .form__form-group):has(#error-${field}) label > span`
          )
        ) {
          document.querySelector(
            `:where(.dialog__form-group, .form__form-group):has(#error-${field}) label > span`
          ).style.color = "red";
        }
        if (
          document.querySelector(
            `:where(.dialog__form-group, .form__form-group):has(#error-${errors[i].field}) i.error-icon`
          )
        ) {
          document.querySelector(
            `:where(.dialog__form-group, .form__form-group):has(#error-${errors[i].field}) i.error-icon`
          ).style.opacity = "1";
        }
        if (
          document.querySelector(
            `:where(.dialog__form-group, .form__form-group):has(#error-${errors[i].field}) i.icon`
          )
        ) {
          document.querySelector(
            `:where(.dialog__form-group, .form__form-group):has(#error-${errors[i].field}) i.icon`
          ).style.opacity = "0";
        }
        document.querySelector(
          `:where(.dialog__form-group, .form__form-group):has(#error-${errors[i].field}) :where(input, select)`
        ).style.borderColor = "red";
      }
    }
  });
}

// Hàm định dạng việc hiển thị tiền VNĐ
export function vietnamMoneyFormat(money) {
  return money.toLocaleString("vi-VN");
}

// Hàm đọc tiền theo kiểu Việt
export function numberToVietnamWords(n) {
  if (n === 0) return "Không đồng";

  let laSoAm = false;
  if (n < 0) {
    n *= -1;
    laSoAm = true;
  }

  const donVi = [
    "",
    "một",
    "hai",
    "ba",
    "bốn",
    "năm",
    "sáu",
    "bảy",
    "tám",
    "chín",
  ];
  const hangChuc = [
    "",
    "mười",
    "hai mươi",
    "ba mươi",
    "bốn mươi",
    "năm mươi",
    "sáu mươi",
    "bảy mươi",
    "tám mươi",
    "chín mươi",
  ];
  const hangTram = [
    "",
    "một trăm",
    "hai trăm",
    "ba trăm",
    "bốn trăm",
    "năm trăm",
    "sáu trăm",
    "bảy trăm",
    "tám trăm",
    "chín trăm",
  ];

  function docBaChuSo(so) {
    let tram = Math.floor(so / 100);
    let chuc = Math.floor((so % 100) / 10);
    let donvi = so % 10;
    let result = "";

    if (tram > 0) result += hangTram[tram] + " ";
    if (chuc > 1) {
      result += hangChuc[chuc] + " ";
      if (donvi > 0) result += donVi[donvi];
    } else if (chuc === 1) {
      result += "mười ";
      if (donvi > 0 && donvi !== 5) result += donVi[donvi];
      else if (donvi === 5) result += "lăm";
    } else {
      if (donvi > 0) result += (tram > 0 ? "lẻ " : "") + donVi[donvi];
    }
    return result.trim();
  }

  function docHangTrieu(so) {
    let trieu = Math.floor(so / 1_000_000);
    let nghin = Math.floor((so % 1_000_000) / 1_000);
    let tram = so % 1_000;
    let result = "";

    if (trieu > 0) result += docBaChuSo(trieu) + " triệu ";
    if (nghin > 0) result += docBaChuSo(nghin) + " nghìn ";
    if (tram > 0) result += docBaChuSo(tram);

    return result.trim();
  }

  return (
    (laSoAm
      ? "Âm " + docHangTrieu(n)
      : " " +
        docHangTrieu(n).charAt(0).toUpperCase() +
        docHangTrieu(n).slice(1)) + " đồng"
  );
}

// Hàm chuyển từ định dạng yyyy-mm-dd sang dd-mm-yyyy
export function formatDate(date) {
  const dateSplit = date.split("T");

  // Năm tháng ngày
  let year = dateSplit[0].slice(0, 4);
  let month = dateSplit[0].slice(5, 7);
  let day = dateSplit[0].slice(8);

  // Giờ phút giây

  return `${day}/${month}/${year} ${dateSplit[1]}`;
}

// Hàm chuyển từ định dạng yyyy-mm-dd hh:mm:ss sang dd-mm-yyyy
export function formatDate1(date) {
  let day = date.getDate().toString().padStart(2, "0");
  let month = (date.getMonth() + 1).toString().padStart(2, "0");
  let year = date.getFullYear().toString().padStart(4, "0");
  return `${day}/${month}/${year}`;
}

export function formatDate2(date) {
  const dateSplit = String(date).split(" ");

  let day = dateSplit[0].toString().slice(-2);
  let month = dateSplit[0].toString().slice(5, 7);
  let year = dateSplit[0].toString().slice(0, 4);

  return `${day}/${month}/${year} ${dateSplit[1]} `;
}

// Hàm lấy ra danh sách các tuần
export function getWeeksInMonth(year, month) {
  // Chuyển tháng về dạng 0-11 cho JS
  month = month - 1;

  let weeks = [];
  // Ngày đầu tháng
  let firstDay = new Date(year, month, 1);
  // Ngày cuối tháng
  let lastDay = new Date(year, month + 1, 0);
  // Ngày duyệt hiện tại
  let current = new Date(firstDay);

  while (current <= lastDay) {
    // Ngày đầu tuần
    let startOfWeek = new Date(current);
    // Ngày cuối tuần
    let endOfWeek = new Date(current);
    endOfWeek.setDate(startOfWeek.getDate() + 6);

    // Đảm bảo endOfWeek không vượt quá ngày cuối tháng
    if (endOfWeek > lastDay) {
      endOfWeek = lastDay;
    }

    // Thêm vào danh sách
    weeks.push({
      week: weeks.length + 1,
      start: formatDate1(startOfWeek),
      end: formatDate1(endOfWeek),
    });

    // Chuyển sang ngày đầu tiên của tuần tiếp theo
    current.setDate(current.getDate() + 7);
  }

  return weeks;
}

// Hàm lấy ra danh sách các tháng
export function getMonthsInYear(year) {
  let months = [];

  for (let month = 0; month < 12; month++) {
    // Ngày đầu tháng
    let start = new Date(year, month, 1);
    // Ngày cuối tháng
    let end = new Date(year, month + 1, 0);

    // Thêm vào danh sách
    months.push({
      month: month + 1,
      start: formatDate1(start),
      end: formatDate1(end),
    });
  }

  return months;
}
