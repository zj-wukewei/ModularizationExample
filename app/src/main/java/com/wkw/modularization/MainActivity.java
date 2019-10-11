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

    private int[] dataArr = new int[]{200, 100, 300, -20, 50, -80};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lineChartView = (LineChartView) findViewById(R.id.line_chart_view);
        tv_ruler_y = (TextView) findViewById(R.id.tv_ruler_y);
        tv_step_space = (TextView) findViewById(R.id.tv_step_space);

        List<LineChartView.Data> datas = new ArrayList<>();


        LineChartView.Data data = new LineChartView.Data(200, "2019-" + (1 + 1) + "-11");
        LineChartView.Data data1 = new LineChartView.Data(100, "2019-" + (1 + 1) + "-14");
        LineChartView.Data data2 = new LineChartView.Data(300, "2019-" + (3 + 1) + "-11");
        LineChartView.Data data3 = new LineChartView.Data(-20, "2019-" + (4 + 1) + "-11");
        LineChartView.Data data4 = new LineChartView.Data(50, "2019-" + (5 + 1) + "-11");
        LineChartView.Data data5 = new LineChartView.Data(-80, "2019-" + (7 + 1) + "-11");
        LineChartView.Data data6 = new LineChartView.Data(100, "2019-" + (11 + 1) + "-11");
        datas.add(data);
        datas.add(data1);
        datas.add(data2);
        datas.add(data3);
        datas.add(data4);
        datas.add(data5);
        datas.add(data6);


        LineChartView.ChartData data11 = new LineChartView.ChartData();
        data11.setColor(Color.parseColor("#0cdfef"));
        data11.setGradientColor(new int[] {Color.parseColor("#1ad4dd"), Color.TRANSPARENT});
        data11.setData(datas);
        lineChartView.setData(data11);

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
