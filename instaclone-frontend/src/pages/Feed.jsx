import { useEffect, useState } from "react";
import { fetchFeed } from "../api/postApi";
import PostCard from "../components/PostCard";
import Navbar from "../components/Navbar";

function Feed() {
  const [posts, setPosts] = useState([]);

  useEffect(() => {
    fetchFeed().then((res) => setPosts(res.data));
  }, []);

  return (
    <>
      <Navbar />
      <div style={{ width: "500px", margin: "20px auto" }}>
        {posts.length === 0 && <p>No posts to show</p>}

        {posts.map((post) => (
          <PostCard key={post.id} post={post} />
        ))}
      </div>
    </>
  );
}

export default Feed;
