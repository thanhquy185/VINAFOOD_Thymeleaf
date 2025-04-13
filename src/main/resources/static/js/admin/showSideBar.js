// Biến chứa đối tượng sidebar
const sidebar = document.getElementById("admin-sidebar");

// Biến chứa icon để ẩn hiển sidebar
const iconToShowSidebar = document.getElementById("show-admin-sidebar");

// Gán sự kiện để có thể ẩn hiện sidebar khi nhấn vào icon
iconToShowSidebar.addEventListener("click", (e) => {
  // Loại bỏ giá trị mặc định
  e.preventDefault();

  // Ẩn hiện sidebar bằng việc thêm hoặc xoá class "hide"
  sidebar.classList.toggle("hide");
});
