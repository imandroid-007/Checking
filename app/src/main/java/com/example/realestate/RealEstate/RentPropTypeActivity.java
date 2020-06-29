package com.example.realestate.RealEstate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.realestate.GridSpacingDecoration;
import com.example.realestate.R;

import java.util.ArrayList;

public class RentPropTypeActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<POJO_PropType> lstcategory;
    private RecyclerviewPropTypes adapter;
    private String fragmentType = null;
    private ProgressBar progressBar;

    private Button takeOnRentBtn, giveOnRentBtn;// nextBtn;
    private RelativeLayout givePropOnRentOptContainer;
    private boolean rentTypeBol = false; // want to take prop on rent

    private TextView wantTo, propTypeTitleTv;

    private Typeface typeface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rent_prop_type);
        setTitle("Rent Property");

        Intent intent = getIntent();
        fragmentType = intent.getStringExtra("FRAGMENT_TYPE");

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        lstcategory = new ArrayList<>();

        takeOnRentBtn = (Button) findViewById(R.id.takePropOnRentBtn);
        giveOnRentBtn = (Button) findViewById(R.id.givePropOnRentBtn);
        //nextBtn = (Button) findViewById(R.id.nextBtn);

        givePropOnRentOptContainer = (RelativeLayout) findViewById(R.id.givePropOnRentOptContainer);
        recyclerView = (RecyclerView) findViewById(R.id.recycPropTypeAct);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        wantTo = (TextView) findViewById(R.id.wantToTitleTv);
        propTypeTitleTv = (TextView) findViewById(R.id.title);

        typeface = Typeface.createFromAsset(getAssets(), "fonts/SourceSansPro-SemiBold.ttf");
        wantTo.setTypeface(typeface);
        propTypeTitleTv.setTypeface(typeface);

        //nextBtn.setAlpha(0.5f);

        lstcategory.add(new POJO_PropType("Flat", "https://icon-library.net/images/apartment-icon/apartment-icon-5.jpg", false, "RESIDENTIAL"));
        lstcategory.add(new POJO_PropType("House/Villa", "https://img.icons8.com/plasticine/2x/home.png", false, "RESIDENTIAL"));
        lstcategory.add(new POJO_PropType("NA Plot", "https://cdn3.iconfinder.com/data/icons/real-estate-flat-icons-vol-2/256/68-512.png", false, "LAND"));
        lstcategory.add(new POJO_PropType("Agricultural Land", "https://webstockreview.net/images/industry-clipart-agro-based-industry-11.png", false, "AGRICULTURAL-LAND"));
        lstcategory.add(new POJO_PropType("Office Space", "https://c7.uihere.com/files/169/771/802/building-icon-flat-building.jpg", false, "COMMERCIAL"));
        lstcategory.add(new POJO_PropType("Shop/Showroom", "https://png.pngtree.com/png-vector/20190227/ourmid/pngtree-vector-storage-warehouse-icon-png-image_707463.jpg", false, "COMMERCIAL"));
        lstcategory.add(new POJO_PropType("Other Commercial", "https://cdn.iconscout.com/icon/premium/png-256-thumb/building-1256-486704.png", false, "COMMERCIAL"));

        if(fragmentType != null) {

            /*if (fragmentType.matches("BUY_FRAGMENT")) {

            } else if(fragmentType.matches("SELL_FRAGMENT")){

            }*/
            int spanCount = 2; // 3 columns
            int spacing = 33; // 50px
            boolean includeEdge = true;
            recyclerView.addItemDecoration(new GridSpacingDecoration(spanCount, spacing, includeEdge));
            adapter = new RecyclerviewPropTypes(RentPropTypeActivity.this, lstcategory, true, fragmentType);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(RentPropTypeActivity.this,2);
            recyclerView.setLayoutManager(gridLayoutManager);
            recyclerView.setAdapter(adapter);

        }

        takeOnRentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(rentTypeBol){

                    rentTypeBol = false;

                    giveOnRentBtn.setBackgroundResource(0);
                    takeOnRentBtn.setBackgroundResource(R.drawable.selected_button_background);
                    //nextBtn.setAlpha(1);
                    givePropOnRentOptContainer.setVisibility(View.VISIBLE);

                    adapter = new RecyclerviewPropTypes(RentPropTypeActivity.this, lstcategory, true, fragmentType, "TAKE_ON_RENT");
                    recyclerView.setAdapter(adapter);

                } else {

                    giveOnRentBtn.setBackgroundResource(0);
                    takeOnRentBtn.setBackgroundResource(R.drawable.selected_button_background);
                    //nextBtn.setAlpha(1);
                    givePropOnRentOptContainer.setVisibility(View.VISIBLE);

                    adapter = new RecyclerviewPropTypes(RentPropTypeActivity.this, lstcategory, true, fragmentType, "TAKE_ON_RENT");
                    recyclerView.setAdapter(adapter);
                }
            }
        });

        giveOnRentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!rentTypeBol){

                    rentTypeBol = true;
                    takeOnRentBtn.setBackgroundResource(0);
                    giveOnRentBtn.setBackgroundResource(R.drawable.selected_button_background);
                    //nextBtn.setAlpha(1);
                    givePropOnRentOptContainer.setVisibility(View.VISIBLE);

                    adapter = new RecyclerviewPropTypes(RentPropTypeActivity.this, lstcategory, true, fragmentType, "GIVE_ON_RENT");
                    recyclerView.setAdapter(adapter);

                }

            }
        });




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



























