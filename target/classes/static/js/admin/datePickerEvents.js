// Hàm hiện date picker khi nhấn vào (hỗ trợ cho việc hiệu ứng)
export function clickToShowDatePicker(id) {
  document.getElementById(id).addEventListener("click", function (e) {
    // Loại bỏ giá trị mặc định
    e.preventDefault();

    this.showPicker();
  });
}

// Hàm kiểm tra nếu thẻ input có type="date" đang có giá trị là "dd/mm/yyyy"
export function defaultDateSelected(id) {
  const date = document.getElementById(id);

  date.addEventListener("change", (e) => {
    if (!date.value) {
      date.classList.remove("hasValidDate");
    } else {
      date.classList.add("hasValidDate");
    }
  });
}
