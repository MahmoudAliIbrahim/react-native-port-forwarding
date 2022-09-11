#import "PortForwarding.h"

#ifdef RCT_NEW_ARCH_ENABLED
#import "RNPortForwardingSpec.h"
#endif

@implementation PortForwarding
RCT_EXPORT_MODULE()

// Example method
// See // https://reactnative.dev/docs/native-modules-ios
RCT_REMAP_METHOD(start,
                 start:(double)port
                 withResolver:(RCTPromiseResolveBlock)resolve
                 withRejecter:(RCTPromiseRejectBlock)reject)
{
    portMapper = [[PortMapper alloc] initWithPort:port];

    portMapper.desiredPublicPort = port;

    [[NSNotificationCenter defaultCenter] addObserverForName:PortMapperChangedNotification object:portMapper queue:nil usingBlock:^(NSNotification *note) {
        resolve(@"Success");
    }];

    if(![portMapper waitTillOpened]) {
        reject(@"error", @"Could not open port", nil);
    }
}


RCT_REMAP_METHOD(stop,
                 stop:(double)port
                 withResolver:(RCTPromiseResolveBlock)resolve
                 withRejecter:(RCTPromiseRejectBlock)reject)
{
    [portMapper close];
    resolve(@"Success");
}

// Don't compile this code when we build for the old architecture.
#ifdef RCT_NEW_ARCH_ENABLED
- (std::shared_ptr<facebook::react::TurboModule>)getTurboModule:
    (const facebook::react::ObjCTurboModule::InitParams &)params
{
    return std::make_shared<facebook::react::NativePortForwardingSpecJSI>(params);
}
#endif

@end
