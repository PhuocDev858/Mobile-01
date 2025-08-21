package com.tranhuuphuoc.tranhuuphuoc_2123110236;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;

public class ProductDetailActivity extends AppCompatActivity {

    private ImageView productImage;
    private TextView productName, productPrice, productDesc;
    private Button btnAddToCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        // Toolbar với nút quay lại
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Chi tiết sản phẩm");
        }
        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        // Ánh xạ view
        productImage = findViewById(R.id.productImage);
        productName = findViewById(R.id.productName);
        productPrice = findViewById(R.id.productPrice);
        productDesc = findViewById(R.id.productDesc);
        btnAddToCart = findViewById(R.id.btnAddToCart);

        // Nhận dữ liệu từ Intent
        String name = getIntent().getStringExtra("name");
        String price = getIntent().getStringExtra("price");
        String desc = getIntent().getStringExtra("desc");
        String imageUrl = getIntent().getStringExtra("image");

        // Gán dữ liệu
        productName.setText(name);
        productPrice.setText(price + " VNĐ");
        productDesc.setText(desc);
        Glide.with(this).load(imageUrl).into(productImage);

        // Xử lý sự kiện thêm vào giỏ hàng
        btnAddToCart.setOnClickListener(v ->
                Toast.makeText(this, "Đã thêm vào giỏ hàng", Toast.LENGTH_SHORT).show()
        );
    }
}
