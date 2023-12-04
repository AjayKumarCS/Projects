import React from 'react'
import { NavigationContainer } from '@react-navigation/native';
import { createNativeStackNavigator } from '@react-navigation/native-stack';
import Main from './Main';
import BudgetEntry from './BudgetEntry';

const Stack = createNativeStackNavigator();
const AppNavigator = () => {
  return (
    <NavigationContainer>
      <Stack.Navigator>
        <Stack.Screen name="Budget Entry" component={BudgetEntry} />
        <Stack.Screen name="Budget Entry Listing" component={Main} />
      </Stack.Navigator>
    </NavigationContainer>
  )
}

export default AppNavigator