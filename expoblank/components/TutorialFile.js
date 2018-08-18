/**
 * Created by tha on 25-10-2017.
 */
import React from 'react';
import { Text, View, Platform, TouchableOpacity, StyleSheet, Button, WebView } from 'react-native';
import { Constants, WebBrowser } from "expo";
import { StackNavigator } from 'react-navigation';
import ListViews from './ListViews';

//Todo: Refactor into a seperate file (Basics.js), import and complete the exercise
class Basics extends React.Component {
    static navigationOptions = { title: "Learn the Basics" }
    render() {
        return (<View><Text>Learn The Bacics</Text></View>)
    }
}

//Todo: Refactor into a seperate file (Props.js), import and complete the exercise
class Props extends React.Component {
    static navigationOptions = { title: "Learn about Props" }
    render() {
        return (
            <View>
                <Text>Props</Text>
            </View>
        )
    }
}
//Todo: Create a new file (State.js), import it, add a "Touchable", and complete the exercise
//Todo: Create a new file (Style.js), import it, add a "Touchable", and complete the exercise
//Todo: Create a new file (HeightWidth.js), import it,add a "Touchable", and complete the exercise
//Todo: Create a new file (HeightWidth.js), import it,add a "Touchable", and complete the exercise
//Todo: Create a new file (FlexBox.js), import it,add a "Touchable", and complete the exercise
//Todo: Create a new file (TextInput.js), import it,add a "Touchable", and complete the exercise
//Todo: Create a new file (Touches.js), import it,add a "Touchable", and complete the exercise
//Todo: Create a new file (ScrollView.js), import it,add a "Touchable", and complete the exercise
//Todo: Create a new file (ListViews.js), import it,add a "Touchable", and complete the exercise
//Todo: Create a new file (NetWorking.js), import it,add a "Touchable", and complete the exercise

const Touchable = (props) => (
    <TouchableOpacity style={styles.button} onPress={props.onPress}>
        <Text style={styles.buttonText}>{props.title}</Text>
    </TouchableOpacity>)

//Todo: Refactor into a seperate file (WhatToDo.js), import and complete the exercise
class WhatToDo extends React.Component {
    static navigationOptions = { title: "What I have to do" }
    render() {
        return (
            <View style={{ flex: 1 }}>
                <Text style={{ marginBottom: 3 }}>Complete all steps in Facebooks "The Basics" tutorial (see below)</Text>
                <Text style={{ marginBottom: 3 }}>For each of the 11 steps, add a corresponding Component in this project + the necessecary changes to navigate into it.
                    (inspired of how you navigated into this)
                </Text>
                <WebView
                    source={{ uri: "https://facebook.github.io/react-native/docs/tutorial.html" }}
                    style={{ marginTop: 20, flex: 1 }}
                />
            </View>
        );
    }
}

class HomeScreen extends React.Component {
    static navigationOptions = { title: 'Day1 Tutorial' };
    render() {
        const { navigate } = this.props.navigation;
        return (
            <View >
                <Text style={{ textAlign: "center", fontSize: 20 }}>See all Demos implemented by XXXX</Text>
                <Touchable onPress={() => navigate('web')} title="What I have to do" />
                <Touchable onPress={() => navigate('basics')} title="Basics" />
                <Touchable onPress={() => navigate('props')} title="Props" />
                <Touchable onPress={() => navigate('list')} title="List Views" />
            </View>
        )
    }
}

const TutorialFile = () => <RouteStack style={{ marginTop: Platform.OS === 'ios' ? 0 : Constants.statusBarHeight / 2 }} />
export default TutorialFile;

const RouteStack = StackNavigator({
    Home: { screen: HomeScreen },
    basics: { screen: Basics },
    props: { screen: Props },
    web: { screen: WhatToDo },
    list: {screen: ListViews},
});

const styles = StyleSheet.create({
    button: {
        margin: 3,
        alignItems: 'center',
        backgroundColor: '#2196F3'
    },
    buttonText: {
        padding: 7,
        fontSize: 18,
        fontWeight: "bold",
        color: 'white'
    }
})