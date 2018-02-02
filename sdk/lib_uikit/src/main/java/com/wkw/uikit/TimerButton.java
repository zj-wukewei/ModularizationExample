package com.wkw.uikit;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.widget.Button;

/**
 * Created by GoGo on 2018-2-2.
 * Email zjwkw1992@163.com
 * GitHub https://github.com/zj-wukewei
 */

public class TimerButton extends Button {
    private static final long UNIT = 1000;
    private final Handler mHandler = new Handler(Looper.getMainLooper());
    private final Runnable mTimerRunnable = new Runnable() {
        public void run() {
            if (mDuration <= 0) {
                setText(mNormalText);
                setEnableStatus(true);
                return;
            }
            setText(String.format(mTimerTextFormat, mDuration / UNIT));
            mDuration -= UNIT;
            mHandler.postDelayed(mTimerRunnable, UNIT);
        }
    };
    private long mDuration;
    private String mTimerTextFormat;
    private String mNormalText;
    private Drawable mEnableBackground;
    private Drawable mDisableBackground;

    public TimerButton(Context context) {
        super(context);
    }

    public TimerButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TimerButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public TimerButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    /**
     * 开始倒计时
     *
     * @param timerTextFormat 倒计时text格式
     * @param normalText      倒计时结束后的普通格式
     * @param duration        持续时长(毫秒)
     */
    public void startTimer(String timerTextFormat, String normalText, long duration) {
        startTimer(timerTextFormat, normalText, duration, false);
    }

    /**
     * 开始倒计时
     *
     * @param timerTextFormat 倒计时text格式
     * @param normalText      倒计时结束后的普通格式
     * @param duration        持续时长(毫秒)
     * @param enable          倒计时期间按钮是否可点击
     */
    public void startTimer(String timerTextFormat, String normalText, long duration, boolean enable) {
        mDuration = duration;
        mTimerTextFormat = timerTextFormat;
        mNormalText = normalText;
        setEnableStatus(enable);
        mHandler.post(mTimerRunnable);
    }

    public void setDisableBackground(Drawable disableBackground) {
        mEnableBackground = getBackground();
        mDisableBackground = disableBackground;
    }

    public void setEnableStatus(boolean enable) {
        setEnabled(enable);
        if (enable) {
            setBackgroundDrawable(mEnableBackground);
        } else {
            setBackgroundDrawable(mDisableBackground);
        }
    }
}
