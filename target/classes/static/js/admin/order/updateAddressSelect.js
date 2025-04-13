//
let provincesInput = document.querySelector(
  ".main__filter input#province-select"
);
let provincesUl = document.querySelector(
  ".main__filter input#province-select + .main__form-group-list"
);
let districtsInput = document.querySelector(
  ".main__filter input#district-select"
);
let districtsUl = document.querySelector(
  ".main__filter input#district-select + .main__form-group-list"
);

//
export function updateAddressSelect() {
  if (provincesInput && provincesUl && districtsInput && districtsUl) {
    fetch(`https://esgoo.net/api-tinhthanh/1/0.htm`, {
      method: "GET",
    })
      .then((response) => {
        if (!response.ok) {
          throw new Error("Xem chi tiết thất bại!");
        }
        return response.json();
      })
      .then((json) => {
        for (let i = 0; i < 63; i++) {
          provincesUl.innerHTML += `
          <li class="main__form-group-item" data-province="${json.data[i].id}">
            <span><i class="fa-solid fa-check"></i></span>&nbsp;&nbsp;${json.data[i].full_name}
          </li>
        `;
        }
      })
      .catch((error) => {
        console.error(error);
        alert("Xem chi tiết thất bại!");
      });
    provincesUl.addEventListener("click", (e) => {
      // Loại bỏ giá trị mặc định
      e.preventDefault();

      // Xóa class selected khỏi tất cả
      for (let j = 0; j < provincesUl.childElementCount; j++) {
        if (e.target != provincesUl.children.item(j)) {
          provincesUl.children.item(j).classList.remove("selected");
        }
      }

      // Thêm class selected cho mục được chọn
      e.target.classList.add("selected");

      //
      const provinceValueSelected = e.target.textContent
        .slice(13)
        .replace(/\s+/g, " ")
        .trim();
      provincesInput.value = "";
      if (
        !provincesUl.children
          .item(0)
          .textContent.slice(13)
          .replace(/\s+/g, " ")
          .trim()
          .includes(provinceValueSelected)
      ) {
        provincesInput.value = provinceValueSelected;
      }

      //
      const provinceIdSelected = e.target.getAttribute("data-province");
      districtsInput.value = "";
      districtsUl.innerHTML = `
      <li class="main__form-group-item selected">
        <span><i class="fa-solid fa-check"></i></span>&nbsp;&nbsp;Chọn Quận / Huyện
      </li>
    `;
      fetch(`https://esgoo.net/api-tinhthanh/2/${provinceIdSelected}.htm`, {
        method: "GET",
      })
        .then((response) => {
          if (!response.ok) {
            throw new Error("Xem chi tiết thất bại!");
          }
          return response.json();
        })
        .then((json) => {
          for (let i = 0; i < json.data.length; i++) {
            districtsUl.innerHTML += `
            <li class="main__form-group-item" data-district="${json.data[i].id}">
              <span><i class="fa-solid fa-check"></i></span>&nbsp;&nbsp;${json.data[i].full_name}
            </li>
          `;
          }
        })
        .catch((error) => {
          console.error(error);
          alert("Xem chi tiết thất bại!");
        });

      districtsUl.addEventListener("click", (e) => {
        // Loại bỏ giá trị mặc định
        e.preventDefault();

        // Xóa class selected khỏi tất cả
        for (let j = 0; j < districtsUl.childElementCount; j++) {
          if (e.target != districtsUl.children.item(j)) {
            districtsUl.children.item(j).classList.remove("selected");
          }
        }

        // Thêm class selected cho mục được chọn
        e.target.classList.add("selected");

        //
        const districtValueSelected = e.target.textContent
          .slice(13)
          .replace(/\s+/g, " ")
          .trim();
        districtsInput.value = "";
        if (
          !districtsUl.children
            .item(0)
            .textContent.slice(13)
            .replace(/\s+/g, " ")
            .trim()
            .includes(districtValueSelected)
        ) {
          districtsInput.value = districtValueSelected;
        }
      });
    });
  }
}

