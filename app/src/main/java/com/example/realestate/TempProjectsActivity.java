package com.example.realestate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;

import java.util.ArrayList;

public class TempProjectsActivity extends AppCompatActivity {

    private String activityTitle = "";

    private RecyclerView recyclerView;
    private RecyclerviewAdapter adapter;
    private ArrayList<POJO_Main> lstcategory;
    private ArrayList<POJO_PropList> lstcategoryInsider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp_projects);

        Intent intent = getIntent();
        activityTitle = intent.getStringExtra("TITLE");

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if(activityTitle != null || !activityTitle.matches("")) {
            setTitle(activityTitle);
        } else {
            setTitle("Properties");
        }

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView_Home);

        lstcategory = new ArrayList<>();
        lstcategoryInsider = new ArrayList<>();

        lstcategoryInsider.add(new POJO_PropList("https://media.equityapartments.com/images/c_crop,x_0,y_0,w_1920,h_1080/c_fill,w_1920,h_1080/q_80/4147-23/the-hesby-apartments-exterior.jpg", "₹ 66 Lac", "2 BHK Flat", "VTP Hilife", "Vakad, Pune.", "Possession by Dec 2020", "Call Owner", "9503696428"));
        lstcategoryInsider.add(new POJO_PropList("https://images.wallpaperscraft.com/image/living_room_design_flat_interior_design_style_metropolis_90530_1280x720.jpg", "₹ 72 Lac", "2 BHK Flat", "VTP Hilife", "Vakad, Pune.", "Possession by Dec 2020", "Call Owner", "9503696428"));
        lstcategoryInsider.add(new POJO_PropList("https://images.wallpaperscraft.com/image/living_room_modern_design_interior_design_furniture_sofa_tv_70088_1280x720.jpg", "₹ 88 Lac", "2 BHK Flat", "VTP Hilife", "Vakad, Pune.", "Possession by Dec 2020", "Call Owner", "9503696428"));
        lstcategoryInsider.add(new POJO_PropList("https://images.wallpaperscraft.com/image/living_room_desk_furniture_design_interior_design_modernism_25616_1280x720.jpg", "₹ 56 Lac", "2 BHK Flat", "VTP Hilife", "Vakad, Pune.", "Possession by Dec 2020", "Call Owner", "9503696428"));
        lstcategoryInsider.add(new POJO_PropList("https://images.wallpaperscraft.com/image/apartments_living_room_bathroom_furniture_design_interior_108940_1280x720.jpg", "₹ 95 Lac", "2 BHK Flat", "VTP Hilife", "Vakad, Pune.", "Possession by Dec 2020", "Call Owner", "9503696428"));
        lstcategoryInsider.add(new POJO_PropList("https://images.wallpaperscraft.com/image/living_room_furniture_style_design_39327_1280x720.jpg", "₹ 69 Lac", "2 BHK Flat", "VTP Hilife", "Vakad, Pune.", "Possession by Dec 2020", "Call Owner", "9503696428"));
        lstcategoryInsider.add(new POJO_PropList("https://images.wallpaperscraft.com/image/living_room_furniture_cosiness_style_comfort_39308_1280x720.jpg", "₹ 78 Lac", "2 BHK Flat", "VTP Hilife", "Vakad, Pune.", "Possession by Dec 2020", "Call Owner", "9503696428"));
        lstcategoryInsider.add(new POJO_PropList("https://images.wallpaperscraft.com/image/living_room_girl_furniture_style_comfort_39343_1280x720.jpg", "₹ 59 Lac", "2 BHK Flat", "VTP Hilife", "Vakad, Pune.", "Possession by Dec 2020", "Call Owner", "9503696428"));
        lstcategoryInsider.add(new POJO_PropList("https://images.wallpaperscraft.com/image/living_room_furniture_style_interior_design_modern_39374_1280x720.jpg", "₹ 82 Lac", "2 BHK Flat", "VTP Hilife", "Vakad, Pune.", "Possession by Dec 2020", "Call Owner", "9503696428"));
        lstcategoryInsider.add(new POJO_PropList("https://images.wallpaperscraft.com/image/living_room_sofa_furniture_interior_style_design_95260_1280x720.jpg", "₹ 80 Lac", "2 BHK Flat", "VTP Hilife", "Vakad, Pune.", "Possession by Dec 2020", "Call Owner", "9503696428"));
        lstcategoryInsider.add(new POJO_PropList("https://images.wallpaperscraft.com/image/living_room_sofa_furniture_comfort_interior_75478_1280x720.jpg", "₹ 67 Lac", "2 BHK Flat", "VTP Hilife", "Vakad, Pune.", "Possession by Dec 2020", "Call Owner", "9503696428"));
        lstcategoryInsider.add(new POJO_PropList("https://images.wallpaperscraft.com/image/living_room_furniture_sofa_table_comfort_design_70084_1280x720.jpg", "₹ 96 Lac", "2 BHK Flat", "VTP Hilife", "Vakad, Pune.", "Possession by Dec 2020", "Call Owner", "9503696428"));

        lstcategory.add(new POJO_Main(lstcategoryInsider, "Owner Properties"));
        lstcategory.add(new POJO_Main(lstcategoryInsider, "Fresh Properties For You"));
        lstcategory.add(new POJO_Main(lstcategoryInsider, "Hot Deals For You"));
        lstcategory.add(new POJO_Main(lstcategoryInsider, "Ready to Move Projects"));
        lstcategory.add(new POJO_Main(lstcategoryInsider, "Newly Launched Projects"));
        lstcategory.add(new POJO_Main(lstcategoryInsider, "Projects in Demand"));

        adapter = new RecyclerviewAdapter(TempProjectsActivity.this, lstcategory);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(TempProjectsActivity.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(TempProjectsActivity.this, LinearLayoutManager.VERTICAL, true));
        recyclerView.setAdapter(adapter);

    }


    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
