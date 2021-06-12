package com.inhatc.secret;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class MapFragment extends Fragment implements OnMapReadyCallback
{
    private MapView mapView = null;

    public MapFragment()
    {
        // required
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_map, container, false);

        mapView = (MapView)layout.findViewById(R.id.map);
        mapView.getMapAsync(this);
        return layout;
    }

    @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onLowMemory();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //액티비티가 처음 생성될 때 실행되는 함수

        if(mapView != null)
        {
            mapView.onCreate(savedInstanceState);
        }
    }

    public void onMapReady(GoogleMap googleMap) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("school", getArguments().getString("school"));

        GetSchool getSchool = new GetSchool();
        try {
            Gson gson = new Gson();
            String jsonStr = getSchool.execute(params).get();
            SchoolVO schoolVO = gson.fromJson(jsonStr, SchoolVO.class);

            double latitude = Float.parseFloat(schoolVO.getLatitude());
            double longtitude = Float.parseFloat(schoolVO.getLongtitude());
            LatLng objLocation = new LatLng(latitude, longtitude);
            Marker objMK2 = googleMap.addMarker(new MarkerOptions().position(objLocation).title(schoolVO.getSchool()).snippet(schoolVO.getSchool()));
            objMK2.showInfoWindow();
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(objLocation));
            googleMap.moveCamera(CameraUpdateFactory.zoomTo(15));
            googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}