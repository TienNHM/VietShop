package hcmute.edu.vn.id18110377.entity;

import android.graphics.drawable.Drawable;

import java.util.ArrayList;

public class MenuItem {
    private String title;
    private Drawable img;
    private Drawable background;

    public MenuItem() {

    }

    public MenuItem(String title, Drawable img) {
        this.title = title;
        this.img = img;
    }

    public MenuItem(String title, Drawable img, Drawable background) {
        this.background = background;
        new MenuItem(title, img);
    }

    public static ArrayList<MenuItem> createListMenuItem(ArrayList<String> lstTitle, ArrayList<Drawable> lstImg, int num) {
        if (num <= 0) {
            num = lstImg.size() < lstTitle.size() ? lstImg.size() : lstTitle.size();
        }
        ArrayList<MenuItem> menu = new ArrayList<MenuItem>();
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
}
