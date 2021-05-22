package hcmute.edu.vn.id18110377.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import hcmute.edu.vn.id18110377.layout.BillHistory;
import hcmute.edu.vn.id18110377.layout.Cart;
import hcmute.edu.vn.id18110377.layout.ChooseLanguage;
import hcmute.edu.vn.id18110377.layout.ConnectAccount;
import hcmute.edu.vn.id18110377.layout.Discount;
import hcmute.edu.vn.id18110377.layout.Help;
import hcmute.edu.vn.id18110377.layout.Logout;
import hcmute.edu.vn.id18110377.layout.SettingsAccount;
import hcmute.edu.vn.id18110377.layout.ShareApp;
import hcmute.edu.vn.id18110377.layout.SignUp;
import hcmute.edu.vn.id18110377.layout.Wallet;

public class ItemMenu {
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
        menuItem.put("Đơn hàng", Cart.class);
        menuItem.put("Nhập mã khuyến mãi", Discount.class);
        menuItem.put("Tài khoản liên kết", ConnectAccount.class);
        menuItem.put("Số dư tài khoản", Wallet.class);
        menuItem.put("Lịch sử hóa đơn", BillHistory.class);
        menuItem.put("Giới thiệu bạn bè", ShareApp.class);
        menuItem.put("Chọn ngôn ngữ", ChooseLanguage.class);
        menuItem.put("Liên hệ hỗ trợ", Help.class);
        menuItem.put("Cài đặt tài khoản", SettingsAccount.class);
        menuItem.put("Đăng xuất", Logout.class);
    }

    private Integer leftImageID;
    private Integer bgImageID;

    public ItemMenu() {

    }

    public ItemMenu(String title, Integer leftImageID, Integer bgImageID) {
        this.title = title;
        this.leftImageID = leftImageID;
        this.bgImageID = bgImageID;
    }

    public ItemMenu(String title, Integer leftImageID) {
        this(title, leftImageID, null);
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

    public static Class getLayout(String title) {
        return menuItem.get(title);
    }
}
