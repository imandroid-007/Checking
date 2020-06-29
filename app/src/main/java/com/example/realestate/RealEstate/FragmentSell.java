package com.example.realestate.RealEstate;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.developer.kalert.KAlertDialog;
import com.example.realestate.GridSpacingDecoration;
import com.example.realestate.Maps.MapsActivity;
import com.example.realestate.R;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.textfield.TextInputLayout;
import com.ramotion.fluidslider.FluidSlider;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentSell extends Fragment {


    public FragmentSell() {
        // Required empty public constructor
    }

    View rootview;
    FragmentActivity activity;
    Context mContext;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof Activity) {
            activity = (FragmentActivity) context;
        }
    }

    //private RecyclerviewPropTypes adapter;
    //private RecyclerView recyclerView;
    //private ArrayList<POJO_PropType> lstcategory;

    //    private Spinner bathroomSpn;
//    private ArrayList<String> bathroomList = new ArrayList<>();
//
//    private Spinner balconySpn;
//    private ArrayList<String> balconySpnList = new ArrayList<>();

    /* FragmentSell.getView().setFocusableInTouchMode(true);
    FragmentSell.getView().requestFocus();
    FragmentSell.getView().setOnKeyListener( new OnKeyListener()
    {
        @Override
        public boolean onKey( View v, int keyCode, KeyEvent event )
        {
            if( keyCode == KeyEvent.KEYCODE_BACK )
            {
                return true;
            }
            return false;
        }
    } );*/

    private Spinner minBudget, maxBudget;

    private ArrayList<String> minBudgetList = new ArrayList<>();
    private ArrayList<String> maxBudgetList = new ArrayList<>();

    private RecyclerviewAdapterBedrooms adapterBedrooms;
    private RecyclerView recyclerViewBedrooms;
    private ArrayList<POJO_bedrooms> lstcategoryBedrooms;

    private Button underConstruction, readyToMove;
    private boolean underConStatus = false, readyToMvStatus = false;

    private ArrayList<String> monthPossesList = new ArrayList<>();
    private ArrayList<String> yearPossesList = new ArrayList<>();
    private Spinner monthPosSpin, yearPosSPin;

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

    private Button btnPostProperty;

    private RecyclerView rvFacing;
    private RecyclerviewAdapterTextOpts facingAdapter;
    private ArrayList<Pojo_TextOpts> lstcategoryFacing;

    private CheckBox reraApprovedPropChkbx, notApplicableReraChkbx, negotiableChkBox, fixedChkBox; //reraApprovedAgentChkbx
    private RadioGroup youAreRadioGrp;

    private Button newBtn, resaleBtn;
    private boolean newPropFlag = false, resalePropFlag = false;
    private EditText owner_name, owner_mobile_no;

    private RecyclerView rvBathroom;
    private RecyclerviewAdapterTextOpts bathroomAdapter;
    private ArrayList<Pojo_TextOpts> lstcategoryBathroom;

    private RecyclerView rvBalcony;
    private RecyclerviewAdapterTextOpts balconyAdapter;
    private ArrayList<Pojo_TextOpts> lstcategoryBalcony;

    private String propTypeStr = null;

    private RelativeLayout bottomSheetRelativeLayout;
    BottomSheetBehavior sheetBehavior;

    private boolean stateStatus = false;
    private LinearLayout filterBtnLayout;
    private LinearLayout closeFiltersLin;

    private LinearLayout possesionInLayout;

    private FluidSlider fluidSlider;
    private Spinner expectedPriceSpin;
    private ArrayList<String> expectedPriceList = new ArrayList<>();

    private TextView bedroomsTitleTv, amenitiesTitleTv, floorTitleTv, bathroomTitleTv, balconyTitleTv, furnishingTitleTv;
    private TextView reraTitleTv, whatTypePropTitleTv, saleTypeTitleTv, societyTitleTv, propAreaTitleTv, maintainanceChrgsTitleTv;
    private TextView typeOfHouseVillaTitleTv, typeOfNAPlotTitleTv, commercPropDetailsTitleTv, facingTitleTv;

    private RadioGroup furnishingRdGrp, typeOfHouseVillaRdGrp, typeOfNAPLotRdGrp;
    private RelativeLayout floorSpinConainer, societySpinContainer;
    private LinearLayout maintainanceChrgsContainer, whatTypeOfPropContainer, saleTypeContainer, comPropDetailContainer;
    private LinearLayout receptionContainer;

    private EditText maintainanceChargesEdtx, propAreaEdtx, propTitleEdtx, propDescEdtx, reraRegistrationNumEdtx;
    private EditText propLocationEdtx, expectedPriceEdtx;

    private Spinner cabinSpin, conferenceSpin, receptionSpin, countryCodeSpin;

    private SeekBar seekBar;
    private TextView budgetValTv;//, pricePerSqftTv;

    private Button uploadPhotosBtn, updateBedroomBtn;

    private TextInputLayout maintainanceChrgsInput;

    private ArrayList<String> cabinSpinList = new ArrayList<>();
    private ArrayList<String> conferenceSpinList = new ArrayList<>();
    private ArrayList<String> receptionSpinList = new ArrayList<>();
    private ArrayList<String> countryCodeSpinList = new ArrayList<>();

    private Button verifyLocationBtn;

    private static String URL_APPROVE_PENDING_REQUEST = "http://192.168.1.6/forms/approvssssed_req_mast_android.php";

    private String latitudeStr = "", longitudeStr = "", geoAddresStr = "";

    private String priceIsStr = "", reraStatusStr = "", googleAddressStr = "";
    private EditText cityEdtx;

    private ScrollView scrollView;
    private boolean isMaintainaceChargesEdtxVisible = true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootview = inflater.inflate(R.layout.fragment_fragment_sell, container, false);

        Bundle bundle = getArguments();
        propTypeStr = bundle.getString("PROP_TYPE");

        //Toast.makeText(activity, "Prop Type = " + " " + propTypeStr, Toast.LENGTH_SHORT).show();
        initializeViews();

        setupViews();

        customizeViews();

        //        if(true) {
