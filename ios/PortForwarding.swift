import YCPortForwarder

@objc(PortForwarding)
class PortForwarding: NSObject {

  @objc(start:fromPort:toPort:withResolver:withRejecter:)
  func start(remoteHost: String, fromPort: UInt16, toPort: UInt16, resolve:RCTPromiseResolveBlock,reject:RCTPromiseRejectBlock) -> Void {

  let forwarder = YCPortForwarder(remoteHost: remoteHost, remotePort: fromPort, toPort: toPort)
    if let localPort = forwarder.start() {
      resolve("Successfully forwarded from port \(fromPort) to port \(toPort)")
    } else {
      reject("error", "Something went wrong.", nil)
    }
  }
}
