package hcmute.edu.vn.id18110377.fragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import hcmute.edu.vn.id18110377.R;
import hcmute.edu.vn.id18110377.activity.LogIn;
import hcmute.edu.vn.id18110377.adapter.RecyleItemViewAdapter;
import hcmute.edu.vn.id18110377.entity.MenuItem;

public class MenuFragment extends Fragment {

    @BindView(R.id.menuLogin)
    LinearLayout menuLogin;
    @BindView(R.id.menuLogout)
    LinearLayout menuLogout;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public MenuFragment() {
        // Required empty public constructor
    }

    public static MenuFragment newInstance(String param1, String param2) {
        MenuFragment fragment = new MenuFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_menu, container, false);
        ButterKnife.bind(this, view);

        setMenuItemSection(view);
        setLoginClick(view);
        setLogoutClick(view);

        menuLogout.setVisibility(View.GONE);
        return view;
    }

    private void setMenuItemSection(View view) {
        List<MenuItem> lstMenuItems = MenuItem.createListMenuItem();
        RecyleItemViewAdapter adapter = new RecyleItemViewAdapter(lstMenuItems);
        RecyclerView rv_account = view.findViewById(R.id.rv_menu_item);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rv_account.setLayoutManager(layoutManager);
        rv_account.setAdapter(adapter);
    }

    private void setLoginClick(View view) {
        menuLogin.setOnClickListener(view1 -> {
            Intent intent = new Intent(view.getContext(), LogIn.class);
            view.getContext().startActivity(intent);
            menuLogin.setVisibility(View.GONE);
            menuLogout.setVisibility(View.VISIBLE);
        });
    }

    private void setLogoutClick(View view) {
        menuLogout.setOnClickListener(view1 -> {
            AlertDialog dialog = new AlertDialog.Builder(view.getContext())
                    .setTitle("Đăng xuất")
                    .setMessage("Bạn có muốn đăng xuất khỏi ứng dụng?")
                    .setPositiveButton("Có", (dialogInterface, i) -> {
                        //TODO Xóa session
                        Toast.makeText(view.getContext(), "Đã đăng xuất thành công", Toast.LENGTH_SHORT).show();
                        menuLogout.setVisibility(View.GONE);
                        menuLogin.setVisibility(View.VISIBLE);
                    })
                    .setNegativeButton("Không", null)
                    .setIcon(R.drawable.shutdown)
                    .show();

        });
    }
}