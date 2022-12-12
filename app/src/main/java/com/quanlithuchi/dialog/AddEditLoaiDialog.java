package com.quanlithuchi.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.InputType;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.android.material.textfield.TextInputLayout;
import com.quanlithuchi.R;

public class AddEditLoaiDialog extends Dialog {

    public TextView tvTieuDe;
    public TextInputLayout tilTenLoai;
    public Button btnHuyBo;
    public Button btnXacNhan;

    public AddEditLoaiDialog(@NonNull Context context) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_add_edit_loai);
        Window window = getWindow();
        window.getAttributes().windowAnimations = R.style.dialogSlide;
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        tvTieuDe = findViewById(R.id.dialog_add_edit_loai_tvTitle);
        tilTenLoai = findViewById(R.id.dialog_add_edit_loai_etTenLoai);
        btnHuyBo = findViewById(R.id.dialog_add_edit_loai_btnCancel);
        btnXacNhan = findViewById(R.id.dialog_add_edit_loai_btnConfirm);

        tilTenLoai.getEditText().setInputType(InputType.TYPE_CLASS_TEXT);
        tilTenLoai.getEditText().setImeOptions(EditorInfo.IME_ACTION_DONE);

        tilTenLoai.setHint("Tên loại");
        tvTieuDe.setText("Sửa tên loại");
        btnHuyBo.setOnClickListener(view -> dismiss());
    }
}
