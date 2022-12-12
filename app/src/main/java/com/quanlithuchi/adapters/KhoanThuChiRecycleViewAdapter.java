package com.quanlithuchi.adapters;


import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.quanlithuchi.R;
import com.quanlithuchi.db.GiaoDichDAO;
import com.quanlithuchi.db.PhanLoaiDAO;
import com.quanlithuchi.dialog.AddEditKhoanDialog;
import com.quanlithuchi.dialog.AskDeleteDialog;
import com.quanlithuchi.models.GiaoDich;
import com.quanlithuchi.models.PhanLoai;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class KhoanThuChiRecycleViewAdapter extends RecyclerView.Adapter<KhoanThuChiRecycleViewAdapter.ViewHolder> {

    private Context context;
    private ArrayList<GiaoDich> listGiaoDich = new ArrayList<>();
    private ArrayList<PhanLoai> listPhanLoai = new ArrayList<>();

    private GiaoDichDAO giaoDichDAO;
    private PhanLoaiDAO phanLoaiDAO;

    public KhoanThuChiRecycleViewAdapter(Context context , String type) {
        this.context = context;
        giaoDichDAO = new GiaoDichDAO(context);
        phanLoaiDAO = new PhanLoaiDAO(context);

        listGiaoDich = giaoDichDAO.layTatCa(type);
        listPhanLoai = phanLoaiDAO.layTatCa(type);

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_khoan_thu_chi, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        GiaoDich giaoDich = listGiaoDich.get(position);
        if (giaoDich != null) {
            int color = Color.rgb(46, 204, 113);
            String flag = "+";
            if (giaoDich.getTenLoai().equals(GiaoDichDAO.CHI)) {
                color = Color.rgb(231, 76, 60);
                flag = "-";
            }
            holder.tvAmount.setText(flag + NumberFormat.getNumberInstance(Locale.US).format(giaoDich.getTien()));
            holder.tvAmount.setTextColor(color);
            holder.tvName.setText(giaoDich.getTenGD());
            holder.tvDescription.setText(giaoDich.getMoTa());
            holder.tvTime.setText(giaoDich.getNgay());
            holder.tvType.setText(giaoDich.getTenLoai());
            holder.btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AskDeleteDialog dialog = new AskDeleteDialog(view.getContext());
                    dialog.tvNoiDung.setText(String.format(dialog.tvNoiDung.getText().toString(), giaoDich.getTenGD()));
                    dialog.btnXacNhan.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            giaoDichDAO.xoa(giaoDich);
                            listGiaoDich.remove(giaoDich);
                            notifyDataSetChanged();
                            dialog.dismiss();
                        }
                    });
                    dialog.show();
                }
            });
            holder.btnEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AddEditKhoanDialog dialog = new AddEditKhoanDialog(view.getContext());
                    dialog.tvTitle.setText("Sửa khoản thu");
                    dialog.tilTenGiaoDich.getEditText().setText(giaoDich.getTenGD());
                    dialog.tilTien.getEditText().setText(NumberFormat.getNumberInstance(Locale.US).format(giaoDich.getTien()).replace(",",""));
                    dialog.tilMota.getEditText().setText(giaoDich.getMoTa());
                    dialog.tilNgay.getEditText().setText(giaoDich.getNgay());
                    dialog.setSpinnerData(view.getContext(), listPhanLoai);

for (int i = 0; i < listPhanLoai.size(); i++) {
    PhanLoai pl = (PhanLoai) listPhanLoai.get(i);
    if (giaoDich.getMaLoai() == pl.getMaLoai()) {
        dialog.spinPhanLoai.setSelection(i);
        break;
    }
}
                    dialog.btnXacNhan.setText("SỬA");
                    dialog.btnXacNhan.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String tenGD = dialog.tilTenGiaoDich.getEditText().getText().toString();
                            String tien = dialog.tilTien.getEditText().getText().toString();
                            String ngay = dialog.tilNgay.getEditText().getText().toString();
                            String moTa = dialog.tilMota.getEditText().getText().toString();
                            PhanLoai phanLoai2 = (PhanLoai)dialog.spinPhanLoai.getSelectedItem();

                            if (tenGD.isEmpty() || tien.isEmpty() || ngay.isEmpty()) {
                                return;
                            }
                            GiaoDich newGiaoDich = new GiaoDich(giaoDich.getMaGD(), tenGD,ngay, Float.parseFloat(tien), moTa, phanLoai2.getMaLoai());
                            giaoDichDAO.sua(newGiaoDich);
                            newGiaoDich.setTenLoai(phanLoai2.getTenLoai());
                            listGiaoDich.set(position, newGiaoDich);
                            notifyDataSetChanged();
                            dialog.dismiss();
                        }
                    });
                    dialog.show();
                }
            });
        }
    }



    @Override
    public int getItemCount() {
        return listGiaoDich.size();
    }

    public void supportAddItem(GiaoDich giaoDich) {
        giaoDichDAO.sua(giaoDich);
        giaoDich.setTenLoai(giaoDich.getTenLoai());
        listGiaoDich.add(giaoDich);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvAmount;
        private TextView tvName;
        private TextView tvType;
        private TextView tvDescription;
        private TextView tvTime;
        private TextView btnEdit;
        private TextView btnDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvAmount = itemView.findViewById(R.id.tv_sotien);
            tvName = itemView.findViewById(R.id.tv_tenkhoan_thuchi);
            tvType = itemView.findViewById(R.id.tv_loai_thuchi);
            tvTime = itemView.findViewById(R.id.tv_thoigian);
            tvDescription = itemView.findViewById(R.id.tv_mota_thuchi);

            btnEdit = itemView.findViewById(R.id.btn_edit_khoan_thuchi);
            btnDelete = itemView.findViewById(R.id.btn_delete_khoan_thuchi);
        }
    }
}
