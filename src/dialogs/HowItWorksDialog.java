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
        
    private final String howItWorksString = "<br/> <b> Step 1</b>: User A enters in his PIN as he normally would, while " 
    		+ "User B watches. <br/> <b> Step 2</b> : User B enters in a guess of User AÕs PIN, assigning a confidence " 
    		+ "level (number from 1- 5) to each digit. <br/> <b> Step 3</b>: The Pin Crack algorithm will calculate the fewest number of " 
    		+ "guesses it would take a computer to guess User AÕs PIN based on User BÕs guess.<br/> <br/> The " 
    		+ "accuracy of the guessing algorithm is heavily contingent on an honest representation " 
    		+ "of confidence. Ideally, the target is to have at least two digits with a confidence of "
    		+ "4-5. Higher confidence leads to a lower number of guesses. <br/><br/> For guesses < 100, output "
    		+ "will be red for dangerous. <br/> For 100 < guesses < 1000, output will be yellow for warning. "
    		+ "<br/> For guesses > 1000, output will be green for secure. <br/>";
		
    
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

