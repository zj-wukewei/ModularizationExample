package com.wkw.modularization;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.github.wkw.login.LoginActivity;
import com.vongihealth.network.download.DownloadManager;
import com.vongihealth.network.download.ProgressCallBack;
import com.vongihealth.pictures.PicturesActivity;
import com.wkw.archives.view.ArchivesActivity;
import com.wkw.commonbusiness.activity.MrActivity;
import com.wkw.ext.utils.ViewUtils;
import com.wkw.knowledge.KnowledgeActivity;
import com.wkw.modularization.widget.CurveChart;
import com.wkw.modularization.widget.LineChartView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;



public class MainActivity extends MrActivity {

    TextView tv_ruler_y;

    TextView tv_step_space;


    LineChartView lineChartView;

    private int[] dataArr = new int[]{200, 100, 300, -20, 50, -80, 200, 100, 300, 50, 200, 150, 160, 100, 300, 50, 200, 150,
            300, 50, 200, 100, 150, 150};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lineChartView = (LineChartView) findViewById(R.id.line_chart_view);
        tv_ruler_y = (TextView) findViewById(R.id.tv_ruler_y);
        tv_step_space = (TextView) findViewById(R.id.tv_step_space);

        List<LineChartView.Data> datas = new ArrayList<>();
        for (int value : dataArr) {
            LineChartView.Data data = new LineChartView.Data(value, "2019-11-11");
            datas.add(data);
        }
        LineChartView.ChartData data = new LineChartView.ChartData();
        data.setColor(Color.parseColor("#0cdfef"));
        data.setData(datas);
        lineChartView.setData(data);

        if (lineChartView != null) {
//            lineChartView.setRulerYSpace(ViewUtils.dpToPx(40));
            lineChartView.setRulerYSpace(80);
            lineChartView.setStepSpace(45);
            lineChartView.setBezierLine(true);
            lineChartView.setShowTable(true);
        }


        if (lineChartView != null) {
        }

    }

    private boolean isShowTable = false;

    public void tableToggle(View view) {
        if (lineChartView != null) {
            isShowTable = !isShowTable;
            lineChartView.setShowTable(isShowTable);
        }
    }

    private boolean isBezier = false;

    public void bezierModelToggle(View view) {
        if (lineChartView != null) {
            isBezier = !isBezier;
            lineChartView.setBezierLine(isBezier);
        }
    }

    private boolean isCube = false;

    public void pointModelToggle(View view) {
        if (lineChartView != null) {
            isCube = !isCube;
            lineChartView.setCubePoint(isCube);
        }
    }

    public void doAnimation(View view) {
        if (lineChartView != null) {
            lineChartView.playAnim();
        }
    }
    @Override
    protected String pageName() {
        return "AAA";
    }
}
