package com.example.room;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class main extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<User> arrayList;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        recyclerView =findViewById(R.id.recycle); //아디 연결
        recyclerView.setHasFixedSize(true); //리사이클뷰 성능강화
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        arrayList = new ArrayList<>(); //user 객체를 담을 arraylist;

        database = FirebaseDatabase.getInstance();//파이어 베이스 데이터베이스 연동

        databaseReference = database.getReference("User");//db테이블 연결
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //파이어베이스 데이터 베이스에 데이터를 받아오는 곳
                arrayList.clear(); //기존 배열리스트가 존재하지 않게 초기화
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){//반복문 데이터 리스트 추출
                    User user = snapshot.getValue(User.class);//만들어놨던 user 객체에 데이터를 담는다.
                    arrayList.add(user);//담은 데이터 배열리스트에 넣고 리사이클뷰에 보냄

                }
                adapter.notifyDataSetChanged();//리스트 저장및 새로 고침
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //에러가 db를 가져오던중에 발생시에 뭐를 띄울지
                Log.e("main", String.valueOf(databaseError.toException()));//에러문 출력
            }
        });

        adapter = new CustomAdapter(arrayList, this);
        recyclerView.setAdapter(adapter);//리사이클뷰 어댑터 연결

    }
}
