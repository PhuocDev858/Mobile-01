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

public class LoginActivity extends AppCompatActivity {

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

        Button btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText objMail = findViewById(R.id.txtMail);
                String txtMail = objMail.getText().toString();

                EditText objPass = findViewById(R.id.txtPass);
                String txtPass = objPass.getText().toString();

                CharSequence text = "Đăng nhập thất bại!";
                int duration = Toast.LENGTH_SHORT;

                if (txtMail.equals("test01@gmail.com") && txtPass.equals("123456"))
                {
                    Intent it = new Intent(getApplicationContext(),MainActivity.class);
                    it.putExtra("mail", txtMail);
                    startActivity(it);
                }
                else
                {
                    Toast toast = Toast.makeText(getApplicationContext(), text, duration);
                    toast.show();
                }
            }
        });

        Button btnRegis = findViewById(R.id.btnRegister);

        btnRegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(getApplicationContext(),RegisterActivity.class);
                startActivity(it);
            }
        });
    }
}