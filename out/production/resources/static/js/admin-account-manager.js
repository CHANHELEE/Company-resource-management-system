class adminAccountManager {
    constructor() {
        const csrfTokenElement = document.querySelector("meta[name='_csrf']");
        this.token = csrfTokenElement ? csrfTokenElement.getAttribute("content") : null;

        const csrfHeaderElement = document.querySelector("meta[name='_csrf_header']");
        this.header = csrfHeaderElement ? csrfHeaderElement.getAttribute("content") : null;

        this.initEvent();
    }

    initEvent() {
        const self = this;

        const submitButton = document.getElementById("submitButton");
        const updateButton = document.getElementById("updateButton");
        const deleteButtons = document.querySelectorAll(".deleteButton");

        //직원등록
        if (submitButton) {
            submitButton.addEventListener("click", async function (e) {

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
                debugger;
                const is_validated = await self.validateUsername(userId);

                switch (is_validated) {
                    case true:
                        closestForm.submit();
                        break;
                    case false:
                        if (spinnerElement) {
                            this.disabled = false;
                            spinnerElement.classList.add("visually-hidden");
                        }
                        alert("중복된 사용자 ID 입니다.");
                        break;
                    default:
                        alert("ID 검증 에러입니다.\n관리자에게 문의해주세요.");
                        break;
                }
            });
        }

        //직원삭제
        if (deleteButtons) {
            deleteButtons.forEach(deleteButton => {
                deleteButton.addEventListener("click", async function () {
                    const is_confirm = confirm("ID를 삭제하시겠습니까?");
                    if (is_confirm) {
                        const spinnerElement = this.querySelector(".spinner-border");
                        const userId = this.getAttribute('data-id');
                        debugger;
                        const url = `/admin/accounts/${userId}`

                        if (spinnerElement) {
                            this.disabled = true;
                            spinnerElement.classList.remove("visually-hidden");
                        }

                        try {
                            const response = await fetch(url, {
                                method: 'DELETE',
                                headers: {
                                    [self.header]: self.token
                                }
                            })

                            const parsedResponse = await response.json();
                            if (parsedResponse.flag) {
                                // location.reload();
                                debugger;
                                window.location.href = parsedResponse.result;
                            } else {
                                throw new Error('Network response was not ok');
                            }
                        } catch (error) {
                            console.log(error);
                            alert("ID 삭제 에러입니다.\n관리자에게 문의해주세요.");
                        }
                    }
                })
            });
        }

        if (updateButton) {
            updateButton.addEventListener('click', function (e) {
                e.preventDefault();
                const spinnerElement = this.querySelector(".spinner-border");
                const closestForm = this.closest("form");
                const is_confirm = confirm("ID를 수정하시겠습니까?")

                if (is_confirm) {
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
                    closestForm.submit();
                }

            })
        }
    }

    //ID 중복체크
    async validateUsername(userId) {
        const self = this;
        const url = '/admin/accounts/validate/id';
        debugger;
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

            const parsedResponse = await response.json();
            return parsedResponse.flag;
        } catch (error) {
            console.log(error);
        }

    }

}