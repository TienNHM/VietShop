package hcmute.edu.vn.id18110377.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;
import java.util.List;

import hcmute.edu.vn.id18110377.R;
import hcmute.edu.vn.id18110377.entity.Notification;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {

    private static final HashMap<String, Integer> mapNotify_Image;

    static {
        mapNotify_Image = new HashMap<>();
        mapNotify_Image.put("cart", R.drawable.shopping_cart);
        mapNotify_Image.put("sale", R.drawable.land_sales);
        mapNotify_Image.put("guide", R.drawable.guide);
        mapNotify_Image.put("account", R.drawable.person);
    }

    private List<Notification> notifications;

    public NotificationAdapter(List<Notification> notifications) {
        this.notifications = notifications;
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.notify_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Notification item = notifications.get(position);
        Log.i("=================notification: ===================", item.toString());

        ImageView ivNotitfyImg = holder.ivNotitfyImg;

        ivNotitfyImg.setImageResource(mapNotify_Image.get(item.getType()).intValue());

        TextView txtNotifyTitle = holder.txtNotifyTitle;
        txtNotifyTitle.setText(item.getType());

        TextView txtNotifyShortDetail = holder.txtNotifyShortDetail;
        txtNotifyShortDetail.setText(item.getShortDetail());
    }

    @Override
    public int getItemCount() {

        return notifications == null ? 0 : notifications.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivNotitfyImg;
        TextView txtNotifyTitle;
        TextView txtNotifyShortDetail;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ivNotitfyImg = itemView.findViewById(R.id.notify_img);
            txtNotifyShortDetail = itemView.findViewById(R.id.notify_shortdetail);
            txtNotifyTitle = itemView.findViewById(R.id.notify_title);
        }
    }
}
