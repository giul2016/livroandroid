package br.com.livroandroid.asstproject.acivity;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;

import br.com.livroandroid.asstproject.R;


public class BaseActivity extends AppCompatActivity {

    protected DrawerLayout drawerLayout;
    protected NavigationView navigationView;

    protected void setUpToolbar(){
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        if(toolbar != null){
            setSupportActionBar(toolbar);
        }
    }


    protected void setUpDrawerlayout(){
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_reorder_white_24dp);

        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        navigationView = (NavigationView)findViewById(R.id.navigation_view);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                item.setChecked(true);

                startGirlFragment(item.getTitle().toString());
                drawerLayout.closeDrawers();
                return true;
            }
        });
    }

    private void startGirlFragment(String girlIdName) {
        Intent intent = new Intent();
        intent.setClass(this, GirlsActivity.class);
        intent.putExtra("nome", girlIdName);
        startActivity(intent);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(drawerLayout != null) {
            if (item.getItemId() == android.R.id.home) {
                drawerLayout.openDrawer(Gravity.START);
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
