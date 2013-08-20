package com.example.pincrack;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import com.example.pincrack.R;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;


public class ViewLogsActivity extends Activity {
    /** Called when the activity is first created. */
    FileInputStream fos;
    BufferedReader reader;
    String text;
    String data;
    
    @Override
    public void onCreate (Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_logs);
        ListView view = (ListView) findViewById(R.id.scrolling_view);

        try {
            fos = openFileInput("results.txt");
        }
        catch (FileNotFoundException e) {
            fos = null;
            e.printStackTrace();
        }

        reader = new BufferedReader(new InputStreamReader(fos));
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                data = line;
            }
            reader.close();
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        String[] values = data.split(",");
        Log.i("values SIZE:", ""+values.length);
        @SuppressWarnings({ "rawtypes", "unchecked" })
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, values);
        view.setAdapter(adapter);

    }
}
