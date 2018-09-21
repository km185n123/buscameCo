package com.buscame.oncor.buscame.Historical.Activity;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.buscame.oncor.buscame.Historical.Activity.Object.PointsHistoricalAdapter;
import com.buscame.oncor.buscame.Historical.Model.Historic;
import com.buscame.oncor.buscame.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class HistoricalMaps extends FragmentActivity implements OnMapReadyCallback {

    private static GoogleMap mMap;
    private  RecyclerView pointsHistoricalRecyclerView;
    HashSet<Historic> points;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historical_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


         pointsHistoricalRecyclerView = findViewById(R.id.pointsHistoricalRecyclerView);
         points = (HashSet<Historic>) getIntent().getExtras().get("POINTS_HISTORICAL");


                loadPointsHistoricalRecyclerView(points);




    }

    private void loadPointsInMap(HashSet<Historic> points, GoogleMap mMap) {

        for (Historic point: points) {
            LatLng sydney = new LatLng(Double.parseDouble( point.getLatitude() ),Double.parseDouble( point.getLongitude() ) );


            HistoricalMaps.mMap.addMarker(new MarkerOptions()
                    .position(sydney)
                    //.title( point.getAddress().isEmpty()?point.getAddress():"" ));
                    .title( point.getAddress() ));
        }
    }

    public static GoogleMap getmMap() {
        return mMap;
    }



    public static void UpdatePintInMap(Double lat, Double lon,GoogleMap mMap){
        LatLng sydney = new LatLng(lat, lon);

        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
         LatLngBounds AUSTRALIA = new LatLngBounds(sydney,sydney);

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(AUSTRALIA.getCenter(), 15));
    }

    private void loadPointsHistoricalRecyclerView(HashSet<Historic> points) {

        List listPoints = new ArrayList(points);




        Collections.sort(listPoints,getCompByHour());

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        pointsHistoricalRecyclerView.setLayoutManager(layoutManager);
        pointsHistoricalRecyclerView.setHasFixedSize(true);
        PointsHistoricalAdapter historicAdapter = new PointsHistoricalAdapter(this, listPoints);

        pointsHistoricalRecyclerView.setAdapter(historicAdapter);




    }

    public static Comparator<Historic> getCompByHour()
    {
        Comparator comp = new Comparator<Historic>(){
            @Override
            public int compare(Historic s1, Historic s2)
            {
                return s2.getStringCreateHour().compareTo(s1.getStringCreateHour());
            }
        };
        return comp;
    }
    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //Disable Map Toolbar:
        mMap.getUiSettings().setMapToolbarEnabled(false);

        loadPointsInMap(this.points,mMap);

        // Add a marker in Sydney and move the camera
       this.UpdatePintInMap(-34.0,151.0,mMap);
    }
}


