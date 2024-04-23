// Dans votre dossier components ou là où vous organisez vos composants
// CustomTabButton.js
import React from 'react';
import { TouchableOpacity, Text, StyleSheet } from 'react-native';
import { useImages } from '../context/ImageContext'; // Ajustez le chemin d'accès
import { launchCamera, launchImageLibrary } from 'react-native-image-picker';
import Icon from 'react-native-vector-icons/EvilIcons'; // Assurez-vous d'avoir importé correctement vos icônes

const CustomTabButton = ({ isCamera }) => {
  const { addImage } = useImages();

  const handlePress = async () => {
    const options = { mediaType: 'photo', quality: 1, selectionLimit: 0 }; // 0 pour illimité
    const result = isCamera ? await launchCamera(options) : await launchImageLibrary(options);
  
    if (!result.didCancel && !result.errorCode && result.assets) {
      result.assets.forEach(asset => {
        addImage(asset.uri); 
      });
    }
  };
  

  return (
    <TouchableOpacity onPress={handlePress} style={styles.button}>
      <Icon name={isCamera ? "camera" : "image"} size={28} color="#FFF" />
      <Text style={styles.buttonText}>{isCamera ? "Caméra" : "Galerie"}</Text>
    </TouchableOpacity>
  );
};

const styles = StyleSheet.create({
  button: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
    backgroundColor: '#000', // Fond noir
  },
  buttonText: {
    color: '#FFF', // Texte blanc
    fontSize: 9,
  }
});

export default CustomTabButton;
