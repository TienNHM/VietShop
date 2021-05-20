package hcmute.edu.vn.id18110377.homepage;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator;

import hcmute.edu.vn.id18110377.R;

public class ItemFragment extends Fragment {

    WormDotsIndicator wormDotsIndicator;
    Drawable drawable;

    public ItemFragment() {
        // Required empty public constructor
    }

    public ItemFragment(Drawable drawable) {
        this.drawable = drawable;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_item, container, false);
        ImageView img_view = view.findViewById(R.id.img_item);
        img_view.setImageDrawable(this.drawable);
        img_view.setScaleType(ImageView.ScaleType.MATRIX);
        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private Bundle drawable2bundle(Drawable drawable) {
        Bundle bundle = new Bundle();
        return bundle;
    }
}