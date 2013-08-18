package com.example.pincrack;

import com.example.pincrack.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class PinCrackActivity extends Activity {
    /** Called when the activity is first created. */
    private static final int DIALOG_ALERT = 10;

    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    public void start (View view) {
        Intent inputIntent = new Intent(PinCrackActivity.this, InputActivityOne.class);
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
