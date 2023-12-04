import React, { Fragment, Component, useEffect, useState } from 'react';
import ImagePicker from 'react-native-image-picker';
import { SafeAreaView, TextInput, Alert, StyleSheet, View, Text, StatusBar, Image, Dimensions, TouchableOpacity } from 'react-native';
import { Colors } from 'react-native/Libraries/NewAppScreen';
import CheckBox from '@react-native-community/checkbox';
import { openDatabase } from 'react-native-sqlite-storage';
import { useNavigation, useRoute } from '@react-navigation/native';


let db = openDatabase({ name: 'ContactDatabase.db' })
export default UpdateContact = () => {
    const [uri, setUri] = useState('');
    const route = useRoute();
    const navigation = useNavigation();
    const [name, setName] = useState('');
    const [mobile, setMobile] = useState(route.params.data.mobile);
    const [landline, setLandline] = useState(route.params.data.landline);
    const [favorite, setFavorite] = useState(false);

    // console.log(route.params.data);

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
        setName(route.params.data.name);
        setMobile(route.params.data.mobile);
        setLandline(route.params.data.landline);
        setFavorite(Boolean(route.params.data.favorite));
        setUri(route.params.data.uri);
    }, []);

    const updateUser = () => {
        db.transaction(tx => {
            tx.executeSql(
                'UPDATE table_user set name=?, mobile=?, landline=?, fav=?, imageUri=? where id=?',
                [name, mobile, landline, favorite, uri, route.params.data.id],
                (tx, results) => {
                    console.log('Results', results.rowsAffected);
                    if (results.rowsAffected > 0) {
                        Alert.alert(
                            'Success',
                            'User updated successfully',
                            [
                                {
                                    text: 'Ok',
                                    onPress: () => navigation.navigate('Home'),
                                },
                            ],
                            { cancelable: false },
                        );
                    } else alert('Updation Failed');
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
                            placeholder="Enter User Name"
                            style={styles.input}
                            value={name}
                            onChangeText={txt => setName(txt)}
                        />
                        <TextInput
                            placeholder="Enter Mobile Number"
                            value={mobile.toString()}
                            onChangeText={txt => setMobile(txt)}
                            style={[styles.input, { marginTop: 20 }]}
                        />
                        <TextInput
                            placeholder="Enter Landline Number"
                            value={landline.toString()}
                            onChangeText={txt => setLandline(txt)}
                            style={[styles.input, { marginTop: 20 }]}
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
                                updateUser();
                            }}>
                            <Text style={styles.updateBtnText}>Update Contact</Text>
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
    updateBtnText: {
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