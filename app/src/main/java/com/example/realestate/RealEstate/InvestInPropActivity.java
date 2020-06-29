package com.example.realestate.RealEstate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.realestate.R;
import com.example.realestate.TempProjectsActivity;
import com.pchmn.materialchips.ChipsInput;
import com.pchmn.materialchips.model.ChipInterface;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class InvestInPropActivity extends AppCompatActivity {

    private RecyclerviewPropTypes adapter;
    private RecyclerView recyclerView;
    private ArrayList<POJO_PropType> lstcategory;
    private Spinner minBudget, maxBudget;

    private ArrayList<String> minBudgetList = new ArrayList<>();
    private ArrayList<String> maxBudgetList = new ArrayList<>();

    private RecyclerviewAdapterBedrooms adapterBedrooms;
    private RecyclerView recyclerViewBedrooms;
    private ArrayList<POJO_bedrooms> lstcategoryBedrooms;

    //private Button recidential, commercial ,agricultural;
    private boolean underConStatus = false, readyToMvStatus = false;

    private ArrayList<String> frmYearPossesList = new ArrayList<>();
    private ArrayList<String> toYearPossesList = new ArrayList<>();
    private Spinner frmYearPosSpin, toYearPosSPin;

    private LinearLayout moreFiltLinLay;
    //private TextView moreFilterTv;

    private RecyclerviewAdapterAmenities recyclerViewAdapterAmenities;
    private ArrayList<POJO_Amenities> lstcategoryAmenities;
    private RecyclerView recyclerViewAmenities;

    private Spinner societySpin;
    private ArrayList<String> societyList = new ArrayList<>();

    private Spinner floorLevelSpin;
    private ArrayList<String> floorLevelList = new ArrayList<>();

//    private Spinner propAreaSpn;
//    private ArrayList<String> propAreaSpnList = new ArrayList<>();

//    private Spinner bathroomSpn;
//    private ArrayList<String> bathroomList = new ArrayList<>();
//
//    private Spinner balconySpn;
//    private ArrayList<String> balconySpnList = new ArrayList<>();

    private RecyclerView rvBathroom;
    private RecyclerviewAdapterTextOpts bathroomAdapter;
    private ArrayList<Pojo_TextOpts> lstcategoryBathroom;

    private RecyclerView rvBalcony;
    private RecyclerviewAdapterTextOpts balconyAdapter;
    private ArrayList<Pojo_TextOpts> lstcategoryBalcony;

    private ChipsInput chipsInput;

    private Spinner propSpn, propAreaMinSpn, propAreaMaxSpn;
    private ArrayList<String> areaList = new ArrayList<>();
    private ArrayList<String> areaMinList = new ArrayList<>();
    private ArrayList<String> areaMaxList = new ArrayList<>();

    private Button btnSeeProjects;

    private RecyclerView rvFacing;
    private RecyclerviewAdapterTextOpts facingAdapter;
    private ArrayList<Pojo_TextOpts> lstcategoryFacing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invest_in_prop);
        setTitle("Invest In Real Estate");

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = (RecyclerView) findViewById(R.id.recyc_propType);
        minBudget = (Spinner) findViewById(R.id.minBudgt_Spin);
        maxBudget = (Spinner) findViewById(R.id.maxBudget_Spin);

        recyclerViewBedrooms = (RecyclerView) findViewById(R.id.recyc_bedrooms);

//        recidential = (Button) findViewById(R.id.btnResidential);
//        commercial = (Button) findViewById(R.id.btnCommercial);
//        agricultural = (Button) findViewById(R.id.btnAgricul);

        frmYearPosSpin = (Spinner) findViewById(R.id.fromYearPossesSpin);
        toYearPosSPin = (Spinner) findViewById(R.id.toYearPossesSPin);

        moreFiltLinLay = (LinearLayout) findViewById(R.id.moreFiltersLinLay);
        //moreFilterTv = (TextView) findViewById(R.id.moreFilters_Tv);

        societySpin = (Spinner) findViewById(R.id.societySpinner);
        //propAreaSpn = (Spinner) findViewById(R.id.propAreaspn);

        floorLevelSpin = (Spinner) findViewById(R.id.floorNoSpin);
