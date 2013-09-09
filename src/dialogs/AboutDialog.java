package dialogs;

import com.pincrack.R;
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
public class AboutDialog extends DialogFragment {

	private final String aboutString = "<br/>Studies show that having one universal PIN for all " +
			"uses is incredibly dangerous. However, it can be a real pain keeping track of all your PINs. " +
			"From bank accounts to smartphones to library cards, it's easy to get all " +
			"the digits mixed up - and that can be annoying.<br/><br/>" +
			"" +
			"Pin Crack is a secure tool that generates educated guesses as to what your PIN is. " +
			"Enter in your guess and assign a confidence to each digit. Through our algorithm, " +
			"Pin Crack takes these inputs and generates the combinations with the highest chance " +
			"of success.<br/><br/>" +
			"" +
			"<b>Note: We will never store any of your data. Please use responsibly.</b><br/><br/>";
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {

		ScrollView scrollView = (ScrollView) View.inflate(getActivity(), R.layout.scroll_view, null);
		TextView text = (TextView) View.inflate(getActivity(), R.layout.text_view, null);
		text.setText(Html.fromHtml(aboutString));
		scrollView.addView(text);

		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		
		builder.setTitle(R.string.about_dialog)
				.setView(scrollView)
				.setNegativeButton(R.string.back,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								// User cancelled the dialog
							}
						});
		return builder.create();
	}
}
