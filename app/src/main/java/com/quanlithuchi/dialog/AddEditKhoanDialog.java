package com.quanlithuchi.dialog;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.android.material.textfield.TextInputLayout;
import com.quanlithuchi.R;
import com.quanlithuchi.adapters.PhanLoaiSpinnerAdapter;
import com.quanlithuchi.models.PhanLoai;

import java.util.ArrayList;
import java.util.Calendar;

public class AddEditKhoanDialog extends Dialog {

    public TextView tvTitle;
    public TextInputLayout tilTenGiaoDich;
    public TextInputLayout tilTien;
    public TextInputLayout tilMota;
    public TextInputLayout tilNgay;
    public Button btnHuyBo;
    public Button btnXacNhan;
    public Spinner spinPhanLoai;


    public AddEditKhoanDialog(@NonNull Context context) {
        super(context);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setContentView(R.layout.dialog_add_edit_khoan);
        Window window = this.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        window.getAttributes().windowAnimations = R.style.dialogScale;

        this.tvTitle = findViewById(R.id.dialog_addedit_tv_title);
        this.tilTenGiaoDich = findViewById(R.id.dialog_addedit_til_ten_giao_dich);
        this.tilTien = findViewById(R.id.dialog_addedit_til_so_tien);
        this.tilMota = findViewById(R.id.dialog_addedit_til_mo_ta);
        this.tilNgay = findViewById(R.id.dialog_addedit_til_ngay);
        this.btnHuyBo = findViewById(R.id.dialog_addedit_btn_cancel);
        this.btnXacNhan = findViewById(R.id.dialog_addedit_btn_confirm);
        this.spinPhanLoai = findViewById(R.id.phanLoaiSpinner);
        tilTenGiaoDich.getEditText().setInputType(InputType.TYPE_CLASS_TEXT);
        tilTenGiaoDich.getEditText().setImeOptions(EditorInfo.IME_ACTION_NEXT);
        tilTenGiaoDich.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int count, int i2) {
                int length = charSequence.toString().length();
                if (length <= 2) {
                    tilTenGiaoDich.setError("Tên giao dịch quá ngắn");
                } else if (length > 35) {
                    tilTenGiaoDich.setError("Tên giao dịch quá dài");
                } else if (tilTenGiaoDich.getError() != null){
                    tilTenGiaoDich.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        this.tilMota.getEditText().setInputType(InputType.TYPE_CLASS_TEXT);
        this.tilMota.getEditText().setImeOptions(EditorInfo.IME_ACTION_DONE);

        this.tilTien.getEditText().setInputType(InputType.TYPE_CLASS_NUMBER);
        this.tilTien.getEditText().setImeOptions(EditorInfo.IME_ACTION_NEXT);
        this.tilTien.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                try {
                    Float.parseFloat(editable.toString());
                    tilTien.setErrorEnabled(false);
                } catch (Exception e) {
                    tilTien.setError("Số tiền không hợp lệ");
                }
            }
        });
        this.tilNgay.getEditText().setFocusable(false);
        this.tilNgay.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                DatePickerDialog datePickerDialog = new DatePickerDialog(view.getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        String ngay = (i2 < 10 ? "0" : "") + i2;
                        String thang = (i1 < 10 ? "0" : "") + i1;
                        tilNgay.getEditText().setText(String.format("%s/%s/%s", ngay, thang, i));
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });
        this.btnHuyBo.setOnClickListener(view -> dismiss());
    }

    public void setSpinnerData(Context context, ArrayList<PhanLoai> listPhanLoai) {
        PhanLoaiSpinnerAdapter spinnerAdapter = new PhanLoaiSpinnerAdapter(context, R.layout.item_spinner_phan_loai_selected, listPhanLoai);
        spinPhanLoai.setAdapter(spinnerAdapter);
    }
}
