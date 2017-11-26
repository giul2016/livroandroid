package br.com.livroandroid.asstproject.acivity;

import android.os.Bundle;

import br.com.livroandroid.asstproject.R;
import br.com.livroandroid.asstproject.fragment.GirlsFragment;


public class GirlsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_girls);

        setUpToolbar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if(savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_girl, new GirlsFragment()
                            .setArgumentsAndReturn(getIntent().getExtras()))
                    .commit();
        }
    }
}
