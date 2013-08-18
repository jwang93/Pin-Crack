package com.example.pincrack;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Html;


@SuppressLint("NewApi")
public class HowItWorksDialog extends DialogFragment {
    
    private final String howItWorksString = "<b>Application:</b><br/> This app is a" +
    		" fun tool to let you see (1) how hard your PIN is to crack, and (2) how good you" +
    		" are at discreetly entering in your PIN. <br/><br/>" +
    		"<b>Three main screens:</b><br/>" +
    		"Screen 1: enter your PIN as you normally would around a friend who is subtly " +
    		"glancing at what you are entering <br/>" +
    		"Screen 2: your friend enters in what he or she thinks you entered <br/> " +
    		"Screen 3: you get a result of how many guesses it took the computer to crack your PIN <br/> ";
    
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
                        
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.how_dialog)
                .setMessage(Html.fromHtml(howItWorksString))
                .setNegativeButton(R.string.back, new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                       // User cancelled the dialog
                   }
               });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}

