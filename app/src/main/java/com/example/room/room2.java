package com.example.room;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
public class room2 extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private FragmentManager fm;
    private FragmentTransaction ft;
    private frag1 Frag1;
    private frag2 Frag2;
    private frag3 Frag3;
    private GoogleMap mMap;
    private GoogleApiClient mGoogleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room2);


        bottomNavigationView = findViewById(R.id.bottomNavi);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch(menuItem.getItemId()){
                    case R.id.camera:
                        setFrag(0);
                        break;
                    case R.id.favorite:
                        setFrag(1);
                        Toast.makeText(getApplicationContext(), "관심사로 등록되었습니다", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.nearby:
                        setFrag(2);
                        break;
                }
                return true;
            }
        });
        Frag1 = new frag1();
        Frag2 = new frag2();
        Frag3 = new frag3();
        setFrag(0); //첫번째 프래그먼트를 어떻게 정해 줄지

    }
//프레그먼트 교체
    private void setFrag(int n){
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        switch (n){
            case 0:
                ft.replace(R.id.main_frame, Frag1);
                ft.commit();
                break;
            case 1:
                ft.replace(R.id.main_frame,Frag2);
                ft.commit();
                break;
            case 2:
                ft.replace(R.id.main_frame, Frag3);
                ft.commit();
                break;
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_maps, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_0:
                Intent i = new Intent(room2.this, MapsActivity.class);
                startActivity(i);
                //버튼을 만들어서 최근으로 돌가아면 좋을듯
                LatLng cham = new LatLng(37.301990, 127.039666);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(cham));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(17.0f));
                break;
            case R.id.menu_1:
                Intent i1 = new Intent(room2.this, room2.class);
                startActivity(i1);
                break;
            case R.id.menu_2:
                Intent i2 = new Intent(room2.this, main.class);
                startActivity(i2);
                break;
        }
        return true;
    }
}
