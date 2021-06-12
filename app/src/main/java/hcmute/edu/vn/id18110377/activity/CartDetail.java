package hcmute.edu.vn.id18110377.activity;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import hcmute.edu.vn.id18110377.R;
import hcmute.edu.vn.id18110377.dbhelper.StoreDbHelper;
import hcmute.edu.vn.id18110377.entity.Cart;
import hcmute.edu.vn.id18110377.entity.Product;
import hcmute.edu.vn.id18110377.entity.Store;

public class CartDetail extends AppCompatActivity {
    public static Cart cart;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart_detail);

        ImageButton img = findViewById(R.id.btnBack);
        img.setOnClickListener(v -> {
            cart = null;
            finish();
        });

        getCart();
    }

    private void getCart() {
        if (cart == null)
            return;
        Product product = cart.getProduct();
        //((ImageView) findViewById(R.id.productImage)).setImageBitmap(product.getImage());
        ((TextView) findViewById(R.id.productTitle)).setText(product.getName());
        ((TextInputEditText) findViewById(R.id.txtQuantity)).setText(cart.getQuantity().toString());
        ((TextView) findViewById(R.id.productPrice)).setText(product.getPrice().toString());
        ((TextView) findViewById(R.id.cartPrice)).setText(cart.getTotalPrice().toString());
        StoreDbHelper storeDbHelper = new StoreDbHelper(this);
        Store store = storeDbHelper.getStoreById(product.getStoreId());

        if (store == null)
            return;
        ((TextView) findViewById(R.id.productStore)).setText(store.getName());
        ((TextView) findViewById(R.id.productStoreAddress)).setText(store.getAddress());
    }
}
