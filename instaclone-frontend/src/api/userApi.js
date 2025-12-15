import api from "./axiosConfig";

// follow user
export const followUser = (userId) => {
  return api.post(`/users/${userId}/follow`);
};

// unfollow user
export const unfollowUser = (userId) => {
  return api.post(`/users/${userId}/unfollow`);
};

// get user profile
export const getUserProfile = (userId) => {
  return api.get(`/users/${userId}`);
};
