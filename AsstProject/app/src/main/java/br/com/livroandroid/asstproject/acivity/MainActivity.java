package br.com.livroandroid.asstproject.acivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import br.com.livroandroid.asstproject.R;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpToolbar();
        setUpDrawerlayout();

    }
}
