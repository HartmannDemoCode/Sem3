/**
 * Created by tha on 29-10-2017.
 */
import React from 'react';
import { Button, Image, View, ActivityIndicator, Text } from 'react-native';
import { ImagePicker } from 'expo';

export default class ImagePickerExample extends React.Component {
    state = {
        image: null,
        status: "",
        uploading: false,
    };

    render() {
        let { image } = this.state;

        return (
            <View style={{ flex: 1, alignItems: 'center', justifyContent: 'center' }}>
                <Button
                    title="Pick an image from camera roll"
                    onPress={this._pickImage}
                />
                {image &&
                <Image source={{ uri: image }} style={{ width: 200, height: 200 }} />}

                {this.state.uploading &&  <ActivityIndicator color="blue" animating size="large" />}
                {image && <Button title="Upload" onPress={() => {
                    this.setState({uploading : true});
                    this._uploadImage(image)}
                } />}
                <Text>{this.state.status}</Text>
            </View>

        );
    }

    _pickImage = async () => {
        let result = await ImagePicker.launchImageLibraryAsync({
            allowsEditing: true,
            aspect: [4, 3],
        });

        console.log(result);

        if (!result.cancelled) {
            this.setState({ image: result.uri });
        }
    };

    _uploadImage =  (uri) => {
        this.setState({uploading : true});
        let apiUrl = "http://a0f8279e.ngrok.io";
        let apiURL = apiUrl + "/fileUploadDemo/api/upload/file";
        console.log('apiURL: ',apiURL);
        let uriParts = uri.split('.');
        let fileType = uriParts[uriParts.length - 1];
        let fileName = uri.split('/').pop();
        console.log('uri: ',uri);
        let formData = new FormData();
        formData.append('file', {
            uri: uri,
            name: `photo.${fileName}`,
            type: `image/${fileType}`,
        });
        formData.append("user","Mobile User");

        let options = {
            method: 'POST',
            body: formData,
            headers: {
                Accept: 'application/json',
                'Content-Type': 'multipart/form-data',
            },
        };

        fetch(apiURL, options).
        then((res) => {
            console.log('RES: ',res);
            let status = res.ok ? "Image uploaded successfully" : "Failed to upload image. Error: "+res.status+": "+res.statusText;
            this.setState({ status,uploading:false })
        }).catch((err)=>{
            console.log("ERROR: ");
            this.setState({status:"Failed to upload image",uploading:false  })
        });
    }
}
