package com.nthily.note.Utilities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.transition.Transition;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.OvershootInterpolator;
import android.view.animation.Transformation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.DrawableRes;
import androidx.annotation.IdRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.interpolator.view.animation.FastOutLinearInInterpolator;

import com.nthily.note.R;

public class Utils extends AppCompatActivity {

    private static AlphaAnimation mHideAnimation = null;
    private static AlphaAnimation mShowAnimation = null;


    public static void sendToastMsg(String text, Context context) {
        @SuppressLint("ShowToast") Toast toast = Toast.makeText(context, text, Toast.LENGTH_LONG);
        toast.setText(text);
        toast.show();
    }

    public static void setHideAnimation(View view, int duration) {
        if (null == view || duration < 0) {
            return;
        }
        if (null != mHideAnimation) {
            mHideAnimation.cancel();
        }
        mHideAnimation = new AlphaAnimation(1.0f, 0.0f);
        mHideAnimation.setDuration(duration);
        mHideAnimation.setFillAfter(true);
        view.startAnimation(mHideAnimation);
    }

    public static void setShowAnimation(View view, int duration ){
        if( null == view || duration < 0 ){
            return;
        }
        mShowAnimation = new AlphaAnimation(0.0f, 1.0f);
        mShowAnimation.setDuration(duration);
        mShowAnimation.setFillAfter(true);
        view.startAnimation(mShowAnimation);
    }
/*
    public static void moveTotop(final View view, final float p1, final float p2) {
        TranslateAnimation animation = new TranslateAnimation(
                Animation.ABSOLUTE, 0f,
                Animation.ABSOLUTE, 0f,
                Animation.ABSOLUTE, p1,
                Animation.ABSOLUTE, p2);

        animation.setInterpolator(new OvershootInterpolator());
        animation.setDuration(600);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                int left = view.getLeft();
                int top = view.getTop()+(int)(p2-p1);
                int width = view.getWidth();
                int height = view.getHeight();

                view.clearAnimation();
                view.layout(left, top, left+width, top+height);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        view.setVisibility(View.VISIBLE);
        view.animate().alpha(1f)
                .setDuration(600)
                .setListener(null);

        view.startAnimation(animation);
    }

    public static void moveTobut(final View view, final float p1, final float p2) {
        TranslateAnimation animation = new TranslateAnimation(
                Animation.ABSOLUTE, 0f,
                Animation.ABSOLUTE, 0f,
                Animation.ABSOLUTE, p1,
                Animation.ABSOLUTE, p2);

        animation.setDuration(600);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                int left = view.getLeft();
                int top = view.getTop()+(int)(p2-p1);
                int width = view.getWidth();
                int height = view.getHeight();

                view.clearAnimation();
                view.layout(left, top, left+width, top+height);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        view.setVisibility(View.GONE);
        view.animate()
                .alpha(0f)
                .setDuration(600)
                .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        view.setVisibility(View.GONE);
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });
        view.startAnimation(animation);
    }



    public static class MyRunnable implements Runnable{

        Handler myHandler = new MyHandler();

        @Override
        public void run() {
            try {
                URL url = new URL("https://api.uixsj.cn/hitokoto/get?type=hitokoto&code=json");
                HttpURLConnection con = null;
                con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                int status = con.getResponseCode();
                if(status == 200) {
                    Message msg = myHandler.obtainMessage();
                    msg.what = SUCCESSFUL_GET_MSG;
                    myHandler.sendMessage(msg);

                } else {
                    Message msg = myHandler.obtainMessage();
                    msg.what = FAILED_GET_MSG;
                    myHandler.sendMessage(msg);
                }
            } catch (IOException e) {
                e.printStackTrace();
                myHandler.sendEmptyMessage(FAILED_GET_MSG);
            }
        }
    }

    public static class MyHandler extends android.os.Handler {

        private WeakReference<Context> mContext;

        public void SomeHandler (Context context) {
            mContext = new WeakReference<Context>(context);
        }

        @Override
        public void dispatchMessage(Message msg) {
            SomeHandler(MainActivity.this);
            Context context = mContext.get();

            if (msg.what == SUCCESSFUL_GET_MSG) {
                sendToastMsg("获取成功", context);
            }
        }
    }*/

    public static void setFavoriteIcon(ImageButton imageButton, @DrawableRes int resId) {
        imageButton.setImageResource(resId);
    }

    public static int getViewMeasuredHeight(View view) {
        int matchParentMeasureSpec = View.MeasureSpec.makeMeasureSpec(((View) view.getParent()).getWidth(), View.MeasureSpec.EXACTLY);
        int wrapContentMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(matchParentMeasureSpec, wrapContentMeasureSpec);
        return view.getMeasuredHeight();
    }

    public static void collapse(final View view) {
        final int initialHeight = view.getMeasuredHeight();
        System.out.println(initialHeight);
        Animation animation = new Animation()
        {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if(interpolatedTime == 1){
                    view.setVisibility(View.GONE);
                }else{
                    view.getLayoutParams().height = initialHeight - (int)(initialHeight * interpolatedTime);
                    view.requestLayout();
                }
            }
            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };
        animation.setDuration(850);
        view.startAnimation(animation);
    }
}
