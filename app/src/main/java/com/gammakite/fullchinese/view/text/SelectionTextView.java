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
    private SpannableString spannableString;
    private BackgroundColorSpan backgroundColorSpan;
    private ForegroundColorSpan foregroundColorSpan;
    private Layout layout;
    private boolean isSkipScrolling = false;

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
        String tmp = getText().toString();
    }

    @Override
    public void scrollTo(int x, int y) {
        if (isSkipScrolling) {
            return;
        } else {
            super.scrollTo(x, y);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                isSkipScrolling = false;
                break;
            case MotionEvent.ACTION_UP:
                int scrollY = getScrollY();
                removeSelection();
                layout = getLayout();
                try {
                    line = layout.getLineForVertical(scrollY + (int) event.getY());
                    startIndex = layout.getOffsetForHorizontal(line, (int) event.getX());
                    spannableString.setSpan(backgroundColorSpan, startIndex,
                            startIndex + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    spannableString.setSpan(foregroundColorSpan, startIndex,
                            startIndex + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    setText(spannableString);
                    int y = getScrollY();
                    setScrollY(scrollY);
                    isSkipScrolling = true;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }

        return super.onTouchEvent(event);
    }

    public void removeSelection() {
        startIndex = 0;
        if (spannableString != null && backgroundColorSpan != null) {
            spannableString.removeSpan(backgroundColorSpan);
            spannableString.removeSpan(foregroundColorSpan);
            setText(spannableString);
        }
    }

    public void moveLeft() {
        if (startIndex > 0) {
            startIndex--;
            spannableString.setSpan(backgroundColorSpan, startIndex,
                    startIndex + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            spannableString.setSpan(foregroundColorSpan, startIndex,
                    startIndex + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            setText(spannableString);
        }
    }

    public void moveRight() {
        if (startIndex + 1 < getText().length()) {
            startIndex++;
            spannableString.setSpan(backgroundColorSpan, startIndex,
                    startIndex + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            spannableString.setSpan(foregroundColorSpan, startIndex,
                    startIndex + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            setText(spannableString);
        }
    }
}
