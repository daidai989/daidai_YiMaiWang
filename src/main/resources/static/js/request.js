// 添加请求拦截器
axios.interceptors.request.use(
    function(config) {
        const token = localStorage.getItem("token"); // 从localStorage中获取token值
        if (token) {
            config.headers.Authorization = token;
        }
        return config;
    },
    function(error) {
        return Promise.reject(error);
    }
);

// 添加响应拦截器
axios.interceptors.response.use(
    function(response) {
        return response.data;
    },
    function(error) {
        if (error.response.status === 401) {
            // 未授权错误，跳转到login.html界面
            window.location.href = 'login.html';
        }
        return Promise.reject(error);
    }
);