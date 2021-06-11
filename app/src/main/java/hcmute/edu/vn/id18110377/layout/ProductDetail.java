package hcmute.edu.vn.id18110377.layout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import butterknife.BindView;
import butterknife.ButterKnife;
import hcmute.edu.vn.id18110377.MainActivity;
import hcmute.edu.vn.id18110377.R;
import hcmute.edu.vn.id18110377.dbhelper.CartDbHelper;
import hcmute.edu.vn.id18110377.dbhelper.ProductDbHelper;
import hcmute.edu.vn.id18110377.entity.Cart;
import hcmute.edu.vn.id18110377.entity.Product;
import hcmute.edu.vn.id18110377.entity.Store;
import hcmute.edu.vn.id18110377.fragment.CartFragment;

public class ProductDetail extends AppCompatActivity {

    public static final String PRODUCT_ID = "productId";
    private Product product;
    @BindView(R.id.btnAddCart)
    Button btnAddCart;
    @BindView(R.id.btnViewCart)
    Button btnViewCart;
    @BindView(R.id.subtract)
    ImageButton btnSubtract;
    @BindView(R.id.plus)
    ImageButton btnPlus;
    @BindView(R.id.txtQuantity)
    EditText txtQuantity;
    @BindView(R.id.svReview)
    ScrollView svReview;
    private int quantity = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_detail);

        ButterKnife.bind(this);
        this.quantity = 0;
        txtQuantity.setText("0");
        btnViewCart.setVisibility(View.GONE);

        findViewById(R.id.btnBack_detail).setOnClickListener(v -> {
            finish();
        });

        setProduct();
        btnSubtract.setOnClickListener(this::setSubtractQuantity);
        btnPlus.setOnClickListener(this::setAddQuantity);
        btnAddCart.setOnClickListener(this::setAddCart);
        btnViewCart.setOnClickListener(this::setViewCart);
    }

    private void setViewCart(View view) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        //transaction.add(new CartFragment(), "hcmute.edu.vn.id18110377.layout.ProductDetail");
        transaction.add(R.id.frame_product_detail, new CartFragment());
        transaction.addToBackStack(null);
        transaction.commit();
        finish();
    }

    private void setAddCart(View view) {
        if (this.quantity <= 0) {
            Toast.makeText(this, "Số lượng sản phẩm tối thiểu là 1.", Toast.LENGTH_SHORT).show();
            return;
        }
        if (MainActivity.user != null) {
            CartDbHelper cartDbHelper = new CartDbHelper(this);
            Cart cart = new Cart(
                    MainActivity.user.getId(),
                    this.product.getId(),
                    this.quantity,
                    MainActivity.user.getAddress());
            long re = cartDbHelper.insert(cart);
            if (re > 0) {
                Toast.makeText(this, "Đã thêm vào giỏ hàng!", Toast.LENGTH_SHORT).show();
                btnAddCart.setVisibility(View.GONE);
                btnViewCart.setVisibility(View.VISIBLE);
            } else
                Toast.makeText(this, "Đã xảy ra lỗi khi thêm giỏ hàng.", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(this, LogIn.class);
            startActivity(intent);
        }
    }

    private void setAddQuantity(View view) {
        this.quantity += 1;
        txtQuantity.setText(String.valueOf(quantity));
    }

    private void setSubtractQuantity(View view) {
        if (this.quantity <= 0) {
            this.quantity = 0;
            txtQuantity.setText("0");
        } else {
            quantity -= 1;
            txtQuantity.setText(String.valueOf(quantity));
        }
    }

    private void setProduct() {
        Bundle bundle = getIntent().getExtras();
        ProductDbHelper productDbHelper = new ProductDbHelper(this);
        this.product = productDbHelper.getProductById((int) bundle.getLong(PRODUCT_ID));

        if (this.product != null) {
            if (product.getProductImages() != null)
                ((ImageView) findViewById(R.id.productImage)).setImageBitmap(product.getProductImages().get(0));

            ((TextView) findViewById(R.id.productTitle)).setText(this.product.getName());
            ((TextView) findViewById(R.id.productPrice)).setText(product.getPrice().toString());

            String productDetail = product.getDetail();
            TextView tvProductDetail = findViewById(R.id.productDescription);
            if (productDetail == null)
                tvProductDetail.setVisibility(TextView.GONE);
            else
                tvProductDetail.setText(product.getDetail());

            Store store = productDbHelper.getStore(this.product.getId());
            if (store != null) {
                ((TextView) findViewById(R.id.productStore)).setText(store.getName());
                ((TextView) findViewById(R.id.productStoreAddress)).setText(store.getAddress());
            }

            svReview.setVisibility(View.GONE);
        }
    }
}