document.getElementById("add-to-cart-button").addEventListener("click",
    () => {
    this.disabled = true;
    this.innerHTML = "Добавено";
    console.log("added");
});