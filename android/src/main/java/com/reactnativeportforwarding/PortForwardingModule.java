package com.reactnativeportforwarding;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.module.annotations.ReactModule;
import com.dosse.upnp.UPnP;

@ReactModule(name = PortForwardingModule.NAME)
public class PortForwardingModule extends ReactContextBaseJavaModule {
    public static final String NAME = "PortForwarding";

    public PortForwardingModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    @NonNull
    public String getName() {
        return NAME;
    }


    @ReactMethod
    public void start(int port, Promise promise) {
      try {
        if (UPnP.isUPnPAvailable()) {
            if (UPnP.isMappedTCP(port)) {
                promise.reject("UPnP port forwarding not enabled: port is already mapped");
            } else if (UPnP.openPortTCP(port)) {
                promise.resolve("UPnP port forwarding enabled");
            } else {
                promise.reject("UPnP port forwarding failed");
            }
          } else {
              promise.reject("UPnP is not available");
          }
      } catch (Exception e) {
        e.printStackTrace();
        promise.reject("Network error: " + e.getMessage());
      }
    }

    @ReactMethod
    public void stop(int port, Promise promise) {
      try {
        if(UPnP.closePortTCP(port)) {
            promise.resolve("UPnP port forwarding disabled");
        } else {
            promise.reject("UPnP port forwarding not disabled");
        }
      } catch (Exception e) {
        e.printStackTrace();
        promise.reject("Network error: " + e.getMessage());
      }

    }
}
