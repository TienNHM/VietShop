package hcmute.edu.vn.id18110377.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import hcmute.edu.vn.id18110377.R;
import hcmute.edu.vn.id18110377.activity.LogIn;
import hcmute.edu.vn.id18110377.adapter.CartAdapter;
import hcmute.edu.vn.id18110377.dbhelper.CartDbHelper;
import hcmute.edu.vn.id18110377.entity.Cart;

import static hcmute.edu.vn.id18110377.utilities.AccountSessionManager.user;

public class CartFragment extends Fragment {

    @BindView(R.id.noMoreCarts)
    LinearLayout noMoreCarts;
    @BindView(R.id.cartList)
    ScrollView cartList;

    private View view;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public CartFragment() {
        // Required empty public constructor
    }

    @NotNull
    public static CartFragment newInstance(String param1, String param2) {
        CartFragment fragment = new CartFragment();
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
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.fragment_cart, container, false);
        ButterKnife.bind(this, view);

        if (user == null) {
            login();
        } else getUnpaidCart(view);
        return this.view;
    }

    private void login() {
        Intent intent = new Intent(this.getContext(), LogIn.class);
        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (user == null) {
            login();
        } else getUnpaidCart(this.view);
    }

    private void getUnpaidCart(View view) {
        CartDbHelper cartDbHelper = new CartDbHelper(this.getContext());
        ArrayList<Cart> unpaidCarts = cartDbHelper.getUnpaidCart(user.getId());
        if (unpaidCarts.size() > 0) {
            CartAdapter adapter = new CartAdapter(unpaidCarts);
            RecyclerView rvCart = view.findViewById(R.id.rvCart);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
            rvCart.setLayoutManager(layoutManager);
            rvCart.setAdapter(adapter);
            noMoreCarts.setVisibility(View.GONE);
            cartList.setVisibility(View.VISIBLE);

            rvCart.addOnChildAttachStateChangeListener(new RecyclerView.OnChildAttachStateChangeListener() {
                @Override
                public void onChildViewAttachedToWindow(@NonNull View view) {

                }

                @Override
                public void onChildViewDetachedFromWindow(@NonNull View view) {
                    if (adapter.getItemCount() == 0) {
                        noMoreCarts.setVisibility(View.VISIBLE);
                        cartList.setVisibility(View.GONE);
                    }
                }
            });
        } else {
            noMoreCarts.setVisibility(View.VISIBLE);
            cartList.setVisibility(View.GONE);
        }
    }
}