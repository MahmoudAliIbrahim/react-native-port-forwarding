import { NativeModules, Platform } from 'react-native';

const LINKING_ERROR =
  `The package 'react-native-port-forwarding' doesn't seem to be linked. Make sure: \n\n` +
  Platform.select({ ios: "- You have run 'pod install'\n", default: '' }) +
  '- You rebuilt the app after installing the package\n' +
  '- You are not using Expo managed workflow\n';

const PortForwarding = NativeModules.PortForwarding
  ? NativeModules.PortForwarding
  : new Proxy(
      {},
      {
        get() {
          throw new Error(LINKING_ERROR);
        },
      }
    );

export function start(port: number): Promise<string> {
  return PortForwarding.start(port);
}

export function stop(port: number): Promise<string> {
  return PortForwarding.stop(port);
}
