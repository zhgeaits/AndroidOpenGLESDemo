package org.zhgeaits.androidopenglesdemo1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private MyOpenGLESView myview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myview = new MyOpenGLESView(this);
        myview.requestFocus();
        myview.setFocusableInTouchMode(true);
        setContentView(myview);
    }

    @Override
    public void onResume() {
        super.onResume();
        myview.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        myview.onPause();
    }
}
