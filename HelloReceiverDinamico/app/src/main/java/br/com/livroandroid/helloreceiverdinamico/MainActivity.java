package br.com.livroandroid.helloreceiverdinamico;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "livroandroid";

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(TAG, "Hello Receiver Dinamico!!!");
            TextView textView = (TextView)findViewById(R.id.text);
            textView.setText("Mensagem recebida pelo HelloReceiver Dinâmico");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btEnviar).setOnClickListener(this);
        registerReceiver(receiver, new IntentFilter("BINGO"));
    }

    @Override
    public void onClick(View v) {
        sendBroadcast(new Intent("BINGO"));
        Toast.makeText(this, "Intent enviada!", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(receiver);
        super.onDestroy();
    }
}
