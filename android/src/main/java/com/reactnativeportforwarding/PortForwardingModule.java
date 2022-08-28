package com.reactnativeportforwarding;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.module.annotations.ReactModule;
import net.sbbi.upnp.impls.InternetGatewayDevice;
import net.sbbi.upnp.messages.UPNPResponseException;
import java.io.IOException;

@ReactModule(name = PortForwardingModule.NAME)
public class PortForwardingModule extends ReactContextBaseJavaModule {
    private InternetGatewayDevice[] internetGatewayDevices;
    private InternetGatewayDevice foundGatewayDevice;

    public static final String NAME = "PortForwarding";

    public PortForwardingModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    @NonNull
    public String getName() {
        return NAME;
    }

    public String findExternalIPAddress () throws IOException, UPNPResponseException {
        if(internetGatewayDevices == null){
            internetGatewayDevices = InternetGatewayDevice.getDevices(10000);
        }
        if(internetGatewayDevices != null) {
            for (InternetGatewayDevice IGD : internetGatewayDevices) {
                foundGatewayDevice = IGD;
                return IGD.getExternalIPAddress().toString();
            }
        }
        return "No External IP Address Found";
    }

    @ReactMethod
    public void start(String remoteHost, int fromPort, int toPort, Promise promise)  throws IOException, UPNPResponseException {
        if(internetGatewayDevices == null) {
            internetGatewayDevices = InternetGatewayDevice.getDevices(10000);
        }
        if(internetGatewayDevices != null) {
            for (InternetGatewayDevice addIGD : internetGatewayDevices) {
                addIGD.addPortMapping("Port Forwarding", findExternalIPAddress(), fromPort, toPort, remoteHost, 0, "TCP");
                addIGD.addPortMapping("Port Forwarding", findExternalIPAddress(), fromPort, toPort, remoteHost, 0, "UDP");
            }
            promise.resolve("Port forwarding started");
        }else{
            promise.reject("No internet gateway devices found");
        }
    }
}
