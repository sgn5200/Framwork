package com.cannan.framwork.api;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import com.cannan.framwork.R;


/**
 * Created by Cannan on 2017/8/15 0015.
 *
 * 网络请求加载时显示的缓冲对话框
 *
 */

public class RequestDialog extends Dialog {
    private ImageView iv;//旋转控件
    private RotateAnimation animation;//选择动画

    @LayoutRes int layout = -1;

    public static RequestDialog getInstance(Context context,@LayoutRes int layout){
        return getInstance(context, R.style.fullScreenDialog,layout);
    }

    private static RequestDialog getInstance(Context context, @StyleRes int themeResId,@LayoutRes int layout){
        return new RequestDialog(context, themeResId,layout);
    }

    /**
     *
     * @param context
     * @param themeResId
     * @param layout   -1 为默认布局，否则为布局id
     */
    private RequestDialog(@NonNull Context context, @StyleRes int themeResId,@LayoutRes int layout) {
        super(context, themeResId);
        this.layout = layout;
        if(layout == -1){
            createDialogView();
        } else{
            init();
        }
    }

    private void createDialogView() {
//        LinearLayout frameLayout = new LinearLayout(getContext());
//        frameLayout.setOrientation(LinearLayout.VERTICAL);
//
//        int pading = SimpleUtil.dip2px(50f);
//        frameLayout.setPadding(pading,pading,pading,pading);
        ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.show();
    }

    /**
     * 初始化，给iv设置动画，tv设置文字
     */
    private void init() {
        setCancelable(false);
        setContentView(layout);
        animation = new RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF,
                0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setDuration(1000);//设置动画持续时间
        animation.setRepeatCount(Integer.MAX_VALUE);//设置重复次数
        iv.setAnimation(animation);
        animation.startNow();
    }


}
