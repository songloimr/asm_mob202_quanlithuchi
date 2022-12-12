package com.quanlithuchi.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.quanlithuchi.R;
import com.quanlithuchi.models.PhanLoai;

import java.util.List;

public class PhanLoaiSpinnerAdapter extends ArrayAdapter<PhanLoai> {

    Context mContext;
    public PhanLoaiSpinnerAdapter(@NonNull Context context, int resource, @NonNull List<PhanLoai> objects) {
        super(context, resource, objects);
        mContext = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(mContext).inflate(R.layout.item_spinner_phan_loai_selected, parent, false);
        TextView tvTenPhanLoai = convertView.findViewById(R.id.tvSpinnerItem2);

        PhanLoai phanLoai = this.getItem(position);
        if (phanLoai != null) {
            tvTenPhanLoai.setText(phanLoai.getTenLoai());
        }
        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(mContext).inflate(R.layout.item_spinner_phan_loai, parent, false);
        TextView tvTenPhanLoai = convertView.findViewById(R.id.tvSpinnerItem);

        PhanLoai phanLoai = this.getItem(position);
        if (phanLoai != null) {
            tvTenPhanLoai.setText(phanLoai.getTenLoai());
        }
        return convertView;
    }
}











