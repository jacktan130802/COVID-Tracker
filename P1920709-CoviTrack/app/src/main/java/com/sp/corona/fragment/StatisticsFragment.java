

package com.sp.corona.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.sp.corona.Constants;
import com.sp.corona.R;
import com.sp.corona.model.casetime.Cases_time_series;
import com.sp.corona.model.casetime.Statewise;
import com.sp.corona.util.PrefUtil;
import com.sp.corona.viewmodel.DailyReportModel;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StatisticsFragment extends Fragment {

    @BindView(R.id.coordinator)
    CoordinatorLayout coordinator;
    @BindView(R.id.chart_bar)
    BarChart chart;
    @BindView(R.id.chart_pie)
    PieChart chartPie;

    private Gson gson = new Gson();

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_statistics, container, false);
        ButterKnife.bind(this, root);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        DailyReportModel model = new ViewModelProvider(this).get(DailyReportModel.class);
        model.getData().observe(getViewLifecycleOwner(), dailyItems -> {
            setupBarChart(dailyItems);
        });
        setupPieChart();
    }

    private void setupBarChart(List<Cases_time_series> casesTimeSeriesList) {

        chart.setDrawBarShadow(false);
        chart.setDrawValueAboveBar(true);

        chart.setPinchZoom(false);
        chart.setDrawGridBackground(false);

        final Description description = chart.getDescription();
        description.setEnabled(false);

        final XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setGranularity(1f);
        xAxis.setDrawLabels(true);
        xAxis.setTextColor(Color.BLACK);
        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return casesTimeSeriesList.get((int) value / 3).getDate();
            }
        });

        chart.getAxisRight().setEnabled(false);

        final YAxis yAxis = chart.getAxisLeft();
        yAxis.setDrawGridLines(false);
        yAxis.setDrawAxisLine(false);
        yAxis.setTextColor(Color.WHITE);

        final Legend legend = chart.getLegend();
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);

        legend.setDrawInside(true);
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setFormSize(8f);
        legend.setTextSize(10f);
        legend.setYOffset(12f);
        legend.setTextColor(Color.WHITE);

        final ArrayList<BarEntry> valuesTotal = new ArrayList<>();
        final ArrayList<BarEntry> valuesRecovered = new ArrayList<>();
        final ArrayList<BarEntry> valuesDeaths = new ArrayList<>();

        int i = 0;
        for (Cases_time_series casesTimeSeries : casesTimeSeriesList) {
            valuesTotal.add(new BarEntry(i++, Float.parseFloat(casesTimeSeries.getDailyconfirmed())));
            valuesRecovered.add(new BarEntry(i++, Float.parseFloat(casesTimeSeries.getDailyrecovered())));
            valuesDeaths.add(new BarEntry(i++, Float.parseFloat(casesTimeSeries.getDailydeceased())));
        }

        final BarDataSet set1 = new BarDataSet(valuesTotal, "New Cases");
        set1.setColor(getResources().getColor(R.color.colorRed));
        set1.setValueTextColor(Color.WHITE);

        final BarDataSet set2 = new BarDataSet(valuesRecovered, "Recovered");
        set2.setColor(getResources().getColor(R.color.colorGreen));
        set2.setValueTextColor(Color.WHITE);

        final BarDataSet set3 = new BarDataSet(valuesDeaths, "Deaths");
        set3.setColor(getResources().getColor(R.color.colorBlue));
        set3.setValueTextColor(Color.WHITE);

        chart.setData(new BarData(set1, set2, set3));
        chart.animateY(1400, Easing.Linear);
    }

    private void setupPieChart() {
        chartPie.setUsePercentValues(true);
        chartPie.getDescription().setEnabled(false);
        chartPie.setExtraOffsets(5, 10, 5, 5);

        chartPie.setDragDecelerationFrictionCoef(0.95f);

        chartPie.setDrawHoleEnabled(true);
        chartPie.setHoleColor(Color.RED);

        chartPie.setTransparentCircleColor(Color.WHITE);
        chartPie.setTransparentCircleAlpha(110);

        chartPie.setHoleRadius(10f);
        chartPie.setTransparentCircleRadius(15f);

        chartPie.setDrawCenterText(true);

        chartPie.setRotationAngle(0);
        chartPie.setRotationEnabled(true);
        chartPie.setHighlightPerTapEnabled(true);

        chartPie.animateY(1400, Easing.EaseInOutQuad);

        final Legend legend = chartPie.getLegend();
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        legend.setOrientation(Legend.LegendOrientation.VERTICAL);
        legend.setTextColor(Color.BLACK);
        legend.setDrawInside(false);
        legend.setXEntrySpace(7f);
        legend.setYEntrySpace(0f);
        legend.setYOffset(0f);

        final ArrayList<PieEntry> entries = new ArrayList<>();

        final String rawOverAll = PrefUtil.getString(requireContext(), Constants.PREFERENCE_OVERALL_DATA);
        final Statewise statewise = gson.fromJson(rawOverAll, Statewise.class);

        if (statewise != null) {
            entries.add(new PieEntry(Integer.parseInt(statewise.getRecovered()), "Recovered"));
            entries.add(new PieEntry(Integer.parseInt(statewise.getDeaths()), "Deaths"));
            entries.add(new PieEntry(Integer.parseInt(statewise.getActive()), "Active"));
        }

        final PieDataSet dataSet = new PieDataSet(entries, "Percentage Stats");

        dataSet.setDrawIcons(true);
        dataSet.setSliceSpace(3f);
        dataSet.setIconsOffset(new MPPointF(0, 40));
        dataSet.setSelectionShift(5f);

        final ArrayList<Integer> colors = new ArrayList<>();
        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);

        dataSet.setColors(colors);
        dataSet.setSelectionShift(0f);

        final PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter(chartPie));
        data.setValueTextSize(10f);
        data.setValueTextColor(Color.BLACK);

        chartPie.setData(data);
        chartPie.highlightValues(null);
        chartPie.invalidate();
        chartPie.setEntryLabelColor(Color.BLACK);
        chartPie.setEntryLabelTextSize(12f);
    }
}

