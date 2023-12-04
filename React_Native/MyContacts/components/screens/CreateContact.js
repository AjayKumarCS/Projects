import React, { Fragment, Component, useEffect, useState } from 'react';
import ImagePicker from 'react-native-image-picker';
import { SafeAreaView, TextInput, Alert, StyleSheet, View, Text, StatusBar, Image, Dimensions, TouchableOpacity } from 'react-native';
import { Colors } from 'react-native/Libraries/NewAppScreen';
import CheckBox from '@react-native-community/checkbox';
import { openDatabase } from 'react-native-sqlite-storage'

let db = openDatabase({ name: 'ContactDatabase.db' })
export default CreateContact = ({ navigation }) => {
    const [uri, setUri] = useState('');
    const [name, setName] = useState('');
    const [mobile, setMobile] = useState('');
    const [landline, setlandline] = useState('');
    const [favorite, setFavorite] = useState(false);

    chooseImage = () => {
        let options = {
            title: 'Select Image',
            storageOptions: {
                skipBackup: true,
                path: 'images',
            },
        };
        ImagePicker.showImagePicker(options, (response) => {

            if (response.didCancel) {
                console.log('User cancelled image picker');
            } else if (response.error) {
                console.log('ImagePicker Error: ', response.error);
            } else {
                setUri(response.uri);
                console.log(uri);
            }
        });
    }

    renderFileUri = () => {
        if (uri) {
            return <Image
                source={{ uri: uri }}
                style={styles.images}
            />
        } else {
            return <Image
                source={require('./assets/dummy.png')}
                style={styles.images}
            />
        }
    }

    useEffect(() => {
        db.transaction(txn => {
            txn.executeSql(
                "SELECT name FROM sqlite_master WHERE type='table' AND name='table_user'",
                [],
                (tx, res) => {
                    console.log('item:', res.rows.length);
                    if (res.rows.length == 0) {
                        txn.executeSql('DROP TABLE IF EXISTS table_user', []);
                        txn.executeSql(
                            'CREATE TABLE IF NOT EXISTS table_user(id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, mobile INTEGER, landline INTEGER, fav BOOLEAN, imageUri TEXT)',
                            [],
                        );
                    }
                    // else{txn.executeSql('DROP TABLE IF EXISTS table_user', []);}
                },
                error => {
                    console.log(error);
                },
            );
        });
    }, []);

    saveContact = () => {
        console.log(name, mobile, landline, favorite, uri);
        db.transaction(function (tx) {
            tx.executeSql(
                'INSERT INTO table_user (name, mobile, landline, fav, imageUri) VALUES (?,?,?,?,?)',
                [name, mobile, landline, favorite, uri],
                (tx, results) => {
                    console.log('Results', results.rowsAffected);
                    if (results.rowsAffected > 0) {
                        Alert.alert(
                            'Success',
                            'You are Registered Successfully',
                            [
                                {
                                    text: 'Ok',
                                    onPress: () => navigation.navigate('Home'),
                                },
                            ],
                            { cancelable: false },
                        );
                    } else alert('Registration Failed');
                },
                error => {
                    console.log(error);
                },
            );
        });
    };

    return (
        <Fragment>
            <StatusBar barStyle="dark-content" />
            <SafeAreaView>
                <View style={styles.body}>
                    <View style={styles.ImageSections}>
                        <View>
                            {renderFileUri()}
                        </View>
                    </View>

                    <View style={styles.btnParentSection}>
                        <TouchableOpacity onPress={chooseImage} style={styles.btnSection}  >
                            <Text style={styles.btnText}>Choose Image</Text>
                        </TouchableOpacity>
                    </View>
                    <View style={styles.container}>
                        <TextInput
                            style={styles.input}
                            placeholder='Enter Name'
                            onChangeText={(text) => setName(text)}
                            value={name}
                        />
                        <TextInput
                            style={[styles.input, { marginTop: 20 }]}
                            placeholder='Enter Mobile Number'
                            onChangeText={(text) => setMobile(text)}
                            value={mobile}
                            keyboardType='phone-pad'
                        />
                        <TextInput
                            style={[styles.input, { marginTop: 20 }]}
                            placeholder='Enter Landline Number'
                            onChangeText={(text) => setlandline(text)}
                            value={landline}
                            keyboardType='phone-pad'
                        />
                        <View style={styles.checkboxContainer}>
                            <CheckBox
                                style={styles.checkbox}
                                value={favorite}
                                onValueChange={(newValue) => setFavorite(newValue)}
                            />
                            <Text style={styles.label}>Make this contact favorite</Text>
                        </View>
                        <TouchableOpacity
                            style={styles.addBtn}
                            onPress={() => {
                                saveContact();
                            }}
                        >
                            <Text style={styles.saveBtnText}>Add New Contact</Text>
                        </TouchableOpacity>
                    </View>
                </View>
            </SafeAreaView>
        </Fragment>
    );
}

const styles = StyleSheet.create({
    scrollView: {
        backgroundColor: Colors.lighter,
    },

    body: {
        backgroundColor: Colors.white,
        justifyContent: 'center',
        borderColor: 'black',
        borderWidth: 1,
        height: Dimensions.get('screen').height - 20,
        width: Dimensions.get('screen').width
    },
    ImageSections: {
        display: 'flex',
        flexDirection: 'row',
        paddingHorizontal: 8,
        paddingVertical: 8,
        justifyContent: 'center'
    },
    images: {
        width: 150,
        height: 150,
        borderColor: 'black',
        borderWidth: 1,
        marginHorizontal: 3
    },
    btnParentSection: {
        alignItems: 'center',
        marginTop: 10
    },
    btnSection: {
        width: 225,
        height: 50,
        borderWidth: 1,
        alignItems: 'center',
        justifyContent: 'center',
        borderRadius: 10,
        marginBottom: 10
    },
    btnText: {
        textAlign: 'center',
        color: 'gray',
        fontSize: 14,
        fontWeight: 'bold'
    },
    label: {
        margin: 8,
    },
    container: {
        flex: 1,
    },
    input: {
        width: '80%',
        height: 50,
        borderRadius: 10,
        borderWidth: 0.3,
        alignSelf: 'center',
        paddingLeft: 20,
        marginTop: 10,
    },
    addBtn: {
        backgroundColor: 'purple',
        width: '80%',
        height: 50,
        borderRadius: 10,
        justifyContent: 'center',
        alignItems: 'center',
        marginTop: 30,
        alignSelf: 'center',
    },
    saveBtnText: {
        color: '#fff',
        fontSize: 18,
    },
    checkboxContainer: {
        flexDirection: 'row',
        marginTop: 30,
        alignItems: 'center',
        justifyContent: 'center',
    },
    checkbox: {
        alignSelf: 'center',
    },
    add_contact: {
        backgroundColor: 'green',
        width: 160,
        height: 60,
        borderRadius: 20,
        position: 'absolute',
        bottom: 20,
        justifyContent: 'center',
        alignItems: 'center',
        alignSelf: 'center'
    },
});