package com.jasmine.vsnick.sih;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction= manager.beginTransaction();
            Fragment frag;
            if( (frag=manager.findFragmentByTag("structures"))!=null){
               transaction.remove(frag);
            }
            if( (frag=manager.findFragmentByTag("analysis"))!=null) {
                transaction.remove(frag);
            }
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fragment = new StructuresFragment();
                    transaction.add(R.id.fragment_container,fragment,"structures");
                    transaction.commit();
                    return true;
                case R.id.navigation_dashboard:
                    fragment = new AnalysisFragment();
                    transaction.add(R.id.fragment_container,fragment,"analysis");
                    transaction.commit();
                    return true;
//                case R.id.navigation_notifications:
//                    mTextMessage.setText(R.string.title_notifications);
//                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction= manager.beginTransaction();
        Fragment fragment = new StructuresFragment();
        transaction.add(R.id.fragment_container,fragment,"structures");
        transaction.commit();
    }

}
