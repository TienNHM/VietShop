package hcmute.edu.vn.id18110377.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import hcmute.edu.vn.id18110377.R;
import hcmute.edu.vn.id18110377.dbhelper.BillDbHelper;
import hcmute.edu.vn.id18110377.dbhelper.CartDbHelper;
import hcmute.edu.vn.id18110377.dbhelper.StoreDbHelper;
import hcmute.edu.vn.id18110377.entity.Bill;
import hcmute.edu.vn.id18110377.entity.Cart;
import hcmute.edu.vn.id18110377.entity.Product;
import hcmute.edu.vn.id18110377.entity.Store;

import static hcmute.edu.vn.id18110377.MainActivity.user;

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
    @BindView(R.id.btnOrder)
    Button btnOrder;

    public static Cart cart;
    private Product product;
    private int quantity = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart_detail);

        if (cart == null) return;

        ButterKnife.bind(this);
        findViewById(R.id.btnBack).setOnClickListener(view -> finish());
        setCartInfo();
        btnOrder.setOnClickListener(this::setOrder);
        btnPlus.setOnClickListener(this::setPlus);
        btnSubtract.setOnClickListener(this::setSubtract);
    }

    private void setCartInfo() {
        if (cart == null)
            return;
        this.product = cart.getProduct();

        productTitle.setText(product.getName());
        productPrice.setText(product.getPrice().toString());
        cartPrice.setText(cart.getTotalPrice().toString());
        setAddress();
        setQuantity();
        setBackgroundImage();

        StoreDbHelper storeDbHelper = new StoreDbHelper(this);
        Store store = storeDbHelper.getStoreById(product.getStoreId());

        if (store == null)
            return;
        txtProductStore.setText(store.getName());
        txtProductStoreAddress.setText(store.getAddress());
    }

    private void setOrder(@NotNull View view) {
        BillDbHelper billDbHelper = new BillDbHelper(view.getContext());
        String deliveryAddress = txtDeliveryAddress.getText().toString();
        Bill bill = new Bill(user.getId(), cart.getId(), deliveryAddress);
        long result = billDbHelper.insert(bill);
        if (result > 0) {
            CartDbHelper cartDbHelper = new CartDbHelper(view.getContext());
            cart.setCartOrdered();
            cart.setQuantity(quantity);
            cartDbHelper.update(cart);
            Toast.makeText(view.getContext(), "Đã đặt hàng thành công.", Toast.LENGTH_SHORT).show();
            btnOrder.setText("Đã đặt hàng");
            btnOrder.setEnabled(false);
        }
    }

    private void setBackgroundImage() {
        ArrayList<Bitmap> images = product.getProductImages();
        if (images != null) {
            if (images.size() > 0)
                productImage.setImageBitmap(images.get(0));
        }
    }

    private void setAddress() {
        String address = user.getAddress();
        if (address.length() > 0) {
            txtDeliveryAddress.setText(address);
        }
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
                quantity = Integer.parseInt(txtQuantity.getText().toString());
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
