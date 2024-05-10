// App.js
import React, { useEffect, useState } from "react";
import { NavigationContainer } from "@react-navigation/native";
import { createNativeStackNavigator } from "@react-navigation/native-stack";
import AppNavigator from "./navigation/AppNavigator.js";
import Login from "./screens/Login";
import Logout from "./screens/Logout.js";
import Signup from "./screens/Signup";
import ForgotPassword from "./screens/ForgotPassword";
import { ImageProvider } from "./context/ImageContext.js";
import AsyncStorage from "@react-native-async-storage/async-storage";
import axios from "axios";

const Stack = createNativeStackNavigator();

export default function App() {
  const baseUrl = "http://10.0.2.2:8080";

  const [isAuthenticated, setIsAuthenticated] = useState(false);

  useEffect(() => {
    checkAuthentication();
  }, []);

  const checkAuthentication = async () => {
    // Check if the user is authenticated
    const token = await AsyncStorage.getItem("jwtToken");
    console.log("Token exists: ", token);
    if (token) {
      console.log("Token exists: ", token);
      // If token exists, set it in Axios headers
      axios.defaults.headers.common["Authorization"] = `Bearer ${token}`;
      setIsAuthenticated(true);
    } else {
      setIsAuthenticated(false);
    }
  };

  return (
    <ImageProvider>
      <NavigationContainer>
        <Stack.Navigator>
          <Stack.Screen
            name="Login"
            component={Login}
            options={{ headerShown: false }}
          />
          <Stack.Screen
            name="HomeTabs"
            component={AppNavigator}
            options={{ headerShown: false }}
          />

          <Stack.Screen
            name="Signup"
            component={Signup}
            options={{ headerShown: false }}
          />
          <Stack.Screen
            name="ForgotPassword"
            component={ForgotPassword}
            options={{
              headerTitle: "",
              headerBackVisible: true,
              headerStyle: { backgroundColor: "black" },
              headerTintColor: "white",
            }}
          />

          <Stack.Screen
            name="Logout"
            component={Logout}
            options={{
              headerTitle: "",
              headerBackVisible: true,
              headerStyle: { backgroundColor: "black" },
              headerTintColor: "white",
            }}
          />
        </Stack.Navigator>
      </NavigationContainer>
    </ImageProvider>
  );
}
