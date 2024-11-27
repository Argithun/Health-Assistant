package com.example.buaaexercise.group;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.buaaexercise.MainActivity;
import com.example.buaaexercise.R;
import com.example.buaaexercise.backend.dbFunction.DBFunction;
import com.example.buaaexercise.backend.recommend.Recommend;
import com.example.buaaexercise.homepagefragments.GroupFragment;
import com.example.buaaexercise.homepagefragments.HomeFragment;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class RecommendGroupActivity extends AppCompatActivity {

    public static RecyclerView recGroupRecyclerView;
    public static GroupAdapter recGroupAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommand_group);

        // Filter the groupItemList based on the search query
        List<GroupItem> searchResults =
                Recommend.recommendGroup(MainActivity.getCurrentUsername(), HomeFragment.getWeather());

        recGroupRecyclerView = findViewById(R.id.recGroupRecyclerView);
        // Display search results using the same adapter
        recGroupAdapter = new GroupAdapter(searchResults, 3);
        recGroupRecyclerView.setAdapter(recGroupAdapter);
        recGroupRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        // Set ItemDecoration for spacing
        int spacingDimen = R.dimen.spacing_between_items;
        int cornerRadiusDimen = R.dimen.corner_radius;
        recGroupRecyclerView.addItemDecoration(new SpaceItemDecoration(this, spacingDimen, cornerRadiusDimen));

        // Display a message if no results were found
        if (searchResults.isEmpty()) {
            Toast.makeText(this, "今天暂无推荐哦~", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        List<GroupItem> searchResults =
                Recommend.recommendGroup(MainActivity.getCurrentUsername(), HomeFragment.getWeather());

        // Display search results using the same adapter
        recGroupAdapter = new GroupAdapter(searchResults, 3);
        recGroupRecyclerView.setAdapter(recGroupAdapter);
    }

}