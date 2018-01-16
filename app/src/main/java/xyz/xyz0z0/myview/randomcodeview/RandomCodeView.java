package xyz.xyz0z0.myview.randomcodeview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.Random;

/**
 * Created by Cheng on 2018/1/16.
 * 随机验证码
 * 来自 https://juejin.im/entry/591969b1da2f60005df66d3e
 */

public class RandomCodeView extends View {

    private static final String TAG = RandomCodeView.class.getSimpleName();

    private int mHeight;
    private int mWidth;
    private int mTextSize;
    private Random mRandom;
    private int mBgColor;
    // 随机码
    private char[] mCodes = new char[4];
    // 随机码颜色
    private int[] mColors = new int[4];
    // 字体的 y 位置
    private float[] mYs = new float[4];
    // 是否已经初始化数据的 flag
    private boolean flag = false;
    private boolean mIsOnClickRefresh = false;
    private Paint mPaint;


    public RandomCodeView(Context context) {
        super(context);
    }

    public RandomCodeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // 先处理尺寸
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);
        if (widthSpecMode == MeasureSpec.AT_MOST && heightMeasureSpec == MeasureSpec.AT_MOST) {
            setMeasuredDimension(200, 100);
        } else if (widthSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(200, heightSpecSize);
        } else if (heightSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(widthSpecSize, 100);
        }

        mHeight = getMeasuredHeight();
        mWidth = getMeasuredWidth();
        mTextSize = (int) ((float) mWidth / 4.5);
        if (mHeight < mTextSize) {
            mTextSize = (int) (0.5 * mHeight);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint.setAntiAlias(true);
        mPaint.setTextSize(mTextSize);
        mPaint.setStrokeWidth(3);
        mPaint.setShader(null);

        Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();

        float textHeight = Math.abs(fontMetrics.ascent) + fontMetrics.descent;
        if (!flag) {
            init(fontMetrics, mHeight);
        }

    }

    /**
     * 初始化各种随机参数
     *
     * @param fontMetrics
     * @param mHeight
     */
    private void init(Paint.FontMetrics fontMetrics, int mHeight) {

        mRandom = new Random(System.currentTimeMillis());
        mBgColor = getBgRandomColor();

        // 获取随机码 Y 位置
        for (int i = 0; i < 4; i++) {
            mYs[i] = getRandomY(fontMetrics, mHeight);
            mCodes[i] = getRandomText();
            mColors[i] = getTextRandomColor();
        }
        if (mIsOnClickRefresh) {

        }
    }

    private int getTextRandomColor() {
    }

    private char getRandomText() {
        int i = mRandom.nextInt(42) + 48;
        while (i > 57 && i < 65) {
            i = mRandom.nextInt(42) + 48;
        }
        char tmp = (char) (i);
        return tmp;
    }

    private float getRandomY(Paint.FontMetrics fontMetrics, int height) {
        int min = (int) (height - Math.abs(fontMetrics.ascent) - fontMetrics.descent);
        return mRandom.nextInt(min) + Math.abs(fontMetrics.ascent);

    }

    // 获取随机背景颜色
    private int getBgRandomColor() {
        int r = mRandom.nextInt(140) + 115;
        int g = mRandom.nextInt(140) + 115;
        int b = mRandom.nextInt(140) + 115;
        return Color.rgb(r, g, b);
    }
}
