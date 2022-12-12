package com.quanlithuchi.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.quanlithuchi.R;
import com.quanlithuchi.adapters.LoaiThuChiRecycleViewAdapter;
import com.quanlithuchi.db.PhanLoaiDAO;
import com.quanlithuchi.dialog.AddEditLoaiDialog;
import com.quanlithuchi.models.PhanLoai;


public class LoaiThuFragment extends Fragment {

    RecyclerView recyclerView;
    LoaiThuChiRecycleViewAdapter loaiThuChiRecycleViewAdapter;

    public LoaiThuFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_loai_thu, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext(), RecyclerView.VERTICAL, false);

        loaiThuChiRecycleViewAdapter = new LoaiThuChiRecycleViewAdapter(view.getContext(), PhanLoaiDAO.THU);

        recyclerView = view.findViewById(R.id.rcv_loai_thu);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(loaiThuChiRecycleViewAdapter);

        FloatingActionButton fab = view.findViewById(R.id.btn_add_loai_thu);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddEditLoaiDialog dialog = new AddEditLoaiDialog(view.getContext());
                dialog.tvTieuDe.setText("Thêm loại thu");
                dialog.btnXacNhan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        PhanLoai newPhanLoai = new PhanLoai(dialog.tilTenLoai.getEditText().getText().toString(), PhanLoaiDAO.THU);
                        loaiThuChiRecycleViewAdapter.supportAddItem(newPhanLoai);
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });


        super.onViewCreated(view, savedInstanceState);
    }
}