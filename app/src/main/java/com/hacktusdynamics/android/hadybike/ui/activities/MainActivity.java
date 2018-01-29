package com.hacktusdynamics.android.hadybike.ui.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.hacktusdynamics.android.hadybike.R;
import com.hacktusdynamics.android.hadybike.ui.fragments.BikeFragment;
import com.hacktusdynamics.android.hadybike.ui.fragments.HomeFragment;
import com.hacktusdynamics.android.hadybike.ui.fragments.TimelineFragment;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    /** We use a {@link FragmentPagerAdapter} derivative, which will keep every loaded fragment in memory. */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /** The {@link ViewPager} that will host the section contents. */
    private ViewPager mViewPager;

    /** The {@link TabLayout} that contains the tabs. */
    private TabLayout mTabLayout;

    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.setCurrentItem(1); getSupportActionBar().setSubtitle(getString(R.string.home));
        mViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                switch (position) {
                    case 0: {
                        getSupportActionBar().setSubtitle(getString(R.string.bike));
                        fab.hide();
                        break;
                    }
                    case 1: {
                        getSupportActionBar().setSubtitle(getString(R.string.home));
                        fab.show();
                        break;
                    }
                    case 2: {
                        getSupportActionBar().setSubtitle(getString(R.string.timeline));
                        fab.hide();
                        break;
                    }
                }
            }
        });

        mTabLayout = (TabLayout) findViewById(R.id.tabs);
        mTabLayout.setupWithViewPager(mViewPager);
        setupTabIcons(); //setup tab icons

        fab = (FloatingActionButton) findViewById(R.id.fab);
        final Activity activity = this;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: QR code reader
                IntentIntegrator integrator = new IntentIntegrator(activity);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
                integrator.setPrompt(" ");
                integrator.setCameraId(0);
                integrator.setOrientationLocked(false);
                integrator.setBeepEnabled(true);
                integrator.setBarcodeImageEnabled(false);
                integrator.initiateScan();

                /*
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                */
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null){
            if(result.getContents() == null){
                Log.d(TAG, "canceled scan");
                Toast.makeText(this, "Canceled", Toast.LENGTH_LONG).show();
            }else{
                Log.d(TAG, "Scanned");
                Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
            }
        }else {
            //this is important otherwise result will not pass to the fragment
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    /** Setup Icons for 3 tabs */
    private void setupTabIcons() {
        mTabLayout.getTabAt(0).setIcon(R.drawable.ic_bike_24dp);
        mTabLayout.getTabAt(1).setIcon(R.drawable.ic_home_24dp);
        mTabLayout.getTabAt(2).setIcon(R.drawable.ic_timeline_24dp);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar will automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_users: {
                Toast.makeText(this, "Users clicked!", Toast.LENGTH_LONG).show();
                startActivity(ProfileActivity.getIntent(this));
                return true;
            }
            case R.id.action_notification: {
                Toast.makeText(this, "Notification clicked!", Toast.LENGTH_LONG).show();
                startActivity(NotificationActivity.getIntent(this));
                return true;
            }
            case R.id.action_settings: {
                Toast.makeText(this, "Settings Clicked!", Toast.LENGTH_LONG).show();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    /** {@link FragmentPagerAdapter} that returns a fragment corresponding to one of the sections/tabs/pages. */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    return BikeFragment.newInstance();
                case 1:
                    return HomeFragment.newInstance();
                case 2:
                    return TimelineFragment.newInstance();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 3; // Show 3 total pages.
        }

        @Override
        public CharSequence getPageTitle(int position) {
            /* switch (position) { case 0: return "SECTION 1"; case 1: return "SECTION 2"; case 2: return "SECTION 3"; } */
            return null;
        }
    }
}
