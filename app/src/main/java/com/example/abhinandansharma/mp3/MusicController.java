package com.example.abhinandansharma.mp3;

import android.content.Context;
import android.view.MotionEvent;
import android.widget.MediaController;


/**
 * Created by abhinandan.sharma on 10/5/2015.
 */
public class MusicController extends MediaController {
    private MediaController mediaController;

    /**
     * Create a new MediaController from a session's token.
     *  @param context The caller's context.
     *
     */
    public MusicController(Context c) {
        super(c);
        mediaController = new MediaController(c);
    }

    void showController () {
        mediaController.show();
    }

    @Override
    public void hide(){}
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //the MediaController will hide after 3 seconds - tap the screen to make it appear again

        mediaController.show();
        return false;
    }


}
