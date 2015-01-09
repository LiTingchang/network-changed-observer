package me.ltc.demo.networkchange.util;

import android.net.NetworkInfo;

public interface NetworkChangedListener {
    
    void onNetworkChanged(NetworkInfo networkInfo);
    
}
