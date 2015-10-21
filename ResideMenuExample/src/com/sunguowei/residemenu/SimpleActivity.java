package com.sunguowei.residemenu;


import android.graphics.Canvas;
import android.os.Bundle;
import android.view.View;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingActivity;

/**
 * 测试只有右侧Menu的情况
 */
public class SimpleActivity extends SlidingActivity implements View.OnClickListener{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSlidingMenu().setMode(SlidingMenu.LEFT_RIGHT);
        setContentView(R.layout.layout_content);
        setBehindContentView(false, R.layout.layout_menu_right);
        SlidingMenu sm = getSlidingMenu();
        sm.setBehindOffset(300, 500);
        sm.setFadeEnabled(false);
        sm.setBehindScrollScale(0.25f);
        sm.setFadeDegree(0.25f);
        sm.setBackgroundImage(R.drawable.img_frame_background);
        sm.setBehindCanvasTransformer(new SlidingMenu.CanvasTransformer() {
            @Override
            public void transformCanvas(Canvas canvas, float percentOpen, float scrollX) {
                float scale = (float) (percentOpen * 0.25 + 0.75);
                if (scrollX < 0) {
                    canvas.scale(scale, scale, -canvas.getWidth() / 2, canvas.getHeight() / 2);
                } else {
                    canvas.scale(scale, scale, canvas.getWidth() * 3 / 2, canvas.getHeight() / 2);
                }
            }
        });

        sm.setAboveCanvasTransformer(new SlidingMenu.CanvasTransformer() {
            @Override
            public void transformCanvas(Canvas canvas, float percentOpen, float scrollX) {
                float scale = (float) (1 - percentOpen * 0.25);
                if (scrollX < 0) {
                    canvas.scale(scale, scale, 0, canvas.getHeight() / 2);
                } else {
                    canvas.scale(scale, scale, canvas.getWidth(), canvas.getHeight() / 2);
                }
            }
        });
        findViewById(R.id.btn_left).setOnClickListener(this);
        findViewById(R.id.btn_right).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_left:
                showLeftMenu();
                break;
            case R.id.btn_right:
                showRightMenu();
                break;
        }
    }
}
