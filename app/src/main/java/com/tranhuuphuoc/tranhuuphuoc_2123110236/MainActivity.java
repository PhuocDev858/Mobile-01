package com.tranhuuphuoc.tranhuuphuoc_2123110236;

import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    ListView l;
    String tutorials[] = { "Algorithms",
            "Data Structures",
            "Languages",
            "Interview Corner",
            "GATE",
            "ISRO CS",
            "UGC NET CS",
            "CS Subjects",
            "Web Technologies" };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide(); // Ẩn action bar
        setContentView(R.layout.activity_main);

        l = findViewById(R.id.list);
        ArrayAdapter<String> arr;

        arr = new ArrayAdapter<String>(this,
                R.layout.item,tutorials);
        l.setAdapter(arr);
    }
}