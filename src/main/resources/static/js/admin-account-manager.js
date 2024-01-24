class adminAccountManager {
    constructor() {
        const csrfTokenElement = document.querySelector("meta[name='_csrf']");
        this.token = csrfTokenElement ? csrfTokenElement.getAttribute("content") : null;

        const csrfHeaderElement = document.querySelector("meta[name='_csrf_header']");
        this.header = csrfHeaderElement ? csrfHeaderElement.getAttribute("content") : null;

        this.addEvent();
    }

    addEvent() {
        const self = this;

        document.getElementById("submitButton").addEventListener("click", async function (e) {

            e.preventDefault();
            const closestForm = this.closest("form");
            const spinnerElement = this.querySelector(".spinner-border");
            const userId = document.getElementById("userId").value;
            if (!closestForm.checkValidity()) {
                document.querySelectorAll(".required").forEach(tg => {
                    tg.classList.add("text-danger");
                });
                return alert("필수항목이 누락되었습니다.");
            }

            if (spinnerElement) {
                this.disabled = true;
                spinnerElement.classList.remove("visually-hidden");
            }

            if (await self.validateUsername(userId)) {
                closestForm.submit();
            } else {
                if (spinnerElement) {
                    this.disabled = false;
                    spinnerElement.classList.add("visually-hidden");
                }
                alert("중복된 사용자 ID 입니다.");
            }

        });
    }

    async validateUsername(userId) {
        const self = this;
        const url = `http://localhost:8091/admin/account/validate/id`

        try {
            const response = await fetch(url, {
                method: 'POST',
                headers: {
                    [self.header]: self.token
                },
                body: new URLSearchParams({
                    userId: userId
                })
            });

            if (!response.ok) {
                throw new Error('Network response was not ok');
            }

            const is_validated = await response.json();
            return is_validated;
        } catch (error) {
            console.log(error);
        }

    }

}