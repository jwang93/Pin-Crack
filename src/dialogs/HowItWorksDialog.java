package dialogs;

import com.example.pincrack.R;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;


@SuppressLint("NewApi")
public class HowItWorksDialog extends DialogFragment {
    
    private final String howItWorksString = "<br/><b>Steps:</b><br/>" +
    		"1. Enter the individual digits to the guessed PIN. <br/> " +
    		"2. Assign a confidence (1-5) to each digit. This confidence is based on your certainty of" +
    		" the digit's correctness in value and position. <br/><br/>" +
    		"" +
    		"<i>Pin Crack's accuracy is heavily contingent on an honest representation of confidence. " +
    		"The target is to have at least two digits with a confidence of 4-5. Higher confidence" +
    		" leads to more precise guesses.</i><br/><br/> ";
    
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
               
		ScrollView scrollView = (ScrollView) View.inflate(getActivity(), R.layout.scroll_view, null);
		TextView text = (TextView) View.inflate(getActivity(), R.layout.text_view, null);
		text.setText(Html.fromHtml(howItWorksString));
		scrollView.addView(text);
		
		
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.how_dialog)
                .setView(scrollView)
                .setNegativeButton(R.string.back, new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                       // User cancelled the dialog
                   }
               });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}

