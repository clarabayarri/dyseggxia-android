package dyseggxia.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import dyseggxia.domainControllers.PreferencesController;
import dyseggxia.factories.ControllerFactory;

public class InfoActivity extends Activity {

	PreferencesController preferencesController;
	CheckBox checkbox;
	EditText textView;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info);
        checkbox = (CheckBox)findViewById(R.id.trackingCheckbox);
        textView = (EditText)findViewById(R.id.usernameBox);
        
        ControllerFactory factory = new ControllerFactory(this);
        preferencesController = factory.getPreferencesController();
        
        textView.setText(preferencesController.getActiveUsername());
        checkbox.setChecked(preferencesController.isTrackingActivated());
    }
	
	public void onClick(View view) {
		preferencesController.setTrackingActivated(checkbox.isChecked());
		preferencesController.setActiveUser(textView.getText().toString());
		finish();
	}
}
