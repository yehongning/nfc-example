package cc.metapro.nfc;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ToggleButton;

/**
 * Created by Boollean on 2017/7/15.
 */

public class NFCMainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        if (getSupportFragmentManager().findFragmentById(R.id.fragment_container) == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, new RecyclerViewFragment())
                    .commit();
        }
    }

    private void initView() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Card card = new Card();
                CardLab.get(NFCMainActivity.this).addCard(card);
                Intent intent = NFCCardActivity.newIntent(NFCMainActivity.this, card.getId());
                startActivity(intent);
            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View headerView = navigationView.getHeaderView(0);
        final ToggleButton toggleButton = headerView.findViewById(R.id.toggleButton);
        toggleButton.setChecked(PrefHelper.getInstance(this).getBoolean(PrefHelper.PREF_DETAILED_READ_MODE, true));
        toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (toggleButton.isChecked()) {
                    PrefHelper.getInstance(NFCMainActivity.this).putBoolean(PrefHelper.PREF_DETAILED_READ_MODE, true);
                } else {
                    PrefHelper.getInstance(NFCMainActivity.this).putBoolean(PrefHelper.PREF_DETAILED_READ_MODE, false);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_cards) {

        } else if (id == R.id.nav_setting) {

            final Intent nfcintent = new Intent(Settings.ACTION_NFC_SETTINGS);
            startActivity(nfcintent);

        } else if (id == R.id.nav_about) {
            Intent intent = AboutUsActivity.newIntent(this);
            startActivity(intent);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}