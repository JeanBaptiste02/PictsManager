import React, { useState } from "react";
import axios from "axios";
import {
  Alert,
  StyleSheet,
  SafeAreaView,
  View,
  Image,
  Text,
  TouchableOpacity,
  TextInput,
} from "react-native";
import { KeyboardAwareScrollView } from "react-native-keyboard-aware-scrollview";
import AsyncStorage from "@react-native-async-storage/async-storage";
import { Logout } from "./Logout.js";

const baseUrl = "http://10.0.2.2:8080";
//const baseUrl = "http://192.168.1.8:8080";

export default function Login({ navigation }) {
  const [form, setForm] = useState({
    email: "",
    password: "",
  });
  const [isLoading, setIsLoading] = useState(false);

  const handleLogin = async () => {
    if (!form.email.trim() || !form.password.trim()) {
      Alert.alert("Error", "Please provide email and password");
      return;
    }
    console.log(form.email);
    console.log(form.password);
    setIsLoading(true);

    try {
      const response = await axios.post(`${baseUrl}/api/login`, {
        email: form.email,
        password: form.password,
      });

      if (response.status === 200) {
        const jwtToken = response.data.jwtToken; // Extract the token from the response
        try {
          await AsyncStorage.setItem("jwtToken", jwtToken); // Store the token in AsyncStorage
        } catch (error) {
          console.error("Error storing token:", error);
          Alert.alert(
            "Error",
            "An error occurred while logging in. Please try again."
          );
          setIsLoading(false);
          return false;
        }
        Alert.alert("Success", "You have successfully logged in");
        setIsLoading(false);
        setForm({ email: "", password: "" });
        return true;
      } else {
        throw new Error("Unexpected response status: " + response.status);
      }
    } catch (error) {
      console.error("Error:", error);
      if (error.response && error.response.status === 401) {
        Alert.alert("Error", "Invalid email or password");
      } else {
        Alert.alert("Error", "An error has occurred. Please try again.");
      }
      setIsLoading(false);
      return false;
    }
  };

  return (
    <SafeAreaView style={{ flex: 1, backgroundColor: "black" }}>
      <View style={styles.container}>
        <KeyboardAwareScrollView>
          <View style={styles.header}>
            <Image
              alt="App Logo"
              resizeMode="contain"
              style={styles.headerImg}
              source={require("../image/main_logo.png")}
            />

            <Text style={styles.title}>
              Sign in to <Text style={{ color: "orange" }}>PictsManager</Text>
            </Text>
          </View>

          <View style={styles.form}>
            <View style={styles.input}>
              <TextInput
                placeholder="Email address"
                placeholderTextColor="#ffffff"
                style={styles.inputControl}
                keyboardType="email-address"
                value={form.email}
                onChangeText={(email) => setForm({ ...form, email })}
              />
            </View>

            <View style={styles.input}>
              <TextInput
                placeholder="Password"
                placeholderTextColor="#ffffff"
                style={styles.inputControl}
                secureTextEntry={true}
                value={form.password}
                onChangeText={(password) => setForm({ ...form, password })}
              />
            </View>
            <View style={styles.formAction}>
              <TouchableOpacity
                onPress={() => {
                  handleLogin().then((success) => {
                    console.log(success);
                    if (success) {
                      console.log(success);
                      navigation.navigate("HomeTabs");
                    }
                  });
                }}
              >
                <View style={styles.btn}>
                  <Text style={styles.btnText}>Sign in</Text>
                </View>
              </TouchableOpacity>
            </View>
            <TouchableOpacity
              onPress={() => {
                navigation.navigate("ForgotPassword");
              }}
            >
              <Text style={styles.formLink}>Forgot password?</Text>
            </TouchableOpacity>
          </View>
        </KeyboardAwareScrollView>

        <TouchableOpacity
          onPress={() => {
            navigation.navigate("Signup");
          }}
          style={{ marginTop: "auto" }}
        >
          <Text style={styles.formFooter}>
            Don't have an account?{" "}
            <Text style={{ textDecorationLine: "underline", color: "orange" }}>
              Sign up
            </Text>
          </Text>
        </TouchableOpacity>

        <TouchableOpacity
          onPress={() => {
            navigation.navigate("Logout");
          }}
          style={{ marginTop: "auto" }}
        >
          <Text style={styles.formFooter}>Disconnect</Text>
        </TouchableOpacity>
      </View>
    </SafeAreaView>
  );
}

const styles = StyleSheet.create({
  container: {
    paddingVertical: 24,
    paddingHorizontal: 0,
    flexGrow: 1,
    flexShrink: 1,
    flexBasis: 0,
  },
  title: {
    fontSize: 31,
    fontWeight: "700",
    color: "white",
    marginBottom: 6,
  },
  subtitle: {
    fontSize: 15,
    fontWeight: "500",
    color: "#929292",
  },
  /** Header */
  header: {
    alignItems: "center",
    justifyContent: "center",
    marginVertical: 36,
  },
  headerImg: {
    width: 100,
    height: 100,
    alignSelf: "center",
    marginBottom: 36,
  },
  /** Form */
  form: {
    marginBottom: 24,
    paddingHorizontal: 24,
    flexGrow: 1,
    flexShrink: 1,
    flexBasis: 0,
  },
  formAction: {
    marginTop: 4,
    marginBottom: 16,
  },
  formLink: {
    fontSize: 16,
    fontWeight: "600",
    color: "white",
    textAlign: "center",
  },
  formFooter: {
    marginBottom: 70,
    fontSize: 15,
    fontWeight: "600",
    color: "white",
    textAlign: "center",
    letterSpacing: 0.15,
  },
  /** Input */
  input: {
    marginBottom: 16,
  },
  inputLabel: {
    fontSize: 17,
    fontWeight: "600",
    color: "white",
    marginBottom: 8,
  },
  inputControl: {
    height: 50,
    backgroundColor: "black",
    paddingHorizontal: 16,
    borderRadius: 12,
    fontSize: 15,
    fontWeight: "500",
    color: "white",
    borderWidth: 1,
    borderColor: "#C9D3DB",
    borderStyle: "solid",
  },
  /** Button */
  btn: {
    flexDirection: "row",
    alignItems: "center",
    justifyContent: "center",
    borderRadius: 30,
    paddingVertical: 10,
    paddingHorizontal: 20,
    borderWidth: 1,
    backgroundColor: "orange",
    borderColor: "orange",
  },
  btnText: {
    fontSize: 18,
    lineHeight: 26,
    fontWeight: "600",
    color: "black",
  },
});
