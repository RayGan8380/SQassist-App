package com.example.sqassist;

import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import kotlinx.coroutines.flow.SharedFlow;

public class NavFragment extends AppCompatActivity {
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BottomNavigationView navView;
        setContentView(R.layout.nav_fragment);

        navView = findViewById(R.id.navView);
        navView.setItemIconTintList(null); //For navigation bar changing selected effect
        navView.setOnItemSelectedListener(navListener);

        //When log in always show home fragment first
        getSupportFragmentManager().beginTransaction().replace(R.id.home_container, new HomeFragment()).commit();

    }

    //Click the navigation icon to navigate to different fragment
    private BottomNavigationView.OnItemSelectedListener navListener =
            item -> {
                Fragment selectedFragment = null;
                switch (item.getItemId()) {
                    case R.id.home_icon:
                        selectedFragment = new HomeFragment();
                        break;
                    case R.id.shop_icon:
                        selectedFragment = new ShopFragment();
                        break;
                    case R.id.news_icon:
                        selectedFragment = new NewsFragment();
                        break;
                    case R.id.forum_icon:
                        selectedFragment = new ForumFragment();
                        break;
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.home_container, selectedFragment).commit();

                return true;
            };

}