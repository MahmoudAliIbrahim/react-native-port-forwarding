#import <React/RCTBridgeModule.h>

@interface RCT_EXTERN_MODULE(PortForwarding, NSObject)

RCT_EXTERN_METHOD(start:(NSString)remoteHost
                 fromPort:(NSNumber)fromPort
                 toPort:(NSNumber)toPort
                 withResolver:(RCTPromiseResolveBlock)resolve
                 withRejecter:(RCTPromiseRejectBlock)reject)

+ (BOOL)requiresMainQueueSetup
{
  return NO;
}

@end
