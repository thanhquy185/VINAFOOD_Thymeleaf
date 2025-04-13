// Hàm cho phép xem trước ảnh khi thêm và cập nhật người dùng
export function previewImage() {
  // Thêm người dùng
  const inputFileWhenAdd = document.getElementById("add-user-image");
  if (inputFileWhenAdd) {
    inputFileWhenAdd.addEventListener("change", (e) => {
      // Loại bỏ giá trị mặc định
      e.preventDefault();

      //
      const file = e.target.files[0];
      if (file) {
        const reader = new FileReader();
        reader.onload = function (e) {
          document.querySelector(".main-crud__form-group.image img").src =
            e.target.result;
        };
        reader.readAsDataURL(file);
      }
    });
  }

  // Cập nhật người dùng
  const inputFileWhenUpdate = document.getElementById("update-user-image");
  if (inputFileWhenUpdate) {
    inputFileWhenUpdate.addEventListener("change", (e) => {
      // Loại bỏ giá trị mặc định
      e.preventDefault();

      //
      const file = e.target.files[0];
      if (file) {
        const reader = new FileReader();
        reader.onload = function (e) {
          document.querySelector(".main-crud__form-group.image img").src =
            e.target.result;
        };
        reader.readAsDataURL(file);
      }
    });
  }
}
