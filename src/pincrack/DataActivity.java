package pincrack;

import com.pincrack.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * This activity is used to display the data gathered by Data Genetics - 
 * the top 20 most used PINs and their frequencies. 
 * 
 * @author Jay Wang
 */
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
