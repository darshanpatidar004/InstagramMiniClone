import api from "./axiosConfig";

// get feed posts
export const fetchFeed = () => {
  return api.get("/feed");
};

// create new post
export const createPost = (postData) => {
  return api.post("/posts", postData);
};

// like post
export const likePost = (postId) => {
  return api.post(`/posts/${postId}/like`);
};

// unlike post
export const unlikePost = (postId) => {
  return api.post(`/posts/${postId}/unlike`);
};

// get single post (for post detail or share)
export const getPostById = (postId) => {
  return api.get(`/posts/${postId}`);
};
