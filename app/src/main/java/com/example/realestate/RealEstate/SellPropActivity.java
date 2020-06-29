package com.example.realestate.RealEstate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.developer.kalert.KAlertDialog;
import com.example.realestate.R;

import java.util.ArrayList;

public class SellPropActivity extends AppCompatActivity {

    private RecyclerviewPropTypes adapter;
    private RecyclerView recyclerView;
    private ArrayList<POJO_PropType> lstcategory;
    private Spinner minBudget, maxBudget;

    private ArrayList<String> minBudgetList = new ArrayList<>();
    private ArrayList<String> maxBudgetList = new ArrayList<>();

    private RecyclerviewAdapterBedrooms adapterBedrooms;
    private RecyclerView recyclerViewBedrooms;
    private ArrayList<POJO_bedrooms> lstcategoryBedrooms;

    private Button underConstruction, readyToMove;
    private boolean underConStatus = false, readyToMvStatus = false;

    private ArrayList<String> frmYearPossesList = new ArrayList<>();
    private ArrayList<String> toYearPossesList = new ArrayList<>();
    private Spinner frmYearPosSpin, toYearPosSPin;

    private LinearLayout moreFiltLinLay;
    private TextView moreFilterTv;

    private RecyclerviewAdapterAmenities recyclerViewAdapterAmenities;
    private ArrayList<POJO_Amenities> lstcategoryAmenities;
    private RecyclerView recyclerViewAmenities;

    private Spinner societySpin;
    private ArrayList<String> societyList = new ArrayList<>();

    private Spinner floorLevelSpin;
    private ArrayList<String> floorLevelList = new ArrayList<>();

    private Spinner maintainanceChrgesSpn;
    private ArrayList<String> maintanceChrgsList = new ArrayList<>();

    private Spinner propAreaSpn;
    private ArrayList<String> propAreaSpnList = new ArrayList<>();

    private Spinner bathroomSpn;
    private ArrayList<String> bathroomList = new ArrayList<>();

    private Spinner balconySpn;
    private ArrayList<String> balconySpnList = new ArrayList<>();

