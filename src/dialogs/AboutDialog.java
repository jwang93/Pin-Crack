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
	
	private final String aboutString ="<br/> It is incredible how powerful the PIN to our smartphones is. " 
			+ "For most people, that PIN grants access to one’s email, text messages, social media accounts, " 
			+ "photos, contacts list. Yet, our culture is very lax about PIN security, especially with regards " 
			+ "to smartphones. One of the most popular attacks on PINs is the observation attack. One specific " 
			+ "type of observation attack is called a shoulder surfing attack, when an attacker peers over the " 
			+ "shoulder of a target entering his password. The security for a PIN in this case is based on " 
			+ "guessing difficulty, observation difficulty, and capture difficulty. <br/> <br/> In this application, we will "
			+ "focus on guessing difficulty and observation difficulty. Shoulder Surfer is an application that " 
			+ "measures (1) how difficult your PIN is to guess and (2) how covertly you enter your PIN. It will "
			+ "then give you an output letting you know how secure your pin is to a shoulder surfing attack. " 
			+ "<br/><br/> Note: We will never store any of your data. Please use responsibly."
			+ "<b><br/><br/> Version 1.0</b><br/>";


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
