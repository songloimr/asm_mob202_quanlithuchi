package com.quanlithuchi.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.quanlithuchi.fragments.KhoanChiFragment;
import com.quanlithuchi.fragments.KhoanThuFragment;
import com.quanlithuchi.fragments.LoaiChiFragment;
import com.quanlithuchi.fragments.LoaiThuFragment;


public class ViewPagerAdapter extends FragmentStateAdapter {
    String type;
    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity, String type) {
        super(fragmentActivity);
        this.type = type;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (type == "Thu") {
            return position == 0 ? new KhoanThuFragment() : new LoaiThuFragment();
        }
        return position == 0 ? new KhoanChiFragment() : new LoaiChiFragment();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
