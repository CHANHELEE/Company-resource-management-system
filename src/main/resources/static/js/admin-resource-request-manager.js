class AdminResourceRequestManager {
    constructor() {
        const csrfTokenElement = document.querySelector("meta[name='_csrf']");
        this.token = csrfTokenElement ? csrfTokenElement.getAttribute("content") : null;
        const csrfHeaderElement = document.querySelector("meta[name='_csrf_header']");
        this.header = csrfHeaderElement ? csrfHeaderElement.getAttribute("content") : null;

        this.queryParam = {
            page: 0,
            size: 20
        }
        this.isScrollActive = true;
        this.addEvent();
    }

    addEvent() {
        const self = this;
        document.getElementById("testBtn").addEventListener("click", function () {
            self.fetchResources();
        });

        document.getElementById("modalBody").addEventListener("scroll", function () {
            const scrolledHeight = this.scrollTop + this.clientHeight;
            const totalHeight = this.scrollHeight;
            const IS_BOTTOM = scrolledHeight === totalHeight;
            if (IS_BOTTOM) {
                if (self.isScrollActive) {
                    self.fetchResources();
                }
            }
        });

    }

    fetchResources() {
        const self = this;
        // debugger;
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
        console.log(resources);
        console.log("render!!!");
        const tableBody = document.getElementById("resourcesTableBody");
        for (const res of resources.results.content) {
            const tr = document.createElement('tr');
            console.log(res);
            let tds = `<th scope="row">1</th>
                   <td>${res.uniqueNum}</td>
                   <td>${res.name}</td>
                   <td>${res.status === 'UNUSING' ? '가능' : '불가능'}</td>
                   <td>${res.userName === null ? 'X' : res.userName}</td>`
            tr.innerHTML = tds;
            tableBody.appendChild(tr);
        }
        this.setFetchData(resources);
    }

    setFetchData(resources) {
        this.queryParam.page++;
        if (resources.results.numberOfElements < this.queryParam.size) {
            this.isScrollActive = false;
        }
    }
}