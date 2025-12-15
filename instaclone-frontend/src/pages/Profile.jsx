import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { getUserProfile, followUser, unfollowUser } from "../api/userApi";
import Navbar from "../components/Navbar";

function Profile() {
  const { id } = useParams();
  const [user, setUser] = useState(null);
  const [following, setFollowing] = useState(false);

  useEffect(() => {
    getUserProfile(id).then((res) => setUser(res.data));
  }, [id]);

  const handleFollow = async () => {
    await followUser(id);
    setFollowing(true);
  };

  const handleUnfollow = async () => {
    await unfollowUser(id);
    setFollowing(false);
  };

  if (!user) return <p>Loading...</p>;

  return (
    <>
      <Navbar />
      <div style={{ width: "500px", margin: "20px auto" }}>
        <h2>{user.username}</h2>
        <p>Followers: {user.followers.length}</p>
        <p>Following: {user.following.length}</p>

        {following ? (
          <button onClick={handleUnfollow}>Unfollow</button>
        ) : (
          <button onClick={handleFollow}>Follow</button>
        )}
      </div>
    </>
  );
}

export default Profile;
