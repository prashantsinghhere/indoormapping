package mapping.indoor.com.mapindoor;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.indooratlas.android.sdk.IALocation;
import com.indooratlas.android.sdk.IALocationListener;
import com.indooratlas.android.sdk.IALocationManager;
import com.indooratlas.android.sdk.IALocationRequest;

public class MainActivity extends AppCompatActivity {

    IALocationManager appLocationManager;

    IALocationListener appLocationListener = new IALocationListener(){

        @Override
        public void onLocationChanged(IALocation appLocation) {
            TextView lattext = (TextView) findViewById(R.id.textView);
            loctext.setText(String.valueOf(appLocation.getLatitude()));
            TextView longtext = (TextView) findViewById(R.id.textView2);
            loctext.setText(String.valueOf(appLocation.getLongitude()));
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        appLocationManager = new IALocationManager.create(this);
    }

    protected void onResume() {

        super.onResume();
        appLocationManager.requestLocationUpdates(IALocationRequest.create(),appLocationListener);
    }

    protected void onPause() {

        super.onPause();
        appLocationManager.removeLocationUpdates(appLocationListener);
    }

    protected void onDestroy() {
        appLocationManager.destroy();
        super.onDestroy();
    }

}
