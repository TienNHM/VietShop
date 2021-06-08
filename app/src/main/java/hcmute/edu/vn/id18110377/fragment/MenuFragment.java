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
import hcmute.edu.vn.id18110377.adapter.ItemMenuAdapter;
import hcmute.edu.vn.id18110377.entity.ItemMenu;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MenuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MenuFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MenuFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MenuFragment.
     */
    // TODO: Rename and change types and number of parameters
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
        List<ItemMenu> lstItemMenu = ItemMenu.createListMenuItem(lstItemMenuTitle, lstItemMenuImg);
        ItemMenuAdapter adapter = new ItemMenuAdapter(lstItemMenu);

        RecyclerView rv_account = view.findViewById(R.id.rv_account);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rv_account.setLayoutManager(layoutManager);
        rv_account.setAdapter(adapter);

        return view;
    }
}