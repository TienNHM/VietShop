package hcmute.edu.vn.id18110377.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import hcmute.edu.vn.id18110377.R;
import hcmute.edu.vn.id18110377.dbhelper.StoreDbHelper;
import hcmute.edu.vn.id18110377.entity.Cart;
import hcmute.edu.vn.id18110377.entity.Product;
import hcmute.edu.vn.id18110377.entity.Store;

public class CartDetail extends AppCompatActivity {
    @BindView(R.id.productImage)
    ImageView productImage;
    @BindView(R.id.productTitle)
    TextView productTitle;
    @BindView(R.id.txtQuantity)
    TextInputEditText txtQuantity;
    @BindView(R.id.productPrice)
    TextView productPrice;
    @BindView(R.id.productStore)
    TextView txtProductStore;
    @BindView(R.id.productStoreAddress)
    TextView txtProductStoreAddress;
    @BindView(R.id.txtDeliveryAddress)
    TextInputEditText txtDeliveryAddress;
    @BindView(R.id.cartPrice)
    TextView cartPrice;

    public static Cart cart;
    private Product product;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart_detail);

        ButterKnife.bind(this);
        findViewById(R.id.btnBack).setOnClickListener(view -> finish());

        getCart();
    }

    private void getCart() {
        if (cart == null)
            return;
        this.product = cart.getProduct();
        productImage.setImageBitmap(product.getImage());
        productTitle.setText(product.getName());
        productPrice.setText(product.getPrice().toString());
        cartPrice.setText(cart.getTotalPrice().toString());
        setQuantity();

        StoreDbHelper storeDbHelper = new StoreDbHelper(this);
        Store store = storeDbHelper.getStoreById(product.getStoreId());

        if (store == null)
            return;
        txtProductStore.setText(store.getName());
        txtProductStoreAddress.setText(store.getAddress());
    }

    private void setQuantity() {
        txtQuantity.setText(cart.getQuantity().toString());
        txtQuantity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (txtQuantity.getText().length() <= 0)
                    txtQuantity.setText("0");
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                calcPrice();
            }

            @Override
            public void afterTextChanged(Editable editable) {
                calcPrice();
            }
        });
    }

    private void calcPrice() {
        String quantity = txtQuantity.getText().toString();
        if (quantity.length() <= 0)
            cartPrice.setText("0");
        else {
            double totalPrice = product.getPrice() * Integer.getInteger(quantity);
            cartPrice.setText(String.valueOf(cartPrice));
        }
    }
}
