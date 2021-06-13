package hcmute.edu.vn.id18110377.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.switchmaterial.SwitchMaterial;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import hcmute.edu.vn.id18110377.R;
import hcmute.edu.vn.id18110377.dbhelper.BillDbHelper;
import hcmute.edu.vn.id18110377.entity.Bill;

import static hcmute.edu.vn.id18110377.MainActivity.user;

public class BillHistory extends AppCompatActivity {

    @BindView(R.id.btnBack)
    ImageButton btnBack;
    @BindView(R.id.swViewAll)
    SwitchMaterial swViewAll;
    @BindView(R.id.rvBillHistory)
    RecyclerView rvBillHistory;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bill_history);
        ButterKnife.bind(this);

        btnBack.setOnClickListener(view -> finish());
        swViewAll.setOnClickListener(this::setViewAll);
    }

    private void setViewAll(View view) {
        BillDbHelper billDbHelper = new BillDbHelper(this);
        ArrayList<Bill> bills = new ArrayList<>();
        if (swViewAll.isChecked())
            bills = billDbHelper.getAllBills(user.getId());
        else
            bills = billDbHelper.getUnpaidBills(user.getId());

    }
}
