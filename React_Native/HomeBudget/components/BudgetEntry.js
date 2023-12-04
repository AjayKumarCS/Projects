import { View, Text, TextInput, StyleSheet, ScrollView, Button } from 'react-native'
import React, { useState } from 'react'
import {useDispatch} from 'react-redux'
import { saveElement } from './redux/action';

const BudgetEntry = ({navigation}) => {
    const [name,setName]=useState("");
    const [planned,setPlanned]=useState("");
    const [actual,setActual]=useState("");
    const dispatch = useDispatch();

    const element = {
        name: String,
        planned: Number,
        actual: Number
    }
    const handleSave = () => {
        element.name=name
        element.planned=planned
        element.actual=actual
        dispatch(saveElement(element))
    }
    return (
        <ScrollView>
            <View>
                <Text>Budget Entry</Text>
                <TextInput 
                style={styles.textInput} 
                placeholder='Enter Name' 
                onChangeText={(text)=>setName(text)}
                value={name}
                />
            </View>
            <View>
                <Text>Planned Amount</Text>
                <TextInput 
                style={styles.textInput} 
                placeholder='Enter Planned Amount' 
                keyboardType='number-pad'
                onChangeText={(text)=>setPlanned(text)}
                value={planned}
                />
            </View>
            <View>
                <Text>Actual Amount</Text>
                <TextInput 
                style={styles.textInput} 
                placeholder='Enter Actual Amount' 
                keyboardType='number-pad'
                onChangeText={(text)=>setActual(text)}
                value={actual}
                />
            </View>
            <View style={styles.button}>
                <Button title='Save' onPress={handleSave} />
            </View>
            <View style={styles.button}>
                <Button title='Show Items' onPress={()=>navigation.navigate("Budget Entry Listing")}/>
            </View>
        </ScrollView>
    )
}

const styles = StyleSheet.create({
    textInput: {
        borderColor: 'black',
        borderWidth: 2,
        margin: 10
    },

    button: {
        margin: 10
    }
})

export default BudgetEntry