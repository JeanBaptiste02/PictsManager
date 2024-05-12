import React, { useState, useEffect } from "react";
import { View, Text, FlatList, Image, StyleSheet } from "react-native";
import axios from "axios";
import AsyncStorage from "@react-native-async-storage/async-storage";

const PhotoList = ({ userId }) => {
  const [photos, setPhotos] = useState([]);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const token = await AsyncStorage.getItem("jwtToken");
        console.log("Token from AsyncStorage:", token);
        const response = await axios.get(
          `http://10.0.2.2:8080/api/photo/image/upload_20240512014146241.jpg`,
          {
            headers: { Authorization: `Bearer ${token}` },
          }
        );
        setPhotos(response.data);
        console.log(response.data);
      } catch (error) {
        console.error("Error fetching data:", error);
      }
    };

    fetchData();
  }, [userId]);

  const formatPath = (path) => {
    return path.replace(/\\/g, "/");
  };

  return (
    <View style={styles.container}>
      <Text style={styles.title}>Photos of User {userId}:</Text>
      <FlatList
        data={photos}
        keyExtractor={(item) => item.id.toString()}
        renderItem={({ item }) => {
          console.log("Original path:", item.path);
          const formattedPath = formatPath(item.path);
          console.log("Formatted path:", formattedPath);
          return (
            <View style={styles.photoContainer}>
              <Text style={styles.photoName}>{formattedPath}</Text>
              <Image
                source={{
                  uri: "file:///C:/Users/talat/IdeaProjects/PictsManager/back/photosData/toto/kam.jpg",
                }}
                style={styles.image}
                onError={(error) =>
                  console.log(
                    "Erreur de chargement de l'image:",
                    error.nativeEvent.error
                  )
                }
              />
            </View>
          );
        }}
      />
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    padding: 10,
  },
  title: {
    fontSize: 20,
    fontWeight: "bold",
    marginBottom: 10,
  },
  photoContainer: {
    marginBottom: 20,
  },
  photoName: {
    fontSize: 16,
    fontWeight: "bold",
    marginBottom: 5,
  },
  image: {
    width: 200,
    height: 200,
    borderRadius: 10,
  },
});

export default PhotoList;
