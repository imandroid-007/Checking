package com.example.realestate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.developer.kalert.KAlertDialog;
import com.example.realestate.OTPReceiver.AppSignatureHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

import es.dmoral.toasty.Toasty;

public class SignUpActivity extends AppCompatActivity {

    private TextView login;
    private Button signUpBtn;

    private static String URL_LOGIN = "http://192.168.1.109/real_estate/registration_master_store.php";

    private EditText usernameEdtx, emailEdtx, mobileNoEdtx, passwordEdtx;
    private ProgressBar progressBar;

    SharedPreferences pref;
    SharedPreferences.Editor editor;

    private RadioGroup radioGrpPost;
    private String postStr = "Customer";
    private TextView noteTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        pref = PreferenceManager.getDefaultSharedPreferences(SignUpActivity.this);

        //login = (TextView) findViewById(R.id.singnIn);
        signUpBtn = (Button) findViewById(R.id.btnSignUp);
        progressBar = (ProgressBar) findViewById(R.id.loading_id);
        
        usernameEdtx = (EditText) findViewById(R.id.full_name_edt); 
        emailEdtx = (EditText) findViewById(R.id.email_edt); 
        mobileNoEdtx = (EditText) findViewById(R.id.mobile_edt); 
        passwordEdtx = (EditText) findViewById(R.id.password_edt);

        radioGrpPost = (RadioGroup) findViewById(R.id.postRdGrp);
        noteTv = (TextView) findViewById(R.id.noteTv);

        /*login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });*/

        radioGrpPost.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){

                    case R.id.custRdBtn:
                        //  reraApprovedAgentChkbx.setVisibility(View.GONE);
                        postStr = "Customer";
                        noteTv.setText("You can buy, rent or invest in projects by registering as a customer.");
                        break;

                    case R.id.sellerRdBtn:
                        //   reraApprovedAgentChkbx.setVisibility(View.VISIBLE);
                        postStr = "Seller";
                        noteTv.setText("You can buy, rent, invest or sell your property as well by registering as a seller.");
                        break;

                    case R.id.agentRdBtn:
                        //  reraApprovedAgentChkbx.setVisibility(View.GONE);
                        postStr = "Agent";
                        noteTv.setText("You can buy, rent, invest or sell your property as well by registering as a agent.");
                        break;
                }
            }
        });

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(SignUpActivity.this, OTP_Activity.class));

                String name = usernameEdtx.getText().toString();
                String email = emailEdtx.getText().toString();
                String mobileNo = mobileNoEdtx.getText().toString();
                String password = passwordEdtx.getText().toString();

                if(postStr == null){
                    Toasty.error(SignUpActivity.this, "Please Selece Registration Post", Toasty.LENGTH_LONG).show();
                } else if(name.matches("")){
                    usernameEdtx.setError("Please Enter Username!");
                } else if(email.matches("")){
                    emailEdtx.setError("Enter Email");
                } else if(mobileNo.matches("")){
                    mobileNoEdtx.setError("Enter Mobile Number");
                } else if(mobileNo.length() != 10){
                    mobileNoEdtx.setError("Mobile Number must be 10 digit");
                } else if(password.matches("")){
                    passwordEdtx.setError("Enter Password");
                } else {
                    doSignUp(name, email, mobileNo, password, postStr);
                }
            }
        });

        AppSignatureHelper appSignatureHelper = new AppSignatureHelper(SignUpActivity.this);

        appSignatureHelper.getAppSignatures();


    }



    private void doSignUp(String username, String email, String mobile, String password, String post){

        progressBar.setVisibility(View.VISIBLE);
        signUpBtn.setEnabled(false);
        signUpBtn.setAlpha(0.5f);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.d("EXCEPTION", "Response : " + " " + response);
                        //Toast.makeText(SignUpActivity.this, "Response : " + " " + response, Toast.LENGTH_LONG).show();

                        try{
                            JSONArray ja = new JSONArray(response);
                            JSONObject jo = null;

                            String custId = null;
                            String otp = null;
                            String message = null;

                            /*JSONObject jsonObject = new JSONObject(response);
                            custId = jsonObject.getString("Message");*/

                            for(int i = 0; i<ja.length(); i++){
                                jo = ja.getJSONObject(i);

                                message = jo.getString("Message");

                                if(message.equals("1")) {
                                    custId = jo.getString("id");
                                    otp = jo.getString("otp");
                                }

                            }

                            //Toast.makeText(SignUpActivity.this, "Message : " + " " + custId, Toast.LENGTH_SHORT).show();

                            if(message.equals("1")){

                                if(custId != null){
                                    Intent intent = new Intent(SignUpActivity.this, OTP_Activity.class);
                                    intent.putExtra("ID", custId);
                                    intent.putExtra("MOBILE", mobile);
                                    intent.putExtra("OTP", otp);
                                    startActivity(intent);
                                }

                            } else if(message.equals("")){

                            } else {

                                new KAlertDialog(SignUpActivity.this, KAlertDialog.ERROR_TYPE)
                                        .setTitleText("Error!")
                                        .setContentText("There was an unexpected error, Please try again!")
                                        .setConfirmClickListener(new KAlertDialog.KAlertClickListener() {
                                            @Override
                                            public void onClick(KAlertDialog kAlertDialog) {
                                                kAlertDialog.dismiss();
                                            }
                                        })
                                        .confirmButtonColor(R.drawable.kalert_button_background)
                                        .show();
                            }

                            progressBar.setVisibility(View.GONE);
                            signUpBtn.setEnabled(true);
                            signUpBtn.setAlpha(1);

                        } catch (JSONException e){
                            e.printStackTrace();

                            //Toast.makeText(SignUpActivity.this, "JSON Exception : " + " " + e.toString(), Toast.LENGTH_SHORT).show();
                            Log.d("EXCEPTION", "JSON Exception : " + " " + e.toString());

                            new KAlertDialog(SignUpActivity.this, KAlertDialog.ERROR_TYPE)
                                    .setTitleText("Error!")
                                    .setContentText("Ops, Something went wrong!")
                                    .setConfirmClickListener(new KAlertDialog.KAlertClickListener() {
                                        @Override
                                        public void onClick(KAlertDialog kAlertDialog) {
                                            kAlertDialog.dismiss();
                                        }
                                    })
                                    .confirmButtonColor(R.drawable.kalert_button_background)
                                    .show();

                            progressBar.setVisibility(View.GONE);
                            signUpBtn.setEnabled(true);
                            signUpBtn.setAlpha(1);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        //Toast.makeText(SignUpActivity.this, "Volley Error : " + " " + error.toString(), Toast.LENGTH_SHORT).show();
                        Log.d("EXCEPTION", "Volley Error : " + " " + error.toString());

                        new KAlertDialog(SignUpActivity.this, KAlertDialog.ERROR_TYPE)
                                .setTitleText("Error!")
                                .setContentText("Can't communicate with server, Please try again.")
                                .setConfirmClickListener(new KAlertDialog.KAlertClickListener() {
                                    @Override
                                    public void onClick(KAlertDialog kAlertDialog) {
                                        kAlertDialog.dismiss();
                                    }
                                })
                                .confirmButtonColor(R.drawable.kalert_button_background)
                                .show();

                        progressBar.setVisibility(View.GONE);
                        signUpBtn.setEnabled(true);
                        signUpBtn.setAlpha(1);
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                params.put("user_name",username);
                params.put("email",email);
                params.put("mobile_no",mobile);
                params.put("password",password);
                params.put("post",post);

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(SignUpActivity.this);
        requestQueue.add(stringRequest);
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

























