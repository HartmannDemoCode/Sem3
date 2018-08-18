/**
 * Created by tha on 25-10-2017.
 */
import React from 'react';
import {View, Text, StyleSheet, FlatList} from 'react-native';
export default class ListViews extends React.Component{
    constructor(){
        super();
        this.state = {data: [{title:''}]};
    }
    // componentDidMount= async()=>{
    //
    //     let data = await fetch('https://facebook.github.io/react-native/movies.json',{method:'get'});
    //     let dataJson = await data.json();
    //     this.setState({data: dataJson.movies});
    //     console.log(this.state.data);
    // };
    componentDidMount=()=>{
        fetch('https://facebook.github.io/react-native/movies.json', {method: 'get'})
            .then(data=>{
                return data.json();
            }).then(data=>{
                this.setState({data: data.movies});
                console.log(this.state.data);
        });
    }
    render(){
        console.log("RENDERING:::");
        return (
            <View>
                <FlatList data={this.state.data} renderItem={({item})=><Text>{item.title}</Text>} keyExtractor={(item, index) => index}/>
            </View>
        )
    }
}