    private Button btnPostProperty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell_prop);
        setTitle("Sell Property");

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = (RecyclerView) findViewById(R.id.recyc_propType);
        minBudget = (Spinner) findViewById(R.id.minBudgt_Spin);
        maxBudget = (Spinner) findViewById(R.id.maxBudget_Spin);

        recyclerViewBedrooms = (RecyclerView) findViewById(R.id.recyc_bedrooms);
        underConstruction = (Button) findViewById(R.id.btnUnderConstr);
        readyToMove = (Button) findViewById(R.id.btnReadyToMove);

        frmYearPosSpin = (Spinner) findViewById(R.id.fromYearPossesSpin);
        toYearPosSPin = (Spinner) findViewById(R.id.toYearPossesSPin);

        moreFiltLinLay = (LinearLayout) findViewById(R.id.moreFiltersLinLay);
        //moreFilterTv = (TextView) findViewById(R.id.moreFilters_Tv);

        societySpin = (Spinner) findViewById(R.id.societySpinner);
        propAreaSpn = (Spinner) findViewById(R.id.propAreaspn);

        recyclerViewAmenities = (RecyclerView) findViewById(R.id.recyc_amenities);

        floorLevelSpin = (Spinner) findViewById(R.id.floorNoSpin);
        bathroomSpn = (Spinner) findViewById(R.id.bathroomSpinner);
        balconySpn = (Spinner) findViewById(R.id.balconySpinner);
        maintainanceChrgesSpn = (Spinner) findViewById(R.id.mntncChrgsTypeSpn);

        btnPostProperty = (Button) findViewById(R.id.btnPostProp);

        lstcategory = new ArrayList<>();
        lstcategoryBedrooms = new ArrayList<>();
        lstcategoryAmenities = new ArrayList<>();

        lstcategory.add(new POJO_PropType("FLat", "https://icon-library.net/images/apartment-icon/apartment-icon-5.jpg", false, "RESIDENTIAL"));
        lstcategory.add(new POJO_PropType("House/Villa", "https://img.icons8.com/plasticine/2x/home.png", false, "RESIDENTIAL"));
        lstcategory.add(new POJO_PropType("Plot", "https://cdn3.iconfinder.com/data/icons/real-estate-flat-icons-vol-2/256/68-512.png", false, "RESIDENTIAL"));
        lstcategory.add(new POJO_PropType("Office Space", "https://c7.uihere.com/files/169/771/802/building-icon-flat-building.jpg", false, "COMMERCIAL"));
        lstcategory.add(new POJO_PropType("Shop/Showroom", "https://png.pngtree.com/png-vector/20190227/ourmid/pngtree-vector-storage-warehouse-icon-png-image_707463.jpg", false, "COMMERCIAL"));
        lstcategory.add(new POJO_PropType("Other Commercial", "https://cdn.iconscout.com/icon/premium/png-256-thumb/building-1256-486704.png", false, "COMMERCIAL"));

        adapter = new RecyclerviewPropTypes(SellPropActivity.this, lstcategory);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(SellPropActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        linearLayoutManager.setStackFromEnd(false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

        minBudgetList.add("₹ Min");
        minBudgetList.add("₹ 5 Lac");
        minBudgetList.add("₹ 10 Lac");
        minBudgetList.add("₹ 20 Lac");
        minBudgetList.add("₹ 30 Lac");
        minBudgetList.add("₹ 40 Lac");
        minBudgetList.add("₹ 50 Lac");
        minBudgetList.add("₹ 60 Lac");
        minBudgetList.add("₹ 70 Lac");
        minBudgetList.add("₹ 80 Lac");
        minBudgetList.add("₹ 90 Lac");
        minBudgetList.add("₹ 1 Cr");
        minBudgetList.add("₹ 1.2 Cr");
        minBudgetList.add("₹ 1.6 Cr");
        minBudgetList.add("₹ 1.8 Cr");
        minBudgetList.add("₹ 2 Cr");
        minBudgetList.add("₹ 2.3 Cr");
        minBudgetList.add("₹ 2.6 Cr");
        minBudgetList.add("₹ 3 Cr");
        minBudgetList.add("₹ 3.5 Cr");
        minBudgetList.add("₹ 4 Cr");
        minBudgetList.add("₹ 4.5 Cr");
        minBudgetList.add("₹ 5 Cr");
        minBudgetList.add("₹ 10 Cr");
        minBudgetList.add("₹ 15 Cr");

        maxBudgetList.add("₹ Max");
        maxBudgetList.add("₹ 5 Lac");
        maxBudgetList.add("₹ 10 Lac");
        maxBudgetList.add("₹ 20 Lac");
        maxBudgetList.add("₹ 30 Lac");
        maxBudgetList.add("₹ 40 Lac");
        maxBudgetList.add("₹ 50 Lac");
        maxBudgetList.add("₹ 60 Lac");
        maxBudgetList.add("₹ 70 Lac");
        maxBudgetList.add("₹ 80 Lac");
        maxBudgetList.add("₹ 90 Lac");
        maxBudgetList.add("₹ 1 Cr");
        maxBudgetList.add("₹ 1.2 Cr");
        maxBudgetList.add("₹ 1.6 Cr");
        maxBudgetList.add("₹ 1.8 Cr");
        maxBudgetList.add("₹ 2 Cr");
        maxBudgetList.add("₹ 2.3 Cr");
        maxBudgetList.add("₹ 2.6 Cr");
        maxBudgetList.add("₹ 3 Cr");
        maxBudgetList.add("₹ 3.5 Cr");
        maxBudgetList.add("₹ 4 Cr");
        maxBudgetList.add("₹ 4.5 Cr");
        maxBudgetList.add("₹ 5 Cr");
        maxBudgetList.add("₹ 10 Cr");
        maxBudgetList.add("₹ 15 Cr");

        final ArrayAdapter<String> spinnerArrayAdapterMin = new ArrayAdapter<String>(SellPropActivity.this, android.R.layout.simple_spinner_item, minBudgetList);
        spinnerArrayAdapterMin.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        minBudget.setAdapter(spinnerArrayAdapterMin);

        final ArrayAdapter<String> spinnerArrayAdapterMax = new ArrayAdapter<String>(SellPropActivity.this, android.R.layout.simple_spinner_item, maxBudgetList);
        spinnerArrayAdapterMax.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        maxBudget.setAdapter(spinnerArrayAdapterMax);

        lstcategoryBedrooms.add(new POJO_bedrooms("1 BHK", false));
        lstcategoryBedrooms.add(new POJO_bedrooms("2 BHK", false));
        lstcategoryBedrooms.add(new POJO_bedrooms("3 BHK", false));
        lstcategoryBedrooms.add(new POJO_bedrooms("4 BHK", false));
        lstcategoryBedrooms.add(new POJO_bedrooms(">4 BHK", false));

        adapterBedrooms = new RecyclerviewAdapterBedrooms(SellPropActivity.this, lstcategoryBedrooms);

        LinearLayoutManager linearLayoutManagerBdrm = new LinearLayoutManager(SellPropActivity.this);
        linearLayoutManagerBdrm.setOrientation(LinearLayoutManager.HORIZONTAL);
        linearLayoutManagerBdrm.setStackFromEnd(false);
        recyclerViewBedrooms.setLayoutManager(linearLayoutManagerBdrm);
        recyclerViewBedrooms.setAdapter(adapterBedrooms);

        underConstruction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (underConStatus) {
                    underConStatus = false;
                    underConstruction.setBackgroundTintList(ContextCompat.getColorStateList(SellPropActivity.this, R.color.white));
                } else {
                    underConstruction.setBackgroundTintList(ContextCompat.getColorStateList(SellPropActivity.this, R.color.colorSelected));
                    underConStatus = true;
                }
            }
        });

        readyToMove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (readyToMvStatus) {
                    readyToMvStatus = false;
                    readyToMove.setBackgroundTintList(ContextCompat.getColorStateList(SellPropActivity.this, R.color.white));
                } else {
                    readyToMove.setBackgroundTintList(ContextCompat.getColorStateList(SellPropActivity.this, R.color.colorSelected));
                    readyToMvStatus = true;
                }
            }
        });

        frmYearPossesList.add("2019");
        frmYearPossesList.add("2020");
        frmYearPossesList.add("2021");
        frmYearPossesList.add("2022");
        frmYearPossesList.add("2023");
        frmYearPossesList.add("2024");
        frmYearPossesList.add("2025");
        frmYearPossesList.add("2026");
        frmYearPossesList.add("2027");
        frmYearPossesList.add("2028");

