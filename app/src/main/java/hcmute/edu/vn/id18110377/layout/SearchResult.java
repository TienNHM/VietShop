package hcmute.edu.vn.id18110377.layout;

import android.content.Intent;
import android.os.Bundle;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import hcmute.edu.vn.id18110377.R;
import hcmute.edu.vn.id18110377.adapter.ProductAdapter;
import hcmute.edu.vn.id18110377.dbhelper.ProductDbHelper;
import hcmute.edu.vn.id18110377.entity.Product;

public class SearchResult extends AppCompatActivity {

    public static final String PRODUCT_ID = "productId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        findViewById(R.id.btnBack).setOnClickListener(v -> {
            finish();
        });

        getSearchResult();
    }

    private void getSearchResult() {
        GridView gridView = findViewById(R.id.searchResult);
        Intent intent = getIntent();
        String txtSearch = intent.getStringExtra("search");

        ProductDbHelper productDbHelper = new ProductDbHelper(this);
        ArrayList<Product> products = productDbHelper.getFullSearchResult(txtSearch);

        ProductAdapter adapter = new ProductAdapter(this, products);

        gridView.setOnItemClickListener((parent, view1, position, id) -> {
            Intent i = new Intent(this, ProductDetail.class);
            intent.putExtra(PRODUCT_ID, adapter.getItemId(position));
            startActivity(intent);
        });
        gridView.setAdapter(adapter);
    }

}