export function updateAddressHaveValueSelect(province, district) {
  fetch(`https://esgoo.net/api-tinhthanh/1/0.htm`, {
    method: "GET",
  })
    .then((response) => {
      if (!response.ok) {
        throw new Error("Xem chi tiết thất bại!");
      }
      return response.json();
    })
    .then((json) => {
      for (let i = 0; i < 63; i++) {
        provincesUl.innerHTML += `
          <li class="main__form-group-item" data-province="${json.data[i].id}">
            <span><i class="fa-solid fa-check"></i></span>&nbsp;&nbsp;${json.data[i].full_name}
          </li>
        `;
      }

      //
      let provinceLi;
      for (let i = 0; i < provincesUl.childElementCount; i++) {
        if (
          provincesUl.children
            .item(i)
            .textContent.slice(13)
            .replace(/\s+/g, " ")
            .trim() == province
        ) {
          provinceLi = provincesUl.children.item(i);
        }
      }

      // Xóa class selected khỏi tất cả
      for (let i = 0; i < provincesUl.childElementCount; i++) {
        if (provinceLi != provincesUl.children.item(i)) {
          provincesUl.children.item(i).classList.remove("selected");
        }
      }

      // Thêm class selected cho mục được chọn
      provinceLi.classList.add("selected");

      // //
      // const provinceValueSelected = provinceLi.textContent
      //   .slice(13)
      //   .replace(/\s+/g, " ")
      //   .trim();
      // provincesInput.value = "";
      // if (
      //   !provincesUl.children
      //     .item(0)
      //     .textContent.slice(13)
      //     .replace(/\s+/g, " ")
      //     .trim()
      //     .includes(provinceValueSelected)
      // ) {
      //   provincesInput.value = provinceValueSelected;
      // }

      //
      const provinceIdSelected = provinceLi.getAttribute("data-province");
      districtsInput.value = "";
      districtsUl.innerHTML = `
        <li class="main__form-group-item selected">
          <span><i class="fa-solid fa-check"></i></span>&nbsp;&nbsp;Chọn Quận / Huyện
        </li>
      `;
      fetch(`https://esgoo.net/api-tinhthanh/2/${provinceIdSelected}.htm`, {
        method: "GET",
      })
        .then((response) => {
          if (!response.ok) {
            throw new Error("Xem chi tiết thất bại!");
          }
          return response.json();
        })
        .then((json) => {
          for (let i = 0; i < json.data.length; i++) {
            districtsUl.innerHTML += `
              <li class="main__form-group-item" data-district="${json.data[i].id}">
                <span><i class="fa-solid fa-check"></i></span>&nbsp;&nbsp;${json.data[i].full_name}
              </li>
            `;
          }

          districtsUl.addEventListener("click", (e) => {
            // Loại bỏ giá trị mặc định
            e.preventDefault();

            // Xóa class selected khỏi tất cả
            for (let j = 0; j < districtsUl.childElementCount; j++) {
              if (e.target != districtsUl.children.item(j)) {
                districtsUl.children.item(j).classList.remove("selected");
              }
            }

            // Thêm class selected cho mục được chọn
            e.target.classList.add("selected");

            //
            const districtValueSelected = e.target.textContent
              .slice(13)
              .replace(/\s+/g, " ")
              .trim();
            districtsInput.value = "";
            if (
              !districtsUl.children
                .item(0)
                .textContent.slice(13)
                .replace(/\s+/g, " ")
                .trim()
                .includes(districtValueSelected)
            ) {
              districtsInput.value = districtValueSelected;
            }
          });

          //
          if (district) {
            //
            districtsInput.value = district;

            //
            let districtLi;
            for (let i = 0; i < districtsUl.childElementCount; i++) {
              if (
                districtsUl.children
                  .item(i)
                  .textContent.slice(13)
                  .replace(/\s+/g, " ")
                  .trim() == district
              ) {
                districtLi = districtsUl.children.item(i);
              }
            }

            // Xóa class selected khỏi tất cả
            for (let j = 0; j < districtsUl.childElementCount; j++) {
              if (districtLi != districtsUl.children.item(j)) {
                districtsUl.children.item(j).classList.remove("selected");
              }
            }

            // Thêm class selected cho mục được chọn
            districtLi.classList.add("selected");
          }
        })
        .catch((error) => {
          console.error(error);
          alert("Xem chi tiết thất bại!");
        });
    })
    .catch((error) => {
      console.error(error);
      alert("Xem chi tiết thất bại!");
    });
}
