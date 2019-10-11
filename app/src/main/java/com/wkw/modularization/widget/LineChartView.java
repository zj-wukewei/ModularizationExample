package com.wkw.modularization.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.Point;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.wkw.ext.utils.ViewUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
/**
 * Created by GoGo on 2019/10/11.
 * Email zjwkw1992@163.com
 * GitHub https://github.com/zj-wukewei
 */
public class LineChartView extends View {

    private Paint linePaint;//曲线画笔
    private Paint pointPaint;//曲线上锚点画笔
    private Paint tablePaint;//表格画笔
    private Paint textRulerPaint;//标尺文本画笔
    private Paint textPointPaint;//曲线上锚点文本画笔

    private Path linePath;//曲线路径

    private Path linearGradientPath;
    private Path tablePath;//表格路径

    private int mWidth, mHeight;

    private List<Data> dataList = new ArrayList<>();

    private Point[] linePoints;
    private int stepStart;
    private int stepEnd;
    private int stepSpace;
    private int stepSpaceDefault = 10;
    private int stepSpaceDP = stepSpaceDefault;//item宽度默认dp
    private int topSpace, bottomSpace;
    private int tablePadding;
    private int tablePaddingDP = 20;//view四周padding默认dp

    private int maxValue, minValue;
    private int rulerValueDefault = 10;
    private int rulerValue = rulerValueDefault;//刻度单位跨度
    private int rulerValuePadding;//刻度单位与轴的间距
    private int rulerValuePaddingDP = 18;//刻度单位与轴的间距默认dp
    private float heightPercent = 0.618f;

//    private int lineColor = Color.parseColor("#286DD4");//曲线颜色
    private float lineWidthDP = 2f;//曲线宽度dp

//    private int pointColor = Color.parseColor("#FF4081");//锚点颜色
    private float pointWidthDefault = 8f;
    private float pointWidthDP = pointWidthDefault;//锚点宽度dp

    private int tableColor = Color.parseColor("#979fb4");//表格线颜色
    private float tableWidthDP = 0.5f;//表格线宽度dp

    private int rulerTextColor = tableColor;//表格标尺文本颜色
    private float rulerTextSizeSP = 10f;//表格标尺文本大小

//    private int pointTextColor = Color.parseColor("#009688");//锚点文本颜色
    private float pointTextSizeSP = 10f;//锚点文本大小

    private boolean isShowTable = false;
    private boolean isBezierLine = false;
    private boolean isCubePoint = false;
    private boolean isInitialized = false;
    private boolean isPlayAnim = false;

    private ValueAnimator valueAnimator;
    private float currentValue = 0f;
    private boolean isAnimating = false;

    public LineChartView(Context context) {
        this(context, null);
    }

