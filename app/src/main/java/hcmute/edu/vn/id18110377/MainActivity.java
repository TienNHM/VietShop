package hcmute.edu.vn.id18110377;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import hcmute.edu.vn.id18110377.dbhelper.AccountDbHelper;
import hcmute.edu.vn.id18110377.dbhelper.UserDbHelper;
import hcmute.edu.vn.id18110377.entity.Account;
import hcmute.edu.vn.id18110377.entity.User;
import hcmute.edu.vn.id18110377.fragment.CartFragment;
import hcmute.edu.vn.id18110377.fragment.DiscountFragment;
import hcmute.edu.vn.id18110377.fragment.HomeFragment;
import hcmute.edu.vn.id18110377.fragment.MenuFragment;
import hcmute.edu.vn.id18110377.fragment.NotificationFragment;
import hcmute.edu.vn.id18110377.utilities.AppUtilities;

public class MainActivity extends AppCompatActivity {
    public static Resources mainResources;
    public static Account account;
    public static User user;
    FirebaseUser currentUser;
    private FirebaseAuth mAuth;

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
            case R.id.menuAvatar:
                fragment = new MenuFragment();
                loadFragment(fragment);
                return true;
            case R.id.menuNotifications:
                fragment = new NotificationFragment();
                loadFragment(fragment);
                return true;
            case R.id.menuDiscount:
                fragment = new DiscountFragment();
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
        checkLogin();

        /*account = AppUtilities.getSession(this);
        if (account != null) {
            UserDbHelper userDbHelper = new UserDbHelper(this);
            user = userDbHelper.getUserByAccountId(account.getId());

            if (user == null){
                account = null;
                AppUtilities.clearSession(this);
                return;
            }
        }*/
    }

    private void checkLogin() {
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            String email = currentUser.getEmail();
            AccountDbHelper accountDbHelper = new AccountDbHelper(this);
            account = accountDbHelper.getAccountByEmail(email);

            UserDbHelper userDbHelper = new UserDbHelper(this);
            user = userDbHelper.getUserByAccountId(account.getId());

            if (user == null) {
                account = null;
                AppUtilities.clearSession(this);
                return;
            } else {
                Toast.makeText(this, "Đã đăng nhập với tên " + user.getFullname(), Toast.LENGTH_SHORT).show();
            }
        }
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