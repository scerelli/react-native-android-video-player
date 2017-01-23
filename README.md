#React Native Android Video Player

### Installation

`npm install --save react-native-android-video-player`
`react-native link react-native-android-video-player`

### Available props

- `style`: You can reference the style `View` inside `react-native` docs to know wich rule you can use.
- `uri`: uri `String` that point to the video resource.
- `speed`: Float number that you can use to speed up or slow down your video playback.
- `...Props`: You can pass all the props available for `View` component.

### How to use it

```
  import MediaPlayer from 'react-native-android-video-player'
  import { Dimensions } from 'react-native'

  const { height, width } = Dimensions.get('window')

  const styles = {
    mediaPlayer: {
      width: width,
      height: height
    }
  }

  ...

  render () {
    <MediaPlayer
      style={styles.mediaPlayer}
      uri={"http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4"}
      speed={0.5} />
  }
```

This component is based on: https://github.com/protyposis/MediaPlayer-Extended
Thanks to @protyposis for the awesome work.


