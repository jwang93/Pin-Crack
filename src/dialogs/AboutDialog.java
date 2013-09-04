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
public class AboutDialog extends DialogFragment {

	private final String aboutString = "<br/>Is there an algorithm that can accurately guess someone’s PIN? "
			+ "Are there are “orderings” that can accurately predict what keys someone entered? <br/><br/> "
			+ "<b>Inspiration:</b> <br/> In college, I was at a party and there were about ten of us "
			+ "crowded on a couch in some apartment. My friend reaches to unlock his smartphone. "
			+ "I was sitting right next to him, as I nonchalantly watched him enter in his PIN. "
			+ "He, like many people, was by no means trying to be discreet. We build complicated"
			+ " passwords for our Email and Social Media accounts, yet that simple PIN has access "
			+ "to his Email, Facebook, and Twitter. Moreover, oftentimes that PIN is linked to other "
			+ "valuable PINs (i.e. Bank Account, Debit Card, etc). <br/> <br/>"
			+ "<b>Premise:</b> <br/> You provide the computer with a guess of what you think the PIN is. This algorithm will"
			+ " try to then guess the real PIN using as few tries as possible.<br/><br/> "
			+ "<b>Version 1.0</b><br/><br/>";

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
