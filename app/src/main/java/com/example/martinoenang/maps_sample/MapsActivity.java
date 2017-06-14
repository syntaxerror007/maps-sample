package com.example.martinoenang.maps_sample;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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

        // Add a marker in Sydney and move the camera
        List<LatLng> latLngList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            LatLng sydney = new LatLng(-34.33333+(i/10.0), 151.33333+(i/10.0));
            latLngList.add(sydney);

            MarkerOptions markerOptions = new MarkerOptions().
                    position(sydney).
                    title(""+i).
                    icon(BitmapDescriptorFactory.fromResource(android.R.drawable.ic_menu_camera));
            mMap.addMarker(markerOptions);
        }
        mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
            @Override
            public View getInfoWindow(Marker marker) {
                TextView textView = new TextView(MapsActivity.this);
                textView.setBackgroundColor(ContextCompat.getColor(MapsActivity.this, R.color.white));
                textView.setText(marker.getTitle());
                return textView;
            }

            @Override
            public View getInfoContents(Marker marker) {
                return null;
            }
        });
        mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(-34.33333, 151.33333)));
    }
}
