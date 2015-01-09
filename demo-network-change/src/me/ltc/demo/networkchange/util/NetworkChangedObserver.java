package me.ltc.demo.networkchange.util;

import android.content.Context;
import android.content.Intent;

public interface NetworkChangedObserver {
    
    public abstract void registerNetworkChangedReceiver(Context context);
    
    public abstract void registerNetworkChangedObserver(NetworkChangedListener observer);
    
    public abstract void deregisterNetworkChangedObserver(NetworkChangedListener observer);
    
    public abstract void notifyObservers(Context context, Intent intent);
    
}
