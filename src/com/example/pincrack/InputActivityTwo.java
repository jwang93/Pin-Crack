package com.example.pincrack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import computation.Computer;
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

        SeekBar confidence = (SeekBar) findViewById(R.id.seek1);
        final TextView confidence_text = (TextView) findViewById(R.id.confidence_text1);
        confidence_text.setText("Confidence: 0");
                
        confidence.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
 
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){
                confidence_text.setText("Confidence: " + progress);
                confidence_array[0] = progress;
            }

			@Override
			public void onStartTrackingTouch(SeekBar arg0) {				
			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {				
			}
        });
        
        final EditText pin2 = (EditText) findViewById(R.id.phone_dialer2);

        SeekBar confidence2 = (SeekBar) findViewById(R.id.seek2);
        final TextView confidence_text2 = (TextView) findViewById(R.id.confidence_text2);
        confidence_text2.setText("Confidence: 0");
        
        confidence2.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
 
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){
                confidence_text2.setText("Confidence: " + progress);
                confidence_array[1] = progress;
            }

			@Override
			public void onStartTrackingTouch(SeekBar arg0) {				
			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {				
			}
        });

        final EditText pin3 = (EditText) findViewById(R.id.phone_dialer3);

        SeekBar confidence3 = (SeekBar) findViewById(R.id.seek3);
        final TextView confidence_text3 = (TextView) findViewById(R.id.confidence_text3);
        confidence_text3.setText("Confidence: 0");
        
        confidence3.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
 
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){
                confidence_text3.setText("Confidence: " + progress);
                confidence_array[2] = progress;
            }

			@Override
			public void onStartTrackingTouch(SeekBar arg0) {				
			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {				
			}
        });
        
        final EditText pin4 = (EditText) findViewById(R.id.phone_dialer4);

        SeekBar confidence4 = (SeekBar) findViewById(R.id.seek4);
        final TextView confidence_text4 = (TextView) findViewById(R.id.confidence_text4);
        confidence_text4.setText("Confidence: 0");
        
        confidence4.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
 
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){
                confidence_text4.setText("Confidence: " + progress);
                confidence_array[3] = progress;
            }

			@Override
			public void onStartTrackingTouch(SeekBar arg0) {				
			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {				
			}
        });
        
        
        
        
    
        
		
        Bundle intentInfo = getIntent().getExtras();
        truePin = intentInfo.getString("realPin");

        Button submit = (Button) findViewById(R.id.button3);
        submit.setOnClickListener(new View.OnClickListener()
        {
            public void onClick (View v)
            {
                InputStream inputStream;
                try {
            		final String pin = pin1.getText().toString() + pin2.getText().toString()
            				+ pin3.getText().toString() + pin4.getText().toString();    
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
        Log.e("Value: ", guessedPin);
    }
}
