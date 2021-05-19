package hcmute.edu.vn.id18110377;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import hcmute.edu.vn.id18110377.adapter.GridViewProductAdapter;
import hcmute.edu.vn.id18110377.entity.Product;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        String[] location = new String[]{"TP.HCM", "Bình Dương", "Đồng Nai", "Long An", "BR-VT"};
        Spinner spinner = view.findViewById(R.id.spiner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), R.layout.dropdown_spinner, location);
        spinner.setAdapter(adapter);

        List<Product> productList = new ArrayList<>();
        productList.add(new Product(0, "Beer", R.drawable.beer, R.drawable.sale));
        productList.add(new Product(1, "Milk", R.drawable.milk_bottle));
        productList.add(new Product(2, "Juice", R.drawable.orange_juice));
        productList.add(new Product(3, "", R.drawable.cocktail, R.drawable.sale));

        GridViewProductAdapter gv_adapter = new GridViewProductAdapter(getContext(), productList);
        GridView gv_product = view.findViewById(R.id.gv_product);
        gv_product.setAdapter(gv_adapter);

        return view;
    }
}