package hcmute.edu.vn.id18110377.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import hcmute.edu.vn.id18110377.R;
import hcmute.edu.vn.id18110377.entity.Bill;
import hcmute.edu.vn.id18110377.entity.Cart;
import hcmute.edu.vn.id18110377.entity.Product;

public class BillAdapter extends RecyclerView.Adapter<BillAdapter.ViewHolder> {
    private ArrayList<Bill> bills;
    private View view;

    public BillAdapter(ArrayList<Bill> bills) {
        this.bills = bills;
    }

    @NonNull
    @Override
    public BillAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        this.view = inflater.inflate(R.layout.bill_item, parent, false);
        return new BillAdapter.ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull BillAdapter.ViewHolder holder, int position) {
        Bill bill = bills.get(position);
        Cart cart = bill.getCart();
        Product product = cart.getProduct();

        holder.productImage.setImageBitmap(product.getImage());
        holder.productName.setText(product.getName());
        holder.productPrice.setText(product.getPrice().toString());
        holder.billQuantity.setText(cart.getQuantity());
        holder.billTotalPrice.setText(cart.getTotalPrice().toString());
        holder.billStatus.setText(bill.getStatus());
        holder.billTime.setText(bill.getDate());
    }

    @Override
    public int getItemCount() {
        return (bills == null) ? 0 : bills.size();
    }

    public ArrayList<Bill> getBills() {
        return bills;
    }

    public void setBills(ArrayList<Bill> bills) {
        this.bills = bills;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView productImage;
        TextView productName;
        TextView productPrice;
        TextView billQuantity;
        TextView billTotalPrice;
        TextView billStatus;
        TextView billTime;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            productImage = itemView.findViewById(R.id.billProductImage);
            productName = itemView.findViewById(R.id.billProductName);
            productPrice = itemView.findViewById(R.id.productPrice);
            billQuantity = itemView.findViewById(R.id.billQuantity);
            billTotalPrice = itemView.findViewById(R.id.billTotalPrice);
            billStatus = itemView.findViewById(R.id.billStatus);
            billTime = itemView.findViewById(R.id.billTime);
        }
    }
}
