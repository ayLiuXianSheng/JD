package com.example.lenovo.jd.view.banner;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.example.lenovo.jd.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义banner轮播
 */

public class AutoBanner extends FrameLayout implements ViewPager.OnPageChangeListener {
    private static final String TAG = "AutoBanner";

    private Context mContext;
    private int mIndicatorWidth, mIndicatorHeight, mIndicatorMargin;
    private List<String> mImgUrls;
    private List<View> mImageViews;
    private List<ImageView> mIndicatorViews;
    private LinearLayout mIndicatorLayout;
    private ViewPager mViewPager;
    private AutoBannerListener mAutoBannerListener;
    private AutoBannerPagerAdapter mAutoBannerPagerAdapter;
    private int count;
    private float ratio = 1.78f;//图片宽高比


    //自动轮播
    private boolean isAuto;
    private Handler mAutoHandler = new Handler();
    private int currentItem;
    private int delayTime = 1000;


    public AutoBanner(Context context) {
        this(context, null);
    }

    public AutoBanner(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AutoBanner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        this.mContext = context;
        mImageViews = new ArrayList<>();
        mImgUrls = new ArrayList<>();
        mIndicatorViews = new ArrayList<>();
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.AutoBanner);
        mIndicatorWidth = typedArray.getDimensionPixelSize(R.styleable.AutoBanner_indicator_width, 30);
        mIndicatorHeight = typedArray.getDimensionPixelSize(R.styleable.AutoBanner_indicator_height, 30);
        mIndicatorMargin = typedArray.getDimensionPixelSize(R.styleable.AutoBanner_indicator_margin, 10);

        ratio = typedArray.getFloat(R.styleable.AutoBanner_ratio_wh, 1.78f);

        typedArray.recycle();

        View view = LayoutInflater.from(context).inflate(R.layout.auto_banner_layout, this, true);
        mViewPager = (ViewPager) view.findViewById(R.id.banner_viewpager);
        mIndicatorLayout = (LinearLayout) view.findViewById(R.id.circleIndicator);


    }

    /**
     * 设置图片源
     *
     * @param imgs
     * @return
     */
    public AutoBanner load(List<String> imgs) {
        this.mImgUrls = imgs;
        this.count = mImgUrls.size();
        return this;
    }


    public AutoBanner setAuto(int time) {
        this.delayTime = time;
        this.isAuto = true;
        return this;
    }

    /**
     * 加载和显示banner
     *
     * @return
     */
    public AutoBanner display() {
        setImages();
        setIndicators();
        return this;
    }


    public AutoBanner setAnimation() {

        return this;

    }


    private void setImages() {


        if (count == 0) {
            return;
        }

        for (int i = 0; i < count; i++) {
            ImageView imageView = new ImageView(mContext);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            mImageViews.add(imageView);
        }

        if (mAutoBannerPagerAdapter == null) {
            mAutoBannerPagerAdapter = new AutoBannerPagerAdapter();
            mViewPager.addOnPageChangeListener(this);
        }

        mViewPager.setAdapter(mAutoBannerPagerAdapter);
        mViewPager.setCurrentItem(0);

        if (isAuto) {
            startAuto();
        }


    }

    /**
     * 自动轮播
     */
    public void startAuto() {
        mAutoHandler.removeCallbacks(task);
        mAutoHandler.postDelayed(task, delayTime);
    }

    public void stopAuto() {
        mAutoHandler.removeCallbacks(task);
    }

    private final Runnable task = new Runnable() {
        @Override
        public void run() {
            if (count > 1 && isAuto) {

                if (currentItem >= count) {
                    currentItem = 0;
                }
                mViewPager.setCurrentItem(currentItem);
                mAutoHandler.postDelayed(task, delayTime);
                Log.i(TAG, "curr:" + currentItem + " count:" + count);
            }
        }
    };


    /**
     * 计算viewpager的宽高比，以便适配
     */
    private void initViewPagerParams() {
        ViewGroup.LayoutParams params = mViewPager.getLayoutParams();
        params.width = getMeasuredWidth();
        params.height = (int) (params.width / ratio);
        mViewPager.setLayoutParams(params);

    }

    private void setIndicators() {

        mIndicatorViews.clear();
        mIndicatorLayout.removeAllViews();
        if (count < 2) {//一个view时不绘制指示器
            return;
        }
        for (int i = 0; i < count; i++) {

            ImageView imageView = new ImageView(mContext);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(mIndicatorWidth, mIndicatorHeight);
            layoutParams.leftMargin = mIndicatorMargin;
            layoutParams.rightMargin = mIndicatorMargin;
            if (i == 0) {
                imageView.setImageResource(R.drawable.indicator_selected);
            } else {
                imageView.setImageResource(R.drawable.indicator_unselect);
            }

            mIndicatorViews.add(imageView);

            mIndicatorLayout.addView(imageView, layoutParams);


        }


    }

    public AutoBanner setAutoBannerAnimation(Class<? extends ViewPager.PageTransformer> transformer) {
        try {
            setPageTransformer(true, transformer.newInstance());
        } catch (Exception e) {
            Log.e(TAG, "Please set the PageTransformer class");
        }
        return this;
    }

    /**
     * Set a {@link ViewPager.PageTransformer} that will be called for each attached page whenever
     * the scroll position is changed. This allows the application to apply custom property
     * transformations to each page, overriding the default sliding look and feel.
     *
     * @param reverseDrawingOrder true if the supplied PageTransformer requires page views
     *                            to be drawn from last to first instead of first to last.
     * @param transformer         PageTransformer that will modify each page's animation properties
     * @return Banner
     */
    public AutoBanner setPageTransformer(boolean reverseDrawingOrder, ViewPager.PageTransformer transformer) {
        mViewPager.setPageTransformer(reverseDrawingOrder, transformer);
        return this;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        currentItem = position % count + 1;

    }

    @Override
    public void onPageSelected(int position) {

        for (int i = 0; i < count; i++) {

            if (i == position) {
                mIndicatorViews.get(i).setImageResource(R.drawable.indicator_selected);
            } else {
                mIndicatorViews.get(i).setImageResource(R.drawable.indicator_unselect);
            }

        }

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    class AutoBannerPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return mImageViews.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {

            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            container.addView(mImageViews.get(position));
            ImageView view = (ImageView) mImageViews.get(position);
            Glide.with(mContext).load(mImgUrls.get(position)).into(view);
            if (mAutoBannerListener != null) {
                view.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mAutoBannerListener.onClickListener(position);
                    }
                });
            }
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }


    public AutoBanner setOnAutoBannerListener(AutoBannerListener listener) {

        this.mAutoBannerListener = listener;

        return this;

    }


    public interface AutoBannerListener {
        void onClickListener(int postion);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        initViewPagerParams();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (isAuto) {
            int action = ev.getAction();
            if (action == MotionEvent.ACTION_UP || action == MotionEvent.ACTION_CANCEL
                    || action == MotionEvent.ACTION_OUTSIDE) {
                startAuto();
            } else if (action == MotionEvent.ACTION_DOWN) {
                stopAuto();
            }
        }
        return super.dispatchTouchEvent(ev);
    }
}