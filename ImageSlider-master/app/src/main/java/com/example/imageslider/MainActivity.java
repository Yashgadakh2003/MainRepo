package com.example.imageslider;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager2 viewPager2;
    private ImageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager2 = findViewById(R.id.viewPagerImageSlider);

        List<Integer> images = Arrays.asList(
                R.drawable.image1,
                R.drawable.image2,
                R.drawable.image3
        );

        adapter = new ImageAdapter(images);
        viewPager2.setAdapter(adapter);
    }
}
