package com.quanlithuchi.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.quanlithuchi.R;
import com.quanlithuchi.adapters.ViewPagerAdapter;

public class ChiFragment extends Fragment {

    ViewPager2 viewPager;

    public ChiFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_chi, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getActivity(), "Chi");
        viewPager = view.findViewById(R.id.chi_ViewPager);
        viewPager.setAdapter(viewPagerAdapter);

        TabLayout tabLayout = view.findViewById(R.id.tablayout_chi);
        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            if (position == 0) {
                tab.setText("Khoản Chi").setIcon(R.drawable.ic_tablayout_header_khoan_thu_chi);
            } else {
                tab.setText("Loại Chi").setIcon(R.drawable.ic_tablayout_header_loai_thu_chi);
            }
        }).attach();
        super.onViewCreated(view, savedInstanceState);
    }
}