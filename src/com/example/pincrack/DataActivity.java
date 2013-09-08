package com.example.pincrack;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class DataActivity extends Activity {
	
    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.data);
    }
    
    public void cont (View view) {
        Intent inputIntent = new Intent(DataActivity.this, InputActivity.class);
        this.startActivity(inputIntent);
        finish();
    }
}
