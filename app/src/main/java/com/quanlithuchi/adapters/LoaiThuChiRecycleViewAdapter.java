package com.quanlithuchi.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.quanlithuchi.R;
import com.quanlithuchi.db.PhanLoaiDAO;
import com.quanlithuchi.dialog.AskDeleteDialog;
import com.quanlithuchi.dialog.AddEditLoaiDialog;
import com.quanlithuchi.models.PhanLoai;

import java.util.ArrayList;

public class LoaiThuChiRecycleViewAdapter extends RecyclerView.Adapter<LoaiThuChiRecycleViewAdapter.ViewHolder> {

    private Context context;
    private ArrayList<PhanLoai> listPhanLoai;
    private String phanLoaiType;
    private PhanLoaiDAO phanLoaiDAO;

    public LoaiThuChiRecycleViewAdapter(Context context, String type) {
        this.context = context;
        this.phanLoaiType = type;
        phanLoaiDAO = new PhanLoaiDAO(context);
        listPhanLoai = phanLoaiDAO.layTatCa(type);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loai_thu_chi, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        PhanLoai phanLoai = listPhanLoai.get(position);
        if (phanLoai != null) {
            holder.tvName.setText(phanLoai.getTenLoai());
            holder.btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AskDeleteDialog dialog = new AskDeleteDialog(view.getContext());
                    String noiDung = String.format(dialog.tvNoiDung.getText().toString(), phanLoai.getTenLoai());
                    dialog.tvNoiDung.setText(noiDung);
                    dialog.btnXacNhan.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            phanLoaiDAO.xoa(phanLoai);
                            listPhanLoai.remove(phanLoai);
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
                    AddEditLoaiDialog dialog = new AddEditLoaiDialog(view.getContext());
                    dialog.tilTenLoai.getEditText().setText(phanLoai.getTenLoai());
                    dialog.btnXacNhan.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            int maLoai = phanLoai.getMaLoai();
                            String tenLoai = dialog.tilTenLoai.getEditText().getText().toString();
                            PhanLoai phanLoai1 = new PhanLoai(maLoai, tenLoai, phanLoaiType);
                            phanLoaiDAO.sua(phanLoai1);
                            listPhanLoai.set(position, phanLoai1);
                            notifyDataSetChanged();
                            dialog.dismiss();
                        }
                    });
                    dialog.show();
                }
            });
        }
    }

    public void supportAddItem(PhanLoai phanLoai) {
        phanLoaiDAO.them(phanLoai);
        listPhanLoai.add(phanLoai);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return listPhanLoai.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvName;
        private TextView btnDelete;
        private TextView btnEdit;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_ten_loai);
            btnDelete = itemView.findViewById(R.id.btn_delete_loai);
            btnEdit = itemView.findViewById(R.id.btn_edit_loai);
        }
    }
}
