package com.example.realestate;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.example.realestate.CCComponent.DialogLocationSelector;
import com.example.realestate.CCComponent.Pojo_CC;
import com.example.realestate.Projects.FragmentBuyProject;
import com.example.realestate.Projects.RegisterProjects;
import com.example.realestate.RealEstate.FragmentBuy;
import com.example.realestate.RealEstate.FragmentGiveOnRent;
import com.example.realestate.RealEstate.FragmentTakeOnRent;
import com.example.realestate.RealEstate.FragmentProject;
import com.example.realestate.RealEstate.FragmentSell;

public class LoadMenuFragmentsActivity extends AppCompatActivity implements DialogLocationSelector.OnLocationSelectionListener {

    private String menuName = null;
    private String propType = null;

    private IOnBackPressed interf;

    FragmentBuy buy;

    FragmentTakeOnRent takeOnRent;
    FragmentGiveOnRent giveOnRent;
    FragmentBuyProject buyProject;

    private String type = "null";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_menu_fragments);

        //getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        menuName = intent.getStringExtra("MENU_NAME");
        propType = intent.getStringExtra("PROP_TYPE");
        type = intent.getStringExtra("DISPLAY_TYPE");


        if(menuName != null){

            if(menuName.matches("BUY")){

                Bundle bundle = new Bundle();
                bundle.putString("PROP_TYPE", propType);

                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

//                Bundle bundle = new Bundle();
//                bundle.putString("REGISTRAION_ID", id);
//                bundle.putString("CUSTOMER_MOBILE_OTP", cust_mobile);

                //FragmentBuy buy = new FragmentBuy();
                buy = new FragmentBuy();
                buy.setArguments(bundle);
                fragmentTransaction.add(R.id.menuFragmentContainerLayout, buy);
                //fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

                setTitle("Buy Property");

                if(propType.matches("Flat")){
//                    setTitle("Buy Flat");
                    setActionBarTitle("Buy Flat");
                } else if(propType.matches("House/Villa")){
                    setActionBarTitle("Buy House Or Villa");
                } else if(propType.matches("NA Plot")){
                    setActionBarTitle("Buy NA Plot");
                } else if(propType.matches("Agricultural Land")){
                    setActionBarTitle("Buy Agricultural Land");
                } else if(propType.matches("Office Space")){
                    setActionBarTitle("Buy Office Space");
                } else if(propType.matches("Shop/Showroom")){
                    setActionBarTitle("Buy Shop Or Showroom");
                } else if(propType.matches("Other Commercial")){
                    setActionBarTitle("Buy Other Commercial Property");
                }

            } else if(menuName.matches("SELL")){

                if(propType != null) {

                    Bundle bundle = new Bundle();
                    bundle.putString("PROP_TYPE", propType);

                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    FragmentSell sell = new FragmentSell();

                    sell.setArguments(bundle);

                    fragmentTransaction.add(R.id.menuFragmentContainerLayout, sell);
                    fragmentTransaction.commit();

                    setTitle("Sell Property");

                    if(propType.matches("Flat")){
                        setActionBarTitle("Sell Flat");
                    } else if(propType.matches("House/Villa")){
                        setActionBarTitle("Sell House Or Villa");
                    } else if(propType.matches("NA Plot")){
                        setActionBarTitle("Sell Plot");
                    } else if(propType.matches("Agricultural Land")){
                        setActionBarTitle("Sell Agricultural Land");
                    } else if(propType.matches("Office Space")){
                        setActionBarTitle("Sell Office Space");
                    } else if(propType.matches("Shop/Showroom")){
                        setActionBarTitle("Sell Shop Or Showroom");
                    } else if(propType.matches("Other Commercial")){
                        setActionBarTitle("Sell Other Commercial Property");
                    }

                }

            } else if(menuName.matches("RENT")){

                if(!type.matches("null")){

                    if(type.matches("TAKE_ON_RENT")){

                        Bundle bundle = new Bundle();
                        bundle.putString("PROP_TYPE", propType);

                        FragmentManager fragmentManager = getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        //FragmentTakeOnRent rent = new FragmentTakeOnRent();

                        takeOnRent = new FragmentTakeOnRent();

                        takeOnRent.setArguments(bundle);

                        fragmentTransaction.add(R.id.menuFragmentContainerLayout, takeOnRent);
                        fragmentTransaction.commit();

                        setTitle("Rent Property");

                        if(propType.matches("Flat")){
                            setActionBarTitle("Rent/LL Flat");
                        } else if(propType.matches("House/Villa")){
                            setActionBarTitle("Rent/LL House Or Villa");
                        } else if(propType.matches("NA Plot")){
                            setActionBarTitle("Lease Plot");
                        } else if(propType.matches("Agricultural Land")){
                            setActionBarTitle("Lease Agricultural Land");
                        } else if(propType.matches("Office Space")){
                            setActionBarTitle("Lease Office Space");
                        } else if(propType.matches("Shop/Showroom")){
                            setActionBarTitle("Lease Shop Or Showroom");
                        } else if(propType.matches("Other Commercial")){
                            setActionBarTitle("Lease Other Commercial Property");
                        }

                    } else if(type.matches("GIVE_ON_RENT")){

                        Bundle bundle = new Bundle();
                        bundle.putString("PROP_TYPE", propType);

                        FragmentManager fragmentManager = getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        //FragmentTakeOnRent rent = new FragmentTakeOnRent();

                        giveOnRent = new FragmentGiveOnRent();

                        giveOnRent.setArguments(bundle);

                        fragmentTransaction.add(R.id.menuFragmentContainerLayout, giveOnRent);
                        fragmentTransaction.commit();

                        setTitle("Rent Property");

                        if(propType.matches("Flat")){
                            setActionBarTitle("Rent/LL Flat");
                        } else if(propType.matches("House/Villa")){
                            setActionBarTitle("Rent/LL House Or Villa");
                        } else if(propType.matches("NA Plot")){
                            setActionBarTitle("Lease Plot");
                        } else if(propType.matches("Agricultural Land")){
                            setActionBarTitle("Lease Agricultural Land");
                        } else if(propType.matches("Office Space")){
                            setActionBarTitle("Lease Office Space");
                        } else if(propType.matches("Shop/Showroom")){
                            setActionBarTitle("Lease Shop Or Showroom");
                        } else if(propType.matches("Other Commercial")){
                            setActionBarTitle("Lease Other Commercial Property");
                        }

                    }


                }


            }  else if(menuName.matches("PROJECTS")){

                if(!type.matches("null")){

                    if(type.matches("REGISTER_PROJECT")){

                        if(propType != null) {

                            Bundle bundle = new Bundle();
                            bundle.putString("PROP_TYPE", propType);

                            FragmentManager fragmentManager = getSupportFragmentManager();
                            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                            RegisterProjects register = new RegisterProjects();

                            register.setArguments(bundle);

                            fragmentTransaction.add(R.id.menuFragmentContainerLayout, register);
                            fragmentTransaction.commit();

                            setTitle("Register Project");

                            if(propType.matches("Apartment")){
                                setActionBarTitle("Register Project (Apartment)");
                            } else if(propType.matches("House/Villa")){
                                setActionBarTitle("Register Project (House/Villa)");
                            } else if(propType.matches("NA Plot")){
                                setActionBarTitle("Register Project (Land)");
                            } else if(propType.matches("Commercial Office")){
                                setActionBarTitle("Register Project (Office)");
                            } else if(propType.matches("Commercial Complex")){
                                setActionBarTitle("Register Project (Commercial Complex)");
                            } else if(propType.matches("Other Commercial")){
                                setActionBarTitle("Register Project (Other Commercial)");
                            }

                        }

                        /*Bundle bundle = new Bundle();
                        bundle.putString("PROP_TYPE", propType);

                        FragmentManager fragmentManager = getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        //FragmentTakeOnRent rent = new FragmentTakeOnRent();

                        takeOnRent = new FragmentTakeOnRent();

                        takeOnRent.setArguments(bundle);

                        fragmentTransaction.add(R.id.menuFragmentContainerLayout, takeOnRent);
                        fragmentTransaction.commit();

                        setTitle("Rent Property");

                        if(propType.matches("Flat")){
                            setTitle("Rent/LL Flat");
                        } else if(propType.matches("House/Villa")){
                            setTitle("Rent/LL House Or Villa");
                        } else if(propType.matches("NA Plot")){
                            setTitle("Lease Plot");
                        } else if(propType.matches("Agricultural Land")){
                            setTitle("Lease Agricultural Land");
                        } else if(propType.matches("Office Space")){
                            setTitle("Lease Office Space");
                        } else if(propType.matches("Shop/Showroom")){
                            setTitle("Lease Shop Or Showroom");
                        } else if(propType.matches("Other Commercial")){
                            setTitle("Lease Other Commercial Property");
                        }*/

                    } else if(type.matches("BUY_PROJECT")){

                        Bundle bundle = new Bundle();
                        bundle.putString("PROP_TYPE", propType);

                        FragmentManager fragmentManager = getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        //FragmentTakeOnRent rent = new FragmentTakeOnRent();

                        buyProject = new FragmentBuyProject();

                        buyProject.setArguments(bundle);

                        fragmentTransaction.add(R.id.menuFragmentContainerLayout, buyProject);
                        fragmentTransaction.commit();

                        setTitle("Buy Project");

                        if(propType.matches("Apartment")){
                            setActionBarTitle("Buy Projects (Apartment)");
                        } else if(propType.matches("House/Villa")){
                            setActionBarTitle("Buy Projects (House/Villa)");
                        } else if(propType.matches("NA Plot")){
                            setActionBarTitle("Buy Projects (Land)");
                        } else if(propType.matches("Commercial Office")){
                            setActionBarTitle("Buy Projects (Office)");
                        } else if(propType.matches("Commercial Complex")){
                            setActionBarTitle("Buy Projects (Commercial Complex)");
                        } else if(propType.matches("Other Commercial")){
                            setActionBarTitle("Buy Projects (Other Commercial)");
                        }

                    }


                }

                /*FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                FragmentProject projects = new FragmentProject();
                fragmentTransaction.add(R.id.menuFragmentContainerLayout, projects);
                fragmentTransaction.commit();

                setTitle("Projects");*/

            }   else if(menuName.matches("ALL")){

                Intent intentS = new Intent(LoadMenuFragmentsActivity.this, TempProjectsActivity.class);
                intentS.putExtra("TITLE", "All Properties");
                startActivity(intent);

                setTitle("Projects");

            }



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


    @Override
    public void onBackPressed() {

        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.viewpager);

        if(buy != null) {

            boolean status = buy.onBackPressed();

            if (status) {
                super.onBackPressed();
            }

        }
        /* else {
            //Toast.makeText(this, "Buy fragment is null", Toast.LENGTH_SHORT).show();
            super.onBackPressed();
        }*/

        else if(takeOnRent != null){

            boolean status = takeOnRent.onBackPressed();

            if (status) {
                super.onBackPressed();
            }

        } else if(buyProject != null){

            boolean status = buyProject.onBackPressed();

            if (status) {
                super.onBackPressed();
            }

        } else {
            super.onBackPressed();
        }

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
        textviewTitle.setText(title);
        abar.setCustomView(viewActionBar, params);
        abar.setDisplayShowCustomEnabled(true);
        abar.setDisplayShowTitleEnabled(false);
        abar.setDisplayHomeAsUpEnabled(true);
        abar.setIcon(R.color.transparent);
        abar.setHomeButtonEnabled(true);

    }


    @Override
    public void onCCSelect(Pojo_CC selectedCC) {

        if(buy != null){
            buy.onCCSelect(selectedCC);
        } else if(takeOnRent != null){
            takeOnRent.onCCSelect(selectedCC);
        } else if(buyProject != null){
            buyProject.onCCSelect(selectedCC);
        }

    }
}




