//        /*sheetBehavior = BottomSheetBehavior.from(bottomSheetRelativeLayout);
//
//        sheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
//
//
//        filterBtnLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                if(stateStatus){
//                    sheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
//                    stateStatus = false;
//                } else {
//                    sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
//                    stateStatus = true;
//                }
//
//            }
//        });
//
//        closeFiltersLin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                sheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
//                stateStatus = false;
//            }
//        });
//
//        sheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
//            @Override
//            public void onStateChanged(@NonNull View bottomSheet, int newState) {
//
//                switch (newState) {
//                    case BottomSheetBehavior.STATE_HIDDEN:
//
//                        break;
//
//                    case BottomSheetBehavior.STATE_EXPANDED:
//
//
//                    break;
//
//                    case BottomSheetBehavior.STATE_COLLAPSED:
//
//
//                    break;
//
//                    case BottomSheetBehavior.STATE_DRAGGING:
//                        sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
//                        break;
//
//                    case BottomSheetBehavior.STATE_SETTLING:
//
//                        break;
//                }
//            }
//
//            @Override
//            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
//
//            }
//        });*/
//        /* updateBedroomBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                for(int i = 0; i< lstcategoryBedrooms.size(); i++){
//                    Log.d("SELECTIONLOG", "isSelected(" + i + ") =" + " " + lstcategoryBedrooms.get(i).isSelected());
//                }
//
//            }
//        });*/
//        /*lstcategoryFacing.add(new Pojo_TextOpts("East", false));
//        lstcategoryFacing.add(new Pojo_TextOpts("North", false));
//        lstcategoryFacing.add(new Pojo_TextOpts("North-East", false));
//        lstcategoryFacing.add(new Pojo_TextOpts("North-West", false));
//        lstcategoryFacing.add(new Pojo_TextOpts("South", false));
//        lstcategoryFacing.add(new Pojo_TextOpts("South-East", false));
//        lstcategoryFacing.add(new Pojo_TextOpts("South-West", false));
//        lstcategoryFacing.add(new Pojo_TextOpts("West", false));
//
//        facingAdapter = new RecyclerviewAdapterTextOpts(activity, lstcategoryFacing, true);
//
//        LinearLayoutManager linearLayoutManagerFacing = new LinearLayoutManager(activity);
//        linearLayoutManagerFacing.setOrientation(LinearLayoutManager.HORIZONTAL);
//        linearLayoutManagerFacing.setStackFromEnd(false);
//        rvFacing.setLayoutManager(linearLayoutManagerFacing);
//        rvFacing.setAdapter(facingAdapter);*/
//        /*LinearLayoutManager linearLayoutManagerBathroom = new LinearLayoutManager(activity);
//        linearLayoutManagerBathroom.setOrientation(LinearLayoutManager.HORIZONTAL);
//        linearLayoutManagerBathroom.setStackFromEnd(false);
//        rvBathroom.setLayoutManager(linearLayoutManagerBathroom);*/
//        /*LinearLayoutManager linearLayoutManagerBalcony = new LinearLayoutManager(activity);
//        linearLayoutManagerBalcony.setOrientation(LinearLayoutManager.HORIZONTAL);
//        linearLayoutManagerBalcony.setStackFromEnd(false);
//        rvBalcony.setLayoutManager(linearLayoutManagerBalcony);*/
//        /*expectedPriceList.add("₹ 10 Lakh");
//        expectedPriceList.add("₹ 15 Lakh");
//        expectedPriceList.add("₹ 20 Lakh");
//        expectedPriceList.add("₹ 25 Lakh");
//        expectedPriceList.add("₹ 30 Lakh");
//        expectedPriceList.add("₹ 35 Lakh");
//        expectedPriceList.add("₹ 40 Lakh");
//        expectedPriceList.add("₹ 45 Lakh");
//        expectedPriceList.add("₹ 50 Lakh");
//        expectedPriceList.add("₹ 55 Lakh");
//        expectedPriceList.add("₹ 60 Lakh");
//        expectedPriceList.add("₹ 65 Lakh");
//        expectedPriceList.add("₹ 70 Lakh");
//        expectedPriceList.add("₹ 75 Lakh");
//        expectedPriceList.add("₹ 80 Lakh");
//        expectedPriceList.add("₹ 85 Lakh");
//        expectedPriceList.add("₹ 90 Lakh");
//        expectedPriceList.add("₹ 95 Lakh");
//        expectedPriceList.add("₹ 1 Cr");
//        expectedPriceList.add("₹ 1.05 Cr");
//        expectedPriceList.add("₹ 1.10 Cr");
//        expectedPriceList.add("₹ 1.15 Cr");
//        expectedPriceList.add("₹ 1.20 Cr");
//        expectedPriceList.add("₹ 1.25 Cr");
//        expectedPriceList.add("₹ 1.30 Cr");
//        expectedPriceList.add("₹ 1.35 Cr");
//        expectedPriceList.add("₹ 1.40 Cr");
//        expectedPriceList.add("₹ 1.45 Cr");
//        expectedPriceList.add("₹ 1.50 Cr");
//        expectedPriceList.add("₹ 1.55 Cr");
//        expectedPriceList.add("₹ 1.60 Cr");
//        expectedPriceList.add("₹ 1.65 Cr");
//        expectedPriceList.add("₹ 1.70 Cr");
//        expectedPriceList.add("₹ 1.75 Cr");
//        expectedPriceList.add("₹ 1.85 Cr");
//        expectedPriceList.add("₹ 1.90 Cr");
//        expectedPriceList.add("₹ 1.95 Cr");
//        expectedPriceList.add("₹ 2 Cr");
//
//        expectedPriceList.add("₹ 2.05 Cr");
//        expectedPriceList.add("₹ 2.10 Cr");
//        expectedPriceList.add("₹ 2.15 Cr");
//        expectedPriceList.add("₹ 2.20 Cr");
//        expectedPriceList.add("₹ 2.25 Cr");
//        expectedPriceList.add("₹ 2.30 Cr");
//        expectedPriceList.add("₹ 2.35 Cr");
//        expectedPriceList.add("₹ 2.40 Cr");
//        expectedPriceList.add("₹ 2.45 Cr");
//        expectedPriceList.add("₹ 2.50 Cr");
//        expectedPriceList.add("₹ 2.55 Cr");
//        expectedPriceList.add("₹ 2.60 Cr");
//        expectedPriceList.add("₹ 2.65 Cr");
//        expectedPriceList.add("₹ 2.70 Cr");
//        expectedPriceList.add("₹ 2.75 Cr");
//        expectedPriceList.add("₹ 2.85 Cr");
//        expectedPriceList.add("₹ 2.90 Cr");
//        expectedPriceList.add("₹ 2.95 Cr");
//        expectedPriceList.add("₹ 3 Cr");
//
//        expectedPriceList.add("₹ 3.05 Cr");
//        expectedPriceList.add("₹ 3.10 Cr");
//        expectedPriceList.add("₹ 3.15 Cr");
//        expectedPriceList.add("₹ 3.20 Cr");
//        expectedPriceList.add("₹ 3.25 Cr");
//        expectedPriceList.add("₹ 3.30 Cr");
//        expectedPriceList.add("₹ 3.35 Cr");
//        expectedPriceList.add("₹ 3.40 Cr");
//        expectedPriceList.add("₹ 3.45 Cr");
//        expectedPriceList.add("₹ 3.50 Cr");
//        expectedPriceList.add("₹ 3.55 Cr");
//        expectedPriceList.add("₹ 3.60 Cr");
//        expectedPriceList.add("₹ 3.65 Cr");
//        expectedPriceList.add("₹ 3.70 Cr");
//        expectedPriceList.add("₹ 3.75 Cr");
//        expectedPriceList.add("₹ 3.85 Cr");
//        expectedPriceList.add("₹ 3.90 Cr");
//        expectedPriceList.add("₹ 3.95 Cr");
//        expectedPriceList.add("₹ 4 Cr");
//
//        final ArrayAdapter<String> spinnerArrayAdapterExpPrice = new ArrayAdapter<String>(activity, android.R.layout.simple_spinner_item, expectedPriceList);
//        spinnerArrayAdapterExpPrice.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        expectedPriceSpin.setAdapter(spinnerArrayAdapterExpPrice);*/
//        /*  private TextView bedroomsTitleTv, amenitiesTitleTv, floorTitleTv, bathroomTitleTv, balconyTitleTv, furnishingTitleTv;
//        private TextView reraTitleTv, whatTypePropTitleTv, saleTypeTitleTv, societyTitleTv;*/
//        }

        return rootview;
    }

    private void customizeViews() {

        if (propTypeStr != null) {

            if (propTypeStr.matches("Flat") || propTypeStr.matches("House/Villa")) {

                lstcategoryAmenities.add(new POJO_Amenities("Parking", "https://img.icons8.com/plasticine/2x/car.png", false));
                lstcategoryAmenities.add(new POJO_Amenities("Lift", "https://upload.wikimedia.org/wikipedia/commons/thumb/e/eb/KRL_Icon_Pink.svg/1024px-KRL_Icon_Pink.svg.png", false));
                lstcategoryAmenities.add(new POJO_Amenities("Power Backup", "https://cdn.iconscout.com/icon/premium/png-512-thumb/power-generator-1717542-1461298.png", false));
                lstcategoryAmenities.add(new POJO_Amenities("Gas Pipeline", "https://cdn2.iconfinder.com/data/icons/construction-butterscotch-vol-2/512/Pipeline-512.png", false));
                lstcategoryAmenities.add(new POJO_Amenities("Park", "https://cdn4.iconfinder.com/data/icons/jetflat-2-buildings-vol-1/60/008_035_ferris_wheel_park_amusement_attractions-512.png", false));
                lstcategoryAmenities.add(new POJO_Amenities("Swimming Pool", "https://png.pngtree.com/png-vector/20190830/ourlarge/pngtree-swimming-pool-icon-design-vector-png-image_1708730.jpg", false));
                lstcategoryAmenities.add(new POJO_Amenities("Club House", "https://cdn0.iconfinder.com/data/icons/edm-red/64/HOUSE-club-party-musical-music-512.png", false));

                lstcategoryAmenities.add(new POJO_Amenities("Gym", "https://www.pngrepo.com/download/200170/dumbbell-gym.png", false));
                lstcategoryAmenities.add(new POJO_Amenities("Temple", "https://i.dlpng.com/static/png/5411504-hindu-temple-png-download-transparent-hindu-temple-png-images-hindu-temple-png-820_625_preview.png", false));
                lstcategoryAmenities.add(new POJO_Amenities("Security", "https://cdn3.iconfinder.com/data/icons/human-resources-3-1/128/113-512.png", false));
                lstcategoryAmenities.add(new POJO_Amenities("Street Lights", "https://cdn3.iconfinder.com/data/icons/real-estate-flat-icons-vol-2/256/82-512.png", false));


                recyclerViewAdapterAmenities = new RecyclerviewAdapterAmenities(activity, lstcategoryAmenities, true);

                int spanCount = 3; // 3 columns
                int spacing = 27; // 50px
                boolean includeEdge = true;
                recyclerViewAmenities.addItemDecoration(new GridSpacingDecoration(spanCount, spacing, includeEdge));
                GridLayoutManager gridLayoutManagerAmenities = new GridLayoutManager(activity, 3);
               /*linearLayoutManagerAmenities.setOrientation(LinearLayoutManager.HORIZONTAL);
               linearLayoutManagerAmenities.setStackFromEnd(false);*/
                recyclerViewAmenities.setLayoutManager(gridLayoutManagerAmenities);
                recyclerViewAmenities.setAdapter(recyclerViewAdapterAmenities);

                //propTitleEdtx.setVisibility(Gon);

                propAreaEdtx.setHint("Property Area");

                if (propTypeStr.matches("Flat")) {

                    propAreaTitleTv.setText("Flat Area");
                    propAreaEdtx.setHint("Flat Area");
                    propTitleEdtx.setHint("Flat Title");
                    propDescEdtx.setHint("Flat Description");

                    floorLevelList.add("Select Floor");
                    floorLevelList.add("Ground Floor");
                    floorLevelList.add("First Floor");
                    floorLevelList.add("Second Floor");
                    floorLevelList.add("Third Floor");
                    floorLevelList.add("Fourth Floor");
                    floorLevelList.add("Fifth Floor");
                    floorLevelList.add("Sixth Floor");
                    floorLevelList.add("Seventh Floor");
                    floorLevelList.add("Eighth Floor");
                    floorLevelList.add("Ninth Floor");
                    floorLevelList.add("Tenth Floor");

                    final ArrayAdapter<String> spinnerArrayAdapterFloor = new ArrayAdapter<String>(activity, android.R.layout.simple_spinner_item, floorLevelList);
                    spinnerArrayAdapterFloor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    floorLevelSpin.setAdapter(spinnerArrayAdapterFloor);

                    lstcategoryBedrooms.add(new POJO_bedrooms("1 BHK", false));
                    lstcategoryBedrooms.add(new POJO_bedrooms("2 BHK", false));
                    lstcategoryBedrooms.add(new POJO_bedrooms("3 BHK", false));
                    lstcategoryBedrooms.add(new POJO_bedrooms("4 BHK", false));
                    lstcategoryBedrooms.add(new POJO_bedrooms(">4 BHK", false));

                    adapterBedrooms = new RecyclerviewAdapterBedrooms(activity, lstcategoryBedrooms, true);

                    /*LinearLayoutManager linearLayoutManagerBdrm = new LinearLayoutManager(activity);
                      linearLayoutManagerBdrm.setOrientation(LinearLayoutManager.HORIZONTAL);
                      linearLayoutManagerBdrm.setStackFromEnd(false);
                     recyclerViewBedrooms.setLayoutManager(linearLayoutManagerBdrm);*/

                    int spanCountBedr = 4; // 3 columns
                    int spacingBedr = 25; // 50px
                    boolean includeEdgeBedr = true;
                    recyclerViewBedrooms.addItemDecoration(new GridSpacingDecoration(spanCountBedr, spacingBedr, includeEdgeBedr));
                    GridLayoutManager gridLayoutManagerBedrooms = new GridLayoutManager(activity, 4);
                    recyclerViewBedrooms.setLayoutManager(gridLayoutManagerBedrooms);
                    recyclerViewBedrooms.setAdapter(adapterBedrooms);
                    //recyclerViewBedrooms.getItemAnimator().setChangeDuration(100);
                    recyclerViewBedrooms.setItemAnimator(null);

                    propAreaSpnList.add("Select Area Type");
                    propAreaSpnList.add("Sqft");
                    propAreaSpnList.add("Sqmtr");

                    final ArrayAdapter<String> spinAdapterPropArea = new ArrayAdapter<String>(activity, android.R.layout.simple_spinner_item, propAreaSpnList);
                    spinAdapterPropArea.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    propAreaSpn.setAdapter(spinAdapterPropArea);

                }

                if (propTypeStr.matches("House/Villa")) {

                    societyTitleTv.setVisibility(View.GONE);
                    societySpinContainer.setVisibility(View.GONE);

                    propAreaTitleTv.setText("House/Villa Area");
                    propAreaEdtx.setHint("House/Villa Area");
                    propTitleEdtx.setHint("House/Villa Title");
                    propDescEdtx.setHint("House/Villa Description");

                    typeOfHouseVillaTitleTv.setVisibility(View.VISIBLE);
                    typeOfHouseVillaRdGrp.setVisibility(View.VISIBLE);

                    //floorTitleTv.setVisibility(View.GONE);
                    // floorSpinConainer.setVisibility(View.GONE);

                    floorLevelList.add("Select Floor");
                    floorLevelList.add("Single Floor");
                    floorLevelList.add("Two Floors");
                    floorLevelList.add("Three Floors");
                    floorLevelList.add("Four Floors");
                    floorLevelList.add("Five Floors");
                    floorLevelList.add("Six Floors");
                    floorLevelList.add("Seven Floors");
                    floorLevelList.add("Eight Floors");
                    floorLevelList.add("Nine Floors");
                    floorLevelList.add("Ten Floors");

                    final ArrayAdapter<String> spinnerArrayAdapterFloor = new ArrayAdapter<String>(activity, android.R.layout.simple_spinner_item, floorLevelList);
                    spinnerArrayAdapterFloor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    floorLevelSpin.setAdapter(spinnerArrayAdapterFloor);

                    lstcategoryBedrooms.add(new POJO_bedrooms("1 Room", false));
                    lstcategoryBedrooms.add(new POJO_bedrooms("2 Rooms", false));
                    lstcategoryBedrooms.add(new POJO_bedrooms("3 Rooms", false));
                    lstcategoryBedrooms.add(new POJO_bedrooms("4 Rooms", false));
                    lstcategoryBedrooms.add(new POJO_bedrooms("5 Rooms", false));

                    lstcategoryBedrooms.add(new POJO_bedrooms("6 Room", false));
                    lstcategoryBedrooms.add(new POJO_bedrooms("7 Rooms", false));
                    lstcategoryBedrooms.add(new POJO_bedrooms("8 Rooms", false));
                    lstcategoryBedrooms.add(new POJO_bedrooms("9 Rooms", false));
                    lstcategoryBedrooms.add(new POJO_bedrooms("10 Rooms", false));
                    lstcategoryBedrooms.add(new POJO_bedrooms("10+ Rooms", false));

                    adapterBedrooms = new RecyclerviewAdapterBedrooms(activity, lstcategoryBedrooms, true);
                    int spanCountBedr = 4; // 3 columns
                    int spacingBedr = 25; // 50px
                    boolean includeEdgeBedr = true;
                    recyclerViewBedrooms.addItemDecoration(new GridSpacingDecoration(spanCountBedr, spacingBedr, includeEdgeBedr));
                    GridLayoutManager gridLayoutManagerBedrooms = new GridLayoutManager(activity, 4);
                    recyclerViewBedrooms.setLayoutManager(gridLayoutManagerBedrooms);
                    recyclerViewBedrooms.setAdapter(adapterBedrooms);
                    //recyclerViewBedrooms.getItemAnimator().setChangeDuration(100);
                    recyclerViewBedrooms.setItemAnimator(null);

                    bedroomsTitleTv.setText("Rooms");

                    propAreaSpnList.add("Select Area Type");
                    propAreaSpnList.add("Guntha");
                    propAreaSpnList.add("Sqft");
                    propAreaSpnList.add("Sqmtr");

                    final ArrayAdapter<String> spinAdapterPropArea = new ArrayAdapter<String>(activity, android.R.layout.simple_spinner_item, propAreaSpnList);
                    spinAdapterPropArea.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    propAreaSpn.setAdapter(spinAdapterPropArea);

                    //floorTitleTv.setText("");

                }


            } else if (propTypeStr.matches("NA Plot") || propTypeStr.matches("Agricultural Land")) {

                bedroomsTitleTv.setVisibility(View.GONE);
                recyclerViewBedrooms.setVisibility(View.GONE);

                //amenitiesTitleTv.setVisibility(View.GONE);
                // recyclerViewAmenities.setVisibility(View.GONE);

                floorTitleTv.setVisibility(View.GONE);

                floorLevelSpin.setVisibility(View.GONE);
                floorSpinConainer.setVisibility(View.GONE);

                bathroomTitleTv.setVisibility(View.GONE);
                rvBathroom.setVisibility(View.GONE);
                balconyTitleTv.setVisibility(View.GONE);
                rvBalcony.setVisibility(View.GONE);
                furnishingTitleTv.setVisibility(View.GONE);
                furnishingRdGrp.setVisibility(View.GONE);
                //reraTitleTv.setVisibility(View.GONE);
                //reraApprovedPropChkbx.setVisibility(View.GONE);
                //notApplicableReraChkbx.setVisibility(View.GONE);
                whatTypePropTitleTv.setVisibility(View.GONE);

                whatTypeOfPropContainer.setVisibility(View.GONE);
                /*underConstruction.setVisibility(View.GONE);
                readyToMove.setVisibility(View.GONE);*/

                saleTypeTitleTv.setVisibility(View.GONE);
                saleTypeContainer.setVisibility(View.GONE);
                /*newBtn.setVisibility(View.GONE);
                resaleBtn.setVisibility(View.GONE);*/

                societyTitleTv.setVisibility(View.GONE);

                societySpin.setVisibility(View.GONE);
                societySpinContainer.setVisibility(View.GONE);

                maintainanceChrgsTitleTv.setVisibility(View.GONE);
                maintainanceChrgsInput.setVisibility(View.GONE);
                maintainanceChrgsContainer.setVisibility(View.GONE);
                maintainanceChargesEdtx.setVisibility(View.GONE);

                if (propTypeStr.matches("NA Plot")) {

                    propAreaTitleTv.setText("Plot Area");
                    propAreaEdtx.setHint("Plot Area");
                    propTitleEdtx.setHint("Plot Title");
                    propDescEdtx.setHint("Plot Description");

                    typeOfNAPlotTitleTv.setVisibility(View.VISIBLE);
                    typeOfNAPLotRdGrp.setVisibility(View.VISIBLE);

                    amenitiesTitleTv.setText("Facilities");
                    lstcategoryAmenities.add(new POJO_Amenities("Water Available", "https://upload.wikimedia.org/wikipedia/commons/thumb/1/1c/Circle-icons-water.svg/1024px-Circle-icons-water.svg.png", false));
                    lstcategoryAmenities.add(new POJO_Amenities("Electricity Available", "https://cdn5.vectorstock.com/i/1000x1000/96/24/lamp-flat-icon-on-white-background-vector-22699624.jpg", false));
                    lstcategoryAmenities.add(new POJO_Amenities("Title Clear", "https://cdn.pixabay.com/photo/2017/09/27/21/05/license-icon-2793454_960_720.png", false));
                    lstcategoryAmenities.add(new POJO_Amenities("Road Touch", "https://www.pngrepo.com/download/80761/road.png", false));

                    lstcategoryAmenities.add(new POJO_Amenities("Gym", "https://www.pngrepo.com/download/200170/dumbbell-gym.png", false));
                    lstcategoryAmenities.add(new POJO_Amenities("Temple", "https://i.dlpng.com/static/png/5411504-hindu-temple-png-download-transparent-hindu-temple-png-images-hindu-temple-png-820_625_preview.png", false));
                    lstcategoryAmenities.add(new POJO_Amenities("Security", "https://cdn3.iconfinder.com/data/icons/human-resources-3-1/128/113-512.png", false));
                    lstcategoryAmenities.add(new POJO_Amenities("Street Lights", "https://cdn3.iconfinder.com/data/icons/real-estate-flat-icons-vol-2/256/82-512.png", false));

                    lstcategoryAmenities.add(new POJO_Amenities("Compound", "https://pngwebicons.com/uploads/brick-wall/512/brick-wall_icon8993.png", false));

                    recyclerViewAdapterAmenities = new RecyclerviewAdapterAmenities(activity, lstcategoryAmenities, true);
                    int spanCount = 3;
                    int spacing = 27;
                    boolean includeEdge = true;
                    recyclerViewAmenities.addItemDecoration(new GridSpacingDecoration(spanCount, spacing, includeEdge));
                    GridLayoutManager gridLayoutManagerAmenities = new GridLayoutManager(activity, 3);
                    recyclerViewAmenities.setLayoutManager(gridLayoutManagerAmenities);
                    recyclerViewAmenities.setAdapter(recyclerViewAdapterAmenities);

                    propAreaSpnList.add("Select Area Type");
                    propAreaSpnList.add("Guntha");
                    propAreaSpnList.add("Sqft");
                    propAreaSpnList.add("Sqmtr");

                    final ArrayAdapter<String> spinAdapterPropArea = new ArrayAdapter<String>(activity, android.R.layout.simple_spinner_item, propAreaSpnList);
                    spinAdapterPropArea.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    propAreaSpn.setAdapter(spinAdapterPropArea);

                }

                if (propTypeStr.matches("Agricultural Land")) {

                    propAreaTitleTv.setText("Land Area");
                    propAreaEdtx.setHint("Land Area");
                    propTitleEdtx.setHint("Land Title");
                    propDescEdtx.setHint("Land Description");

                    reraTitleTv.setVisibility(View.GONE);
                    reraApprovedPropChkbx.setVisibility(View.GONE);
                    notApplicableReraChkbx.setVisibility(View.GONE);

                    amenitiesTitleTv.setText("Facilities");
                    lstcategoryAmenities.add(new POJO_Amenities("Water Available", "https://upload.wikimedia.org/wikipedia/commons/thumb/1/1c/Circle-icons-water.svg/1024px-Circle-icons-water.svg.png", false));
                    lstcategoryAmenities.add(new POJO_Amenities("Electricity Available", "https://cdn5.vectorstock.com/i/1000x1000/96/24/lamp-flat-icon-on-white-background-vector-22699624.jpg", false));
                    lstcategoryAmenities.add(new POJO_Amenities("Road Touch", "https://www.pngrepo.com/download/80761/road.png", false));
                    lstcategoryAmenities.add(new POJO_Amenities("Farm House", "https://www.shareicon.net/data/512x512/2015/08/20/87922_home_512x512.png", false));
                    lstcategoryAmenities.add(new POJO_Amenities("Title Clear", "https://cdn.pixabay.com/photo/2017/09/27/21/05/license-icon-2793454_960_720.png", false));

                    lstcategoryAmenities.add(new POJO_Amenities("Temple", "https://i.dlpng.com/static/png/5411504-hindu-temple-png-download-transparent-hindu-temple-png-images-hindu-temple-png-820_625_preview.png", false));
                    lstcategoryAmenities.add(new POJO_Amenities("Street Lights", "https://cdn3.iconfinder.com/data/icons/real-estate-flat-icons-vol-2/256/82-512.png", false));

                    recyclerViewAdapterAmenities = new RecyclerviewAdapterAmenities(activity, lstcategoryAmenities, true);
                    int spanCount = 3;
                    int spacing = 27;
                    boolean includeEdge = true;
                    recyclerViewAmenities.addItemDecoration(new GridSpacingDecoration(spanCount, spacing, includeEdge));
                    GridLayoutManager gridLayoutManagerAmenities = new GridLayoutManager(activity, 3);
                    recyclerViewAmenities.setLayoutManager(gridLayoutManagerAmenities);
                    recyclerViewAmenities.setAdapter(recyclerViewAdapterAmenities);

                    propAreaSpnList.add("Select Area Type");
                    propAreaSpnList.add("Acre");
                    propAreaSpnList.add("Hectare");
                    propAreaSpnList.add("Guntha");
                    propAreaSpnList.add("Sqft");
                    propAreaSpnList.add("Sqmtr");

                    final ArrayAdapter<String> spinAdapterPropArea = new ArrayAdapter<String>(activity, android.R.layout.simple_spinner_item, propAreaSpnList);
                    spinAdapterPropArea.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    propAreaSpn.setAdapter(spinAdapterPropArea);

                }

            } else if (propTypeStr.matches("Office Space") || propTypeStr.matches("Shop/Showroom") || propTypeStr.matches("Other Commercial")) {

                lstcategoryAmenities.add(new POJO_Amenities("Parking", "https://img.icons8.com/plasticine/2x/car.png", false));
                lstcategoryAmenities.add(new POJO_Amenities("Lift", "https://upload.wikimedia.org/wikipedia/commons/thumb/e/eb/KRL_Icon_Pink.svg/1024px-KRL_Icon_Pink.svg.png", false));
                lstcategoryAmenities.add(new POJO_Amenities("Power Backup", "https://cdn.iconscout.com/icon/premium/png-512-thumb/power-generator-1717542-1461298.png", false));
                lstcategoryAmenities.add(new POJO_Amenities("Gas Pipeline", "https://cdn2.iconfinder.com/data/icons/construction-butterscotch-vol-2/512/Pipeline-512.png", false));
                lstcategoryAmenities.add(new POJO_Amenities("Park", "https://cdn4.iconfinder.com/data/icons/jetflat-2-buildings-vol-1/60/008_035_ferris_wheel_park_amusement_attractions-512.png", false));
                lstcategoryAmenities.add(new POJO_Amenities("Swimming Pool", "https://png.pngtree.com/png-vector/20190830/ourlarge/pngtree-swimming-pool-icon-design-vector-png-image_1708730.jpg", false));
                lstcategoryAmenities.add(new POJO_Amenities("Club House", "https://cdn0.iconfinder.com/data/icons/edm-red/64/HOUSE-club-party-musical-music-512.png", false));

                lstcategoryAmenities.add(new POJO_Amenities("Gym", "https://www.pngrepo.com/download/200170/dumbbell-gym.png", false));
                lstcategoryAmenities.add(new POJO_Amenities("Temple", "https://i.dlpng.com/static/png/5411504-hindu-temple-png-download-transparent-hindu-temple-png-images-hindu-temple-png-820_625_preview.png", false));
                lstcategoryAmenities.add(new POJO_Amenities("Security", "https://cdn3.iconfinder.com/data/icons/human-resources-3-1/128/113-512.png", false));
                lstcategoryAmenities.add(new POJO_Amenities("Street Lights", "https://cdn3.iconfinder.com/data/icons/real-estate-flat-icons-vol-2/256/82-512.png", false));


                recyclerViewAdapterAmenities = new RecyclerviewAdapterAmenities(activity, lstcategoryAmenities, true);

                int spanCount = 3; // 3 columns
                int spacing = 27; // 50px
                boolean includeEdge = true;
                recyclerViewAmenities.addItemDecoration(new GridSpacingDecoration(spanCount, spacing, includeEdge));
                GridLayoutManager gridLayoutManagerAmenities = new GridLayoutManager(activity, 3);
               /*linearLayoutManagerAmenities.setOrientation(LinearLayoutManager.HORIZONTAL);
               linearLayoutManagerAmenities.setStackFromEnd(false);*/
                recyclerViewAmenities.setLayoutManager(gridLayoutManagerAmenities);
                recyclerViewAmenities.setAdapter(recyclerViewAdapterAmenities);

                bedroomsTitleTv.setVisibility(View.GONE);
                recyclerViewBedrooms.setVisibility(View.GONE);
                societySpin.setVisibility(View.GONE);
                societySpinContainer.setVisibility(View.GONE);
                propAreaEdtx.setHint("Property Area");

                propTitleEdtx.setHint("Property Title");
                propDescEdtx.setHint("Property Description");

                commercPropDetailsTitleTv.setVisibility(View.VISIBLE);
                comPropDetailContainer.setVisibility(View.VISIBLE);
                receptionContainer.setVisibility(View.VISIBLE);

                societyTitleTv.setVisibility(View.GONE);

                if (propTypeStr.matches("Office Space")) {

                    propAreaEdtx.setHint("Office Area");
                    propTitleEdtx.setHint("Office Property Title");
                    propDescEdtx.setHint("Office Property Description");
                    propAreaTitleTv.setText("Office Area");

                    commercPropDetailsTitleTv.setText("Office Details");

                } else if (propTypeStr.matches("Shop/Showroom")) {

                    propAreaEdtx.setHint("Shop/Showroom Area");
                    propTitleEdtx.setHint("Shop/Showroom Property Title");
                    propDescEdtx.setHint("Shop/Showroom Property Description");
                    propAreaTitleTv.setText("Shop/Showroom Area");

                    commercPropDetailsTitleTv.setText("Shop/Showroom Details");

                    rvFacing.setVisibility(View.VISIBLE);
                    facingTitleTv.setVisibility(View.VISIBLE);

                    lstcategoryFacing.add(new Pojo_TextOpts("East", false));
                    lstcategoryFacing.add(new Pojo_TextOpts("North", false));
                    lstcategoryFacing.add(new Pojo_TextOpts("North-East", false));
                    lstcategoryFacing.add(new Pojo_TextOpts("North-West", false));
                    lstcategoryFacing.add(new Pojo_TextOpts("South", false));
                    lstcategoryFacing.add(new Pojo_TextOpts("South-East", false));
                    lstcategoryFacing.add(new Pojo_TextOpts("South-West", false));
                    lstcategoryFacing.add(new Pojo_TextOpts("West", false));

                    facingAdapter = new RecyclerviewAdapterTextOpts(activity, lstcategoryFacing, true);

                    int spanCountFace = 4; // 3 columns
                    int spacingFace = 25; // 50px
                    boolean includeEdgeFace = true;
                    rvFacing.addItemDecoration(new GridSpacingDecoration(spanCountFace, spacingFace, includeEdgeFace));
                    GridLayoutManager gridLayoutManagerFacing = new GridLayoutManager(activity, 4);
                    rvFacing.setLayoutManager(gridLayoutManagerFacing);
                    rvFacing.setAdapter(facingAdapter);
                    rvFacing.setItemAnimator(null);

                } else if (propTypeStr.matches("Other Commercial")) {

                    propAreaEdtx.setHint("Commercial Property Area");
                    propTitleEdtx.setHint("Commercial Property Title");
                    propDescEdtx.setHint("Commercial Property Description");
                    propAreaTitleTv.setText("Commercial Property Area");

                    commercPropDetailsTitleTv.setText("Other Commercial Property Details");

                }

                floorLevelList.add("Select Floor");
                floorLevelList.add("Ground Floor");
                floorLevelList.add("First Floor");
                floorLevelList.add("Second Floor");
                floorLevelList.add("Third Floor");
                floorLevelList.add("Fourth Floor");
                floorLevelList.add("Fifth Floor");
                floorLevelList.add("Sixth Floor");
                floorLevelList.add("Seventh Floor");
                floorLevelList.add("Eighth Floor");
                floorLevelList.add("Ninth Floor");
                floorLevelList.add("Tenth Floor");

                final ArrayAdapter<String> spinnerArrayAdapterFloor = new ArrayAdapter<String>(activity, android.R.layout.simple_spinner_item, floorLevelList);
                spinnerArrayAdapterFloor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                floorLevelSpin.setAdapter(spinnerArrayAdapterFloor);

                propAreaSpnList.add("Select Area Type");
                propAreaSpnList.add("Guntha");
                propAreaSpnList.add("Sqft");
                propAreaSpnList.add("Sqmtr");

                final ArrayAdapter<String> spinAdapterPropArea = new ArrayAdapter<String>(activity, android.R.layout.simple_spinner_item, propAreaSpnList);
                spinAdapterPropArea.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                propAreaSpn.setAdapter(spinAdapterPropArea);
            }

        }
    }

    private void setupViews() {

        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {

                youAreRadioGrp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        switch (checkedId) {

                            case R.id.ownerRdBtn:
                                //  reraApprovedAgentChkbx.setVisibility(View.GONE);
                                break;

                            case R.id.agentRdBtn:
                                //   reraApprovedAgentChkbx.setVisibility(View.VISIBLE);
                                break;

                            case R.id.builderRdBtn:
                                //  reraApprovedAgentChkbx.setVisibility(View.GONE);
                                break;
                        }
                    }
                });

                reraApprovedPropChkbx.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                            notApplicableReraChkbx.setEnabled(false);
                            notApplicableReraChkbx.setAlpha(0.7f);
                            reraRegistrationNumEdtx.setVisibility(View.VISIBLE);
                            reraStatusStr = "RERA Approved";
                        } else {
                            notApplicableReraChkbx.setEnabled(true);
                            notApplicableReraChkbx.setAlpha(1);
                            reraRegistrationNumEdtx.setVisibility(View.GONE);
                            reraStatusStr = "";
                        }
                    }
                });

                notApplicableReraChkbx.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                            reraApprovedPropChkbx.setEnabled(false);
                            reraApprovedPropChkbx.setAlpha(0.7f);
                            reraStatusStr = "Not Applicable";
                        } else {
                            reraApprovedPropChkbx.setEnabled(true);
                            reraApprovedPropChkbx.setAlpha(1);
                            reraStatusStr = "";
                        }
                    }
                });

                /*propLocationEdtx.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        final int DRAWABLE_LEFT = 0;
                        final int DRAWABLE_TOP = 1;
                        final int DRAWABLE_RIGHT = 2;
                        final int DRAWABLE_BOTTOM = 3;

                        if(event.getAction() == MotionEvent.ACTION_UP) {
                            if(event.getRawX() >= (propLocationEdtx.getRight() - propLocationEdtx.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                                // your action here

                                String address = propLocationEdtx.getText().toString();
                                if(!address.matches("")){
                                    Intent intent = new Intent(activity, MapsActivity.class);
                                    intent.putExtra("ADDRESS", propLocationEdtx.getText().toString());
                                    startActivityForResult(intent, 101);
                                } else {
                                    propLocationEdtx.setError("Enter Address");
                                }



                                //Toast.makeText(activity, "Hey, Map is gonna start here", Toast.LENGTH_SHORT).show();

                                return true;
                            }
                        }
                        return false;
                    }
                });*/

                verifyLocationBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String city = cityEdtx.getText().toString().trim();
                        String propLocationS = propLocationEdtx.getText().toString().trim();

                        if (propLocationS.matches("")) {
                            propLocationEdtx.setError("Enter Property Address !");
                        } else if (city.matches("")) {
                            cityEdtx.setError("Enter City !");
                        } else {
                            Intent intent = new Intent(activity, MapsActivity.class);
                            intent.putExtra("ADDRESS", propLocationEdtx.getText().toString().trim());
                            startActivityForResult(intent, 101);
                        }

                    }
                });

                uploadPhotosBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        /*Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(pickPhoto , 1);*/

                        Intent intent = new Intent();
                        intent.setType("image/*");
                        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);

                    }
                });

                //seekBar.setMin(10);
                //seekBar.setMax(400);
                seekBar.setProgress(40);
                budgetValTv.setText("40 Lakh");

                seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress,
                                                  boolean fromUser) {

                        if (progress > 99) {
                            budgetValTv.setText(String.valueOf(progress).charAt(0) + "." + String.valueOf(progress).substring(1, String.valueOf(progress).length() - 1) + " " + "Crore");
                        } else {
                            budgetValTv.setText(String.valueOf(progress) + " " + "Lakh");
                        }

                        expectedPriceEdtx.setText(String.valueOf(progress * 100000));

                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
                        //  Toast.makeText(getApplicationContext(),"seekbar touch started!", Toast.LENGTH_SHORT).show();
                        // budget_value.setText(""+progress);
                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        //   Toast.makeText(getApplicationContext(),"seekbar touch stopped!", Toast.LENGTH_SHORT).show();
                        //budget_value.setText(""+progress);
                    }
                });

                expectedPriceEdtx.setText("4000000");

                expectedPriceEdtx.addTextChangedListener(new TextWatcher() {

                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                    }

                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    }

                    public void afterTextChanged(Editable s) {

                        //int progress = seekBar.getProgress();

                        if (!String.valueOf(s).matches("")) {

                            String valStr = String.valueOf(s);
                            int valInt = Integer.parseInt(valStr);

                            if (valInt <= 40000000) {

                                seekBar.setProgress(valInt / 100000);

                                Log.d("SEEKBAREDITTEXTVALLOG", "Value :" + " " + valInt / 100000);

                                expectedPriceEdtx.setSelection(expectedPriceEdtx.getText().toString().trim().length());

                            } else {

                                activity.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        expectedPriceEdtx.setText("40000000");
                                        Toasty.error(activity, "Cannot add more than 4 Crore", Toasty.LENGTH_SHORT).show();
                                    }
                                });

                            }
                        }
                    }
                });

                negotiableChkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                            fixedChkBox.setEnabled(false);
                            fixedChkBox.setAlpha(0.7f);
                            priceIsStr = "Negotiable";
                        } else {
                            fixedChkBox.setEnabled(true);
                            fixedChkBox.setAlpha(1);
                            priceIsStr = "";
                        }
                    }
                });

                fixedChkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                            negotiableChkBox.setEnabled(false);
                            negotiableChkBox.setAlpha(0.7f);
                            priceIsStr = "Fixed";
                        } else {
                            negotiableChkBox.setEnabled(true);
                            negotiableChkBox.setAlpha(1);
                            priceIsStr = "";
                        }
                    }
                });

                /* lstcategory.add(new POJO_PropType("FLat", "https://icon-library.net/images/apartment-icon/apartment-icon-5.jpg", false, "RESIDENTIAL"));
                lstcategory.add(new POJO_PropType("House/Villa", "https://img.icons8.com/plasticine/2x/home.png", false, "RESIDENTIAL"));
                lstcategory.add(new POJO_PropType("Plot", "https://cdn3.iconfinder.com/data/icons/real-estate-flat-icons-vol-2/256/68-512.png", false, "LAND"));
                lstcategory.add(new POJO_PropType("Agricultural Land", "https://webstockreview.net/images/industry-clipart-agro-based-industry-11.png", false, "AGRICULTURAL-LAND"));
                lstcategory.add(new POJO_PropType("Office Space", "https://c7.uihere.com/files/169/771/802/building-icon-flat-building.jpg", false, "COMMERCIAL"));
                lstcategory.add(new POJO_PropType("Shop/Showroom", "https://png.pngtree.com/png-vector/20190227/ourmid/pngtree-vector-storage-warehouse-icon-png-image_707463.jpg", false, "COMMERCIAL"));
                lstcategory.add(new POJO_PropType("Other Commercial", "https://cdn.iconscout.com/icon/premium/png-256-thumb/building-1256-486704.png", false, "COMMERCIAL"));

                adapter = new RecyclerviewPropTypes(activity, lstcategory, true);

                GridLayoutManager linearLayoutManager = new GridLayoutManager(activity,3);
                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                linearLayoutManager.setStackFromEnd(false);
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setAdapter(adapter);*/

                //                minBudgetList.add("₹ Min");
