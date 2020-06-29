package com.example.realestate.RealEstate;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.developer.kalert.KAlertDialog;
import com.example.realestate.CCComponent.DialogLocationSelector;
import com.example.realestate.CCComponent.OnCcEmpty;
import com.example.realestate.CCComponent.Pojo_CC;
import com.example.realestate.CCComponent.RecyclerviewAdapterCC;
import com.example.realestate.GridSpacingDecoration;
import com.example.realestate.IOnBackPressed;
import com.example.realestate.Maps.MapsActivity;
import com.example.realestate.POJO_Main;
import com.example.realestate.POJO_PropList;
import com.example.realestate.R;
import com.example.realestate.RecyclerviewAdapter;
import com.example.realestate.TempProjectsActivity;
import com.google.android.gms.vision.text.Line;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.innovattic.rangeseekbar.RangeSeekBar;
import com.pchmn.materialchips.ChipsInput;
import com.pchmn.materialchips.model.ChipInterface;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentTakeOnRent extends Fragment implements IOnBackPressed, DialogLocationSelector.OnLocationSelectionListener  {


    public FragmentTakeOnRent() {
        // Required empty public constructor
    }

    View rootview;
    FragmentActivity activity;
    Context mContext;

    //private Button bachelors, family;

    // BOTTOMSHEET

    private RelativeLayout bottomSheetRelativeLayout;
    BottomSheetBehavior sheetBehavior;

    private boolean stateStatus = false;

    private LinearLayout filterBtnLayout;
    private LinearLayout closeFiltersLin;

    int spanCount = 3; // 3 columns
    int spacing = 25; // 50px
    boolean includeEdge = true;


    @Override
    public boolean onBackPressed() {

        //Toast.makeText(activity, "stateStatus =" + " " + stateStatus, Toast.LENGTH_SHORT).show();

        if (stateStatus) {
            sheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
            stateStatus = false;
            return false;
        } else {
            return true;
        }
    }


    private RecyclerviewAdapterBedrooms adapterBedrooms;
    private RecyclerView recyclerViewBedrooms;
    private ArrayList<POJO_bedrooms> lstcategoryBedrooms;

    //private Button underConstruction, readyToMove;
    //private boolean underConStatus = false, readyToMvStatus = false;

    private ArrayList<String> monthPossesList = new ArrayList<>();
    private ArrayList<String> yearPossesList = new ArrayList<>();
    private Spinner monthPosSpin, yearPosSPin;

    private RecyclerviewAdapterAmenities recyclerViewAdapterAmenities;
    private ArrayList<POJO_Amenities> lstcategoryAmenities;
    private RecyclerView recyclerViewAmenities;

    private Spinner societySpin;
    private ArrayList<String> societyList = new ArrayList<>();

    //private ChipsInput chipsInput;

    private Spinner propAreaSpn;// propAreaMinSpn, propAreaMaxSpn;
    private ArrayList<String> areaList = new ArrayList<>();

    private RecyclerviewAdapterTextOpts facingAdapter;
    private ArrayList<Pojo_TextOpts> lstcategoryFacing;

    private RecyclerView rvBathroom;
    private RecyclerviewAdapterTextOpts bathroomAdapter;
    private ArrayList<Pojo_TextOpts> lstcategoryBathroom;

    private RecyclerView rvPostSince;
    private RecyclerviewAdapterTextOpts postSinceAdapter;
    private ArrayList<Pojo_TextOpts> lstcategorypostSince;

    private Button agentBtn, ownerBtn, builderBtn;

    private boolean agengtFlag = false, ownerFlag = false, builderFlag = false;

    private RecyclerView recyclerViewAllProj;
    private RecyclerviewAdapter adapterAllProj;
    private ArrayList<POJO_Main> lstcategoryAllProj;
    private ArrayList<POJO_PropList> lstcategoryInsiderAllProj;

    private String propTypeStr = null;

    private TextView bedroomsTitleTv, bathroomTitleTv, amenitiesTitleTv, propAreaTitleTv, furnishingTitleTv;
    private TextView budgetTitleTv, possessionTitleTv, societyTitleTv, postedSinceTitleTv;  //  balconyTitleTv
    private TextView postedByTitleTv, showOnlyTitleTv, facingTitleTv, commercPropDetailsTitleTv, floorTitleTv;
    private TextView typeOfHouseVillaTitlteTv, typeOfPlotTitleTv, tenantsTypeTitleTv, agreementTypeTitleTv;

    private RelativeLayout floorSpinConainer, societySpinContainer, floorContainer;
    private LinearLayout maintainanceChrgsContainer, possessionContainer, postedByContainer;
    private LinearLayout comPropDetailContainer, receptionContainer, funrnishingContainer, agreementTypeContainer;
    private LinearLayout typeOfHouseVillaContainer, typeOfPlotContainer, tenantsTypeContainer;

    private Button bachelorsBtn, familyBtn;
    private EditText mintainanceChargesEdtx;

    //private CheckBox otherPropChkbox;

    private Spinner cabinSpin, conferenceSpin, receptionSpin;

    private RangeSeekBar rangeSeekBar, rangeDepositSeekbar;
    private TextView minBudgetTv, maxBudgetTv, minDepositTv, maxDepositTv;

    private RangeSeekBar rangeSeekBarArea;
    private TextView minAreaTv, maxAreaTv;

    private RecyclerView rvFacing;

    private ArrayList<String> cabinSpinList = new ArrayList<>();
    private ArrayList<String> conferenceSpinList = new ArrayList<>();
    private ArrayList<String> receptionSpinList = new ArrayList<>();

    private Spinner floorLevelSpin;
    private ArrayList<String> floorLevelList = new ArrayList<>();

    private boolean bachelorsFlag = false;
    private boolean familyFlag = false;

    private LinearLayout selectLocationBtn;
    private Button doneLocationSelectionBtn;
    private RecyclerView rvSelectedLocations;
    private RecyclerView rvLocationList;
    private EditText locationsSearchEdtx;
    private TextView currentLocationTv;
    private ImageView currentLocationIcon;

    private EditText minAreaEdtx, maxAreaEdtx;
    private CheckBox refundableChkBox, nonRefundableChkBox;

    private int minBudget = 10, maxBudget = 25;
    private int minDeposite = 10, maxDeposite = 25;

    private boolean isExclusivePropChecked = false, isVerifiedPropChecked = false, isPropsWithDiscsAndOffChecked = false;

    private RecyclerView rvLocations;
    private RecyclerviewAdapterCC adapterLocationChips;
    private ArrayList<Pojo_CC> locationsList;

    private TextView addLocationsTv;
    private ImageView addLocationImg;
    private TextView applyFilterTv;

    private boolean wantToInclUnfurnished = true, wantInclSemiFurnished = false, wantInclFullyFurnished = false;
    private CheckBox unfurnishedChkbx, semiFurnishedChkbx, fullyFurnishedChkbx;

    private boolean rentalAgreementType = true, llAgreementType = false;
    private CheckBox rentalAgreementChkbx, llAgreementChkbx;

    private boolean refundableFlag = false, nonRefundableFlag = false;

    private boolean rccHouseVilla = true, loadBearingHouseVilla = false, otherHouseVilla = false;
    private CheckBox rccHouseVillaChkbx, loadBearingChkbx, otherChkbx;

    private boolean recidentialPlotFlag = true, commercialPlotFlag = false, industrialPlotFlag = false;
    private CheckBox recidentialPlotChkbx, commercialPlotChkbx, industrialPlotChkbx;

    private CheckBox exclusivePropsChkbx, verifiedPropsChkbx, propsWithDealsAndDiscsChkbx;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootview = inflater.inflate(R.layout.fragment_take_on_rent, container, false);

        Bundle bundle = getArguments();
        propTypeStr = bundle.getString("PROP_TYPE");

        intializeViews();

        lstcategoryBedrooms = new ArrayList<>();
        lstcategoryAmenities = new ArrayList<>();
        lstcategoryFacing = new ArrayList<>();
        lstcategoryBathroom = new ArrayList<>();
        lstcategorypostSince = new ArrayList<>();

        lstcategoryAllProj = new ArrayList<>();
        lstcategoryInsiderAllProj = new ArrayList<>();

        lstcategoryFacing = new ArrayList<>();
        locationsList = new ArrayList<>();


        setupViews();


        customizePropTypes();


        return rootview;
    }

    private void setupViews() {

        sheetBehavior = BottomSheetBehavior.from(bottomSheetRelativeLayout);

        sheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);

        filterBtnLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (stateStatus) {
                    sheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                    stateStatus = false;
                } else {
                    sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    stateStatus = true;
                }
            }
        });

        closeFiltersLin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                stateStatus = false;
            }
        });

        sheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {

                switch (newState) {
                    case BottomSheetBehavior.STATE_HIDDEN:
                        stateStatus = false;
                        break;

                    case BottomSheetBehavior.STATE_EXPANDED:


                        break;

                    case BottomSheetBehavior.STATE_COLLAPSED:
                        //Toast.makeText(activity, "Collapsed", Toast.LENGTH_SHORT).show();
                        //sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                        break;

                    case BottomSheetBehavior.STATE_DRAGGING:
                        sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                        //Toast.makeText(activity, "Dragging", Toast.LENGTH_SHORT).show();
                        break;

                    case BottomSheetBehavior.STATE_SETTLING:
                        //Toast.makeText(activity, "Settling", Toast.LENGTH_SHORT).show();
                        //sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                        break;
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });

        refundableChkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    nonRefundableChkBox.setEnabled(false);
                    nonRefundableChkBox.setAlpha(0.5f);
                } else {
                    nonRefundableChkBox.setEnabled(true);
                    nonRefundableChkBox.setAlpha(1);
                }

            }
        });

        nonRefundableChkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    refundableChkBox.setEnabled(false);
                    refundableChkBox.setAlpha(0.5f);
                } else {
                    refundableChkBox.setEnabled(true);
                    refundableChkBox.setAlpha(1);
                }

            }
        });

        minAreaEdtx.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void afterTextChanged(Editable s) {

                int min = rangeSeekBarArea.getMinRange();
                int max = rangeSeekBarArea.getMax();

                if (String.valueOf(s).length() > 0) {

                    int val = Integer.parseInt(String.valueOf(s));

                    if (val > max) {
                        minAreaEdtx.setText(String.valueOf(max));
                        rangeSeekBarArea.setMinThumbValue(max);
                        Toasty.error(activity, "Maximum value exceeded", Toasty.LENGTH_SHORT).show();

                    } else {
                        rangeSeekBarArea.setMinThumbValue(Integer.parseInt(String.valueOf(s)));
                        minAreaTv.setText(Integer.parseInt(String.valueOf(s)) + " " + propAreaSpn.getSelectedItem());
                    }

                  /*  if(Integer.parseInt(String.valueOf(s)) < max) {

                        rangeSeekBarArea.setMinThumbValue(Integer.parseInt(String.valueOf(s)));
                        minAreaTv.setText(Integer.parseInt(String.valueOf(s)) + " " + propAreaSpn.getSelectedItem());
                    } else {
                        rangeSeekBarArea.setMinThumbValue(max);
                        minAreaTv.setText(Integer.parseInt(String.valueOf(s)) + " " + propAreaSpn.getSelectedItem());
                    }*/
                }
            }
        });

        maxAreaEdtx.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void afterTextChanged(Editable s) {

                int min = rangeSeekBarArea.getMinRange();
                int max = rangeSeekBarArea.getMax();

                if (String.valueOf(s).length() > 0) {

                    int val = Integer.parseInt(String.valueOf(s));

                    if (val > max) {

                        maxAreaEdtx.setText(String.valueOf(max));
                        rangeSeekBarArea.setMaxThumbValue(max);
                        Toasty.error(activity, "Maximum value exceeded", Toasty.LENGTH_SHORT).show();

                        if (Integer.parseInt(minAreaEdtx.getText().toString()) < val) {
                            rangeSeekBarArea.setMinThumbValue(Integer.parseInt(minAreaEdtx.getText().toString()));
                        }

                    } else {
                        rangeSeekBarArea.setMaxThumbValue(Integer.parseInt(String.valueOf(s)));
                        maxAreaTv.setText(Integer.parseInt(String.valueOf(s)) + " " + propAreaSpn.getSelectedItem());

                        if (Integer.parseInt(minAreaEdtx.getText().toString()) < val) {
                            rangeSeekBarArea.setMinThumbValue(Integer.parseInt(minAreaEdtx.getText().toString()));
                        }
                    }

                   /* if(Integer.parseInt(String.valueOf(s)) < max) {

                        rangeSeekBarArea.setMaxThumbValue(Integer.parseInt(String.valueOf(s)));
                        maxAreaTv.setText(Integer.parseInt(String.valueOf(s)) + " " + propAreaSpn.getSelectedItem());
                    } else {
                        rangeSeekBarArea.setMaxThumbValue(max);
                        maxAreaTv.setText(Integer.parseInt(String.valueOf(s)) + " " + propAreaSpn.getSelectedItem());
                    }*/
                }
            }
        });

        rangeSeekBar.setMinRange(1);
        rangeSeekBar.setMax(100);

        rangeSeekBar.setMinThumbValue(10);

        rangeSeekBar.setMaxThumbValue(25);

        rangeSeekBar.setSeekBarChangeListener(new RangeSeekBar.SeekBarChangeListener() {
            @Override
            public void onStartedSeeking() {

            }

            @Override
            public void onStoppedSeeking() {

            }

            @Override
            public void onValueChanged(int i, int i1) {

                DecimalFormat decimalFormat = new DecimalFormat("#.00");

                minBudget = i;
                maxBudget = i1;

                if (i > 99) {
                    minBudgetTv.setText(String.valueOf(i).charAt(0) + "." + String.valueOf(i).substring(1, String.valueOf(i).length() - 1) + " " + "Lakh");
                } else {
                    minBudgetTv.setText(String.valueOf(i) + " " + "Thousand");
                }

                if (i1 > 99) {
                    maxBudgetTv.setText(String.valueOf(i1).charAt(0) + "." + String.valueOf(i1).substring(1, String.valueOf(i1).length() - 1) + " " + "Lakh");
                } else {
                    maxBudgetTv.setText(String.valueOf(i1) + " " + "Thousand");
                }
                //maxBudgetTv.setText(String.valueOf(i1) + " " + "Lakh");
                // Log.d("SEEKBARLOGCHECK", "MinValue =" + " " + i + " " + " " + " " + "MaxValue =" + " " + i1);
            }
        });

        rangeDepositSeekbar.setMinRange(1);
        rangeDepositSeekbar.setMax(100);

        rangeDepositSeekbar.setMinThumbValue(10);

        rangeDepositSeekbar.setMaxThumbValue(25);

        rangeDepositSeekbar.setSeekBarChangeListener(new RangeSeekBar.SeekBarChangeListener() {
            @Override
            public void onStartedSeeking() {

            }

            @Override
            public void onStoppedSeeking() {

            }

            @Override
            public void onValueChanged(int i, int i1) {

                DecimalFormat decimalFormat = new DecimalFormat("#.00");

                minDeposite = i;
                maxDeposite = i1;

                if (i > 99) {
                    minDepositTv.setText(String.valueOf(i).charAt(0) + "." + String.valueOf(i).substring(1, String.valueOf(i).length() - 1) + " " + "Lakh");
                } else {
                    minDepositTv.setText(String.valueOf(i) + " " + "Thousand");
                }

                if (i1 > 99) {
                    maxDepositTv.setText(String.valueOf(i1).charAt(0) + "." + String.valueOf(i1).substring(1, String.valueOf(i1).length() - 1) + " " + "Lakh");
                } else {
                    maxDepositTv.setText(String.valueOf(i1) + " " + "Thousand");
                }
                //maxBudgetTv.setText(String.valueOf(i1) + " " + "Lakh");
                // Log.d("SEEKBARLOGCHECK", "MinValue =" + " " + i + " " + " " + " " + "MaxValue =" + " " + i1);
            }
        });

        rangeSeekBarArea.setMinRange(1);
        rangeSeekBarArea.setMax(1000);

        minAreaTv.setText("1 Acre");
        maxAreaTv.setText("100 Acre");

        rangeSeekBarArea.setMinThumbValue(1);

        rangeSeekBarArea.setMaxThumbValue(100);

        //rangeSeekBarArea.setMax(2000);

        rangeSeekBarArea.setSeekBarChangeListener(new RangeSeekBar.SeekBarChangeListener() {
            @Override
            public void onStartedSeeking() {

            }

            @Override
            public void onStoppedSeeking() {

            }

            @Override
            public void onValueChanged(int i, int i1) {

                minAreaTv.setText(String.valueOf(i) + " " + "Acre");
                maxAreaTv.setText(String.valueOf(i1) + " " + "Acre");

                Log.d("SEEKBARLOGCHECK", "MinValue =" + " " + i + " " + " " + " " + "MaxValue =" + " " + i1);

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

        /*List<LocationChip> locationList = new ArrayList<>();
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

                if (contactsSelected.size() > 5) {
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
        });*/

        lstcategoryInsiderAllProj.add(new POJO_PropList("https://media.equityapartments.com/images/c_crop,x_0,y_0,w_1920,h_1080/c_fill,w_1920,h_1080/q_80/4147-23/the-hesby-apartments-exterior.jpg", "₹ 66 Lac", "2 BHK Flat", "VTP Hilife", "Vakad, Pune.", "Possession by Dec 2020", "Call Owner", "9503696428"));
        lstcategoryInsiderAllProj.add(new POJO_PropList("https://images.wallpaperscraft.com/image/living_room_design_flat_interior_design_style_metropolis_90530_1280x720.jpg", "₹ 72 Lac", "2 BHK Flat", "VTP Hilife", "Vakad, Pune.", "Possession by Dec 2020", "Call Owner", "9503696428"));
        lstcategoryInsiderAllProj.add(new POJO_PropList("https://images.wallpaperscraft.com/image/living_room_modern_design_interior_design_furniture_sofa_tv_70088_1280x720.jpg", "₹ 88 Lac", "2 BHK Flat", "VTP Hilife", "Vakad, Pune.", "Possession by Dec 2020", "Call Owner", "9503696428"));
        lstcategoryInsiderAllProj.add(new POJO_PropList("https://images.wallpaperscraft.com/image/living_room_desk_furniture_design_interior_design_modernism_25616_1280x720.jpg", "₹ 56 Lac", "2 BHK Flat", "VTP Hilife", "Vakad, Pune.", "Possession by Dec 2020", "Call Owner", "9503696428"));
        lstcategoryInsiderAllProj.add(new POJO_PropList("https://images.wallpaperscraft.com/image/apartments_living_room_bathroom_furniture_design_interior_108940_1280x720.jpg", "₹ 95 Lac", "2 BHK Flat", "VTP Hilife", "Vakad, Pune.", "Possession by Dec 2020", "Call Owner", "9503696428"));
        lstcategoryInsiderAllProj.add(new POJO_PropList("https://images.wallpaperscraft.com/image/living_room_furniture_style_design_39327_1280x720.jpg", "₹ 69 Lac", "2 BHK Flat", "VTP Hilife", "Vakad, Pune.", "Possession by Dec 2020", "Call Owner", "9503696428"));
        lstcategoryInsiderAllProj.add(new POJO_PropList("https://images.wallpaperscraft.com/image/living_room_furniture_cosiness_style_comfort_39308_1280x720.jpg", "₹ 78 Lac", "2 BHK Flat", "VTP Hilife", "Vakad, Pune.", "Possession by Dec 2020", "Call Owner", "9503696428"));
        lstcategoryInsiderAllProj.add(new POJO_PropList("https://images.wallpaperscraft.com/image/living_room_girl_furniture_style_comfort_39343_1280x720.jpg", "₹ 59 Lac", "2 BHK Flat", "VTP Hilife", "Vakad, Pune.", "Possession by Dec 2020", "Call Owner", "9503696428"));
        lstcategoryInsiderAllProj.add(new POJO_PropList("https://images.wallpaperscraft.com/image/living_room_furniture_style_interior_design_modern_39374_1280x720.jpg", "₹ 82 Lac", "2 BHK Flat", "VTP Hilife", "Vakad, Pune.", "Possession by Dec 2020", "Call Owner", "9503696428"));
        lstcategoryInsiderAllProj.add(new POJO_PropList("https://images.wallpaperscraft.com/image/living_room_sofa_furniture_interior_style_design_95260_1280x720.jpg", "₹ 80 Lac", "2 BHK Flat", "VTP Hilife", "Vakad, Pune.", "Possession by Dec 2020", "Call Owner", "9503696428"));
        lstcategoryInsiderAllProj.add(new POJO_PropList("https://images.wallpaperscraft.com/image/living_room_sofa_furniture_comfort_interior_75478_1280x720.jpg", "₹ 67 Lac", "2 BHK Flat", "VTP Hilife", "Vakad, Pune.", "Possession by Dec 2020", "Call Owner", "9503696428"));
        lstcategoryInsiderAllProj.add(new POJO_PropList("https://images.wallpaperscraft.com/image/living_room_furniture_sofa_table_comfort_design_70084_1280x720.jpg", "₹ 96 Lac", "2 BHK Flat", "VTP Hilife", "Vakad, Pune.", "Possession by Dec 2020", "Call Owner", "9503696428"));

        lstcategoryAllProj.add(new POJO_Main(lstcategoryInsiderAllProj, "Owner Properties"));
        lstcategoryAllProj.add(new POJO_Main(lstcategoryInsiderAllProj, "Fresh Properties For You"));
        lstcategoryAllProj.add(new POJO_Main(lstcategoryInsiderAllProj, "Hot Deals For You"));
        lstcategoryAllProj.add(new POJO_Main(lstcategoryInsiderAllProj, "Ready to Move Projects"));
        lstcategoryAllProj.add(new POJO_Main(lstcategoryInsiderAllProj, "Newly Launched Projects"));
        lstcategoryAllProj.add(new POJO_Main(lstcategoryInsiderAllProj, "Projects in Demand"));

        adapterAllProj = new RecyclerviewAdapter(activity, lstcategoryAllProj);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(activity);
        recyclerViewAllProj.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, true));
        recyclerViewAllProj.setAdapter(adapterAllProj);

        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {

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

//              final ArrayAdapter<String> spinnerArrayAdapterfrmYrSpn = new ArrayAdapter<String>(activity, android.R.layout.simple_spinner_item, frmYearPossesList);
//              spinnerArrayAdapterMin.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//              frmYearPosSpin.setAdapter(spinnerArrayAdapterfrmYrSpn);

                final ArrayAdapter<String> spinnerArrayAdapterfrmYrSpn = new ArrayAdapter<String>(activity, android.R.layout.simple_spinner_item, monthPossesList);
                spinnerArrayAdapterfrmYrSpn.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                monthPosSpin.setAdapter(spinnerArrayAdapterfrmYrSpn);

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

                lstcategoryBathroom.add(new Pojo_TextOpts("1", false));
                lstcategoryBathroom.add(new Pojo_TextOpts("2", false));
                lstcategoryBathroom.add(new Pojo_TextOpts("3", false));
                lstcategoryBathroom.add(new Pojo_TextOpts("4", false));
                lstcategoryBathroom.add(new Pojo_TextOpts("5", false));

                bathroomAdapter = new RecyclerviewAdapterTextOpts(activity, lstcategoryBathroom);

                int spanCountBathroom = 4; // 3 columns
                int spacingBathroom = 25; // 50px
                boolean includeEdgeBathroom = true;
                rvBathroom.addItemDecoration(new GridSpacingDecoration(spanCountBathroom, spacingBathroom, includeEdgeBathroom));
                GridLayoutManager gridLayoutManagerBathroom = new GridLayoutManager(activity, 4);
                rvBathroom.setLayoutManager(gridLayoutManagerBathroom);

                rvBathroom.setAdapter(bathroomAdapter);

                //lstcategorypostSince.add(new Pojo_TextOpts("Anytime", false));
                lstcategorypostSince.add(new Pojo_TextOpts("All", false));
                lstcategorypostSince.add(new Pojo_TextOpts("Today", false));
                lstcategorypostSince.add(new Pojo_TextOpts("Yesterday", false));
                lstcategorypostSince.add(new Pojo_TextOpts("Last Week", false));
                // lstcategorypostSince.add(new Pojo_TextOpts("Last 2 Weeks", false));
                lstcategorypostSince.add(new Pojo_TextOpts("Last 3 Weeks", false));
                lstcategorypostSince.add(new Pojo_TextOpts("Last Month", false));
                //lstcategorypostSince.add(new Pojo_TextOpts("Last 2 Months", false));
                lstcategorypostSince.add(new Pojo_TextOpts("Last 3 Months", false));
                //lstcategorypostSince.add(new Pojo_TextOpts("Last 4 Months", false));
                lstcategorypostSince.add(new Pojo_TextOpts("Last 4 Months +", false));

                postSinceAdapter = new RecyclerviewAdapterTextOpts(activity, lstcategorypostSince);

                int spanCountPostS = 4; // 3 columns
                int spacingPostS = 25; // 50px
                boolean includeEdgePostS = true;
                rvPostSince.addItemDecoration(new GridSpacingDecoration(spanCountPostS, spacingPostS, includeEdgePostS));
                GridLayoutManager gridLayoutManagerPostS = new GridLayoutManager(activity, 4);
                rvPostSince.setLayoutManager(gridLayoutManagerPostS);
                rvPostSince.setAdapter(postSinceAdapter);

                agentBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (agengtFlag) {
                            agentBtn.setBackgroundTintList(ContextCompat.getColorStateList(activity, R.color.white));
                            agengtFlag = false;
                        } else {
//                           if(ownerFlag){
//                               ownerFlag = false;
//                               ownerBtn.setBackgroundTintList(ContextCompat.getColorStateList(activity, R.color.white));
//                           }
//                           if(builderFlag){
//                               builderFlag = false;
//                               builderBtn.setBackgroundTintList(ContextCompat.getColorStateList(activity, R.color.white));
//                           }
                            agengtFlag = true;
                            agentBtn.setBackgroundTintList(ContextCompat.getColorStateList(activity, R.color.colorSelected));
                        }
                    }
                });


                builderBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (builderFlag) {
                            builderBtn.setBackgroundTintList(ContextCompat.getColorStateList(activity, R.color.white));
                            builderFlag = false;
                        } else {

//                           if(agengtFlag){
//                               agengtFlag = false;
//                               agentBtn.setBackgroundTintList(ContextCompat.getColorStateList(activity, R.color.white));
//                           }
//                           if(ownerFlag){
//                               ownerFlag = false;
//                               ownerBtn.setBackgroundTintList(ContextCompat.getColorStateList(activity, R.color.white));
//                           }

                            builderFlag = true;
                            builderBtn.setBackgroundTintList(ContextCompat.getColorStateList(activity, R.color.colorSelected));
                        }
                    }
                });


                ownerBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (ownerFlag) {
                            ownerBtn.setBackgroundTintList(ContextCompat.getColorStateList(activity, R.color.white));
                            ownerFlag = false;
                        } else {

//                           if(agengtFlag){
//                               agengtFlag = false;
//                               agentBtn.setBackgroundTintList(ContextCompat.getColorStateList(activity, R.color.white));
//                           }
//                           if(builderFlag){
//                               builderFlag = false;
//                               builderBtn.setBackgroundTintList(ContextCompat.getColorStateList(activity, R.color.white));
//                           }
                            ownerFlag = true;
                            ownerBtn.setBackgroundTintList(ContextCompat.getColorStateList(activity, R.color.colorSelected));
                        }
                    }
                });

                //private boolean tenantTypeFlag = false; // false = bachelors

                bachelorsBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (!bachelorsFlag) {
                            bachelorsFlag = true;
                            bachelorsBtn.setBackgroundResource(R.drawable.selected_button_background);
                        } else {
                            bachelorsBtn.setBackgroundResource(0);
                            bachelorsFlag = false;
                        }
                    }
                });


                familyBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (!familyFlag) {
                            familyFlag = true;
                            familyBtn.setBackgroundResource(R.drawable.selected_button_background);
                        } else {
                            familyFlag = false;
                            familyBtn.setBackgroundResource(0);
                        }
                    }
                });


            }
        });

        adapterLocationChips = new RecyclerviewAdapterCC(activity, locationsList, new OnCcEmpty() {
            @Override
            public void isCcEmpty(boolean isEmpty) {

                if (isEmpty) {
                    addLocationsTv.setVisibility(View.VISIBLE);
                } else {
                    addLocationsTv.setVisibility(View.GONE);
                }

            }
        });
        rvLocations.setLayoutManager(new LinearLayoutManager(activity));
        rvLocations.setAdapter(adapterLocationChips);

        addLocationImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Toast.makeText(activity, "locationsList.size() =" + " " + locationsList.size(), Toast.LENGTH_SHORT).show();

                if (locationsList.size() < 5) {

                    DialogLocationSelector dialog = new DialogLocationSelector();
                    Bundle bundle = new Bundle();
                    /*bundle.putStringArrayList("FILE_PATH_LIST", filePathsList);
                          bundle.putStringArrayList("FILE_NAME_LIST", fileNameList);
                          bundle.putStringArrayList("FILE_EXTENSION_LIST", fileExtensionList);
                          bundle.putStringArrayList("FILE_UPLOADING_STATUS_LIST", fileUploadStatusList);*/
                    //                    bundle.putSerializable("CCLIST_SERIALIZABLE", (Serializable) ccList);
//                    bundle.putString("CCTYPE", "APPROVERONE");
                    dialog.setArguments(bundle);
                    dialog.show(getChildFragmentManager(), "Add Location");

                } else {

                    new KAlertDialog(activity, KAlertDialog.WARNING_TYPE)
                            .setTitleText("Warning!")
                            .setContentText("You can add maximum of 5 locations!")
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
        });

        applyFilterTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                applyFilters(propTypeStr);

            }
        });

        unfurnishedChkbx.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    wantToInclUnfurnished = true;
                } else {
                    wantToInclUnfurnished = false;
                }
            }
        });

        semiFurnishedChkbx.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    wantInclSemiFurnished = true;
                } else {
                    wantInclSemiFurnished = false;
                }
            }
        });

        fullyFurnishedChkbx.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    wantInclFullyFurnished = true;
                } else {
                    wantInclFullyFurnished = false;
                }
            }
        });

        rentalAgreementChkbx.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    rentalAgreementType = true;
                } else {
                    rentalAgreementType = false;
                }
            }
        });

        llAgreementChkbx.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    llAgreementType = true;
                } else {
                    llAgreementType = false;
                }
            }
        });

        refundableChkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    refundableFlag = true;
                } else {
                    refundableFlag = false;
                }
            }
        });

        nonRefundableChkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    nonRefundableFlag = true;
                } else {
                    nonRefundableFlag = false;
                }
            }
        });


        rccHouseVillaChkbx.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    rccHouseVilla = true;
                } else {
                    rccHouseVilla = false;
                }
            }
        });

        loadBearingChkbx.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    loadBearingHouseVilla = true;
                } else {
                    loadBearingHouseVilla = false;
                }
            }
        });

        otherChkbx.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    otherHouseVilla = true;
                } else {
                    otherHouseVilla = false;
                }
            }
        });

        recidentialPlotChkbx.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    recidentialPlotFlag = true;
                } else {
                    recidentialPlotFlag = false;
                }
            }
        });

        commercialPlotChkbx.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    commercialPlotFlag = true;
                } else {
                    commercialPlotFlag = false;
                }
            }
        });

        industrialPlotChkbx.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    industrialPlotFlag = true;
                } else {
                    industrialPlotFlag = false;
                }
            }
        });

        exclusivePropsChkbx.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    isExclusivePropChecked = true;
                } else {
                    isExclusivePropChecked = false;
                }
            }
        });

        verifiedPropsChkbx.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    isVerifiedPropChecked = true;
                } else {
                    isVerifiedPropChecked = false;
                }
            }
        });

        propsWithDealsAndDiscsChkbx.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    isPropsWithDiscsAndOffChecked = true;
                } else {
                    isPropsWithDiscsAndOffChecked = false;
                }
            }
        });

    }


    private void intializeViews() {

        recyclerViewBedrooms = (RecyclerView) rootview.findViewById(R.id.recyc_bedrooms);

        monthPosSpin = (Spinner) rootview.findViewById(R.id.fromYearPossesSpin);
        yearPosSPin = (Spinner) rootview.findViewById(R.id.toYearPossesSPin);

        societySpin = (Spinner) rootview.findViewById(R.id.societySpinner);

        recyclerViewAmenities = (RecyclerView) rootview.findViewById(R.id.recyc_amenities);

        //chipsInput = (ChipsInput) rootview.findViewById(R.id.locationsChips);

        propAreaSpn = (Spinner) rootview.findViewById(R.id.propAreaSpin2);

        rvBathroom = (RecyclerView) rootview.findViewById(R.id.recyc_bathrooms);
        rvPostSince = (RecyclerView) rootview.findViewById(R.id.recyc_postSince);

        agentBtn = (Button) rootview.findViewById(R.id.btnPostByAgent);
        ownerBtn = (Button) rootview.findViewById(R.id.btnPostByOwner);
        builderBtn = (Button) rootview.findViewById(R.id.btnPostByBuilder);


        recyclerViewAllProj = (RecyclerView) rootview.findViewById(R.id.recyclerView_BuyAllProj);

        bedroomsTitleTv = (TextView) rootview.findViewById(R.id.bedroomTitleTv);
        bathroomTitleTv = (TextView) rootview.findViewById(R.id.bathroomTitleTv);
        amenitiesTitleTv = (TextView) rootview.findViewById(R.id.amenitiesTitleTv);
        propAreaTitleTv = (TextView) rootview.findViewById(R.id.propAreaTitleTv);
        furnishingTitleTv = (TextView) rootview.findViewById(R.id.furnishingStsTitleTv);
        budgetTitleTv = (TextView) rootview.findViewById(R.id.budgetTitleTv);

        possessionTitleTv = (TextView) rootview.findViewById(R.id.posseionTitleTv);
        societyTitleTv = (TextView) rootview.findViewById(R.id.societyTitleTv);
        floorTitleTv = (TextView) rootview.findViewById(R.id.floorDetailsTitleTv);
        typeOfHouseVillaTitlteTv = (TextView) rootview.findViewById(R.id.typeOfHoseVillaTitleTv);
        typeOfPlotTitleTv = (TextView) rootview.findViewById(R.id.typeOfNAPlotTitleTv);

        budgetTitleTv = (TextView) rootview.findViewById(R.id.budgetTitleTv);
        postedSinceTitleTv = (TextView) rootview.findViewById(R.id.postedSinceTitleTv);
        postedByTitleTv = (TextView) rootview.findViewById(R.id.postedByTitleTv);
        propAreaTitleTv = (TextView) rootview.findViewById(R.id.propAreaTitleTv);
        tenantsTypeTitleTv = (TextView) rootview.findViewById(R.id.tenantsTypeTitleTv);
        agreementTypeTitleTv = (TextView) rootview.findViewById(R.id.agreementTypeTitleTv);

        //otherPropChkbox = (CheckBox) rootview.findViewById(R.id.otherPropChkboxId);

        bottomSheetRelativeLayout = (RelativeLayout) rootview.findViewById(R.id.rent_bottomsheet);
        filterBtnLayout = (LinearLayout) rootview.findViewById(R.id.filterBtn);
        closeFiltersLin = (LinearLayout) rootview.findViewById(R.id.closeFiltersLin);

        possessionContainer = (LinearLayout) rootview.findViewById(R.id.possessionContainerLin);

        postedByContainer = (LinearLayout) rootview.findViewById(R.id.postedByContainerLin);
        societySpinContainer = (RelativeLayout) rootview.findViewById(R.id.societySpinContainer);
        //maintainanceChrgsContainer = (LinearLayout) rootview.findViewById(R.id.postedByContainerLin);
        comPropDetailContainer = (LinearLayout) rootview.findViewById(R.id.propDetailsSpinContainer);
        receptionContainer = (LinearLayout) rootview.findViewById(R.id.receptionContainer);
        floorContainer = (RelativeLayout) rootview.findViewById(R.id.floorSpinContainer);
        funrnishingContainer = (LinearLayout) rootview.findViewById(R.id.funrnishingContainer);
        typeOfHouseVillaContainer = (LinearLayout) rootview.findViewById(R.id.typeOfHouseVillContainer);
        typeOfPlotContainer = (LinearLayout) rootview.findViewById(R.id.typeOfNaPlotContainer);
        tenantsTypeContainer = (LinearLayout) rootview.findViewById(R.id.tenantsTypeContainer);
        agreementTypeContainer = (LinearLayout) rootview.findViewById(R.id.agreementTypeContainer);

        rangeSeekBar = (RangeSeekBar) rootview.findViewById(R.id.rangeSeekbar);
        minBudgetTv = (TextView) rootview.findViewById(R.id.minBudgetTv);
        maxBudgetTv = (TextView) rootview.findViewById(R.id.maxBudgetTv);

        rangeDepositSeekbar = (RangeSeekBar) rootview.findViewById(R.id.rangeDepositSeekbar);
        minDepositTv = (TextView) rootview.findViewById(R.id.minDepositTv);
        maxDepositTv = (TextView) rootview.findViewById(R.id.maxDepositTv);

        rangeSeekBarArea = (RangeSeekBar) rootview.findViewById(R.id.areaRangeSeekbar);
        minAreaTv = (TextView) rootview.findViewById(R.id.minAreaTv);
        maxAreaTv = (TextView) rootview.findViewById(R.id.maxAreaTv);

        facingTitleTv = (TextView) rootview.findViewById(R.id.facingTitleTv);
        rvFacing = (RecyclerView) rootview.findViewById(R.id.recyc_facing);

        cabinSpin = (Spinner) rootview.findViewById(R.id.cabinSpin);
        conferenceSpin = (Spinner) rootview.findViewById(R.id.confRoomSpin);
        receptionSpin = (Spinner) rootview.findViewById(R.id.receptionSpin);

        floorLevelSpin = (Spinner) rootview.findViewById(R.id.floorNoSpin);

        commercPropDetailsTitleTv = (TextView) rootview.findViewById(R.id.propDetailsTitleTv);

        bachelorsBtn = (Button) rootview.findViewById(R.id.btnBachelors);
        familyBtn = (Button) rootview.findViewById(R.id.btnFamily);

        minAreaEdtx = (EditText) rootview.findViewById(R.id.minAreaEdtx);
        maxAreaEdtx = (EditText) rootview.findViewById(R.id.maxAreaEdtx);

        refundableChkBox = (CheckBox) rootview.findViewById(R.id.refundableChkbox);
        nonRefundableChkBox = (CheckBox) rootview.findViewById(R.id.nonRefundableChkBox);

        addLocationsTv = (TextView) rootview.findViewById(R.id.addLocationTv);

        rvLocations = (RecyclerView) rootview.findViewById(R.id.rvApproverOneCC);
        addLocationImg = (ImageView) rootview.findViewById(R.id.addBtnApproverOne);

        applyFilterTv = (TextView) rootview.findViewById(R.id.applyFilterTv);

        unfurnishedChkbx = (CheckBox) rootview.findViewById(R.id.unfurnishedChkbx);
        semiFurnishedChkbx = (CheckBox) rootview.findViewById(R.id.semiFurnChkbx);
        fullyFurnishedChkbx = (CheckBox) rootview.findViewById(R.id.fullyFurnChkbx);

        rentalAgreementChkbx = (CheckBox) rootview.findViewById(R.id.rentalChkBox);
        llAgreementChkbx = (CheckBox) rootview.findViewById(R.id.leaveAndLicenceChkBox);

        rccHouseVillaChkbx = (CheckBox) rootview.findViewById(R.id.rccChkBx);
        loadBearingChkbx = (CheckBox) rootview.findViewById(R.id.loadBearingChkbx);
        otherChkbx = (CheckBox) rootview.findViewById(R.id.otherChkbx);

        recidentialPlotChkbx = (CheckBox) rootview.findViewById(R.id.residentialChkBx);
        commercialPlotChkbx = (CheckBox) rootview.findViewById(R.id.commercialChkbx);
        industrialPlotChkbx = (CheckBox) rootview.findViewById(R.id.industrialChkbx);

        exclusivePropsChkbx = (CheckBox) rootview.findViewById(R.id.exclusivePropsChkbx);
        verifiedPropsChkbx = (CheckBox) rootview.findViewById(R.id.verifiedPropsChkbx);
        propsWithDealsAndDiscsChkbx = (CheckBox) rootview.findViewById(R.id.propsWithDiscsAndOffersChkbx);

        applyFilterTv = (TextView) rootview.findViewById(R.id.applyFilterTv);

        /* selectLocationBtn = (LinearLayout) rootview.findViewById(R.id.locationSelectBtn);
        doneLocationSelectionBtn = (Button) rootview.findViewById(R.id.doneBtn);

        rvSelectedLocations = (RecyclerView) rootview.findViewById(R.id.rvAddedLocations);
        rvLocationList = (RecyclerView) rootview.findViewById(R.id.rvLocations);

        locationsSearchEdtx = (EditText) rootview.findViewById(R.id.locationsSearchEdtx);*/

        /*rvSelectedLocations;
        private RecyclerView rvLocationList;*/
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof Activity) {
            activity = (FragmentActivity) context;
        }
    }


    public void customizePropTypes() {

        if (propTypeStr != null) {

            if (propTypeStr.matches("Flat") || propTypeStr.matches("House/Villa")) {

                tenantsTypeTitleTv.setVisibility(View.VISIBLE);
                tenantsTypeContainer.setVisibility(View.VISIBLE);
                bachelorsBtn.setVisibility(View.VISIBLE);
                familyBtn.setVisibility(View.VISIBLE);

                agreementTypeTitleTv.setVisibility(View.VISIBLE);
                agreementTypeContainer.setVisibility(View.VISIBLE);

                if (propTypeStr.matches("Flat")) {

                    propAreaTitleTv.setText("Flat Area");
                    lstcategoryBedrooms.add(new POJO_bedrooms("1 BHK", false));
                    lstcategoryBedrooms.add(new POJO_bedrooms("2 BHK", false));
                    lstcategoryBedrooms.add(new POJO_bedrooms("3 BHK", false));
                    lstcategoryBedrooms.add(new POJO_bedrooms("4 BHK", false));
                    lstcategoryBedrooms.add(new POJO_bedrooms(">4 BHK", false));

                    adapterBedrooms = new RecyclerviewAdapterBedrooms(activity, lstcategoryBedrooms);

                    int spanCountBedroom = 4; // 3 columns
                    int spacingBedroom = 25; // 50px
                    boolean includeEdgeBedroom = true;
                    recyclerViewBedrooms.addItemDecoration(new GridSpacingDecoration(spanCountBedroom, spacingBedroom, includeEdgeBedroom));
                    GridLayoutManager gridLayoutManagerBedrooms = new GridLayoutManager(activity, 4);
                    recyclerViewBedrooms.setLayoutManager(gridLayoutManagerBedrooms);
                    recyclerViewBedrooms.setAdapter(adapterBedrooms);

                    areaList.add("Sqft");
                    areaList.add("Sqmtr");

                    final ArrayAdapter<String> spinAdapterArea = new ArrayAdapter<String>(activity, android.R.layout.simple_spinner_item, areaList);
                    spinAdapterArea.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    propAreaSpn.setAdapter(spinAdapterArea);

                    minAreaTv.setText("1 Sqft");
                    maxAreaTv.setText("10000 Sqft");

                    propAreaSpn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                            switch (position) {

                                case 0:

                                    rangeSeekBarArea.setMinRange(1);
                                    rangeSeekBarArea.setMax(50000);

                                    minAreaTv.setText("1 Sqft");
                                    maxAreaTv.setText("10000 Sqft");

                                    rangeSeekBarArea.setMinThumbValue(1);

                                    rangeSeekBarArea.setMaxThumbValue(10000);

                                    minAreaEdtx.setText("1");
                                    maxAreaEdtx.setText("10000");

                                    //rangeSeekBarArea.setMax(2000);

                                    rangeSeekBarArea.setSeekBarChangeListener(new RangeSeekBar.SeekBarChangeListener() {
                                        @Override
                                        public void onStartedSeeking() {

                                        }

                                        @Override
                                        public void onStoppedSeeking() {

                                        }

                                        @Override
                                        public void onValueChanged(int i, int i1) {

                                            minAreaTv.setText(String.valueOf(i) + " " + "Sqft");
                                            maxAreaTv.setText(String.valueOf(i1) + " " + "Sqft");

                                            minAreaEdtx.setText(String.valueOf(i));
                                            maxAreaEdtx.setText(String.valueOf(i1));

                                            Log.d("SEEKBARLOGCHECK", "MinValue =" + " " + i + " " + " " + " " + "MaxValue =" + " " + i1);

                                        }
                                    });

                                    break;

                                case 1:
                                    rangeSeekBarArea.setMinRange(1);
                                    rangeSeekBarArea.setMax(50000);

                                    minAreaTv.setText("1 Sqmtr");
                                    maxAreaTv.setText("10000 Sqmtr");

                                    rangeSeekBarArea.setMinThumbValue(1);

                                    rangeSeekBarArea.setMaxThumbValue(10000);

                                    minAreaEdtx.setText("1");
                                    maxAreaEdtx.setText("10000");

                                    //rangeSeekBarArea.setMax(2000);

                                    rangeSeekBarArea.setSeekBarChangeListener(new RangeSeekBar.SeekBarChangeListener() {
                                        @Override
                                        public void onStartedSeeking() {

                                        }

                                        @Override
                                        public void onStoppedSeeking() {

                                        }

                                        @Override
                                        public void onValueChanged(int i, int i1) {

                                            minAreaTv.setText(String.valueOf(i) + " " + "Sqmtr");
                                            maxAreaTv.setText(String.valueOf(i1) + " " + "Sqmtr");

                                            minAreaEdtx.setText(String.valueOf(i));
                                            maxAreaEdtx.setText(String.valueOf(i1));

                                            Log.d("SEEKBARLOGCHECK", "MinValue =" + " " + i + " " + " " + " " + "MaxValue =" + " " + i1);

                                        }
                                    });

                                    break;

                            }


                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });

                } else if (propTypeStr.matches("House/Villa")) {

                    societyTitleTv.setVisibility(View.GONE);
                    societySpinContainer.setVisibility(View.GONE);

                    propAreaTitleTv.setText("House/Villa Area");
                    bedroomsTitleTv.setText("Rooms");

                    floorTitleTv.setVisibility(View.VISIBLE);
                    floorContainer.setVisibility(View.VISIBLE);

                    //typeOfHouseVillaRdGrp.setVisibility(View.VISIBLE);
                    typeOfHouseVillaContainer.setVisibility(View.VISIBLE);
                    typeOfHouseVillaTitlteTv.setVisibility(View.VISIBLE);

                    lstcategoryBedrooms.add(new POJO_bedrooms("1 Room", false));
                    lstcategoryBedrooms.add(new POJO_bedrooms("2 Rooms", false));
                    lstcategoryBedrooms.add(new POJO_bedrooms("3 Rooms", false));
                    lstcategoryBedrooms.add(new POJO_bedrooms("4 Rooms", false));
                    lstcategoryBedrooms.add(new POJO_bedrooms("5 Rooms", false));
                    lstcategoryBedrooms.add(new POJO_bedrooms("6 Rooms", false));
                    lstcategoryBedrooms.add(new POJO_bedrooms("7 Rooms", false));
                    lstcategoryBedrooms.add(new POJO_bedrooms("8 Rooms", false));
                    lstcategoryBedrooms.add(new POJO_bedrooms("9 Rooms", false));
                    lstcategoryBedrooms.add(new POJO_bedrooms("10 Rooms", false));
                    lstcategoryBedrooms.add(new POJO_bedrooms("10+ Rooms", false));

                    adapterBedrooms = new RecyclerviewAdapterBedrooms(activity, lstcategoryBedrooms);

                    int spanCountBedroom = 4; // 3 columns
                    int spacingBedroom = 25; // 50px
                    boolean includeEdgeBedroom = true;
                    recyclerViewBedrooms.addItemDecoration(new GridSpacingDecoration(spanCountBedroom, spacingBedroom, includeEdgeBedroom));
                    GridLayoutManager gridLayoutManagerBedrooms = new GridLayoutManager(activity, 4);
                    recyclerViewBedrooms.setLayoutManager(gridLayoutManagerBedrooms);
                    recyclerViewBedrooms.setAdapter(adapterBedrooms);

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

                    areaList.add("Guntha");
                    areaList.add("Sqft");
                    areaList.add("Sqmtr");

                    minAreaTv.setText("1 Guntha");
                    maxAreaTv.setText("40 Guntha");

                    final ArrayAdapter<String> spinAdapterArea = new ArrayAdapter<String>(activity, android.R.layout.simple_spinner_item, areaList);
                    spinAdapterArea.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    propAreaSpn.setAdapter(spinAdapterArea);

                    propAreaSpn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                            switch (position) {

                                case 0:

                                    rangeSeekBarArea.setMinRange(1);
                                    rangeSeekBarArea.setMax(100);

                                    minAreaTv.setText("1 Guntha");
                                    maxAreaTv.setText("40 Guntha");

                                    rangeSeekBarArea.setMinThumbValue(1);

                                    rangeSeekBarArea.setMaxThumbValue(40);

                                    minAreaEdtx.setText("1");
                                    maxAreaEdtx.setText("40");

                                    //rangeSeekBarArea.setMax(2000);

                                    rangeSeekBarArea.setSeekBarChangeListener(new RangeSeekBar.SeekBarChangeListener() {
                                        @Override
                                        public void onStartedSeeking() {

                                        }

                                        @Override
                                        public void onStoppedSeeking() {

                                        }

                                        @Override
                                        public void onValueChanged(int i, int i1) {

                                            minAreaTv.setText(String.valueOf(i) + " " + "Guntha");
                                            maxAreaTv.setText(String.valueOf(i1) + " " + "Guntha");

                                            minAreaEdtx.setText(String.valueOf(i));
                                            maxAreaEdtx.setText(String.valueOf(i1));

                                            Log.d("SEEKBARLOGCHECK", "MinValue =" + " " + i + " " + " " + " " + "MaxValue =" + " " + i1);

                                        }
                                    });

                                    break;

                                case 1:

                                    rangeSeekBarArea.setMinRange(1);
                                    rangeSeekBarArea.setMax(50000);

                                    minAreaTv.setText("1 Sqft");
                                    maxAreaTv.setText("10000 Sqft");

                                    rangeSeekBarArea.setMinThumbValue(1);

                                    rangeSeekBarArea.setMaxThumbValue(10000);

                                    minAreaEdtx.setText("1");
                                    maxAreaEdtx.setText("10000");

                                    //rangeSeekBarArea.setMax(2000);

                                    rangeSeekBarArea.setSeekBarChangeListener(new RangeSeekBar.SeekBarChangeListener() {
                                        @Override
                                        public void onStartedSeeking() {

                                        }

                                        @Override
                                        public void onStoppedSeeking() {

                                        }

                                        @Override
                                        public void onValueChanged(int i, int i1) {

                                            minAreaTv.setText(String.valueOf(i) + " " + "Sqft");
                                            maxAreaTv.setText(String.valueOf(i1) + " " + "Sqft");

                                            minAreaEdtx.setText(String.valueOf(i));
                                            maxAreaEdtx.setText(String.valueOf(i1));

                                            Log.d("SEEKBARLOGCHECK", "MinValue =" + " " + i + " " + " " + " " + "MaxValue =" + " " + i1);

                                        }
                                    });

                                    break;

                                case 2:
                                    rangeSeekBarArea.setMinRange(1);
                                    rangeSeekBarArea.setMax(50000);

                                    minAreaTv.setText("1 Sqmtr");
                                    maxAreaTv.setText("10000 Sqmtr");

                                    rangeSeekBarArea.setMinThumbValue(1);

                                    rangeSeekBarArea.setMaxThumbValue(10000);

                                    minAreaEdtx.setText("1");
                                    maxAreaEdtx.setText("10000");

                                    //rangeSeekBarArea.setMax(2000);

                                    rangeSeekBarArea.setSeekBarChangeListener(new RangeSeekBar.SeekBarChangeListener() {
                                        @Override
                                        public void onStartedSeeking() {

                                        }

                                        @Override
                                        public void onStoppedSeeking() {

                                        }

                                        @Override
                                        public void onValueChanged(int i, int i1) {

                                            minAreaTv.setText(String.valueOf(i) + " " + "Sqmtr");
                                            maxAreaTv.setText(String.valueOf(i1) + " " + "Sqmtr");

                                            minAreaEdtx.setText(String.valueOf(i));
                                            maxAreaEdtx.setText(String.valueOf(i1));

                                            Log.d("SEEKBARLOGCHECK", "MinValue =" + " " + i + " " + " " + " " + "MaxValue =" + " " + i1);

                                        }
                                    });

                                    break;

                            }


                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });

                }

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

                int spanCount = 3; // 3 columns
                int spacing = 27; // 50px
                boolean includeEdge = true;
                recyclerViewAmenities.addItemDecoration(new GridSpacingDecoration(spanCount, spacing, includeEdge));
                recyclerViewAdapterAmenities = new RecyclerviewAdapterAmenities(activity, lstcategoryAmenities, true);
                GridLayoutManager gridLayoutManagerAmenities = new GridLayoutManager(activity, 3);
                recyclerViewAmenities.setLayoutManager(gridLayoutManagerAmenities);
                recyclerViewAmenities.setAdapter(recyclerViewAdapterAmenities);


            } else if (propTypeStr.matches("NA Plot") || propTypeStr.matches("Agricultural Land")) {


                bedroomsTitleTv.setVisibility(View.GONE);
                recyclerViewBedrooms.setVisibility(View.GONE);
               /* amenitiesTitleTv.setVisibility(View.GONE);
                recyclerViewAmenities.setVisibility(View.GONE);
*/

                bathroomTitleTv.setVisibility(View.GONE);
                rvBathroom.setVisibility(View.GONE);
//                balconyTitleTv.setVisibility(View.GONE);
//                rvBalcony.setVisibility(View.GONE);
                furnishingTitleTv.setVisibility(View.GONE);
                //furnishingRdGrp.setVisibility(View.GONE);
                funrnishingContainer.setVisibility(View.GONE);

                //otherPropChkbox.setVisibility(View.GONE);

                //underConstruction.setVisibility(View.GONE);
                //readyToMove.setVisibility(View.GONE);

                possessionTitleTv.setVisibility(View.GONE);
                possessionContainer.setVisibility(View.GONE);

                societyTitleTv.setVisibility(View.GONE);
                //societySpin.setVisibility(View.GONE);
                societySpinContainer.setVisibility(View.GONE);

                //newBtn.setVisibility(View.GONE);
                //resaleBtn.setVisibility(View.GONE);

                if (propTypeStr.matches("NA Plot")) {

                    // typeOfPlotTitleTv

                    LinearLayout.LayoutParams params = new
                            LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    params.setMargins(10, 20, 10, 40); // setMargins(left, top, right, bottom)
                    typeOfPlotContainer.setLayoutParams(params);

                    propAreaTitleTv.setText("NA Plot Area");

                    typeOfPlotTitleTv.setVisibility(View.VISIBLE);
                    //typeOfPlotRdGrp.setVisibility(View.VISIBLE);
                    typeOfPlotContainer.setVisibility(View.VISIBLE);

                    amenitiesTitleTv.setText("Facilities");
                    lstcategoryAmenities.add(new POJO_Amenities("Water Available", "https://upload.wikimedia.org/wikipedia/commons/thumb/1/1c/Circle-icons-water.svg/1024px-Circle-icons-water.svg.png", false));
                    lstcategoryAmenities.add(new POJO_Amenities("Electricity Available", "https://cdn5.vectorstock.com/i/1000x1000/96/24/lamp-flat-icon-on-white-background-vector-22699624.jpg", false));
                    lstcategoryAmenities.add(new POJO_Amenities("Road Touch", "https://www.pngrepo.com/download/80761/road.png", false));
                    lstcategoryAmenities.add(new POJO_Amenities("Title Clear", "https://cdn.pixabay.com/photo/2017/09/27/21/05/license-icon-2793454_960_720.png", false));

                    lstcategoryAmenities.add(new POJO_Amenities("Gym", "https://www.pngrepo.com/download/200170/dumbbell-gym.png", false));
                    lstcategoryAmenities.add(new POJO_Amenities("Temple", "https://i.dlpng.com/static/png/5411504-hindu-temple-png-download-transparent-hindu-temple-png-images-hindu-temple-png-820_625_preview.png", false));
                    lstcategoryAmenities.add(new POJO_Amenities("Security", "https://cdn3.iconfinder.com/data/icons/human-resources-3-1/128/113-512.png", false));
                    lstcategoryAmenities.add(new POJO_Amenities("Park", "https://cdn4.iconfinder.com/data/icons/jetflat-2-buildings-vol-1/60/008_035_ferris_wheel_park_amusement_attractions-512.png", false));
                    lstcategoryAmenities.add(new POJO_Amenities("Swimming Pool", "https://png.pngtree.com/png-vector/20190830/ourlarge/pngtree-swimming-pool-icon-design-vector-png-image_1708730.jpg", false));
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

                    areaList.add("Guntha");
                    areaList.add("Sqft");
                    areaList.add("Sqmtr");

                    final ArrayAdapter<String> spinAdapterArea = new ArrayAdapter<String>(activity, android.R.layout.simple_spinner_item, areaList);
                    spinAdapterArea.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    propAreaSpn.setAdapter(spinAdapterArea);

                    minAreaTv.setText("1 Guntha");
                    maxAreaTv.setText("40 Guntha");

                    propAreaSpn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                            switch (position) {

                                case 0:

                                    rangeSeekBarArea.setMinRange(1);
                                    rangeSeekBarArea.setMax(100);

                                    minAreaTv.setText("1 Guntha");
                                    maxAreaTv.setText("40 Guntha");

                                    rangeSeekBarArea.setMinThumbValue(1);

                                    rangeSeekBarArea.setMaxThumbValue(40);

                                    minAreaEdtx.setText("1");
                                    maxAreaEdtx.setText("40");

                                    //rangeSeekBarArea.setMax(2000);

                                    rangeSeekBarArea.setSeekBarChangeListener(new RangeSeekBar.SeekBarChangeListener() {
                                        @Override
                                        public void onStartedSeeking() {

                                        }

                                        @Override
                                        public void onStoppedSeeking() {

                                        }

                                        @Override
                                        public void onValueChanged(int i, int i1) {

                                            minAreaTv.setText(String.valueOf(i) + " " + "Guntha");
                                            maxAreaTv.setText(String.valueOf(i1) + " " + "Guntha");

                                            minAreaEdtx.setText(String.valueOf(i));
                                            maxAreaEdtx.setText(String.valueOf(i1));

                                            Log.d("SEEKBARLOGCHECK", "MinValue =" + " " + i + " " + " " + " " + "MaxValue =" + " " + i1);

                                        }
                                    });

                                    break;

                                case 1:

                                    rangeSeekBarArea.setMinRange(1);
                                    rangeSeekBarArea.setMax(50000);

                                    minAreaTv.setText("1 Sqft");
                                    maxAreaTv.setText("10000 Sqft");

                                    rangeSeekBarArea.setMinThumbValue(1);

                                    rangeSeekBarArea.setMaxThumbValue(10000);

                                    minAreaEdtx.setText("1");
                                    maxAreaEdtx.setText("10000");

                                    //rangeSeekBarArea.setMax(2000);

                                    rangeSeekBarArea.setSeekBarChangeListener(new RangeSeekBar.SeekBarChangeListener() {
                                        @Override
                                        public void onStartedSeeking() {

                                        }

                                        @Override
                                        public void onStoppedSeeking() {

                                        }

                                        @Override
                                        public void onValueChanged(int i, int i1) {

                                            minAreaTv.setText(String.valueOf(i) + " " + "Sqft");
                                            maxAreaTv.setText(String.valueOf(i1) + " " + "Sqft");

                                            minAreaEdtx.setText(String.valueOf(i));
                                            maxAreaEdtx.setText(String.valueOf(i1));

                                            Log.d("SEEKBARLOGCHECK", "MinValue =" + " " + i + " " + " " + " " + "MaxValue =" + " " + i1);

                                        }
                                    });

                                    break;

                                case 2:
                                    rangeSeekBarArea.setMinRange(1);
                                    rangeSeekBarArea.setMax(50000);

                                    minAreaTv.setText("1 Sqmtr");
                                    maxAreaTv.setText("10000 Sqmtr");

                                    rangeSeekBarArea.setMinThumbValue(1);

                                    rangeSeekBarArea.setMaxThumbValue(10000);

                                    minAreaEdtx.setText("1");
                                    maxAreaEdtx.setText("10000");

                                    //rangeSeekBarArea.setMax(2000);

                                    rangeSeekBarArea.setSeekBarChangeListener(new RangeSeekBar.SeekBarChangeListener() {
                                        @Override
                                        public void onStartedSeeking() {

                                        }

                                        @Override
                                        public void onStoppedSeeking() {

                                        }

                                        @Override
                                        public void onValueChanged(int i, int i1) {

                                            minAreaTv.setText(String.valueOf(i) + " " + "Sqmtr");
                                            maxAreaTv.setText(String.valueOf(i1) + " " + "Sqmtr");

                                            minAreaEdtx.setText(String.valueOf(i));
                                            maxAreaEdtx.setText(String.valueOf(i1));

                                            Log.d("SEEKBARLOGCHECK", "MinValue =" + " " + i + " " + " " + " " + "MaxValue =" + " " + i1);

                                        }
                                    });

                                    break;

                            }


                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });


                } else if (propTypeStr.matches("Agricultural Land")) {

                    propAreaTitleTv.setText("Agricultural Land Area");

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

                    areaList.add("Acre");
                    areaList.add("Hectare");
                    areaList.add("Guntha");
                    areaList.add("Sqft");
                    areaList.add("Sqmtr");

                    minAreaTv.setText("1 Acre");
                    maxAreaTv.setText("100 Acre");

                    final ArrayAdapter<String> spinAdapterArea = new ArrayAdapter<String>(activity, android.R.layout.simple_spinner_item, areaList);
                    spinAdapterArea.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    propAreaSpn.setAdapter(spinAdapterArea);

                    propAreaSpn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                            switch (position) {

                                case 0:

                                    rangeSeekBarArea.setMinRange(1);
                                    rangeSeekBarArea.setMax(1000);

                                    minAreaTv.setText("1 Acre");
                                    maxAreaTv.setText("100 Acre");

                                    rangeSeekBarArea.setMinThumbValue(1);

                                    rangeSeekBarArea.setMaxThumbValue(100);

                                    minAreaEdtx.setText("1");
                                    maxAreaEdtx.setText("100");

                                    //rangeSeekBarArea.setMax(2000);

                                    rangeSeekBarArea.setSeekBarChangeListener(new RangeSeekBar.SeekBarChangeListener() {
                                        @Override
                                        public void onStartedSeeking() {

                                        }

                                        @Override
                                        public void onStoppedSeeking() {

                                        }

                                        @Override
                                        public void onValueChanged(int i, int i1) {

                                            minAreaTv.setText(String.valueOf(i) + " " + "Acre");
                                            maxAreaTv.setText(String.valueOf(i1) + " " + "Acre");

                                            minAreaEdtx.setText(String.valueOf(i));
                                            maxAreaEdtx.setText(String.valueOf(i1));

                                            Log.d("SEEKBARLOGCHECK", "MinValue =" + " " + i + " " + " " + " " + "MaxValue =" + " " + i1);

                                        }
                                    });

                                    break;

                                case 1:

                                    rangeSeekBarArea.setMinRange(1);
                                    rangeSeekBarArea.setMax(500);

                                    minAreaTv.setText("1 Hectare");
                                    maxAreaTv.setText("50 Hectare");

                                    rangeSeekBarArea.setMinThumbValue(1);

                                    rangeSeekBarArea.setMaxThumbValue(50);

                                    minAreaEdtx.setText("1");
                                    maxAreaEdtx.setText("50");

                                    //rangeSeekBarArea.setMax(2000);

                                    rangeSeekBarArea.setSeekBarChangeListener(new RangeSeekBar.SeekBarChangeListener() {
                                        @Override
                                        public void onStartedSeeking() {

                                        }

                                        @Override
                                        public void onStoppedSeeking() {

                                        }

                                        @Override
                                        public void onValueChanged(int i, int i1) {

                                            minAreaTv.setText(String.valueOf(i) + " " + "Hectare");
                                            maxAreaTv.setText(String.valueOf(i1) + " " + "Hectare");

                                            minAreaEdtx.setText(String.valueOf(i));
                                            maxAreaEdtx.setText(String.valueOf(i1));

                                            Log.d("SEEKBARLOGCHECK", "MinValue =" + " " + i + " " + " " + " " + "MaxValue =" + " " + i1);

                                        }
                                    });

                                    break;

                                case 2:

                                    rangeSeekBarArea.setMinRange(1);
                                    rangeSeekBarArea.setMax(100);

                                    minAreaTv.setText("1 Guntha");
                                    maxAreaTv.setText("40 Guntha");

                                    rangeSeekBarArea.setMinThumbValue(1);

                                    rangeSeekBarArea.setMaxThumbValue(40);

                                    minAreaEdtx.setText("1");
                                    maxAreaEdtx.setText("40");

                                    //rangeSeekBarArea.setMax(2000);

                                    rangeSeekBarArea.setSeekBarChangeListener(new RangeSeekBar.SeekBarChangeListener() {
                                        @Override
                                        public void onStartedSeeking() {

                                        }

                                        @Override
                                        public void onStoppedSeeking() {

                                        }

                                        @Override
                                        public void onValueChanged(int i, int i1) {

                                            minAreaTv.setText(String.valueOf(i) + " " + "Guntha");
                                            maxAreaTv.setText(String.valueOf(i1) + " " + "Guntha");

                                            minAreaEdtx.setText(String.valueOf(i));
                                            maxAreaEdtx.setText(String.valueOf(i1));

                                            Log.d("SEEKBARLOGCHECK", "MinValue =" + " " + i + " " + " " + " " + "MaxValue =" + " " + i1);

                                        }
                                    });

                                    break;

                                case 3:

                                    rangeSeekBarArea.setMinRange(1);
                                    rangeSeekBarArea.setMax(50000);

                                    minAreaTv.setText("1 Sqft");
                                    maxAreaTv.setText("10000 Sqft");

                                    rangeSeekBarArea.setMinThumbValue(1);

                                    rangeSeekBarArea.setMaxThumbValue(10000);

                                    minAreaEdtx.setText("1");
                                    maxAreaEdtx.setText("10000");

                                    //rangeSeekBarArea.setMax(2000);

                                    rangeSeekBarArea.setSeekBarChangeListener(new RangeSeekBar.SeekBarChangeListener() {
                                        @Override
                                        public void onStartedSeeking() {

                                        }

                                        @Override
                                        public void onStoppedSeeking() {

                                        }

                                        @Override
                                        public void onValueChanged(int i, int i1) {

                                            minAreaTv.setText(String.valueOf(i) + " " + "Sqft");
                                            maxAreaTv.setText(String.valueOf(i1) + " " + "Sqft");

                                            minAreaEdtx.setText(String.valueOf(i));
                                            maxAreaEdtx.setText(String.valueOf(i1));

                                            Log.d("SEEKBARLOGCHECK", "MinValue =" + " " + i + " " + " " + " " + "MaxValue =" + " " + i1);

                                        }
                                    });

                                    break;

                                case 4:
                                    rangeSeekBarArea.setMinRange(1);
                                    rangeSeekBarArea.setMax(50000);

                                    minAreaTv.setText("1 Sqmtr");
                                    maxAreaTv.setText("10000 Sqmtr");

                                    rangeSeekBarArea.setMinThumbValue(1);

                                    rangeSeekBarArea.setMaxThumbValue(10000);

                                    minAreaEdtx.setText("1");
                                    maxAreaEdtx.setText("10000");

                                    //rangeSeekBarArea.setMax(2000);

                                    rangeSeekBarArea.setSeekBarChangeListener(new RangeSeekBar.SeekBarChangeListener() {
                                        @Override
                                        public void onStartedSeeking() {

                                        }

                                        @Override
                                        public void onStoppedSeeking() {

                                        }

                                        @Override
                                        public void onValueChanged(int i, int i1) {

                                            minAreaTv.setText(String.valueOf(i) + " " + "Sqmtr");
                                            maxAreaTv.setText(String.valueOf(i1) + " " + "Sqmtr");

                                            minAreaEdtx.setText(String.valueOf(i));
                                            maxAreaEdtx.setText(String.valueOf(i1));

                                            Log.d("SEEKBARLOGCHECK", "MinValue =" + " " + i + " " + " " + " " + "MaxValue =" + " " + i1);

                                        }
                                    });

                                    break;

                            }


                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });


                }

                LinearLayout.LayoutParams params = new
                        LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                params.setMargins(10, 0, 5, 0); // setMargins(left, top, right, bottom)
                propAreaTitleTv.setLayoutParams(params);
                //propAreaTitleTv.setLayoutParams(lp);

