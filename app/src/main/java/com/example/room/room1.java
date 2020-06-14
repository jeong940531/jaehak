package com.example.room;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class room1 extends AppCompatActivity {
    Button btn_school;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room1);
        btn_school = (Button) findViewById(R.id.btn_school);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_maps, menu);
        return true;
    }
    protected void onStart(){
        super.onStart();
        btn_school.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.naver.com")));
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_0:
                Intent i = new Intent(room1.this, MapsActivity.class);
                startActivity(i);
                break;
            case R.id.menu_1:
                Intent i1 = new Intent(room1.this, room2.class);
                startActivity(i1);
                break;
            case R.id.menu_2:
                Intent i2 = new Intent(room1.this, main.class);
                startActivity(i2);
                break;
        }
        return true;
    }
}