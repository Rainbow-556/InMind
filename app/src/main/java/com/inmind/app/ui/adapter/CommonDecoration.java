package com.inmind.app.ui.adapter;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.inmind.app.R;
import com.inmind.app.common.CommonUtil;

/**
 * Created by lixiang on 2017/9/5.
 */
public final class CommonDecoration extends RecyclerView.ItemDecoration{
    private int mDividerHeight = (int) CommonUtil.dp2px(1);
    private int mLeftMargin = (int) CommonUtil.dp2px(25);
    private int mRightMargin = (int) CommonUtil.dp2px(25);
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public CommonDecoration(){
        mPaint.setColor(CommonUtil.getColor(R.color.common_gray_line));
        mPaint.setStrokeWidth(mDividerHeight);
        mPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    public void onDraw(Canvas canvas, RecyclerView parent, RecyclerView.State state){
        final int childCount = parent.getChildCount();
        int parentWidth = parent.getWidth();
        for(int i = 0; i < childCount; i++){
            final View child = parent.getChildAt(i);
            float top = child.getBottom() + mDividerHeight / 2;
            canvas.drawLine(mLeftMargin, top, parentWidth - mRightMargin, top, mPaint);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state){
        int adapterPosition = parent.getChildAdapterPosition(view);
        if(adapterPosition < parent.getAdapter().getItemCount() - 1){
            outRect.set(0, 0, 0, mDividerHeight);
        }else{
            outRect.set(0, 0, 0, 0);
        }
    }
}
