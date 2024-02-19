class AdminResourceManager {
    constructor() {
        const csrfTokenElement = document.querySelector("meta[name='_csrf']");
        this.token = csrfTokenElement ? csrfTokenElement.getAttribute("content") : null;
        const csrfHeaderElement = document.querySelector("meta[name='_csrf_header']");
        this.header = csrfHeaderElement ? csrfHeaderElement.getAttribute("content") : null;

        this.initEvent();
    }

    initEvent() {
        let self = this;
        document.getElementById("deleteButton").addEventListener("click", function () {
            const isDelete = confirm("폐기시 해당 장비를 사용중인 사용자의 장비 또한 해제됩니다.\n폐기를 진행하시겠습니까?");
            if (isDelete) {
                const equipmentId = this.getAttribute("data-id");
                let url = `/admin/resources/${equipmentId}` ;

                fetch(url, {
                    method: 'DELETE',
                    headers: {
                        [self.header]: self.token
                    }
                }).then(res => {
                    if (res.ok) {
                        location.reload();
                    }
                }).catch(error => {
                    alert("폐기에 실패하였습니다.\n잠시후 시도해주세요.");
                    console.log(error);
                });
            }
        });
    }
}