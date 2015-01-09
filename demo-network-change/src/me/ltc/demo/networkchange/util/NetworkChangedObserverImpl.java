
package me.ltc.demo.networkchange.util;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkChangedObserverImpl implements NetworkChangedObserver {

    private List<WeakReference<NetworkChangedListener>> mObservers;

    private BroadcastReceiver mBroadcastReceiver;

    public NetworkChangedObserverImpl() {
        mObservers = new ArrayList<WeakReference<NetworkChangedListener>>();

        mBroadcastReceiver = new BroadcastReceiver() {

            @Override
            public void onReceive(Context context, Intent intent) {
                notifyObservers(context, intent);
            }
        };
    }

    @Override
    public void registerNetworkChangedObserver(NetworkChangedListener observer) {
        synchronized (mObservers) {
            mObservers.add(new WeakReference<NetworkChangedListener>(observer));
        }
    }

    @Override
    public void deregisterNetworkChangedObserver(NetworkChangedListener observer) {
        synchronized (mObservers) {
            Iterator<WeakReference<NetworkChangedListener>> iterator = mObservers.iterator();
            while (iterator.hasNext()) {
                WeakReference<NetworkChangedListener> weakReference = iterator.next();
                if (weakReference.get() == observer) {
                    iterator.remove();
                }
            }
        }
    }

    @Override
    public synchronized void notifyObservers(Context context, Intent intent) {
        synchronized (mObservers) {
            
            NetworkInfo networkInfo =  ConnectivityUtil.getNetworkInfo(context);
            
            Iterator<WeakReference<NetworkChangedListener>> iterator = mObservers.iterator();
            while (iterator.hasNext()) {
                WeakReference<NetworkChangedListener> weakReference = iterator.next();

                NetworkChangedListener observer = weakReference.get();
                if (observer != null) {
                    observer.onNetworkChanged(networkInfo);
                } else {
                    iterator.remove();
                }
            }
        }
    }

    @Override
    public void registerNetworkChangedReceiver(Context context) {
        final IntentFilter mFilter = new IntentFilter();
        mFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        context.registerReceiver(mBroadcastReceiver, mFilter);
    }
}
