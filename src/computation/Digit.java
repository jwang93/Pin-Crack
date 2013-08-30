package computation;

import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * This class encapsulates the logic used to process a digit entered 
 * in InputActivityTwo. 
 *
 */
public class Digit {
	
	private EditText myEditText;
	private SeekBar mySeekBar;
	private TextView myTextView;
	
	public Digit (EditText editText, SeekBar seekBar, TextView textView) {
		myEditText = editText;
		mySeekBar = seekBar;
		myTextView = textView;
	}
	
	public EditText getEditText() {
		return myEditText;
	}

	public SeekBar getSeekBar() {
		return mySeekBar;
	}

	public TextView getTextView() {
		return myTextView;
	}
}
