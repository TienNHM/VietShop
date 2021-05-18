package hcmute.edu.vn.id18110377.entity;

import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.List;

public class ItemMenu {
    private String title;
    private Drawable img;
    private Drawable background;

    public ItemMenu() {

    }

    public ItemMenu(String title, Drawable img) {
        this.title = title;
        this.img = img;
    }

    public ItemMenu(String title, Drawable img, Drawable background) {
        this.background = background;
        new ItemMenu(title, img);
    }

    public static List<ItemMenu> createListMenuItem(List<String> lstTitle, List<Drawable> lstImg) {
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

    public Drawable getImg() {
        return img;
    }

    public void setImg(Drawable img) {
        this.img = img;
    }

    public Drawable getBackground() {
        return background;
    }

    public void setBackground(Drawable background) {
        this.background = background;
    }

    @Override
    public String toString() {
        return "ItemMenu{" +
                "title='" + title + '\'' +
                ", img=" + img +
                ", background=" + background +
                '}';
    }
}
