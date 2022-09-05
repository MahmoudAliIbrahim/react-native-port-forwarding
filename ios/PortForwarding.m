#import <React/RCTBridgeModule.h>

@interface RCT_EXTERN_MODULE(PortForwarding, NSObject)

RCT_EXTERN_METHOD(start:(NSNumber)toPort
                 withResolver:(RCTPromiseResolveBlock)resolve
                 withRejecter:(RCTPromiseRejectBlock)reject)

+ (BOOL)requiresMainQueueSetup
{
  return NO;
}

@end