//                postedSinceTitleTv.setVisibility(View.GONE);
//                rvPostSince.setVisibility(View.GONE);

//                postedByTitleTv.setVisibility(View.GONE);
//                postedByContainer.setVisibility(View.GONE);

                /*maintainanceChrgsContainer.setVisibility(View.GONE);
                mintainanceChargesEdtx.setVisibility(View.GONE);*/


            } else if (propTypeStr.matches("Office Space") || propTypeStr.matches("Shop/Showroom") || propTypeStr.matches("Other Commercial")) {

                bedroomsTitleTv.setVisibility(View.GONE);
                recyclerViewBedrooms.setVisibility(View.GONE);
                societyTitleTv.setVisibility(View.GONE);
                societySpin.setVisibility(View.GONE);
                societySpinContainer.setVisibility(View.GONE);

                commercPropDetailsTitleTv.setVisibility(View.VISIBLE);
                comPropDetailContainer.setVisibility(View.VISIBLE);
                receptionContainer.setVisibility(View.VISIBLE);

                LinearLayout.LayoutParams params = new
                        LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                params.setMargins(5, 20, 0, 0); // setMargins(left, top, right, bottom)
                commercPropDetailsTitleTv.setLayoutParams(params);

                if (propTypeStr.matches("Shop/Showroom")) {

                    propAreaTitleTv.setText("Shop/Showroom Area");

                    facingTitleTv.setVisibility(View.VISIBLE);
                    rvFacing.setVisibility(View.VISIBLE);

                    lstcategoryFacing.add(new Pojo_TextOpts("East", false));
                    lstcategoryFacing.add(new Pojo_TextOpts("North", false));
                    lstcategoryFacing.add(new Pojo_TextOpts("North-East", false));
                    lstcategoryFacing.add(new Pojo_TextOpts("North-West", false));
                    lstcategoryFacing.add(new Pojo_TextOpts("South", false));
                    lstcategoryFacing.add(new Pojo_TextOpts("South-East", false));
                    lstcategoryFacing.add(new Pojo_TextOpts("South-West", false));
                    lstcategoryFacing.add(new Pojo_TextOpts("West", false));

                    facingAdapter = new RecyclerviewAdapterTextOpts(activity, lstcategoryFacing, false);

                    int spanCountBath = 4; // 3 columns
                    int spacingBath = 25; // 50px
                    boolean includeEdgeBath = true;
                    rvFacing.addItemDecoration(new GridSpacingDecoration(spanCountBath, spacingBath, includeEdgeBath));
                    GridLayoutManager gridLayoutManagerFacing = new GridLayoutManager(activity, 4);
                    rvFacing.setLayoutManager(gridLayoutManagerFacing);
                    rvFacing.setAdapter(facingAdapter);

                    commercPropDetailsTitleTv.setText("Shop/Showroom Details");

                } else if (propTypeStr.matches("Office Space")) {

                    propAreaTitleTv.setText("Office Space Area");

                    commercPropDetailsTitleTv.setText("Office Details");

                } else if (propTypeStr.matches("Other Commercial")) {

                    propAreaTitleTv.setText("Commercial Space Area");

                    commercPropDetailsTitleTv.setText("Commercial Property Details");

                }

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


                int spanCount = 3; // 3 columns
                int spacing = 27; // 50px
                boolean includeEdge = true;
                recyclerViewAmenities.addItemDecoration(new GridSpacingDecoration(spanCount, spacing, includeEdge));
                recyclerViewAdapterAmenities = new RecyclerviewAdapterAmenities(activity, lstcategoryAmenities, true);
                GridLayoutManager gridLayoutManagerAmenities = new GridLayoutManager(activity, 3);
                recyclerViewAmenities.setLayoutManager(gridLayoutManagerAmenities);
                recyclerViewAmenities.setAdapter(recyclerViewAdapterAmenities);

                areaList.add("Guntha");
                areaList.add("Sqft");
                areaList.add("Sqmtr");

                minAreaTv.setText("1 Guntha");
                maxAreaTv.setText("40 Guntha");

                final ArrayAdapter<String> spinAdapterArea = new ArrayAdapter<String>(activity, android.R.layout.simple_spinner_item, areaList);
                spinAdapterArea.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                propAreaSpn.setAdapter(spinAdapterArea);

                propAreaSpn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                        switch (position) {

                            case 0:

                                rangeSeekBarArea.setMinRange(1);
                                rangeSeekBarArea.setMax(100);

                                minAreaTv.setText("1 Guntha");
                                maxAreaTv.setText("40 Guntha");

                                rangeSeekBarArea.setMinThumbValue(1);

                                rangeSeekBarArea.setMaxThumbValue(40);

                                minAreaEdtx.setText("1");
                                maxAreaEdtx.setText("40");

                                //rangeSeekBarArea.setMax(2000);

                                rangeSeekBarArea.setSeekBarChangeListener(new RangeSeekBar.SeekBarChangeListener() {
                                    @Override
                                    public void onStartedSeeking() {

                                    }

                                    @Override
                                    public void onStoppedSeeking() {

                                    }

                                    @Override
                                    public void onValueChanged(int i, int i1) {

                                        minAreaTv.setText(String.valueOf(i) + " " + "Guntha");
                                        maxAreaTv.setText(String.valueOf(i1) + " " + "Guntha");

                                        minAreaEdtx.setText(String.valueOf(i));
                                        maxAreaEdtx.setText(String.valueOf(i1));

                                        Log.d("SEEKBARLOGCHECK", "MinValue =" + " " + i + " " + " " + " " + "MaxValue =" + " " + i1);

                                    }
                                });

                                break;

                            case 1:

                                rangeSeekBarArea.setMinRange(1);
                                rangeSeekBarArea.setMax(50000);

                                minAreaTv.setText("1 Sqft");
                                maxAreaTv.setText("10000 Sqft");

                                rangeSeekBarArea.setMinThumbValue(1);

                                rangeSeekBarArea.setMaxThumbValue(10000);

                                minAreaEdtx.setText("1");
                                maxAreaEdtx.setText("10000");

                                //rangeSeekBarArea.setMax(2000);

                                rangeSeekBarArea.setSeekBarChangeListener(new RangeSeekBar.SeekBarChangeListener() {
                                    @Override
                                    public void onStartedSeeking() {

                                    }

                                    @Override
                                    public void onStoppedSeeking() {

                                    }

                                    @Override
                                    public void onValueChanged(int i, int i1) {

                                        minAreaTv.setText(String.valueOf(i) + " " + "Sqft");
                                        maxAreaTv.setText(String.valueOf(i1) + " " + "Sqft");

                                        minAreaEdtx.setText(String.valueOf(i));
                                        maxAreaEdtx.setText(String.valueOf(i1));

                                        Log.d("SEEKBARLOGCHECK", "MinValue =" + " " + i + " " + " " + " " + "MaxValue =" + " " + i1);

                                    }
                                });

                                break;

                            case 2:
                                rangeSeekBarArea.setMinRange(1);
                                rangeSeekBarArea.setMax(50000);

                                minAreaTv.setText("1 Sqmtr");
                                maxAreaTv.setText("10000 Sqmtr");

                                rangeSeekBarArea.setMinThumbValue(1);

                                rangeSeekBarArea.setMaxThumbValue(10000);

                                minAreaEdtx.setText("1");
                                maxAreaEdtx.setText("10000");

                                //rangeSeekBarArea.setMax(2000);

                                rangeSeekBarArea.setSeekBarChangeListener(new RangeSeekBar.SeekBarChangeListener() {
                                    @Override
                                    public void onStartedSeeking() {

                                    }

                                    @Override
                                    public void onStoppedSeeking() {

                                    }

                                    @Override
                                    public void onValueChanged(int i, int i1) {

                                        minAreaTv.setText(String.valueOf(i) + " " + "Sqmtr");
                                        maxAreaTv.setText(String.valueOf(i1) + " " + "Sqmtr");

                                        minAreaEdtx.setText(String.valueOf(i));
                                        maxAreaEdtx.setText(String.valueOf(i1));

                                        Log.d("SEEKBARLOGCHECK", "MinValue =" + " " + i + " " + " " + " " + "MaxValue =" + " " + i1);

                                    }
                                });

                                break;

                        }


                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });


            } else if (propTypeStr.matches("Show All")) {

                propAreaTitleTv.setText("Property Area");

                commercPropDetailsTitleTv.setText("Property Details");

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


                int spanCount = 3; // 3 columns
                int spacing = 27; // 50px
                boolean includeEdge = true;
                recyclerViewAmenities.addItemDecoration(new GridSpacingDecoration(spanCount, spacing, includeEdge));
                recyclerViewAdapterAmenities = new RecyclerviewAdapterAmenities(activity, lstcategoryAmenities, true);
                GridLayoutManager gridLayoutManagerAmenities = new GridLayoutManager(activity, 3);
                recyclerViewAmenities.setLayoutManager(gridLayoutManagerAmenities);
                recyclerViewAmenities.setAdapter(recyclerViewAdapterAmenities);

                lstcategoryBedrooms.add(new POJO_bedrooms("1 BHK", false));
                lstcategoryBedrooms.add(new POJO_bedrooms("2 BHK", false));
                lstcategoryBedrooms.add(new POJO_bedrooms("3 BHK", false));
                lstcategoryBedrooms.add(new POJO_bedrooms("4 BHK", false));
                lstcategoryBedrooms.add(new POJO_bedrooms(">4 BHK", false));

                adapterBedrooms = new RecyclerviewAdapterBedrooms(activity, lstcategoryBedrooms);

                int spanCountBedroom = 4; // 3 columns
                int spacingBedroom = 25; // 50px
                boolean includeEdgeBedroom = true;
                recyclerViewBedrooms.addItemDecoration(new GridSpacingDecoration(spanCountBedroom, spacingBedroom, includeEdgeBedroom));
                GridLayoutManager gridLayoutManagerBedrooms = new GridLayoutManager(activity, 4);
                recyclerViewBedrooms.setLayoutManager(gridLayoutManagerBedrooms);
                recyclerViewBedrooms.setAdapter(adapterBedrooms);

                areaList.add("Acre");
                areaList.add("Hectare");
                areaList.add("Guntha");
                areaList.add("Sqft");
                areaList.add("Sqmtr");

                minAreaTv.setText("1 Acre");
                maxAreaTv.setText("100 Acre");

                final ArrayAdapter<String> spinAdapterArea = new ArrayAdapter<String>(activity, android.R.layout.simple_spinner_item, areaList);
                spinAdapterArea.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                propAreaSpn.setAdapter(spinAdapterArea);

                propAreaSpn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                        switch (position) {

                            case 0:

                                rangeSeekBarArea.setMinRange(1);
                                rangeSeekBarArea.setMax(1000);

                                minAreaTv.setText("1 Acre");
                                maxAreaTv.setText("100 Acre");

                                rangeSeekBarArea.setMinThumbValue(1);

                                rangeSeekBarArea.setMaxThumbValue(100);

                                minAreaEdtx.setText("1");
                                maxAreaEdtx.setText("100");

                                //rangeSeekBarArea.setMax(2000);

                                rangeSeekBarArea.setSeekBarChangeListener(new RangeSeekBar.SeekBarChangeListener() {
                                    @Override
                                    public void onStartedSeeking() {

                                    }

                                    @Override
                                    public void onStoppedSeeking() {

                                    }

                                    @Override
                                    public void onValueChanged(int i, int i1) {

                                        minAreaTv.setText(String.valueOf(i) + " " + "Acre");
                                        maxAreaTv.setText(String.valueOf(i1) + " " + "Acre");

                                        minAreaEdtx.setText(String.valueOf(i));
                                        maxAreaEdtx.setText(String.valueOf(i1));

                                        Log.d("SEEKBARLOGCHECK", "MinValue =" + " " + i + " " + " " + " " + "MaxValue =" + " " + i1);

                                    }
                                });

                                break;

                            case 1:

                                rangeSeekBarArea.setMinRange(1);
                                rangeSeekBarArea.setMax(500);

                                minAreaTv.setText("1 Hectare");
                                maxAreaTv.setText("50 Hectare");

                                rangeSeekBarArea.setMinThumbValue(1);

                                rangeSeekBarArea.setMaxThumbValue(50);

                                minAreaEdtx.setText("1");
                                maxAreaEdtx.setText("50");

                                //rangeSeekBarArea.setMax(2000);

                                rangeSeekBarArea.setSeekBarChangeListener(new RangeSeekBar.SeekBarChangeListener() {
                                    @Override
                                    public void onStartedSeeking() {

                                    }

                                    @Override
                                    public void onStoppedSeeking() {

                                    }

                                    @Override
                                    public void onValueChanged(int i, int i1) {

                                        minAreaTv.setText(String.valueOf(i) + " " + "Hectare");
                                        maxAreaTv.setText(String.valueOf(i1) + " " + "Hectare");

                                        minAreaEdtx.setText(String.valueOf(i));
                                        maxAreaEdtx.setText(String.valueOf(i1));

                                        Log.d("SEEKBARLOGCHECK", "MinValue =" + " " + i + " " + " " + " " + "MaxValue =" + " " + i1);

                                    }
                                });

                                break;

                            case 2:

                                rangeSeekBarArea.setMinRange(1);
                                rangeSeekBarArea.setMax(100);

                                minAreaTv.setText("1 Guntha");
                                maxAreaTv.setText("40 Guntha");

                                rangeSeekBarArea.setMinThumbValue(1);

                                rangeSeekBarArea.setMaxThumbValue(40);

                                minAreaEdtx.setText("1");
                                maxAreaEdtx.setText("40");

                                //rangeSeekBarArea.setMax(2000);

                                rangeSeekBarArea.setSeekBarChangeListener(new RangeSeekBar.SeekBarChangeListener() {
                                    @Override
                                    public void onStartedSeeking() {

                                    }

                                    @Override
                                    public void onStoppedSeeking() {

                                    }

                                    @Override
                                    public void onValueChanged(int i, int i1) {

                                        minAreaTv.setText(String.valueOf(i) + " " + "Guntha");
                                        maxAreaTv.setText(String.valueOf(i1) + " " + "Guntha");

                                        minAreaEdtx.setText(String.valueOf(i));
                                        maxAreaEdtx.setText(String.valueOf(i1));

                                        Log.d("SEEKBARLOGCHECK", "MinValue =" + " " + i + " " + " " + " " + "MaxValue =" + " " + i1);

                                    }
                                });

                                break;

                            case 3:

                                rangeSeekBarArea.setMinRange(1);
                                rangeSeekBarArea.setMax(50000);

                                minAreaTv.setText("1 Sqft");
                                maxAreaTv.setText("10000 Sqft");

                                rangeSeekBarArea.setMinThumbValue(1);

                                rangeSeekBarArea.setMaxThumbValue(10000);

                                minAreaEdtx.setText("1");
                                maxAreaEdtx.setText("10000");

                                //rangeSeekBarArea.setMax(2000);

                                rangeSeekBarArea.setSeekBarChangeListener(new RangeSeekBar.SeekBarChangeListener() {
                                    @Override
                                    public void onStartedSeeking() {

                                    }

                                    @Override
                                    public void onStoppedSeeking() {

                                    }

                                    @Override
                                    public void onValueChanged(int i, int i1) {

                                        minAreaTv.setText(String.valueOf(i) + " " + "Sqft");
                                        maxAreaTv.setText(String.valueOf(i1) + " " + "Sqft");

                                        minAreaEdtx.setText(String.valueOf(i));
                                        maxAreaEdtx.setText(String.valueOf(i1));

                                        Log.d("SEEKBARLOGCHECK", "MinValue =" + " " + i + " " + " " + " " + "MaxValue =" + " " + i1);

                                    }
                                });

                                break;

                            case 4:
                                rangeSeekBarArea.setMinRange(1);
                                rangeSeekBarArea.setMax(50000);

                                minAreaTv.setText("1 Sqmtr");
                                maxAreaTv.setText("10000 Sqmtr");

                                rangeSeekBarArea.setMinThumbValue(1);

                                rangeSeekBarArea.setMaxThumbValue(10000);

                                minAreaEdtx.setText("1");
                                maxAreaEdtx.setText("10000");

                                //rangeSeekBarArea.setMax(2000);

                                rangeSeekBarArea.setSeekBarChangeListener(new RangeSeekBar.SeekBarChangeListener() {
                                    @Override
                                    public void onStartedSeeking() {

                                    }

                                    @Override
                                    public void onStoppedSeeking() {

                                    }

                                    @Override
                                    public void onValueChanged(int i, int i1) {

                                        minAreaTv.setText(String.valueOf(i) + " " + "Sqmtr");
                                        maxAreaTv.setText(String.valueOf(i1) + " " + "Sqmtr");

                                        minAreaEdtx.setText(String.valueOf(i));
                                        maxAreaEdtx.setText(String.valueOf(i1));

                                        Log.d("SEEKBARLOGCHECK", "MinValue =" + " " + i + " " + " " + " " + "MaxValue =" + " " + i1);

                                    }
                                });

                                break;

                        }


                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }
        }

    }


    //  ---------- "applyFilters()" method description --------
    // In this applyFilters() method applying filters of all the types for buy is done.
    // Yeah.. this method seems way too much complicated but as there was no option left for me cause we're running out of time that's why
    // i have to do it like that, all the filters applied to show filtered list code work has done properly and well tested programatically. The code is messy but works perfectly.
    // Please be patient and once go through this...

    private void applyFilters(String propertyType) {

        // ------- Universal Common Properties of Sell Property ---------

        String minAreaStr = minAreaEdtx.getText().toString().trim();
        String maxAreaStr = maxAreaEdtx.getText().toString().trim();

        String areaTypeStr = propAreaSpn.getSelectedItem().toString().trim();

        String minBudgetStr = String.valueOf(minBudget);
        String maxBudgetStr = String.valueOf(maxBudget);

        String minDepositeStr = String.valueOf(minDeposite);
        String maxDepositeStr = String.valueOf(maxDeposite);

        String areaMeasurementType = propAreaSpn.getSelectedItem().toString().trim();

        String exclusivePropsStr = "";
        String verifiedPropsStr = "";
        String propsWithDiscsAndOffersStr = "";

        if (isExclusivePropChecked) {
            exclusivePropsStr = "Exclusive Properties";
        } else {
            exclusivePropsStr = "";
        }

        if (isVerifiedPropChecked) {
            verifiedPropsStr = "Verified Properties";
        } else {
            verifiedPropsStr = "";
        }

        if (isPropsWithDiscsAndOffChecked) {
            propsWithDiscsAndOffersStr = "Properties With Discounts And Offers";
        } else {
            propsWithDiscsAndOffersStr = "";
        }

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

        String postedSince = "";

        JSONArray postedSinceJSONArray = new JSONArray();

        for (int i = 0; i < lstcategorypostSince.size(); i++) {

            try {
                JSONObject jo = new JSONObject();

                jo.put("Posted Since" + " " + i, lstcategorypostSince.get(i).getTitle());

                if (lstcategorypostSince.get(i).isSelected()) {
                    postedSinceJSONArray.put(jo);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        if (postedSinceJSONArray.length() > 0) {
            postedSince = postedSinceJSONArray.toString().trim();
        }

        String postedByAgentStr = "", postedByOwnerStr = "", postedByBuilder = "";

        if (agengtFlag) {
            postedByAgentStr = "Agent";
        } else {
            postedByAgentStr = "";
        }

        if (ownerFlag) {
            postedByOwnerStr = "Owner";
        } else {
            postedByOwnerStr = "";
        }

        if (builderFlag) {
            postedByBuilder = "Builder";
        } else {
            postedByBuilder = "";
        }

        JSONArray locationJSONArray = new JSONArray();
        String locationStrJsonArr = "";

        for (int i = 0; i < locationsList.size(); i++) {

            try {
                JSONObject jo = new JSONObject();

                jo.put("Location Id", locationsList.get(i).getId());
                jo.put("Location", locationsList.get(i).getCityName());

                locationJSONArray.put(jo);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        locationStrJsonArr = locationJSONArray.toString().trim();

        String refundableDepositeStr = "", nonRefundableDepositeStr = "";

        if (refundableFlag) {
            refundableDepositeStr = "Refundable";
        } else {
            refundableDepositeStr = "";
        }

        if (nonRefundableFlag) {
            nonRefundableDepositeStr = "Non-Refundable";
        } else {
            nonRefundableDepositeStr = "";
        }


        /*if (minAreaStr.matches("")) {

            propAreaTitleTv.requestFocus();
            propAreaTitleTv.getParent().requestChildFocus(propAreaTitleTv, propAreaTitleTv);
            displayWarning("Select Minimum Area");

        } else if(minAreaStr.matches("0")) {

            propAreaTitleTv.requestFocus();
            propAreaTitleTv.getParent().requestChildFocus(propAreaTitleTv, propAreaTitleTv);
            displayWarning("Select Minimum Area");

        } else if(maxAreaStr.matches("")) {

            propAreaTitleTv.requestFocus();
            propAreaTitleTv.getParent().requestChildFocus(propAreaTitleTv, propAreaTitleTv);
            displayWarning("Select Maximum Area");

        } else if(maxAreaStr.matches("0")) {

            propAreaTitleTv.requestFocus();
            propAreaTitleTv.getParent().requestChildFocus(propAreaTitleTv, propAreaTitleTv);
            displayWarning("Select Maximum Area");

        } else if(areaMeasurementType.matches("Select Area Type")){

            propAreaTitleTv.requestFocus();
            propAreaTitleTv.getParent().requestChildFocus(propAreaTitleTv, propAreaTitleTv);
            displayWarning("Select Area Type");

        } else if(minBudgetStr.matches("")){

            budgetTitleTv.requestFocus();
            budgetTitleTv.getParent().requestChildFocus(budgetTitleTv, budgetTitleTv);
            displayWarning("Select Minimum Budget");

        } else if(minBudgetStr.matches("0")){

            budgetTitleTv.requestFocus();
            budgetTitleTv.getParent().requestChildFocus(budgetTitleTv, budgetTitleTv);
            displayWarning("Select Minimum Budget");

        } else if(maxBudgetStr.matches("")){

            budgetTitleTv.requestFocus();
            budgetTitleTv.getParent().requestChildFocus(budgetTitleTv, budgetTitleTv);
            displayWarning("Select Maximum Budget");

        } else if(maxBudgetStr.matches("0")){

            budgetTitleTv.requestFocus();
            budgetTitleTv.getParent().requestChildFocus(budgetTitleTv, budgetTitleTv);
            displayWarning("Select Maximum Budget");

        } else if(maxBudgetStr.matches("1")){

            budgetTitleTv.requestFocus();
            budgetTitleTv.getParent().requestChildFocus(budgetTitleTv, budgetTitleTv);
            displayWarning("Select Maximum Budget Greater Than One Lakh");

        } else if(amenitiesJSONArray.length() < 0){

            amenitiesTitleTv.requestFocus();
            amenitiesTitleTv.getParent().requestChildFocus(amenitiesTitleTv, amenitiesTitleTv);
            displayWarning("Select Amenities");

        } else if(postedBy.matches("")){

            postedByTitleTv.requestFocus();
            postedByTitleTv.getParent().requestChildFocus(postedByTitleTv, postedByTitleTv);
            displayWarning("Select Posted By");

        }*/


        // ----*----*----*----*----*----*----*----*----*----*----*----*----*----*----*----*----*----*----*----*----*----*----
        // ----*----*----*----*----*----*----*---- Category Wise Properties ----*----*----*----*----*----*----*----*---*---*----

        if (propertyType.matches("Flat")) {

            String rentalAgreementStr = "", llAgreementStr = "";

            if (rentalAgreementType) {
                rentalAgreementStr = "Rental Agreement";
            } else {
                rentalAgreementStr = "";
            }

            if (llAgreementType) {
                llAgreementStr = "Leave and licence Agreement";
            } else {
                llAgreementStr = "";
            }

            String bachelorsTenantsStr = "", familyTenantsStr = "";

            if (bachelorsFlag) {
                bachelorsTenantsStr = "Bachelors";
            } else {
                bachelorsTenantsStr = "";
            }

            if (familyFlag) {
                familyTenantsStr = "Family";
            } else {
                familyTenantsStr = "";
            }


            String bedroomStr = "";

            JSONArray bedroomsJSONArray = new JSONArray();

            for (int i = 0; i < lstcategoryBedrooms.size(); i++) {

                try {
                    JSONObject jo = new JSONObject();

                    jo.put("Bedrooms" + " " + i, lstcategoryBedrooms.get(i).getBedroom());

                    if (lstcategoryBedrooms.get(i).isSelected()) {
                        bedroomsJSONArray.put(jo);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            if (bedroomsJSONArray.length() > 0) {
                bedroomStr = bedroomsJSONArray.toString().trim();
            }


            String bathroomStr = "";

            JSONArray bathroomJSONArray = new JSONArray();

            for (int i = 0; i < lstcategoryBathroom.size(); i++) {

                try {
                    JSONObject jo = new JSONObject();

                    jo.put("Bathrooms" + " " + i, lstcategoryBathroom.get(i).getTitle());

                    if (lstcategoryBathroom.get(i).isSelected()) {
                        bathroomJSONArray.put(jo);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            if (bathroomJSONArray.length() > 0) {
                bathroomStr = bathroomJSONArray.toString().trim();
            }

            String unfurnishedStr = "", semiFurnishedStr = "", fullyFurnishedStr = "";

            if (wantToInclUnfurnished) {
                unfurnishedStr = "Unfurnished";
            } else {
                unfurnishedStr = "";
            }

            if (wantInclSemiFurnished) {
                semiFurnishedStr = "Semi Furnished";
            } else {
                semiFurnishedStr = "";
            }

            if (wantInclFullyFurnished) {
                fullyFurnishedStr = "Fully Furnished";
            } else {
                fullyFurnishedStr = "";
            }

            String possesionMonth = "", possesionYear = "";

            possesionMonth = monthPosSpin.getSelectedItem().toString().trim();
            possesionYear = yearPosSPin.getSelectedItem().toString().trim();

            String societyStr = "";
            societyStr = societySpin.getSelectedItem().toString().trim();


            Toast.makeText(activity, "Applying For Flat", Toast.LENGTH_SHORT).show();

            Log.d("SUBMIT_FLAT_LOG", "\n\n ---------------- Flat ------------------------ \n\n");

            Log.d("SUBMIT_FLAT_LOG", "Location =" + " " + locationStrJsonArr);

            Log.d("SUBMIT_FLAT_LOG", "Rental Agreement Type =" + " " + rentalAgreementStr);
            Log.d("SUBMIT_FLAT_LOG", "Leave And Licenece Agreement Type =" + " " + llAgreementStr);

            Log.d("SUBMIT_FLAT_LOG", "Bachelors Tenents =" + " " + bachelorsTenantsStr);
            Log.d("SUBMIT_FLAT_LOG", "Family Tenants =" + " " + familyTenantsStr);

            Log.d("SUBMIT_FLAT_LOG", "Bedroom =" + " " + bedroomStr);
            Log.d("SUBMIT_FLAT_LOG", "Bathroom =" + " " + bathroomStr);
            Log.d("SUBMIT_FLAT_LOG", "Flat Area Type =" + " " + areaMeasurementType);
            Log.d("SUBMIT_FLAT_LOG", "Minimum Area =" + " " + minAreaStr);
            Log.d("SUBMIT_FLAT_LOG", "Maximum Area =" + " " + maxAreaStr);

            Log.d("SUBMIT_FLAT_LOG", "Unfurnished Status =" + " " + unfurnishedStr);
            Log.d("SUBMIT_FLAT_LOG", "Semi-Furnished Status =" + " " + semiFurnishedStr);
            Log.d("SUBMIT_FLAT_LOG", "Fully Furnished Status =" + " " + fullyFurnishedStr);

            Log.d("SUBMIT_FLAT_LOG", "Minimum Budget =" + " " + minBudgetStr);
            Log.d("SUBMIT_FLAT_LOG", "Maximum Budget =" + " " + maxBudgetStr);

            Log.d("SUBMIT_FLAT_LOG", "Maximum Deposite =" + " " + minDepositeStr);
            Log.d("SUBMIT_FLAT_LOG", "Maximum Deposite =" + " " + maxDepositeStr);

            Log.d("SUBMIT_FLAT_LOG", "Refundable =" + " " + refundableDepositeStr);
            Log.d("SUBMIT_FLAT_LOG", "Non Refundable =" + " " + nonRefundableDepositeStr);

            Log.d("SUBMIT_FLAT_LOG", "Possesion year =" + " " + possesionYear);
            Log.d("SUBMIT_FLAT_LOG", "Possesion month =" + " " + possesionMonth);

            Log.d("SUBMIT_FLAT_LOG", "Society =" + " " + societyStr);

            Log.d("SUBMIT_FLAT_LOG", "Amenities =" + " " + amenitiesStr);
            Log.d("SUBMIT_FLAT_LOG", "Posted Since =" + " " + postedSince);

            Log.d("SUBMIT_FLAT_LOG", "Posted By Agent =" + " " + postedByAgentStr);
            Log.d("SUBMIT_FLAT_LOG", "Posted By Owner =" + " " + postedByOwnerStr);
            Log.d("SUBMIT_FLAT_LOG", "Posted By Builder =" + " " + postedByBuilder);

            Log.d("SUBMIT_FLAT_LOG", "Exclusive Properties =" + " " + exclusivePropsStr);
            Log.d("SUBMIT_FLAT_LOG", "Verfieid Properties =" + " " + verifiedPropsStr);
            Log.d("SUBMIT_FLAT_LOG", "Properties With Discount And Offers =" + " " + propsWithDiscsAndOffersStr);

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

            String rentalAgreementStr = "", llAgreementStr = "";

            if (rentalAgreementType) {
                rentalAgreementStr = "Rental Agreement";
            } else {
                rentalAgreementStr = "";
            }

            if (llAgreementType) {
                llAgreementStr = "Leave and licence Agreement";
            } else {
                llAgreementStr = "";
            }

            String rccHouseVillaStr = "", loadBearingHouseVillaStr = "", otherHouseVillaStr = "";

            if (rccHouseVilla) {
                rccHouseVillaStr = "RCC";
            } else {
                rccHouseVillaStr = "";
            }

            if (loadBearingHouseVilla) {
                loadBearingHouseVillaStr = "Load Bearing";
            } else {
                loadBearingHouseVillaStr = "";
            }

            if (otherHouseVilla) {
                otherHouseVillaStr = "Other";
            } else {
                otherHouseVillaStr = "";
            }

            String bachelorsTenantsStr = "", familyTenantsStr = "";

            if (bachelorsFlag) {
                bachelorsTenantsStr = "Bachelors";
            } else {
                bachelorsTenantsStr = "";
            }

            if (familyFlag) {
                familyTenantsStr = "Family";
            } else {
                familyTenantsStr = "";
            }


            String houseRoomsStr = "";

            JSONArray houseRoomsJSONArray = new JSONArray();

            for (int i = 0; i < lstcategoryBedrooms.size(); i++) {

                try {
                    JSONObject jo = new JSONObject();

                    jo.put("Bedrooms" + " " + i, lstcategoryBedrooms.get(i).getBedroom());

                    if (lstcategoryBedrooms.get(i).isSelected()) {
                        houseRoomsJSONArray.put(jo);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            if (houseRoomsJSONArray.length() > 0) {
                houseRoomsStr = houseRoomsJSONArray.toString().trim();
            }

            // ---- Residential Exclusive Logical Properties for House & Villa----

            String houseFloors = floorLevelSpin.getSelectedItem().toString().trim();

            String bathroomStr = "";

            JSONArray bathroomJSONArray = new JSONArray();

            for (int i = 0; i < lstcategoryBathroom.size(); i++) {

                try {
                    JSONObject jo = new JSONObject();

                    jo.put("Bathrooms" + " " + i, lstcategoryBathroom.get(i).getTitle());

                    if (lstcategoryBathroom.get(i).isSelected()) {
                        bathroomJSONArray.put(jo);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            if (bathroomJSONArray.length() > 0) {
                bathroomStr = bathroomJSONArray.toString().trim();
            }

            String unfurnishedStr = "", semiFurnishedStr = "", fullyFurnishedStr = "";

            if (wantToInclUnfurnished) {
                unfurnishedStr = "Unfurnished";
            } else {
                unfurnishedStr = "";
            }

            if (wantInclSemiFurnished) {
                semiFurnishedStr = "Semi Furnished";
            } else {
                semiFurnishedStr = "";
            }

            if (wantInclFullyFurnished) {
                fullyFurnishedStr = "Fully Furnished";
            } else {
                fullyFurnishedStr = "";
            }

            String possesionMonth = "", possesionYear = "";

            possesionMonth = monthPosSpin.getSelectedItem().toString().trim();
            possesionYear = yearPosSPin.getSelectedItem().toString().trim();

            Toast.makeText(activity, "Applying For House/Villa", Toast.LENGTH_SHORT).show();

            Log.d("SUBMIT_FLAT_LOG", "\n\n --------------- House / Villa ------------------------ \n\n");

            Log.d("SUBMIT_FLAT_LOG", "Location =" + " " + locationStrJsonArr);

            Log.d("SUBMIT_FLAT_LOG", "Rental Agreement Type =" + " " + rentalAgreementStr);
            Log.d("SUBMIT_FLAT_LOG", "Leave And Licenece Agreement Type =" + " " + llAgreementStr);

            Log.d("SUBMIT_FLAT_LOG", "Bachelors Tenents =" + " " + bachelorsTenantsStr);
            Log.d("SUBMIT_FLAT_LOG", "Family Tenants =" + " " + familyTenantsStr);

            Log.d("SUBMIT_FLAT_LOG", "RCC House =" + " " + rccHouseVillaStr);
            Log.d("SUBMIT_FLAT_LOG", "Load Bearing =" + " " + loadBearingHouseVillaStr);
            Log.d("SUBMIT_FLAT_LOG", "Other House =" + " " + otherHouseVillaStr);

            Log.d("SUBMIT_FLAT_LOG", "Bedroom =" + " " + houseRoomsStr);
            Log.d("SUBMIT_FLAT_LOG", "House Floors =" + " " + houseFloors);
            Log.d("SUBMIT_FLAT_LOG", "Bathroom =" + " " + bathroomStr);
            Log.d("SUBMIT_FLAT_LOG", "Flat Area Type =" + " " + areaMeasurementType);
            Log.d("SUBMIT_FLAT_LOG", "Minimum Area =" + " " + minAreaStr);
            Log.d("SUBMIT_FLAT_LOG", "Maximum Area =" + " " + maxAreaStr);

            Log.d("SUBMIT_FLAT_LOG", "Unfurnished Status =" + " " + unfurnishedStr);
            Log.d("SUBMIT_FLAT_LOG", "Semi-Furnished Status =" + " " + semiFurnishedStr);
            Log.d("SUBMIT_FLAT_LOG", "Fully Furnished Status =" + " " + fullyFurnishedStr);

            Log.d("SUBMIT_FLAT_LOG", "Minimum Budget =" + " " + minBudgetStr);
            Log.d("SUBMIT_FLAT_LOG", "Maximum Budget =" + " " + maxBudgetStr);

            Log.d("SUBMIT_FLAT_LOG", "Minimum Deposite =" + " " + minDepositeStr);
            Log.d("SUBMIT_FLAT_LOG", "Maximum Deposite =" + " " + maxDepositeStr);

            Log.d("SUBMIT_FLAT_LOG", "Refundable =" + " " + refundableDepositeStr);
            Log.d("SUBMIT_FLAT_LOG", "Non Refundable =" + " " + nonRefundableDepositeStr);

            Log.d("SUBMIT_FLAT_LOG", "Possesion year =" + " " + possesionYear);
            Log.d("SUBMIT_FLAT_LOG", "Possesion month =" + " " + possesionMonth);

            Log.d("SUBMIT_FLAT_LOG", "Amenities =" + " " + amenitiesStr);
            Log.d("SUBMIT_FLAT_LOG", "Posted Since =" + " " + postedSince);

            Log.d("SUBMIT_FLAT_LOG", "Posted By Agent =" + " " + postedByAgentStr);
            Log.d("SUBMIT_FLAT_LOG", "Posted By Owner =" + " " + postedByOwnerStr);
            Log.d("SUBMIT_FLAT_LOG", "Posted By Builder =" + " " + postedByBuilder);

            Log.d("SUBMIT_FLAT_LOG", "Exclusive Properties =" + " " + exclusivePropsStr);
            Log.d("SUBMIT_FLAT_LOG", "Verfieid Properties =" + " " + verifiedPropsStr);
            Log.d("SUBMIT_FLAT_LOG", "Properties With Discount And Offers =" + " " + propsWithDiscsAndOffersStr);


        }

        else if (propertyType.matches("NA Plot")) {

            // ---- Residential Exclusive Logical Properties for House & Villa ----

            String recidentialNaPlotStr = "", commercialNaPlotStr = "", industrialNaPlotStr = "";

            if (recidentialPlotFlag) {
                recidentialNaPlotStr = "Recidential";
            } else {
                recidentialNaPlotStr = "";
            }

            if (commercialPlotFlag) {
                commercialNaPlotStr = "Commercial";
            } else {
                commercialNaPlotStr = "";
            }

            if (industrialPlotFlag) {
                industrialNaPlotStr = "Industrial";
            } else {
                industrialNaPlotStr = "";
            }

            Toast.makeText(activity, "Applying for NA Plot", Toast.LENGTH_SHORT).show();

            Log.d("SUBMIT_FLAT_LOG", "\n\n --------------- NA Plot ------------------------ \n\n");

            Log.d("SUBMIT_FLAT_LOG", "Location =" + " " + locationStrJsonArr);

            Log.d("SUBMIT_FLAT_LOG", "Residential House =" + " " + recidentialNaPlotStr);
            Log.d("SUBMIT_FLAT_LOG", "Commercial Bearing =" + " " + commercialNaPlotStr);
            Log.d("SUBMIT_FLAT_LOG", "Industrial House =" + " " + industrialNaPlotStr);

            Log.d("SUBMIT_FLAT_LOG", "Flat Area Type =" + " " + areaMeasurementType);
            Log.d("SUBMIT_FLAT_LOG", "Minimum Area =" + " " + minAreaStr);
            Log.d("SUBMIT_FLAT_LOG", "Maximum Area =" + " " + maxAreaStr);

            Log.d("SUBMIT_FLAT_LOG", "Minimum Budget =" + " " + minBudgetStr);
            Log.d("SUBMIT_FLAT_LOG", "Maximum Budget =" + " " + maxBudgetStr);

            Log.d("SUBMIT_FLAT_LOG", "Minimum Deposite =" + " " + minDepositeStr);
            Log.d("SUBMIT_FLAT_LOG", "Maximum Deposite =" + " " + maxDepositeStr);

            Log.d("SUBMIT_FLAT_LOG", "Refundable =" + " " + refundableDepositeStr);
            Log.d("SUBMIT_FLAT_LOG", "Non Refundable =" + " " + nonRefundableDepositeStr);

            Log.d("SUBMIT_FLAT_LOG", "Amenities =" + " " + amenitiesStr);
            Log.d("SUBMIT_FLAT_LOG", "Posted Since =" + " " + postedSince);

            Log.d("SUBMIT_FLAT_LOG", "Posted By Agent =" + " " + postedByAgentStr);
            Log.d("SUBMIT_FLAT_LOG", "Posted By Owner =" + " " + postedByOwnerStr);
            Log.d("SUBMIT_FLAT_LOG", "Posted By Builder =" + " " + postedByBuilder);

            Log.d("SUBMIT_FLAT_LOG", "Exclusive Properties =" + " " + exclusivePropsStr);
            Log.d("SUBMIT_FLAT_LOG", "Verifieid Properties =" + " " + verifiedPropsStr);
            Log.d("SUBMIT_FLAT_LOG", "Properties With Discount And Offers =" + " " + propsWithDiscsAndOffersStr);

        }

        else if (propertyType.matches("Agricultural Land")) {

            Toast.makeText(activity, "Applying For Agricultural Land", Toast.LENGTH_SHORT).show();

            Log.d("SUBMIT_FLAT_LOG", "\n\n --------------- Agricultural Land ------------------------ \n\n");

            Log.d("SUBMIT_FLAT_LOG", "Location =" + " " + locationStrJsonArr);

            Log.d("SUBMIT_FLAT_LOG", "Flat Area Type =" + " " + areaMeasurementType);
            Log.d("SUBMIT_FLAT_LOG", "Minimum Area =" + " " + minAreaStr);
            Log.d("SUBMIT_FLAT_LOG", "Maximum Area =" + " " + maxAreaStr);

            Log.d("SUBMIT_FLAT_LOG", "Minimum Budget =" + " " + minBudgetStr);
            Log.d("SUBMIT_FLAT_LOG", "Maximum Budget =" + " " + maxBudgetStr);

            Log.d("SUBMIT_FLAT_LOG", "Maximum Deposite =" + " " + minDepositeStr);
            Log.d("SUBMIT_FLAT_LOG", "Maximum Deposite =" + " " + maxDepositeStr);

            Log.d("SUBMIT_FLAT_LOG", "Refundable =" + " " + refundableDepositeStr);
            Log.d("SUBMIT_FLAT_LOG", "Non Refundable =" + " " + nonRefundableDepositeStr);

            Log.d("SUBMIT_FLAT_LOG", "Amenities =" + " " + amenitiesStr);
            Log.d("SUBMIT_FLAT_LOG", "Posted Since =" + " " + postedSince);

            Log.d("SUBMIT_FLAT_LOG", "Posted By Agent =" + " " + postedByAgentStr);
            Log.d("SUBMIT_FLAT_LOG", "Posted By Owner =" + " " + postedByOwnerStr);
            Log.d("SUBMIT_FLAT_LOG", "Posted By Builder =" + " " + postedByBuilder);

            Log.d("SUBMIT_FLAT_LOG", "Exclusive Properties =" + " " + exclusivePropsStr);
            Log.d("SUBMIT_FLAT_LOG", "Verfieid Properties =" + " " + verifiedPropsStr);
            Log.d("SUBMIT_FLAT_LOG", "Properties With Discount And Offers =" + " " + propsWithDiscsAndOffersStr);

        }

        else if (propertyType.matches("Office Space")) {

            // ---- Office Space Exclusive Logical Properties -----

            Toast.makeText(activity, "Applying For Office Space", Toast.LENGTH_SHORT).show();

            Log.d("SUBMIT_FLAT_LOG", "\n\n --------------- Office Space ------------------------ \n\n");

            Log.d("SUBMIT_FLAT_LOG", "Location =" + " " + locationStrJsonArr);

            String cabins = cabinSpin.getSelectedItem().toString().trim();
            String conferenceRoom = conferenceSpin.getSelectedItem().toString().trim();
            String receptions = receptionSpin.getSelectedItem().toString().trim();

            String bathroomStr = "";

            JSONArray bathroomJSONArray = new JSONArray();

            for (int i = 0; i < lstcategoryBathroom.size(); i++) {

                try {
                    JSONObject jo = new JSONObject();

                    jo.put("Bedrooms" + " " + i, lstcategoryBathroom.get(i).getTitle());

                    if (lstcategoryBathroom.get(i).isSelected()) {
                        bathroomJSONArray.put(jo);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            if (bathroomJSONArray.length() > 0) {
                bathroomStr = bathroomJSONArray.toString().trim();
            }

            String unfurnishedStr = "", semiFurnishedStr = "", fullyFurnishedStr = "";

            if (wantToInclUnfurnished) {
                unfurnishedStr = "Unfurnished";
            } else {
                unfurnishedStr = "";
            }

            if (wantInclSemiFurnished) {
                semiFurnishedStr = "Semi Furnished";
            } else {
                semiFurnishedStr = "";
            }

            if (wantInclFullyFurnished) {
                fullyFurnishedStr = "Fully Furnished";
            } else {
                fullyFurnishedStr = "";
            }


            String possesionMonth = "", possesionYear = "";

            possesionMonth = monthPosSpin.getSelectedItem().toString().trim();
            possesionYear = yearPosSPin.getSelectedItem().toString().trim();


            Toast.makeText(activity, "Applying For Office Space", Toast.LENGTH_SHORT).show();

            Log.d("SUBMIT_FLAT_LOG", "\n\n --------------- Office Space ------------------------ \n\n");

            Log.d("SUBMIT_FLAT_LOG", "Location =" + " " + locationStrJsonArr);
            Log.d("SUBMIT_FLAT_LOG", "Cabins =" + " " + cabins);
            Log.d("SUBMIT_FLAT_LOG", "Conference Rooms =" + " " + conferenceRoom);
            Log.d("SUBMIT_FLAT_LOG", "Receptions =" + " " + receptions);

            Log.d("SUBMIT_FLAT_LOG", "Bathroom =" + " " + bathroomStr);

            Log.d("SUBMIT_FLAT_LOG", "Area Measurement Type =" + " " + areaMeasurementType);
            Log.d("SUBMIT_FLAT_LOG", "Office Min Area =" + " " + minAreaStr);
            Log.d("SUBMIT_FLAT_LOG", "Office Max Area =" + " " + maxAreaStr);

            Log.d("SUBMIT_FLAT_LOG", "Unfurnished =" + " " + unfurnishedStr);
            Log.d("SUBMIT_FLAT_LOG", "Semi-Furnished =" + " " + semiFurnishedStr);
            Log.d("SUBMIT_FLAT_LOG", "Fully Furnished =" + " " + fullyFurnishedStr);

            Log.d("SUBMIT_FLAT_LOG", "Minimum Budget =" + " " + minBudgetStr);
            Log.d("SUBMIT_FLAT_LOG", "Maximum Budget =" + " " + maxBudgetStr);

            Log.d("SUBMIT_FLAT_LOG", "Maximum Deposite =" + " " + minDepositeStr);
            Log.d("SUBMIT_FLAT_LOG", "Maximum Deposite =" + " " + maxDepositeStr);

            Log.d("SUBMIT_FLAT_LOG", "Refundable =" + " " + refundableDepositeStr);
            Log.d("SUBMIT_FLAT_LOG", "Non Refundable =" + " " + nonRefundableDepositeStr);

            Log.d("SUBMIT_FLAT_LOG", "Possesion year =" + " " + possesionYear);
            Log.d("SUBMIT_FLAT_LOG", "Possesion month =" + " " + possesionMonth);

            Log.d("SUBMIT_FLAT_LOG", "Amenities =" + " " + amenitiesStr);
            Log.d("SUBMIT_FLAT_LOG", "Posted Since =" + " " + postedSince);

            Log.d("SUBMIT_FLAT_LOG", "Posted By Agent =" + " " + postedByAgentStr);
            Log.d("SUBMIT_FLAT_LOG", "Posted By Owner =" + " " + postedByOwnerStr);
            Log.d("SUBMIT_FLAT_LOG", "Posted By Builder =" + " " + postedByBuilder);

            Log.d("SUBMIT_FLAT_LOG", "Exclusive Properties =" + " " + exclusivePropsStr);
            Log.d("SUBMIT_FLAT_LOG", "Verfieid Properties =" + " " + verifiedPropsStr);
            Log.d("SUBMIT_FLAT_LOG", "Properties With Discount And Offers =" + " " + propsWithDiscsAndOffersStr);


        }

        else if (propertyType.matches("Shop/Showroom")) {

            // ---- Shop/Showroom Exclusive Logical Properties -----

            String facingStr = "";
            JSONArray facingJSONArray = new JSONArray();

            for (int i = 0; i < lstcategoryFacing.size(); i++) {

                try {
                    JSONObject jo = new JSONObject();

                    jo.put("Bedrooms" + " " + i, lstcategoryFacing.get(i).getTitle());

                    if (lstcategoryFacing.get(i).isSelected()) {
                        facingJSONArray.put(jo);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            if (facingJSONArray.length() > 0) {
                facingStr = facingJSONArray.toString().trim();
            }

            String cabins = cabinSpin.getSelectedItem().toString().trim();
            String conferenceRoom = conferenceSpin.getSelectedItem().toString().trim();
            String receptions = receptionSpin.getSelectedItem().toString().trim();

            String bathroomStr = "";

            JSONArray bathroomJSONArray = new JSONArray();

            for (int i = 0; i < lstcategoryBathroom.size(); i++) {

                try {
                    JSONObject jo = new JSONObject();

                    jo.put("Bedrooms" + " " + i, lstcategoryBathroom.get(i).getTitle());

                    if (lstcategoryBathroom.get(i).isSelected()) {
                        bathroomJSONArray.put(jo);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            if (bathroomJSONArray.length() > 0) {
                bathroomStr = bathroomJSONArray.toString().trim();
            }

            String unfurnishedStr = "", semiFurnishedStr = "", fullyFurnishedStr = "";

            if (wantToInclUnfurnished) {
                unfurnishedStr = "Unfurnished";
            } else {
                unfurnishedStr = "";
            }

            if (wantInclSemiFurnished) {
                semiFurnishedStr = "Semi Furnished";
            } else {
                semiFurnishedStr = "";
            }

            if (wantInclFullyFurnished) {
                fullyFurnishedStr = "Fully Furnished";
            } else {
                fullyFurnishedStr = "";
            }

            String possesionMonth = "", possesionYear = "";

            possesionMonth = monthPosSpin.getSelectedItem().toString().trim();
            possesionYear = yearPosSPin.getSelectedItem().toString().trim();


            Toast.makeText(activity, "Apllying For Shop/Showroom", Toast.LENGTH_SHORT).show();

            Log.d("SUBMIT_FLAT_LOG", "\n\n --------------- Shop/Showroom ------------------------ \n\n");

            Log.d("SUBMIT_FLAT_LOG", "Location =" + " " + locationStrJsonArr);
            Log.d("SUBMIT_FLAT_LOG", "Cabins =" + " " + cabins);
            Log.d("SUBMIT_FLAT_LOG", "Conference Rooms =" + " " + conferenceRoom);
            Log.d("SUBMIT_FLAT_LOG", "Receptions =" + " " + receptions);

            Log.d("SUBMIT_FLAT_LOG", "Bathroom =" + " " + bathroomStr);

            Log.d("SUBMIT_FLAT_LOG", "Area Measurement Type =" + " " + areaMeasurementType);
            Log.d("SUBMIT_FLAT_LOG", "Office Min Area =" + " " + minAreaStr);
            Log.d("SUBMIT_FLAT_LOG", "Office Max Area =" + " " + maxAreaStr);

            Log.d("SUBMIT_FLAT_LOG", "Unfurnished =" + " " + unfurnishedStr);
            Log.d("SUBMIT_FLAT_LOG", "Semi-Furnished =" + " " + semiFurnishedStr);
            Log.d("SUBMIT_FLAT_LOG", "Fully Furnished =" + " " + fullyFurnishedStr);

            Log.d("SUBMIT_FLAT_LOG", "Minimum Budget =" + " " + minBudgetStr);
            Log.d("SUBMIT_FLAT_LOG", "Maximum Budget =" + " " + maxBudgetStr);

            Log.d("SUBMIT_FLAT_LOG", "Maximum Deposite =" + " " + minDepositeStr);
            Log.d("SUBMIT_FLAT_LOG", "Maximum Deposite =" + " " + maxDepositeStr);

            Log.d("SUBMIT_FLAT_LOG", "Refundable =" + " " + refundableDepositeStr);
            Log.d("SUBMIT_FLAT_LOG", "Non Refundable =" + " " + nonRefundableDepositeStr);

            Log.d("SUBMIT_FLAT_LOG", "Possesion year =" + " " + possesionYear);
            Log.d("SUBMIT_FLAT_LOG", "Possesion month =" + " " + possesionMonth);

            Log.d("SUBMIT_FLAT_LOG", "Facing =" + " " + facingStr);

            Log.d("SUBMIT_FLAT_LOG", "Amenities =" + " " + amenitiesStr);
            Log.d("SUBMIT_FLAT_LOG", "Posted Since =" + " " + postedSince);

            Log.d("SUBMIT_FLAT_LOG", "Posted By Agent =" + " " + postedByAgentStr);
            Log.d("SUBMIT_FLAT_LOG", "Posted By Owner =" + " " + postedByOwnerStr);
            Log.d("SUBMIT_FLAT_LOG", "Posted By Builder =" + " " + postedByBuilder);

            Log.d("SUBMIT_FLAT_LOG", "Exclusive Properties =" + " " + exclusivePropsStr);
            Log.d("SUBMIT_FLAT_LOG", "Verfieid Properties =" + " " + verifiedPropsStr);
            Log.d("SUBMIT_FLAT_LOG", "Properties With Discount And Offers =" + " " + propsWithDiscsAndOffersStr);

        }

        else if (propertyType.matches("Other Commercial")) {

            // ---- Other Commercial Exclusive Logical Properties -----

            /* String facingStr = "";
            JSONArray facingJSONArray = new JSONArray();

            for (int i = 0; i < lstcategoryFacing.size(); i++) {

                try {
                    JSONObject jo = new JSONObject();

                    jo.put("Bedrooms" + " " + i, lstcategoryFacing.get(i).getTitle());

                    if (lstcategoryFacing.get(i).isSelected()) {
                        facingJSONArray.put(jo);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            if (facingJSONArray.length() > 0) {
                facingStr = facingJSONArray.toString().trim();
            }*/

            String facingStr = "";
            JSONArray facingJSONArray = new JSONArray();

            for (int i = 0; i < lstcategoryFacing.size(); i++) {

                try {
                    JSONObject jo = new JSONObject();

                    jo.put("Bedrooms" + " " + i, lstcategoryFacing.get(i).getTitle());

                    if (lstcategoryFacing.get(i).isSelected()) {
                        facingJSONArray.put(jo);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            if (facingJSONArray.length() > 0) {
                facingStr = facingJSONArray.toString().trim();
            }

            String cabins = cabinSpin.getSelectedItem().toString().trim();
            String conferenceRoom = conferenceSpin.getSelectedItem().toString().trim();
            String receptions = receptionSpin.getSelectedItem().toString().trim();

            String bathroomStr = "";

            JSONArray bathroomJSONArray = new JSONArray();

            for (int i = 0; i < lstcategoryBathroom.size(); i++) {

                try {
                    JSONObject jo = new JSONObject();

                    jo.put("Bedrooms" + " " + i, lstcategoryBathroom.get(i).getTitle());

                    if (lstcategoryBathroom.get(i).isSelected()) {
                        bathroomJSONArray.put(jo);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            if (bathroomJSONArray.length() > 0) {
                bathroomStr = bathroomJSONArray.toString().trim();
            }

            String unfurnishedStr = "", semiFurnishedStr = "", fullyFurnishedStr = "";

            if (wantToInclUnfurnished) {
                unfurnishedStr = "Unfurnished";
            } else {
                unfurnishedStr = "";
            }

            if (wantInclSemiFurnished) {
                semiFurnishedStr = "Semi Furnished";
            } else {
                semiFurnishedStr = "";
            }

            if (wantInclFullyFurnished) {
                fullyFurnishedStr = "Fully Furnished";
            } else {
                fullyFurnishedStr = "";
            }

            String possesionMonth = "", possesionYear = "";

            possesionMonth = monthPosSpin.getSelectedItem().toString().trim();
            possesionYear = yearPosSPin.getSelectedItem().toString().trim();


            Toast.makeText(activity, "Apllying For Shop/Showroom", Toast.LENGTH_SHORT).show();

            Log.d("SUBMIT_FLAT_LOG", "\n\n --------------- Shop/Showroom ------------------------ \n\n");

            Log.d("SUBMIT_FLAT_LOG", "Location =" + " " + locationStrJsonArr);
            Log.d("SUBMIT_FLAT_LOG", "Cabins =" + " " + cabins);
            Log.d("SUBMIT_FLAT_LOG", "Conference Rooms =" + " " + conferenceRoom);
            Log.d("SUBMIT_FLAT_LOG", "Receptions =" + " " + receptions);

            Log.d("SUBMIT_FLAT_LOG", "Bathroom =" + " " + bathroomStr);

            Log.d("SUBMIT_FLAT_LOG", "Area Measurement Type =" + " " + areaMeasurementType);
            Log.d("SUBMIT_FLAT_LOG", "Office Min Area =" + " " + minAreaStr);
            Log.d("SUBMIT_FLAT_LOG", "Office Max Area =" + " " + maxAreaStr);

            Log.d("SUBMIT_FLAT_LOG", "Unfurnished =" + " " + unfurnishedStr);
            Log.d("SUBMIT_FLAT_LOG", "Semi-Furnished =" + " " + semiFurnishedStr);
            Log.d("SUBMIT_FLAT_LOG", "Fully Furnished =" + " " + fullyFurnishedStr);

            Log.d("SUBMIT_FLAT_LOG", "Minimum Budget =" + " " + minBudgetStr);
            Log.d("SUBMIT_FLAT_LOG", "Maximum Budget =" + " " + maxBudgetStr);

            Log.d("SUBMIT_FLAT_LOG", "Maximum Deposite =" + " " + minDepositeStr);
            Log.d("SUBMIT_FLAT_LOG", "Maximum Deposite =" + " " + maxDepositeStr);

            Log.d("SUBMIT_FLAT_LOG", "Refundable =" + " " + refundableDepositeStr);
            Log.d("SUBMIT_FLAT_LOG", "Non Refundable =" + " " + nonRefundableDepositeStr);

            Log.d("SUBMIT_FLAT_LOG", "Possesion year =" + " " + possesionYear);
            Log.d("SUBMIT_FLAT_LOG", "Possesion month =" + " " + possesionMonth);

            Log.d("SUBMIT_FLAT_LOG", "Facing =" + " " + facingStr);

            Log.d("SUBMIT_FLAT_LOG", "Amenities =" + " " + amenitiesStr);
            Log.d("SUBMIT_FLAT_LOG", "Posted Since =" + " " + postedSince);

            Log.d("SUBMIT_FLAT_LOG", "Posted By Agent =" + " " + postedByAgentStr);
            Log.d("SUBMIT_FLAT_LOG", "Posted By Owner =" + " " + postedByOwnerStr);
            Log.d("SUBMIT_FLAT_LOG", "Posted By Builder =" + " " + postedByBuilder);

            Log.d("SUBMIT_FLAT_LOG", "Exclusive Properties =" + " " + exclusivePropsStr);
            Log.d("SUBMIT_FLAT_LOG", "Verfieid Properties =" + " " + verifiedPropsStr);
            Log.d("SUBMIT_FLAT_LOG", "Properties With Discount And Offers =" + " " + propsWithDiscsAndOffersStr);

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


    @Override
    public void onCCSelect(Pojo_CC selectedCC) {

        addLocationsTv.setVisibility(View.GONE);

        //Toast.makeText(activity, "Selected Location Id =" + " " + selectedCC.getId(), Toast.LENGTH_SHORT).show();

        //Toast.makeText(activity, "inside onCCSelect", Toast.LENGTH_SHORT).show();

        //if (locationJSONArray.length() <= 5) {

        boolean isPresent = false;

        Log.d("CHECKINGLOCPRESENT", "Selected Loc Id =" + " " + selectedCC.getId());

        for (int i = 0; i < locationsList.size(); i++){

            Log.d("CHECKINGLOCPRESENT", "i =" + " " + i + "\nLocation Id =" + " " + locationsList.get(i).getId() + "\n\n");

            if(selectedCC.getId().matches(locationsList.get(i).getId())){

                isPresent = true;

                Toast.makeText(activity, "Matches, selected id =" + " " + selectedCC.getId() + "\nlocation list id =" + " " + locationsList.get(i).getId(), Toast.LENGTH_LONG).show();

            }
        }


        if(!isPresent){

            rvLocations.setVisibility(View.VISIBLE);

            locationsList.add(new Pojo_CC(selectedCC.getId(), selectedCC.getCityName(),selectedCC.getLabel(), selectedCC.getCcIdColor()));
            adapterLocationChips.notifyDataSetChanged();

            //Toast.makeText(activity, "locationsList.size() =" + " " + locationsList.size(), Toast.LENGTH_SHORT).show();

                /*try {
                    JSONObject jo = new JSONObject();

                    jo.put("Location Id", selectedCC.getId());
                    jo.put("Location", selectedCC.getCityName());

                    locationJSONArray.put(jo);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                locationStrJsonArr = locationJSONArray.toString().trim();*/

        } else {

            new KAlertDialog(activity, KAlertDialog.WARNING_TYPE)
                    .setTitleText("Warning!")
                    .setContentText("This location is already selected!")
                    .setConfirmClickListener(new KAlertDialog.KAlertClickListener() {
                        @Override
                        public void onClick(KAlertDialog kAlertDialog) {
                            kAlertDialog.dismiss();

                        }
                    })
                    .confirmButtonColor(R.drawable.kalert_button_background)
                    .show();

        }


        /*} else {

            new KAlertDialog(activity, KAlertDialog.WARNING_TYPE)
                    .setTitleText("Warning!")
                    .setContentText("You can select maximum of 5 locations")
                    .setConfirmClickListener(new KAlertDialog.KAlertClickListener() {
                        @Override
                        public void onClick(KAlertDialog kAlertDialog) {
                            kAlertDialog.dismiss();

                        }
                    })
                    .confirmButtonColor(R.drawable.kalert_button_background)
                    .show();

        }*/

    }
}



















