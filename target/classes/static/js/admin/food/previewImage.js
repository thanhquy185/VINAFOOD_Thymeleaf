// Hàm cho phép xem trước ảnh khi thêm và cập nhật món ăn
export function previewImage() {
  // Thêm món ăn
  const inputFileWhenAdd = document.getElementById("add-food-image");
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

  // Cập nhật món ăn
  const inputFileWhenUpdate = document.getElementById("update-food-image");
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
