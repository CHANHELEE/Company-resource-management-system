class spinnerManager {
    constructor() {
        this.addEvent();
    }

    addEvent() {
        document.getElementById("syncSubmitButton").addEventListener("click", function (e) {
            e.preventDefault();
            const closestForm = this.closest("form");
            const spinnerElement = this.querySelector(".spinner-border");

            if (spinnerElement) {
                this.disabled = true;
                spinnerElement.classList.remove("visually-hidden");
            }
            closestForm.submit();
        });
    }
}