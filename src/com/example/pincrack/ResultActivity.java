package com.example.pincrack;

import com.example.pincrack.R;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class ResultActivity extends Activity {
    /** Called when the activity is first created. */
    int counter;
    @Override
    public void onCreate (Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);
        
        Bundle intentInfo = getIntent().getExtras();
        counter = intentInfo.getInt("counter");
        
        TextView resultView = (TextView) findViewById(R.id.result_text);
        resultView.setText(String.valueOf(counter));
        
        Button submit = (Button) findViewById(R.id.button4);
        submit.setOnClickListener(new View.OnClickListener()
        {
            public void onClick (View v)
            {
                Intent viewLogs = new Intent(ResultActivity.this, ViewLogsActivity.class);
                ResultActivity.this.startActivity(viewLogs);
            }
        });
    }
}