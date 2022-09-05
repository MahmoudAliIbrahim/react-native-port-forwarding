import * as React from 'react';

import { StyleSheet, View, Text } from 'react-native';
import { start, stop } from 'react-native-port-forwarding';

export default function App() {
  const [result, setResult] = React.useState<string | undefined>();

  React.useEffect(() => {
    start(22).then(setResult);

    return () => {
      stop(22).then(setResult);
    };
  }, []);

  return (
    <View style={styles.container}>
      <Text>Result: {result}</Text>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
  },
  box: {
    width: 60,
    height: 60,
    marginVertical: 20,
  },
});
