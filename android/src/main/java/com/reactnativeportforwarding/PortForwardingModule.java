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
      UPnP.openPortTCP(port);
      UPnP.openPortUDP(port);

      promise.resolve("Port forwarding started at port " + port);
    }

    @ReactMethod
    public void stop(int port,Promise promise) {
      UPnP.closePortTCP(port);
      UPnP.closePortUDP(port);

      promise.resolve("Port forwarding stopped at port " + port);
    }
}
