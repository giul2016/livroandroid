package br.com.livroandroid.carros.activity;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.livroandroid.carros.R;

public class BaseActivity extends livroandroid.lib.activity.BaseActivity {

    protected DrawerLayout drawerLayout;
    private MediaPlayer media = new MediaPlayer();

    protected void setUpToolbar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if(toolbar != null){
            setSupportActionBar(toolbar);
        }
    }

    protected void setupNavDrawer(){
        final ActionBar actionBar = getSupportActionBar();

        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        actionBar.setDisplayHomeAsUpEnabled(true);
        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);



        NavigationView navigationView = (NavigationView)findViewById(R.id.nav_view);
        if(navigationView != null && drawerLayout != null){
            setNavViewValues(navigationView, R.string.nav_drawer_username, R.string.nav_drawer_email, R.mipmap.ic_launcher);

            navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(MenuItem item) {
                    item.setChecked(true);
                    drawerLayout.closeDrawers();
                    onNavDrawerItemSelected(item);
                    return true;
                }
            });
        }
    }

    public static void setNavViewValues(NavigationView navigationView, int nav_drawer_username, int nav_drawer_email, int ic_launcher) {
        View viewHeader = navigationView.getHeaderView(0);
        TextView tNome = (TextView)viewHeader.findViewById(R.id.tNome);
        TextView tEmail = (TextView)viewHeader.findViewById(R.id.tEmail);
        ImageView imgView = (ImageView)viewHeader.findViewById(R.id.img);
        tNome.setText(nav_drawer_username);
        tEmail.setText(nav_drawer_email);
        imgView.setImageResource(ic_launcher);
    }

    private void onNavDrawerItemSelected(MenuItem menuItem){
        switch (menuItem.getItemId()){
            case R.id.nav_item_carros_todos:
                toast("Clicou em carros");
                break;
            case R.id.nav_item_carros_classicos:
                startCarrosActivity(R.string.classicos);
                break;
            case R.id.nav_item_carros_esportivos:
                startCarrosActivity(R.string.esportivos);
                break;
            case R.id.nav_item_carros_luxo:
                startCarrosActivity(R.string.luxo);
                break;
            case R.id.nav_item_site_livro:
                startActivity(new Intent().setClass(getContext(), SiteDoLivroActivity.class));
                break;
            case R.id.nav_item_settings:
                toast("Clicou em configurções");
                break;
        }
    }

    private void startCarrosActivity(int tipo) {
        Intent intent = new Intent();
        intent.setClass(getContext(), CarrosActivity.class);
        intent.putExtra("tipo", tipo);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                if(drawerLayout != null){
                    openDrawer();
                    return true;
                }
        }
        return super.onOptionsItemSelected(item);
    }

    protected void openDrawer(){
        if(drawerLayout != null){
            drawerLayout.openDrawer(Gravity.START);
        }
    }

    protected void closeDrawer(){
        if(drawerLayout != null){
            drawerLayout.closeDrawer(Gravity.START);
        }
    }

    private Context baseContext(){
        return this;
    }
}
