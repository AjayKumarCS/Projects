import React from 'react'
import { DrawerItem, createDrawerNavigator } from '@react-navigation/drawer';
import FavContactList from './FavContactList';
import Contacts from './Contacts';

const Drawer = createDrawerNavigator();
const Home = () => {
  return (
      <Drawer.Navigator screenOptions={{
        drawerStyle: {
          width: 210,
        },
      }}>
        <Drawer.Screen name="Contact List" component={Contacts} />
        <Drawer.Screen name="Favorite contact List" component={FavContactList} />
      </Drawer.Navigator>
  )
}

export default Home