package com.example.pincrack;

import com.example.pincrack.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class PinCrackActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    public void start (View view) {
        Intent inputIntent = new Intent(PinCrackActivity.this, InputActivityOne.class);
        this.startActivity(inputIntent);
    }

}
