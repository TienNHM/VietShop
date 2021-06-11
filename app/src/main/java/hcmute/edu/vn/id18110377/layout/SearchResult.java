package hcmute.edu.vn.id18110377.layout;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.GridView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import hcmute.edu.vn.id18110377.R;
import hcmute.edu.vn.id18110377.adapter.ProductAdapter;
import hcmute.edu.vn.id18110377.dbhelper.ProductDbHelper;
import hcmute.edu.vn.id18110377.entity.Product;

public class SearchResult extends AppCompatActivity {
    @BindView(R.id.chipGroupProductType)
    ChipGroup chipGroupProductType;
    @BindView(R.id.chipClothes)
    Chip chipClothes;
    @BindView(R.id.chipDrink)
    Chip chipDrinks;
    @BindView(R.id.chipFood)
    Chip chipFood;
    @BindView(R.id.chipElectronic)
    Chip chipElectronic;
    @BindView(R.id.chipFreshFood)
    Chip chipFreshFood;
    @BindView(R.id.chipFruit)
    Chip chipFruit;
    @BindView(R.id.chipOthers)
    Chip chipOthers;
    @BindView(R.id.chipAllProductTypes)
    Chip chipAllTypes;

    private ArrayList<String> selectedProductTypes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        findViewById(R.id.btnBack).setOnClickListener(v -> {
            finish();
        });

        getSearchResult();
        chipClothes.setOnCheckedChangeListener(this::setChipClothesOnCheckedChanged);
        chipFood.setOnCheckedChangeListener(this::setChipFoodOnCheckedChanged);
        chipFreshFood.setOnCheckedChangeListener(this::setChipFreshFoodOnCheckedChanged);
        chipFruit.setOnCheckedChangeListener(this::setChipFruitOnCheckedChanged);
        chipDrinks.setOnCheckedChangeListener(this::setChipDrinkOnCheckedChanged);
        chipElectronic.setOnCheckedChangeListener(this::setChipElectronicOnCheckedChanged);
        chipOthers.setOnCheckedChangeListener(this::setChipOthersOnCheckedChanged);
        chipAllTypes.setOnCheckedChangeListener(this::setChipAllTypesOnCheckedChanged);
    }

    private void setChipClothesOnCheckedChanged(CompoundButton compoundButton, boolean b) {
        if (b) {
            selectedProductTypes.add("1");
            chipAllTypes.setChecked(false);
        } else
            selectedProductTypes.remove("1");
        getSearchResultByType();
    }

    private void setChipFoodOnCheckedChanged(CompoundButton compoundButton, boolean b) {
        if (b) {
            selectedProductTypes.add("2");
            chipAllTypes.setChecked(false);
        } else
            selectedProductTypes.remove("2");
        getSearchResultByType();
    }

    private void setChipFruitOnCheckedChanged(CompoundButton compoundButton, boolean b) {
        if (b) {
            selectedProductTypes.add("3");
            chipAllTypes.setChecked(false);
        } else
            selectedProductTypes.remove("3");
        getSearchResultByType();
    }

    private void setChipDrinkOnCheckedChanged(CompoundButton compoundButton, boolean b) {
        if (b) {
            selectedProductTypes.add("4");
            chipAllTypes.setChecked(false);
        } else
            selectedProductTypes.remove("4");
        getSearchResultByType();
    }

    private void setChipElectronicOnCheckedChanged(CompoundButton compoundButton, boolean b) {
        if (b) {
            selectedProductTypes.add("5");
            chipAllTypes.setChecked(false);
        } else
            selectedProductTypes.remove("5");
        getSearchResultByType();
    }

    private void setChipFreshFoodOnCheckedChanged(CompoundButton compoundButton, boolean b) {
        if (b) {
            selectedProductTypes.add("6");
            chipAllTypes.setChecked(false);
        } else
            selectedProductTypes.remove("6");
        getSearchResultByType();
    }

    private void setChipOthersOnCheckedChanged(CompoundButton compoundButton, boolean b) {
        if (b) {
            selectedProductTypes.add("7");
            chipAllTypes.setChecked(false);
        } else
            selectedProductTypes.remove("7");
        getSearchResultByType();
    }

    private void setChipAllTypesOnCheckedChanged(CompoundButton compoundButton, boolean b) {
        if (b) {
            chipFood.setChecked(false);
            chipClothes.setChecked(false);
            chipDrinks.setChecked(false);
            chipFreshFood.setChecked(false);
            chipFruit.setChecked(false);
            chipElectronic.setChecked(false);
            chipOthers.setChecked(false);
            selectedProductTypes = new ArrayList<>();
            for (int i = 1; i <= 7; i++)
                selectedProductTypes.add(String.valueOf(i));
            getSearchResult();
        } else {
            selectedProductTypes.clear();
        }
        getSearchResultByType();
    }

    private void getSearchResultByType() {
        ProductDbHelper productDbHelper = new ProductDbHelper(this);
        ArrayList<Product> products = productDbHelper.getProductByListTypeId(this.selectedProductTypes);
        setProductsOnGridView(products);
    }

    private void getSearchResult() {
        String txtSearch = getIntent().getStringExtra("search");
        ProductDbHelper productDbHelper = new ProductDbHelper(this);
        ArrayList<Product> products = productDbHelper.getFullSearchResult(txtSearch);
        if (products == null)
            return;

        setProductsOnGridView(products);
    }

    private void setProductsOnGridView(ArrayList<Product> products) {
        TextView tvNumberProducts = findViewById(R.id.tvNumProducts);
        tvNumberProducts.setText(String.valueOf(products.size()));
        ProductAdapter adapter = new ProductAdapter(this, products);

        GridView gridView = findViewById(R.id.searchResult);
        gridView.setOnItemClickListener((parent, view1, position, id) -> {
            Intent intent = new Intent(this, ProductDetail.class);
            intent.putExtra(ProductDetail.PRODUCT_ID, adapter.getItemId(position));
            startActivity(intent);
        });
        gridView.setAdapter(adapter);
    }
}