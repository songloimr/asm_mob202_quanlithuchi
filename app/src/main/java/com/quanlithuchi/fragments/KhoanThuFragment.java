package com.quanlithuchi.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.quanlithuchi.R;
import com.quanlithuchi.adapters.KhoanThuChiRecycleViewAdapter;
import com.quanlithuchi.db.GiaoDichDAO;
import com.quanlithuchi.db.PhanLoaiDAO;
import com.quanlithuchi.dialog.AddEditKhoanDialog;
import com.quanlithuchi.dialog.AddEditLoaiDialog;
import com.quanlithuchi.models.GiaoDich;
import com.quanlithuchi.models.PhanLoai;

import java.util.ArrayList;

//01549d
public class KhoanThuFragment extends Fragment {

    RecyclerView recyclerView;
    KhoanThuChiRecycleViewAdapter khoanThuChiRecycleViewAdapter;
    public KhoanThuFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_khoan_thu, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        khoanThuChiRecycleViewAdapter = new KhoanThuChiRecycleViewAdapter(view.getContext(), GiaoDichDAO.THU);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext(), RecyclerView.VERTICAL, false);

        recyclerView = view.findViewById(R.id.rcv_khoan_thu);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(khoanThuChiRecycleViewAdapter);

        FloatingActionButton fab = view.findViewById(R.id.btn_add_khoan_thu);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddEditKhoanDialog dialog = new AddEditKhoanDialog(view.getContext());
                PhanLoaiDAO phanLoaiDAO = new PhanLoaiDAO(view.getContext());
                dialog.tvTitle.setText("Thêm khoản thu");
                dialog.setSpinnerData(view.getContext(), phanLoaiDAO.layTatCa(GiaoDichDAO.THU));
                dialog.btnXacNhan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String tenGD = dialog.tilTenGiaoDich.getEditText().getText().toString();
                        String tien = dialog.tilTien.getEditText().getText().toString();
                        String ngay = dialog.tilNgay.getEditText().getText().toString();
                        String moTa = dialog.tilMota.getEditText().getText().toString();


                        PhanLoai spinPhanLoaiSelectedItem = (PhanLoai)dialog.spinPhanLoai.getSelectedItem();
                        GiaoDich giaoDich = new GiaoDich(tenGD, ngay, Float.parseFloat(tien), moTa, spinPhanLoaiSelectedItem.getMaLoai());
                        giaoDich.setTenLoai(spinPhanLoaiSelectedItem.getTrangThai());
                        khoanThuChiRecycleViewAdapter.supportAddItem(giaoDich);
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });

        super.onViewCreated(view, savedInstanceState);
    }
}