//                minBudgetList.add("₹ 5 Lac");
//                minBudgetList.add("₹ 10 Lac");
//                minBudgetList.add("₹ 20 Lac");
//                minBudgetList.add("₹ 30 Lac");
//                minBudgetList.add("₹ 40 Lac");
//                minBudgetList.add("₹ 50 Lac");
//                minBudgetList.add("₹ 60 Lac");
//                minBudgetList.add("₹ 70 Lac");
//                minBudgetList.add("₹ 80 Lac");
//                minBudgetList.add("₹ 90 Lac");
//                minBudgetList.add("₹ 1 Cr");
//                minBudgetList.add("₹ 1.2 Cr");
//                minBudgetList.add("₹ 1.6 Cr");
//                minBudgetList.add("₹ 1.8 Cr");
//                minBudgetList.add("₹ 2 Cr");
//                minBudgetList.add("₹ 2.3 Cr");
//                minBudgetList.add("₹ 2.6 Cr");
//                minBudgetList.add("₹ 3 Cr");
//                minBudgetList.add("₹ 3.5 Cr");
//                minBudgetList.add("₹ 4 Cr");
//                minBudgetList.add("₹ 4.5 Cr");
//                minBudgetList.add("₹ 5 Cr");
//                minBudgetList.add("₹ 10 Cr");
//                minBudgetList.add("₹ 15 Cr");
//
//                maxBudgetList.add("₹ Max");
//                maxBudgetList.add("₹ 5 Lac");
//                maxBudgetList.add("₹ 10 Lac");
//                maxBudgetList.add("₹ 20 Lac");
//                maxBudgetList.add("₹ 30 Lac");
//                maxBudgetList.add("₹ 40 Lac");
//                maxBudgetList.add("₹ 50 Lac");
//                maxBudgetList.add("₹ 60 Lac");
//                maxBudgetList.add("₹ 70 Lac");
//                maxBudgetList.add("₹ 80 Lac");
//                maxBudgetList.add("₹ 90 Lac");
//                maxBudgetList.add("₹ 1 Cr");
//                maxBudgetList.add("₹ 1.2 Cr");
//                maxBudgetList.add("₹ 1.6 Cr");
//                maxBudgetList.add("₹ 1.8 Cr");
//                maxBudgetList.add("₹ 2 Cr");
//                maxBudgetList.add("₹ 2.3 Cr");
//                maxBudgetList.add("₹ 2.6 Cr");
//                maxBudgetList.add("₹ 3 Cr");
//                maxBudgetList.add("₹ 3.5 Cr");
//                maxBudgetList.add("₹ 4 Cr");
//                maxBudgetList.add("₹ 4.5 Cr");
//                maxBudgetList.add("₹ 5 Cr");
//                maxBudgetList.add("₹ 10 Cr");
//                maxBudgetList.add("₹ 15 Cr");
//
//                final ArrayAdapter<String> spinnerArrayAdapterMin = new ArrayAdapter<String>(activity, android.R.layout.simple_spinner_item, minBudgetList);
//                spinnerArrayAdapterMin.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                minBudget.setAdapter(spinnerArrayAdapterMin);
//
//                final ArrayAdapter<String> spinnerArrayAdapterMax = new ArrayAdapter<String>(activity, android.R.layout.simple_spinner_item, maxBudgetList);
//                spinnerArrayAdapterMax.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                maxBudget.setAdapter(spinnerArrayAdapterMax);

                underConstruction.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (underConStatus) {
                            underConStatus = false;
                            underConstruction.setBackgroundTintList(ContextCompat.getColorStateList(activity, R.color.white));
                            possesionInLayout.setVisibility(View.GONE);
                        } else {
                            if (readyToMvStatus) {
                                readyToMvStatus = false;
                                readyToMove.setBackgroundTintList(ContextCompat.getColorStateList(activity, R.color.white));
                            }
                            underConstruction.setBackgroundTintList(ContextCompat.getColorStateList(activity, R.color.colorSelected));
                            underConStatus = true;
                            possesionInLayout.setVisibility(View.VISIBLE);
                        }
                    }
                });

                readyToMove.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (readyToMvStatus) {
                            readyToMvStatus = false;
                            readyToMove.setBackgroundTintList(ContextCompat.getColorStateList(activity, R.color.white));
                        } else {
                            if (underConStatus) {
                                underConStatus = false;
                                underConstruction.setBackgroundTintList(ContextCompat.getColorStateList(activity, R.color.white));
                            }
                            readyToMove.setBackgroundTintList(ContextCompat.getColorStateList(activity, R.color.colorSelected));
                            readyToMvStatus = true;
                            possesionInLayout.setVisibility(View.GONE);
                        }
                    }
                });

                cabinSpinList.add("Select Cabins");
                cabinSpinList.add("0");
                cabinSpinList.add("1");
                cabinSpinList.add("2");
                cabinSpinList.add("3");
                cabinSpinList.add("4");
                cabinSpinList.add("5");
                cabinSpinList.add("6");
                cabinSpinList.add("7");
                cabinSpinList.add("8");
                cabinSpinList.add("9");
                cabinSpinList.add("10");
                cabinSpinList.add("11");
                cabinSpinList.add("12");
                cabinSpinList.add("13");

                final ArrayAdapter<String> spinnerArrayAdapterCabin = new ArrayAdapter<String>(activity, android.R.layout.simple_spinner_item, cabinSpinList);
                spinnerArrayAdapterCabin.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                cabinSpin.setAdapter(spinnerArrayAdapterCabin);

                conferenceSpinList.add("Select Conference Rooms");
                conferenceSpinList.add("0");
                conferenceSpinList.add("1");
                conferenceSpinList.add("2");
                conferenceSpinList.add("3");

                final ArrayAdapter<String> spinnerArrayAdapterConfRooms = new ArrayAdapter<String>(activity, android.R.layout.simple_spinner_item, conferenceSpinList);
                spinnerArrayAdapterConfRooms.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                conferenceSpin.setAdapter(spinnerArrayAdapterConfRooms);

                receptionSpinList.add("Select Receptions");
                receptionSpinList.add("0");
                receptionSpinList.add("1");
                receptionSpinList.add("2");
                receptionSpinList.add("3");
                receptionSpinList.add("4");
                receptionSpinList.add("5");

                final ArrayAdapter<String> spinnerArrayAdapterReception = new ArrayAdapter<String>(activity, android.R.layout.simple_spinner_item, receptionSpinList);
                spinnerArrayAdapterReception.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                receptionSpin.setAdapter(spinnerArrayAdapterReception);

                monthPossesList.add("Select Month");
                monthPossesList.add("January");
                monthPossesList.add("February");
                monthPossesList.add("March");
                monthPossesList.add("April");
                monthPossesList.add("May");
                monthPossesList.add("June");
                monthPossesList.add("July");
                monthPossesList.add("August");
                monthPossesList.add("September");
                monthPossesList.add("October");
                monthPossesList.add("November");
                monthPossesList.add("December");

                //        final ArrayAdapter<String> spinnerArrayAdapterfrmYrSpn = new ArrayAdapter<String>(activity, android.R.layout.simple_spinner_item, frmYearPossesList);
//        spinnerArrayAdapterMin.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        frmYearPosSpin.setAdapter(spinnerArrayAdapterfrmYrSpn);

                final ArrayAdapter<String> spinnerArrayAdapterfrmYrSpn = new ArrayAdapter<String>(activity, android.R.layout.simple_spinner_item, monthPossesList);
                spinnerArrayAdapterfrmYrSpn.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                monthPosSpin.setAdapter(spinnerArrayAdapterfrmYrSpn);

                yearPossesList.add("Select Year");
                yearPossesList.add("2020");
                yearPossesList.add("2021");
                yearPossesList.add("2022");
                yearPossesList.add("2023");
                yearPossesList.add("2024");
                yearPossesList.add("2025");
                yearPossesList.add("2026");
                yearPossesList.add("2027");
                yearPossesList.add("2028");

                final ArrayAdapter<String> spinnerArrayAdapterToYrSpn = new ArrayAdapter<String>(activity, android.R.layout.simple_spinner_item, yearPossesList);
                spinnerArrayAdapterToYrSpn.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                yearPosSPin.setAdapter(spinnerArrayAdapterToYrSpn);

                //                moreFilterTv.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        moreFilterTv.setVisibility(View.GONE);
