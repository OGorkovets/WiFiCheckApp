package ogorkovets.greenrivertech.net.wificheckapp;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.graphics.Color;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialize Watch Video Button
        Button btnSrc = (Button) findViewById(R.id.watchvideo);

        TextView tv2 = (TextView) findViewById(R.id.textWiFi);

        boolean wifi = isNetworkOnline();

        if(wifi){
            tv2.setText("WiFi is ON");
            tv2.setTextColor(Color.parseColor("green"));
            btnSrc.setTextColor(Color.parseColor("blue"));
            btnSrc.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this, OtherActivity.class);
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=pUylKKhql2E")));
                }
            });
        }
        else{
            tv2.setText("WiFi is OFF");
            tv2.setTextColor(Color.parseColor("red"));
            btnSrc.setTextColor(Color.parseColor("grey"));
            btnSrc.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Toast.makeText(getApplicationContext(), "WiFi has to be ON",
                            Toast.LENGTH_LONG).show();
                }
            });

        }


    }

    public boolean isNetworkOnline() {
        boolean status=false;
        try{
            ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getNetworkInfo(0);
            if (netInfo != null && netInfo.getState()==NetworkInfo.State.CONNECTED) {
                status= true;
            }else {
                netInfo = cm.getNetworkInfo(1);
                if(netInfo!=null && netInfo.getState()== NetworkInfo.State.DISCONNECTED)
                    status= false;
            }
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return status;

    }
}
