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

        const submitButton = document.getElementById("submitButton");
        const deleteButton = document.getElementById("deleteButton");

        //직원등록
        if (submitButton) {
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
        if (deleteButton) {
            deleteButton.addEventListener("click", async function () {
                const is_confirm = confirm("id를 삭제하시겠습니까?");
                if (is_confirm) {
                    const userId = this.getAttribute('data-id');
                    const url = `/admin/account/delete/${userId}`
                    try {
                        const response = await fetch(url, {
                            method: 'DELETE',
                            headers: {
                                [self.header]: self.token
                            }
                        })

                        const parsedResponse = await response.json();
                        if (parsedResponse.flag) {
                            location.reload();
                        } else {
                            throw new Error('Network response was not ok');
                        }
                    } catch (error) {
                        console.log(error);
                        alert("ID 삭제 에러입니다.\n관리자에게 문의해주세요.");
                    }
                }
            });
        }
    }

    //ID 중복체크
    async validateUsername(userId) {
        const self = this;
        const url = '/admin/account/validate/id';
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