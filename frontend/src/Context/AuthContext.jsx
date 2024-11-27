import React, { createContext, useContext, useEffect, useState } from "react";

// Create context for authentication
export const AuthContext = createContext();

// Custom hook to use authentication context
export const useAuthContext = () => {
  return useContext(AuthContext);
};

// Provider component to manage authentication state
export const AuthContextProvider = ({ children }) => {
  // State to store the authentication token
  const [token, setToken] = useState(() => {
    // Load token from localStorage, or return null if not available
    const storedToken = localStorage.getItem("causeBank-token");
    return storedToken || null;
  });

  // State to store authenticated user data
  const [authUser, setAuthUser] = useState(() => {
    // Load user from localStorage, or return null if not available
    const storedUser = localStorage.getItem("causeBank-user");
    const storedTimestamp = localStorage.getItem("causebank-user-timestamp");

    const now = Date.now();
    const oneDayInMillis = 24 * 60 * 60 * 1000; // 24 hours in milliseconds

    if (
      storedUser &&
      storedTimestamp &&
      now - storedTimestamp < oneDayInMillis
    ) {
      return JSON.parse(storedUser);
    } else {
      // Clear localStorage if timestamp is older than 24 hours
      localStorage.removeItem("causeBank-user");
      localStorage.removeItem("causebank-user-timestamp");
      return null;
    }
  });

  // Function to update authUser and token with the latest user data
  const updateUserProfile = (userData) => {
    setAuthUser(userData);
    setToken(userData?.token || null); // Ensure the token is set if available
  };

  // Effect to update localStorage when authUser or token changes
  useEffect(() => {
    if (authUser) {
      // Store updated authUser and timestamp in localStorage
      localStorage.setItem("causeBank-user", JSON.stringify(authUser));
      localStorage.setItem("causebank-user-timestamp", Date.now());
    }
    if (token) {
      // Store the token in localStorage
      localStorage.setItem("causeBank-token", token);
    }
  }, [authUser, token]);

  // Provide authUser, token, setAuthUser, and updateUserProfile to children components
  return (
    <AuthContext.Provider
      value={{
        authUser,
        token,
        setAuthUser,
        setToken,
        updateUserProfile,
      }}>
      {children}
    </AuthContext.Provider>
  );
};
