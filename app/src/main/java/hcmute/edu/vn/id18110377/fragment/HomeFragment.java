package hcmute.edu.vn.id18110377.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;

import java.util.List;

import hcmute.edu.vn.id18110377.R;
import hcmute.edu.vn.id18110377.adapter.ProductAdapter;
import hcmute.edu.vn.id18110377.adapter.ProductTypeAdapter;
import hcmute.edu.vn.id18110377.dbhelper.ProductDbHelper;
import hcmute.edu.vn.id18110377.dbhelper.ProductTypeDbHelper;
import hcmute.edu.vn.id18110377.entity.Product;
import hcmute.edu.vn.id18110377.entity.ProductType;
import hcmute.edu.vn.id18110377.layout.ProductDetail;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    public static final String PROMO_PRODUCT_ID = "productId";
    public static final String PRODUCT_TYPE_ID = "productType";

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

        ProductDbHelper productDbHelper = new ProductDbHelper(this.getContext());
        //Promos
        setPromoItem(view, productDbHelper);

        //Products
        setProductItem(view, productDbHelper);

        return view;
    }

    private void setPromoItem(View view, ProductDbHelper productDbHelper) {
        List<Product> promoProducts = productDbHelper.getPromoProducts(4);
        ProductAdapter productAdapter = new ProductAdapter(getContext(), promoProducts);
        GridView gv_promo = view.findViewById(R.id.homePromo);
        gv_promo.setOnItemClickListener((parent, view1, position, id) -> {
            Intent intent = new Intent(this.getContext(), ProductDetail.class);
            Log.i("%%%%%%%%%%%%%%", (String.valueOf(productAdapter.getItemId(position))));
            intent.putExtra(PROMO_PRODUCT_ID, productAdapter.getItemId(position));
            startActivity(intent);
        });
        gv_promo.setAdapter(productAdapter);
    }

    private void setProductItem(View view, ProductDbHelper productDbHelper) {
        ProductTypeDbHelper productTypeDbHelper = new ProductTypeDbHelper(this.getContext());
        List<ProductType> productTypes = productTypeDbHelper.getAllProductTypes();
        ProductTypeAdapter productTypeAdapter = new ProductTypeAdapter(getContext(), productTypes);
        GridView gv_product = view.findViewById(R.id.homeProduct);
        gv_product.setOnItemClickListener((parent, view1, position, id) -> {
            Intent intent = new Intent(this.getContext(), ProductDetail.class);
            intent.putExtra(PRODUCT_TYPE_ID, productTypeAdapter.getItemId(position));
            startActivity(intent);
        });
        gv_product.setAdapter(productTypeAdapter);
    }
}