package com.tranhuuphuoc.tranhuuphuoc_2123110236;

import static java.security.AccessController.getContext;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HomeActivity extends AppCompatActivity {

    private static final String API_URL = "https://68930efec49d24bce8693cb2.mockapi.io/phuocdev/v1/products"; // đổi URL API
    private GridLayout productGrid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        productGrid = findViewById(R.id.productGrid);

        loadProducts();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.nav_home) {
                startActivity(new Intent(HomeActivity.this, HomeActivity.class));
                return true;
            }
            else if (id == R.id.nav_customer) {
                startActivity(new Intent(HomeActivity.this, CustomerActivity.class));
                return true;
            }

            return false;
        });


    }

    private void loadProducts() {
        RequestQueue queue = Volley.newRequestQueue(this);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                API_URL,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        productGrid.removeAllViews();

                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject product = response.getJSONObject(i);

                                String name = product.optString("name");
                                String imageUrl = product.optString("image"); // đổi key nếu API khác
                                String price = product.optString("price");
                                String desc = product.optString("description");

                                Log.d("IMG_URL", imageUrl); // log để kiểm tra URL ảnh

                                View itemView = LayoutInflater.from(HomeActivity.this)
                                        .inflate(R.layout.item_product, productGrid, false);

                                ImageView imgProduct = itemView.findViewById(R.id.productImage);
                                TextView txtName = itemView.findViewById(R.id.productName);
                                TextView txtPrice = itemView.findViewById(R.id.productPrice);


                                txtName.setText(name);
                                txtPrice.setText(price + " VNĐ");

                                // Thiết lập LayoutParams để chia đều 3 cột
                                GridLayout.LayoutParams params = new GridLayout.LayoutParams();
                                params.width = 0;
                                params.height = GridLayout.LayoutParams.WRAP_CONTENT;
                                params.columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f);
                                itemView.setLayoutParams(params);

                                Glide.with(HomeActivity.this)
                                        .load(imageUrl)
                                        .placeholder(R.drawable.ic_placeholder) // ảnh tạm
                                        .error(R.drawable.ic_error) // ảnh lỗi
                                        .into(imgProduct);

                                productGrid.addView(itemView);

                                itemView.setOnClickListener(v -> {
                                    Intent intent = new Intent(HomeActivity.this, ProductDetailActivity.class);
                                    intent.putExtra("name", name);
                                    intent.putExtra("price", price);
                                    intent.putExtra("desc", desc);
                                    intent.putExtra("image", imageUrl);
                                    startActivity(intent);
                                });


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(HomeActivity.this, "Lỗi tải sản phẩm", Toast.LENGTH_SHORT).show();
                        Log.e("API_ERROR", error.toString());
                    }
                }
        );

        queue.add(jsonArrayRequest);
    }

}


