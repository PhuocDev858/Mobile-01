package com.tranhuuphuoc.tranhuuphuoc_2123110236;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;

public class CustomerActivity extends AppCompatActivity {

    private ImageView imgAvatar;
    private TextView txtUserName;
    private RecyclerView recyclerView;
    private CustomerOptionAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);

        imgAvatar = findViewById(R.id.imgAvatar);
        txtUserName = findViewById(R.id.txtUserName);
        recyclerView = findViewById(R.id.recyclerOptions);

        // 🔹 Tạm thời gán dữ liệu test
        txtUserName.setText("Phuoc Otaku");
        imgAvatar.setImageResource(R.drawable.ic_user); // ảnh avatar mặc định

        // 🔹 Danh sách chức năng
        List<String> options = Arrays.asList("Thông tin người dùng", "Thanh toán");

        adapter = new CustomerOptionAdapter(options, option -> {
            Toast.makeText(CustomerActivity.this, "Bạn chọn: " + option, Toast.LENGTH_SHORT).show();
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}
