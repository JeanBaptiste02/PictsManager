// Dans votre dossier components ou là où vous organisez vos composants
// CustomTabButton.js
import { React, useState } from "react";
import { TouchableOpacity, Text, StyleSheet } from "react-native";
import { useImages } from "../context/ImageContext"; // Ajustez le chemin d'accès
import { launchCamera, launchImageLibrary } from "react-native-image-picker";
import Icon from "react-native-vector-icons/EvilIcons"; // Assurez-vous d'avoir importé correctement vos icônes
import axios from "axios";
import AsyncStorage from "@react-native-async-storage/async-storage";
import { getAlbumById } from "../services/Album";

const baseUrl = "http://10.0.2.2:8080";
//const baseUrl = "http://192.168.1.8:8080"; //ipv4

const CustomTabButton = ({ isCamera }) => {
  const [isLoading, setIsLoading] = useState(false);
  const { addImage } = useImages();

  const handlePress = async () => {
    const options = { mediaType: "photo", quality: 1, selectionLimit: 0 }; // 0 pour illimité
    const result = isCamera
      ? await launchCamera(options)
      : await launchImageLibrary(options);

    let photoUri = null;
    if (!result.didCancel && !result.errorCode && result.assets) {
      result.assets.forEach((asset) => {
        addImage(asset.uri);
        photoUri = asset.uri;
      });
    }

    const CancelToken = axios.CancelToken;
    const source = CancelToken.source();

    try {
      const token = await AsyncStorage.getItem("jwtToken");
      console.log("Token for chicken:", token); // Log the token to ensure it's correct

      const user = await getAlbumById(token);
      console.log("User:", user); // Log the user object to ensure it's correct

      const date = new Date();
      const timestamp =
        date.getFullYear() +
        ("0" + (date.getMonth() + 1)).slice(-2) +
        ("0" + date.getDate()).slice(-2) +
        ("0" + date.getHours()).slice(-2) +
        ("0" + date.getMinutes()).slice(-2) +
        ("0" + date.getSeconds()).slice(-2) +
        ("00" + date.getMilliseconds()).slice(-3);

      const fileName = `upload_${timestamp}.jpg`;

      const formData = new FormData();
      formData.append("name", "");
      formData.append("description", "");
      formData.append("albumId", user);
      formData.append("file", {
        uri: photoUri,
        type: "image/jpeg", // or whichever type your image is
        name: fileName, // you can choose any name
      });

      const response = await axios.post(
        `${baseUrl}/api/photo/upload`,
        formData,
        {
          cancelToken: source.token,
          headers: {
            Authorization: `Bearer ${token}`,
            "Content-Type": "multipart/form-data",
          },
        }
      );

      console.log("Response:", response);

      if (response.status === 201 || response.status === 200) {
        alert(` You have created: ${JSON.stringify(response.data)}`);
        setIsLoading(false);
      } else {
        throw new Error("An error has occurred");
      }
    } catch (error) {
      if (axios.isCancel(error)) {
        console.log("Request canceled", error.message);
      } else {
        alert("An error has occurred for storing the photo");
        console.error("Error:", error);
        setIsLoading(false);
      }
    }
  };

  return (
    <TouchableOpacity onPress={handlePress} style={styles.button}>
      {isLoading ? (
        <ActivityIndicator size="large" color="#0000ff" />
      ) : (
        <>
          <Icon name={isCamera ? "camera" : "image"} size={28} color="#FFF" />
          <Text style={styles.buttonText}>
            {isCamera ? "Caméra" : "Galerie"}
          </Text>
        </>
      )}
    </TouchableOpacity>
  );
};

const styles = StyleSheet.create({
  button: {
    flex: 1,
    alignItems: "center",
    justifyContent: "center",
    backgroundColor: "#000", // Fond noir
  },
  buttonText: {
    color: "#FFF", // Texte blanc
    fontSize: 9,
  },
});

export default CustomTabButton;
