class AdminResourceRequestManager {
    constructor() {
        const csrfTokenElement = document.querySelector("meta[name='_csrf']");
        this.token = csrfTokenElement ? csrfTokenElement.getAttribute("content") : null;
        const csrfHeaderElement = document.querySelector("meta[name='_csrf_header']");
        this.header = csrfHeaderElement ? csrfHeaderElement.getAttribute("content") : null;

        this.queryParam = {
            page: 0,
            size: 15
        }

        this.isScrollActive = true;
        this.initEvent();
    }

    initEvent() {
        const self = this;

        document.getElementById("modalBody").addEventListener("scroll", function () {
            const scrolledHeight = this.scrollTop + this.clientHeight;
            const totalHeight = this.scrollHeight;
            const IS_BOTTOM = Math.abs(scrolledHeight - totalHeight) <= 5;
            if (IS_BOTTOM && self.isScrollActive) {
                self.fetchResources();
            }
        });

        document.getElementById("resourceSearchModal").addEventListener("show.bs.modal", function () {
            if (self.queryParam.page === 0) {
                self.fetchResources();
            }
        });

        document.getElementById("approveButton").addEventListener("click", function (e) {
            if (!document.getElementById("equipmentId").value) {
                return alert("배정자원을 지정해주세요.");
            }
            document.getElementById("requestStatus").value = 'APPROVED';
            document.getElementById("requestForm").submit();
        });

        document.getElementById("rejectButton").addEventListener("click", function (e) {
            document.getElementById("requestStatus").value = 'REJECTED';
            if (!document.getElementById("rejectReason").value) {
                return alert("거절사유를 입력해 주세요.");
            }
            document.getElementById("requestForm").submit();
        });

    }

    fetchResources() {
        const self = this;
        let url = new URL("/admin/resource-requests/search", window.location.origin);
        console.log(this.queryParam)
        for (let key in this.queryParam) {
            if (Object.hasOwnProperty.call(this.queryParam, key)) {
                const value = this.queryParam[key];
                url.searchParams.append(key, value);
            }
        }

        console.log(url.toString());

        fetch(url.toString(), {
            method: "GET",
            headers: {
                [self.header]: self.token
            },

        }).then(res => res.json())
            .then(resJson => self.render(resJson));
    }

    render(resources) {
        const tableBody = document.getElementById("resourcesTableBody");
        for (const res of resources.results.content) {
            const tr = document.createElement('tr');
            let tds = `
                   <th scope="row">1</th>
                   <td>${res.uniqueNum}</td>
                   <td>${res.name}</td>
                   <td>${res.status === 'DISCARDED' ? '폐기' : res.status === 'UNUSING' ? '미사용중' : '사용중' }</td>
                   <td>${res.userName === null ? 'X' : res.userName}</td>`;
            tr.dataset.id = res.id;
            tr.dataset.equipmentName = res.name;
            if (res.status === 'UNUSING') {
                tr.classList.add('clickable-row');
            }
            tr.innerHTML = tds;

            tableBody.appendChild(tr);
        }
        document.querySelectorAll(".clickable-row").forEach(function (row) {
            row.addEventListener("click", function () {
                const equipmentId = row.getAttribute("data-id");
                const equipmentName = row.getAttribute("data-equipment-name");
                const modal = bootstrap.Modal.getInstance(document.getElementById("resourceSearchModal"));

                document.getElementById("equipmentId").value = equipmentId;
                document.getElementById("equipmentName").value = equipmentName;
                modal.hide();
            })
        });
        this.setFetchConditions(resources);
    }

    setFetchConditions(resources) {
        this.queryParam.page++;
        if (resources.results.numberOfElements < this.queryParam.size) {
            this.isScrollActive = false;
        }
    }
}