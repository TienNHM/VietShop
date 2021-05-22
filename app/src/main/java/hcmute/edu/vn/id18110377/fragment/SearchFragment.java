package hcmute.edu.vn.id18110377.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import hcmute.edu.vn.id18110377.R;
import hcmute.edu.vn.id18110377.adapter.GridViewProductAdapter;
import hcmute.edu.vn.id18110377.entity.Product;

public class SearchFragment extends Fragment {
    @BindView(R.id.btnBack_search)
    ImageButton btnBack;

    @BindView(R.id.txtSearch_search)
    EditText txtSearch;

    @BindView(R.id.gvSponsor)
    GridView gvSponsor;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SearchFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SearchFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SearchFragment newInstance(String param1, String param2) {
        SearchFragment fragment = new SearchFragment();
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
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        ButterKnife.bind(this, view);

        List<Product> productList = createListSponsor();
        GridViewProductAdapter gv_adapter = new GridViewProductAdapter(getContext(), productList);
        gvSponsor.setAdapter(gv_adapter);

        return view;
    }

    private List<Product> createListSponsor() {
        List<Product> productList = new ArrayList<>();
        productList.add(new Product(0, "Soda", R.drawable.soda));
        productList.add(new Product(1, "Milk", R.drawable.milk_bottle));
        productList.add(new Product(2, "Juice", R.drawable.orange_juice));
        productList.add(new Product(3, "Fast food", R.drawable.fast_food));
        return productList;
    }
}