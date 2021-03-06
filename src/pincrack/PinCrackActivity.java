package pincrack;

import com.pincrack.R;
import dialogs.AboutDialog;
import dialogs.HowItWorksDialog;
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
        Intent inputIntent = new Intent(PinCrackActivity.this, DataActivity.class);
        this.startActivity(inputIntent);
        finish();
    }

   public void startAbout (View view) {
       AboutDialog dialog = new AboutDialog();
       dialog.show(getFragmentManager(), "about Dialog");
   }
   
   public void startHowItWorks (View view) {
       HowItWorksDialog dialog = new HowItWorksDialog();
       dialog.show(getFragmentManager(), "howItWorks Dialog");
   }

}
