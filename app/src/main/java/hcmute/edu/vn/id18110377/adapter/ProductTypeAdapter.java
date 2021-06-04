package hcmute.edu.vn.id18110377.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;

import java.util.List;

import hcmute.edu.vn.id18110377.R;
import hcmute.edu.vn.id18110377.entity.ProductType;
import hcmute.edu.vn.id18110377.layout.ProductDetail;

public class ProductTypeAdapter extends BaseAdapter {
    private List<ProductType> productTypes;
    private LayoutInflater inflater;

    public ProductTypeAdapter(Context context, List<ProductType> productTypes) {
        inflater = LayoutInflater.from(context);
        this.productTypes = productTypes;
    }

    public LayoutInflater getInflater() {
        return inflater;
    }

    public void setInflater(LayoutInflater inflater) {
        this.inflater = inflater;
    }

    public List<ProductType> getProductTypes() {
        return productTypes;
    }

    public void setProductTypes(List<ProductType> productTypes) {
        this.productTypes = productTypes;
    }

    @Override
    public int getCount() {
        return productTypes == null ? 0 : productTypes.size();
    }

    @Override
    public Object getItem(int position) {
        return productTypes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return productTypes.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ProductTypeAdapter.ProductTypeView productView;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.product_item, parent, false);

            productView = new ProductTypeAdapter.ProductTypeView();
            productView.ibtnProduct = convertView.findViewById(R.id.ibtnProduct);
            productView.tv_product_name = convertView.findViewById(R.id.tv_product_name);
            productView.iv_special_image = convertView.findViewById(R.id.iv_special_image);

            convertView.setTag(productView);
        } else {
            productView = (ProductTypeAdapter.ProductTypeView) convertView.getTag();
        }


        ProductType productType = productTypes.get(position);

        productView.ibtnProduct.setImageBitmap(productType.getImage());
        //TODO Set Special image
        productView.iv_special_image.setImageBitmap(null);
        productView.tv_product_name.setText(productType.getName());

        return convertView;
    }

    private class ProductTypeView implements View.OnClickListener {
        private ImageButton ibtnProduct;
        private TextView tv_product_name;
        private ImageView iv_special_image;

        @Override
        public void onClick(View v) {
            Log.i("...................", "===================================");
            Intent intent = new Intent(v.getContext(), ProductDetail.class);
            v.getContext().startActivity(intent);
            ActivityCompat.startActivityForResult(new ProductDetail(), intent, 200, new Bundle());
        }
    }
}
