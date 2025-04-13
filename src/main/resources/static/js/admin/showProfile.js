document.getElementById("avatar").addEventListener("click", function () {
  let dropdown = document.getElementById("dropdown");
  dropdown.style.display =
    dropdown.style.display === "block" ? "none" : "block";
});

// document.addEventListener("click", function (event) {
//   let dropdown = document.getElementById("dropdown");
//   let avatar = document.getElementById("avatar");
//   if (!avatar.contains(event.target) && !dropdown.contains(event.target)) {
//     dropdown.style.display = "none";
//   }
// });
