package com.example.realestate.RealEstate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.realestate.ContactAndEnquiry.ContactPropListedOwnerActivity;
import com.example.realestate.GridSpacingDecoration;
import com.example.realestate.Pojo_propDetails;
import com.example.realestate.R;
import com.example.realestate.RecyclerviewAdapterPropDetail;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PropDetailsActivity extends AppCompatActivity {

    private ImageView propImage;

    private CollapsingToolbarLayout collapsingToolbarLayout;
    private Toolbar toolbar;

    private String imageUrl;

    private RecyclerView recyclerView;
    private RecyclerviewAdapterPropDetail adapterPropDetails;
    private ArrayList<Pojo_propDetails> listPropDetails;

    private RecyclerView recyclerViewAmenities;
    private RecyclerviewAdapterAmenities adapterAmenities;
    private ArrayList<POJO_Amenities> listAmenities;

    private ImageView likeBtn;
    private boolean likePropAddFavFlag = false;

    private TextView toolbarTitleTv, enquiryNowTv;

    private LinearLayout callNowGetContNo;
    private Button getContactNoBtn, enquiryNowBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prop_details);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        propImage = (ImageView) findViewById(R.id.propImage);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.rv_propDetails);
        recyclerViewAmenities = (RecyclerView) findViewById(R.id.rv_amenities);

        likeBtn = (ImageView) findViewById(R.id.likeBtn);
        toolbarTitleTv = (TextView) findViewById(R.id.toolbarTitleTv);

        callNowGetContNo = (LinearLayout) findViewById(R.id.callNowGetContactNoLin);
        getContactNoBtn = (Button) findViewById(R.id.getContactNoBtn);

        enquiryNowTv = (TextView) findViewById(R.id.enquiryNowTv);
        enquiryNowBtn = (Button) findViewById(R.id.enquiryNowBtn);

        collapsingToolbarLayout.setTitleEnabled(false);
        toolbar.setTitle("₹ 65 Lac - 2 BHK Flat");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        imageUrl = intent.getStringExtra("IMAGE_URL");

        toolbarTitleTv.setText("₹ 65 Lac - 2 BHK Flat");

        listPropDetails = new ArrayList<>();
        listAmenities = new ArrayList<>();

        likeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(PropDetailsActivity.this, "Hii from onClick", Toast.LENGTH_SHORT).show();

                if(likePropAddFavFlag){

                    likeBtn.setImageResource(R.drawable.white_bordered_like_button_new);
                    likeBtn.setColorFilter(ContextCompat.getColor(PropDetailsActivity.this, R.color.white), android.graphics.PorterDuff.Mode.MULTIPLY);

                    likePropAddFavFlag = false;

                } else {

                    Toast.makeText(PropDetailsActivity.this, "Making flag true", Toast.LENGTH_SHORT).show();

                    likeBtn.setImageResource(R.drawable.like_icon);

                    likePropAddFavFlag = true;

                }
            }
        });

        callNowGetContNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PropDetailsActivity.this, ContactPropListedOwnerActivity.class));
            }
        });

        getContactNoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PropDetailsActivity.this, ContactPropListedOwnerActivity.class));
            }
        });

        enquiryNowTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PropDetailsActivity.this, ContactPropListedOwnerActivity.class));
            }
        });

        enquiryNowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PropDetailsActivity.this, ContactPropListedOwnerActivity.class));
            }
        });

        if(imageUrl != null){
            Picasso.get().load(imageUrl).into(propImage);
        }

        listPropDetails.add(new Pojo_propDetails("Car Parking", "1 Covered"));
        listPropDetails.add(new Pojo_propDetails("Floor", "7 of 8 Floor"));
        listPropDetails.add(new Pojo_propDetails("Furnishing", "Unfurnished"));
        listPropDetails.add(new Pojo_propDetails("Facing", "East"));
        listPropDetails.add(new Pojo_propDetails("Transaction", "Resale (New Construction)"));
        listPropDetails.add(new Pojo_propDetails("Overlooking", "Garden/Park"));
        listPropDetails.add(new Pojo_propDetails("Maintenance Charges", "0 Monthly"));
        listPropDetails.add(new Pojo_propDetails("Token Amount", "1.0 Lac"));
        listPropDetails.add(new Pojo_propDetails("Flooring", "Verified"));
        listPropDetails.add(new Pojo_propDetails("Landmark", "Near BY-Mankar Chowk, Wakad Chowk"));
        listPropDetails.add(new Pojo_propDetails("Water Availability", "24 Hours Available"));
        listPropDetails.add(new Pojo_propDetails("Status of Electricity", "No/Rare Powercut"));

        adapterPropDetails = new RecyclerviewAdapterPropDetail(PropDetailsActivity.this, listPropDetails);
        recyclerView.setLayoutManager(new LinearLayoutManager(PropDetailsActivity.this));
        recyclerView.setAdapter(adapterPropDetails);

        listAmenities.add(new POJO_Amenities("Parking", "https://img.icons8.com/plasticine/2x/car.png", false));
        listAmenities.add(new POJO_Amenities("Lift", "https://upload.wikimedia.org/wikipedia/commons/thumb/e/eb/KRL_Icon_Pink.svg/1024px-KRL_Icon_Pink.svg.png", false));
        listAmenities.add(new POJO_Amenities("Power Backup", "https://cdn.iconscout.com/icon/premium/png-512-thumb/power-generator-1717542-1461298.png", false));
        listAmenities.add(new POJO_Amenities("Park", "https://cdn4.iconfinder.com/data/icons/jetflat-2-buildings-vol-1/60/008_035_ferris_wheel_park_amusement_attractions-512.png", false));
        listAmenities.add(new POJO_Amenities("Gym", "https://www.pngrepo.com/download/200170/dumbbell-gym.png", false));
        listAmenities.add(new POJO_Amenities("Gas Pipeline", "https://cdn2.iconfinder.com/data/icons/construction-butterscotch-vol-2/512/Pipeline-512.png", false));

        adapterAmenities = new RecyclerviewAdapterAmenities(PropDetailsActivity.this, listAmenities, false);

        /*LinearLayoutManager linearLayoutManagerAmenities = new LinearLayoutManager(PropDetailsActivity.this);
        linearLayoutManagerAmenities.setOrientation(LinearLayoutManager.HORIZONTAL);
        linearLayoutManagerAmenities.setStackFromEnd(false);
        recyclerViewAmenities.setLayoutManager(linearLayoutManagerAmenities);
        recyclerViewAmenities.setAdapter(adapterAmenities);*/

        int spanCount = 3; // 3 columns
        int spacing = 27; // 50px
        boolean includeEdge = true;
        recyclerViewAmenities.addItemDecoration(new GridSpacingDecoration(spanCount, spacing, includeEdge));
        adapterAmenities = new RecyclerviewAdapterAmenities(PropDetailsActivity.this, listAmenities, false);
        GridLayoutManager gridLayoutManagerAmenities = new GridLayoutManager(PropDetailsActivity.this, 3);
        recyclerViewAmenities.setLayoutManager(gridLayoutManagerAmenities);
        recyclerViewAmenities.setAdapter(adapterAmenities);

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






























