package com.gammakite.fullchinese.view.text;

import android.content.Context;
import android.text.Layout;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.gammakite.fullchinese.R;

/**
 * Created by stone on 17-12-15.
 */

public class SelectionTextView extends android.support.v7.widget.AppCompatTextView {

    private int startIndex = 0;
    private int line = 0;
    private int endIndex = 0;
    private boolean isOnScroll = false;
    private SpannableString spannableString;
    private BackgroundColorSpan backgroundColorSpan;
    private ForegroundColorSpan foregroundColorSpan;
    private Layout layout;

    public SelectionTextView(Context context) {
        this(context, null);
    }

    public SelectionTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SelectionTextView(Context context, AttributeSet attrs,
                             int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        backgroundColorSpan = new BackgroundColorSpan(getResources().getColor(R.color.LightPink));
        foregroundColorSpan = new ForegroundColorSpan(getResources().getColor(R.color.colorPrimary));
    }

    @Override
    protected void onTextChanged(CharSequence text, int start,
                                 int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
        spannableString = new SpannableString(getText());
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        layout = getLayout();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                removeSelection();
                try {
                    // 0 = getScrollY()
                    line = layout.getLineForVertical(0 + (int) event.getY());
                    startIndex = layout.getOffsetForHorizontal(line, (int) event.getX());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                isOnScroll = true;
                break;
            case MotionEvent.ACTION_MOVE:
                spannableString.removeSpan(backgroundColorSpan);
                if (isOnScroll) {
                    /*
                    if (event.getY() > scrollView.getScrollY()
                            + scrollView.getHeight()) {
                        scrollView.smoothScrollTo(0, scrollView.getScrollY()
                                + scrollSpeed); //  0 =scrollView.getScrollX()
                    } else if (event.getY() - scrollView.getScrollY() < 0) {
                        scrollView.smoothScrollTo(0, scrollView.getScrollY()
                                - scrollSpeed); //  0 =scrollView.getScrollX()
                    } else {
                    }
                    */
                }
                try {
                    // 0 = getScrollY()
                    line = layout.getLineForVertical(0 + (int) event.getY());
                    endIndex = layout.getOffsetForHorizontal(line, (int) event.getX());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (startIndex < endIndex) {
                    spannableString.setSpan(backgroundColorSpan, startIndex,
                            endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    spannableString.setSpan(foregroundColorSpan, startIndex,
                            endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                } else {
                    spannableString.setSpan(backgroundColorSpan, endIndex,
                            startIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    spannableString.setSpan(foregroundColorSpan, endIndex,
                            startIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                }
                setText(spannableString);
                break;
            case MotionEvent.ACTION_UP:
                if (getSelectedText() != null) {
                }
                isOnScroll = false;
                break;
            case MotionEvent.ACTION_CANCEL:
                removeSelection();
                break;
            default:
                break;
        }
        return true;
    }

    public void removeSelection() {
        startIndex = 0;
        endIndex = 0;
        if (spannableString != null && backgroundColorSpan != null) {
            spannableString.removeSpan(backgroundColorSpan);
            spannableString.removeSpan(foregroundColorSpan);
            setText(spannableString);
        }
    }

    public CharSequence getSelectedText() {
        if (startIndex == endIndex)
            return null;
        if (startIndex < endIndex)
            return getText().subSequence(startIndex, endIndex);
        return getText().subSequence(endIndex, startIndex);
    }
}
