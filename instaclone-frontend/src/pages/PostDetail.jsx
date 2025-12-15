import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { getPostById } from "../api/postApi";
import Navbar from "../components/Navbar";

function PostDetail() {
  const { id } = useParams();
  const [post, setPost] = useState(null);

  useEffect(() => {
    getPostById(id).then((res) => setPost(res.data));
  }, [id]);

  const sharePost = () => {
    const link = `http://localhost:3000/post/${id}`;
    navigator.clipboard.writeText(link);
    alert("Post link copied");
  };

  if (!post) return <p>Loading...</p>;

  return (
    <>
      <Navbar />
      <div style={{ width: "500px", margin: "20px auto" }}>
        <h3>{post.user?.username}</h3>
        <img src={post.imageUrl} alt="" width="100%" />
        <p>{post.caption}</p>
        <button onClick={sharePost}>Share</button>
      </div>
    </>
  );
}

export default PostDetail;
