export function showToast(type, content, time, top, right) {
  const toast = document.createElement("dialog");
  toast.classList.add("toast");
  if (top && right) {
    toast.classList.add("top-right");
    toast.style.top = `${top}%`;    
    toast.style.right = `${right}%`;
  }
  toast.classList.add(`${type}`);
  toast.innerHTML = `
        <img src="/assets/images/others/${type}-toast.png" alt="toast-icon" class="toast__image" />
        <p class="toast__content">${content}</p>
        <div class="toast__progress"></div>
    `;

  document.body.appendChild(toast);
  toast.showModal();

  // Delay để kích hoạt hiệu ứng xuất hiện
  setTimeout(() => {
    toast.classList.add("show");
  }, 10);

  const progress = document.querySelector(".toast__progress");
  progress.style.transition = `width ${time}s linear`;
  setTimeout(() => {
    progress.style.width = "100%";
  }, 10);

  setTimeout(() => {
    toast.classList.remove("show");
    toast.classList.add("hide");

    setTimeout(() => {
      toast.remove();
    }, 500);
  }, time * 1000);
}
