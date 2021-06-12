package hcmute.edu.vn.id18110377.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import hcmute.edu.vn.id18110377.activity.BillHistory;
import hcmute.edu.vn.id18110377.activity.CartDetail;
import hcmute.edu.vn.id18110377.activity.ChangeLanguage;
import hcmute.edu.vn.id18110377.activity.ConnectAccount;
import hcmute.edu.vn.id18110377.activity.Discount;
import hcmute.edu.vn.id18110377.activity.Help;
import hcmute.edu.vn.id18110377.activity.LogIn;
import hcmute.edu.vn.id18110377.activity.SettingsAccount;
import hcmute.edu.vn.id18110377.activity.ShareApp;
import hcmute.edu.vn.id18110377.activity.SignUp;
import hcmute.edu.vn.id18110377.activity.Wallet;

public class MenuItem {
    private String title;
    public static List<String> lstItemMenuTitle;
    public static HashMap<String, Class> menuItem;

    static {
        lstItemMenuTitle = Arrays.asList(
                "Đăng nhập", "Đơn hàng", "Nhập mã khuyến mãi", "Tài khoản liên kết", "Số dư tài khoản",
                "Lịch sử hóa đơn", "Giới thiệu bạn bè", "Chọn ngôn ngữ", "Liên hệ hỗ trợ", "Cài đặt tài khoản", "Đăng xuất"
        );
        menuItem = new HashMap<>();
        menuItem.put("Đăng nhập", SignUp.class);
        menuItem.put("Đơn hàng", CartDetail.class);
        menuItem.put("Nhập mã khuyến mãi", Discount.class);
        menuItem.put("Tài khoản liên kết", ConnectAccount.class);
        menuItem.put("Số dư tài khoản", Wallet.class);
        menuItem.put("Lịch sử hóa đơn", BillHistory.class);
        menuItem.put("Giới thiệu bạn bè", ShareApp.class);
        menuItem.put("Chọn ngôn ngữ", ChangeLanguage.class);
        menuItem.put("Liên hệ hỗ trợ", Help.class);
        menuItem.put("Cài đặt tài khoản", SettingsAccount.class);
        menuItem.put("Đăng xuất", LogIn.class);
    }

    private Integer discountImageID;
    private Integer bgImageID;

    public MenuItem() {

    }

    public MenuItem(String title, Integer discountImageID, Integer bgImageID) {
        this.title = title;
        this.discountImageID = discountImageID;
        this.bgImageID = bgImageID;
    }

    public MenuItem(String title, Integer discountImageID) {
        this(title, discountImageID, null);
    }

    public static List<MenuItem> createListMenuItem(List<String> lstTitle, List<Integer> lstImg) {
        int num = lstImg.size() < lstTitle.size() ? lstImg.size() : lstTitle.size();
        List<MenuItem> menu = new ArrayList<MenuItem>();
        for (int i = 0; i < num; i++) {
            menu.add(new MenuItem(lstTitle.get(i), lstImg.get(i)));
        }

        return menu;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getDiscountImageID() {
        return discountImageID;
    }

    public void setDiscountImageID(Integer discountImageID) {
        this.discountImageID = discountImageID;
    }

    public Integer getBgImageID() {
        return bgImageID;
    }

    public void setBgImageID(Integer bgImageID) {
        this.bgImageID = bgImageID;
    }

    public static Class getLayout(String title) {
        return menuItem.get(title);
    }
}
