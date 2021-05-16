package hcmute.edu.vn.id18110377;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final BottomNavigationView.OnNavigationItemSelectedListener
            onNavItemSelectedListener = item -> {
        Fragment fragment;
        switch (item.getItemId()) {
            case R.id.account:
                fragment = new AccountFragment();
                loadFragment(fragment);
                return true;
            case R.id.notifications:
                return true;
            case R.id.search:
                return true;
        }
        return false;
    };
    Spinner spinner;
    String[] location = new String[]{"TP.HCM", "Bình Dương", "Đồng Nai", "Long An", "BR-VT"};
    ArrayAdapter<String> adapter;
    WormDotsIndicator wormDotsIndicator;
    ViewPager2 viewPager;
    FragmentStateAdapter fragmentStateAdapter;
    List<Drawable> img_list = new ArrayList<Drawable>() {
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigationView = this.findViewById(R.id.bottom_navbar);
        navigationView.setOnNavigationItemSelectedListener(onNavItemSelectedListener);

        spinner = this.findViewById(R.id.spiner);
        adapter = new ArrayAdapter<>(this, R.layout.dropdown_spinner, location);
        spinner.setAdapter(adapter);

        ImageButton acc = this.findViewById(R.id.account);
        acc.setOnClickListener(v -> {
            Fragment fragment = new AccountFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.main_activity, fragment);
            transaction.addToBackStack(null);
            transaction.commit();
        });
        /*img_list.add(ResourcesCompat.getDrawable(getResources(), R.drawable.bg_food, null));
        img_list.add(ResourcesCompat.getDrawable(getResources(), R.drawable.bg, null));
        img_list.add(ResourcesCompat.getDrawable(getResources(), R.drawable.food_background, null));
         */

        //wormDotsIndicator = (WormDotsIndicator) findViewById(R.id.worm_dots_indicator);
        /*viewPager = findViewById(R.id.view_pager);
        fragmentStateAdapter = new FragmentStateAdapter(this) {
            @Override
            public int getItemCount() {
                return img_list.size();
            }

            @NonNull
            @Override
            public Fragment createFragment(int position) {
                return new ItemFragment(img_list.get(position));
            }
        };
        viewPager.setAdapter(fragmentStateAdapter);*/
        //wormDotsIndicator.setViewPager2(viewPager);
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