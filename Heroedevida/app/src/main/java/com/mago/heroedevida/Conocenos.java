package com.mago.heroedevida;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Conocenos extends FragmentActivity implements OnMapReadyCallback {
    GoogleMap map;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_conocenos);
        /*SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);*/
        SupportMapFragment smap =(SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map);
        map=smap.getMap();

        map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        map.setMyLocationEnabled(true);

        map.addMarker(new MarkerOptions().position(new LatLng(19.2988711, -99.211733)).title("Centro Nacional de Transplantes"));
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(19.2988711, -99.211733), 15));
    }

    @Override
    public void onMapReady(GoogleMap map) {
        // Add a marker in Sydney, Australia, and move the camera.
        LatLng sydney = new LatLng(19.2988711,-99.211733);
        map.addMarker(new MarkerOptions().position(sydney).title("Centro Nacional de Transplantes"));
        map.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}