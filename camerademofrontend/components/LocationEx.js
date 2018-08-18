/**
 * Created by tha on 29-10-2017.
 */
import Expo from 'expo';
import React, { Component } from 'react';
import { Platform, Text, View, StyleSheet } from 'react-native';
import { Constants, Location, Permissions } from 'expo';
import { MapView } from 'expo';

// const GEOLOCATION_OPTIONS = { enableHighAccuracy: true, timeout: 20000, maximumAge: 1000 };
export default class LocationEx extends Component {
    state = {
        location: null,
        errorMessage: null,
    };
    componentWillMount() {
        // Location.watchPositionAsync(GEOLOCATION_OPTIONS, this.locationChanged);
        this.locationChanged();
    }
    locationChanged = (location) => {
        const region = {
            latitude: location.coords.latitude,
            longitude: location.coords.longitude,
            latitudeDelta: 0.0922,
            longitudeDelta: 0.0421,
        };
        this.setState({location, region});
    }
    componentWillMount() {
        if (Platform.OS === 'android' && !Constants.isDevice) {
            this.setState({
                errorMessage: 'Oops, this will not work on Sketch in an Android emulator. Try it on your device!',
            });
        } else {
            this._getLocationAsync();
        }
    }

    _getLocationAsync = async () => {
        let { status } = await Permissions.askAsync(Permissions.LOCATION);
        if (status !== 'granted') {
            this.setState({
                errorMessage: 'Permission to access location was denied',
            });
        }

        let location = await Location.getCurrentPositionAsync({});
        this.setState({ location });
    };

    render() {
        let text = 'Waiting..';
        let loc = '', position = '';
        if (this.state.errorMessage) {
            text = this.state.errorMessage;
        } else if (this.state.location) {
            loc = this.state.location;
            text = JSON.stringify(this.state.location);
            position = `${loc.coords.latitude},${loc.coords.longitude}`;
            var img_url = "https://maps.googleapis.com/maps/api/staticmap?center="
                +position+"&zoom=14&size=400x300&key=AIzaSyBu-916DdpKAjTmJNIgngS6HL_kDIKU0aU";
        }

        return (


            <View style={styles.container}>
                <Text style={styles.paragraph}>
                    Time: {new Date(loc.timestamp).toLocaleString()},
                </Text>
                <Text style={styles.paragraph}>
                    Position (latitude and longitude): {position}
                </Text>

                <MapView
                    style={{ flex: 0.5 }}
                    initialRegion={this.state.region}
                />

            </View>


        );
    }
}

const styles = StyleSheet.create({
    container: {
        flex: 1,
        alignItems: 'center',
        justifyContent: 'center',
        paddingTop: Constants.statusBarHeight,
        backgroundColor: '#ecf0f1',
    },
    paragraph: {
        margin: 24,
        fontSize: 18,
        textAlign: 'center',
    },
});