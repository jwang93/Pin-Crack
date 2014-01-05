package pincrack;

import java.lang.reflect.Method;

import com.pincrack.R;

import altCntrl.altCntrlActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

/**
 * This activity is used to display the data gathered by Data Genetics - 
 * the top 20 most used PINs and their frequencies. 
 * 
 * @author Jay Wang
 */
public class DataActivity extends altCntrlActivity {

    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.data);

        /* Configuration for altCntrl*/
        try {
        	Method[] methods = new Method[4];
			methods[0] = this.getClass().getMethod("cont", View.class);
			methods[1] = this.getClass().getMethod("finish", View.class);
			super.altCntrlSetUp(methods, this, findViewById(R.layout.data));
		} catch (NoSuchMethodException e) {}
    }
    
    public void finish(View view) {
    	finish();
    }
    
    
    public void cont (View view) {
        Intent inputIntent = new Intent(DataActivity.this, InputActivity.class);
        this.startActivity(inputIntent);
    }
}
