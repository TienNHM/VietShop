package hcmute.edu.vn.id18110377.adapter;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import hcmute.edu.vn.id18110377.R;
import hcmute.edu.vn.id18110377.entity.Product;
import hcmute.edu.vn.id18110377.layout.ProductDetail;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private List<Product> lstProduct;

    public ProductAdapter(List<Product> lstProduct) {
        this.lstProduct = lstProduct;
    }

    public List<Product> getLstProduct() {
        return lstProduct;
    }

    public void setLstProduct(List<Product> lstProduct) {
        this.lstProduct = lstProduct;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.product_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = lstProduct.get(position);
        holder.tvTitle.setText(product.getName());
        holder.ibtnItem.setImageBitmap(product.getImage());
        //TODO
        holder.ivSpecialImage.setImageBitmap(null);
    }

    @Override
    public int getItemCount() {
        return lstProduct.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageButton ibtnItem;
        TextView tvTitle;
        ImageView ivSpecialImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ibtnItem = itemView.findViewById(R.id.ibtnProduct);
            tvTitle = itemView.findViewById(R.id.tv_product_name);
            ivSpecialImage = itemView.findViewById(R.id.iv_special_image);
            Log.i("===============", (String) tvTitle.getText());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i("========ClickCLICKClick=======", (String) tvTitle.getText());
                    Intent intent = new Intent(v.getContext(), ProductDetail.class);
                    itemView.getContext().startActivity(intent);
                }
            });
        }
    }

}