//        final ArrayAdapter<String> spinnerArrayAdapterfrmYrSpn = new ArrayAdapter<String>(activity, android.R.layout.simple_spinner_item, frmYearPossesList);
//        spinnerArrayAdapterMin.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        frmYearPosSpin.setAdapter(spinnerArrayAdapterfrmYrSpn);

        final ArrayAdapter<String> spinnerArrayAdapterfrmYrSpn = new ArrayAdapter<String>(SellPropActivity.this, android.R.layout.simple_spinner_item, frmYearPossesList);
        spinnerArrayAdapterfrmYrSpn.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        frmYearPosSpin.setAdapter(spinnerArrayAdapterfrmYrSpn);

        toYearPossesList.add("2020");
        toYearPossesList.add("2021");
        toYearPossesList.add("2022");
        toYearPossesList.add("2023");
        toYearPossesList.add("2024");
        toYearPossesList.add("2025");
        toYearPossesList.add("2026");
        toYearPossesList.add("2027");
        toYearPossesList.add("2028");

        final ArrayAdapter<String> spinnerArrayAdapterToYrSpn = new ArrayAdapter<String>(SellPropActivity.this, android.R.layout.simple_spinner_item, toYearPossesList);
        spinnerArrayAdapterToYrSpn.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        toYearPosSPin.setAdapter(spinnerArrayAdapterToYrSpn);

