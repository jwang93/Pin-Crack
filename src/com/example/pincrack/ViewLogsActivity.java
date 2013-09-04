package com.example.pincrack;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import com.example.pincrack.R;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ViewLogsActivity extends Activity {
    /** Called when the activity is first created. */
	
    @Override
    public void onCreate (Bundle savedInstanceState) {
    
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_logs);
        ListView view = (ListView) findViewById(R.id.scrolling_view);
        
        @SuppressWarnings({ "rawtypes", "unchecked" })
        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.logs_list_view, R.id.list_content, processResults());
        view.setAdapter(adapter);
    }
    
    /**
     * Helper method that processes the results text file.
     * Returns a string[] of the guesses for the adapter.
     * @return
     */
    private String[] processResults() {
        FileInputStream fos;
        BufferedReader reader;
        String data = null;
    	
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
            e.printStackTrace();
        }
        
        return data.split(",");
    }
}
