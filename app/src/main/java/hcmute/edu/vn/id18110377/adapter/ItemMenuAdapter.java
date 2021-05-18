package hcmute.edu.vn.id18110377.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import hcmute.edu.vn.id18110377.R;
import hcmute.edu.vn.id18110377.entity.ItemMenu;

public class ItemMenuAdapter extends RecyclerView.Adapter<ItemMenuAdapter.ViewHolder> {
    private List<ItemMenu> lstItemMenu;

    public ItemMenuAdapter(List<ItemMenu> lstItemMenu) {
        this.lstItemMenu = lstItemMenu;
    }

    public List<ItemMenu> getLstItemMenu() {
        return lstItemMenu;
    }

    public void setLstItemMenu(ArrayList<ItemMenu> lstItemMenu) {
        this.lstItemMenu = lstItemMenu;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.menu_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ItemMenu itemMenu = lstItemMenu.get(position);
        ImageButton expanded_menu_item = holder.expanded_menu_item;
        expanded_menu_item.setBackground(itemMenu.getBackground());
        TextView menu_item_title = holder.menu_item_title;
        menu_item_title.setText(itemMenu.getTitle());
        ImageView menu_item_img = holder.menu_item_img;
        menu_item_img.setBackground(itemMenu.getBackground());
    }

    @Override
    public int getItemCount() {
        Log.i("menu size", Integer.toString(lstItemMenu.size()));
        return lstItemMenu.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageButton expanded_menu_item;
        TextView menu_item_title;
        ImageView menu_item_img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            menu_item_img = itemView.findViewById(R.id.menu_item_img);
            menu_item_title = itemView.findViewById(R.id.menu_item_title);
            expanded_menu_item = itemView.findViewById(R.id.expanded_menu_item);
        }
    }
}
