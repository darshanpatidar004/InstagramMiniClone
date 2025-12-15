// check if user is logged in
export const isLoggedIn = () => {
  const token = localStorage.getItem("token");
  return token !== null && token.length > 0;
};

// get stored token
export const getToken = () => {
  return localStorage.getItem("token");
};

// clear auth data
export const logoutUser = () => {
  localStorage.removeItem("token");
};
