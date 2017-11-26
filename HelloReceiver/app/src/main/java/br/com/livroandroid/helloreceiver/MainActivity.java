package br.com.livroandroid.helloreceiver;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickDispararBroadcast(View view){
        sendBroadcast(new Intent("BINGO"));
        Toast.makeText(this, "Intent enviada!", Toast.LENGTH_SHORT).show();
    }
}
