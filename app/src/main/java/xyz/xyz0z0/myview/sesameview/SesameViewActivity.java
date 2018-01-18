package xyz.xyz0z0.myview.sesameview;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import xyz.xyz0z0.myview.R;

public class SesameViewActivity extends AppCompatActivity {

    private final int[] mColors = new int[] {
            0xFFFF80AB,
            0xFFFF4081,
            0xFFFF5177,
            0xFFFF7997
    };
    private LinearLayout mLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sesame_view);
        mLayout = findViewById(R.id.layout);
        SesameCreditView sesameCreditView = findViewById(R.id.sesame_cred_view);
        sesameCreditView.setSesameValues(470);
        startColorChangeAnim();
    }

    public void startColorChangeAnim() {

        ObjectAnimator animator = ObjectAnimator.ofInt(mLayout, "backgroundColor", mColors);
        animator.setDuration(3000);
        animator.setEvaluator(new ArgbEvaluator());
        animator.start();
    }
}
