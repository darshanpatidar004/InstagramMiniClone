import axios from "axios";

const axiosInstance = axios.create({
  baseURL: "http://localhost:8080/api",
  headers: {
    "Content-Type": "application/json",
  },
});

//  DO NOT attach token for login/signup
axiosInstance.interceptors.request.use((config) => {
  const user = JSON.parse(localStorage.getItem("user"));

  if (
    user?.token &&
    !config.url.includes("/auth/login") &&
    !config.url.includes("/auth/signup")
  ) {
    config.headers.Authorization = `Bearer ${user.token}`;
  }

  return config;
});

export default axiosInstance;
