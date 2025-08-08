package com.tranhuuphuoc.tranhuuphuoc_2123110236;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    EditText txtName, txtEmail, phoneNum, Pass, PassConfirm;
    Button btnAccept, btnBack2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Ánh xạ view
        txtName = findViewById(R.id.txtName);
        txtEmail = findViewById(R.id.txtEmail);
        phoneNum = findViewById(R.id.phoneNum);
        Pass = findViewById(R.id.Pass);
        PassConfirm = findViewById(R.id.PassConfirm);
        btnAccept = findViewById(R.id.btnAccept);
        btnBack2 = findViewById(R.id.btnBack2);

        // Nút quay lại
        btnBack2.setOnClickListener(v -> {
            Intent it = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(it);
        });

        // Nút đăng ký
        btnAccept.setOnClickListener(v -> {
            String name = txtName.getText().toString().trim();
            String email = txtEmail.getText().toString().trim();
            String phone = phoneNum.getText().toString().trim();
            String password = Pass.getText().toString().trim();
            String confirmPass = PassConfirm.getText().toString().trim();

            // Kiểm tra trống
            if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || password.isEmpty() || confirmPass.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
                return;
            }

            // Kiểm tra định dạng email
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(this, "Email không hợp lệ!", Toast.LENGTH_SHORT).show();
                return;
            }

            // Kiểm tra số điện thoại (10 chữ số ở VN)
            if (!phone.matches("0\\d{9}")) {
                Toast.makeText(this, "Số điện thoại không hợp lệ!", Toast.LENGTH_SHORT).show();
                return;
            }

            // Kiểm tra mật khẩu xác nhận
            if (!password.equals(confirmPass)) {
                Toast.makeText(this, "Mật khẩu xác nhận không khớp!", Toast.LENGTH_SHORT).show();
                return;
            }

            // Gửi API
            registerUser(name, email, phone, password);
        });
    }

    private void registerUser(String name, String email, String phone, String password) {
        String url = "https://68930efec49d24bce8693cb2.mockapi.io/phuocdev/v1/users";

        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                response -> {
                    Toast.makeText(this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();
                    System.out.println("Response: " + response);

                    // Quay về màn hình đăng nhập
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                },
                error -> {
                    Toast.makeText(this, "Lỗi kết nối API!", Toast.LENGTH_SHORT).show();
                    error.printStackTrace();
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("name", name);
                params.put("email", email);
                params.put("phone", phone);
                params.put("password", password);
                return params;
            }

            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }
        };

        queue.add(postRequest);
    }
}
