class AdminResourceOwnershipManager {
    constructor() {
        const csrfTokenElement = document.querySelector("meta[name='_csrf']");
        this.token = csrfTokenElement ? csrfTokenElement.getAttribute("content") : null;

        const csrfHeaderElement = document.querySelector("meta[name='_csrf_header']");
        this.header = csrfHeaderElement ? csrfHeaderElement.getAttribute("content") : null;

        this.initEvents();
    }

    initEvents() {
        document.querySelectorAll(".deleteButton").forEach(function (deleteButton) {
            deleteButton.addEventListener('click', function () {
                const isDelete = confirm("해당 장비를 미사용상태로 변경하시겠습니까?");
                if (isDelete) {
                    debugger;
                    const accountId = this.getAttribute("accountId");
                    const equipmentId = this.getAttribute("equipmentId");
                    const url = '/admin/accounts/' + accountId + '/resources/' + equipmentId;
                    fetch(url, {
                        method: 'PUT',
                        headers: {
                            [self.header]: self.token
                        }
                    }).then(res => {
                        if (res.ok) {
                            location.reload();
                        }
                    }).catch(error => {
                        console.log(error);
                        return alert("해당 장비 상태변경에 실패하였습니다.")
                    });
                }
            });
        });
    }
}