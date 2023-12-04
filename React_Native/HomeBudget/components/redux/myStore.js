import rootReducer from './rootReducer';
import { configureStore } from '@reduxjs/toolkit';
import persistReducer from 'redux-persist/es/persistReducer';
import AsyncStorage from '@react-native-async-storage/async-storage';

let persistConfig = {
    key: 'root',
    storage: AsyncStorage,
};
let persistedReducer = persistReducer(persistConfig, rootReducer)

export const myStore = configureStore({
    reducer: persistedReducer
});
