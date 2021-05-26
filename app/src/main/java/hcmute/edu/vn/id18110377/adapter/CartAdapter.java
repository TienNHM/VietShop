package hcmute.edu.vn.id18110377.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import hcmute.edu.vn.id18110377.R;
import hcmute.edu.vn.id18110377.entity.Product;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    public List<Product> productList;

    public CartAdapter(List<Product> productList) {
        this.productList = productList;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_cart, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.cartImage.setImageResource(product.getMainImageID());
        holder.cartTitle.setText(product.getProductName());
    }

    @Override
    public int getItemCount() {
        return productList == null ? 0 : productList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView cartImage;
        TextView cartTitle;
        TextView cartDetail;
        Button btnCheckout;
        Button btnCancelCart;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cartImage = itemView.findViewById(R.id.cart_img);
            cartTitle = itemView.findViewById(R.id.cart_title);
            cartDetail = itemView.findViewById(R.id.cart_detail);
            btnCheckout = itemView.findViewById(R.id.btnCheckout);
            btnCancelCart = itemView.findViewById(R.id.btnCancelCart);
        }
    }
}
