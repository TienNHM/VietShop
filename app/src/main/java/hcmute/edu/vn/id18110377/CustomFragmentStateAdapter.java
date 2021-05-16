package hcmute.edu.vn.id18110377;

import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.List;

public class CustomFragmentStateAdapter extends FragmentStateAdapter {

    List<Drawable> img_list;

    public CustomFragmentStateAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);


    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return null;
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
