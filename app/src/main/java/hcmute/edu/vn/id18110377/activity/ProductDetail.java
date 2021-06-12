package hcmute.edu.vn.id18110377.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import hcmute.edu.vn.id18110377.R;
import hcmute.edu.vn.id18110377.dbhelper.ProductDbHelper;
import hcmute.edu.vn.id18110377.entity.Product;
import hcmute.edu.vn.id18110377.entity.Store;

public class ProductDetail extends AppCompatActivity {

    public static final String PRODUCT_ID = "productId";
    private Product product;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_detail);

        findViewById(R.id.btnBack_detail).setOnClickListener(v -> {
            finish();
        });

        setProduct();
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
                tvProductDetail.setVisibility(TextView.INVISIBLE);
            else
                tvProductDetail.setText(product.getDetail());

            Store store = productDbHelper.getStore(this.product.getId());
            if (store != null) {
                ((TextView) findViewById(R.id.productStore)).setText(store.getName());

                ((TextView) findViewById(R.id.productStoreAddress)).setText(store.getAddress());
            }
        }
    }
}