package com.sunguowei.residemenu;

import android.graphics.Canvas;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

public class MainActivity extends SlidingFragmentActivity implements View.OnClickListener {

    private Fragment mContent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_main);

        // check if the content frame contains the menu frame
        if (findViewById(R.id.menu_frame) == null) {
            setBehindContentView(R.layout.menu_frame);
            getSlidingMenu().setSlidingEnabled(true);
            getSlidingMenu()
                    .setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        } else {
            // add a dummy view
            View v = new View(this);
            setBehindContentView(v);
            getSlidingMenu().setSlidingEnabled(false);
            getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
        }

        // set the Above View Fragment
        if (savedInstanceState != null) {
            mContent = getSupportFragmentManager().getFragment(savedInstanceState, "mContent");
        }

        if (mContent == null) {
            mContent = new ContentFragment();
        }
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame, mContent).commit();

        // set the Behind View Fragment
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.menu_frame, new MenuFragment()).commit();

        // customize the SlidingMenu
        SlidingMenu sm = getSlidingMenu();
        sm.setMode(SlidingMenu.LEFT_RIGHT);
        sm.setBehindOffset(300, 500);
        sm.setFadeEnabled(false);
        sm.setBehindScrollScale(0.25f);
        sm.setFadeDegree(0.25f);
        sm.setRightMenu(R.layout.layout_menu_right);
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
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        getSupportFragmentManager().putFragment(outState, "mContent", mContent);
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
