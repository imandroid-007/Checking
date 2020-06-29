package com.example.realestate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.developer.kalert.KAlertDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Guest_login extends AppCompatActivity {

    private static String URL_LOGIN = "http://192.168.1.109/real_estate/guest_login.php";

    private Button loginBtn, signUpBtn;
    private ProgressBar progressBar;
    private EditText mobileno, passwordEdtx;
    SharedPreferences pref;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_login);

        loginBtn = (Button) findViewById(R.id.guest_loginBtn);
        signUpBtn = (Button) findViewById(R.id.guest_signUpBtn);
        progressBar = (ProgressBar) findViewById(R.id.guest_loginProgress);

        mobileno = (EditText) findViewById(R.id.guest_mobile_edt);

        pref = PreferenceManager.getDefaultSharedPreferences(Guest_login.this);



        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(LoginActivity.this, MainActivity.class));

                String mobile_no = mobileno.getText().toString();
              //  String password = passwordEdtx.getText().toString();

                //startActivity(new Intent(LoginActivity.this, MainActivity.class));

                if (mobile_no.matches("")) {
                    mobileno.setError("Enter Mobile Number");
                } else {
                    doLogin(mobile_no);
                }

            }
        });
    }


    private void doLogin(String mobileno) {

        progressBar.setVisibility(View.VISIBLE);
        loginBtn.setEnabled(false);
        loginBtn.setAlpha(0.5f);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.d("EXCEPTION", "Response : " + " " + response);
                        //Toast.makeText(LoginActivity.this, "Response : " + " " + response, Toast.LENGTH_LONG).show();

                        try {
                            JSONArray ja = new JSONArray(response);
                            JSONObject jo = null;

                            String custId = null;
                            String otp = null;
                            String message = null;

                            /*JSONObject jsonObject = new JSONObject(response);
                            custId = jsonObject.getString("Message");*/

                            for (int i = 0; i < ja.length(); i++) {
                                jo = ja.getJSONObject(i);

                                message = jo.getString("Message");

                                if(message.equals("1")) {
                                    custId = jo.getString("id");
                                    otp = jo.getString("otp");
                                }


                            }

                            //Toast.makeText(LoginActivity.this, "Message : " + " " + custId, Toast.LENGTH_SHORT).show();

                            if(message.equals("1")){

                                if(custId != null){
                                    Intent intent = new Intent(Guest_login.this, OTP_Activity.class);
                                    intent.putExtra("ID", custId);
                                    intent.putExtra("MOBILE", mobileno);
                                    intent.putExtra("OTP", otp);
                                    startActivity(intent);
                                }

                            } else if(message.equals("")){

                            } else {
                                new KAlertDialog(Guest_login.this, KAlertDialog.ERROR_TYPE)
                                        .setTitleText("Error!")
                                        .setContentText("Some unexpected error occurred, Please try again later or contact service provider!")
                                        .setConfirmClickListener(new KAlertDialog.KAlertClickListener() {
                                            @Override
                                            public void onClick(KAlertDialog kAlertDialog) {
                                                kAlertDialog.dismiss();
                                            }
                                        })
                                        .confirmButtonColor(R.drawable.kalert_button_background)
                                        .show();
                            }

                            progressBar.setVisibility(View.INVISIBLE);
                            loginBtn.setEnabled(true);
                            loginBtn.setAlpha(1);

                        } catch (JSONException e) {
                            e.printStackTrace();

                            //Toast.makeText(LoginActivity.this, "JSON Exception : " + " " + e.toString(), Toast.LENGTH_SHORT).show();
                            Log.d("EXCEPTION", "JSON Exception : " + " " + e.toString());

                            new KAlertDialog(Guest_login.this, KAlertDialog.ERROR_TYPE)
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

                            progressBar.setVisibility(View.INVISIBLE);
                            loginBtn.setEnabled(true);
                            loginBtn.setAlpha(1);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        //Toast.makeText(LoginActivity.this, "Volley Error : " + " " + error.toString(), Toast.LENGTH_SHORT).show();
                        Log.d("EXCEPTION", "Volley Error : " + " " + error.toString());

                        new KAlertDialog(Guest_login.this, KAlertDialog.ERROR_TYPE)
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

                        progressBar.setVisibility(View.INVISIBLE);
                        loginBtn.setEnabled(true);
                        loginBtn.setAlpha(1);
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                params.put("user_mobile_no", mobileno);
                //params.put("user_pass", password);

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(Guest_login.this);
        requestQueue.add(stringRequest);
    }

}
