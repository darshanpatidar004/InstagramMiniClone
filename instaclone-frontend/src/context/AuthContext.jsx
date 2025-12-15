// import { createContext, useState, useEffect } from "react";
// import { useNavigate } from "react-router-dom";

// export const AuthContext = createContext();

// function AuthProvider({ children }) {
//   const [isLoggedIn, setIsLoggedIn] = useState(false);
//   const navigate = useNavigate();

//   useEffect(() => {
//     const token = localStorage.getItem("token");

//     if (token) {
//       setIsLoggedIn(true);
//     } else {
//       setIsLoggedIn(false);
//     }
//   }, []);

//   const login = (token) => {
//     localStorage.setItem("token", token);
//     setIsLoggedIn(true);
//     navigate("/feed");
//   };

//   const logout = () => {
//     localStorage.removeItem("token");
//     setIsLoggedIn(false);
//     navigate("/login");
//   };

//   return (
//     <AuthContext.Provider value={{ isLoggedIn, login, logout }}>
//       {children}
//     </AuthContext.Provider>
//   );
// }

// export default AuthProvider;


import { createContext, useState } from "react";
import { loginApi } from "../api/authApi";

export const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
  const [user, setUser] = useState(
    JSON.parse(localStorage.getItem("user")) || null
  );

  const login = async (email, password) => {
    const res = await loginApi({ email, password });

    localStorage.setItem("user", JSON.stringify(res.data));
    setUser(res.data);
  };

  const logout = () => {
    localStorage.removeItem("user");
    setUser(null);
  };

  return (
    <AuthContext.Provider value={{ user, login, logout }}>
      {children}
    </AuthContext.Provider>
  );
};
