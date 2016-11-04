import { PropTypes } from 'react';
import { requireNativeComponent, View } from 'react-native';

module.exports = requireNativeComponent('RCTMediaPlayer', {
  name: 'MediaPlayer',
  propTypes: {
    uri: PropTypes.string,
    speed: PropTypes.string,
    play: PropTypes.bool.isRequired,
    ...View.propTypes
  }
});
