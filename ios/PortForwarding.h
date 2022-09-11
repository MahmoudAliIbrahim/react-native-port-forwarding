#import <React/RCTBridgeModule.h>
#import "PortMapper.h" // https://github.com/KosmicTask/Cocoa-PortMapper

@interface PortForwarding : NSObject <RCTBridgeModule>
{
    PortMapper *portMapper;
}
@end
