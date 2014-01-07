package pincrack;

import java.lang.reflect.Method;

import com.pincrack.R;
import dialogs.AboutDialog;
import dialogs.HowItWorksDialog;
import altCntrl.altCntrlActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class PinCrackActivity extends altCntrlActivity {
	/** Called when the activity is first created. */

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		/* Configuration for altCntrl */
		try {
			Method[] methods = new Method[4];
			Object[] objects = new Object[4];
			methods[0] = this.getClass().getMethod("start", View.class);
			objects[0] = this;
			methods[1] = this.getClass().getMethod("finish", View.class);
			objects[1] = this;
			super.altCntrlSetUp(methods, objects, findViewById(R.layout.main));
		} catch (NoSuchMethodException e) {}
	}

	public void finish(View view) {
		finish();
	}

	public void start(View view) {
		Intent inputIntent = new Intent(PinCrackActivity.this,
				DataActivity.class);
		this.startActivity(inputIntent);
	}

	public void startAbout(View view) {
		AboutDialog dialog = new AboutDialog();
		dialog.show(getFragmentManager(), "about Dialog");
	}

	public void startHowItWorks(View view) {
		HowItWorksDialog dialog = new HowItWorksDialog();
		dialog.show(getFragmentManager(), "howItWorks Dialog");
	}

}
