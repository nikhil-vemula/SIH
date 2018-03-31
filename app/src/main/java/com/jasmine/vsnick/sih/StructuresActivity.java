package com.jasmine.vsnick.sih;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class StructuresActivity extends AppCompatActivity {
    private Location location;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_structures);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        AppBarLayout appBarLayout = findViewById(R.id.app_bar);
//        appBarLayout.setBackground(R.drawable.checkdam);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Water level will be added", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        Structure structure = (Structure) getIntent().getSerializableExtra("structure");
        Log.d("My Activity",structure.getId());
        CollapsingToolbarLayout collapsingToolbarLayout= findViewById(R.id.toolbar_layout);
        collapsingToolbarLayout.setTitle(structure.getType());

        location = structure.getLocation();

        TextView view ;
        view = findViewById(R.id.structure_state);
        view.setText(structure.getState());
        view = findViewById(R.id.structure_dept);
        view.setText(structure.getDept());
        view = findViewById(R.id.structure_type);
        view.setText(structure.getType());
        view = findViewById(R.id.structure_status);
        view.setText(structure.getStatus());

        final ListView listView = findViewById(R.id.water_level_list_view);
        final ArrayList<WaterLevel> data = new ArrayList<>();
        final ArrayAdapter<WaterLevel> adapter = new CustomLevelAdapter(data,getApplicationContext());
        listView.setAdapter(adapter);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("waterlevel/"+structure.getId()+"/");
        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                WaterLevel waterlevel = dataSnapshot.getValue(WaterLevel.class);
                Log.d("My Activity",waterlevel.toString());
                adapter.add(waterlevel);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        ImageView typeImage = findViewById(R.id.structure_img_view);
        if(structure.getType().equals("tube-well")){
            typeImage.setImageResource(this.getResources().getIdentifier("tubewell","drawable",this.getPackageName()));
        }else if(structure.getType().equals("check-dam")){
            typeImage.setImageResource(this.getResources().getIdentifier("checkdam","drawable",this.getPackageName()));
        }else if(structure.getType().equals("percolation")){
            typeImage.setImageResource(this.getResources().getIdentifier("percolation","drawable",this.getPackageName()));
        }

    }

    public void map(View view) {
        Intent intent = new Intent(this,MapsActivity.class);
        intent.putExtra("location",location);
        startActivity(intent);
    }
}
