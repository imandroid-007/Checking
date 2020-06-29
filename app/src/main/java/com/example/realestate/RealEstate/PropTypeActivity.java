package com.example.realestate.RealEstate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.realestate.GridSpacingDecoration;
import com.example.realestate.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class PropTypeActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private ArrayList<POJO_PropType> lstcategory;

    private RecyclerviewPropTypes adapter;

    private String fragmentType = null;
    private TextView propTypeTitleTv;
    private Typeface typeface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prop_type);

        Intent intent = getIntent();
        fragmentType = intent.getStringExtra("FRAGMENT_TYPE");

        if(fragmentType.matches("BUY")){
            setTitle("Buy Property");
        } else if(fragmentType.matches("SELL")){
            setTitle("Sell Property");
        } else if(fragmentType.matches("RENT")){
            setTitle("Rent Property");
        } else if(fragmentType.matches("PROJECTS")){
            setTitle("Projects");
        }

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        lstcategory = new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.recycPropTypeAct);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        propTypeTitleTv = (TextView) findViewById(R.id.title);

        typeface = Typeface.createFromAsset(getAssets(), "fonts/SourceSansPro-SemiBold.ttf");
        propTypeTitleTv.setTypeface(typeface);

        lstcategory.add(new POJO_PropType("Flat", "https://icon-library.net/images/apartment-icon/apartment-icon-5.jpg", false, "RESIDENTIAL"));
        lstcategory.add(new POJO_PropType("House/Villa", "https://img.icons8.com/plasticine/2x/home.png", false, "RESIDENTIAL"));
        lstcategory.add(new POJO_PropType("NA Plot", "https://cdn3.iconfinder.com/data/icons/real-estate-flat-icons-vol-2/256/68-512.png", false, "LAND"));
        lstcategory.add(new POJO_PropType("Agricultural Land", "https://webstockreview.net/images/industry-clipart-agro-based-industry-11.png", false, "AGRICULTURAL-LAND"));
        lstcategory.add(new POJO_PropType("Office Space", "https://c7.uihere.com/files/169/771/802/building-icon-flat-building.jpg", false, "COMMERCIAL"));
        lstcategory.add(new POJO_PropType("Shop/Showroom", "https://png.pngtree.com/png-vector/20190227/ourmid/pngtree-vector-storage-warehouse-icon-png-image_707463.jpg", false, "COMMERCIAL"));
        lstcategory.add(new POJO_PropType("Other Commercial", "https://cdn.iconscout.com/icon/premium/png-256-thumb/building-1256-486704.png", false, "COMMERCIAL"));

        if(fragmentType.matches("BUY") || fragmentType.matches("RENT")){
            lstcategory.add(new POJO_PropType("Show All", "https://www.shareicon.net/data/512x512/2016/09/21/831108_view_512x512.png", false, "ALL"));
        }

        if(fragmentType != null) {

            /*if (fragmentType.matches("BUY_FRAGMENT")) {

            } else if(fragmentType.matches("SELL_FRAGMENT")){

            }*/

            int spanCount = 2; // 3 columns
            int spacing = 30; // 50px
            boolean includeEdge = true;
            recyclerView.addItemDecoration(new GridSpacingDecoration(spanCount, spacing, includeEdge));
            adapter = new RecyclerviewPropTypes(PropTypeActivity.this, lstcategory, true, fragmentType);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(PropTypeActivity.this,2);
            recyclerView.setLayoutManager(gridLayoutManager);
            recyclerView.setAdapter(adapter);
        }



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






















































