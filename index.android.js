/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 * @flow
 */

import React, { Component } from 'react';
import {
  AppRegistry,
  StyleSheet,
  Text,
  View,
  NativeModules,
  Dimensions,
  TouchableOpacity
} from 'react-native';

var {height, width} = Dimensions.get('window');

import MediaPlayer from './MediaPlayer'

export default class reactNativeAndroidMediaPlayer extends Component {
  constructor(props){
    super(props);
    this.state = {
      uri: 'http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4',
      prepared: false,
      playing: false,
      duration: 0
    };

    this.play = this.play.bind(this);
    this.pause = this.pause.bind(this);
  }

  componentDidMount(){

  }

  play(){
    if (this.state.prepared === true) {
      this.setState({ playing: true });
      MediaPlayer.play();
      return true;
    }
    return false;
  }

  pause(){
    if (this.state.prepared === true && this.state.playing === true) {
      MediaPlayer.pause();
      this.setState({ playing: false })
      return true;
    }
    return false;
  }

  componentDidMount() {
    console.log(MediaPlayer)
  }

  render() {
    console.log(MediaPlayer)
    return (
      <View style={styles.container}>
        <MediaPlayer style={{width: width, height: height}} uri={this.state.uri} speed={"0.01"} />
      </View>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#F5FCFF',
  },
  welcome: {
    fontSize: 20,
    textAlign: 'center',
    margin: 10,
  },
  instructions: {
    textAlign: 'center',
    color: '#333333',
    marginBottom: 5,
  },
});

AppRegistry.registerComponent('reactNativeAndroidMediaPlayer', () => reactNativeAndroidMediaPlayer);