//                        moreFiltLinLay.setVisibility(View.VISIBLE);
//                    }
//                });

                /* lstcategoryAmenities.add(new POJO_Amenities("Parking", "https://img.icons8.com/plasticine/2x/car.png", false));
                lstcategoryAmenities.add(new POJO_Amenities("Lift", "https://upload.wikimedia.org/wikipedia/commons/thumb/e/eb/KRL_Icon_Pink.svg/1024px-KRL_Icon_Pink.svg.png", false));
                lstcategoryAmenities.add(new POJO_Amenities("Power Backup", "https://cdn.iconscout.com/icon/premium/png-512-thumb/power-generator-1717542-1461298.png", false));
                lstcategoryAmenities.add(new POJO_Amenities("Gas Pipeline", "https://cdn2.iconfinder.com/data/icons/construction-butterscotch-vol-2/512/Pipeline-512.png", false));
                lstcategoryAmenities.add(new POJO_Amenities("Park", "https://cdn4.iconfinder.com/data/icons/jetflat-2-buildings-vol-1/60/008_035_ferris_wheel_park_amusement_attractions-512.png", false));
                lstcategoryAmenities.add(new POJO_Amenities("Swimming Pool", "https://png.pngtree.com/png-vector/20190830/ourlarge/pngtree-swimming-pool-icon-design-vector-png-image_1708730.jpg", false));
                lstcategoryAmenities.add(new POJO_Amenities("Club House", "https://cdn0.iconfinder.com/data/icons/edm-red/64/HOUSE-club-party-musical-music-512.png", false));


                recyclerViewAdapterAmenities = new RecyclerviewAdapterAmenities(activity, lstcategoryAmenities, true);

                int spanCount = 3; // 3 columns
                int spacing = 27; // 50px
                boolean includeEdge = true;
                recyclerViewAmenities.addItemDecoration(new GridSpacingDecoration(spanCount, spacing, includeEdge));
                GridLayoutManager gridLayoutManagerAmenities = new GridLayoutManager(activity, 3);
                */
                /*linearLayoutManagerAmenities.setOrientation(LinearLayoutManager.HORIZONTAL);
                linearLayoutManagerAmenities.setStackFromEnd(false);*//*
                recyclerViewAmenities.setLayoutManager(gridLayoutManagerAmenities);
                recyclerViewAmenities.setAdapter(recyclerViewAdapterAmenities);*/

                societyList.add("Select Society");
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

                maintanceChrgsList.add("Select Charges");
                maintanceChrgsList.add("Monthly");
                maintanceChrgsList.add("Yearly");
                maintanceChrgsList.add("NA");

                final ArrayAdapter<String> spinAdapterMaintainance = new ArrayAdapter<String>(activity, android.R.layout.simple_spinner_item, maintanceChrgsList);
                spinAdapterMaintainance.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                maintainanceChrgesSpn.setAdapter(spinAdapterMaintainance);

                maintainanceChrgesSpn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        if (position == 3) {
                            maintainanceChrgsInput.setVisibility(View.GONE);
                            isMaintainaceChargesEdtxVisible = false;
                        } else {
                            maintainanceChrgsInput.setVisibility(View.VISIBLE);
                            isMaintainaceChargesEdtxVisible = true;
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

                /*propAreaSpnList.add("sqft");
                propAreaSpnList.add("sq-yrd");
                propAreaSpnList.add("sq-m");*/

                /*propAreaSpnList.add("Acre");
                propAreaSpnList.add("Hectare");
                propAreaSpnList.add("Guntha");
                propAreaSpnList.add("Sqft");
                propAreaSpnList.add("Sqmtr");

                final ArrayAdapter<String> spinAdapterPropArea = new ArrayAdapter<String>(activity, android.R.layout.simple_spinner_item, propAreaSpnList);
                spinAdapterPropArea.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                propAreaSpn.setAdapter(spinAdapterPropArea);*/

                //                bathroomList.add("1");
//                bathroomList.add("2");
//                bathroomList.add("3");
//                bathroomList.add("4");
//                bathroomList.add("4+");
//
//                final ArrayAdapter<String> spinAdapterPropbathroom = new ArrayAdapter<String>(activity, android.R.layout.simple_spinner_item, bathroomList);
//                spinAdapterPropbathroom.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                bathroomSpn.setAdapter(spinAdapterPropbathroom);
//
//                balconySpnList.add("1");
//                balconySpnList.add("2");
//                balconySpnList.add("3");
//                balconySpnList.add("4");
//                balconySpnList.add("4+");
//
//                final ArrayAdapter<String> spinAdapterPropBalcony = new ArrayAdapter<String>(activity, android.R.layout.simple_spinner_item, balconySpnList);
//                spinAdapterPropBalcony.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                balconySpn.setAdapter(spinAdapterPropBalcony);

                btnPostProperty.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (propTypeStr != null) {

                            postProperty(propTypeStr);
                        }

                    }
                });

                newBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (newPropFlag) {
                            newPropFlag = false;
                            newBtn.setBackgroundTintList(ContextCompat.getColorStateList(activity, R.color.white));

                        } else {

                            if (resalePropFlag) {
                                resalePropFlag = false;
                                resaleBtn.setBackgroundTintList(ContextCompat.getColorStateList(activity, R.color.white));
                            }
                            newPropFlag = true;
                            newBtn.setBackgroundTintList(ContextCompat.getColorStateList(activity, R.color.colorSelected));
                        }
                    }
                });

                resaleBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (resalePropFlag) {
                            resalePropFlag = false;
                            resaleBtn.setBackgroundTintList(ContextCompat.getColorStateList(activity, R.color.white));

                        } else {

                            if (newPropFlag) {
                                newPropFlag = false;
                                newBtn.setBackgroundTintList(ContextCompat.getColorStateList(activity, R.color.white));
                            }
                            resalePropFlag = true;
                            resaleBtn.setBackgroundTintList(ContextCompat.getColorStateList(activity, R.color.colorSelected));
                        }
                    }
                });

                lstcategoryBathroom.add(new Pojo_TextOpts("1", false));
                lstcategoryBathroom.add(new Pojo_TextOpts("2", false));
                lstcategoryBathroom.add(new Pojo_TextOpts("3", false));
                lstcategoryBathroom.add(new Pojo_TextOpts("4", false));
                lstcategoryBathroom.add(new Pojo_TextOpts("4+", false));

                bathroomAdapter = new RecyclerviewAdapterTextOpts(activity, lstcategoryBathroom, true);

                int spanCountBath = 4; // 3 columns
                int spacingBath = 25; // 50px
                boolean includeEdgeBath = true;
                rvBathroom.addItemDecoration(new GridSpacingDecoration(spanCountBath, spacingBath, includeEdgeBath));
                GridLayoutManager gridLayoutManagerBathroom = new GridLayoutManager(activity, 4);
                rvBathroom.setLayoutManager(gridLayoutManagerBathroom);
                rvBathroom.setAdapter(bathroomAdapter);
                rvBathroom.setItemAnimator(null);


                lstcategoryBalcony.add(new Pojo_TextOpts("0", false));
                lstcategoryBalcony.add(new Pojo_TextOpts("1", false));
                lstcategoryBalcony.add(new Pojo_TextOpts("2", false));
                lstcategoryBalcony.add(new Pojo_TextOpts("3", false));
                lstcategoryBalcony.add(new Pojo_TextOpts("4", false));
                lstcategoryBalcony.add(new Pojo_TextOpts("4+", false));

                balconyAdapter = new RecyclerviewAdapterTextOpts(activity, lstcategoryBalcony, true);

                int spanCountBalc = 4; // 3 columns
                int spacingBalc = 25; // 50px
                boolean includeEdgeBathBalc = true;
                rvBalcony.addItemDecoration(new GridSpacingDecoration(spanCountBalc, spacingBalc, includeEdgeBathBalc));
                GridLayoutManager gridLayoutManagerBalcony = new GridLayoutManager(activity, 4);
                rvBalcony.setLayoutManager(gridLayoutManagerBalcony);
                rvBalcony.setAdapter(balconyAdapter);
                rvBalcony.setItemAnimator(null);

                countryCodeSpinList.add("+91");
                countryCodeSpinList.add("+1");
                countryCodeSpinList.add("+971");
                countryCodeSpinList.add("+966");
                countryCodeSpinList.add("+65");
                countryCodeSpinList.add("+974");
                countryCodeSpinList.add("+61");
                countryCodeSpinList.add("+506");
                countryCodeSpinList.add("+354");
                countryCodeSpinList.add("+972");
                countryCodeSpinList.add("+81");
                countryCodeSpinList.add("+60");
                countryCodeSpinList.add("+52");
                countryCodeSpinList.add("+7");

                final ArrayAdapter<String> spinnerAdapterCountryCode = new ArrayAdapter<String>(activity, android.R.layout.simple_spinner_item, countryCodeSpinList);
                spinnerAdapterCountryCode.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                countryCodeSpin.setAdapter(spinnerAdapterCountryCode);

            }
        });
    }

    private void initializeViews() {

        //recyclerView = (RecyclerView) rootview.findViewById(R.id.recyc_propType);
        minBudget = (Spinner) rootview.findViewById(R.id.minBudgt_Spin);
        maxBudget = (Spinner) rootview.findViewById(R.id.maxBudget_Spin);

        recyclerViewBedrooms = (RecyclerView) rootview.findViewById(R.id.recyc_bedrooms);
        underConstruction = (Button) rootview.findViewById(R.id.btnUnderConstr);
        readyToMove = (Button) rootview.findViewById(R.id.btnReadyToMove);
        owner_name = (EditText) rootview.findViewById(R.id.owner_name);
        owner_mobile_no = (EditText) rootview.findViewById(R.id.owner_mobile_no);

        monthPosSpin = (Spinner) rootview.findViewById(R.id.fromYearPossesSpin);
        yearPosSPin = (Spinner) rootview.findViewById(R.id.toYearPossesSPin);

        moreFiltLinLay = (LinearLayout) rootview.findViewById(R.id.moreFiltersLinLay);
        //moreFilterTv = (TextView) rootview.findViewById(R.id.moreFilters_Tv);

        societySpin = (Spinner) rootview.findViewById(R.id.societySpinner);
        propAreaSpn = (Spinner) rootview.findViewById(R.id.propAreaspn);
        countryCodeSpin = (Spinner) rootview.findViewById(R.id.countryCodeSpin);

        recyclerViewAmenities = (RecyclerView) rootview.findViewById(R.id.recyc_amenities);

        floorLevelSpin = (Spinner) rootview.findViewById(R.id.floorNoSpin);
        maintainanceChrgesSpn = (Spinner) rootview.findViewById(R.id.mntncChrgsTypeSpn);
        //bathroomSpn = (Spinner) rootview.findViewById(R.id.bathroomSpinner);
        //balconySpn = (Spinner) rootview.findViewById(R.id.balconySpinner);

        btnPostProperty = (Button) rootview.findViewById(R.id.btnPostProp);

        rvFacing = (RecyclerView) rootview.findViewById(R.id.recyc_facing);
        rvBathroom = (RecyclerView) rootview.findViewById(R.id.recyc_bathroom);
        rvBalcony = (RecyclerView) rootview.findViewById(R.id.recyc_balcony);

        reraApprovedPropChkbx = (CheckBox) rootview.findViewById(R.id.reraAprvdPropChkbx);
        //reraApprovedAgentChkbx = (CheckBox) rootview.findViewById(R.id.reraAprvdAgentChkbx);
        notApplicableReraChkbx = (CheckBox) rootview.findViewById(R.id.notApplicableReraChkbx);
        youAreRadioGrp = (RadioGroup) rootview.findViewById(R.id.youAreRdGrp);

        newBtn = (Button) rootview.findViewById(R.id.btnSaleNew);
        resaleBtn = (Button) rootview.findViewById(R.id.btnSaleResale);

        possesionInLayout = (LinearLayout) rootview.findViewById(R.id.possesionInLayout);
        //expectedPriceSpin = (Spinner) rootview.findViewById(R.id.expectedPriceSPinner);

        bedroomsTitleTv = (TextView) rootview.findViewById(R.id.bedroomTitleTV);
        amenitiesTitleTv = (TextView) rootview.findViewById(R.id.amenitiesTitleTv);
        floorTitleTv = (TextView) rootview.findViewById(R.id.floorDetailsTitleTv);
        bathroomTitleTv = (TextView) rootview.findViewById(R.id.bathroomTitleTv);
        balconyTitleTv = (TextView) rootview.findViewById(R.id.balconyTitleTv);
        furnishingTitleTv = (TextView) rootview.findViewById(R.id.furnishingTitleTv);
        reraTitleTv = (TextView) rootview.findViewById(R.id.reraApprovedTitleTv);
        whatTypePropTitleTv = (TextView) rootview.findViewById(R.id.whatTypePropTitleTv);
        saleTypeTitleTv = (TextView) rootview.findViewById(R.id.saleTypeTitileTv);
        societyTitleTv = (TextView) rootview.findViewById(R.id.societyTitleTv);
        propAreaTitleTv = (TextView) rootview.findViewById(R.id.propAreaTitleTv);
        maintainanceChrgsTitleTv = (TextView) rootview.findViewById(R.id.maintainanceChrgsTitleTv);
        typeOfHouseVillaTitleTv = (TextView) rootview.findViewById(R.id.typeOfHoseVillaTitleTv);
        typeOfNAPlotTitleTv = (TextView) rootview.findViewById(R.id.typeOfNAPlotTitleTv);
        commercPropDetailsTitleTv = (TextView) rootview.findViewById(R.id.propDetailsTitleTv);
        facingTitleTv = (TextView) rootview.findViewById(R.id.facingTitleTv);

        //pricePerSqftTv = (TextView) rootview.findViewById(R.id.pricePerSqft);

        furnishingRdGrp = (RadioGroup) rootview.findViewById(R.id.furnishingRdGrp);
        typeOfHouseVillaRdGrp = (RadioGroup) rootview.findViewById(R.id.typeOfHouseVillaRdGrp);
        typeOfNAPLotRdGrp = (RadioGroup) rootview.findViewById(R.id.typeOfNAPlotRdGrp);

        floorSpinConainer = (RelativeLayout) rootview.findViewById(R.id.floorSpinContainer);
        societySpinContainer = (RelativeLayout) rootview.findViewById(R.id.societySpinContainer);
        maintainanceChrgsContainer = (LinearLayout) rootview.findViewById(R.id.maintainanceChrgsContainer);
        whatTypeOfPropContainer = (LinearLayout) rootview.findViewById(R.id.whatTypeOfPropContainer);
        saleTypeContainer = (LinearLayout) rootview.findViewById(R.id.saleTypeContainer);
        comPropDetailContainer = (LinearLayout) rootview.findViewById(R.id.propDetailsSpinContainer);
        receptionContainer = (LinearLayout) rootview.findViewById(R.id.receptionContainer);

        maintainanceChargesEdtx = (EditText) rootview.findViewById(R.id.mntnanceChrgsEdtx);
        propAreaEdtx = (EditText) rootview.findViewById(R.id.propAreaEdtx);
        propTitleEdtx = (EditText) rootview.findViewById(R.id.propTitleEdtx);
        propDescEdtx = (EditText) rootview.findViewById(R.id.propDescrEdtx);
        reraRegistrationNumEdtx = (EditText) rootview.findViewById(R.id.reraRegistrNumEdtx);
        propLocationEdtx = (EditText) rootview.findViewById(R.id.propLocationEdtx);
        expectedPriceEdtx = (EditText) rootview.findViewById(R.id.expectedPriceEdtx);
        cityEdtx = (EditText) rootview.findViewById(R.id.cityEdtx);

        negotiableChkBox = (CheckBox) rootview.findViewById(R.id.negotiableChkbox);
        fixedChkBox = (CheckBox) rootview.findViewById(R.id.fixedChkBox);

        seekBar = (SeekBar) rootview.findViewById(R.id.seekBar);
        budgetValTv = (TextView) rootview.findViewById(R.id.budgetValTv);

        uploadPhotosBtn = (Button) rootview.findViewById(R.id.uploadFileBtn);
        verifyLocationBtn = (Button) rootview.findViewById(R.id.verifyLocationButton);
        maintainanceChrgsInput = (TextInputLayout) rootview.findViewById(R.id.maintncChrgsTxtInput);

        cabinSpin = (Spinner) rootview.findViewById(R.id.cabinSpin);
        conferenceSpin = (Spinner) rootview.findViewById(R.id.confRoomSpin);
        receptionSpin = (Spinner) rootview.findViewById(R.id.receptionSpin);

        scrollView = (ScrollView) rootview.findViewById(R.id.scrollView);

        //updateBedroomBtn = (Button) rootview.findViewById(R.id.updateBedroomButtonz);

        //fluidSlider = (FluidSlider) rootview.findViewById(R.id.fluidSlider);

        /* bottomSheetRelativeLayout = (RelativeLayout) rootview.findViewById(R.id.sell_bottomsheet);
        filterBtnLayout = (LinearLayout) rootview.findViewById(R.id.filterBtn);

        closeFiltersLin = (LinearLayout) rootview.findViewById(R.id.closeFiltersLin);*/

        //lstcategory = new ArrayList<>();
        lstcategoryBedrooms = new ArrayList<>();
        lstcategoryAmenities = new ArrayList<>();
        lstcategoryFacing = new ArrayList<>();
        lstcategoryBathroom = new ArrayList<>();
        lstcategoryBalcony = new ArrayList<>();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        Log.d("MAPRESULTLOG", "ACTIVITY'S RESULT");

        super.onActivityResult(requestCode, resultCode, data);

        //String latitude = "", longitude = "", geoAddress = "";

        if (requestCode == 101) {

            if (resultCode == Activity.RESULT_OK) {

                latitudeStr = data.getStringExtra("LATITUDE");
                longitudeStr = data.getStringExtra("LONGITUDE");
                geoAddresStr = data.getStringExtra("GEO_ADDRESS");
            }

        }

        if (!geoAddresStr.matches("") && !geoAddresStr.matches(".")) {
            //propLocationEdtx.setText(geoAddresStr);
            googleAddressStr = geoAddresStr;
        }

        if (!latitudeStr.matches("") && !latitudeStr.matches("null") && !latitudeStr.matches(".") && !longitudeStr.matches("") && !longitudeStr.matches("null") && !longitudeStr.matches(".")) {
            verifyLocationBtn.setBackground(ContextCompat.getDrawable(activity, R.drawable.buttonshape_green));
            verifyLocationBtn.setText("Confirmed");
            verifyLocationBtn.setError(null);
            propLocationEdtx.setEnabled(false);
            propLocationEdtx.setAlpha(0.8f);
            cityEdtx.setEnabled(false);
            cityEdtx.setAlpha(0.8f);
        }

        Log.d("MAPRESULTLOG", "Latitude =" + " " + latitudeStr + "\nLongitude =" + " " + longitudeStr + "\nGeo Address =" + geoAddresStr);

    }


    //    private void postFlat() {
