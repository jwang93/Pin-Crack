package com.example.pincrack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import computation.Computer;
import computation.Digit;

import com.example.pincrack.R;
import dialogs.PINValidatorDialog;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;


public class InputActivityTwo extends Activity {
    /** Called when the activity is first created. */

    String guessedPin;
    String truePin;
    BufferedReader reader;

    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input_two);

        final int[] confidence_array = new int[4];
        
        final EditText pin1 = (EditText) findViewById(R.id.phone_dialer1);
        final EditText pin2 = (EditText) findViewById(R.id.phone_dialer2);
        final EditText pin3 = (EditText) findViewById(R.id.phone_dialer3);
        final EditText pin4 = (EditText) findViewById(R.id.phone_dialer4);

        SeekBar confidence1 = (SeekBar) findViewById(R.id.seek1);
        SeekBar confidence2 = (SeekBar) findViewById(R.id.seek2);
        SeekBar confidence3 = (SeekBar) findViewById(R.id.seek3);
        SeekBar confidence4 = (SeekBar) findViewById(R.id.seek4);

        final TextView confidence_text1 = (TextView) findViewById(R.id.confidence_text1);
        final TextView confidence_text2 = (TextView) findViewById(R.id.confidence_text2);
        final TextView confidence_text3 = (TextView) findViewById(R.id.confidence_text3);
        final TextView confidence_text4 = (TextView) findViewById(R.id.confidence_text4);

        
        final Digit digit1 = new Digit((EditText) findViewById(R.id.phone_dialer1), (SeekBar) findViewById(R.id.seek1), (TextView) findViewById(R.id.confidence_text1));
        final Digit digit2 = new Digit(pin2, confidence2, confidence_text2);
        final Digit digit3 = new Digit(pin3, confidence3, confidence_text3);
        final Digit digit4 = new Digit(pin4, confidence4, confidence_text4);
        
        List<Digit> digits = Arrays.asList(digit1, digit2, digit3, digit4);
        
        
        
        
        for (final Digit digit : digits) {
        	digit.getTextView().setText("Confidence: 0");
            digit.getSeekBar().setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            	 
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){
                    digit.getTextView().setText("Confidence: " + progress);
                    confidence_array[0] = progress;
                }

    			@Override
    			public void onStartTrackingTouch(SeekBar arg0) {}

    			@Override
    			public void onStopTrackingTouch(SeekBar seekBar) {}
            });
        }
        
		
        Bundle intentInfo = getIntent().getExtras();
        truePin = intentInfo.getString("realPin");

        Button submit = (Button) findViewById(R.id.button3);
        submit.setOnClickListener(new View.OnClickListener()
        {
            public void onClick (View v)
            {
                InputStream inputStream;
                try {
            		final String pin = digit1.getEditText().getText().toString() + digit2.getEditText().getText().toString()
            				+ digit3.getEditText().getText().toString() + digit4.getEditText().getText().toString();    
                    if (validationPassed(pin)) {
                        setPin(pin);
                        inputStream = getAssets().open("orderings.txt");
                        reader = new BufferedReader(new InputStreamReader(inputStream));
                        int result = Computer.calculate(Integer.parseInt(truePin),
                                                 Integer.parseInt(guessedPin), reader,
                                                 getApplicationContext(),
                                                 confidence_array);

                        Intent resultIntent =
                                new Intent(InputActivityTwo.this, ResultActivity.class);
                        resultIntent.putExtra("counter", result);
                        InputActivityTwo.this.startActivity(resultIntent);
                        finish();
                    }
                    else {
                        PINValidatorDialog dialog = new PINValidatorDialog();
                        dialog.show(getFragmentManager(), "error Dialog");
                    }
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    protected boolean validationPassed (String string) {
        if (string.length() == 4) return true;
        return false;
    }

    private void setPin (String pin) {
        guessedPin = pin;
    }
}
