package bombey77.sa97;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static final String LOG = "myLogs";

    ServiceConnection servConnect;

    Intent intent = new Intent("bombey77.sa97service.bomba");

    private boolean bind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intent.setPackage("bombey77.servicesa97");

        Log.d(LOG, "onCreate");

        servConnect = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            bind = true;
            Log.d(LOG, "onServiceConnected");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            bind = false;
            Log.d(LOG, "onServiceDisconnected");
        }
        };
    }

    public void onClickStartService(View view) {
        startService(intent);
        Log.d(LOG, "onStartService");
    }

    public void onClickStopService(View view) {
        stopService(intent);
        Log.d(LOG, "onStopService");
    }

    public void onClickBind(View view) {
        bindService(intent, servConnect, BIND_AUTO_CREATE);
        Log.d(LOG, "bindService");
    }

    public void onClickUnbind(View view) {
        if (!bind) return;
        unbindService(servConnect);
        bind = false;
        Log.d(LOG, "onUnbindService");
    }
}
