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
import Icon from "react-native-vector-icons/EvilIcons"; // Assurez-vous d'avoir importé correctement vos icônes

const baseUrl = "http://10.0.2.2:8080";
//const baseUrl = "http://192.168.1.8:8080";

export default function EditUser({ navigation }) {
  const [form, setForm] = useState({
    username: "",
    email: "",
    password: "",
  });

  const handleUpdateuser = async () => {
    const token = await AsyncStorage.getItem("jwtToken"); // Récupération du token
    if (!form.nom.trim() || !form.email.trim() || !form.password.trim()) {
      Alert.alert("Error");
      return;
    }

    try {
      const response = await axios.put(
        `${baseUrl}/api/users/update/user`,
        {
          nom: form.nom,
          email: form.email,
          password: form.password,
        },
        {
          headers: {
            Authorization: `Bearer ${token}`, // Inclusion du token dans l'en-tête
          },
        }
      );

      if (response.status === 200) {
        Alert.alert(
          "Success",
          `You have created: ${JSON.stringify(response.data)}`
        );
        setForm({ nom: "", email: "", password: "" });
      } else {
        throw new Error("An error has occurred");
      }
    } catch (error) {
      console.error("Error:", error);
      Alert.alert("Error", "An error has occurred. Please try again.");
      setIsLoading(false);
    }
  };

  return (
    <SafeAreaView style={{ flex: 1, backgroundColor: "black" }}>
      <View style={styles.container}>
        <KeyboardAwareScrollView>
          {/* <View style={styles.header}>
          <Image
              alt="App Logo"
              resizeMode="contain"
              style={styles.headerImg}
              source={{
                uri: 'https://assets.withfra.me/SignIn.2.png',
              }} />
                 <Icon name="camera" size={20} color="white" style={styles.searchIcon} /> 
            <TouchableOpacity style={styles.editIcon}>
            </TouchableOpacity>
          </View> */}

          <View style={styles.form}>
            <View style={styles.input}>
              <Text style={styles.inputLabel}>Username</Text>

              <TextInput
                placeholder="Your Name"
                placeholderTextColor="#ffffff"
                style={styles.inputControl}
                value={form.nom}
                onChangeText={(nom) => setForm({ ...form, nom })}
              />
            </View>
            <View style={styles.input}>
              <Text style={styles.inputLabel}>Email address</Text>

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
              <Text style={styles.inputLabel}>Password</Text>

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
              <TouchableOpacity onPress={handleUpdateuser}>
                <View style={styles.btn}>
                  <Text style={styles.btnText}>Edit profile</Text>
                </View>
              </TouchableOpacity>
            </View>
          </View>
        </KeyboardAwareScrollView>
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
    color: "#1D2A32",
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
    width: 80,
    height: 80,
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
    color: "#222",
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
    paddingHorizontal: 16,
    borderRadius: 12,
    fontSize: 15,
    fontWeight: "500",
    color: "white",
    backgroundColor: "black",
    borderColor: "grey",
    borderWidth: 1,
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
    borderColor: "#075eec",
  },
  btnText: {
    fontSize: 18,
    lineHeight: 26,
    fontWeight: "600",
    color: "black",
  },

  searchIcon: {
    position: "absolute",
    color: "blue",
    top: 65,
    left: 230,
    backgroundColor: "orange",
    borderRadius: 30,
    padding: 2,
  },
});
