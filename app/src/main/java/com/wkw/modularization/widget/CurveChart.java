package com.wkw.modularization.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.wkw.ext.utils.ViewUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GoGo on 2019/10/11.
 * Email zjwkw1992@163.com
 * GitHub https://github.com/zj-wukewei
 */
public class CurveChart extends View {
    private static int DEFAULT_SIZE = ViewUtils.dpToPx(45);
    private static int DEFAULT_RADIUS_SIZE = ViewUtils.dpToPx(4);
    private static int DEFAULT_INNET_RADIUS_SIZE = ViewUtils.dpToPx(2);

    private Paint mTextPaint = new Paint();
    private Paint mPaint = new Paint();

    private CurveData mCurveData;
    public CurveChart(Context context) {
        super(context);
        init();
    }

    public CurveChart(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CurveChart(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();

    }
    private void init() {
        mTextPaint.setColor(Color.parseColor("#979fb4"));
        mTextPaint.setTextSize(ViewUtils.spToPx(9));
        mTextPaint.getTextBounds("2019", 0, 4, mTextRectF);
        mTextPaint.setAntiAlias(true);
        mPaint.setAntiAlias(true);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        if (hasData()) {
            //右边paddig
            int curveWidth = (mCurveData.getData().size()) * DEFAULT_SIZE;
            int width =  Math.min(curveWidth, ViewUtils.getScreenWidth() - DEFAULT_SIZE);
            setMeasuredDimension(width, height);
        } else {
            //还没有出来暂无数据
            setMeasuredDimension(ViewUtils.getScreenWidth() - DEFAULT_SIZE, height);
        }
    }
    private Rect mTextRectF = new Rect();
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int height = (getHeight() - DEFAULT_RADIUS_SIZE * 2 - mTextRectF.height() * 2 - DEFAULT_INNET_RADIUS_SIZE -  DEFAULT_RADIUS_SIZE * 2 );
        float eachHeight = height / (maxValues - minValues);
        Log.d("eachHeight111", eachHeight+"");
        if (hasData()) {
            for (int i = 0; i < mCurveData.getData().size(); i++) {
                Values values = mCurveData.getData().get(i);
                String dataY = values.getDate().split("-")[0];
                String dataD = values.getDate().split("-")[1] + "/" + values.getDate().split("-")[2];

                float x = DEFAULT_SIZE * i + mTextRectF.width() / 2f;
                float y = (maxValues - values.getValus()) * eachHeight + DEFAULT_RADIUS_SIZE;
                values.setX(x);
                values.setY(y);
                mPaint.setColor(mCurveData.getColor());
                canvas.drawCircle(x, y, DEFAULT_RADIUS_SIZE, mPaint);

                mPaint.setColor(Color.parseColor("#ffffff"));
                canvas.drawCircle(x, y, DEFAULT_INNET_RADIUS_SIZE, mPaint);
                //话年
                mTextPaint.setColor(Color.parseColor("#979fb4"));
                canvas.drawText(dataY, DEFAULT_SIZE * i, getHeight(), mTextPaint);

                mTextPaint.setColor(Color.parseColor("#6c7b8a"));
                canvas.drawText(dataD, DEFAULT_SIZE * i, getHeight() - mTextRectF.height() - DEFAULT_INNET_RADIUS_SIZE, mTextPaint);
            }

            drawCurve();
        } else {

        }
    }

    private void  drawCurve() {

    }

    private Path mPath = new Path();



    public CurveData getCurveData() {
        return mCurveData;
    }
    private float maxValues;
    private float minValues;

    public void setCurveData(CurveData mCurveData) {
        this.mCurveData = mCurveData;
        if (hasData()) {
            this.maxValues = mCurveData.getData().get(0).getValus();
            this.minValues = mCurveData.getData().get(0).getValus();
            for (int i = 0; i < mCurveData.getData().size(); i++) {
                Values values = mCurveData.getData().get(i);
                if (values.getValus() > maxValues) {
                    maxValues = values.getValus();
                }
                if (values.getValus() < minValues) {
                    minValues = values.getValus();
                }
            }
        }
        invalidate();
    }

    private boolean hasData() {
        return mCurveData != null && mCurveData.getData() != null && mCurveData.getData().size() > 0;
    }



    public static class CurveData {
        private int color = Color.parseColor("#0cdfef");
        private String name;
        private String company;
        private List<Values> data;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }

        public List<Values> getData() {
            return data;
        }

        public void setData(List<Values> data) {
            this.data = data;
        }

        public int getColor() {
            return color;
        }

        public void setColor(int color) {
            this.color = color;
        }
    }

    public static class Values {
        private String date;
        private float values;
        private float x;
        private float y;

        public float getX() {
            return x;
        }

        public void setX(float x) {
            this.x = x;
        }

        public float getY() {
            return y;
        }

        public void setY(float y) {
            this.y = y;
        }

        public Values(String date, float values) {
            this.date = date;
            this.values = values;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public float getValus() {
            return values;
        }

        public void setValus(float valus) {
            this.values = valus;
        }
    }
}
