package dialogs;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Html;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import com.example.pincrack.R;

@SuppressLint("NewApi")
public class PINValidatorDialog extends DialogFragment {

    private final String errorString = "<br/>PIN must be 4 digits long.<br/>";

    @Override
    public Dialog onCreateDialog (Bundle savedInstanceState) {

        TextView text = (TextView) View.inflate(getActivity(), R.layout.text_view, null);
        text.setText(Html.fromHtml(errorString));
        text.setGravity(Gravity.CENTER);
        
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.invalid_pin)
                .setView(text)
                .setNegativeButton(R.string.back, new DialogInterface.OnClickListener() {
                    public void onClick (DialogInterface dialog, int id) {
                    }
                });
        return builder.create();
    }
}
