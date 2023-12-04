import { View, Text, StyleSheet } from 'react-native'
import React from 'react'
import AppNavigator from './components/AppNavigator'
import { Provider } from 'react-redux';
import { PersistGate } from 'redux-persist/integration/react';
import persistStore from 'redux-persist/es/persistStore';
import { myStore } from './components/redux/myStore';

let persistor = persistStore(myStore);
const App = () => {
  return (
    <Provider store={myStore}>
        <PersistGate persistor={persistor}>
            <AppNavigator />
        </PersistGate>
    </Provider>
  );
}

export default App