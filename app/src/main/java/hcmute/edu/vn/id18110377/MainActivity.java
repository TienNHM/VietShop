package hcmute.edu.vn.id18110377;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import hcmute.edu.vn.id18110377.fragment.CartFragment;
import hcmute.edu.vn.id18110377.fragment.HomeFragment;
import hcmute.edu.vn.id18110377.fragment.MenuFragment;
import hcmute.edu.vn.id18110377.fragment.NotificationFragment;
import hcmute.edu.vn.id18110377.fragment.SearchFragment;

public class MainActivity extends AppCompatActivity {

    public static Resources mainResources;

    private final BottomNavigationView.OnNavigationItemSelectedListener
            onNavItemSelectedListener = item -> {
        Fragment fragment;
        switch (item.getItemId()) {
            case R.id.menuHome:
                fragment = new HomeFragment();
                loadFragment(fragment);
                return true;
            case R.id.menuCart:
                fragment = new CartFragment();
                loadFragment(fragment);
                return true;
            case R.id.menuMain:
                fragment = new MenuFragment();
                loadFragment(fragment);
                return true;
            case R.id.menuNotifications:
                fragment = new NotificationFragment();
                loadFragment(fragment);
                return true;
            case R.id.menuDiscount:
                fragment = new SearchFragment();
                loadFragment(fragment);
                return true;
        }
        return false;
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigationView = this.findViewById(R.id.bottom_navbar);
        navigationView.setOnNavigationItemSelectedListener(onNavItemSelectedListener);

        if (savedInstanceState == null) {
            navigationView.setSelectedItemId(R.id.menuHome);
        }
        mainResources = getResources();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.getMenuInflater().inflate(R.menu.bottom_navbar, menu);
        return true;
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}