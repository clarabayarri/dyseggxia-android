package dyseggxia.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class CreateUserActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.createuser);
	}
	
	public void onClick(View view) {
    	switch(view.getId()) {
    	case R.id.usersCreateSave:
    		createUser();
    		finish();
    		break;
    	case R.id.usersCreateCancel:
    		finish();
    		break;
    	}
    }
	
	private void createUser() {
		
	}
	
}
