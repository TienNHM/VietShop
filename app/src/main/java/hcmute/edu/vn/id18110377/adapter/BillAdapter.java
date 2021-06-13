package hcmute.edu.vn.id18110377.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import hcmute.edu.vn.id18110377.R;
import hcmute.edu.vn.id18110377.entity.Bill;

public class BillAdapter extends RecyclerView.Adapter<BillAdapter.ViewHolder> {
    private ArrayList<Bill> bills;

    public BillAdapter(ArrayList<Bill> bills) {
        this.bills = bills;
    }

    @NonNull
    @Override
    public BillAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull BillAdapter.ViewHolder holder, int position) {

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
        TextView billDeliveryAddress;
        TextView billTime;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            productImage = itemView.findViewById(R.id.billProductImage);
            productName = itemView.findViewById(R.id.billProductName);
            productPrice = itemView.findViewById(R.id.productPrice);
            billQuantity = itemView.findViewById(R.id.billQuantity);
            billTotalPrice = itemView.findViewById(R.id.billTotalPrice);
            billStatus = itemView.findViewById(R.id.billStatus);
            billDeliveryAddress = itemView.findViewById(R.id.billDeliveryAddress);
            billTime = itemView.findViewById(R.id.billTime);
        }
    }
}
