package br.com.livroandroid.carros.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import br.com.livroandroid.carros.R;
import br.com.livroandroid.carros.domain.Carro;
import br.com.livroandroid.carros.fragments.CarroFragment;


public class CarroActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carro);

        setUpToolbar();

        Carro carro = getIntent().getParcelableExtra("carro");
        getSupportActionBar().setTitle(carro.nome);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if(savedInstanceState == null){
            CarroFragment carroFragment = new CarroFragment();
            carroFragment.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction().add(R.id.carro_fragment, carroFragment).commit();
        }

        ImageView appBarImg = (ImageView) findViewById(R.id.appbar_img);
        Picasso.with(getContext()).load(carro.urlFoto).fit().into(appBarImg);
    }
}
