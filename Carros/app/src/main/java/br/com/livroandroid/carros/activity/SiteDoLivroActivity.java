package br.com.livroandroid.carros.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import br.com.livroandroid.carros.R;
import br.com.livroandroid.carros.fragments.SiteDoLivroFragment;

public class SiteDoLivroActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_site_do_livro);

        setUpToolbar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.site_do_livro);

        if(savedInstanceState == null) {
            SiteDoLivroFragment fragmentSiteDoLivro = new SiteDoLivroFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.container, fragmentSiteDoLivro).commit();
        }

    }
}
