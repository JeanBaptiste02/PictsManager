// ImageContext.js
import React, { createContext, useContext, useState } from 'react';

const ImageContext = createContext();

export const useImages = () => useContext(ImageContext);

export const ImageProvider = ({ children }) => {
  const [images, setImages] = useState([]);

  const addImage = (uri) => {
    setImages((oldImages) => [...oldImages, uri]);
  };

  return (
    <ImageContext.Provider value={{ images, addImage }}>
      {children}
    </ImageContext.Provider>
  );
};
