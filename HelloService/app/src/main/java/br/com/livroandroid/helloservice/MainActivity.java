package br.com.livroandroid.helloservice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickStart(View view){
        startService(new Intent(this, HelloService.class));
    }

    public void onClickStop(View view){
        stopService(new Intent(this, HelloService.class));
    }
}
