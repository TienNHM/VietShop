package hcmute.edu.vn.id18110377.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
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
    EditText txtQuantity;
    @BindView(R.id.subtract)
    ImageButton btnSubtract;
    @BindView(R.id.plus)
    ImageButton btnPlus;
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
    private int quantity = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart_detail);

        ButterKnife.bind(this);
        findViewById(R.id.btnBack).setOnClickListener(view -> finish());

        setCartInfo();
        btnPlus.setOnClickListener(this::setPlus);
        btnSubtract.setOnClickListener(this::setSubtract);
    }

    private void setCartInfo() {
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
        this.quantity = cart.getQuantity();
        txtQuantity.setText(cart.getQuantity().toString());
        txtQuantity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (txtQuantity.getText().equals("0"))
                    txtQuantity.setText("");
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (txtQuantity.getText().length() <= 0)
                    txtQuantity.setText("0");
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
        double totalPrice = product.getPrice() * Integer.valueOf(quantity);
        cartPrice.setText(String.valueOf(totalPrice));
    }

    private void setSubtract(View view) {
        if (quantity <= 0) return;
        this.quantity--;
        txtQuantity.setText(String.valueOf(quantity));
    }

    private void setPlus(View view) {
        this.quantity++;
        txtQuantity.setText(String.valueOf(quantity));
    }
}
