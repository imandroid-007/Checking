package com.example.realestate.Projects;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.realestate.GridSpacingDecoration;
import com.example.realestate.R;
import com.example.realestate.RealEstate.POJO_PropType;
import com.example.realestate.RealEstate.RecyclerviewPropTypes;

import java.util.ArrayList;

public class ProjectsPropTypeActivity extends AppCompatActivity {
    
    private RecyclerView recyclerView;
    private ArrayList<POJO_PropType> lstcategory;
    private RecyclerviewPropTypes adapter;
    private String fragmentType = null;
    private ProgressBar progressBar;

    private Button registerProjectBtn, buyProjectBtn;// nextBtn;
    private RelativeLayout optionsContainer;
    private boolean projectTypeBol = false; // want to take prop on rent

    private TextView wantToTv, propTypeTitleTv;
    private Typeface typeface;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projects_prop_type);
        setTitle("Project");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        fragmentType = intent.getStringExtra("FRAGMENT_TYPE");

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        lstcategory = new ArrayList<>();

        registerProjectBtn = (Button) findViewById(R.id.registerProjectBtn);
        buyProjectBtn = (Button) findViewById(R.id.buyProjectBtn);

        optionsContainer = (RelativeLayout) findViewById(R.id.projectsOptionsContainer);
        recyclerView = (RecyclerView) findViewById(R.id.recycProjectType);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        wantToTv = (TextView) findViewById(R.id.wantToTitleTv);
        propTypeTitleTv = (TextView) findViewById(R.id.title);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        typeface = Typeface.createFromAsset(getAssets(), "fonts/SourceSansPro-SemiBold.ttf");
        wantToTv.setTypeface(typeface);
        propTypeTitleTv.setTypeface(typeface);

        lstcategory.add(new POJO_PropType("Apartment", "https://icon-library.net/images/apartment-icon/apartment-icon-5.jpg", false, "RESIDENTIAL"));
        lstcategory.add(new POJO_PropType("House/Villa", "https://img.icons8.com/plasticine/2x/home.png", false, "RESIDENTIAL"));
        lstcategory.add(new POJO_PropType("NA Plot", "https://cdn3.iconfinder.com/data/icons/real-estate-flat-icons-vol-2/256/68-512.png", false, "LAND"));

        lstcategory.add(new POJO_PropType("Agricultural Project", "https://webstockreview.net/images/industry-clipart-agro-based-industry-11.png", false, "AGRICULTURAL-LAND"));

        lstcategory.add(new POJO_PropType("Commercial Office", "https://c7.uihere.com/files/169/771/802/building-icon-flat-building.jpg", false, "COMMERCIAL"));
        lstcategory.add(new POJO_PropType("Commercial Complex", "https://png.pngtree.com/png-vector/20190227/ourmid/pngtree-vector-storage-warehouse-icon-png-image_707463.jpg", false, "COMMERCIAL"));
        lstcategory.add(new POJO_PropType("Other Commercial", "https://cdn.iconscout.com/icon/premium/png-256-thumb/building-1256-486704.png", false, "COMMERCIAL"));

        if(fragmentType != null) {

            int spanCount = 2; // 3 columns
            int spacing = 33; // 50px
            boolean includeEdge = true;
            recyclerView.addItemDecoration(new GridSpacingDecoration(spanCount, spacing, includeEdge));
            adapter = new RecyclerviewPropTypes(ProjectsPropTypeActivity.this, lstcategory, true, fragmentType);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(ProjectsPropTypeActivity.this,2);
            recyclerView.setLayoutManager(gridLayoutManager);
            recyclerView.setAdapter(adapter);

        }

        registerProjectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(projectTypeBol){

                    projectTypeBol = false;

                    buyProjectBtn.setBackgroundResource(0);
                    registerProjectBtn.setBackgroundResource(R.drawable.selected_button_background);
                    //nextBtn.setAlpha(1);
                    optionsContainer.setVisibility(View.VISIBLE);

                    adapter = new RecyclerviewPropTypes(ProjectsPropTypeActivity.this, lstcategory, true, fragmentType, "REGISTER_PROJECT");
                    recyclerView.setAdapter(adapter);

                } else {
                    buyProjectBtn.setBackgroundResource(0);
                    registerProjectBtn.setBackgroundResource(R.drawable.selected_button_background);
                    //nextBtn.setAlpha(1);
                    optionsContainer.setVisibility(View.VISIBLE);

                    adapter = new RecyclerviewPropTypes(ProjectsPropTypeActivity.this, lstcategory, true, fragmentType, "REGISTER_PROJECT");
                    recyclerView.setAdapter(adapter);
                }
            }
        });


        buyProjectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!projectTypeBol){

                    projectTypeBol = true;
                    registerProjectBtn.setBackgroundResource(0);
                    buyProjectBtn.setBackgroundResource(R.drawable.selected_button_background);

                    optionsContainer.setVisibility(View.VISIBLE);

                    adapter = new RecyclerviewPropTypes(ProjectsPropTypeActivity.this, lstcategory, true, fragmentType, "BUY_PROJECT");
                    recyclerView.setAdapter(adapter);

                }
            }
        });





    }


    private void setActionBarTitle(String title){

        final ActionBar abar = getSupportActionBar();
        //abar.setBackgroundDrawable(getResources().getDrawable(R.drawable.actionbar_background));//line under the action bar
        View viewActionBar = getLayoutInflater().inflate(R.layout.actionbar_titletext_layout, null);
        ActionBar.LayoutParams params = new ActionBar.LayoutParams(//Center the textview in the ActionBar !
                ActionBar.LayoutParams.WRAP_CONTENT,
                ActionBar.LayoutParams.MATCH_PARENT,
                Gravity.CENTER);
        TextView textviewTitle = (TextView) viewActionBar.findViewById(R.id.actionbar_textview);
        textviewTitle.setText("title");
        abar.setCustomView(viewActionBar, params);
        abar.setDisplayShowCustomEnabled(true);
        abar.setDisplayShowTitleEnabled(false);
        abar.setDisplayHomeAsUpEnabled(true);
        abar.setIcon(R.color.transparent);
        abar.setHomeButtonEnabled(true);


    }


    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    
    
    
    
    
}


































