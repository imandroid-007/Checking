package com.example.realestate.Projects;


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
import com.example.realestate.RealEstate.POJO_Amenities;
import com.example.realestate.RealEstate.POJO_bedrooms;
import com.example.realestate.RealEstate.Pojo_TextOpts;
import com.example.realestate.RealEstate.RecyclerviewAdapterAmenities;
import com.example.realestate.RealEstate.RecyclerviewAdapterBedrooms;
import com.example.realestate.RealEstate.RecyclerviewAdapterTextOpts;
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
public class RegisterProjects extends Fragment {


    public RegisterProjects() {
        // Required empty public constructor
    }

    View rootview;
    FragmentActivity activity;
    Context mContext;

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

    private Button btnPostProject;

    private RecyclerView rvFacing;
    private RecyclerviewAdapterTextOpts facingAdapter;
    private ArrayList<Pojo_TextOpts> lstcategoryFacing;

    private CheckBox reraApprovedPropChkbx, notApplicableReraChkbx, negotiableChkBox, fixedChkBox; //reraApprovedAgentChkbx
    private RadioGroup youAreRadioGrp;

    private boolean newPropFlag = false, resalePropFlag = false;
    private EditText owner_name, owner_mobile_no;

    private RecyclerView rvTower;
    private RecyclerviewAdapterTextOpts towerAdapter;
    private ArrayList<Pojo_TextOpts> lstcategoryTower;

    /*private RecyclerView rvBalcony;
    private RecyclerviewAdapterTextOpts balconyAdapter;
    private ArrayList<Pojo_TextOpts> lstcategoryBalcony;*/

    private String propTypeStr = null;

    private RelativeLayout bottomSheetRelativeLayout;
    BottomSheetBehavior sheetBehavior;

    private boolean stateStatus = false;
    private LinearLayout filterBtnLayout;
    private LinearLayout closeFiltersLin;

    private LinearLayout possesionInLayout;

    private FluidSlider fluidSlider;
    private Spinner expectedPriceSpin, countryCodeSpin;
    private ArrayList<String> expectedPriceList = new ArrayList<>();

    private TextView bedroomsTitleTv, amenitiesTitleTv, floorTitleTv, towerTitleTv, agriProjectTitleTv;// furnishingTitleTv;
    private TextView reraTitleTv, whatTypePropTitleTv, societyTitleTv, propAreaTitleTv, maintainanceChrgsTitleTv;
    private TextView typeOfHouseVillaTitleTv, typeOfNAPlotTitleTv, facingTitleTv, builderSpinTitleTv, noOfUnitsTitleTv;

//    bathroomTitleTv

    private RadioGroup typeOfHouseVillaRdGrp, typeOfNAPLotRdGrp;  //furnishingRdGrp
    private RelativeLayout floorSpinConainer, societySpinContainer;
    private LinearLayout maintainanceChrgsContainer, whatTypeOfPropContainer, agriculturalProjTypeContainer;

    private EditText propAreaEdtx, propTitleEdtx, propDescEdtx, reraRegistrationNumEdtx;
    private EditText propLocationEdtx, noOfUnitsEdtx;

    private SeekBar seekBar;
    private TextView budgetValTv;

    private Button uploadPhotosBtn, updateBedroomBtn;

    private TextInputLayout maintainanceChrgsInput;
    /*private Spinner builderSpinner;
    private ArrayList<String> builderSpinList;*/

    private EditText builderEdtx, expectedPriceEdtx, cityEdtx;

    private Button verifyLocationBtn;

    private Spinner agriProjectSpin;
    private ArrayList<String> agriProjectList = new ArrayList<>();
    private ArrayList<String> countryCodeSpinList = new ArrayList<>();

    private ScrollView scrollView;

    private String priceIsStr = "", reraStatusStr = "", googleAddressStr = "";
    private String latitudeStr = "", longitudeStr = "", geoAddresStr = "";

    private EditText maintainanceChargesEdtx;
    private boolean isMaintainaceChargesEdtxVisible = true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootview = inflater.inflate(R.layout.fragment_register_projects, container, false);

        Bundle bundle = getArguments();
        propTypeStr = bundle.getString("PROP_TYPE");

        lstcategoryBedrooms = new ArrayList<>();
        lstcategoryAmenities = new ArrayList<>();
        lstcategoryFacing = new ArrayList<>();
        //lstcategoryBathroom = new ArrayList<>();
        //lstcategoryBalcony = new ArrayList<>();
        lstcategoryTower = new ArrayList<>();
        //builderSpinList = new ArrayList<>();

        initializeViews();

        setupViews();

        customizeViews();


