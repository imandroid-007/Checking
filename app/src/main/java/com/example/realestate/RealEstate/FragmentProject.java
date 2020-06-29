package com.example.realestate.RealEstate;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.realestate.R;
import com.example.realestate.TempProjectsActivity;
import com.pchmn.materialchips.ChipsInput;
import com.pchmn.materialchips.model.ChipInterface;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentProject extends Fragment {


    public FragmentProject() {
        // Required empty public constructor
    }

    View rootview;
    FragmentActivity activity;
    Context mContext;

    private ChipsInput chipsInputLocation;
    private Button seeProjectsBtn;

    private Spinner minBudget, maxBudget;

    private ArrayList<String> minBudgetList = new ArrayList<>();
    private ArrayList<String> maxBudgetList = new ArrayList<>();

    private RecyclerviewAdapterBedrooms adapterBedrooms;
    private RecyclerView recyclerViewBedrooms;
    private ArrayList<POJO_bedrooms> lstcategoryBedrooms;

    private RecyclerviewPropTypes adapter;
    private RecyclerView recyclerView;
    private ArrayList<POJO_PropType> lstcategory;

    private RecyclerviewAdapterAmenities recyclerViewAdapterAmenities;
    private ArrayList<POJO_Amenities> lstcategoryAmenities;
    private RecyclerView recyclerViewAmenities;

    private LinearLayout moreFiltLinLay;
    private TextView moreFilterTv;

    private Button underConstruction, readyToMove;
    private boolean underConStatus = false, readyToMvStatus = false;

    private Spinner societySpin;
    private ArrayList<String> societyList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootview = inflater.inflate(R.layout.fragment_fragment_project, container, false);

        chipsInputLocation = (ChipsInput) rootview.findViewById(R.id.locationsChips);
        seeProjectsBtn = (Button) rootview.findViewById(R.id.seeProjBtn);

        minBudget = (Spinner) rootview.findViewById(R.id.minBudgt_Spin);
        maxBudget = (Spinner) rootview.findViewById(R.id.maxBudget_Spin);

        recyclerView = (RecyclerView) rootview.findViewById(R.id.recyc_propType);
        recyclerViewBedrooms = (RecyclerView) rootview.findViewById(R.id.recyc_bedrooms);
        recyclerViewAmenities = (RecyclerView) rootview.findViewById(R.id.recyc_amenities);

        moreFiltLinLay = (LinearLayout) rootview.findViewById(R.id.moreFiltersLinLay);
        //moreFilterTv = (TextView) rootview.findViewById(R.id.moreFilters_Tv);

        underConstruction = (Button) rootview.findViewById(R.id.btnUnderConstr);
        readyToMove = (Button) rootview.findViewById(R.id.btnReadyToMove);

        societySpin = (Spinner) rootview.findViewById(R.id.societySpinner);

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
        chipsInputLocation.setFilterableList(locationList);

        chipsInputLocation.addChipsListener(new ChipsInput.ChipsListener() {
            @Override
            public void onChipAdded(ChipInterface chip, int newSize) {
                // chip added
                // newSize is the size of the updated selected chip list
                //Toast.makeText(activity, "Chip Added  Chip : " + " " + chip + " " + " new Size : " + " " + newSize, Toast.LENGTH_SHORT).show();
                Log.d("CHIPSLOG", "Chip Added  Chip : " + " " + chip + " " + " new Size : " + " " + newSize);

                List<ChipInterface> contactsSelected = (List<ChipInterface>) chipsInputLocation.getSelectedChipList();

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

        seeProjectsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<ChipInterface> contactsSelected = (List<ChipInterface>) chipsInputLocation.getSelectedChipList();

                if(contactsSelected.size() > 0) {
                    Log.d("CHIPSLOG", "Locations.Size() : " + " " + contactsSelected.size());
                    Log.d("CHIPSLOG", "Locations data Label : " + " " + contactsSelected.get(0).getLabel() + "  " + "Info : " + " " + contactsSelected.get(0).getInfo());
                }

                Intent intent = new Intent(activity, TempProjectsActivity.class);
                intent.putExtra("TITLE", "Projects");
                startActivity(intent);
            }
        });

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

        final ArrayAdapter<String> spinnerArrayAdapterMin = new ArrayAdapter<String>(activity, android.R.layout.simple_spinner_item, minBudgetList);
        spinnerArrayAdapterMin.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        minBudget.setAdapter(spinnerArrayAdapterMin);

        final ArrayAdapter<String> spinnerArrayAdapterMax = new ArrayAdapter<String>(activity, android.R.layout.simple_spinner_item, maxBudgetList);
        spinnerArrayAdapterMax.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        maxBudget.setAdapter(spinnerArrayAdapterMax);

        lstcategory = new ArrayList<>();
        lstcategoryBedrooms = new ArrayList<>();
        lstcategoryAmenities = new ArrayList<>();

        lstcategory.add(new POJO_PropType("FLat", "https://icon-library.net/images/apartment-icon/apartment-icon-5.jpg", false, "RECIDENTIAL"));
        lstcategory.add(new POJO_PropType("House/Villa", "https://img.icons8.com/plasticine/2x/home.png", false, "RECIDENTIAL"));
        //lstcategory.add(new POJO_PropType("Plot", "https://cdn3.iconfinder.com/data/icons/real-estate-flat-icons-vol-2/256/68-512.png", false, "LAND"));
        lstcategory.add(new POJO_PropType("Office Space", "https://c7.uihere.com/files/169/771/802/building-icon-flat-building.jpg", false, "COMMERCIAL"));
        lstcategory.add(new POJO_PropType("Shop/Showroom", "https://png.pngtree.com/png-vector/20190227/ourmid/pngtree-vector-storage-warehouse-icon-png-image_707463.jpg", false, "COMMERCIAL"));
        lstcategory.add(new POJO_PropType("Other Commercial", "https://cdn.iconscout.com/icon/premium/png-256-thumb/building-1256-486704.png", false, "COMMERCIAL"));

        adapter = new RecyclerviewPropTypes(activity, lstcategory);

        GridLayoutManager linearLayoutManager = new GridLayoutManager(activity,3);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        linearLayoutManager.setStackFromEnd(false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);


        lstcategoryBedrooms.add(new POJO_bedrooms("1 BHK", false));
        lstcategoryBedrooms.add(new POJO_bedrooms("2 BHK", false));
        lstcategoryBedrooms.add(new POJO_bedrooms("3 BHK", false));
        lstcategoryBedrooms.add(new POJO_bedrooms("4 BHK", false));
        lstcategoryBedrooms.add(new POJO_bedrooms(">4 BHK", false));

        adapterBedrooms = new RecyclerviewAdapterBedrooms(activity, lstcategoryBedrooms);

        LinearLayoutManager linearLayoutManagerBdrm = new LinearLayoutManager(activity);
        linearLayoutManagerBdrm.setOrientation(LinearLayoutManager.HORIZONTAL);
        linearLayoutManagerBdrm.setStackFromEnd(false);
        recyclerViewBedrooms.setLayoutManager(linearLayoutManagerBdrm);
        recyclerViewBedrooms.setAdapter(adapterBedrooms);

        lstcategoryAmenities.add(new POJO_Amenities("Parking", "https://img.icons8.com/plasticine/2x/car.png", false));
        lstcategoryAmenities.add(new POJO_Amenities("Lift", "https://upload.wikimedia.org/wikipedia/commons/thumb/e/eb/KRL_Icon_Pink.svg/1024px-KRL_Icon_Pink.svg.png", false));
        lstcategoryAmenities.add(new POJO_Amenities("Power Backup", "https://cdn.iconscout.com/icon/premium/png-512-thumb/power-generator-1717542-1461298.png", false));
        lstcategoryAmenities.add(new POJO_Amenities("Gas Pipeline", "https://cdn2.iconfinder.com/data/icons/construction-butterscotch-vol-2/512/Pipeline-512.png", false));
        lstcategoryAmenities.add(new POJO_Amenities("Park", "https://cdn4.iconfinder.com/data/icons/jetflat-2-buildings-vol-1/60/008_035_ferris_wheel_park_amusement_attractions-512.png", false));
        lstcategoryAmenities.add(new POJO_Amenities("Swimming Pool", "https://png.pngtree.com/png-vector/20190830/ourlarge/pngtree-swimming-pool-icon-design-vector-png-image_1708730.jpg", false));
        lstcategoryAmenities.add(new POJO_Amenities("Club House", "https://cdn0.iconfinder.com/data/icons/edm-red/64/HOUSE-club-party-musical-music-512.png", false));

        recyclerViewAdapterAmenities = new RecyclerviewAdapterAmenities(activity, lstcategoryAmenities, true);

        GridLayoutManager linearLayoutManagerAmenities = new GridLayoutManager(activity,3);
        linearLayoutManagerAmenities.setOrientation(LinearLayoutManager.HORIZONTAL);
        linearLayoutManagerAmenities.setStackFromEnd(false);
        recyclerViewAmenities.setLayoutManager(linearLayoutManagerAmenities);
        recyclerViewAmenities.setAdapter(recyclerViewAdapterAmenities);

//        moreFilterTv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                moreFilterTv.setVisibility(View.GONE);
//                moreFiltLinLay.setVisibility(View.VISIBLE);
//            }
//        });

        underConstruction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(underConStatus){
                    underConStatus = false;
                    underConstruction.setBackgroundTintList(ContextCompat.getColorStateList(activity, R.color.white));
                } else {
                    underConstruction.setBackgroundTintList(ContextCompat.getColorStateList(activity, R.color.colorSelected));
                    underConStatus = true;
                }
            }
        });

        readyToMove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(readyToMvStatus){
                    readyToMvStatus = false;
                    readyToMove.setBackgroundTintList(ContextCompat.getColorStateList(activity, R.color.white));
                } else {
                    readyToMove.setBackgroundTintList(ContextCompat.getColorStateList(activity, R.color.colorSelected));
                    readyToMvStatus = true;
                }
            }
        });

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

        final ArrayAdapter<String> spinnerArrayAdapterSociety = new ArrayAdapter<String>(activity, android.R.layout.simple_spinner_item, societyList);
        spinnerArrayAdapterSociety.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        societySpin.setAdapter(spinnerArrayAdapterSociety);

        return rootview;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof Activity){
            activity = (FragmentActivity) context;
        }
    }

}
