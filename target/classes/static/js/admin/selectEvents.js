//
const formGroup = document.querySelectorAll(".form-group.filter.select");

function selectCommon(list, input) {
  // Duyệt qua các mục khi chọn mục và cập nhật vào input
  for (let i = 0; i < list.childElementCount; i++) {
    list.children.item(i).addEventListener("click", () => {
      // Xóa class selected khỏi tất cả
      for (let j = 0; j < list.childElementCount; j++) {
        if (i != j) {
          list.children.item(j).classList.remove("selected");
        }
      }

      // Thêm class selected cho mục được chọn
      list.children.item(i).classList.add("selected");

      // Cập nhật nội dung vào input (loại bỏ thẻ span và 2 khoảng trắng)
      if (input.id === "timeline-select") {
        input.value = list.children
          .item(i)
          .textContent.slice(13)
          .replace(/\s+/g, " ")
          .trim();
      } else {
        input.value = list.children.item(i).textContent.slice(2);
      }

      // Nếu là chọn mục mặc định (cho hiển thị placeholder)
      if (i == 0) {
        input.value = "";
      }
    });
  }
}

//
export function selectEvent01() {
  formGroup.forEach((form) => {
    const input = form.children.item(1);
    const list = form.children.item(2);

    let hideTimeout;

    // Hiển thị danh sách khi hover vào form-group
    form.addEventListener("mouseenter", () => {
      clearTimeout(hideTimeout);
      setTimeout(() => {
        list.style.display = "block";
      }, 20);
    });

    // Không ẩn danh sách khi di chuột vào danh sách
    list.addEventListener("mouseenter", () => {
      clearTimeout(hideTimeout);
      list.style.display = "block";
    });

    // Đặt thời gian chờ trước khi ẩn danh sách
    form.addEventListener("mouseleave", () => {
      hideTimeout = setTimeout(() => {
        list.style.display = "none";
      }, 100);
    });

    // Duyệt qua các mục khi chọn mục và cập nhật vào input
    for (let i = 0; i < list.childElementCount; i++) {
      list.children.item(i).addEventListener("click", () => {
        // Xóa class selected khỏi tất cả
        for (let j = 0; j < list.childElementCount; j++) {
          if (i != j) {
            list.children.item(j).classList.remove("selected");
          }
        }

        // Thêm class selected cho mục được chọn
        list.children.item(i).classList.add("selected");

        // Cập nhật nội dung vào input (loại bỏ thẻ span và 2 khoảng trắng)
        input.value = list.children
          .item(i)
          .textContent.slice(13)
          .replace(/\s+/g, " ")
          .trim();

        // Nếu là chọn mục mặc định (cho hiển thị placeholder)
        if (i == 0) {
          input.value = "";
        }
      });
    }
  });
}

// Nếu không chọn mục ban đầu thì thay đổi định dạng
export function selectEvent02(select) {
  // - Thay đổi khi chọn mục
  if (select.value !== "") {
    select.classList.add("changed");
  } else {
    select.classList.remove("changed");
  }

  select.addEventListener("change", function (e) {
    // - Loại bỏ giá trị mặc định
    e.preventDefault();

    // - Thay đổi khi chọn mục
    if (select.value !== "") {
      select.classList.add("changed");
    } else {
      select.classList.remove("changed");
    }
  });
}

// Cập nhật giá trị đã được chọn đã lưu từ Session Storage
export function selectEvent03(ul, value) {
  //
  const ulSort = document.querySelectorAll(ul);

  //
  ulSort.forEach((li) => {
    li.classList.remove("selected");
  });

  //
  if (value === "") {
    ulSort.item(0).classList.add("selected");
  } else {
    ulSort.forEach((li) => {
      const textInLi = String(li.textContent).slice(13).replace(/\s+/g, " ");
      if (textInLi.includes(value)) {
        li.classList.add("selected");
      }
    });
  }
}

