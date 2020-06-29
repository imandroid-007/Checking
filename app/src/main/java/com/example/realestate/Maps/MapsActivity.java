package com.example.realestate.Maps;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.Image;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.developer.kalert.KAlertDialog;
import com.example.realestate.R;
import com.github.florent37.shapeofview.shapes.BubbleView;
import com.github.florent37.shapeofview.shapes.RoundRectView;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import es.dmoral.toasty.Toasty;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, com.google.android.gms.location.LocationListener {

    private GoogleMap mMap;

    private EditText searchLocationEdtx;
    private ImageView findMyLocationImg;

    List<String> localityAddress = new ArrayList<String>();

    MarkerOptions options = new MarkerOptions();
    boolean addOnlyoneMarker = false;

    Double lLatitude, lLongitude;

    String addressSearch = null;

    String googleAddress = null;

    LocationManager locationManager;

    boolean firstTimeZoom = false;

    //double longtitude, latitude;

    private static final int MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION = 1;

    boolean statusOfGPS;

    public static final int LOCATION_REQUEST = 500;

    double latitude, longitude;

    LocationListener myLocationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            longitude = location.getLongitude();
            latitude = location.getLatitude();

            /**
             *  25-May-2019
             *
             *  Disbled to stop getting location updates.
             */

            //findOnMap();
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String s) {

        }

        @Override
        public void onProviderDisabled(String s) {

        }
    };

    boolean isSatteliteViewEnabled = false;

    private ImageView mapViewTypeImg;

    private RoundRectView mapViewRelativeButton;

    Typeface typeface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_map_activity);

        Intent intent = getIntent();
        addressSearch = intent.getStringExtra("ADDRESS");

        searchLocationEdtx = (EditText) findViewById(R.id.searchLocationEdtx);
        findMyLocationImg = (ImageView) findViewById(R.id.findMyLocation);

        mapViewTypeImg = (ImageView) findViewById(R.id.googleMapViewTypeImg);
        mapViewRelativeButton = (RoundRectView) findViewById(R.id.mavViewRelativeButton);

        typeface = Typeface.createFromAsset(getAssets(), "fonts/SourceSansPro-SemiBold.ttf");

        searchLocationEdtx.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if (actionId == EditorInfo.IME_ACTION_DONE) {

                    String locality = searchLocationEdtx.getText().toString();

                    if (locality.matches("")) {
                        searchLocationEdtx.setError("Enter Location");
                    } else {
                        getLocationFromAddress(MapsActivity.this, locality, 15);
                        Toast.makeText(MapsActivity.this, "Searching", Toast.LENGTH_SHORT).show();
                    }

                    return true;
                }

                return false;
            }
        });


        findMyLocationImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                statusOfGPS = manager.isProviderEnabled(LocationManager.GPS_PROVIDER);

                if (!statusOfGPS) {
                    try {
                        new AlertDialog.Builder(MapsActivity.this)
                                .setTitle("Required Location Permission")
                                .setMessage("You have to give this permission to acess this feature")
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                        ActivityCompat.requestPermissions(MapsActivity.this,
                                                new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                                                MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION);

                                        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                                        startActivity(intent);
                                        statusOfGPS = true;
                                    }
                                })
                                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.dismiss();
                                    }
                                })
                                .create()
                                .show();
                        //openDialog();

                    } catch (Exception e) {
                        ///Toast.makeText(activity, "" + e, Toast.LENGTH_LONG).show();
                    }

                } else {

                    if (ActivityCompat.checkSelfPermission(MapsActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MapsActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(MapsActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_REQUEST);

                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.

                        return;
                    }
                    Location location = locationManager.getLastKnownLocation(locationManager.NETWORK_PROVIDER);
                    //onLocationChanged(location);

                    if (location != null) {
                        // always need to get longitude and latitude from location before calling findOnMap();
                        longitude = location.getLongitude();
                        latitude = location.getLatitude();
                        findOnMap();
                        onLocationChanged(location);
                    } else {

                        if (ActivityCompat.checkSelfPermission(MapsActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MapsActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions(MapsActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_REQUEST);
                            // TODO: Consider calling
                            //    ActivityCompat#requestPermissions
                            // here to request the missing permissions, and then overriding
                            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                            //                                          int[] grantResults)
                            // to handle the case where the user grants the permission. See the documentation
                            // for ActivityCompat#requestPermissions for more details.
                            return;
                        }
                        try {
                            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, myLocationListener);
                            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 0, myLocationListener);
                            locationManager.requestLocationUpdates(LocationManager.PASSIVE_PROVIDER, 1000, 0, myLocationListener);
                            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, myLocationListener);
                            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 0, myLocationListener);
                            locationManager.requestLocationUpdates(LocationManager.PASSIVE_PROVIDER, 1000, 0, myLocationListener);

                            //locationManager.requestLocationUpdates(bestProvider, 1000, 0, this);
                            //locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,1000,0,this);
                            //locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,1000,0, (LocationListener) contexct);
                        } catch (Exception e) {
                            ///Toast.makeText(activity, "" + e, Toast.LENGTH_LONG).show();
                            e.printStackTrace();
                        }
                    }

                }

            }
        });

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        mapViewRelativeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mMap != null) {

                    if (isSatteliteViewEnabled) {
                        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                        isSatteliteViewEnabled = false;
                        mapViewTypeImg.setImageResource(R.drawable.ic_sattelite_view_icon);
                    } else {
                        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                        isSatteliteViewEnabled = true;
                        mapViewTypeImg.setImageResource(R.drawable.ic_map_view_icon);
                    }

                }

            }
        });

    }


    public void findOnMap() {

        Geocoder geocoder = new Geocoder(MapsActivity.this);

        try {

            //List<Address> myList1 = geocoder.getFromLocation(17.694794, 74.015452, 1);
            List<Address> myList1 = geocoder.getFromLocation(latitude, longitude, 1);
            //Log.d("address", ""+myList.get(0));
            Address address = myList1.get(0);
            String locality = address.getLocality();
            /// Toast.makeText(activity, "Locality: " + locality, Toast.LENGTH_LONG).show();

            /*making global variables
            double lat = address.getLatitude();
            double lon = address.getLongitude();*/

            double lat, lon;

            lat = address.getLatitude();
            lon = address.getLongitude();

            if (!firstTimeZoom) {

                float minZoomLevel = mMap.getMaxZoomLevel();
                float maxZoomLevel = mMap.getMaxZoomLevel();

                Log.d("ZOOMLEVELLOG", "MinZoomLevel =" + " " + minZoomLevel + "\nMaxZoomLevel =" + " " + maxZoomLevel);

                goTOLocation(lat, lon, 15);
                firstTimeZoom = true;
            } else {
                goTOLocation(lat, lon, 15);

            }

            //making global
            //MarkerOptions options = new MarkerOptions();

            /**
             * Custom snippets
             */
            //options.title("I am here !");

            // following method adds the color to the marker, even if you dont call following method the default
            // marker color will be RED.

            //options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
            //options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));
            //options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));

            //To add your image instead of marker use following method.
            //options.icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher));

            //options.draggable(true);
            //options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));

            options.icon(BitmapDescriptorFactory.defaultMarker());

            //options.snippet(locality);
            //options.snippet("at "+locality);

            //options.position(new LatLng(lat,lon));  //no need to call this here, we have passed lat longs to options in goToLocation().

            //mMap.addMarker(options); // marker added in handler which is under goToLocation().

            //geofenceMarker = mMap.addMarker(options); // marker added in handler which is under goToLocation().

            //mMap.addMarker(options).showInfoWindow();

        } catch (Exception e) {
            //Toast.makeText(getApplicationContext(), "findOnMap() error" + e, Toast.LENGTH_LONG).show();
            /// Toast.makeText(activity, "Internet conenection Error", Toast.LENGTH_LONG).show();

            AlertDialog.Builder builder = new AlertDialog.Builder(MapsActivity.this);
            builder.setMessage("Something is wrong with connection, Please try after some time.")
                    .setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            //do things
                            dialog.dismiss();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
        }

    }


    public LatLng getLocationFromAddress(Context context, String strAddress, int zoom) {

        Geocoder coder = new Geocoder(MapsActivity.this);
        List<Address> address;
        LatLng p1 = null;

        try {
            address = coder.getFromLocationName(strAddress, 5);
            if (address == null) {
                return null;
            }
            Address location = address.get(0);
            location.getLatitude();
            location.getLongitude();

            p1 = new LatLng(location.getLatitude(), location.getLongitude());

            double lat = location.getLatitude();
            double lon = location.getLongitude();

            getAddressFromLocation(lat, lon, zoom);

        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(), "Caut an Exception" + ex, Toast.LENGTH_LONG).show();
            Log.d("caut", "Exception = " + " " + ex);
            ex.printStackTrace();
        }

        return p1;
    }

    @Override
    public void onBackPressed() {
        /*AlertDialog.Builder builder = new AlertDialog.Builder(MapsActivity.this);
        builder.setMessage("Please select location, add marker and click on top of the marker to make selection.")
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();*/
        super.onBackPressed();
    }


    public void getAddressFromLocation(final double latitude, final double longitude, final int zoom) {

      /*  runOnUiThread(new Runnable() {
            @Override
            public void run() {

                Geocoder geocoder = new Geocoder(MapsActivity.this, Locale.getDefault());
                try {
                    List<Address> addressList = geocoder.getFromLocation(
                            latitude, longitude, 1);
                    if (addressList != null && addressList.size() > 0) {
                        Address address = addressList.get(0);
                        StringBuilder sb = new StringBuilder();
                        for (int i = 0; i < address.getMaxAddressLineIndex(); i++) {
                            sb.append(address.getAddressLine(i)).append("\n");
                        }

                        String featureName = address.getFeatureName();
                        if (featureName != null) {
                            if (!featureName.equals("null") || !featureName.matches("")) {
                                //Log.d("GeoLocAddr", " feature Name = " + " " + featureName);  //returns Ex : bandhkam bhawan
                                sb.append(address.getFeatureName()).append(",");
                            }
                        }

                        String locality = address.getLocality();
                        //Log.d("GeoLocAddr", " Locality = " + " " + locality);
                        String adminArea = address.getAdminArea();
                        //Log.d("GeoLocAddr", " Admin Area = " + " " + adminArea);
                        //String subAdminArea = address.getSubAdminArea();
                        //Log.d("GeoLocAddr", " SubAdmin Area = " + " " + subAdminArea);
                        String countryName = address.getCountryName();
                        //Log.d("GeoLocAddr", " Country Name = " + " " + countryName);
                        String postalCode = address.getPostalCode();
                        //Log.d("GeoLocAddr", " Postal Code = " + " " + postalCode);

                        sb.append(" " + locality).append(",");
                        sb.append(" " + adminArea).append(",");
                        sb.append(" " + countryName).append("");
                        sb.append(" " + postalCode).append(".");


                        //sb.append(" " + subAdminArea).append("");

                        */
      /*sb.append(" " + address.getLocality()).append(" ");
                        sb.append(" " + address.getAdminArea()).append(" ");
                        sb.append(" " + address.getSubAdminArea()).append(" ");
                        sb.append(address.getCountryName()).append(" ");
                        sb.append(address.getPostalCode()).append(" ");*/
      /*

                        googleAddress = sb.toString();
                        goTOLocation(latitude, longitude, 15);

                        //Toast.makeText(getApplicationContext(), "address from lat long = " + " " + googleAddress, Toast.LENGTH_LONG).show();

                    }
                } catch (IOException e) {
                    //Log.e("TEST", "Unable connect to Geocoder", e);
                    //Toast.makeText(getApplicationContext(), "Unable connect to Geocoder", Toast.LENGTH_LONG).show();

                    AlertDialog.Builder builder = new AlertDialog.Builder(MapsActivity.this);
                    builder.setMessage("Unable to get Address! please try again.")
                            .setCancelable(false)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.dismiss();
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();
                }
            }
        });*/

        Thread thread = new Thread() {
            @Override
            public void run() {

                Geocoder geocoder = new Geocoder(MapsActivity.this, Locale.getDefault());
                try {
                    List<Address> addressList = geocoder.getFromLocation(
                            latitude, longitude, 1);
                    if (addressList != null && addressList.size() > 0) {
                        Address address = addressList.get(0);
                        StringBuilder sb = new StringBuilder();
                        for (int i = 0; i < address.getMaxAddressLineIndex(); i++) {
                            sb.append(address.getAddressLine(i)).append("\n");
                        }

                        String featureName = address.getFeatureName();
                        if (featureName != null) {
                            if (!featureName.equals("null") || !featureName.matches("")) {
                                //Log.d("GeoLocAddr", " feature Name = " + " " + featureName);  //returns Ex : bandhkam bhawan
                                sb.append(address.getFeatureName()).append(",");
                            }
                        }

                        String locality = address.getLocality();
                        //Log.d("GeoLocAddr", " Locality = " + " " + locality);
                        String adminArea = address.getAdminArea();
                        //Log.d("GeoLocAddr", " Admin Area = " + " " + adminArea);
                        //String subAdminArea = address.getSubAdminArea();
                        //Log.d("GeoLocAddr", " SubAdmin Area = " + " " + subAdminArea);
                        String countryName = address.getCountryName();
                        //Log.d("GeoLocAddr", " Country Name = " + " " + countryName);
                        String postalCode = address.getPostalCode();
                        //Log.d("GeoLocAddr", " Postal Code = " + " " + postalCode);

                        sb.append(" " + locality).append(",");
                        sb.append(" " + adminArea).append(",");
                        sb.append(" " + countryName).append("");
                        sb.append(" " + postalCode).append(".");


                        //sb.append(" " + subAdminArea).append("");

                        /*sb.append(" " + address.getLocality()).append(" ");
                        sb.append(" " + address.getAdminArea()).append(" ");
                        sb.append(" " + address.getSubAdminArea()).append(" ");
                        sb.append(address.getCountryName()).append(" ");
                        sb.append(address.getPostalCode()).append(" ");*/

                        googleAddress = sb.toString();

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                goTOLocation(latitude, longitude, zoom);
                            }
                        });
                        //Toast.makeText(getApplicationContext(), "address from lat long = " + " " + googleAddress, Toast.LENGTH_LONG).show();
                    }
                } catch (IOException e) {
                    //Log.e("TEST", "Unable connect to Geocoder", e);
                    //Toast.makeText(getApplicationContext(), "Unable connect to Geocoder", Toast.LENGTH_LONG).show();

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            Toasty.error(MapsActivity.this, "Unable To get address, You can still proceed further", Toasty.LENGTH_LONG).show();

                            /*AlertDialog.Builder builder = new AlertDialog.Builder(MapsActivity.this);
                            builder.setMessage("Unable to get Address! please try again.")
                                    .setCancelable(false)
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            dialog.dismiss();
                                        }
                                    });
                            AlertDialog alert = builder.create();
                            alert.show();*/

                        }
                    });
                }

            }
        };

        thread.start();

        /*Thread thread = new Thread() {
            @Override
            public void run() {
                Geocoder geocoder = new Geocoder(MapsActivity.this, Locale.getDefault());
                try {
                    List<Address> addressList = geocoder.getFromLocation(
                            latitude, longitude, 1);
                    if (addressList != null && addressList.size() > 0) {
                        Address address = addressList.get(0);
                        StringBuilder sb = new StringBuilder();
                        for (int i = 0; i < address.getMaxAddressLineIndex(); i++) {
                            sb.append(address.getAddressLine(i)).append("\n");
                        }
                        sb.append(address.getLocality()).append("\n");
                        sb.append(address.getPostalCode()).append("\n");
                        sb.append(address.getCountryName());
                        googleAddress = sb.toString();
                        goTOLocation(latitude, longitude, 15);

                        Toast.makeText(getApplicationContext(), "address from lat long = " + " " + googleAddress, Toast.LENGTH_LONG).show();

                    }
                } catch (IOException e) {
                    Log.e("TEST", "Unable connect to Geocoder", e);
                    Toast.makeText(getApplicationContext(), "Unable connect to Geocoder", Toast.LENGTH_LONG).show();
                }
            }
        };
        thread.start();*/
    }

    public void goTOLocation(double latitude, double longitude, int zoom) {

        LatLng latLng = new LatLng(latitude, longitude);

        //CameraUpdate update = CameraUpdateFactory.newLatLngZoom(latLng, 15);

        float zm = mMap.getCameraPosition().zoom;

        //Toast.makeText(this, "Current Zoom =" + " " + zm, Toast.LENGTH_SHORT).show();

        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(latLng, zoom);
        mMap.moveCamera(update);

        mMap.clear();
        options.position(new LatLng(latLng.latitude, latLng.longitude)).title("OK");
        mMap.addMarker(options);
        addOnlyoneMarker = true;
        lLatitude = latLng.latitude;
        lLongitude = latLng.longitude;

    }


    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;

        //mMap.setOnMarkerClickListener(this);

        if (addressSearch != null) {
            searchLocationEdtx.setText(addressSearch);
            //search.callOnClick();
            getLocationFromAddress(getApplicationContext(), addressSearch, 15);
        }

        mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
            @Override
            public View getInfoWindow(Marker marker) {
                return null;
            }

            @Override
            public View getInfoContents(Marker marker) {

                /**
                 *   This section contains snippet layout code so Track button code can be placed here.
                 */

                View row = getLayoutInflater().inflate(R.layout.custome_address, null);
                TextView t1_locality = (TextView) row.findViewById(R.id.locality);
                TextView t2_latitude = (TextView) row.findViewById(R.id.latTxt);
                TextView t3_longitude = (TextView) row.findViewById(R.id.lngTxt);
                TextView t4_snippet = (TextView) row.findViewById(R.id.snippet);

                //ImageView img = (ImageView) row.findViewById(R.id.imageicon_id);

                //img.setOnClickListener(new View.OnClickListener() {
                //@Override
                //public void onClick(View view) {
                        /*Intent intent = new Intent(getApplicationContext(), TestActivity.class);
                        String slat = String.valueOf(lat);
                        String slong = String.valueOf(lon);
                        intent.putExtra("latitude", slat);
                        intent.putExtra("longitude", slong);
                        startActivity(intent);*/

                        /*if(lLongitude != null){
                            Intent intent = new Intent(MapsActivity.this, GetLatLongActivity.class);
                            String l = Double.toString(lLatitude);
                            String lo = Double.toString(lLongitude);
                            Toast.makeText(getApplicationContext(), "Lat:"+l+" lon: "+lo, Toast.LENGTH_SHORT).show();
                            intent.putExtra("LATITUDE", l);
                            intent.putExtra("LONGITUDE", lo);
                            startActivity(intent);
                        }
                    }
                });*/

                /*LatLng ll = marker.getPosition();
                t1_locality.setText(marker.getTitle());
                t2_latitude.setText(String.valueOf(ll.latitude));
                t3_longitude.setText(String.valueOf(ll.longitude));
                t4_snippet.setText(marker.getSnippet());*/

                LatLng ll = marker.getPosition();
                t1_locality.setText(marker.getTitle());
                t1_locality.setText("Click Here To\nSelect This Location");
                t2_latitude.setText(String.format("Latitude : %.6f", lLatitude));
                t3_longitude.setText(String.format("Longitude : %.6f", lLongitude));
                t4_snippet.setText(marker.getSnippet());

                Log.d("MARKERSINFOLOG", "Title =" + " " + marker.getTitle() + "\nSnippet =" + " " + marker.getSnippet());

                t1_locality.setTypeface(typeface);
                t2_latitude.setTypeface(typeface);
                t3_longitude.setTypeface(typeface);
                t4_snippet.setTypeface(typeface);

                return row;
            }
        });

        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {

                if (lLongitude != null) {

                    Intent intent = new Intent();

                    int zm = (int) mMap.getCameraPosition().zoom;

                    getAddressFromLocation(lLatitude, lLongitude, zm);

                    //Toast.makeText(MapsActivity.this, "Latitude =" + " " + lLatitude + "\nLongitude =" + lLongitude, Toast.LENGTH_LONG).show();

                    String l = Double.toString(lLatitude);
                    String lo = Double.toString(lLongitude);

                    intent.putExtra("LATITUDE", l);
                    intent.putExtra("LONGITUDE", lo);

                    if (googleAddress != null) {
                        if (!googleAddress.matches("null") || !googleAddress.matches("")) {
                            intent.putExtra("GEO_ADDRESS", googleAddress);
                            //intent.putExtra("LATITUDE", l);
                            //intent.putExtra("LONGITUDE", lo);
                        } else {
                            intent.putExtra("GEO_ADDRESS", "");
                        }
                    } else {
                        intent.putExtra("GEO_ADDRESS", "");
                    }
                    //startActivity(intent);
                    setResult(Activity.RESULT_OK, intent);
                    finish();

                }
            }
        });


        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {

                try {

                    if (!addOnlyoneMarker) {
                        options.position(new LatLng(latLng.latitude, latLng.longitude)).title("OK");
                        mMap.addMarker(options);
                        addOnlyoneMarker = true;
                        lLatitude = latLng.latitude;
                        lLongitude = latLng.longitude;

                        int zm = (int) mMap.getCameraPosition().zoom;

                        getAddressFromLocation(lLatitude, lLongitude, zm);

                    } else {
                        mMap.clear();
                        options.position(new LatLng(latLng.latitude, latLng.longitude)).title("OK");
                        mMap.addMarker(options);
                        addOnlyoneMarker = true;
                        lLatitude = latLng.latitude;
                        lLongitude = latLng.longitude;

                        int zm = (int) mMap.getCameraPosition().zoom;

                        getAddressFromLocation(lLatitude, lLongitude, zm);
                    }

                    /*mMap.clear();
                    options.position(new LatLng(latLng.latitude, latLng.longitude)).title("OK");
                    mMap.addMarker(options);
                    addOnlyoneMarker = true;
                    lLatitude = latLng.latitude;
                    lLongitude = latLng.longitude;*/
                    //Toast.makeText(getApplicationContext(), "Hello from onMapClick()", Toast.LENGTH_LONG).show();

                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(MapsActivity.this, "Marker Exception =" + " " + e, Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Add a marker in Sydney and move the camera

        /*LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));*/

        // End of onMapReady();

    }


}



















