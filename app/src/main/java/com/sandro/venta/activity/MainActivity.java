package com.sandro.venta.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.sandro.venta.R;
import com.sandro.venta.api.model.ControlResponse;
import com.sandro.venta.api.model.CustomerResponse;
import com.sandro.venta.api.model.ProductResponse;
import com.sandro.venta.api.service.ControlListServiceInterface;
import com.sandro.venta.api.service.ControlListServicePresenter;
import com.sandro.venta.api.service.CustomerServiceInterface;
import com.sandro.venta.api.service.CustomerServicePresenter;
import com.sandro.venta.api.service.ProductServiceInterface;
import com.sandro.venta.api.service.ProductServicePresenter;
import com.sandro.venta.bean.Client;
import com.sandro.venta.bean.Control;
import com.sandro.venta.bean.Product;
import com.sandro.venta.bean.SalesMan;
import com.sandro.venta.fragment.TabClients;
import com.sandro.venta.fragment.TabListOrders;
import com.sandro.venta.helper.DatabaseHelper;
import com.sandro.venta.util.SessionManager;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private final String TAG = "GGRANADOS";

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    @BindView(R.id.container) ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

        final Intent intent = new Intent(getIntent());
        int value = intent.getIntExtra("value", -1);
        if(value == 2){
            mViewPager.setCurrentItem(1);
        } else {
            mViewPager.setCurrentItem(0);
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    TabClients tabClients = new TabClients();
                    return tabClients;
                case 1:
                    TabListOrders tabListOrders = new TabListOrders();
                    return tabListOrders;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 2;
        }
    }
}
