/*
 * This file is in PUBLIC DOMAIN. You can use it freely. No guarantee.
 */
package org.fanhongtao.page;

import org.fanhongtao.common.BaseActivity;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.ViewFlipper;

/** 
 * Test of ViewFlipper with animation.
 * @author Fan Hongtao &ltfanhongtao@gmail.com&gt
 */
public class Flip2 extends BaseActivity {
    private ViewFlipper flipper;
    private GestureDetector detector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        TAG = "Flip2";
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flipper);

        detector = new GestureDetector(new OnGestureListener2());
        flipper = (ViewFlipper) this.findViewById(R.id.viewFlipper1);
        flipper.addView(addTextView("Page 1", R.drawable.color_blue));
        flipper.addView(addTextView("Page 2", R.drawable.color_green));
        flipper.addView(addTextView("Page 3", R.drawable.color_red));
        flipper.addView(addTextView("Page 4", R.drawable.color_white));
        flipper.addView(addTextView("Page 5", R.drawable.color_yellow));
    }

    private View addTextView(String text, int id) {
        TextView tv = new TextView(this);
        tv.setText(text);
        tv.setGravity(1);
        tv.setBackgroundResource(id);
        return tv;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return detector.onTouchEvent(event);
    }

    class OnGestureListener2 extends SimpleOnGestureListener {

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            if (e1.getX() - e2.getX() > 100) {
                flipper.setInAnimation(AnimationUtils.loadAnimation(Flip2.this, R.anim.left_in));
                flipper.setOutAnimation(AnimationUtils.loadAnimation(Flip2.this, R.anim.left_out)); 
                flipper.showNext();
                return true;
            } else if (e1.getX() - e2.getX() < -100) {
                flipper.setInAnimation(AnimationUtils.loadAnimation(Flip2.this, R.anim.right_in));
                flipper.setOutAnimation(AnimationUtils.loadAnimation(Flip2.this, R.anim.right_out));
                flipper.showPrevious();
                return true;
            }
            return false;
        }
    }
}
