package com.example.pincrack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import computation.Computer;
import computation.StaticComputer;
import com.example.pincrack.R;
import dialogs.PINValidatorDialog;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class InputActivityTwo extends Activity {
    /** Called when the activity is first created. */

    String guessedPin;
    String truePin;
    Computer computer;
    BufferedReader reader;

    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input_two);

        final EditText pin = (EditText) findViewById(R.id.phone_dialer);

        Bundle intentInfo = getIntent().getExtras();
        truePin = intentInfo.getString("realPin");

        Button submit = (Button) findViewById(R.id.button3);
        submit.setOnClickListener(new View.OnClickListener()
        {
            public void onClick (View v)
            {
                setPin(pin.getText().toString());
                InputStream inputStream;
                try {

                    if (validationPassed(pin.getText().toString())) {
                        inputStream = getAssets().open("orderings.txt");
                        reader = new BufferedReader(new InputStreamReader(inputStream));
                        // computer = new Computer(Integer.parseInt(truePin),
                        // Integer.parseInt(guessedPin), reader, getApplicationContext());
                        // computer.calculate();
                        StaticComputer.calculate_updated(Integer.parseInt(truePin),
                                                 Integer.parseInt(guessedPin), reader,
                                                 getApplicationContext());
                        Log.i("used SIZE:", ""+StaticComputer.used.size());

                        Intent resultIntent =
                                new Intent(InputActivityTwo.this, ResultActivity.class);
                        resultIntent.putExtra("counter", StaticComputer.counter);
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
