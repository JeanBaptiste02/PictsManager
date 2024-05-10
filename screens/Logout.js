import React, { useEffect } from "react";
import { View, Text, Button } from "react-native";
import AsyncStorage from "@react-native-async-storage/async-storage"; // for async storage
import { token } from "App.js";

const Logout = ({ navigation }) => {
  useEffect(() => {
    // Function to clear user session
    const clearSession = async () => {
      try {
        // Get the token before removing it
        const token = await AsyncStorage.getItem("jwtToken");
        console.log("Token to be removed: ", token);
        // Clear any stored authentication tokens or user session information
        await AsyncStorage.removeItem("jwtToken"); // example: remove authentication token from async storage
        // Navigate to the login screen
        console.log("Token removed successfuly: ", token);
        navigation.navigate("Login");
      } catch (error) {
        console.error("Error clearing session:", error);
      }
    };

    // Call the function to clear user session
    clearSession();
  }, []);

  return (
    <View style={{ flex: 1, justifyContent: "center", alignItems: "center" }}>
      <Text>Logging out...</Text>
      {/* You can add a loading spinner here if necessary */}
    </View>
  );
};

export default Logout;
