package com.quanlithuchi.fragments;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.quanlithuchi.R;
import com.quanlithuchi.db.ThongKeDAO;

import java.util.ArrayList;


public class ThongKeFragment extends Fragment implements OnChartValueSelectedListener {


    public ThongKeFragment() {
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
        return inflater.inflate(R.layout.fragment_thong_ke, container, false);
    }

    PieChart mChart;
    ThongKeDAO thongKeDAO;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        thongKeDAO = new ThongKeDAO(view.getContext());
        mChart = view.findViewById(R.id.piechart);
        mChart.setRotationEnabled(true);
        mChart.setHoleRadius(35f);
        mChart.setTransparentCircleAlpha(0);
        mChart.setCenterText("Thống kê");
        mChart.setCenterTextSize(15f);
        mChart.setDrawEntryLabels(true);
        addDataSet(mChart);
        mChart.setOnChartValueSelectedListener(this);
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }

    private void addDataSet(PieChart pieChart) {
        ArrayList<PieEntry> entrys = new ArrayList<>();
        float[] yData = thongKeDAO.layTongThuChi();
        String[] xData = { "Khoản thu", "Khoản chi" };
        for (int i = 0; i < yData.length;i++){
            entrys.add(new PieEntry(yData[i], xData[i]));
        }

        PieDataSet pieDataSet = new PieDataSet(entrys," ");
        pieDataSet.setSliceSpace(2f);
        pieDataSet.setValueTextSize(12f);
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(ColorTemplate.JOYFUL_COLORS[0]);
        colors.add(ColorTemplate.JOYFUL_COLORS[1]);
        pieDataSet.setColors(colors);

        Legend l = pieChart.getLegend();
        l.setTextSize(15f);
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setForm(Legend.LegendForm.SQUARE);
        l.setFormSize(20f);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(5f);

        pieChart.setEntryLabelTextSize(13f);
        pieChart.setData(new PieData(pieDataSet));
        pieChart.invalidate();
    }
}