package hcmute.edu.vn.id18110377.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import hcmute.edu.vn.id18110377.R;
import hcmute.edu.vn.id18110377.entity.Cart;
import hcmute.edu.vn.id18110377.layout.CartDetail;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    public static final String PARAM_CART = "hcmute.edu.vn.id18110377.adapter.Cart";

    public ArrayList<Cart> carts;

    public CartAdapter(ArrayList<Cart> carts) {
        this.carts = carts;
    }

    public ArrayList<Cart> getCarts() {
        return carts;
    }

    public void setCarts(ArrayList<Cart> carts) {
        this.carts = carts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.cart_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Cart cart = carts.get(position);
        holder.cart = cart;
        holder.cartImage.setImageBitmap(cart.getProduct().getImage());
        holder.cartTitle.setText(cart.getProduct().getName());
        holder.cartQuantity.setText(String.valueOf(cart.getQuantity()));
        String status = cart.getStatus() == "Paid" ? "Đã thanh toán" : "Chưa thanh toán";
        holder.cartStatus.setText(status);
    }

    @Override
    public int getItemCount() {
        return carts == null ? 0 : carts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView cartImage;
        TextView cartTitle;
        TextView cartQuantity;
        TextView cartStatus;
        Cart cart;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cartImage = itemView.findViewById(R.id.cartImage);
            cartTitle = itemView.findViewById(R.id.cartProductName);
            cartQuantity = itemView.findViewById(R.id.cartQuantity);
            cartStatus = itemView.findViewById(R.id.cartStatus);

            itemView.setOnClickListener(v -> {
                CartDetail.cart = cart;
                Intent intent = new Intent(itemView.getContext(), CartDetail.class);
                itemView.getContext().startActivity(intent);
            });
        }
    }
}