//        bathroomSpn = (Spinner) findViewById(R.id.bathroomSpinner);
//        balconySpn = (Spinner) findViewById(R.id.balconySpinner);

        recyclerViewAmenities = (RecyclerView) findViewById(R.id.recyc_amenities);

        chipsInput = (ChipsInput) findViewById(R.id.locationsChips);

        propSpn = (Spinner) findViewById(R.id.propAreaSpin);
        propAreaMinSpn = (Spinner) findViewById(R.id.propAreaMinSpin);
        propAreaMaxSpn = (Spinner) findViewById(R.id.propAreaMaxSpin);

        btnSeeProjects = (Button) findViewById(R.id.btnSeeProjects);

        rvFacing = (RecyclerView) findViewById(R.id.recyc_facing);
        rvBathroom = (RecyclerView) findViewById(R.id.recyc_bathroom);
        rvBalcony = (RecyclerView) findViewById(R.id.recyc_balcony);

        List<LocationChip> locationList = new ArrayList<>();
        locationList.add(new LocationChip("1", "Pune", "Swarget, Pune"));
        locationList.add(new LocationChip("2", "Katraj Pune", "Katraj, Pune"));
        locationList.add(new LocationChip("3", "Hinjawdi Pune", "Hinjawdi, Pune"));
        locationList.add(new LocationChip("4", "Camp Pune", "Camp, Pune"));
        locationList.add(new LocationChip("5", "Magarpatta Pune", "Magarpatta, Pune"));
        locationList.add(new LocationChip("6", "Sadar Bazar Satara", "Sadar Bazar, Satara"));
        locationList.add(new LocationChip("7", "Moti Chowk Satara", "Moti Chowk, Satara"));
        locationList.add(new LocationChip("8", "Godoli Satara", "Godoli, Satara"));
        locationList.add(new LocationChip("9", "Cooper Colony Satara", "Cooper Colony, Satara"));
        locationList.add(new LocationChip("10", "Mangalwar Peth Kolhapur", "Mangalwar Peth, Kolhapur"));
        locationList.add(new LocationChip("11", "Rajarampuri Kolhapur", "Rajarampuri, Kolhapur"));
        locationList.add(new LocationChip("12", "Petala Kolhapur", "Petala, Kolhapur"));
        locationList.add(new LocationChip("13", "Sambhaji Nagar Kolhapur", "Sambhaji Nagar, Kolhapur"));
        locationList.add(new LocationChip("14", "Mahadwar Road Kolhapur", "Mahadwar Road, Kolhapur"));
        locationList.add(new LocationChip("15", "Sangli", "Shivaji Chowk, Sangli"));
        locationList.add(new LocationChip("16", "Panvel Mumbai", "Panvel, Mumbai"));
        locationList.add(new LocationChip("17", "Dombiwali Mumbai", "Dombiwali, Mumbai"));
        chipsInput.setFilterableList(locationList);


        chipsInput.addChipsListener(new ChipsInput.ChipsListener() {
            @Override
            public void onChipAdded(ChipInterface chip, int newSize) {
                // chip added
                // newSize is the size of the updated selected chip list
                //Toast.makeText(activity, "Chip Added  Chip : " + " " + chip + " " + " new Size : " + " " + newSize, Toast.LENGTH_SHORT).show();
                Log.d("CHIPSLOG", "Chip Added  Chip : " + " " + chip + " " + " new Size : " + " " + newSize);

                List<ChipInterface> contactsSelected = (List<ChipInterface>) chipsInput.getSelectedChipList();

                if(contactsSelected.size() > 5){
                    //Toast.makeText(activity, "Cannot add more than 5 locations", Toast.LENGTH_SHORT).show();
                    //Log.d("CHIPSLOG", "Cannot add more than 5 locations" + " " + "removed chip loc : " + " " + contactsSelected.get(newSize -1).getLabel());
                    //chipsInput.removeChipById(String.valueOf(newSize - 1));
                    //chipsInput.removeChipByLabel(chip.getLabel());
                    //chipsInput.notify();
                    //chipsInput.notifyAll();

                }
            }

            @Override
            public void onChipRemoved(ChipInterface chip, int newSize) {
                // chip removed
                // newSize is the size of the updated selected chip list
            }

            @Override
            public void onTextChanged(CharSequence text) {
                // text changed
            }
        });

        lstcategory = new ArrayList<>();
        lstcategoryBedrooms = new ArrayList<>();
        lstcategoryAmenities = new ArrayList<>();
        lstcategoryFacing = new ArrayList<>();
        lstcategoryBathroom = new ArrayList<>();
        lstcategoryBalcony = new ArrayList<>();

        /*moreFilterTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moreFilterTv.setVisibility(View.GONE);
                moreFiltLinLay.setVisibility(View.VISIBLE);
            }
        });*/

        lstcategory.add(new POJO_PropType("FLat", "https://icon-library.net/images/apartment-icon/apartment-icon-5.jpg", false, "RESIDENTIAL"));
        lstcategory.add(new POJO_PropType("House/Villa", "https://img.icons8.com/plasticine/2x/home.png", false, "RESIDENTIAL"));
        lstcategory.add(new POJO_PropType("NA Plot", "https://cdn3.iconfinder.com/data/icons/real-estate-flat-icons-vol-2/256/68-512.png", false, "LAND"));
        lstcategory.add(new POJO_PropType("Agricultural Land", "https://webstockreview.net/images/industry-clipart-agro-based-industry-11.png", false, "AGRICULTURAL-LAND"));
        lstcategory.add(new POJO_PropType("Office Space", "https://c7.uihere.com/files/169/771/802/building-icon-flat-building.jpg", false, "COMMERCIAL"));
        lstcategory.add(new POJO_PropType("Shop/Showroom", "https://png.pngtree.com/png-vector/20190227/ourmid/pngtree-vector-storage-warehouse-icon-png-image_707463.jpg", false, "COMMERCIAL"));
        lstcategory.add(new POJO_PropType("Other Commercial", "https://cdn.iconscout.com/icon/premium/png-256-thumb/building-1256-486704.png", false, "COMMERCIAL"));

        adapter = new RecyclerviewPropTypes(InvestInPropActivity.this, lstcategory);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(InvestInPropActivity.this);
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

        final ArrayAdapter<String> spinnerArrayAdapterMin = new ArrayAdapter<String>(InvestInPropActivity.this, android.R.layout.simple_spinner_item, minBudgetList);
        spinnerArrayAdapterMin.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        minBudget.setAdapter(spinnerArrayAdapterMin);

        final ArrayAdapter<String> spinnerArrayAdapterMax = new ArrayAdapter<String>(InvestInPropActivity.this, android.R.layout.simple_spinner_item, maxBudgetList);
        spinnerArrayAdapterMax.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        maxBudget.setAdapter(spinnerArrayAdapterMax);

        lstcategoryBedrooms.add(new POJO_bedrooms("1 BHK", false));
        lstcategoryBedrooms.add(new POJO_bedrooms("2 BHK", false));
        lstcategoryBedrooms.add(new POJO_bedrooms("3 BHK", false));
        lstcategoryBedrooms.add(new POJO_bedrooms("4 BHK", false));
        lstcategoryBedrooms.add(new POJO_bedrooms(">4 BHK", false));

        adapterBedrooms = new RecyclerviewAdapterBedrooms(InvestInPropActivity.this, lstcategoryBedrooms);

        LinearLayoutManager linearLayoutManagerBdrm = new LinearLayoutManager(InvestInPropActivity.this);
        linearLayoutManagerBdrm.setOrientation(LinearLayoutManager.HORIZONTAL);
        linearLayoutManagerBdrm.setStackFromEnd(false);
        recyclerViewBedrooms.setLayoutManager(linearLayoutManagerBdrm);
        recyclerViewBedrooms.setAdapter(adapterBedrooms);

        /*recidential.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(underConStatus){
                    underConStatus = false;
                    recidential.setBackgroundTintList(ContextCompat.getColorStateList(InvestInPropActivity.this, R.color.white));
                } else {
                    recidential.setBackgroundTintList(ContextCompat.getColorStateList(InvestInPropActivity.this, R.color.colorSelected));
                    underConStatus = true;
                }
            }
        });

        commercial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(readyToMvStatus){
                    readyToMvStatus = false;
                    commercial.setBackgroundTintList(ContextCompat.getColorStateList(InvestInPropActivity.this, R.color.white));
                } else {
                    commercial.setBackgroundTintList(ContextCompat.getColorStateList(InvestInPropActivity.this, R.color.colorSelected));
                    readyToMvStatus = true;
                }
            }
        });

        agricultural.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(readyToMvStatus){
                    readyToMvStatus = false;
                    agricultural.setBackgroundTintList(ContextCompat.getColorStateList(InvestInPropActivity.this, R.color.white));
                } else {
                    agricultural.setBackgroundTintList(ContextCompat.getColorStateList(InvestInPropActivity.this, R.color.colorSelected));
                    readyToMvStatus = true;
                }
            }
        });*/

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

        final ArrayAdapter<String> spinnerArrayAdapterfrmYrSpn = new ArrayAdapter<String>(InvestInPropActivity.this, android.R.layout.simple_spinner_item, frmYearPossesList);
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

        final ArrayAdapter<String> spinnerArrayAdapterToYrSpn = new ArrayAdapter<String>(InvestInPropActivity.this, android.R.layout.simple_spinner_item, toYearPossesList);
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


        recyclerViewAdapterAmenities = new RecyclerviewAdapterAmenities(InvestInPropActivity.this, lstcategoryAmenities, true);

        LinearLayoutManager linearLayoutManagerAmenities = new LinearLayoutManager(InvestInPropActivity.this);
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

        final ArrayAdapter<String> spinnerArrayAdapterSociety = new ArrayAdapter<String>(InvestInPropActivity.this, android.R.layout.simple_spinner_item, societyList);
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

        final ArrayAdapter<String> spinnerArrayAdapterFloor = new ArrayAdapter<String>(InvestInPropActivity.this, android.R.layout.simple_spinner_item, floorLevelList);
        spinnerArrayAdapterFloor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        floorLevelSpin.setAdapter(spinnerArrayAdapterFloor);


        areaList.add("Sqft");
        areaList.add("Sqyrd");
        areaList.add("Sqm");
        areaList.add("Acre");
        areaList.add("Hectare");
        areaList.add("Guntha");

        final ArrayAdapter<String> spinAdapterArea = new ArrayAdapter<String>(InvestInPropActivity.this, android.R.layout.simple_spinner_item, areaList);
        spinAdapterArea.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        propSpn.setAdapter(spinAdapterArea);

        areaMinList.add("Min");
        areaMinList.add("100");
        areaMinList.add("200");
        areaMinList.add("300");
        areaMinList.add("400");
        areaMinList.add("1000");
        areaMinList.add("1500");
        areaMinList.add("2000");
        areaMinList.add("3000");
        areaMinList.add("4000");
        areaMinList.add("5000");
        areaMinList.add("10000");
        areaMinList.add("25000");
        areaMinList.add("50000");
        areaMinList.add("50000+");

        final ArrayAdapter<String> spinAdapterAreaMin = new ArrayAdapter<String>(InvestInPropActivity.this, android.R.layout.simple_spinner_item, areaMinList);
        spinAdapterAreaMin.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        propAreaMinSpn.setAdapter(spinAdapterAreaMin);

        areaMaxList.add("Max");
        areaMaxList.add("100");
        areaMaxList.add("200");
        areaMaxList.add("300");
        areaMaxList.add("400");
        areaMaxList.add("1000");
        areaMaxList.add("1500");
        areaMaxList.add("2000");
        areaMaxList.add("3000");
        areaMaxList.add("4000");
        areaMaxList.add("5000");
        areaMaxList.add("10000");
        areaMaxList.add("25000");
        areaMaxList.add("50000");
        areaMaxList.add("50000+");

        final ArrayAdapter<String> spinAdapterAreaMax = new ArrayAdapter<String>(InvestInPropActivity.this, android.R.layout.simple_spinner_item, areaMaxList);
        spinAdapterAreaMax.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        propAreaMaxSpn.setAdapter(spinAdapterAreaMax);

        /*bathroomList.add("1");
        bathroomList.add("2");
        bathroomList.add("3");
        bathroomList.add("4");
        bathroomList.add("4+");

        final ArrayAdapter<String> spinAdapterPropbathroom = new ArrayAdapter<String>(InvestInPropActivity.this, android.R.layout.simple_spinner_item, bathroomList);
        spinAdapterPropbathroom.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bathroomSpn.setAdapter(spinAdapterPropbathroom);

        balconySpnList.add("1");
        balconySpnList.add("2");
        balconySpnList.add("3");
        balconySpnList.add("4");
        balconySpnList.add("4+");

        final ArrayAdapter<String> spinAdapterPropBalcony = new ArrayAdapter<String>(InvestInPropActivity.this, android.R.layout.simple_spinner_item, balconySpnList);
        spinAdapterPropBalcony.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        balconySpn.setAdapter(spinAdapterPropBalcony);*/

        lstcategoryBathroom.add(new Pojo_TextOpts("1", false));
        lstcategoryBathroom.add(new Pojo_TextOpts("2", false));
        lstcategoryBathroom.add(new Pojo_TextOpts("3", false));
        lstcategoryBathroom.add(new Pojo_TextOpts("4", false));
        lstcategoryBathroom.add(new Pojo_TextOpts("4+", false));

        bathroomAdapter = new RecyclerviewAdapterTextOpts(InvestInPropActivity.this, lstcategoryBathroom);

        LinearLayoutManager linearLayoutManagerBathroom = new LinearLayoutManager(InvestInPropActivity.this);
        linearLayoutManagerBathroom.setOrientation(LinearLayoutManager.HORIZONTAL);
        linearLayoutManagerBathroom.setStackFromEnd(false);
        rvBathroom.setLayoutManager(linearLayoutManagerBathroom);
        rvBathroom.setAdapter(bathroomAdapter);


        lstcategoryBalcony.add(new Pojo_TextOpts("1", false));
        lstcategoryBalcony.add(new Pojo_TextOpts("2", false));
        lstcategoryBalcony.add(new Pojo_TextOpts("3", false));
        lstcategoryBalcony.add(new Pojo_TextOpts("4", false));
        lstcategoryBalcony.add(new Pojo_TextOpts("4+", false));

        balconyAdapter = new RecyclerviewAdapterTextOpts(InvestInPropActivity.this, lstcategoryBalcony);

        LinearLayoutManager linearLayoutManagerBalcony = new LinearLayoutManager(InvestInPropActivity.this);
        linearLayoutManagerBalcony.setOrientation(LinearLayoutManager.HORIZONTAL);
        linearLayoutManagerBalcony.setStackFromEnd(false);
        rvBalcony.setLayoutManager(linearLayoutManagerBalcony);
        rvBalcony.setAdapter(balconyAdapter);

        btnSeeProjects.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InvestInPropActivity.this, TempProjectsActivity.class);
                intent.putExtra("TITLE", "Projects to Invest in");
                startActivity(intent);
            }
        });

        lstcategoryFacing.add(new Pojo_TextOpts("East", false));
        lstcategoryFacing.add(new Pojo_TextOpts("North", false));
        lstcategoryFacing.add(new Pojo_TextOpts("North-East", false));
        lstcategoryFacing.add(new Pojo_TextOpts("North-West", false));
        lstcategoryFacing.add(new Pojo_TextOpts("South", false));
        lstcategoryFacing.add(new Pojo_TextOpts("South-East", false));
        lstcategoryFacing.add(new Pojo_TextOpts("South-West", false));
        lstcategoryFacing.add(new Pojo_TextOpts("West", false));

        facingAdapter = new RecyclerviewAdapterTextOpts(InvestInPropActivity.this, lstcategoryFacing);

        LinearLayoutManager linearLayoutManagerFacing = new LinearLayoutManager(InvestInPropActivity.this);
        linearLayoutManagerFacing.setOrientation(LinearLayoutManager.HORIZONTAL);
        linearLayoutManagerFacing.setStackFromEnd(false);
        rvFacing.setLayoutManager(linearLayoutManagerFacing);
        rvFacing.setAdapter(facingAdapter);
        
        
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
