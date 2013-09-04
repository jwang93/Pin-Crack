package com.example.pincrack;

import com.example.pincrack.R;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
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
        
        TextView resultView = (TextView) findViewById(R.id.result_number);
        resultView.setText(String.valueOf(counter));
        resultView.setTextColor(getTextColor(counter));
        
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
    
    /**
     * Helper method that returns the color for result text.
     * Color is either: red, green, or yellow (depending on how counter)
     * @param counter
     * @return
     */
    private int getTextColor (int counter) {
        if (counter > 1000) {
            return Color.RED;
        } else if (counter > 100) {
            return Color.YELLOW;
        }
        return Color.GREEN;
    }
    
    /**
     * Helper method to restart the application
     * @param view
     */
    public void startOver (View view) {
        Intent startOver = new Intent(ResultActivity.this, InputActivityOne.class);
        ResultActivity.this.startActivity(startOver);
        finish();
    }
}