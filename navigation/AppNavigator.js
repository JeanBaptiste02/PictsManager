import React from 'react';
import { createBottomTabNavigator } from '@react-navigation/bottom-tabs';
import Home from '../screens/Home';
import EditUser from '../screens/EditUser';
import CustomTabButton from '../custom/CustomButton'; // Assurez-vous que le chemin est correct
import Icons from 'react-native-vector-icons/FontAwesome'; // Supposons que vous l'utilisiez quelque part ailleurs
import Icons2 from 'react-native-vector-icons/EvilIcons';
import Icon from 'react-native-vector-icons/MaterialIcons';
// Supposons que vous l'utilisiez quelque part ailleurs

const Tab = createBottomTabNavigator();

const TabNavigator = () => (
  <Tab.Navigator
     
  screenOptions={{
    tabBarStyle: { backgroundColor: '#000' }, // Pour la barre d'onglets
    tabBarActiveTintColor: 'orange',
    tabBarInactiveTintColor: '#FFF',
    headerStyle: { backgroundColor: '#000' }, // Fond noir pour l'en-tête
  }}
  >
    < Tab.Screen
      name="Home"
      component={Home}
      options={{
        headerShown: false,
        headerStyle: { backgroundColor: 'red' }, 
        tabBarIcon: ({ color, size }) => (
          <Icon name="home" color={color} size={26} />
        ),
      }} />
    <Tab.Screen
      name="Edit"
      component={EditUser}
      options={{
        headerShown: false,
        tabBarIcon: ({ color, size }) => (
          <Icons2 name="user" color={color} size={26} />
        )
      }}
    />
    <Tab.Screen
      name="Camera"
      component={Home} // Ou un autre écran par défaut
      options={{
        tabBarButton: (props) => <CustomTabButton {...props} isCamera={true} />
      }}
    />
    <Tab.Screen
      name="Gallery"
      component={Home} // Ou un autre écran par défaut
      options={{
        tabBarButton: (props) => <CustomTabButton {...props} isCamera={false} />
      }}
    />
    <Tab.Screen
      name="Logout"
      component={Logout} // Render the Logout component
      options={{
        tabBarButton: (props) => <CustomTabButton {...props} isCamera={true} />,
        tabBarIcon: ({ color, size }) => (
          <Icons name="sign-out" color={color} size={26} /> // Example icon for logout
        ),
      }}
    />
  </Tab.Navigator>
);

export default TabNavigator;