    public LineChartView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LineChartView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        setupView();
    }

    private Paint mInnerPaint;
    private Paint linearGradientPaint;

    private void setupView() {
        linePaint = new Paint();
        linePaint.setAntiAlias(true);//抗锯齿
        linePaint.setStyle(Paint.Style.STROKE);//STROKE描边FILL填充
//        linePaint.setColor(lineColor);
        linePaint.setStrokeWidth(dip2px(lineWidthDP));//边框宽度

        pointPaint = new Paint();
        pointPaint.setAntiAlias(true);
        pointPaint.setStyle(Paint.Style.FILL);
//        pointPaint.setColor(pointColor);
        pointPaint.setStrokeWidth(dip2px(pointWidthDP));

        mInnerPaint = new Paint();
        mInnerPaint.setAntiAlias(true);
        mInnerPaint.setStyle(Paint.Style.FILL);
        mInnerPaint.setColor(Color.WHITE);
        mInnerPaint.setStrokeWidth(dip2px(pointWidthDP));


        linearGradientPaint = new Paint();
        linearGradientPaint.setAntiAlias(true);
        linearGradientPaint.setStyle(Paint.Style.FILL);
        linearGradientPaint.setStrokeWidth(dip2px(pointWidthDP));

        tablePaint = new Paint();
        tablePaint.setAntiAlias(true);
        tablePaint.setStyle(Paint.Style.STROKE);
        tablePaint.setColor(tableColor);
        tablePaint.setStrokeWidth(dip2px(tableWidthDP));

        textRulerPaint = new Paint();
        textRulerPaint.setAntiAlias(true);
        textRulerPaint.setStyle(Paint.Style.FILL);
        textRulerPaint.setTextAlign(Paint.Align.CENTER);
        textRulerPaint.setColor(rulerTextColor);//文本颜色
        textRulerPaint.setTextSize(sp2px(rulerTextSizeSP));//字体大小

        textPointPaint = new Paint();
        textPointPaint.setAntiAlias(true);
        textPointPaint.setStyle(Paint.Style.FILL);
        textPointPaint.setTextAlign(Paint.Align.CENTER);
//        textPointPaint.setColor(pointTextColor);//文本颜色
        textPointPaint.setTextSize(sp2px(pointTextSizeSP));//字体大小

        linePath = new Path();
        linearGradientPath = new Path();
        tablePath = new Path();

        resetParam();
    }

    private void initAnim() {
        valueAnimator = ValueAnimator.ofFloat(0f, 1f).setDuration(dataList.size() * 150);
        valueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                currentValue = (float) animation.getAnimatedValue();
                postInvalidate();
            }
        });
        valueAnimator.addListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                currentValue = 0f;
                isAnimating = true;
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                currentValue = 1f;
                isAnimating = false;
                isPlayAnim = false;
            }
        });
        valueAnimator.setStartDelay(500);
    }

    private void resetParam() {
        linePath.reset();
        tablePath.reset();
        linearGradientPath.reset();
        stepSpace = dip2px(stepSpaceDP);
        tablePadding = dip2px(tablePaddingDP);
        rulerValuePadding = dip2px(rulerValuePaddingDP);
        stepStart = tablePadding * (isShowTable ? 2 : 1);
        stepEnd = stepStart + stepSpace * (dataList.size() - 1);
        topSpace = bottomSpace = tablePadding;
        linePoints = new Point[dataList.size()];

        initAnim();
        isInitialized = false;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = tablePadding + getTableEnd() + getPaddingLeft() + getPaddingRight();//计算自己的宽度
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);//父类期望的高度
        if (MeasureSpec.EXACTLY == heightMode) {
            height = getPaddingTop() + getPaddingBottom() + height;
        }
        setMeasuredDimension(hasData() ? width : ViewUtils.getScreenWidth(), height);//设置自己的宽度和高度
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    private boolean hasData () {
        return mChartData != null && !mChartData.getData().isEmpty();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.TRANSPARENT);//绘制背景颜色
        if (hasData()) {
            canvas.translate(0f, mHeight / 2f + (getViewDrawHeight() + topSpace + bottomSpace) / 2f);//设置画布中心点垂直居中

            if (!isInitialized) {
                setupLine();
            }

            if (isShowTable) {
                drawTable(canvas);//绘制表格
            }
            drawLine(canvas);//绘制曲线
            drawLinePoints(canvas);//绘制曲线上的点
        } else {
            drawRulerXText(canvas, "暂无数据", getWidth() / 2f, getHeight() / 2f, true);
        }

    }

    private void drawText(Canvas canvas, Paint textPaint, String text, float x, float y) {
        canvas.drawText(text, x, y, textPaint);
    }

    /**
     * 绘制标尺y轴文本
     *
     * @param canvas
     * @param text
     * @param x
     * @param y
     */
    private void drawRulerYText(Canvas canvas, String text, float x, float y) {
        textRulerPaint.setTextAlign(Paint.Align.RIGHT);
        Paint.FontMetrics fontMetrics = textRulerPaint.getFontMetrics();
        float fontTotalHeight = fontMetrics.bottom - fontMetrics.top;
        float offsetY = fontTotalHeight / 2 - fontMetrics.bottom;
        float newY = y + offsetY;
        float newX = x - rulerValuePadding;
        drawText(canvas, textRulerPaint, text, newX, newY);
    }

    /**
     * 绘制标尺x轴文本
     *
     * @param canvas
     * @param text
     * @param x
     * @param y
     */
    private void drawRulerXText(Canvas canvas, String text, float x, float y, boolean isYear) {
        if (isYear) {
            textRulerPaint.setColor(tableColor);
        } else {
            textRulerPaint.setColor(Color.parseColor("#677B89"));
        }
        textRulerPaint.setTextAlign(Paint.Align.CENTER);
        Paint.FontMetrics fontMetrics = textRulerPaint.getFontMetrics();
        float fontTotalHeight = fontMetrics.bottom - fontMetrics.top;
        float offsetY = fontTotalHeight / 2 - fontMetrics.bottom;
        float newY = y + offsetY + rulerValuePadding;
        drawText(canvas, textRulerPaint, text, x, newY);
        textPointPaint.setColor(tableColor);
    }

    /**
     * 绘制曲线上锚点文本
     *
     * @param canvas
     * @param text
     * @param x
     * @param y
     */
    private void drawLinePointText(Canvas canvas, String text, float x, float y) {
        textPointPaint.setTextAlign(Paint.Align.CENTER);
        float newY = y - rulerValuePadding;
        drawText(canvas, textPointPaint, text, x, newY);
    }

    private int getTableStart() {
        return isShowTable ? stepStart + tablePadding : stepStart;
    }

    private int getTableEnd() {
        return isShowTable ? stepEnd + tablePadding : stepEnd;
    }

    /**
     * 绘制背景表格
     *
     * @param canvas
     */
    private void drawTable(Canvas canvas) {
        int tableEnd = getTableEnd();
//
//        int rulerCount = maxValue / rulerValue;
//        int rulerMaxCount = maxValue % rulerValue > 0 ? rulerCount + 1 : rulerCount;
//        int rulerMax = rulerValue * rulerMaxCount + rulerValueDefault;
//
//        tablePath.moveTo(stepStart, -getValueHeight(rulerMax));//加上顶部的间隔
//        tablePath.lineTo(stepStart, 0);//标尺y轴
//        tablePath.lineTo(tableEnd, 0);//标尺x轴
//
        int startValue = minValue - (minValue > 0 ? 0 : minValue % rulerValue);
        int endValue = (maxValue + rulerValue);
//
        //标尺y轴连接线
        if (linePoints.length == 1) {
            drawRulerYText(canvas, String.valueOf(startValue), stepStart, linePoints[0].y);
        } else {
            do {
                int startHeight = -getValueHeight(startValue);
                tablePath.moveTo(stepStart, startHeight);
                tablePath.lineTo(tableEnd, startHeight);
                //绘制y轴刻度单位
                drawRulerYText(canvas, String.valueOf(startValue), stepStart, startHeight);
                startValue += rulerValue;
            } while (startValue < endValue);
        }

        tablePaint.setColor(Color.parseColor("#F4F5F6"));
        canvas.drawPath(tablePath, tablePaint);
        tablePaint.setColor(tableColor);
        //绘制x轴刻度单位
        drawRulerXValue(canvas);
    }

    /**
     * 绘制标尺x轴上所有文本
     *
     * @param canvas
     */
    private void drawRulerXValue(Canvas canvas) {
        if (linePoints == null) return;
        for (int i = 0; i < linePoints.length; i++) {
            Point point = linePoints[i];
            if (point == null) break;
            Data data = dataList.get(i);
            String y = data.getDate().split("-")[0];
            String dM = data.getDate().split("-")[1] + "/" +  data.getDate().split("-")[2];
            drawRulerXText(canvas, y, linePoints[i].x, 0, true);
            drawRulerXText(canvas, dM, linePoints[i].x, dip2px(-13), false);
        }
    }

    /**
     * 绘制曲线
     *
     * @param canvas
     */
    private void drawLine(Canvas canvas) {
        if (isPlayAnim) {
            Path dst = new Path();
            PathMeasure measure = new PathMeasure(linePath, false);
            measure.getSegment(0, currentValue * measure.getLength(), dst, true);
            canvas.drawPath(dst, linePaint);
        } else {
            canvas.drawPath(linePath, linePaint);
        }
        mShader = new LinearGradient(0, -getValueHeight(maxValue), 0, -getValueHeight(minValue), mChartData.getGradientColor(), null, Shader.TileMode.CLAMP);

        linearGradientPaint.setShader(mShader);
        canvas.drawPath(linearGradientPath, linearGradientPaint);

    }

    /**
     * 绘制曲线上的锚点
     *
     * @param canvas
     */
    private void drawLinePoints(Canvas canvas) {
        if (linePoints == null) return;

        float pointWidth = dip2px(pointWidthDP) / 2;
        int pointCount = linePoints.length;
        if (isPlayAnim) {
            pointCount = Math.round(currentValue * linePoints.length);
        }
        for (int i = 0; i < pointCount; i++) {
            Point point = linePoints[i];
            if (point == null) break;
            if (isCubePoint) {
                canvas.drawPoint(point.x, point.y, pointPaint);
            } else {
                canvas.drawCircle(point.x, point.y, pointWidth, pointPaint);
                canvas.drawCircle(point.x, point.y, pointWidth / 2, mInnerPaint);
            }
            //绘制点的文本
            drawLinePointText(canvas, String.valueOf(dataList.get(i).getValue()), point.x, point.y);
        }
    }

    /**
     * 获取value值所占的view高度
     *
     * @param value
     * @return
     */
    private int getValueHeight(int value) {
        float valuePercent = Math.abs(value - minValue) * 100f / (Math.abs(maxValue - minValue) * 100f);//计算value所占百分比
        return (int) (getViewDrawHeight() * valuePercent + bottomSpace + 0.5f);//底部加上间隔
    }

    /**
     * 获取绘制区域高度
     *
     * @return
     */
    private float getViewDrawHeight() {
        return getMeasuredHeight() * heightPercent;
    }

    /**
     * 初始化曲线数据
     */
    private void setupLine() {
        if (dataList.isEmpty()) return;

        int stepTemp = getTableStart();
        Point pre = new Point();
        pre.set(stepTemp, -getValueHeight(dataList.get(0).getValue()));//坐标系从0,0默认在第四象限绘制
        linePoints[0] = pre;
        linePath.moveTo(pre.x, pre.y);

        linearGradientPath.moveTo(pre.x, -getValueHeight(minValue));
        linearGradientPath.lineTo(pre.x, pre.y);

        if (dataList.size() == 1) {
            pre.set(stepTemp, -getValueHeight(dataList.get(0).getValue()) - getHeight() / 2);
            isInitialized = true;
            return;
        }

        int lastX = 0;

        for (int i = 1; i < dataList.size(); i++) {
            Data data = dataList.get(i);
            Point next = new Point();
            next.set(stepTemp += stepSpace, -getValueHeight(data.getValue()));

            if (isBezierLine) {
                int cW = pre.x + stepSpace / 2;

                Point p1 = new Point();//控制点1
                p1.set(cW, pre.y);

                Point p2 = new Point();//控制点2
                p2.set(cW, next.y);

                linePath.cubicTo(p1.x, p1.y, p2.x, p2.y, next.x, next.y);//创建三阶贝塞尔曲线
                linearGradientPath.cubicTo(p1.x, p1.y, p2.x, p2.y, next.x, next.y);//创建三阶贝塞尔曲线
            } else {
                linePath.lineTo(next.x, next.y);
                linearGradientPath.lineTo(next.x, next.y);
            }

            pre = next;
            lastX = pre.x;
            linePoints[i] = next;
        }

        linearGradientPath.lineTo(lastX, -getValueHeight(minValue));
        linearGradientPath.lineTo(linePoints[0].x, -getValueHeight(minValue));

        isInitialized = true;
    }

    private int dip2px(float dipValue) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    private int sp2px(float spValue) {
        final float fontScale = getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    private void refreshLayout() {
        resetParam();
        requestLayout();
        postInvalidate();
    }

    /*-------------可操作方法---------------*/

    /**
     * 设置数据
     *
     */
    ChartData mChartData;
    Shader mShader;
    private float mothSize = 1f;

    public void setData(ChartData chartData) {
        mothSize = 1f;
        this.mChartData = chartData;
        if (chartData.getData() == null) {
            throw new RuntimeException("dataList cannot is null!");
        }
        if (chartData.getData().isEmpty()) return;
        this.dataList.clear();
        this.dataList.addAll(chartData.getData());
        linePaint.setColor(chartData.color);
        pointPaint.setColor(chartData.color);
        textPointPaint.setColor(chartData.color);

        maxValue = Collections.max(this.dataList, new Comparator<Data>() {
            @Override
            public int compare(Data o1, Data o2) {
                return o1.getValue() - o2.getValue();
            }
        }).getValue();



        minValue = Collections.min(this.dataList, new Comparator<Data>() {
            @Override
            public int compare(Data o1, Data o2) {
                return o1.getValue() - o2.getValue();
            }
        }).getValue();

        for (int i = 0; i < chartData.getData().size() - 1; i++) {
            Data data = chartData.getData().get(i);
            Data nextData = chartData.getData().get(i + 1);
            int day = differentDays(data.getDate(), nextData.getDate());
            mothSize  = mothSize + day > 30 ? day / 30f : 1f;
        }

        refreshLayout();
    }

    /**
     * 设置是否显示标尺表格
     *
     * @param showTable
     */
    public void setShowTable(boolean showTable) {
        isShowTable = showTable;
        refreshLayout();
    }

    /**
     * 设置是否是贝塞尔曲线
     *
     * @param isBezier
     */
    public void setBezierLine(boolean isBezier) {
        isBezierLine = isBezier;
        refreshLayout();
    }

    /**
     * 设置锚点形状
     *
     * @param isCube
     */
    public void setCubePoint(boolean isCube) {
        isCubePoint = isCube;
        refreshLayout();
    }

    /**
     * 设置标尺y轴间距
     *
     * @param space
     */
    public void setRulerYSpace(int space) {
        if (space <= 0) {
            space = rulerValueDefault;
        }
        this.rulerValue = space;
        refreshLayout();
    }

    /**
     * 设置曲线点的间距，标尺x轴间距
     *
     * @param dp
     */
    public void setStepSpace(int dp) {
        if (dp < stepSpaceDefault) {
            dp = stepSpaceDefault;
        }
        this.stepSpaceDP = dp;
        refreshLayout();
    }

    /**
     * 设置锚点尺寸
     *
     * @param dp
     */
    public void setPointWidth(float dp) {
        if (dp <= 0) {
            dp = pointWidthDefault;
        }
        this.pointWidthDP = dp;
        refreshLayout();
    }

    /**
     * 播放动画
     */
    public void playAnim() {
        this.isPlayAnim = true;
        if (isAnimating) return;
        if (valueAnimator != null) {
            valueAnimator.start();
        }
    }


    private int differentDays(String day1, String day2) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date fDate = sdf.parse(day1);
            Date oDate = sdf.parse(day2);
            int days = (int) ((oDate.getTime() - fDate.getTime()) / (1000*3600*24));
            return days;
        } catch (Exception e) {
            return 0;
        }

    }

    public static class ChartData {
        private int color;
        private int[] gradientColor;
        private List<Data> data;

        public int[] getGradientColor() {
            return gradientColor;
        }

        public void setGradientColor(int[] gradientColor) {
            this.gradientColor = gradientColor;
        }

        public int getColor() {
            return color;
        }

        public void setColor(int color) {
            this.color = color;
        }

        public List<Data> getData() {
            return data;
        }

        public void setData(List<Data> data) {
            this.data = data;
        }
    }

    public static class Data {

        int value;
        String date;


        public Data(int value, String date) {
            this.value = value;
            this.date = date;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public void setValue(int value) {
            this.value = value;
        }


        public Data(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

    }
}

