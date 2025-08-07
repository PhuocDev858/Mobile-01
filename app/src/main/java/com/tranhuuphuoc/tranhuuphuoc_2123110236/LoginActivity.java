package com.tranhuuphuoc.tranhuuphuoc_2123110236;

import android.content.Intent;
import android.os.Bundle;
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

import org.json.JSONArray;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    private EditText edtEmail, edtPass;
    private Button btnLogin, btnRegister;
    private String apiUrl = "https://68930efec49d24bce8693cb2.mockapi.io/phuocdev/v1/users"; // GIẢ LẬP API (bạn thay bằng URL của bạn)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        edtEmail = findViewById(R.id.txtMail);
        edtPass = findViewById(R.id.txtPass);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);

        btnLogin.setOnClickListener(v -> {
            String email = edtEmail.getText().toString().trim();
            String password = edtPass.getText().toString().trim();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                return;
            }

            checkLogin(email, password);
        });

        btnRegister.setOnClickListener(v -> {
            Intent it = new Intent(getApplicationContext(), RegisterActivity.class);
            startActivity(it);
        });
    }

    private void checkLogin(String email, String password) {
        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest request = new StringRequest(Request.Method.GET, apiUrl,
                response -> {
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        boolean isMatch = false;

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject user = jsonArray.getJSONObject(i);
                            String apiEmail = user.getString("email");
                            String apiPass = user.getString("password");

                            if (email.equals(apiEmail) && password.equals(apiPass)) {
                                isMatch = true;
                                break;
                            }
                        }

                        if (isMatch) {
                            Intent it = new Intent(getApplicationContext(), HomeActivity    .class);
                            it.putExtra("mail", email);
                            startActivity(it);
                            Toast.makeText(this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(this, "Đăng nhập thất bại!", Toast.LENGTH_SHORT).show();
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(this, "Lỗi xử lý dữ liệu!", Toast.LENGTH_SHORT).show();
                    }
                },
                error -> Toast.makeText(this, "Lỗi kết nối API!", Toast.LENGTH_SHORT).show()
        );

        queue.add(request);
    }
}
