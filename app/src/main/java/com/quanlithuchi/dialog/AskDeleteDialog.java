package com.quanlithuchi.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.quanlithuchi.R;


public class AskDeleteDialog extends Dialog {

    public TextView tvNoiDung;
    public Button btnHuyBo;
    public Button btnXacNhan;

    public AskDeleteDialog(@NonNull Context context) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_confirm_delete);
        Window window = getWindow();
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        window.getAttributes().windowAnimations = R.style.dialogScale;

        tvNoiDung = findViewById(R.id.dialog_ask_delete_tv_content);
        btnHuyBo = findViewById(R.id.dialog_ask_delete_btn_cancel);
        btnXacNhan = findViewById(R.id.dialog_ask_delete_btn_confirm);

        btnHuyBo.setOnClickListener(view -> dismiss());
    }
}
