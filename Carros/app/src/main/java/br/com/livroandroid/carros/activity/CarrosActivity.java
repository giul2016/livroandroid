package br.com.livroandroid.carros.activity;

import android.os.Bundle;

import br.com.livroandroid.carros.R;
import br.com.livroandroid.carros.fragments.CarrosFragment;

public class CarrosActivity extends BaseActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carros);

        setUpToolbar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String tipo = getString(getIntent().getIntExtra("tipo", 0));
        getSupportActionBar().setTitle(tipo);

        if(savedInstanceState == null){
            CarrosFragment carrosFragment = new CarrosFragment();
            carrosFragment.setArguments(getIntent().getExtras());

            getSupportFragmentManager().beginTransaction().add(R.id.container, carrosFragment).commit();
        }
    }
}
