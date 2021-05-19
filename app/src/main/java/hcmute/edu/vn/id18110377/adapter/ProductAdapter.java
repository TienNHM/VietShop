package hcmute.edu.vn.id18110377.adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import hcmute.edu.vn.id18110377.entity.Product;

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
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return lstProduct.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

        }
    }
}
