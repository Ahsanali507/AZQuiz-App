package com.ahsan.azquizapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    NavigationView nav;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        nav = findViewById(R.id.navmenu);
        drawer = findViewById(R.id.drawer);

        toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.open, R.string.close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        getSupportFragmentManager().beginTransaction().replace(R.id.container,new home()).commit();
        nav.setCheckedItem(R.id.home);

        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            Fragment temp;
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int itemId = menuItem.getItemId();
                if (itemId == R.id.home) {
                    temp = new home();
                } else if (itemId == R.id.quiz) {
                    temp = new TakingQuiz();
                } else if (itemId == R.id.result) {
                    temp = new ShowResult();
                } else if(itemId==R.id.github){
                    // Create a Uri object to represent the GitHub link you want to open
                    Uri uri = Uri.parse("https://github.com/Ahsan-507"); // Replace with the GitHub link you want to open

                    // Create an Intent with the ACTION_VIEW action and the Uri object, and start the browser activity
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                }


                getSupportFragmentManager().beginTransaction().replace(R.id.container, temp).commit();
                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }
}