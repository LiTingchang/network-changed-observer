package me.ltc.demo.networkchange;

import me.ltc.demo.networkchange.util.NetworkChangedObserver;
import me.ltc.demo.networkchange.util.NetworkChangedObserverImpl;
import android.app.Application;

public class MyApplication extends Application {
    
    private NetworkChangedObserver networkChangedObserver;
    
    @Override
    public void onCreate() {
        super.onCreate();
        
        networkChangedObserver = new NetworkChangedObserverImpl();
        networkChangedObserver.registerNetworkChangedReceiver(getApplicationContext());
        
    }
    
    public NetworkChangedObserver getNetworkChangedObserver() {
        return networkChangedObserver;
    }
}