//
//
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_APPROVE_PENDING_REQUEST,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//
//                        Log.d("EXCEPTION", "Response : " + " " + response);
//
//                        try {
//
////                            JSONArray ja = new JSONArray(response);
////                            JSONObject jo = null;
//
//                            JSONObject jsonObject = new JSONObject(response);
//
//                            String message = null;
//
//                            /*for (int i = 0; i < ja.length(); i++) {
//                                jo = ja.getJSONObject(i);
//
//                                message = jo.getString("Message");
//                            }*/
//
//                            message = jsonObject.getString("Message");
//
//
//                            if (message.matches("1")) {
//
//                                Intent intent = new Intent(activity, UploadDocumentsActivity.class);
//                                startActivity(intent);
//
//                            /*    new KAlertDialog(activity, KAlertDialog.SUCCESS_TYPE)
//                                        .setTitleText("Success!")
//                                        .setContentText("Your property has been sent for verification, it will reflect in our app as soon as verification process is completed.")
//                                        .setConfirmClickListener(new KAlertDialog.KAlertClickListener() {
//                                            @Override
//                                            public void onClick(KAlertDialog kAlertDialog) {
//                                                kAlertDialog.dismiss();
//
//                                            }
//                                        })
//                                        .confirmButtonColor(R.drawable.kalert_button_background)
//                                        .show();*/
//
//                            } else if (message.matches("0")) {
//
//                                new KAlertDialog(mContext, KAlertDialog.ERROR_TYPE)
//                                        .setTitleText("Error!")
//                                        .setContentText("Can't post property, Please try again !")
//                                        .setConfirmClickListener(new KAlertDialog.KAlertClickListener() {
//                                            @Override
//                                            public void onClick(KAlertDialog kAlertDialog) {
//                                                kAlertDialog.dismiss();
//                                            }
//                                        })
//                                        .confirmButtonColor(R.drawable.kalert_button_background)
//                                        .show();
//
//                            }
//
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                            //Toast.makeText(mContext, "JSON Exception : " + " " + e.toString(), Toast.LENGTH_SHORT).show();
//                            Log.d("EXCEPTION", "JSON Exception : " + " " + e.toString());
//
//                            new KAlertDialog(mContext, KAlertDialog.ERROR_TYPE)
//                                    .setTitleText("Error!")
//                                    .setContentText("Ops, Something went wrong!")
//                                    .setConfirmClickListener(new KAlertDialog.KAlertClickListener() {
//                                        @Override
//                                        public void onClick(KAlertDialog kAlertDialog) {
//                                            kAlertDialog.dismiss();
//                                        }
//                                    })
//                                    .confirmButtonColor(R.drawable.kalert_button_background)
//                                    .show();
//
//
//                        }
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//
//                        //Toast.makeText(mContext, "Volley Error : " + " " + error.toString(), Toast.LENGTH_SHORT).show();
//                        Log.d("EXCEPTION", "Volley Error : " + " " + error.toString());
//
//                        new KAlertDialog(mContext, KAlertDialog.ERROR_TYPE)
//                                .setTitleText("Error!")
//                                .setContentText("Can't communicate with server, Please try again!")
//                                .setConfirmClickListener(new KAlertDialog.KAlertClickListener() {
//                                    @Override
//                                    public void onClick(KAlertDialog kAlertDialog) {
//                                        kAlertDialog.dismiss();
//                                    }
//                                })
//                                .confirmButtonColor(R.drawable.kalert_button_background)
//                                .show();
//
//
//                    }
//                }) {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> params = new HashMap<>();
//
//                params.put("operation", "ggggg");
//
//                return params;
//            }
//        };
//        RequestQueue requestQueue = Volley.newRequestQueue(mContext);
//        requestQueue.add(stringRequest);
//    }

    //  ---------- "postProperty()" method description --------
    // In this postProperty() method all the validations and submitting properties of all the types for sale is done.
    // Yeah.. this method seems way too much complicated but as there was no option left for me cause we're running out of time that's why
    // i have to do it like that, all the validations and submitting work has done properly and well tested. The code is messy but works perfectly.
    // Please be patient and once go through this...
    private void postProperty(String propertyType) {

        // ------- Universal Common Properties of Sell Property ---------

        String location = propLocationEdtx.getText().toString().trim();
        String city = cityEdtx.getText().toString().trim();

        String areaStr = "";
        areaStr = propAreaEdtx.getText().toString().trim();

        String areaMeasurementType = propAreaSpn.getSelectedItem().toString().trim();

        String propTitle = propTitleEdtx.getText().toString().trim();
        String propDescription = propDescEdtx.getText().toString().trim();

        String amenitiesStr = "";
        JSONArray amenitiesJSONArray = new JSONArray();

        for (int i = 0; i < lstcategoryAmenities.size(); i++) {

            try {
                JSONObject jo = new JSONObject();

                jo.put("Amenities" + " " + i, lstcategoryAmenities.get(i).getAmenitiesType());

                if (lstcategoryAmenities.get(i).isSelected()) {
                    amenitiesJSONArray.put(jo);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        if (amenitiesJSONArray.length() > 0) {
            amenitiesStr = amenitiesJSONArray.toString().trim();
        }

        String postAsStr = "";

        int postAsId = youAreRadioGrp.getCheckedRadioButtonId();

        switch (postAsId) {

            case R.id.ownerRdBtn:
                postAsStr = "Owner";
                break;

            case R.id.agentRdBtn:
                postAsStr = "Agent";
                break;

            case R.id.builderRdBtn:
                postAsStr = "Builder/Developer";
                break;
        }

        String ownerName = owner_name.getText().toString().trim();

        String mobileNum = owner_mobile_no.getText().toString().trim();

        String countryCode = countryCodeSpin.getSelectedItem().toString().trim();

        String expectedPrice = expectedPriceEdtx.getText().toString().trim();

        if (location.matches("")) {

            propLocationEdtx.requestFocus();
            propLocationEdtx.setError("Enter Location");
            scrollView.scrollTo(0, propLocationEdtx.getTop());

        } else if (city.matches("")) {

            propAreaEdtx.requestFocus();
            cityEdtx.setError("Enter City");
            scrollView.scrollTo(0, cityEdtx.getBottom());

        }
        /*else if (googleAddressStr.matches("")) {

            verifyLocationBtn.setError("Verify Location");
            scrollView.scrollTo(0, verifyLocationBtn.getTop());
            displayWarning("Verify Location");

        } */
        else if (latitudeStr.matches("")) {

            verifyLocationBtn.setError("Verify Location");
            scrollView.scrollTo(0, verifyLocationBtn.getTop());
            displayWarning("Verify Location");
        } else if (longitudeStr.matches("")) {

            verifyLocationBtn.setError("Verify Location");
            scrollView.scrollTo(0, verifyLocationBtn.getTop());
            displayWarning("Verify Location");

        } else if (areaStr.matches("")) {

            propAreaEdtx.requestFocus();
            propAreaEdtx.setError("Enter Area");
            scrollView.scrollTo(0, propAreaEdtx.getTop());

        } else if (areaMeasurementType.matches("Select Area Type")) {

            propAreaTitleTv.requestFocus();
            scrollView.scrollTo(0, propAreaTitleTv.getBottom());
            displayWarning("Select Area Type");

        } else if (propTitle.matches("")) {

            propTitleEdtx.requestFocus();
            propTitleEdtx.setError("Enter Title");
            scrollView.scrollTo(0, propTitleEdtx.getTop());

        } else if (propDescription.matches("")) {

            propDescEdtx.requestFocus();
            propDescEdtx.setError("Enter Description");
            scrollView.scrollTo(0, propDescEdtx.getTop());

        } else if (ownerName.matches("")) {

            owner_name.requestFocus();
            owner_name.setError("Enter Owner Name");
            scrollView.scrollTo(0, owner_name.getBottom());

        } else if (mobileNum.matches("")) {

            owner_mobile_no.requestFocus();
            owner_mobile_no.setError("Enter Owner Mobile Number");
            scrollView.scrollTo(0, owner_mobile_no.getBottom());

        } else if (expectedPrice.matches("0")) {

            expectedPriceEdtx.requestFocus();
            expectedPriceEdtx.setError("Enter Or Select Expected Price");
            scrollView.scrollTo(0, expectedPriceEdtx.getBottom());

        } else if (expectedPrice.matches("")) {

            expectedPriceEdtx.requestFocus();
            expectedPriceEdtx.setError("Enter Or Select Expected Price");
            scrollView.scrollTo(0, expectedPriceEdtx.getBottom());

        } else if (priceIsStr.matches("")) {

            negotiableChkBox.requestFocus();
            negotiableChkBox.getParent().requestChildFocus(negotiableChkBox, negotiableChkBox);
            displayWarning("Select Price is Negotiable or Fixed");

        } else if (amenitiesJSONArray.length() == 0) {

            recyclerViewAmenities.requestFocus();
            scrollView.scrollTo(0, recyclerViewAmenities.getTop());
            displayWarning("Select Amenities");
            //Toasty.error(activity, "Select Amenities", Toasty.LENGTH_LONG).show();

        } else {

            // ----*----*----*----*----*----*----*----*----*----*----*----*----*----*----*----*----*----*----*----*----*----*----
            // ----*----*----*----*----*----*----*---- Category Wise Properties ----*----*----*----*----*----*----*----*---*---*----

            if (propertyType.matches("Flat")) {

                String bedroomStr = "";

                for (int i = 0; i < lstcategoryBedrooms.size(); i++) {

                    if (lstcategoryBedrooms.get(i).isSelected()) {
                        bedroomStr = lstcategoryBedrooms.get(i).getBedroom();
                    }
                }

                String floor = floorLevelSpin.getSelectedItem().toString().trim();

                String bathroomStr = "";

                for (int i = 0; i < lstcategoryBathroom.size(); i++) {

                    if (lstcategoryBathroom.get(i).isSelected()) {
                        bathroomStr = lstcategoryBathroom.get(i).getTitle();
                    }
                }

                String balconyStr = "";

                for (int i = 0; i < lstcategoryBalcony.size(); i++) {

                    if (lstcategoryBalcony.get(i).isSelected()) {
                        balconyStr = lstcategoryBalcony.get(i).getTitle();
                    }
                }

                String furnishingStatusStr = "";

                int furnishingId = furnishingRdGrp.getCheckedRadioButtonId();

                switch (furnishingId) {

                    case R.id.unfurnishedRdBtn:
                        furnishingStatusStr = "Unfurnished";
                        break;

                    case R.id.semiFurnRdBtn:
                        furnishingStatusStr = "Semi Furnished";
                        break;

                    case R.id.fullyFurnRdBtn:
                        furnishingStatusStr = "Fully Furnished";
                        break;
                }

                String propTypeStr = "";

                if (underConStatus) {
                    propTypeStr = "Under Construction";
                } else if (readyToMvStatus) {
                    propTypeStr = "Ready To Move";
                }

                String possesionMonth = "", possesionYear = "";

                if (propTypeStr.matches("Under Construction")) {

                    possesionMonth = monthPosSpin.getSelectedItem().toString().trim();
                    possesionYear = yearPosSPin.getSelectedItem().toString().trim();

                } else {
                    possesionMonth = "";
                    possesionYear = "";
                }

                String saleType = "";

                if (newPropFlag) {
                    saleType = "New";
                } else if (resalePropFlag) {
                    saleType = "Resale";
                }

                String societyStr = "";
                societyStr = societySpin.getSelectedItem().toString().trim();


                String maintainanceChrgs = maintainanceChrgsInput.getEditText().getText().toString().trim();
                String maintainanceChrgsType = maintainanceChrgesSpn.getSelectedItem().toString().trim();

                String reraRegistrationNum = "";
                if (reraStatusStr.matches("RERA Approved")) {
                    reraRegistrationNum = reraRegistrationNumEdtx.getText().toString().trim();
                }

                // ---------------- Residential and commercial common validations ------------------

                if (bedroomStr.matches("")) {

                    bedroomsTitleTv.requestFocus();
                    scrollView.scrollTo(0, bedroomsTitleTv.getTop());
                    displayWarning("Select Bedrom");

                } else if (floor.matches("Select Floor")) {

                    floorTitleTv.requestFocus();
                    scrollView.scrollTo(0, floorTitleTv.getBottom());
                    displayWarning("Select Floor");

                } else if (bathroomStr.matches("")) {

                    bathroomTitleTv.requestFocus();
                    scrollView.scrollTo(0, bathroomTitleTv.getTop());
                    displayWarning("Select Bathroom");

                } else if (balconyStr.matches("")) {

                    balconyTitleTv.requestFocus();
                    scrollView.scrollTo(0, balconyTitleTv.getTop());
                    displayWarning("Select Balcony");

                } else if (reraStatusStr.matches("")) {

                    reraApprovedPropChkbx.requestFocus();
                    //scrollView.scrollTo(0, reraApprovedPropChkbx.getTop());
                    reraTitleTv.getParent().requestChildFocus(reraTitleTv, reraTitleTv);
                    displayWarning("Select RERA Status");
                } else if (reraStatusStr.matches("RERA Approved") && reraRegistrationNum.matches("")) {

                    reraApprovedPropChkbx.requestFocus();
                    reraRegistrationNumEdtx.setError("Enter RERA Registration Number");
                    //scrollView.scrollTo(0, reraApprovedPropChkbx.getTop());
                    reraTitleTv.getParent().requestChildFocus(reraTitleTv, reraTitleTv);
                } else if (propTypeStr.matches("")) {

                    underConstruction.requestFocus();
                    underConstruction.getParent().requestChildFocus(underConstruction, underConstruction);
                    displayWarning("Select Property Status");

                } else if (propTypeStr.matches("Under Construction") && possesionMonth.matches("Select Month")) {

                    monthPosSpin.requestFocus();
                    monthPosSpin.getParent().requestChildFocus(monthPosSpin, monthPosSpin);
                    displayWarning("Select Month");

                } else if (propTypeStr.matches("Under Construction") && possesionYear.matches("Select Year")) {

                    yearPosSPin.requestFocus();
                    yearPosSPin.getParent().requestChildFocus(yearPosSPin, yearPosSPin);
                    displayWarning("Select Year");
                } else if (saleType.matches("")) {

                    newBtn.requestFocus();
                    newBtn.getParent().requestChildFocus(newBtn, newBtn);
                    displayWarning("Select Sale Type");

                } else if (societyStr.matches("Select Society")) {

                    societySpin.requestFocus();
                    //scrollView.scrollTo(0, societySpin.getTop());
                    societySpin.getParent().requestChildFocus(societySpin, societySpin);
                    displayWarning("Select Society");

                } else if (maintainanceChrgsType.matches("Select Charges")) {

                    maintainanceChrgesSpn.requestFocus();
                    //scrollView.scrollTo(0, maintainanceChrgesSpn.getTop());
                    maintainanceChrgsTitleTv.getParent().requestChildFocus(maintainanceChrgsTitleTv, maintainanceChrgsTitleTv);
                    displayWarning("Select Maintenance Charges Type");

//                } else if (maintainanceChrgsType.matches("Monthly") || maintainanceChrgs.matches("Yearly") && maintainanceChrgs.matches("")) {
                } else if (isMaintainaceChargesEdtxVisible && maintainanceChrgs.matches("")) {

                    //Toast.makeText(activity, "Maintainance Charges =" + " " + maintainanceChrgs, Toast.LENGTH_SHORT).show();
                    maintainanceChargesEdtx.requestFocus();
                    maintainanceChargesEdtx.setError("Enter Maintenance Charges");
                    maintainanceChrgsTitleTv.getParent().requestChildFocus(maintainanceChrgsTitleTv, maintainanceChrgsTitleTv);

                } else {

                    Toast.makeText(activity, "Submiting Flat", Toast.LENGTH_SHORT).show();

                    Log.d("SUBMIT_FLAT_LOG", "\n\n ---------------- Flat ------------------------ \n\n");

                    Log.d("SUBMIT_FLAT_LOG", "Location =" + " " + location);
                    Log.d("SUBMIT_FLAT_LOG", "Latitude =" + " " + latitudeStr);
                    Log.d("SUBMIT_FLAT_LOG", "Longitude =" + " " + longitudeStr);
                    Log.d("SUBMIT_FLAT_LOG", "City =" + " " + city);
                    Log.d("SUBMIT_FLAT_LOG", "Address =" + " " + googleAddressStr);
                    Log.d("SUBMIT_FLAT_LOG", "Bedroom =" + " " + bedroomStr);
                    Log.d("SUBMIT_FLAT_LOG", "Floor =" + " " + floor);
                    Log.d("SUBMIT_FLAT_LOG", "Bathroom =" + " " + bathroomStr);
                    Log.d("SUBMIT_FLAT_LOG", "Balcony =" + " " + balconyStr);
                    Log.d("SUBMIT_FLAT_LOG", "Furnishing Status =" + " " + furnishingStatusStr);
                    Log.d("SUBMIT_FLAT_LOG", "Furnishing Status Id =" + " " + furnishingId);
                    Log.d("SUBMIT_FLAT_LOG", "Flat Area =" + " " + areaStr);
                    Log.d("SUBMIT_FLAT_LOG", "Flat Area Type =" + " " + areaMeasurementType);
                    Log.d("SUBMIT_FLAT_LOG", "Flat Title =" + " " + propTitle);
                    Log.d("SUBMIT_FLAT_LOG", "Flat Descripion =" + " " + propDescription);
                    Log.d("SUBMIT_FLAT_LOG", "Rera Status =" + " " + reraStatusStr);

                    if (reraStatusStr.matches("RERA Approved")) {
                        Log.d("SUBMIT_FLAT_LOG", "Rera Registration Num =" + " " + reraRegistrationNum);
                    }
                    Log.d("SUBMIT_FLAT_LOG", "Amenities =" + " " + amenitiesStr);
                    Log.d("SUBMIT_FLAT_LOG", "Prop Type =" + " " + propTypeStr);

                    if (propTypeStr.matches("Under Construction")) {
                        Log.d("SUBMIT_FLAT_LOG", "Possesion year =" + " " + possesionYear);
                        Log.d("SUBMIT_FLAT_LOG", "Possesion month =" + " " + possesionMonth);
                    }

                    Log.d("SUBMIT_FLAT_LOG", "Sale Type =" + " " + saleType);
                    Log.d("SUBMIT_FLAT_LOG", "Society =" + " " + societyStr);
                    Log.d("SUBMIT_FLAT_LOG", "Post As =" + " " + postAsStr);
                    Log.d("SUBMIT_FLAT_LOG", "Post As Id =" + " " + postAsId);
                    Log.d("SUBMIT_FLAT_LOG", "Owner Name =" + " " + ownerName);
                    Log.d("SUBMIT_FLAT_LOG", "Country Code =" + " " + countryCode);
                    Log.d("SUBMIT_FLAT_LOG", "Mobile Num =" + " " + mobileNum);
                    Log.d("SUBMIT_FLAT_LOG", "Expected Price =" + " " + expectedPrice);
                    Log.d("SUBMIT_FLAT_LOG", "Price Is =" + " " + priceIsStr);
                    Log.d("SUBMIT_FLAT_LOG", "Maintainance Charges =" + " " + maintainanceChrgs);
                    Log.d("SUBMIT_FLAT_LOG", "Maintainance Charges =" + " " + maintainanceChrgsType);

                }

                //Log.i("SUBMIT_FLAT_LOG", );


//            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_APPROVE_PENDING_REQUEST,
//                    new Response.Listener<String>() {
//                        @Override
//                        public void onResponse(String response) {
//
//                            Log.d("EXCEPTION", "Response : " + " " + response);
//
//                            try {
//
////                            JSONArray ja = new JSONArray(response);
////                            JSONObject jo = null;
//
//                                JSONObject jsonObject = new JSONObject(response);
//
//                                String message = null;
//
//                            /*for (int i = 0; i < ja.length(); i++) {
//                                jo = ja.getJSONObject(i);
//
//                                message = jo.getString("Message");
//                            }*/
//
//                                message = jsonObject.getString("Message");
//
//
//                                if (message.matches("1")) {
//
//                                    Intent intent = new Intent(activity, UploadDocumentsActivity.class);
//                                    startActivity(intent);
//
//                            /*    new KAlertDialog(activity, KAlertDialog.SUCCESS_TYPE)
//                                        .setTitleText("Success!")
//                                        .setContentText("Your property has been sent for verification, it will reflect in our app as soon as verification process is completed.")
//                                        .setConfirmClickListener(new KAlertDialog.KAlertClickListener() {
//                                            @Override
//                                            public void onClick(KAlertDialog kAlertDialog) {
//                                                kAlertDialog.dismiss();
//
//                                            }
//                                        })
//                                        .confirmButtonColor(R.drawable.kalert_button_background)
//                                        .show();*/
//
//                                } else if (message.matches("0")) {
//
//                                    new KAlertDialog(mContext, KAlertDialog.ERROR_TYPE)
//                                            .setTitleText("Error!")
//                                            .setContentText("Can't post property, Please try again !")
//                                            .setConfirmClickListener(new KAlertDialog.KAlertClickListener() {
//                                                @Override
//                                                public void onClick(KAlertDialog kAlertDialog) {
//                                                    kAlertDialog.dismiss();
//                                                }
//                                            })
//                                            .confirmButtonColor(R.drawable.kalert_button_background)
//                                            .show();
//
//                                }
//
//
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                                //Toast.makeText(mContext, "JSON Exception : " + " " + e.toString(), Toast.LENGTH_SHORT).show();
//                                Log.d("EXCEPTION", "JSON Exception : " + " " + e.toString());
//
//                                new KAlertDialog(mContext, KAlertDialog.ERROR_TYPE)
//                                        .setTitleText("Error!")
//                                        .setContentText("Ops, Something went wrong!")
//                                        .setConfirmClickListener(new KAlertDialog.KAlertClickListener() {
//                                            @Override
//                                            public void onClick(KAlertDialog kAlertDialog) {
//                                                kAlertDialog.dismiss();
//                                            }
//                                        })
//                                        .confirmButtonColor(R.drawable.kalert_button_background)
//                                        .show();
//
//
//                            }
//                        }
//                    },
//                    new Response.ErrorListener() {
//                        @Override
//                        public void onErrorResponse(VolleyError error) {
//
//                            //Toast.makeText(mContext, "Volley Error : " + " " + error.toString(), Toast.LENGTH_SHORT).show();
//                            Log.d("EXCEPTION", "Volley Error : " + " " + error.toString());
//
//                            new KAlertDialog(mContext, KAlertDialog.ERROR_TYPE)
//                                    .setTitleText("Error!")
//                                    .setContentText("Can't communicate with server, Please try again!")
//                                    .setConfirmClickListener(new KAlertDialog.KAlertClickListener() {
//                                        @Override
//                                        public void onClick(KAlertDialog kAlertDialog) {
//                                            kAlertDialog.dismiss();
//                                        }
//                                    })
//                                    .confirmButtonColor(R.drawable.kalert_button_background)
//                                    .show();
//
//
//                        }
//                    }) {
//                @Override
//                protected Map<String, String> getParams() throws AuthFailureError {
//                    Map<String, String> params = new HashMap<>();
//
//                    params.put("operation", "ggggg");
//
//                    return params;
//                }
//            };
//            RequestQueue requestQueue = Volley.newRequestQueue(mContext);
//            requestQueue.add(stringRequest);
            } 
            
            else if (propertyType.matches("House/Villa")) {

                // ---- House Villa Exclusive Logical Properties -----

                String houseRooms = "";

                for (int i = 0; i < lstcategoryBedrooms.size(); i++) {

                    if (lstcategoryBedrooms.get(i).isSelected()) {
                        houseRooms = lstcategoryBedrooms.get(i).getBedroom();
                    }
                }

                String typeOfHouseVilla = "";

                int typeOfHouseVillaId = typeOfHouseVillaRdGrp.getCheckedRadioButtonId();

                switch (typeOfHouseVillaId) {

                    case R.id.rccRdBtn:
                        typeOfHouseVilla = "RCC";
                        break;

                    case R.id.loadBearingRdBtn:
                        typeOfHouseVilla = "Load Bearing";
                        break;

                    case R.id.otherRdBtn:
                        typeOfHouseVilla = "Other";
                        break;
                }

                // ---- Residential Exclusive Logical Properties for House & Villa----

                String floor = floorLevelSpin.getSelectedItem().toString().trim();

                String bathroomStr = "";

                for (int i = 0; i < lstcategoryBathroom.size(); i++) {

                    if (lstcategoryBathroom.get(i).isSelected()) {
                        bathroomStr = lstcategoryBathroom.get(i).getTitle();
                    }
                }


                String balconyStr = "";

                for (int i = 0; i < lstcategoryBalcony.size(); i++) {

                    if (lstcategoryBalcony.get(i).isSelected()) {
                        balconyStr = lstcategoryBalcony.get(i).getTitle();
                    }
                }

                String furnishingStatusStr = "";

                int furnishingId = furnishingRdGrp.getCheckedRadioButtonId();

                switch (furnishingId) {

                    case R.id.unfurnishedRdBtn:
                        furnishingStatusStr = "Unfurnished";
                        break;

                    case R.id.semiFurnRdBtn:
                        furnishingStatusStr = "Semi Furnished";
                        break;

                    case R.id.fullyFurnRdBtn:
                        furnishingStatusStr = "Fully Furnished";
                        break;
                }

                String propTypeStr = "";

                if (underConStatus) {
                    propTypeStr = "Under Construction";
                } else if (readyToMvStatus) {
                    propTypeStr = "Ready To Move";
                }

                String possesionMonth = "", possesionYear = "";

                if (propTypeStr.matches("Under Construction")) {

                    possesionMonth = monthPosSpin.getSelectedItem().toString().trim();
                    possesionYear = yearPosSPin.getSelectedItem().toString().trim();
                } else {
                    possesionMonth = "";
                    possesionYear = "";
                }

                String saleType = "";

                if (newPropFlag) {
                    saleType = "New";
                } else if (resalePropFlag) {
                    saleType = "Resale";
                }

                String maintainanceChrgs = maintainanceChrgsInput.getEditText().getText().toString().trim();
                String maintainanceChrgsType = maintainanceChrgesSpn.getSelectedItem().toString().trim();

                String reraRegistrationNum = "";
                if (reraStatusStr.matches("RERA Approved")) {
                    reraRegistrationNum = reraRegistrationNumEdtx.getText().toString().trim();
                }


                // ---------------- House/Villa validations ------------------

                if (houseRooms.matches("")) {

                    bedroomsTitleTv.requestFocus();
                    scrollView.scrollTo(0, bedroomsTitleTv.getTop());
                    displayWarning("Select Bedrom");

                } else if (floor.matches("Select Floor")) {

                    floorTitleTv.requestFocus();
                    scrollView.scrollTo(0, floorTitleTv.getBottom());
                    displayWarning("Select Floor");

                } else if (bathroomStr.matches("")) {

                    bathroomTitleTv.requestFocus();
                    scrollView.scrollTo(0, bathroomTitleTv.getTop());
                    displayWarning("Select Bathroom");

                } else if (balconyStr.matches("")) {

                    balconyTitleTv.requestFocus();
                    scrollView.scrollTo(0, balconyTitleTv.getTop());
                    displayWarning("Select Balcony");

                } else if (reraStatusStr.matches("")) {

                    reraApprovedPropChkbx.requestFocus();
                    //scrollView.scrollTo(0, reraApprovedPropChkbx.getTop());
                    reraTitleTv.getParent().requestChildFocus(reraTitleTv, reraTitleTv);
                    displayWarning("Select RERA Status");
                } else if (reraStatusStr.matches("RERA Approved") && reraRegistrationNum.matches("")) {

                    reraApprovedPropChkbx.requestFocus();
                    reraRegistrationNumEdtx.setError("Enter RERA Registration Number");
                    //scrollView.scrollTo(0, reraApprovedPropChkbx.getTop());
                    reraTitleTv.getParent().requestChildFocus(reraTitleTv, reraTitleTv);
                } else if (propTypeStr.matches("")) {

                    underConstruction.requestFocus();
                    underConstruction.getParent().requestChildFocus(underConstruction, underConstruction);
                    displayWarning("Select Property Status");

                } else if (propTypeStr.matches("Under Construction") && possesionMonth.matches("Select Month")) {

                    monthPosSpin.requestFocus();
                    monthPosSpin.getParent().requestChildFocus(monthPosSpin, monthPosSpin);
                    displayWarning("Select Month");

                } else if (propTypeStr.matches("Under Construction") && possesionYear.matches("Select Year")) {

                    yearPosSPin.requestFocus();
                    yearPosSPin.getParent().requestChildFocus(yearPosSPin, yearPosSPin);
                    displayWarning("Select Year");
                } else if (saleType.matches("")) {

                    newBtn.requestFocus();
                    newBtn.getParent().requestChildFocus(newBtn, newBtn);
                    displayWarning("Select Sale Type");

                } else if (maintainanceChrgsType.matches("Select Charges")) {

                    maintainanceChrgesSpn.requestFocus();
                    //scrollView.scrollTo(0, maintainanceChrgesSpn.getTop());
                    maintainanceChrgsTitleTv.getParent().requestChildFocus(maintainanceChrgsTitleTv, maintainanceChrgsTitleTv);
                    displayWarning("Select Maintainance Charges Type");

//                } else if (maintainanceChrgsType.matches("Monthly") || maintainanceChrgs.matches("Yearly") && maintainanceChrgs.matches("")) {
                } else if (isMaintainaceChargesEdtxVisible && maintainanceChrgs.matches("")) {

                    //Toast.makeText(activity, "Maintainance Charges =" + " " + maintainanceChrgs, Toast.LENGTH_SHORT).show();
                    maintainanceChargesEdtx.requestFocus();
                    maintainanceChargesEdtx.setError("Enter Maintainance Charges");
                    maintainanceChrgsTitleTv.getParent().requestChildFocus(maintainanceChrgsTitleTv, maintainanceChrgsTitleTv);

                } else {

                    Toast.makeText(activity, "Submitting House/Villa", Toast.LENGTH_SHORT).show();

                    Log.d("SUBMIT_FLAT_LOG", "\n\n --------------- House / Villa ------------------------ \n\n");

                    Log.d("SUBMIT_FLAT_LOG", "Rooms =" + " " + houseRooms);
                    Log.d("SUBMIT_FLAT_LOG", "Type of House/Villa =" + " " + typeOfHouseVilla);
                    Log.d("SUBMIT_FLAT_LOG", "Location =" + " " + location);
                    Log.d("SUBMIT_FLAT_LOG", "Latitude =" + " " + latitudeStr);
                    Log.d("SUBMIT_FLAT_LOG", "Longitude =" + " " + longitudeStr);
                    Log.d("SUBMIT_FLAT_LOG", "City =" + " " + city);
                    Log.d("SUBMIT_FLAT_LOG", "Address =" + " " + googleAddressStr);
                    Log.d("SUBMIT_FLAT_LOG", "Bedroom =" + " " + houseRooms);
                    Log.d("SUBMIT_FLAT_LOG", "Floor =" + " " + floor);
                    Log.d("SUBMIT_FLAT_LOG", "Bathroom =" + " " + bathroomStr);
                    Log.d("SUBMIT_FLAT_LOG", "Balcony =" + " " + balconyStr);
                    Log.d("SUBMIT_FLAT_LOG", "Furnishing Status =" + " " + furnishingStatusStr);
                    //Log.d("SUBMIT_FLAT_LOG", "Furnishing Status Id =" + " " + furnishingId);
                    Log.d("SUBMIT_FLAT_LOG", "Flat Area =" + " " + areaStr);
                    Log.d("SUBMIT_FLAT_LOG", "Flat Area Type =" + " " + areaMeasurementType);
                    Log.d("SUBMIT_FLAT_LOG", "Flat Title =" + " " + propTitle);
                    Log.d("SUBMIT_FLAT_LOG", "Flat Descripion =" + " " + propDescription);
                    Log.d("SUBMIT_FLAT_LOG", "Rera Status =" + " " + reraStatusStr);

                    if (reraStatusStr.matches("RERA Approved")) {
                        Log.d("SUBMIT_FLAT_LOG", "Rera Registration Num =" + " " + reraRegistrationNum);
                    }
                    Log.d("SUBMIT_FLAT_LOG", "Amenities =" + " " + amenitiesStr);
                    Log.d("SUBMIT_FLAT_LOG", "Prop Type =" + " " + propTypeStr);

                    if (propTypeStr.matches("Under Construction")) {
                        Log.d("SUBMIT_FLAT_LOG", "Possesion year =" + " " + possesionYear);
                        Log.d("SUBMIT_FLAT_LOG", "Possesion month =" + " " + possesionMonth);
                    }

                    Log.d("SUBMIT_FLAT_LOG", "Sale Type =" + " " + saleType);
                    Log.d("SUBMIT_FLAT_LOG", "Post As =" + " " + postAsStr);
                    //Log.d("SUBMIT_FLAT_LOG", "Post As Id =" + " " + postAsId);
                    Log.d("SUBMIT_FLAT_LOG", "Owner Name =" + " " + ownerName);
                    Log.d("SUBMIT_FLAT_LOG", "Country Code =" + " " + countryCode);
                    Log.d("SUBMIT_FLAT_LOG", "Mobile Num =" + " " + mobileNum);
                    Log.d("SUBMIT_FLAT_LOG", "Expected Price =" + " " + expectedPrice);
                    Log.d("SUBMIT_FLAT_LOG", "Price Is =" + " " + priceIsStr);
                    Log.d("SUBMIT_FLAT_LOG", "Maintainance Charges =" + " " + maintainanceChrgs);
                    Log.d("SUBMIT_FLAT_LOG", "Maintainance Charges =" + " " + maintainanceChrgsType);

                }

            } 
            
            else if (propertyType.matches("NA Plot")) {

                String typeOfNA_Plot = "";

                int typeOfNA_PlotId = typeOfNAPLotRdGrp.getCheckedRadioButtonId();

                switch (typeOfNA_PlotId) {

                    case R.id.residentialRdBtn:
                        typeOfNA_Plot = "Residential";
                        break;

                    case R.id.commercialRdBtn:
                        typeOfNA_Plot = "Commercial";
                        break;

                    case R.id.industrialRdBtn:
                        typeOfNA_Plot = "Industrial";
                        break;
                }

                String reraRegistrationNum = "";
                if (reraStatusStr.matches("RERA Approved")) {
                    reraRegistrationNum = reraRegistrationNumEdtx.getText().toString().trim();
                }


                // ---------------- NA Plot validations ------------------

                if (reraStatusStr.matches("")) {

                    reraApprovedPropChkbx.requestFocus();
                    //scrollView.scrollTo(0, reraApprovedPropChkbx.getTop());
                    reraTitleTv.getParent().requestChildFocus(reraTitleTv, reraTitleTv);
                    displayWarning("Select RERA Status");

                } else if (reraStatusStr.matches("RERA Approved") && reraRegistrationNum.matches("")) {

                    reraApprovedPropChkbx.requestFocus();
                    reraRegistrationNumEdtx.setError("Enter RERA Registration Number");
                    //scrollView.scrollTo(0, reraApprovedPropChkbx.getTop());
                    reraTitleTv.getParent().requestChildFocus(reraTitleTv, reraTitleTv);

                } else {

                    Toast.makeText(activity, "Submitting NA Plot", Toast.LENGTH_SHORT).show();

                    Log.d("SUBMIT_FLAT_LOG", "\n\n --------------- NA Plot ------------------------ \n\n");

                    Log.d("SUBMIT_FLAT_LOG", "Type of NA Plot =" + " " + typeOfNA_Plot);
                    Log.d("SUBMIT_FLAT_LOG", "Location =" + " " + location);
                    Log.d("SUBMIT_FLAT_LOG", "Latitude =" + " " + latitudeStr);
                    Log.d("SUBMIT_FLAT_LOG", "Longitude =" + " " + longitudeStr);
                    Log.d("SUBMIT_FLAT_LOG", "City =" + " " + city);
                    Log.d("SUBMIT_FLAT_LOG", "Address =" + " " + googleAddressStr);
                    Log.d("SUBMIT_FLAT_LOG", "Flat Area =" + " " + areaStr);
                    Log.d("SUBMIT_FLAT_LOG", "Flat Area Type =" + " " + areaMeasurementType);
                    Log.d("SUBMIT_FLAT_LOG", "Flat Title =" + " " + propTitle);
                    Log.d("SUBMIT_FLAT_LOG", "Flat Descripion =" + " " + propDescription);
                    Log.d("SUBMIT_FLAT_LOG", "Rera Status =" + " " + reraStatusStr);

                    if (reraStatusStr.matches("RERA Approved")) {
                        Log.d("SUBMIT_FLAT_LOG", "Rera Registration Num =" + " " + reraRegistrationNum);
                    }
                    Log.d("SUBMIT_FLAT_LOG", "Amenities =" + " " + amenitiesStr);
                    Log.d("SUBMIT_FLAT_LOG", "Post As =" + " " + postAsStr);
                    //Log.d("SUBMIT_FLAT_LOG", "Post As Id =" + " " + postAsId);
                    Log.d("SUBMIT_FLAT_LOG", "Owner Name =" + " " + ownerName);
                    Log.d("SUBMIT_FLAT_LOG", "Country Code =" + " " + countryCode);
                    Log.d("SUBMIT_FLAT_LOG", "Mobile Num =" + " " + mobileNum);
                    Log.d("SUBMIT_FLAT_LOG", "Expected Price =" + " " + expectedPrice);
                    Log.d("SUBMIT_FLAT_LOG", "Price Is =" + " " + priceIsStr);

                }

            } 
            
            else if (propertyType.matches("Agricultural Land")) {

                Toast.makeText(activity, "Submitting Agricultural Land", Toast.LENGTH_SHORT).show();

                Log.d("SUBMIT_FLAT_LOG", "\n\n --------------- Agricultural Land ------------------------ \n\n");

                Log.d("SUBMIT_FLAT_LOG", "Location =" + " " + location);
                Log.d("SUBMIT_FLAT_LOG", "Latitude =" + " " + latitudeStr);
                Log.d("SUBMIT_FLAT_LOG", "Longitude =" + " " + longitudeStr);
                Log.d("SUBMIT_FLAT_LOG", "City =" + " " + city);
                Log.d("SUBMIT_FLAT_LOG", "Address =" + " " + googleAddressStr);
                Log.d("SUBMIT_FLAT_LOG", "Flat Area =" + " " + areaStr);
                Log.d("SUBMIT_FLAT_LOG", "Flat Area Type =" + " " + areaMeasurementType);
                Log.d("SUBMIT_FLAT_LOG", "Flat Title =" + " " + propTitle);
                Log.d("SUBMIT_FLAT_LOG", "Flat Descripion =" + " " + propDescription);
                //Log.d("SUBMIT_FLAT_LOG", "Rera Status =" + " " + reraStatusStr); // no rera for agricultural

                Log.d("SUBMIT_FLAT_LOG", "Amenities =" + " " + amenitiesStr);
                Log.d("SUBMIT_FLAT_LOG", "Post As =" + " " + postAsStr);
                //Log.d("SUBMIT_FLAT_LOG", "Post As Id =" + " " + postAsId);
                Log.d("SUBMIT_FLAT_LOG", "Owner Name =" + " " + ownerName);
                Log.d("SUBMIT_FLAT_LOG", "Country Code =" + " " + countryCode);
                Log.d("SUBMIT_FLAT_LOG", "Mobile Num =" + " " + mobileNum);
                Log.d("SUBMIT_FLAT_LOG", "Expected Price =" + " " + expectedPrice);
                Log.d("SUBMIT_FLAT_LOG", "Price Is =" + " " + priceIsStr);

            } 
            
            else if (propertyType.matches("Office Space")) {

                // ---- Office Space Exclusive Logical Properties -----

                String cabins = cabinSpin.getSelectedItem().toString().trim();
                String conferenceRoom = conferenceSpin.getSelectedItem().toString().trim();
                String receptions = receptionSpin.getSelectedItem().toString().trim();

                // ---- Residential Exclusive Logical Properties for House & Villa ----

                String floor = floorLevelSpin.getSelectedItem().toString().trim();

                String bathroomStr = "";

                for (int i = 0; i < lstcategoryBathroom.size(); i++) {

                    if (lstcategoryBathroom.get(i).isSelected()) {
                        bathroomStr = lstcategoryBathroom.get(i).getTitle();
                    }
                }


                String balconyStr = "";

                for (int i = 0; i < lstcategoryBalcony.size(); i++) {

                    if (lstcategoryBalcony.get(i).isSelected()) {
                        balconyStr = lstcategoryBalcony.get(i).getTitle();
                    }
                }

                String furnishingStatusStr = "";

                int furnishingId = furnishingRdGrp.getCheckedRadioButtonId();

                switch (furnishingId) {

                    case R.id.unfurnishedRdBtn:
                        furnishingStatusStr = "Unfurnished";
                        break;

                    case R.id.semiFurnRdBtn:
                        furnishingStatusStr = "Semi Furnished";
                        break;

                    case R.id.fullyFurnRdBtn:
                        furnishingStatusStr = "Fully Furnished";
                        break;
                }

                String propTypeStr = "";

                if (underConStatus) {
                    propTypeStr = "Under Construction";
                } else if(readyToMvStatus){
                    propTypeStr = "Ready To Move";
                }

                String possesionMonth = "", possesionYear = "";

                if (propTypeStr.matches("Under Construction")) {

                    possesionMonth = monthPosSpin.getSelectedItem().toString().trim();
                    possesionYear = yearPosSPin.getSelectedItem().toString().trim();
                } else {
                    possesionMonth = "";
                    possesionYear = "";
                }

                String saleType = "";

                if (newPropFlag) {
                    saleType = "New";
                } else if(resalePropFlag){
                    saleType = "Resale";
                }

                String maintainanceChrgs = maintainanceChrgsInput.getEditText().getText().toString().trim();
                String maintainanceChrgsType = maintainanceChrgesSpn.getSelectedItem().toString().trim();

                String reraRegistrationNum = "";
                if (reraStatusStr.matches("RERA Approved")) {
                    reraRegistrationNum = reraRegistrationNumEdtx.getText().toString().trim();
                }

                // ---------------- Office Space common validations ------------------
                
                if(cabins.matches("Select Cabins")){
                    
                    cabinSpin.requestFocus();
                    //scrollView.scrollTo(0, reraApprovedPropChkbx.getTop());
                    cabinSpin.getParent().requestChildFocus(cabinSpin, cabinSpin);
                    displayWarning("Select Cabins");
                }

                else if(conferenceRoom.matches("Select Conference Rooms")){

                    conferenceSpin.requestFocus();
                    //scrollView.scrollTo(0, reraApprovedPropChkbx.getTop());
                    conferenceSpin.getParent().requestChildFocus(conferenceSpin, conferenceSpin);
                    displayWarning("Select Conference Rooms");
                }

                else if(receptions.matches("Select Receptions")){

                    receptionSpin.requestFocus();
                    //scrollView.scrollTo(0, reraApprovedPropChkbx.getTop());
                    receptionSpin.getParent().requestChildFocus(receptionSpin, receptionSpin);
                    displayWarning("Select Receptions");
                }

                else if (floor.matches("Select Floor")) {

                    floorTitleTv.requestFocus();
                    scrollView.scrollTo(0, floorTitleTv.getBottom());
                    displayWarning("Select Floor");

                } else if (bathroomStr.matches("")) {

                    bathroomTitleTv.requestFocus();
                    scrollView.scrollTo(0, bathroomTitleTv.getTop());
                    displayWarning("Select Bathroom");

                } else if (balconyStr.matches("")) {

                    balconyTitleTv.requestFocus();
                    scrollView.scrollTo(0, balconyTitleTv.getTop());
                    displayWarning("Select Balcony");

                } else if (reraStatusStr.matches("")) {

                    reraApprovedPropChkbx.requestFocus();
                    //scrollView.scrollTo(0, reraApprovedPropChkbx.getTop());
                    reraTitleTv.getParent().requestChildFocus(reraTitleTv, reraTitleTv);
                    displayWarning("Select RERA Status");
                    
                }
                else if (reraStatusStr.matches("RERA Approved") && reraRegistrationNum.matches("")) {

                    reraApprovedPropChkbx.requestFocus();
                    reraRegistrationNumEdtx.setError("Enter RERA Registration Number");
                    //scrollView.scrollTo(0, reraApprovedPropChkbx.getTop());
                    reraTitleTv.getParent().requestChildFocus(reraTitleTv, reraTitleTv);

                } else if (propTypeStr.matches("")) {

                    underConstruction.requestFocus();
                    underConstruction.getParent().requestChildFocus(underConstruction, underConstruction);
                    displayWarning("Select Property Status");

                } else if (propTypeStr.matches("Under Construction") && possesionMonth.matches("Select Month")) {

                    monthPosSpin.requestFocus();
                    monthPosSpin.getParent().requestChildFocus(monthPosSpin, monthPosSpin);
                    displayWarning("Select Month");

                } else if (propTypeStr.matches("Under Construction") && possesionYear.matches("Select Year")) {

                    yearPosSPin.requestFocus();
                    yearPosSPin.getParent().requestChildFocus(yearPosSPin, yearPosSPin);
                    displayWarning("Select Year");
                } else if (saleType.matches("")) {

                    newBtn.requestFocus();
                    newBtn.getParent().requestChildFocus(newBtn, newBtn);
                    displayWarning("Select Sale Type");

                } else if (maintainanceChrgsType.matches("Select Charges")) {

                    maintainanceChrgesSpn.requestFocus();
                    //scrollView.scrollTo(0, maintainanceChrgesSpn.getTop());
                    maintainanceChrgsTitleTv.getParent().requestChildFocus(maintainanceChrgsTitleTv, maintainanceChrgsTitleTv);
                    displayWarning("Select Maintainance Charges Type");

//                } else if (maintainanceChrgsType.matches("Monthly") || maintainanceChrgs.matches("Yearly") && maintainanceChrgs.matches("")) {
                } else if (isMaintainaceChargesEdtxVisible && maintainanceChrgs.matches("")) {

                    //Toast.makeText(activity, "Maintainance Charges =" + " " + maintainanceChrgs, Toast.LENGTH_SHORT).show();
                    maintainanceChargesEdtx.requestFocus();
                    maintainanceChargesEdtx.setError("Enter Maintainance Charges");
                    maintainanceChrgsTitleTv.getParent().requestChildFocus(maintainanceChrgsTitleTv, maintainanceChrgsTitleTv);

                } else {

                    Toast.makeText(activity, "Submitting Office Space", Toast.LENGTH_SHORT).show();

                    Log.d("SUBMIT_FLAT_LOG", "\n\n --------------- Office Space ------------------------ \n\n");

                    Log.d("SUBMIT_FLAT_LOG", "Cabins =" + " " + cabins);
                    Log.d("SUBMIT_FLAT_LOG", "Conference Rooms =" + " " + conferenceRoom);
                    Log.d("SUBMIT_FLAT_LOG", "Receptions =" + " " + receptions);
                    Log.d("SUBMIT_FLAT_LOG", "Location =" + " " + location);
                    Log.d("SUBMIT_FLAT_LOG", "Latitude =" + " " + latitudeStr);
                    Log.d("SUBMIT_FLAT_LOG", "Longitude =" + " " + longitudeStr);
                    Log.d("SUBMIT_FLAT_LOG", "City =" + " " + city);
                    Log.d("SUBMIT_FLAT_LOG", "Address =" + " " + googleAddressStr);
                    Log.d("SUBMIT_FLAT_LOG", "Floor =" + " " + floor);
                    Log.d("SUBMIT_FLAT_LOG", "Bathroom =" + " " + bathroomStr);
                    Log.d("SUBMIT_FLAT_LOG", "Balcony =" + " " + balconyStr);
                    Log.d("SUBMIT_FLAT_LOG", "Furnishing Status =" + " " + furnishingStatusStr);
                    //Log.d("SUBMIT_FLAT_LOG", "Furnishing Status Id =" + " " + furnishingId);
                    Log.d("SUBMIT_FLAT_LOG", "Flat Area =" + " " + areaStr);
                    Log.d("SUBMIT_FLAT_LOG", "Flat Area Type =" + " " + areaMeasurementType);
                    Log.d("SUBMIT_FLAT_LOG", "Flat Title =" + " " + propTitle);
                    Log.d("SUBMIT_FLAT_LOG", "Flat Descripion =" + " " + propDescription);
                    Log.d("SUBMIT_FLAT_LOG", "Rera Status =" + " " + reraStatusStr);

                    if (reraStatusStr.matches("RERA Approved")) {
                        Log.d("SUBMIT_FLAT_LOG", "Rera Registration Num =" + " " + reraRegistrationNum);
                    }
                    Log.d("SUBMIT_FLAT_LOG", "Amenities =" + " " + amenitiesStr);
                    Log.d("SUBMIT_FLAT_LOG", "Prop Type =" + " " + propTypeStr);

                    if (propTypeStr.matches("Under Construction")) {
                        Log.d("SUBMIT_FLAT_LOG", "Possesion year =" + " " + possesionYear);
                        Log.d("SUBMIT_FLAT_LOG", "Possesion month =" + " " + possesionMonth);
                    }

                    Log.d("SUBMIT_FLAT_LOG", "Sale Type =" + " " + saleType);
                    Log.d("SUBMIT_FLAT_LOG", "Post As =" + " " + postAsStr);
                    //Log.d("SUBMIT_FLAT_LOG", "Post As Id =" + " " + postAsId);
                    Log.d("SUBMIT_FLAT_LOG", "Owner Name =" + " " + ownerName);
                    Log.d("SUBMIT_FLAT_LOG", "Country Code =" + " " + countryCode);
                    Log.d("SUBMIT_FLAT_LOG", "Mobile Num =" + " " + mobileNum);
                    Log.d("SUBMIT_FLAT_LOG", "Expected Price =" + " " + expectedPrice);
                    Log.d("SUBMIT_FLAT_LOG", "Price Is =" + " " + priceIsStr);
                    Log.d("SUBMIT_FLAT_LOG", "Maintainance Charges =" + " " + maintainanceChrgs);
                    Log.d("SUBMIT_FLAT_LOG", "Maintainance Charges =" + " " + maintainanceChrgsType);

                }

            } 
            
            else if (propertyType.matches("Shop/Showroom")) {

                String facing = "";

                for (int i = 0; i < lstcategoryFacing.size(); i++) {

                    if (lstcategoryFacing.get(i).isSelected()) {
                        facing = lstcategoryFacing.get(i).getTitle();
                    }
                }

                // ---- Shop/Showroom Exclusive Logical Properties -----

                String cabins = cabinSpin.getSelectedItem().toString().trim();
                String conferenceRoom = conferenceSpin.getSelectedItem().toString().trim();
                String receptions = receptionSpin.getSelectedItem().toString().trim();

                // ---- Shop/Showroom Exclusive Logical Properties for Shop/Showroom----

                String floor = floorLevelSpin.getSelectedItem().toString().trim();

                String bathroomStr = "";

                for (int i = 0; i < lstcategoryBathroom.size(); i++) {

                    if (lstcategoryBathroom.get(i).isSelected()) {
                        bathroomStr = lstcategoryBathroom.get(i).getTitle();
                    }
                }


                String balconyStr = "";

                for (int i = 0; i < lstcategoryBalcony.size(); i++) {

                    if (lstcategoryBalcony.get(i).isSelected()) {
                        balconyStr = lstcategoryBalcony.get(i).getTitle();
                    }
                }

                String furnishingStatusStr = "";

                int furnishingId = furnishingRdGrp.getCheckedRadioButtonId();

                switch (furnishingId) {

                    case R.id.unfurnishedRdBtn:
                        furnishingStatusStr = "Unfurnished";
                        break;

                    case R.id.semiFurnRdBtn:
                        furnishingStatusStr = "Semi Furnished";
                        break;

                    case R.id.fullyFurnRdBtn:
                        furnishingStatusStr = "Fully Furnished";
                        break;
                }

                String propTypeStr = "";

                if (underConStatus) {
                    propTypeStr = "Under Construction";
                } else if(readyToMvStatus) {
                    propTypeStr = "Ready To Move";
                }

                String possesionMonth = "", possesionYear = "";

                if (propTypeStr.matches("Under Construction")) {

                    possesionMonth = monthPosSpin.getSelectedItem().toString().trim();
                    possesionYear = yearPosSPin.getSelectedItem().toString().trim();
                } else {
                    possesionMonth = "";
                    possesionYear = "";
                }

                String saleType = "";

                if (newPropFlag) {
                    saleType = "New";
                } else if(resalePropFlag){
                    saleType = "Resale";
                }

                String maintainanceChrgs = maintainanceChrgsInput.getEditText().getText().toString().trim();
                String maintainanceChrgsType = maintainanceChrgesSpn.getSelectedItem().toString().trim();

                String reraRegistrationNum = "";
                if (reraStatusStr.matches("RERA Approved")) {
                    reraRegistrationNum = reraRegistrationNumEdtx.getText().toString().trim();
                }

                // ---------------- Office Space common validations ------------------

                if(cabins.matches("Select Cabins")){

                    cabinSpin.requestFocus();
                    //scrollView.scrollTo(0, reraApprovedPropChkbx.getTop());
                    cabinSpin.getParent().requestChildFocus(cabinSpin, cabinSpin);
                    displayWarning("Select Cabins");
                }

                else if(conferenceRoom.matches("Select Conference Rooms")){

                    conferenceSpin.requestFocus();
                    //scrollView.scrollTo(0, reraApprovedPropChkbx.getTop());
                    conferenceSpin.getParent().requestChildFocus(conferenceSpin, conferenceSpin);
                    displayWarning("Select Conference Rooms");
                }

                else if(receptions.matches("Select Receptions")){

                    receptionSpin.requestFocus();
                    //scrollView.scrollTo(0, reraApprovedPropChkbx.getTop());
                    receptionSpin.getParent().requestChildFocus(receptionSpin, receptionSpin);
                    displayWarning("Select Receptions");
                }

                else if (floor.matches("Select Floor")) {

                    floorTitleTv.requestFocus();
                    scrollView.scrollTo(0, floorTitleTv.getBottom());
                    displayWarning("Select Floor");

                } else if (bathroomStr.matches("")) {

                    bathroomTitleTv.requestFocus();
                    scrollView.scrollTo(0, bathroomTitleTv.getTop());
                    displayWarning("Select Bathroom");

                } else if (balconyStr.matches("")) {

                    balconyTitleTv.requestFocus();
                    scrollView.scrollTo(0, balconyTitleTv.getTop());
                    displayWarning("Select Balcony");

                } else if (reraStatusStr.matches("")) {

                    reraApprovedPropChkbx.requestFocus();
                    //scrollView.scrollTo(0, reraApprovedPropChkbx.getTop());
                    reraTitleTv.getParent().requestChildFocus(reraTitleTv, reraTitleTv);
                    displayWarning("Select RERA Status");

                }
                else if (reraStatusStr.matches("RERA Approved") && reraRegistrationNum.matches("")) {

                    reraApprovedPropChkbx.requestFocus();
                    reraRegistrationNumEdtx.setError("Enter RERA Registration Number");
                    //scrollView.scrollTo(0, reraApprovedPropChkbx.getTop());
                    reraTitleTv.getParent().requestChildFocus(reraTitleTv, reraTitleTv);
                } else if (propTypeStr.matches("")) {

                    underConstruction.requestFocus();
                    underConstruction.getParent().requestChildFocus(underConstruction, underConstruction);
                    displayWarning("Select Property Status");

                } else if (propTypeStr.matches("Under Construction") && possesionMonth.matches("Select Month")) {

                    monthPosSpin.requestFocus();
                    monthPosSpin.getParent().requestChildFocus(monthPosSpin, monthPosSpin);
                    displayWarning("Select Month");

                } else if (propTypeStr.matches("Under Construction") && possesionYear.matches("Select Year")) {

                    yearPosSPin.requestFocus();
                    yearPosSPin.getParent().requestChildFocus(yearPosSPin, yearPosSPin);
                    displayWarning("Select Year");

                } else if (saleType.matches("")) {

                    newBtn.requestFocus();
                    newBtn.getParent().requestChildFocus(newBtn, newBtn);
                    displayWarning("Select Sale Type");

                }

                else if (facing.matches("")) {

                    rvFacing.requestFocus();
                    rvFacing.getParent().requestChildFocus(rvFacing, rvFacing);
                    displayWarning("Select Property Facing");

                }

                else if (maintainanceChrgsType.matches("Select Charges")) {

                    maintainanceChrgesSpn.requestFocus();
                    //scrollView.scrollTo(0, maintainanceChrgesSpn.getTop());
                    maintainanceChrgsTitleTv.getParent().requestChildFocus(maintainanceChrgsTitleTv, maintainanceChrgsTitleTv);
                    displayWarning("Select Maintainance Charges Type");

//                } else if (maintainanceChrgsType.matches("Monthly") || maintainanceChrgs.matches("Yearly") && maintainanceChrgs.matches("")) {
                } else if (isMaintainaceChargesEdtxVisible && maintainanceChrgs.matches("")) {

                    //Toast.makeText(activity, "Maintainance Charges =" + " " + maintainanceChrgs, Toast.LENGTH_SHORT).show();
                    maintainanceChargesEdtx.requestFocus();
                    maintainanceChargesEdtx.setError("Enter Maintainance Charges");
                    maintainanceChrgsTitleTv.getParent().requestChildFocus(maintainanceChrgsTitleTv, maintainanceChrgsTitleTv);

                } else {

                    Toast.makeText(activity, "Submitting Shop/Showroom", Toast.LENGTH_SHORT).show();

                    Log.d("SUBMIT_FLAT_LOG", "\n\n --------------- Shop/Showroom ------------------------ \n\n");

                    Log.d("SUBMIT_FLAT_LOG", "Facing =" + " " + facing);
                    Log.d("SUBMIT_FLAT_LOG", "Cabins =" + " " + cabins);
                    Log.d("SUBMIT_FLAT_LOG", "Conference Rooms =" + " " + conferenceRoom);
                    Log.d("SUBMIT_FLAT_LOG", "Receptions =" + " " + receptions);
                    Log.d("SUBMIT_FLAT_LOG", "Location =" + " " + location);
                    Log.d("SUBMIT_FLAT_LOG", "Latitude =" + " " + latitudeStr);
                    Log.d("SUBMIT_FLAT_LOG", "Longitude =" + " " + longitudeStr);
                    Log.d("SUBMIT_FLAT_LOG", "City =" + " " + city);
                    Log.d("SUBMIT_FLAT_LOG", "Address =" + " " + googleAddressStr);
                    Log.d("SUBMIT_FLAT_LOG", "Floor =" + " " + floor);
                    Log.d("SUBMIT_FLAT_LOG", "Bathroom =" + " " + bathroomStr);
                    Log.d("SUBMIT_FLAT_LOG", "Balcony =" + " " + balconyStr);
                    Log.d("SUBMIT_FLAT_LOG", "Furnishing Status =" + " " + furnishingStatusStr);
                    //Log.d("SUBMIT_FLAT_LOG", "Furnishing Status Id =" + " " + furnishingId);
                    Log.d("SUBMIT_FLAT_LOG", "Flat Area =" + " " + areaStr);
                    Log.d("SUBMIT_FLAT_LOG", "Flat Area Type =" + " " + areaMeasurementType);
                    Log.d("SUBMIT_FLAT_LOG", "Flat Title =" + " " + propTitle);
                    Log.d("SUBMIT_FLAT_LOG", "Flat Descripion =" + " " + propDescription);
                    Log.d("SUBMIT_FLAT_LOG", "Rera Status =" + " " + reraStatusStr);

                    if (reraStatusStr.matches("RERA Approved")) {
                        Log.d("SUBMIT_FLAT_LOG", "Rera Registration Num =" + " " + reraRegistrationNum);
                    }
                    Log.d("SUBMIT_FLAT_LOG", "Amenities =" + " " + amenitiesStr);
                    Log.d("SUBMIT_FLAT_LOG", "Prop Type =" + " " + propTypeStr);

                    if (propTypeStr.matches("Under Construction")) {
                        Log.d("SUBMIT_FLAT_LOG", "Possesion year =" + " " + possesionYear);
                        Log.d("SUBMIT_FLAT_LOG", "Possesion month =" + " " + possesionMonth);
                    }

                    Log.d("SUBMIT_FLAT_LOG", "Sale Type =" + " " + saleType);
                    Log.d("SUBMIT_FLAT_LOG", "Post As =" + " " + postAsStr);
                    //Log.d("SUBMIT_FLAT_LOG", "Post As Id =" + " " + postAsId);
                    Log.d("SUBMIT_FLAT_LOG", "Owner Name =" + " " + ownerName);
                    Log.d("SUBMIT_FLAT_LOG", "Country Code =" + " " + countryCode);
                    Log.d("SUBMIT_FLAT_LOG", "Mobile Num =" + " " + mobileNum);
                    Log.d("SUBMIT_FLAT_LOG", "Expected Price =" + " " + expectedPrice);
                    Log.d("SUBMIT_FLAT_LOG", "Price Is =" + " " + priceIsStr);
                    Log.d("SUBMIT_FLAT_LOG", "Maintainance Charges =" + " " + maintainanceChrgs);
                    Log.d("SUBMIT_FLAT_LOG", "Maintainance Charges =" + " " + maintainanceChrgsType);

                }

            } 
            
            else if (propertyType.matches("Other Commercial")) {

                // ---- Office Space Exclusive Logical Properties -----

                String cabins = cabinSpin.getSelectedItem().toString().trim();
                String conferenceRoom = conferenceSpin.getSelectedItem().toString().trim();
                String receptions = receptionSpin.getSelectedItem().toString().trim();

                // ---- Residential Exclusive Logical Properties for House & Villa----

                String floor = floorLevelSpin.getSelectedItem().toString().trim();

                String bathroomStr = "";

                for (int i = 0; i < lstcategoryBathroom.size(); i++) {

                    if (lstcategoryBathroom.get(i).isSelected()) {
                        bathroomStr = lstcategoryBathroom.get(i).getTitle();
                    }
                }


                String balconyStr = "";

                for (int i = 0; i < lstcategoryBalcony.size(); i++) {

                    if (lstcategoryBalcony.get(i).isSelected()) {
                        balconyStr = lstcategoryBalcony.get(i).getTitle();
                    }
                }

                String furnishingStatusStr = "";

                int furnishingId = furnishingRdGrp.getCheckedRadioButtonId();

                switch (furnishingId) {

                    case R.id.unfurnishedRdBtn:
                        furnishingStatusStr = "Unfurnished";
                        break;

                    case R.id.semiFurnRdBtn:
                        furnishingStatusStr = "Semi Furnished";
                        break;

                    case R.id.fullyFurnRdBtn:
                        furnishingStatusStr = "Fully Furnished";
                        break;
                }

                String propTypeStr = "";

                if (underConStatus) {
                    propTypeStr = "Under Construction";
                } else if(readyToMvStatus){
                    propTypeStr = "Ready To Move";
                }

                String possesionMonth = "", possesionYear = "";

                if (propTypeStr.matches("Under Construction")) {

                    possesionMonth = monthPosSpin.getSelectedItem().toString().trim();
                    possesionYear = yearPosSPin.getSelectedItem().toString().trim();
                } else {
                    possesionMonth = "";
                    possesionYear = "";
                }

                String saleType = "";

                if (newPropFlag) {
                    saleType = "New";
                } else if(resalePropFlag){
                    saleType = "Resale";
                }

                String maintainanceChrgs = maintainanceChrgsInput.getEditText().getText().toString().trim();
                String maintainanceChrgsType = maintainanceChrgesSpn.getSelectedItem().toString().trim();

                String reraRegistrationNum = "";
                if (reraStatusStr.matches("RERA Approved")) {
                    reraRegistrationNum = reraRegistrationNumEdtx.getText().toString().trim();
                }

                // ---------------- Other Commercial common validations ------------------

                if(cabins.matches("Select Cabins")){

                    cabinSpin.requestFocus();
                    //scrollView.scrollTo(0, reraApprovedPropChkbx.getTop());
                    cabinSpin.getParent().requestChildFocus(cabinSpin, cabinSpin);
                    displayWarning("Select Cabins");
                }

                else if(conferenceRoom.matches("Select Conference Rooms")){

                    conferenceSpin.requestFocus();
                    //scrollView.scrollTo(0, reraApprovedPropChkbx.getTop());
                    conferenceSpin.getParent().requestChildFocus(conferenceSpin, conferenceSpin);
                    displayWarning("Select Conference Rooms");
                }

                else if(receptions.matches("Select Receptions")){

                    receptionSpin.requestFocus();
                    //scrollView.scrollTo(0, reraApprovedPropChkbx.getTop());
                    receptionSpin.getParent().requestChildFocus(receptionSpin, receptionSpin);
                    displayWarning("Select Receptions");
                }

                else if (floor.matches("Select Floor")) {

                    floorTitleTv.requestFocus();
                    scrollView.scrollTo(0, floorTitleTv.getBottom());
                    displayWarning("Select Floor");

                } else if (bathroomStr.matches("")) {

                    bathroomTitleTv.requestFocus();
                    scrollView.scrollTo(0, bathroomTitleTv.getTop());
                    displayWarning("Select Bathroom");

                } else if (balconyStr.matches("")) {

                    balconyTitleTv.requestFocus();
                    scrollView.scrollTo(0, balconyTitleTv.getTop());
                    displayWarning("Select Balcony");

                } else if (reraStatusStr.matches("")) {

                    reraApprovedPropChkbx.requestFocus();
                    //scrollView.scrollTo(0, reraApprovedPropChkbx.getTop());
                    reraTitleTv.getParent().requestChildFocus(reraTitleTv, reraTitleTv);
                    displayWarning("Select RERA Status");

                }
                else if (reraStatusStr.matches("RERA Approved") && reraRegistrationNum.matches("")) {

                    reraApprovedPropChkbx.requestFocus();
                    reraRegistrationNumEdtx.setError("Enter RERA Registration Number");
                    //scrollView.scrollTo(0, reraApprovedPropChkbx.getTop());
                    reraTitleTv.getParent().requestChildFocus(reraTitleTv, reraTitleTv);
                } else if (propTypeStr.matches("")) {

                    underConstruction.requestFocus();
                    underConstruction.getParent().requestChildFocus(underConstruction, underConstruction);
                    displayWarning("Select Property Status");

                } else if (propTypeStr.matches("Under Construction") && possesionMonth.matches("Select Month")) {

                    monthPosSpin.requestFocus();
                    monthPosSpin.getParent().requestChildFocus(monthPosSpin, monthPosSpin);
                    displayWarning("Select Month");

                } else if (propTypeStr.matches("Under Construction") && possesionYear.matches("Select Year")) {

                    yearPosSPin.requestFocus();
                    yearPosSPin.getParent().requestChildFocus(yearPosSPin, yearPosSPin);
                    displayWarning("Select Year");

                } else if (saleType.matches("")) {

                    newBtn.requestFocus();
                    newBtn.getParent().requestChildFocus(newBtn, newBtn);
                    displayWarning("Select Sale Type");

                }

                /*else if (facing.matches("")) {

                    rvFacing.requestFocus();
                    rvFacing.getParent().requestChildFocus(rvFacing, rvFacing);
                    displayWarning("Select Property Facing");

                }*/

                else if (maintainanceChrgsType.matches("Select Charges")) {

                    maintainanceChrgesSpn.requestFocus();
                    //scrollView.scrollTo(0, maintainanceChrgesSpn.getTop());
                    maintainanceChrgsTitleTv.getParent().requestChildFocus(maintainanceChrgsTitleTv, maintainanceChrgsTitleTv);
                    displayWarning("Select Maintainance Charges Type");

//                } else if (maintainanceChrgsType.matches("Monthly") || maintainanceChrgs.matches("Yearly") && maintainanceChrgs.matches("")) {
                } else if (isMaintainaceChargesEdtxVisible && maintainanceChrgs.matches("")) {

                    //Toast.makeText(activity, "Maintainance Charges =" + " " + maintainanceChrgs, Toast.LENGTH_SHORT).show();
                    maintainanceChargesEdtx.requestFocus();
                    maintainanceChargesEdtx.setError("Enter Maintainance Charges");
                    maintainanceChrgsTitleTv.getParent().requestChildFocus(maintainanceChrgsTitleTv, maintainanceChrgsTitleTv);

                } else {

                    Toast.makeText(activity, "Submitting Other Commercial", Toast.LENGTH_SHORT).show();

                    Log.d("SUBMIT_FLAT_LOG", "\n\n --------------- Other Commercial ------------------------ \n\n");

                    Log.d("SUBMIT_FLAT_LOG", "Cabins =" + " " + cabins);
                    Log.d("SUBMIT_FLAT_LOG", "Conference Rooms =" + " " + conferenceRoom);
                    Log.d("SUBMIT_FLAT_LOG", "Receptions =" + " " + receptions);
                    Log.d("SUBMIT_FLAT_LOG", "Location =" + " " + location);
                    Log.d("SUBMIT_FLAT_LOG", "Latitude =" + " " + latitudeStr);
                    Log.d("SUBMIT_FLAT_LOG", "Longitude =" + " " + longitudeStr);
                    Log.d("SUBMIT_FLAT_LOG", "City =" + " " + city);
                    Log.d("SUBMIT_FLAT_LOG", "Address =" + " " + googleAddressStr);
                    Log.d("SUBMIT_FLAT_LOG", "Floor =" + " " + floor);
                    Log.d("SUBMIT_FLAT_LOG", "Bathroom =" + " " + bathroomStr);
                    Log.d("SUBMIT_FLAT_LOG", "Balcony =" + " " + balconyStr);
                    Log.d("SUBMIT_FLAT_LOG", "Furnishing Status =" + " " + furnishingStatusStr);
                    //Log.d("SUBMIT_FLAT_LOG", "Furnishing Status Id =" + " " + furnishingId);
                    Log.d("SUBMIT_FLAT_LOG", "Flat Area =" + " " + areaStr);
                    Log.d("SUBMIT_FLAT_LOG", "Flat Area Type =" + " " + areaMeasurementType);
                    Log.d("SUBMIT_FLAT_LOG", "Flat Title =" + " " + propTitle);
                    Log.d("SUBMIT_FLAT_LOG", "Flat Descripion =" + " " + propDescription);
                    Log.d("SUBMIT_FLAT_LOG", "Rera Status =" + " " + reraStatusStr);

                    if (reraStatusStr.matches("RERA Approved")) {
                        Log.d("SUBMIT_FLAT_LOG", "Rera Registration Num =" + " " + reraRegistrationNum);
                    }
                    Log.d("SUBMIT_FLAT_LOG", "Amenities =" + " " + amenitiesStr);
                    Log.d("SUBMIT_FLAT_LOG", "Prop Type =" + " " + propTypeStr);

                    if (propTypeStr.matches("Under Construction")) {
                        Log.d("SUBMIT_FLAT_LOG", "Possesion year =" + " " + possesionYear);
                        Log.d("SUBMIT_FLAT_LOG", "Possesion month =" + " " + possesionMonth);
                    }

                    Log.d("SUBMIT_FLAT_LOG", "Sale Type =" + " " + saleType);
                    Log.d("SUBMIT_FLAT_LOG", "Post As =" + " " + postAsStr);
                    //Log.d("SUBMIT_FLAT_LOG", "Post As Id =" + " " + postAsId);
                    Log.d("SUBMIT_FLAT_LOG", "Owner Name =" + " " + ownerName);
                    Log.d("SUBMIT_FLAT_LOG", "Country Code =" + " " + countryCode);
                    Log.d("SUBMIT_FLAT_LOG", "Mobile Num =" + " " + mobileNum);
                    Log.d("SUBMIT_FLAT_LOG", "Expected Price =" + " " + expectedPrice);
                    Log.d("SUBMIT_FLAT_LOG", "Price Is =" + " " + priceIsStr);
                    Log.d("SUBMIT_FLAT_LOG", "Maintainance Charges =" + " " + maintainanceChrgs);
                    Log.d("SUBMIT_FLAT_LOG", "Maintainance Charges =" + " " + maintainanceChrgsType);

                }

            }

        }


    }


    private String pricePerSquareFeet() {

        String price = "-1";

        String area = propAreaEdtx.getText().toString().trim();

        if (propAreaSpn.getSelectedItem().toString().trim().matches("Sqmtr")) {

            if (!area.matches("")) {

                int flatAreaInt = Integer.parseInt(area);

                double sqmtrIntoSqft = flatAreaInt * 10.764;

                double pricePerSqftDble = sqmtrIntoSqft * 1000;

                price = String.valueOf(pricePerSqftDble);

                return price;

            } else {
                propAreaEdtx.setError("Enter Area");
            }

        } else if (propAreaSpn.getSelectedItem().toString().trim().matches("Acre")) {

            if (!area.matches("")) {

                int flatAreaInt = Integer.parseInt(area);

                double sqmtrIntoSqft = flatAreaInt * 10.764;

                double pricePerSqftDble = sqmtrIntoSqft * 1000;

                price = String.valueOf(pricePerSqftDble);

                return price;

            } else {
                propAreaEdtx.setError("Enter Area");
            }
        } else if (propAreaSpn.getSelectedItem().toString().trim().matches("Sqft")) {

            if (!area.matches("")) {

                int flatAreaInt = Integer.parseInt(area);

                double pricePerSqftDble = flatAreaInt * 1000;

                price = String.valueOf(pricePerSqftDble);

                return price;

            } else {
                propAreaEdtx.setError("Enter Area");
            }

        }

        return price;
    }

    // displayWarning() description
    // displayWarning() method do display KalertDialog, calling this method makes code simpler and organised, and it also saves some lines :p
    public void displayWarning(String message) {

        new KAlertDialog(activity, KAlertDialog.WARNING_TYPE)
                .setTitleText("Message!")
                .setContentText(message)
                .setConfirmClickListener(new KAlertDialog.KAlertClickListener() {
                    @Override
                    public void onClick(KAlertDialog kAlertDialog) {
                        kAlertDialog.dismiss();

                    }
                })
                .confirmButtonColor(R.drawable.kalert_button_background)
                .show();
    }


}































