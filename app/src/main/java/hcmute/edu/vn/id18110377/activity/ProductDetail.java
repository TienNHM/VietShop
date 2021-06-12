package hcmute.edu.vn.id18110377.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import hcmute.edu.vn.id18110377.R;
import hcmute.edu.vn.id18110377.dbhelper.ProductDbHelper;
import hcmute.edu.vn.id18110377.entity.Product;
import hcmute.edu.vn.id18110377.entity.Store;

public class ProductDetail extends AppCompatActivity {

    public static final String PRODUCT_ID = "productId";
    private Product product;
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
        findViewById(R.id.btnBack_detail).setOnClickListener(v -> {
            finish();
        });

        setProduct();
        btnSubtract.setOnClickListener(this::setSubtractQuantity);
        btnPlus.setOnClickListener(this::setAddQuantity);
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