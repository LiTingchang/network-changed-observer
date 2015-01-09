
package me.ltc.demo.networkchange;

import me.ltc.demo.networkchange.util.ConnectivityUtil;
import me.ltc.demo.networkchange.util.NetworkChangedListener;
import android.app.Activity;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends Activity implements NetworkChangedListener{
    
    private static final String TAG = "NetworkChangedActicity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    
    @Override
    protected void onResume() {
        ((MyApplication)getApplication()).getNetworkChangedObserver().registerNetworkChangedObserver(this);
        super.onRestart();
    }
    
    @Override
    protected void onPause() {
        ((MyApplication)getApplication()).getNetworkChangedObserver().deregisterNetworkChangedObserver(this);
        super.onPause();
    }

    @Override
    public void onNetworkChanged(NetworkInfo networkInfo) {
        // TODO Auto-generated method stub
        Log.e(TAG, "onNetworkChanged isConnected:" + ConnectivityUtil.isConnected(networkInfo)
                + " isWifi:" + ConnectivityUtil.isConnectedWifi(networkInfo)
                + " isMobile:" + ConnectivityUtil.isConnectedMobile(networkInfo)
                + " isConnectedFast:" +ConnectivityUtil.isConnectedFast(networkInfo));
    }

}
