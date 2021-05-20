package hcmute.edu.vn.id18110377.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import hcmute.edu.vn.id18110377.R;

public class ItemMenu {
    private String title;
    public static List<String> lstItemMenuTitle;
    public static HashMap<String, Integer> menuItem;

    static {
        lstItemMenuTitle = Arrays.asList(
                "Đăng nhập", "Đơn hàng", "Nhập mã khuyến mãi", "Tài khoản liên kết", "Số dư tài khoản",
                "Lịch sử hóa đơn", "Giới thiệu bạn bè", "Chọn ngôn ngữ", "Liên hệ hỗ trợ", "Cài đặt tài khoản", "Đăng xuất"
        );
        menuItem = new HashMap<>();
        menuItem.put("Đăng nhập", R.layout.login);
        menuItem.put("Đơn hàng", R.layout.fragment_cart);
        menuItem.put("Nhập mã khuyến mãi", R.layout.discount);
        menuItem.put("Tài khoản liên kết", R.layout.connect_account);
        menuItem.put("Số dư tài khoản", R.layout.wallet);
        menuItem.put("Lịch sử hóa đơn", R.layout.bill_history);
        menuItem.put("Giới thiệu bạn bè", R.layout.share_app);
        menuItem.put("Chọn ngôn ngữ", R.layout.choose_language);
        menuItem.put("Liên hệ hỗ trợ", R.layout.help);
        menuItem.put("Cài đặt tài khoản", R.layout.settings_account);
        menuItem.put("Đăng xuất", R.layout.logout);
    }

    private Integer leftImageID;
    private Integer bgImageID;

    public ItemMenu() {

    }

    public ItemMenu(String title, Integer leftImageID) {
        this.title = title;
        this.leftImageID = leftImageID;
    }

    public ItemMenu(String title, Integer leftImageID, Integer bgImageID) {
        this.bgImageID = bgImageID;
        new ItemMenu(title, leftImageID);
    }

    public static List<ItemMenu> createListMenuItem(List<String> lstTitle, List<Integer> lstImg) {
        int num = lstImg.size() < lstTitle.size() ? lstImg.size() : lstTitle.size();
        List<ItemMenu> menu = new ArrayList<ItemMenu>();
        for (int i = 0; i < num; i++) {
            menu.add(new ItemMenu(lstTitle.get(i), lstImg.get(i)));
        }

        return menu;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getLeftImageID() {
        return leftImageID;
    }

    public void setLeftImageID(Integer leftImageID) {
        this.leftImageID = leftImageID;
    }

    public Integer getBgImageID() {
        return bgImageID;
    }

    public void setBgImageID(Integer bgImageID) {
        this.bgImageID = bgImageID;
    }

    public static Integer getLayout(String title) {
        return menuItem.get(title);
    }
}