//                moreFilterTv.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        moreFilterTv.setVisibility(View.GONE);
//                        moreFiltLinLay.setVisibility(View.VISIBLE);
//                    }
//                });


        lstcategoryAmenities.add(new POJO_Amenities("Parking", "https://img.icons8.com/plasticine/2x/car.png", false));
        lstcategoryAmenities.add(new POJO_Amenities("Lift", "https://upload.wikimedia.org/wikipedia/commons/thumb/e/eb/KRL_Icon_Pink.svg/1024px-KRL_Icon_Pink.svg.png", false));
        lstcategoryAmenities.add(new POJO_Amenities("Power Backup", "https://cdn.iconscout.com/icon/premium/png-512-thumb/power-generator-1717542-1461298.png", false));
        lstcategoryAmenities.add(new POJO_Amenities("Gas Pipeline", "https://cdn2.iconfinder.com/data/icons/construction-butterscotch-vol-2/512/Pipeline-512.png", false));
        lstcategoryAmenities.add(new POJO_Amenities("Park", "https://cdn4.iconfinder.com/data/icons/jetflat-2-buildings-vol-1/60/008_035_ferris_wheel_park_amusement_attractions-512.png", false));
        lstcategoryAmenities.add(new POJO_Amenities("Swimming Pool", "https://png.pngtree.com/png-vector/20190830/ourlarge/pngtree-swimming-pool-icon-design-vector-png-image_1708730.jpg", false));
        lstcategoryAmenities.add(new POJO_Amenities("Club House", "https://cdn0.iconfinder.com/data/icons/edm-red/64/HOUSE-club-party-musical-music-512.png", false));


        recyclerViewAdapterAmenities = new RecyclerviewAdapterAmenities(SellPropActivity.this, lstcategoryAmenities, true);

        LinearLayoutManager linearLayoutManagerAmenities = new LinearLayoutManager(SellPropActivity.this);
        linearLayoutManagerAmenities.setOrientation(LinearLayoutManager.HORIZONTAL);
        linearLayoutManagerAmenities.setStackFromEnd(false);
        recyclerViewAmenities.setLayoutManager(linearLayoutManagerAmenities);
        recyclerViewAmenities.setAdapter(recyclerViewAdapterAmenities);


        societyList.add("Western Avenue");
        societyList.add("VTP Hilife");
        societyList.add("Signature Park");
        societyList.add("Sancheti Dreamcastle");
        societyList.add("Twin Tower");
        societyList.add("Yashwin Encore");
        societyList.add("ITrend Life");
        societyList.add("Akshar ELementa");
        societyList.add("Park Titanium");
        societyList.add("Kalpataru Harmony");
        societyList.add("Royal Grande");

        final ArrayAdapter<String> spinnerArrayAdapterSociety = new ArrayAdapter<String>(SellPropActivity.this, android.R.layout.simple_spinner_item, societyList);
        spinnerArrayAdapterSociety.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        societySpin.setAdapter(spinnerArrayAdapterSociety);


        floorLevelList.add("Select Floor");
        floorLevelList.add("Ground Floor");
        floorLevelList.add("1");
        floorLevelList.add("2");
        floorLevelList.add("3");
        floorLevelList.add("4");
        floorLevelList.add("5");
        floorLevelList.add("6");
        floorLevelList.add("7");

        final ArrayAdapter<String> spinnerArrayAdapterFloor = new ArrayAdapter<String>(SellPropActivity.this, android.R.layout.simple_spinner_item, floorLevelList);
        spinnerArrayAdapterFloor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        floorLevelSpin.setAdapter(spinnerArrayAdapterFloor);


        maintanceChrgsList.add("Monthly");
        maintanceChrgsList.add("Yearly");

        final ArrayAdapter<String> spinAdapterMaintainance = new ArrayAdapter<String>(SellPropActivity.this, android.R.layout.simple_spinner_item, maintanceChrgsList);
        spinAdapterMaintainance.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        maintainanceChrgesSpn.setAdapter(spinAdapterMaintainance);


        propAreaSpnList.add("sqft");
        propAreaSpnList.add("sq-yrd");
        propAreaSpnList.add("sq-m");

        final ArrayAdapter<String> spinAdapterPropArea = new ArrayAdapter<String>(SellPropActivity.this, android.R.layout.simple_spinner_item, propAreaSpnList);
        spinAdapterPropArea.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        propAreaSpn.setAdapter(spinAdapterPropArea);

        bathroomList.add("1");
        bathroomList.add("2");
        bathroomList.add("3");
        bathroomList.add("4");
        bathroomList.add("4+");

        final ArrayAdapter<String> spinAdapterPropbathroom = new ArrayAdapter<String>(SellPropActivity.this, android.R.layout.simple_spinner_item, bathroomList);
        spinAdapterPropbathroom.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bathroomSpn.setAdapter(spinAdapterPropbathroom);

        balconySpnList.add("1");
        balconySpnList.add("2");
        balconySpnList.add("3");
        balconySpnList.add("4");
        balconySpnList.add("4+");

        final ArrayAdapter<String> spinAdapterPropBalcony = new ArrayAdapter<String>(SellPropActivity.this, android.R.layout.simple_spinner_item, balconySpnList);
        spinAdapterPropBalcony.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        balconySpn.setAdapter(spinAdapterPropBalcony);

        btnPostProperty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new KAlertDialog(SellPropActivity.this, KAlertDialog.SUCCESS_TYPE)
                        .setTitleText("Success!")
                        .setContentText("Your property has been posted successfully, it will reflect in our app shortly.")
                        .setConfirmClickListener(new KAlertDialog.KAlertClickListener() {
                            @Override
                            public void onClick(KAlertDialog kAlertDialog) {
                                kAlertDialog.dismiss();

                            }
                        })
                        .confirmButtonColor(R.drawable.kalert_button_background)
                        .show();
            }
        });

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
