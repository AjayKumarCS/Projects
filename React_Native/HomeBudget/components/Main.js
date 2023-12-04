import { View, Text, StyleSheet, Button, ScrollView } from 'react-native'
import React from 'react'
import { useSelector } from 'react-redux'

const Main = ({ navigation }) => {
    const data = useSelector((state) => state.reducer)
    return (
        <ScrollView>
            {data.map((item) =>
                <View>
                    <Text>{item.name}</Text>
                    <Text>{item.planned}</Text>
                    <Text>{item.actual}</Text>
                </View>
            )}
        </ScrollView>
    )
}

export default Main