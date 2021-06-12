package hcmute.edu.vn.id18110377.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import hcmute.edu.vn.id18110377.R;
import hcmute.edu.vn.id18110377.adapter.NotificationAdapter;
import hcmute.edu.vn.id18110377.entity.Notification;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NotificationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NotificationFragment extends Fragment {

    @BindView(R.id.rvNotifications)
    RecyclerView rvNotifications;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public NotificationFragment() {
        // Required empty public constructor
    }

    public static NotificationFragment newInstance(String param1, String param2) {
        NotificationFragment fragment = new NotificationFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notification, container, false);

        ButterKnife.bind(this, view);

        List<Notification> notificationList = getAllNotifications();
        NotificationAdapter adapter = new NotificationAdapter(notificationList);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext());
        rvNotifications.setLayoutManager(manager);
        rvNotifications.setAdapter(adapter);

        return view;
    }

    private List<Notification> getAllNotifications() {
        List<Notification> notifications = new ArrayList<>();
        notifications.add(new Notification(0, "account", "Read", "Đăng nhập ngay để trải nghiệm những tiện ích dành riêng cho bạn.", "Đăng nhập"));
        notifications.add(new Notification(1, "guide", "Read", "Hướng dẫn sử dụng ứng dụng cho người dùng lần đầu đăng ký tài khoản.", "Hướng dẫn"));
        notifications.add(new Notification(2, "sale", "Unread", "Chào Lễ lớn, Đón khuyến mãi to cùng VietShop", "Khuyến mãi"));
        notifications.add(new Notification(3, "cart", "Unread", "Bạn có đơn hàng chưa thanh toán.", "Thanh toán"));
        for (Notification x :
                notifications) {
            Log.i("=====", x.toString());
        }
        return notifications;
    }
}