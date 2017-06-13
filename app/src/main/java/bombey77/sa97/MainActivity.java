package bombey77.sa97;


import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static final String LOG = "myLogs";

    ServiceConnection serConn;

    Intent intent;

    boolean bind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intent = new Intent("bombey77.firstservicebinding.com");
        intent.setPackage("bombey77.servicesa97");

        serConn = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                Log.d(LOG, "onServiceConnected");
                bind = true;
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                Log.d(LOG, "onServiceDisconnected");
                bind = false;
            }
        };
    }

    public void onClickStartService(View view) {
        startService(intent);
        Log.d(LOG, "onClickStartService");
    }

    public void onClickStopService(View view) {
        stopService(intent);
        Log.d(LOG, "onClickStopService");
    }

    public void onClickBind(View view) {
        bindService(intent, serConn, BIND_AUTO_CREATE);
        bind = true;
        Log.d(LOG, "onClickBind");
    }

    public void onClickUnbind(View view) {
        if (!bind)return;
        unbindService(serConn);
        bind = false;
        Log.d(LOG, "onClickUnbind");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(LOG, "onDestroy - MainActivity");
        Log.d(LOG, "onDestroy - MainActivity");
        Log.d(LOG, "onDestroy - MainActivity");
        onClickUnbind(null);
    }
}
