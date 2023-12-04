import React from 'react'
import { NavigationContainer } from '@react-navigation/native';
import { createNativeStackNavigator } from '@react-navigation/native-stack';
import CreateContact from './screens/CreateContact';
import Home from './screens/Home';
import UpdateContact from './screens/UpdateContact';


const Stack = createNativeStackNavigator();
const AppNavigator = () => {
  return (
    <NavigationContainer>
      <Stack.Navigator>
        <Stack.Screen name="Home" component={Home} options={{headerShown: false}} />
        <Stack.Screen name="Add New Contact" component={CreateContact} />
        <Stack.Screen name="Update Contact" component={UpdateContact} />
      </Stack.Navigator>
    </NavigationContainer>
  )
}

export default AppNavigator