// import api from "./axiosConfig";

// //login user
// export const loginUser = async (data) => {
//   return api.post("../auth/login", data);
// };

// //signup user
// export const signupUser = async (data) => {
//   return api.post("/InstaClone/src/main/java/com/trueigtech/instaclone/controller/AuthController.java/auth/signup", data);
// };


import axiosInstance from "./axiosConfig";


export const loginApi = (data) =>
  axiosInstance.post("/auth/login", {
    email: data.email,
    password: data.password,
  });

export const signupApi = (data) =>
  axiosInstance.post("/auth/signup", {
    username: data.username,
    email: data.email,
    password: data.password,
  });

// export const loginApi = (data) => {
//   return axiosInstance.post("/auth/login", data);
// };

// export const loginApi = (data) =>
//   axiosInstance.post("/auth/login", {
//     email: data.email,
//     password: data.password
//   });


// export const registerApi = (data) => {
//   return axiosInstance.post("/auth/signup", data);
// };
