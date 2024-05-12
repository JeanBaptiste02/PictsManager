import React, { useState, useEffect } from "react";
import axios from "axios";
import {
  Alert,
  StyleSheet,
  SafeAreaView,
  ScrollView,
  View,
  Text,
  Image,
  TouchableOpacity,
  TextInput,
} from "react-native";
import { createAlbum } from "../services/Album";
import AsyncStorage from "@react-native-async-storage/async-storage";

const baseUrl = "http://10.0.2.2:8080";
//const baseUrl = "http://192.168.1.8:8080";

export default function Signup({ navigation }) {
  const [form, setForm] = useState({
    nom: "",
    email: "",
    password: "",
  });

  const [isLoading, setIsLoading] = useState(false);

  const handleSignUp = async () => {
    if (!form.nom.trim() || !form.email.trim() || !form.password.trim()) {
      Alert.alert("Error", "Please provide name, email, and password");
      return false;
    }

    console.log("Form Data:", form);

    setIsLoading(true);

    try {
      const response = await axios.post(`${baseUrl}/api/users/adduser`, {
        nom: form.nom,
        email: form.email,
        password: form.password,
      });

      if (response.status === 200) {
        Alert.alert(
          "Success",
          `You have created: ${JSON.stringify(response.data)}`
        );
        setIsLoading(false);
        setForm({ nom: "", email: "", password: "" });

        // Create an album for the user
        const token = await AsyncStorage.getItem("jwtToken");
        console.log("Token for the creation of an album:", token);
        const album = await createAlbum();
        console.log("Checking album content:", album);
        if (album) {
          console.log("Album created:", album);
          navigation.navigate("Login");
        } else {
          console.log("Album not created");
        }

        return true;
      } else {
        throw new Error("An error has occurred");
      }
    } catch (error) {
      console.error("Error:", error);
      Alert.alert("Error", "An error has occurred. Please try again.");
      setIsLoading(false);
      return false;
    }
  };

  return (
    <SafeAreaView style={{ flex: 1, backgroundColor: "black" }}>
      <ScrollView contentContainerStyle={styles.container}>
        <View style={styles.header}>
          <Image
            alt="App Logo"
            resizeMode="contain"
            style={styles.headerImg}
            source={require("../image/main_logo.png")}
          />
          <Text style={styles.title}>
            Sign up to <Text style={{ color: "orange" }}>PictsManager</Text>
          </Text>
        </View>

        <View style={styles.input}>
          <TextInput
            placeholder="Your Name"
            placeholderTextColor="#ffffff"
            style={styles.inputControl}
            value={form.nom}
            onChangeText={(nom) => setForm({ ...form, nom })}
          />
        </View>

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
            onPress={() =>
              handleSignUp().then((success) => {
                console.log(success);
                if (success) {
                  console.log(success);
                  navigation.navigate("Login");
                }
              })
            }
          >
            <View style={styles.btn}>
              <Text style={styles.btnText}>Sign up</Text>
            </View>
          </TouchableOpacity>
        </View>

        <TouchableOpacity
          onPress={() => {
            navigation.navigate("Login");
          }}
          style={{ marginTop: "auto" }}
        >
          <Text style={styles.formFooter}>
            Have an account?{" "}
            <Text style={{ textDecorationLine: "underline", color: "orange" }}>
              Sign in
            </Text>
          </Text>
        </TouchableOpacity>
      </ScrollView>
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
    color: "#075eec",
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
