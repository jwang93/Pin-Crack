package com.example.pincrack;

import com.example.pincrack.R;
import dialogs.PINValidatorDialog;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class InputActivityOne extends Activity {
    /** Called when the activity is first created. */
       
    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input_one);
        final EditText pin = (EditText) findViewById(R.id.phone_dialer);
       
        Button submit = (Button) findViewById(R.id.button2);
        submit.setOnClickListener(new View.OnClickListener()
        {
            public void onClick (View v)
            {
                if (validationPassed(pin.getText().toString())) {
                    Intent inputIntent = new Intent(InputActivityOne.this, InputActivityTwo.class);
                    inputIntent.putExtra("realPin", pin.getText().toString());
                    InputActivityOne.this.startActivity(inputIntent);
                    finish();
                } else {
                    PINValidatorDialog dialog = new PINValidatorDialog();
                    dialog.show(getFragmentManager(), "error Dialog");
                }
            }
        });
    }
    
    /**
     * Helper method to validate the user input of the PIN
     * @param string
     */
    protected boolean validationPassed (String string) {
        if (string.length() == 4) return true;
        return false;
    }

}
