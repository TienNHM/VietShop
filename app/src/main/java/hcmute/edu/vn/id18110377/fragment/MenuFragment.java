package hcmute.edu.vn.id18110377.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import hcmute.edu.vn.id18110377.R;
import hcmute.edu.vn.id18110377.adapter.RecyleItemViewAdapter;
import hcmute.edu.vn.id18110377.entity.MenuItem;

public class MenuFragment extends Fragment {

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

    private ArrayList<Integer> createMenuItemImage() {
        ArrayList<Integer> lstItemMenuImg = new ArrayList<>();
        lstItemMenuImg.add(R.drawable.key);
        lstItemMenuImg.add(R.drawable.shopping_cart);
        lstItemMenuImg.add(R.drawable.discount);
        lstItemMenuImg.add(R.drawable.user_folder);
        lstItemMenuImg.add(R.drawable.wallet);
        lstItemMenuImg.add(R.drawable.history);
        lstItemMenuImg.add(R.drawable.add);
        lstItemMenuImg.add(R.drawable.translation);
        lstItemMenuImg.add(R.drawable.messaging);
        lstItemMenuImg.add(R.drawable.settings);
        lstItemMenuImg.add(R.drawable.shutdown);
        return lstItemMenuImg;
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_menu, container, false);

        List<String> lstItemMenuTitle = Arrays.asList(getResources().getStringArray(R.array.account_menu_items));
        ArrayList<Integer> lstItemMenuImg = createMenuItemImage();
        List<MenuItem> lstMenuItems = MenuItem.createListMenuItem(lstItemMenuTitle, lstItemMenuImg);
        RecyleItemViewAdapter adapter = new RecyleItemViewAdapter(lstMenuItems);

        RecyclerView rv_account = view.findViewById(R.id.rv_account);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rv_account.setLayoutManager(layoutManager);
        rv_account.setAdapter(adapter);

        return view;
    }
}