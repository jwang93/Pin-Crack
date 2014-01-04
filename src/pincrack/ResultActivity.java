package pincrack;

import java.lang.reflect.Method;

import com.pincrack.R;

import altCntrl.altCntrlActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class ResultActivity extends altCntrlActivity {
    /** Called when the activity is first created. */
    int counter;
    Intent viewLogs;

    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);
        viewLogs = new Intent(ResultActivity.this, ViewLogsActivity.class);
        
        /* Configuration for altCntrl*/
        try {
        	Method[] methods = new Method[4];
        	methods[0] = this.getClass().getMethod("startOver", View.class);
        	methods[1] = this.getClass().getMethod("view25", View.class);
        	methods[2] = this.getClass().getMethod("View50", View.class);
        	methods[3] = this.getClass().getMethod("view100", View.class);
			super.altCntrlSetUp(methods, this, findViewById(R.layout.main));
		} catch (NoSuchMethodException e) {}
        
    }
        
    /**
     * Helper method to restart the application
     * @param view
     */
    public void startOver (View view) {
        Intent startOver = new Intent(ResultActivity.this, InputActivity.class);
        ResultActivity.this.startActivity(startOver);
    }
    
    public void view25 (View view) {
    	viewLogs.putExtra("size", 25);
        ResultActivity.this.startActivity(viewLogs);
    }
    
    public void view50 (View view) {
    	viewLogs.putExtra("size", 50);
        ResultActivity.this.startActivity(viewLogs);
    }
    
    public void view100 (View view) {
    	viewLogs.putExtra("size", 100);
        ResultActivity.this.startActivity(viewLogs);
    }
}