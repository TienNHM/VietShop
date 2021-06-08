package hcmute.edu.vn.id18110377.layout;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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
        ((ImageView) findViewById(R.id.productImage)).setImageBitmap(product.getImage());
        ((TextView) findViewById(R.id.productTitle)).setText(product.getName());
        ((TextView) findViewById(R.id.productPrice)).setText(product.getPrice().toString());

        StoreDbHelper storeDbHelper = new StoreDbHelper(this);
        Store store = storeDbHelper.getStoreById(product.getStoreId());
        Log.e("=====================", product.getStoreId().toString());
        if (store == null)
            return;
        ((TextView) findViewById(R.id.productStore)).setText(store.getName());
        ((TextView) findViewById(R.id.productStoreAddress)).setText(store.getAddress());
    }
}