// Hàm ...
export function selectEvent04() {
  // Biến giữ các đối tượng liên quan đến select cho việc lọc thống kê
  const typeInputSelect = document.querySelector(
    ".main__filter input#type-select"
  );
  const timelineInputSelect = document.querySelector(
    ".main__filter input#timeline-select"
  );
  const timelineDetailInputSelect = document.querySelector(
    ".main__filter input#timeline-detail-select"
  );
  const typeUlSelect = document.querySelector(
    ".main__filter .main__form-group:has(input#type-select) ul"
  );
  const timelineUlSelect = document.querySelector(
    ".main__filter .main__form-group:has(input#timeline-select) ul"
  );
  const timelineDetailUlSelect = document.querySelector(
    ".main__filter .main__form-group:has(input#timeline-detail-select) ul"
  );

  //
  typeUlSelect.addEventListener("click", (e) => {
    // Loại bỏ giá trị mặc định
    e.preventDefault();

    //
    if (
      typeInputSelect.value === "Thống kê Lợi nhuận" ||
      typeInputSelect.value === "Thống kê Doanh thu" ||
      typeInputSelect.value === "Thống kê Phiếu nhập"
    ) {
      timelineUlSelect.innerHTML = `
        <li class="main__form-group-item selected">
          <span><i class="fa-solid fa-check"></i></span>&nbsp;&nbsp;Chọn Mốc
          thời gian
        </li>
        <li class="main__form-group-item">
          <span><i class="fa-solid fa-check"></i></span>&nbsp;&nbsp;Lọc theo năm
        </li>
        <li class="main__form-group-item">
          <span><i class="fa-solid fa-check"></i></span>&nbsp;&nbsp;Lọc theo
          tháng
        </li>
      `;
      timelineInputSelect.value = "";
      timelineDetailUlSelect.innerHTML = `
        <li class="main__form-group-item selected">
          <span><i class="fa-solid fa-check"></i></span>&nbsp;&nbsp;Chọn Thời gian cụ thể
        </li>
      `;
      timelineDetailInputSelect.value = "";

      // Tuỳ chỉnh thời gian khi "lọc theo năm" hoặc "lọc theo tháng"
      const yearStart = 2020,
        yearEnd = 2030;
      let timelineDetailYearFormat = `
    <li class="main__form-group-item selected">
      <span><i class="fa-solid fa-check"></i></span>&nbsp;&nbsp;Chọn Thời gian cụ thể
    </li>`;
      for (let year = yearStart; year <= yearEnd; year++) {
        timelineDetailYearFormat += `<li class="main__form-group-item"><span><i class="fa-solid fa-check"></i></span>&nbsp;&nbsp;Năm ${String(
          year
        ).padStart(4, "0")}</li>`;
      }
      let timelineDetailMonthFormat = `
    <li class="main__form-group-item selected">
      <span><i class="fa-solid fa-check"></i></span>&nbsp;&nbsp;Chọn Thời gian cụ thể
    </li>`;
      for (let year = yearStart; year <= yearEnd; year++) {
        for (let month = 1; month <= 12; month++) {
          timelineDetailMonthFormat += `<li class="main__form-group-item"><span><i class="fa-solid fa-check"></i></span>&nbsp;&nbsp;Tháng ${String(
            month
          ).padStart(2, "0")}/${String(year).padStart(4, "0")}</li>`;
        }
      }

      // Gán sự kiện cho "Khoảng thời gian"
      selectCommon(timelineUlSelect, timelineInputSelect);
      timelineUlSelect.addEventListener("click", (e) => {
        // Loại bỏ giá trị mặc định
        e.preventDefault();

        // Cập nhật lại "Thời gian cụ thể"
        timelineDetailInputSelect.value = "";

        // Tuỳ theo giá trị "Khoảng thời gian" mà cập nhật tương ứng
        if (timelineInputSelect.value) {
          if (timelineInputSelect.value === "Lọc theo năm") {
            timelineDetailUlSelect.innerHTML = timelineDetailYearFormat;
          } else if (timelineInputSelect.value === "Lọc theo tháng") {
            timelineDetailUlSelect.innerHTML = timelineDetailMonthFormat;
          }
          selectCommon(timelineDetailUlSelect, timelineDetailInputSelect);
        } else {
          timelineDetailUlSelect.innerHTML = `
          <li class="main__form-group-item selected">
            <span><i class="fa-solid fa-check"></i></span>&nbsp;&nbsp;Chọn Thời gian cụ thể
          </li>
        `;
          timelineDetailUlSelect.value = "";
        }
      });
    } else {
      timelineUlSelect.innerHTML = `
        <li class="main__form-group-item selected">
          <span><i class="fa-solid fa-check"></i></span>&nbsp;&nbsp;Chọn Mốc
          thời gian
        </li>
       `;
      timelineInputSelect.value = "";
    }
  });
}

//
// fetch(`/admin/users/detail/${userIdSelected}`, { method: "GET" })
// .then((response) => {
//   if (!response.ok) {
//     throw new Error("Xem chi tiết thất bại!");
//   }
//   return response.json();
// })
// .then((json) => {
//   console.log(json.data.id);

//   // Điều hướng về danh sách sản phẩm
//   window.location.href = `/admin/users/detail/${json.data.id}`;
//   //   window.location.reload();

//   // Hiển thị thông báo thành công
//   //   alert(json.data.message);

//   // Điều hướng về danh sách sản phẩm
//   //   window.location.href = "/admin/users";
//   //   window.location.reload();
// })
// .catch((error) => {
//   console.error(error);
//   alert("Xem chi tiết thất bại!");
// });