        return rootview;
    }

    private void initializeViews() {

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

        societySpin = (Spinner) rootview.findViewById(R.id.societySpinner);
        propAreaSpn = (Spinner) rootview.findViewById(R.id.propAreaspn);

        recyclerViewAmenities = (RecyclerView) rootview.findViewById(R.id.recyc_amenities);

        floorLevelSpin = (Spinner) rootview.findViewById(R.id.floorNoSpin);
        maintainanceChrgesSpn = (Spinner) rootview.findViewById(R.id.mntncChrgsTypeSpn);
        //builderSpinner = (Spinner) rootview.findViewById(R.id.builderSpin);
        agriProjectSpin = (Spinner) rootview.findViewById(R.id.agriProjectTypeSpin);

        btnPostProject = (Button) rootview.findViewById(R.id.btnPostProp);

        rvFacing = (RecyclerView) rootview.findViewById(R.id.recyc_facing);
        //rvBathroom = (RecyclerView) rootview.findViewById(R.id.recyc_bathroom);
        rvTower = (RecyclerView) rootview.findViewById(R.id.recyc_tower);
        //rvBalcony = (RecyclerView) rootview.findViewById(R.id.recyc_balcony);

        reraApprovedPropChkbx = (CheckBox) rootview.findViewById(R.id.reraAprvdPropChkbx);
        notApplicableReraChkbx = (CheckBox) rootview.findViewById(R.id.notApplicableReraChkbx);
        youAreRadioGrp = (RadioGroup) rootview.findViewById(R.id.youAreRdGrp);

//        newBtn = (Button) rootview.findViewById(R.id.btnSaleNew);
//        resaleBtn = (Button) rootview.findViewById(R.id.btnSaleResale);

        possesionInLayout = (LinearLayout) rootview.findViewById(R.id.possesionInLayout);

        bedroomsTitleTv = (TextView) rootview.findViewById(R.id.bedroomTitleTV);
        amenitiesTitleTv = (TextView) rootview.findViewById(R.id.amenitiesTitleTv);
        floorTitleTv = (TextView) rootview.findViewById(R.id.floorDetailsTitleTv);
        //bathroomTitleTv = (TextView) rootview.findViewById(R.id.bathroomTitleTv);
        towerTitleTv = (TextView) rootview.findViewById(R.id.towerTitleTv);
        //balconyTitleTv = (TextView) rootview.findViewById(R.id.balconyTitleTv);
        //furnishingTitleTv = (TextView) rootview.findViewById(R.id.furnishingTitleTv);
        reraTitleTv = (TextView) rootview.findViewById(R.id.reraApprovedTitleTv);
        whatTypePropTitleTv = (TextView) rootview.findViewById(R.id.whatTypePropTitleTv);
        //saleTypeTitleTv = (TextView) rootview.findViewById(R.id.saleTypeTitileTv);
        societyTitleTv = (TextView) rootview.findViewById(R.id.societyTitleTv);
        propAreaTitleTv = (TextView) rootview.findViewById(R.id.propAreaTitleTv);
        maintainanceChrgsTitleTv = (TextView) rootview.findViewById(R.id.maintainanceChrgsTitleTv);
        typeOfHouseVillaTitleTv = (TextView) rootview.findViewById(R.id.typeOfHoseVillaTitleTv);
        typeOfNAPlotTitleTv = (TextView) rootview.findViewById(R.id.typeOfNAPlotTitleTv);
        facingTitleTv = (TextView) rootview.findViewById(R.id.facingTitleTv);
        builderSpinTitleTv = (TextView) rootview.findViewById(R.id.builderTitleTv);
        noOfUnitsTitleTv = (TextView) rootview.findViewById(R.id.noOfUnitsTitleTv);
        agriProjectTitleTv = (TextView) rootview.findViewById(R.id.agriProjectTypeTitleTv);

        //furnishingRdGrp = (RadioGroup) rootview.findViewById(R.id.furnishingRdGrp);
        typeOfHouseVillaRdGrp = (RadioGroup) rootview.findViewById(R.id.typeOfHouseVillaRdGrp);
        typeOfNAPLotRdGrp = (RadioGroup) rootview.findViewById(R.id.typeOfNAPlotRdGrp);

        floorSpinConainer = (RelativeLayout) rootview.findViewById(R.id.floorSpinContainer);
        societySpinContainer = (RelativeLayout) rootview.findViewById(R.id.societySpinContainer);
        maintainanceChrgsContainer = (LinearLayout) rootview.findViewById(R.id.maintainanceChrgsContainer);
        whatTypeOfPropContainer = (LinearLayout) rootview.findViewById(R.id.whatTypeOfPropContainer);
        //builderSpinContainer = (RelativeLayout) rootview.findViewById(R.id.builderSpiContainer);
        //saleTypeContainer = (LinearLayout) rootview.findViewById(R.id.saleTypeContainer);
        agriculturalProjTypeContainer = (LinearLayout) rootview.findViewById(R.id.agriProjectsTypeContainer);

        propAreaEdtx = (EditText) rootview.findViewById(R.id.propAreaEdtx);
        propTitleEdtx = (EditText) rootview.findViewById(R.id.propTitleEdtx);
        propDescEdtx = (EditText) rootview.findViewById(R.id.propDescrEdtx);
        reraRegistrationNumEdtx = (EditText) rootview.findViewById(R.id.reraRegistrNumEdtx);
        propLocationEdtx = (EditText) rootview.findViewById(R.id.propLocationEdtx);
        noOfUnitsEdtx = (EditText) rootview.findViewById(R.id.noOfUnitsEdtx);
        builderEdtx = (EditText) rootview.findViewById(R.id.builderEdtx);
        expectedPriceEdtx = (EditText) rootview.findViewById(R.id.expectedPriceEdtx);
        cityEdtx = (EditText) rootview.findViewById(R.id.cityEdtx);
        maintainanceChargesEdtx = (EditText) rootview.findViewById(R.id.mntnanceChrgsEdtx);

        negotiableChkBox = (CheckBox) rootview.findViewById(R.id.negotiableChkbox);
        fixedChkBox = (CheckBox) rootview.findViewById(R.id.fixedChkBox);

        seekBar = (SeekBar) rootview.findViewById(R.id.seekBar);
        budgetValTv = (TextView) rootview.findViewById(R.id.budgetValTv);

        uploadPhotosBtn = (Button) rootview.findViewById(R.id.uploadFileBtn);
        verifyLocationBtn = (Button) rootview.findViewById(R.id.verifyLocationButton);
        maintainanceChrgsInput = (TextInputLayout) rootview.findViewById(R.id.maintncChrgsTxtInput);

        scrollView = (ScrollView) rootview.findViewById(R.id.scrollView);
        maintainanceChargesEdtx = (EditText) rootview.findViewById(R.id.mntnanceChrgsEdtx);

        countryCodeSpin = (Spinner) rootview.findViewById(R.id.countryCodeSpin);

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

                // Location drawable end click listener : old

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

                        if(!String.valueOf(s).matches("")) {

                            String valStr = String.valueOf(s);
                            int valInt = Integer.parseInt(valStr);

                            if (valInt <= 40000000) {

                                seekBar.setProgress(valInt / 100000);

                                Log.d("SEEKBAREDITTEXTVALLOG", "Value :" + " " + valInt / 100000);

                                expectedPriceEdtx.setSelection(expectedPriceEdtx.getText().toString().length());

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

                underConstruction.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (underConStatus) {
                            underConStatus = false;
                            //underConstruction.setBackgroundTintList(ContextCompat.getColorStateList(activity, R.color.white));
                            underConstruction.setBackgroundResource(R.drawable.buttonshape_trans);
                            possesionInLayout.setVisibility(View.GONE);
                        } else {
                            if (readyToMvStatus) {
                                readyToMvStatus = false;
                                //readyToMove.setBackgroundTintList(ContextCompat.getColorStateList(activity, R.color.white));
                                readyToMove.setBackgroundResource(R.drawable.buttonshape_trans);
                            }
                            //underConstruction.setBackgroundTintList(ContextCompat.getColorStateList(activity, R.color.colorSelected));
                            underConstruction.setBackgroundResource(R.drawable.selected_button_background);
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
//                            readyToMove.setBackgroundTintList(ContextCompat.getColorStateList(activity, R.color.white));
                            readyToMove.setBackgroundResource(R.drawable.buttonshape_trans);
                        } else {
                            if (underConStatus) {
                                underConStatus = false;
                                //underConstruction.setBackgroundTintList(ContextCompat.getColorStateList(activity, R.color.white));
                                underConstruction.setBackgroundResource(R.drawable.buttonshape_trans);
                            }
                            //readyToMove.setBackgroundTintList(ContextCompat.getColorStateList(activity, R.color.colorSelected));
                            readyToMove.setBackgroundResource(R.drawable.selected_button_background);
                            readyToMvStatus = true;
                            possesionInLayout.setVisibility(View.GONE);
                        }
                    }
                });

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

                btnPostProject.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        postProperty(propTypeStr);

                        /*new KAlertDialog(activity, KAlertDialog.SUCCESS_TYPE)
                                .setTitleText("Success!")
                                .setContentText("Your property has been sent for verification, it will reflect in our app as soon as verification process is completed.")
                                .setConfirmClickListener(new KAlertDialog.KAlertClickListener() {
                                    @Override
                                    public void onClick(KAlertDialog kAlertDialog) {
                                        kAlertDialog.dismiss();

                                    }
                                })
                                .confirmButtonColor(R.drawable.kalert_button_background)
                                .show();*/

                    }
                });

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

                /*newBtn.setOnClickListener(new View.OnClickListener() {
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
                });*/

                /*lstcategoryBathroom.add(new Pojo_TextOpts("1", false));
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
                rvBathroom.setItemAnimator(null);*/

                /*lstcategoryBalcony.add(new Pojo_TextOpts("0", false));
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
                rvBalcony.setItemAnimator(null);*/

            }
        });

    }

    private void customizeViews() {

        if (propTypeStr != null) {

            if (propTypeStr.matches("Apartment") || propTypeStr.matches("House/Villa")) {

                /*builderSpinList.add("Gokhale Contructions");
                builderSpinList.add("Kolte Patil ");
                builderSpinList.add("Kumar Properties");
                builderSpinList.add("Paranjape Builders");
                builderSpinList.add("Nyati Group Builders");
                builderSpinList.add("Paranjape Builders");
                builderSpinList.add("Amar Builders");
                builderSpinList.add("Shree Ram Builders");
                builderSpinList.add("Karan Group");
                builderSpinList.add("Raviraj Realty");

                final ArrayAdapter<String> spinAdapterBuilder = new ArrayAdapter<String>(activity, android.R.layout.simple_spinner_item, builderSpinList);
                spinAdapterBuilder.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                builderSpinner.setAdapter(spinAdapterBuilder);

                builderSpinTitleTv.setVisibility(View.VISIBLE);
                builderSpinContainer.setVisibility(View.VISIBLE);*/

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

                if (propTypeStr.matches("Apartment")) {

                    noOfUnitsTitleTv.setText("Total No of Flats");
                    noOfUnitsEdtx.setHint("Total No of Flats");

                    propAreaTitleTv.setText("Apartment Area");
                    propAreaEdtx.setHint("Apartment Area");
                    propTitleEdtx.setHint("Apartment Title");
                    propDescEdtx.setHint("Apartment Description");

                    floorTitleTv.setText("No. of Floors");

                    floorLevelList.add("Select Floors");
                    floorLevelList.add("Three Floors");
                    floorLevelList.add("Four Floors");
                    floorLevelList.add("Five Floors");
                    floorLevelList.add("Six Floors");
                    floorLevelList.add("Seven Floors");
                    floorLevelList.add("Eight Floors");
                    floorLevelList.add("Nine Floors");
                    floorLevelList.add("Ten Floors");
                    floorLevelList.add("Ten+ Floors");

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

                    propAreaSpnList.add("Select Area");
                    propAreaSpnList.add("Sqft");
                    propAreaSpnList.add("Sqmtr");

                    final ArrayAdapter<String> spinAdapterPropArea = new ArrayAdapter<String>(activity, android.R.layout.simple_spinner_item, propAreaSpnList);
                    spinAdapterPropArea.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    propAreaSpn.setAdapter(spinAdapterPropArea);

                    towerTitleTv.setText("No. of Towers");

                    lstcategoryTower.add(new Pojo_TextOpts("1", false));
                    lstcategoryTower.add(new Pojo_TextOpts("2", false));
                    lstcategoryTower.add(new Pojo_TextOpts("3", false));
                    lstcategoryTower.add(new Pojo_TextOpts("4", false));
                    lstcategoryTower.add(new Pojo_TextOpts("5", false));
                    lstcategoryTower.add(new Pojo_TextOpts("6", false));
                    lstcategoryTower.add(new Pojo_TextOpts("6+", false));

                    towerAdapter = new RecyclerviewAdapterTextOpts(activity, lstcategoryTower, true);

                    int spanCountBath = 4; // 3 columns
                    int spacingBath = 25; // 50px
                    boolean includeEdgeBath = true;
                    rvTower.addItemDecoration(new GridSpacingDecoration(spanCountBath, spacingBath, includeEdgeBath));
                    GridLayoutManager gridLayoutManagerBathroom = new GridLayoutManager(activity, 4);
                    rvTower.setLayoutManager(gridLayoutManagerBathroom);
                    rvTower.setAdapter(towerAdapter);
                    rvTower.setItemAnimator(null);

                    towerTitleTv.setVisibility(View.VISIBLE);
                    rvTower.setVisibility(View.VISIBLE);

                }

                if (propTypeStr.matches("House/Villa")) {

                    noOfUnitsTitleTv.setText("Total No of House/Villa");
                    noOfUnitsEdtx.setHint("Total No of House/Villa");

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

                    propAreaSpnList.add("Select Area");
                    propAreaSpnList.add("Guntha");
                    propAreaSpnList.add("Sqft");
                    propAreaSpnList.add("Sqmtr");

                    final ArrayAdapter<String> spinAdapterPropArea = new ArrayAdapter<String>(activity, android.R.layout.simple_spinner_item, propAreaSpnList);
                    spinAdapterPropArea.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    propAreaSpn.setAdapter(spinAdapterPropArea);

                    //floorTitleTv.setText("");

                }


            }

            else if (propTypeStr.matches("NA Plot") || propTypeStr.matches("Agricultural Project")) {

                builderEdtx.setVisibility(View.GONE);
                builderSpinTitleTv.setVisibility(View.GONE);

                bedroomsTitleTv.setVisibility(View.GONE);
                recyclerViewBedrooms.setVisibility(View.GONE);

                //amenitiesTitleTv.setVisibility(View.GONE);
                // recyclerViewAmenities.setVisibility(View.GONE);

                floorTitleTv.setVisibility(View.GONE);

                floorLevelSpin.setVisibility(View.GONE);
                floorSpinConainer.setVisibility(View.GONE);

//                bathroomTitleTv.setVisibility(View.GONE);
//                rvBathroom.setVisibility(View.GONE);
                /*balconyTitleTv.setVisibility(View.GONE);
                rvBalcony.setVisibility(View.GONE);*/
//                furnishingTitleTv.setVisibility(View.GONE);
//                furnishingRdGrp.setVisibility(View.GONE);
                //reraTitleTv.setVisibility(View.GONE);
                //reraApprovedPropChkbx.setVisibility(View.GONE);
                //notApplicableReraChkbx.setVisibility(View.GONE);

                whatTypePropTitleTv.setVisibility(View.GONE);

                whatTypeOfPropContainer.setVisibility(View.GONE);

                 /*underConstruction.setVisibility(View.GONE);
                 readyToMove.setVisibility(View.GONE);*/

                 //                saleTypeTitleTv.setVisibility(View.GONE);
//                saleTypeContainer.setVisibility(View.GONE);
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

                    noOfUnitsTitleTv.setText("Total No of Plots");
                    noOfUnitsEdtx.setHint("Total No of Plots");

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

                    propAreaSpnList.add("Select Area");
                    propAreaSpnList.add("Guntha");
                    propAreaSpnList.add("Sqft");
                    propAreaSpnList.add("Sqmtr");

                    final ArrayAdapter<String> spinAdapterPropArea = new ArrayAdapter<String>(activity, android.R.layout.simple_spinner_item, propAreaSpnList);
                    spinAdapterPropArea.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    propAreaSpn.setAdapter(spinAdapterPropArea);

                }
                if (propTypeStr.matches("Agricultural Project")) {

                    agriProjectTitleTv.setVisibility(View.VISIBLE);
                    agriculturalProjTypeContainer.setVisibility(View.VISIBLE);

                    noOfUnitsTitleTv.setText("Total No of Lands");
                    noOfUnitsEdtx.setHint("Total No of Lands");

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

                    propAreaSpnList.add("Select Area");
                    propAreaSpnList.add("Acre");
                    propAreaSpnList.add("Hectare");
                    propAreaSpnList.add("Guntha");
                    propAreaSpnList.add("Sqft");
                    propAreaSpnList.add("Sqmtr");

                    final ArrayAdapter<String> spinAdapterPropArea = new ArrayAdapter<String>(activity, android.R.layout.simple_spinner_item, propAreaSpnList);
                    spinAdapterPropArea.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    propAreaSpn.setAdapter(spinAdapterPropArea);

                    agriProjectList.add("Select Project");
                    agriProjectList.add("Fertilizer Production");
                    agriProjectList.add("Bee Keeping");
                    agriProjectList.add("Botanical Pesticide Production");
                    agriProjectList.add("Jaggery Production");
                    agriProjectList.add("Mushroom Farming");
                    agriProjectList.add("Organic Farm Green House");
                    agriProjectList.add("Nursery Plant");
                    agriProjectList.add("Fish Farming");
                    agriProjectList.add("Sunflower Farming");
                    agriProjectList.add("Snail Farming");
                    agriProjectList.add("Poultry Farming");

                    final ArrayAdapter<String> spinAdapterAgriProjects = new ArrayAdapter<String>(activity, android.R.layout.simple_spinner_item, agriProjectList);
                    spinAdapterAgriProjects.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    agriProjectSpin.setAdapter(spinAdapterAgriProjects);

                }

            }

            else if (propTypeStr.matches("Commercial Office") || propTypeStr.matches("Commercial Complex") || propTypeStr.matches("Other Commercial")) {

                /*builderSpinList.add("Gokhale Contructions");
                builderSpinList.add("Kolte Patil ");
                builderSpinList.add("Kumar Properties");
                builderSpinList.add("Paranjape Builders");
                builderSpinList.add("Nyati Group Builders");
                builderSpinList.add("Paranjape Builders");
                builderSpinList.add("Amar Builders");
                builderSpinList.add("Shree Ram Builders");
                builderSpinList.add("Karan Group");
                builderSpinList.add("Raviraj Realty");

                final ArrayAdapter<String> spinAdapterBuilder = new ArrayAdapter<String>(activity, android.R.layout.simple_spinner_item, builderSpinList);
                spinAdapterBuilder.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                builderSpinner.setAdapter(spinAdapterBuilder);

                builderSpinTitleTv.setVisibility(View.VISIBLE);
                builderSpinContainer.setVisibility(View.VISIBLE);*/

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

                societyTitleTv.setVisibility(View.GONE);
                societySpin.setVisibility(View.GONE);
                societySpinContainer.setVisibility(View.GONE);

                propAreaEdtx.setHint("Property Area");

                propTitleEdtx.setHint("Property Title");
                propDescEdtx.setHint("Property Description");

                if (propTypeStr.matches("Commercial Office")) {

                    noOfUnitsTitleTv.setText("Total No of Office");
                    noOfUnitsEdtx.setHint("Total No of Office");

                    propAreaEdtx.setHint("Office Area");
                    propTitleEdtx.setHint("Office Property Title");
                    propDescEdtx.setHint("Office Property Description");
                    propAreaTitleTv.setText("Office Area");

                } else if (propTypeStr.matches("Commercial Complex")) {

                    noOfUnitsTitleTv.setText("Total No of Commercial Complex");
                    noOfUnitsEdtx.setHint("Total No of Commercial Complex");

                    propAreaEdtx.setHint("Commercial Complex Area");
                    propTitleEdtx.setHint("Commercial Complex Title");
                    propDescEdtx.setHint("Commercial Complex Description");
                    propAreaTitleTv.setText("Commercial Complex Area");

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

                    noOfUnitsTitleTv.setText("Total No of Commercial Property Units");
                    noOfUnitsEdtx.setHint("Total No of Commercial Property Units");

                    propAreaEdtx.setHint("Commercial Property Area");
                    propTitleEdtx.setHint("Commercial Property Title");
                    propDescEdtx.setHint("Commercial Property Description");
                    propAreaTitleTv.setText("Commercial Property Area");

                }

                floorTitleTv.setText("No. of Floors");

                floorLevelList.add("Select Floors");
                floorLevelList.add("Three Floors");
                floorLevelList.add("Four Floors");
                floorLevelList.add("Five Floors");
                floorLevelList.add("Six Floors");
                floorLevelList.add("Seven Floors");
                floorLevelList.add("Eight Floors");
                floorLevelList.add("Nine Floors");
                floorLevelList.add("Ten Floors");
                floorLevelList.add("Ten+ Floors");

                final ArrayAdapter<String> spinnerArrayAdapterFloor = new ArrayAdapter<String>(activity, android.R.layout.simple_spinner_item, floorLevelList);
                spinnerArrayAdapterFloor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                floorLevelSpin.setAdapter(spinnerArrayAdapterFloor);

                propAreaSpnList.add("Select Area");
                propAreaSpnList.add("Guntha");
                propAreaSpnList.add("Sqft");
                propAreaSpnList.add("Sqmtr");

                final ArrayAdapter<String> spinAdapterPropArea = new ArrayAdapter<String>(activity, android.R.layout.simple_spinner_item, propAreaSpnList);
                spinAdapterPropArea.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                propAreaSpn.setAdapter(spinAdapterPropArea);
            }

        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof Activity) {
            activity = (FragmentActivity) context;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        Log.d("MAPRESULTLOG", "ACTIVITY'S RESULT");

        super.onActivityResult(requestCode, resultCode, data);

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
            verifyLocationBtn.setText("Verified");
            verifyLocationBtn.setError(null);
            propLocationEdtx.setEnabled(false);
            propLocationEdtx.setAlpha(0.8f);
            cityEdtx.setEnabled(false);
            cityEdtx.setAlpha(0.8f);
        }


        Log.d("MAPRESULTLOG", "Latitude =" + " " + latitudeStr + "\nLongitude =" + " " + longitudeStr + "\nGeo Address =" + geoAddresStr);
    }



    private void postProperty(String propertyType) {

        Toast.makeText(activity, "Prop Type :" + " " + propertyType, Toast.LENGTH_LONG).show();

        // -------- Universal Common Properties of Sell Property ---------

        String location = propLocationEdtx.getText().toString().trim();
        String city = cityEdtx.getText().toString().trim();

        /*String totalNoOfFlatsStr = "";
        totalNoOfFlatsStr = noOfUnitsEdtx.getText().toString().trim();*/

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

        } else if (areaMeasurementType.matches("Select Area")) {

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

            if (propertyType.matches("Apartment")) {

                String bedroomStr = "";

                for (int i = 0; i < lstcategoryBedrooms.size(); i++) {

                    if (lstcategoryBedrooms.get(i).isSelected()) {
                        bedroomStr = lstcategoryBedrooms.get(i).getBedroom();
                    }
                }

                String floor = floorLevelSpin.getSelectedItem().toString().trim();

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

                /*String saleType = "";

                if (newPropFlag) {
                    saleType = "New";
                } else if (resalePropFlag) {
                    saleType = "Resale";
                }*/

                String societyStr = "";
                societyStr = societySpin.getSelectedItem().toString().trim();

                String builderName = "";
                builderName = builderEdtx.getText().toString();

                String maintainanceChrgs = maintainanceChrgsInput.getEditText().getText().toString().trim();
                String maintainanceChrgsType = maintainanceChrgesSpn.getSelectedItem().toString().trim();

                String reraRegistrationNum = "";
                if (reraStatusStr.matches("RERA Approved")) {
                    reraRegistrationNum = reraRegistrationNumEdtx.getText().toString().trim();
                }

                String totalNoOfTowers = "";
                for(int i = 0; i < lstcategoryTower.size(); i++){

                    if(lstcategoryTower.get(i).isSelected()) {
                        totalNoOfTowers = lstcategoryTower.get(i).getTitle();
                    }
                }

                String totalNoOfFlats = noOfUnitsEdtx.getText().toString();

                // ---------------- Residential and commercial common validations ------------------

                if(totalNoOfFlats.matches("")){

                    noOfUnitsEdtx.requestFocus();
                    noOfUnitsEdtx.setError("Enter Total Number of flats!");
                    noOfUnitsTitleTv.getParent().requestChildFocus(noOfUnitsTitleTv, noOfUnitsTitleTv);

                } else if (totalNoOfFlats.matches("0")){

                    noOfUnitsEdtx.requestFocus();
                    noOfUnitsEdtx.setError("Total Number of flats cannot be zero!");
                    noOfUnitsTitleTv.getParent().requestChildFocus(noOfUnitsTitleTv, noOfUnitsTitleTv);

                } else if (bedroomStr.matches("")) {

                    bedroomsTitleTv.requestFocus();
                    scrollView.scrollTo(0, bedroomsTitleTv.getTop());
                    displayWarning("Select Bedrom");

                } else if (floor.matches("Select Floor")) {

                    floorTitleTv.requestFocus();
                    scrollView.scrollTo(0, floorTitleTv.getBottom());
                    displayWarning("Select Floor");

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

                } else if (societyStr.matches("Select Society")) {

                    societySpin.requestFocus();
                    //scrollView.scrollTo(0, societySpin.getTop());
                    societySpin.getParent().requestChildFocus(societySpin, societySpin);
                    displayWarning("Select Society");

                } else if(builderName.matches("")){

                    builderEdtx.requestFocus();
                    //scrollView.scrollTo(0, societySpin.getTop());
                    builderEdtx.getParent().requestChildFocus(builderEdtx, societySpin);
                    displayWarning("Enter builder name!");
                    builderEdtx.setError("Enter Builder Name!");
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

                    Toast.makeText(activity, "Submiting Flat", Toast.LENGTH_SHORT).show();

                    Log.d("SUBMIT_FLAT_LOG", "\n\n ---------------- Flat ------------------------ \n\n");

                    Log.d("SUBMIT_FLAT_LOG", "Location =" + " " + location);
                    Log.d("SUBMIT_FLAT_LOG", "Latitude =" + " " + latitudeStr);
                    Log.d("SUBMIT_FLAT_LOG", "Longitude =" + " " + longitudeStr);
                    Log.d("SUBMIT_FLAT_LOG", "City =" + " " + city);
                    Log.d("SUBMIT_FLAT_LOG", "Address =" + " " + googleAddressStr);
                    Log.d("SUBMIT_FLAT_LOG", "No of Flats =" + " " + totalNoOfFlats);
                    Log.d("SUBMIT_FLAT_LOG", "Bedroom =" + " " + bedroomStr);
                    Log.d("SUBMIT_FLAT_LOG", "Floor =" + " " + floor);
                    Log.d("SUBMIT_FLAT_LOG", "Total No of Towers =" + " " + totalNoOfTowers);
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

                    //Log.d("SUBMIT_FLAT_LOG", "Sale Type =" + " " + saleType);
                    Log.d("SUBMIT_FLAT_LOG", "Society =" + " " + societyStr);
                    Log.d("SUBMIT_FLAT_LOG", "Builder Name =" + " " + builderName);
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

                String totalNoOfHouseVilla = noOfUnitsEdtx.getText().toString();

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

                // ---- Residential Exclusive Logical Properties for House & Villa ----

                String builderName = "";
                builderName = builderEdtx.getText().toString();

                String floor = floorLevelSpin.getSelectedItem().toString().trim();

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

                /*String saleType = "";

                if (newPropFlag) {
                    saleType = "New";
                } else if (resalePropFlag) {
                    saleType = "Resale";
                }*/

                String maintainanceChrgs = maintainanceChrgsInput.getEditText().getText().toString().trim();
                String maintainanceChrgsType = maintainanceChrgesSpn.getSelectedItem().toString().trim();

                String reraRegistrationNum = "";
                if (reraStatusStr.matches("RERA Approved")) {
                    reraRegistrationNum = reraRegistrationNumEdtx.getText().toString().trim();
                }


                // ---------------- House/Villa validations ------------------

                if(totalNoOfHouseVilla.matches("")){

                    noOfUnitsEdtx.requestFocus();
                    noOfUnitsEdtx.setError("Enter Total Number of House/Vila");
                    noOfUnitsTitleTv.getParent().requestChildFocus(noOfUnitsTitleTv, noOfUnitsTitleTv);

                } else if(totalNoOfHouseVilla.matches("0")){

                    noOfUnitsEdtx.requestFocus();
                    noOfUnitsEdtx.setError("No of House/Villa can't be zero");
                    noOfUnitsTitleTv.getParent().requestChildFocus(noOfUnitsTitleTv, noOfUnitsTitleTv);

                } else if (houseRooms.matches("")) {

                    bedroomsTitleTv.requestFocus();
                    scrollView.scrollTo(0, bedroomsTitleTv.getTop());
                    displayWarning("Select Bedrom");

                } else if (floor.matches("Select Floor")) {

                    floorTitleTv.requestFocus();
                    scrollView.scrollTo(0, floorTitleTv.getBottom());
                    displayWarning("Select Floor");

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

                }  else if(builderName.matches("")){

                    builderEdtx.requestFocus();
                    builderEdtx.getParent().requestChildFocus(builderEdtx, societySpin);
                    displayWarning("Enter builder name!");
                    builderEdtx.setError("Enter Builder Name!");

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

                    Log.d("SUBMIT_FLAT_LOG", "Location =" + " " + location);
                    Log.d("SUBMIT_FLAT_LOG", "Latitude =" + " " + latitudeStr);
                    Log.d("SUBMIT_FLAT_LOG", "Longitude =" + " " + longitudeStr);
                    Log.d("SUBMIT_FLAT_LOG", "City =" + " " + city);
                    Log.d("SUBMIT_FLAT_LOG", "Address =" + " " + googleAddressStr);
                    Log.d("SUBMIT_FLAT_LOG", "Total No of House/Villa =" + " " + totalNoOfHouseVilla);
                    Log.d("SUBMIT_FLAT_LOG", "Bedroom =" + " " + houseRooms);
                    Log.d("SUBMIT_FLAT_LOG", "Floor =" + " " + floor);
                    Log.d("SUBMIT_FLAT_LOG", "Type of House/Villa =" + " " + typeOfHouseVilla);

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

                    //Log.d("SUBMIT_FLAT_LOG", "Sale Type =" + " " + saleType);
                    Log.d("SUBMIT_FLAT_LOG", "Builder Name =" + " " + builderName);
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

                String totalNoOfNAPlotStr = noOfUnitsEdtx.getText().toString();

                // ---------------- NA Plot validations ------------------

                if(totalNoOfNAPlotStr.matches("")){

                    noOfUnitsEdtx.requestFocus();
                    noOfUnitsEdtx.setError("Enter Total Number of Plots");
                    noOfUnitsTitleTv.getParent().requestChildFocus(noOfUnitsTitleTv, noOfUnitsTitleTv);

                } else if(totalNoOfNAPlotStr.matches("0")){

                    noOfUnitsEdtx.requestFocus();
                    noOfUnitsEdtx.setError("Total no of plots cannot be zero!");
                    noOfUnitsTitleTv.getParent().requestChildFocus(noOfUnitsTitleTv, noOfUnitsTitleTv);

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

                } else {

                    Toast.makeText(activity, "Submitting NA Plot", Toast.LENGTH_SHORT).show();

                    Log.d("SUBMIT_FLAT_LOG", "\n\n --------------- NA Plot ------------------------ \n\n");

                    Log.d("SUBMIT_FLAT_LOG", "Location =" + " " + location);
                    Log.d("SUBMIT_FLAT_LOG", "Latitude =" + " " + latitudeStr);
                    Log.d("SUBMIT_FLAT_LOG", "Longitude =" + " " + longitudeStr);
                    Log.d("SUBMIT_FLAT_LOG", "City =" + " " + city);
                    Log.d("SUBMIT_FLAT_LOG", "Address =" + " " + googleAddressStr);
                    Log.d("SUBMIT_FLAT_LOG", "Total No of NA Plot =" + " " + totalNoOfNAPlotStr);
                    Log.d("SUBMIT_FLAT_LOG", "Type of NA Plot =" + " " + typeOfNA_Plot);
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

            } else if (propertyType.matches("Agricultural Project")) {

                //Toast.makeText(activity, "Submitting Agricultural Land", Toast.LENGTH_SHORT).show();

                String totalNoOfLandStr = noOfUnitsEdtx.getText().toString().trim();
                String agriProjectType = agriProjectSpin.getSelectedItem().toString().trim();

                if(totalNoOfLandStr.matches("")){

                    noOfUnitsEdtx.requestFocus();
                    noOfUnitsEdtx.setError("Enter Total Number of Lands");
                    noOfUnitsTitleTv.getParent().requestChildFocus(noOfUnitsTitleTv, noOfUnitsTitleTv);

                } else if(totalNoOfLandStr.matches("0")){

                    noOfUnitsEdtx.requestFocus();
                    noOfUnitsEdtx.setError("Total no of lands cannot be zero!");
                    noOfUnitsTitleTv.getParent().requestChildFocus(noOfUnitsTitleTv, noOfUnitsTitleTv);

                } else {

                    Log.d("SUBMIT_FLAT_LOG", "\n\n --------------- Agricultural Land ------------------------ \n\n");

                    Log.d("SUBMIT_FLAT_LOG", "Location =" + " " + location);
                    Log.d("SUBMIT_FLAT_LOG", "Latitude =" + " " + latitudeStr);
                    Log.d("SUBMIT_FLAT_LOG", "Longitude =" + " " + longitudeStr);
                    Log.d("SUBMIT_FLAT_LOG", "City =" + " " + city);
                    Log.d("SUBMIT_FLAT_LOG", "Address =" + " " + googleAddressStr);
                    Log.d("SUBMIT_FLAT_LOG", "Land Area =" + " " + areaStr);
                    Log.d("SUBMIT_FLAT_LOG", "Total No of Lands =" + " " + totalNoOfLandStr);
                    Log.d("SUBMIT_FLAT_LOG", "Agri Project Type =" + " " + agriProjectType);
                    Log.d("SUBMIT_FLAT_LOG", "Land Area Type =" + " " + areaMeasurementType);
                    Log.d("SUBMIT_FLAT_LOG", "Land Title =" + " " + propTitle);
                    Log.d("SUBMIT_FLAT_LOG", "Land Descripion =" + " " + propDescription);
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

            } else if (propertyType.matches("Commercial Complex")) {

                // ---- Office Space Exclusive Logical Properties -----
                String floor = floorLevelSpin.getSelectedItem().toString().trim();
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

                /*String saleType = "";

                if (newPropFlag) {
                    saleType = "New";
                } else if(resalePropFlag){
                    saleType = "Resale";
                }*/

                String maintainanceChrgs = maintainanceChrgsInput.getEditText().getText().toString().trim();
                String maintainanceChrgsType = maintainanceChrgesSpn.getSelectedItem().toString().trim();

                String reraRegistrationNum = "";
                if (reraStatusStr.matches("RERA Approved")) {
                    reraRegistrationNum = reraRegistrationNumEdtx.getText().toString().trim();
                }

                String totalNoOfOffice = noOfUnitsEdtx.getText().toString().trim();

                String builderName = builderEdtx.getText().toString().trim();

                String facing = "";

                for (int i = 0; i < lstcategoryFacing.size(); i++) {

                    if (lstcategoryFacing.get(i).isSelected()) {
                        facing = lstcategoryFacing.get(i).getTitle();
                    }
                }

                // ---------------- Office Space common validations ------------------

                if(totalNoOfOffice.matches("")) {

                    noOfUnitsEdtx.requestFocus();
                    noOfUnitsEdtx.setError("Enter Total Number of Office");
                    noOfUnitsTitleTv.getParent().requestChildFocus(noOfUnitsTitleTv, noOfUnitsTitleTv);

                } else if(totalNoOfOffice.matches("0")){

                    noOfUnitsEdtx.requestFocus();
                    noOfUnitsEdtx.setError("Total no of office's cannot be zero!");
                    noOfUnitsTitleTv.getParent().requestChildFocus(noOfUnitsTitleTv, noOfUnitsTitleTv);

                } else if (floor.matches("Select Floors")) {

                    floorTitleTv.requestFocus();
                    scrollView.scrollTo(0, floorTitleTv.getBottom());
                    displayWarning("Select Floor");

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

                } else if (facing.matches("")) {

                    rvFacing.requestFocus();
                    rvFacing.getParent().requestChildFocus(rvFacing, rvFacing);
                    displayWarning("Select Property Facing");

                } else if(builderName.matches("")){

                    builderEdtx.requestFocus();
                    builderEdtx.getParent().requestChildFocus(builderEdtx, societySpin);
                    displayWarning("Enter builder name!");
                    builderEdtx.setError("Enter Builder Name!");

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

                    Log.d("SUBMIT_FLAT_LOG", "Location =" + " " + location);
                    Log.d("SUBMIT_FLAT_LOG", "Latitude =" + " " + latitudeStr);
                    Log.d("SUBMIT_FLAT_LOG", "Longitude =" + " " + longitudeStr);
                    Log.d("SUBMIT_FLAT_LOG", "City =" + " " + city);
                    Log.d("SUBMIT_FLAT_LOG", "Address =" + " " + googleAddressStr);
                    Log.d("SUBMIT_FLAT_LOG", "Total No Of Office =" + " " + totalNoOfOffice);
                    Log.d("SUBMIT_FLAT_LOG", "Floor =" + " " + floor);

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

                    //Log.d("SUBMIT_FLAT_LOG", "Sale Type =" + " " + saleType);
                    Log.d("SUBMIT_FLAT_LOG", "Facing =" + " " + facing);
                    Log.d("SUBMIT_FLAT_LOG", "Builder Name =" + " " + builderName);
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

            } else if (propertyType.matches("Shop/Showroom")) {

                String facing = "";

                for (int i = 0; i < lstcategoryFacing.size(); i++) {

                    if (lstcategoryFacing.get(i).isSelected()) {
                        facing = lstcategoryFacing.get(i).getTitle();
                    }
                }

                // ---- Shop/Showroom Exclusive Logical Properties -----

                String floor = floorLevelSpin.getSelectedItem().toString().trim();

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

                /*String saleType = "";

                if (newPropFlag) {
                    saleType = "New";
                } else if(resalePropFlag){
                    saleType = "Resale";
                }*/

                String maintainanceChrgs = maintainanceChrgsInput.getEditText().getText().toString().trim();
                String maintainanceChrgsType = maintainanceChrgesSpn.getSelectedItem().toString().trim();

                String reraRegistrationNum = "";
                if (reraStatusStr.matches("RERA Approved")) {
                    reraRegistrationNum = reraRegistrationNumEdtx.getText().toString().trim();
                }

                String totalNoOfOffice = noOfUnitsEdtx.getText().toString().trim();
                String builderName = builderEdtx.getText().toString().trim();



                // ---------------- Office Space common validations ------------------

                if(totalNoOfOffice.matches("")) {

                    noOfUnitsEdtx.requestFocus();
                    noOfUnitsEdtx.setError("Enter Total Number of shop/showrooms!");
                    noOfUnitsTitleTv.getParent().requestChildFocus(noOfUnitsTitleTv, noOfUnitsTitleTv);

                } else if(totalNoOfOffice.matches("0")){

                    noOfUnitsEdtx.requestFocus();
                    noOfUnitsEdtx.setError("Total no of shop/showrooms cannot be zero!");
                    noOfUnitsTitleTv.getParent().requestChildFocus(noOfUnitsTitleTv, noOfUnitsTitleTv);

                } else if (floor.matches("Select Floors")) {

                    floorTitleTv.requestFocus();
                    scrollView.scrollTo(0, floorTitleTv.getBottom());
                    displayWarning("Select Floor");

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

                } else if (facing.matches("")) {

                    rvFacing.requestFocus();
                    rvFacing.getParent().requestChildFocus(rvFacing, rvFacing);
                    displayWarning("Select Property Facing");

                } else if(builderName.matches("")){

                    builderEdtx.requestFocus();
                    builderEdtx.getParent().requestChildFocus(builderEdtx, societySpin);
                    displayWarning("Enter builder name!");
                    builderEdtx.setError("Enter Builder Name!");

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

                    Toast.makeText(activity, "Submitting Shop/Showroom", Toast.LENGTH_SHORT).show();

                    Log.d("SUBMIT_FLAT_LOG", "\n\n --------------- Shop/Showroom ------------------------ \n\n");

                    Log.d("SUBMIT_FLAT_LOG", "Location =" + " " + location);
                    Log.d("SUBMIT_FLAT_LOG", "Latitude =" + " " + latitudeStr);
                    Log.d("SUBMIT_FLAT_LOG", "Longitude =" + " " + longitudeStr);
                    Log.d("SUBMIT_FLAT_LOG", "City =" + " " + city);
                    Log.d("SUBMIT_FLAT_LOG", "Address =" + " " + googleAddressStr);
                    Log.d("SUBMIT_FLAT_LOG", "Total No of Commercial Complex =" + " " + totalNoOfOffice);
                    Log.d("SUBMIT_FLAT_LOG", "Floor =" + " " + floor);

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

                    Log.d("SUBMIT_FLAT_LOG", "Facing =" + " " + facing);
                    //Log.d("SUBMIT_FLAT_LOG", "Sale Type =" + " " + saleType);
                    Log.d("SUBMIT_FLAT_LOG", "Builder Name =" + " " + builderName);
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

                Toast.makeText(activity, "Inside Other Commercial Office", Toast.LENGTH_SHORT).show();

                // ---- Office Space Exclusive Logical Properties -----

                String floor = floorLevelSpin.getSelectedItem().toString().trim();

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

                /*String saleType = "";

                if (newPropFlag) {
                    saleType = "New";
                } else if(resalePropFlag){
                    saleType = "Resale";
                }*/

                String maintainanceChrgs = maintainanceChrgsInput.getEditText().getText().toString().trim();
                String maintainanceChrgsType = maintainanceChrgesSpn.getSelectedItem().toString().trim();

                String reraRegistrationNum = "";
                if (reraStatusStr.matches("RERA Approved")) {
                    reraRegistrationNum = reraRegistrationNumEdtx.getText().toString().trim();
                }

                String totalNoOfCommercialOffice = noOfUnitsEdtx.getText().toString().trim();
                String builderName = builderEdtx.getText().toString().trim();

                // ---------------- Other Commercial common validations ------------------

                if(totalNoOfCommercialOffice.matches("")) {

                    noOfUnitsEdtx.requestFocus();
                    noOfUnitsEdtx.setError("Enter Total Number of commercial complex!");
                    noOfUnitsTitleTv.getParent().requestChildFocus(noOfUnitsTitleTv, noOfUnitsTitleTv);

                } else if(totalNoOfCommercialOffice.matches("0")){

                    noOfUnitsEdtx.requestFocus();
                    noOfUnitsEdtx.setError("Total no of commercial complex cannot be zero!");
                    noOfUnitsTitleTv.getParent().requestChildFocus(noOfUnitsTitleTv, noOfUnitsTitleTv);

                } else if (floor.matches("Select Floors")) {

                    floorTitleTv.requestFocus();
                    scrollView.scrollTo(0, floorTitleTv.getBottom());
                    displayWarning("Select Floor");

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

                } else if(builderName.matches("")){

                    builderEdtx.requestFocus();
                    builderEdtx.getParent().requestChildFocus(builderEdtx, societySpin);
                    displayWarning("Enter builder name!");
                    builderEdtx.setError("Enter Builder Name!");

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


                    Log.d("SUBMIT_FLAT_LOG", "Location =" + " " + location);
                    Log.d("SUBMIT_FLAT_LOG", "Latitude =" + " " + latitudeStr);
                    Log.d("SUBMIT_FLAT_LOG", "Longitude =" + " " + longitudeStr);
                    Log.d("SUBMIT_FLAT_LOG", "City =" + " " + city);
                    Log.d("SUBMIT_FLAT_LOG", "Address =" + " " + googleAddressStr);
                    Log.d("SUBMIT_FLAT_LOG", "Total No of Other Commercial Complexes =" + " " + totalNoOfCommercialOffice);
                    Log.d("SUBMIT_FLAT_LOG", "Floor =" + " " + floor);

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

                    //Log.d("SUBMIT_FLAT_LOG", "Sale Type =" + " " + saleType);
                    Log.d("SUBMIT_FLAT_LOG", "Builder Name =" + " " + builderName);
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






















