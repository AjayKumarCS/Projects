import { View, Text, Button, StyleSheet, ScrollView, Image, TouchableOpacity, FlatList, Alert, } from 'react-native'
import React, { useEffect, useState } from 'react'
import { useIsFocused, useNavigation } from '@react-navigation/native';
import { openDatabase } from 'react-native-sqlite-storage'
import { SwipeListView } from 'react-native-swipe-list-view';

let db = openDatabase({ name: 'ContactDatabase.db' })
const Contacts = ({ navigation }) => {
  const isFocused = useIsFocused();
  const nav = useNavigation();
  const [contacts, setContacts] = useState([]);
  useEffect(() => {
    getData();
  }, [isFocused]);
  const getData = () => {
    db.transaction(tx => {
      tx.executeSql('SELECT * FROM table_user', [], (tx, results) => {
        var temp = [];
        for (let i = 0; i < results.rows.length; ++i)
          temp.push(results.rows.item(i));
        setContacts(temp);
      });
    });
  };
  let deleteUser = id => {
    db.transaction(tx => {
      tx.executeSql(
        'DELETE FROM  table_user where id=?',
        [id],
        (tx, results) => {
          console.log('Results', results.rowsAffected);
          if (results.rowsAffected > 0) {
            Alert.alert(
              'Success',
              'User deleted successfully',
              [
                {
                  text: 'Ok',
                  onPress: () => {
                    getData();
                  },
                },
              ],
              { cancelable: false },
            );
          } else {
            alert('Please insert a valid User Id');
          }
        },
      );
    });
  };
  
  return (
    <View style={styles.container}>
      <View style={styles.container}>
        <SwipeListView
          data={contacts}
          renderItem={({ item, index }) => {
            return (
              <View style={
                // styles.userItem
                styles.rowFront
              }>
                {item.imageUri ?
                  <Image source={{ uri: item.imageUri }} style={styles.images} /> :
                  <Image source={require('./assets/dummy.png')} style={styles.images} />
                }
                <Text style={styles.itemName}>{item.name}</Text>
                <View style={styles.itemLabelContainer}>
                  <Text style={styles.itemLabel}>{'mobile: '}</Text>
                  <Text style={styles.itemLabel}>{'landline: '}</Text>
                </View>
                <View style={styles.itemTextContainer}>
                  <Text style={styles.itemText}>{item.mobile}</Text>
                  <Text style={styles.itemText}>{item.landline}</Text>
                </View>
                <Text style={styles.fav}>{item.fav ? '❤️' : ''}</Text>
              </View>
            );
          }}
          renderHiddenItem={({ item, index }) => (
            <View style={styles.rowBack}>
              <TouchableOpacity
                style={[styles.backBtn, styles.backRightBtn]}
                onPress={() => {
                  nav.navigate('Update Contact', {
                    data: {
                      name: item.name,
                      mobile: item.mobile,
                      landline: item.landline,
                      id: item.id,
                      favorite: item.fav,
                      uri: item.imageUri,
                    },
                  })
                }
                }
              >
                <Text style={styles.backTextWhite}>Edit</Text>
              </TouchableOpacity>
              <TouchableOpacity
                style={[styles.backBtn, styles.backLeftBtn]}
                onPress={() => { deleteUser(item.id) }}
              >
                <Text style={styles.backTextWhite}>Delete</Text>
              </TouchableOpacity>
            </View>
          )}
          leftOpenValue={75}
          rightOpenValue={-75}
        />
      </View>
        <TouchableOpacity style={styles.add_contact} onPress={() => navigation.navigate("Add New Contact")}>
          <Text style={styles.btnText}>+</Text>
        </TouchableOpacity>
    </View>
  )
}

const styles = StyleSheet.create({
  container: {
      flex: 1,
  },
  btnText: {
      color: '#fff',
      fontSize: 30,
  },
  fav: {
    position: 'absolute',
    bottom: 10,
    left: 20,
    fontSize: 35,
  },
  itemName: {
    fontSize: 42,
    fontWeight: '700',
    position: 'absolute',
    top: 5,
    left: 20,
  },
  itemLabelContainer: {
    position: 'absolute',
    left: 25,
  },
  itemTextContainer: {
    position: 'absolute',
    left: 105,
  },
  itemLabel: {
    marginTop: 10,
    fontSize: 19,
    fontWeight: '900'
  },
  itemText: {
    marginTop: 10,
    fontSize: 19,
  },
  images: {
    position: 'absolute',
    right: 20,
    top: 70,
    width: 110,
    height: 120,
    borderRadius: 60,
    borderColor: 'black',
    borderWidth: 1,
    marginHorizontal: 3,
  },
  add_contact: {
    backgroundColor: 'purple',
    width: 60,
    height: 60,
    borderRadius: 50,
    position: 'absolute',
    bottom: 20,
    right: 20,
    justifyContent: 'center',
    alignItems: 'center',
  },
  container: {
    backgroundColor: 'white',
    flex: 1,
  },
  backTextWhite: {
    color: '#FFF',
  },
  rowFront: {
    alignItems: 'center',
    backgroundColor: 'grey',
    borderBottomColor: 'black',
    borderBottomWidth: 1,
    justifyContent: 'center',
    height: 200
  },
  rowBack: {
    alignItems: 'center',
    backgroundColor: '#DDD',
    flex: 1,
    flexDirection: 'row',
    justifyContent: 'space-between',
    paddingLeft: 15,
  },
  backBtn: {
    alignItems: 'center',
    bottom: 0,
    justifyContent: 'center',
    position: 'absolute',
    top: 0,
    width: 75,
  },
  backLeftBtn: {
    backgroundColor: 'red',
    left: 0,
  },
  backRightBtn: {
    backgroundColor: 'blue',
    right: 0,
  },
})
export default Contacts