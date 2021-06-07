package hcmute.edu.vn.id18110377.fragment;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import hcmute.edu.vn.id18110377.R;
import hcmute.edu.vn.id18110377.adapter.ProductAdapter;
import hcmute.edu.vn.id18110377.entity.Product;

public class DiscountFragment extends Fragment {

    @BindView(R.id.txtSearchDiscount)
    SearchView txtSearch;

    @BindView(R.id.gvSponsor)
    GridView gvSponsor;

    @BindView(R.id.gvSearchResult)
    GridView gvSearchResult;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DiscountFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DiscountFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DiscountFragment newInstance(String param1, String param2) {
        DiscountFragment fragment = new DiscountFragment();
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
        View view = inflater.inflate(R.layout.fragment_discount, container, false);

        ButterKnife.bind(this, view);

        // List Product Sponsor
        List<Product> productList = createListSponsor();
        ProductAdapter gv_adapter = new ProductAdapter(getContext(), productList);
        gvSponsor.setAdapter(gv_adapter);

        //txtSearch
        txtSearch.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    List<Product> lstSearchResult = getSearchResult();
                    ProductAdapter gv_adapter = new ProductAdapter(getContext(), lstSearchResult);
                    gvSearchResult.setAdapter(gv_adapter);
                    return true;
                }
                return false;
            }
        });
        return view;
    }

    private List<Product> createListSponsor() {
        List<Product> productList = new ArrayList<>();
        productList.add(new Product(1, 0, "Soda", R.drawable.soda));
        productList.add(new Product(2, 1, "Milk", R.drawable.milk_bottle));
        productList.add(new Product(1, 2, "Juice", R.drawable.orange_juice));
        productList.add(new Product(3, 3, "Fast food", R.drawable.fast_food));
        return productList;
    }

    private List<Product> getSearchResult() {
        List<Product> productList = new ArrayList<>();
        productList.add(new Product(1, 1, "Beer", R.drawable.beer));
        productList.add(new Product(2, 1, "Milk", R.drawable.milk_bottle));
        productList.add(new Product(3, 2, "Juice", R.drawable.orange_juice));
        productList.add(new Product(4, 3, "Fast food", R.drawable.fast_food));
        productList.add(new Product(5, 4, "Soda", R.drawable.soda));
        productList.add(new Product(6, 5, "Apple", R.drawable.apple));
        productList.add(new Product(7, 6, "Paprika", R.drawable.paprika));
        productList.add(new Product(8, 4, "Pineaple", R.drawable.pineapple));
        return productList;
    }
}