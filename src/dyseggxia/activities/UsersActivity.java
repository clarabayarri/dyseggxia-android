package dyseggxia.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class UsersActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.users);
	}
	
	public void onClick(View view) {
    	switch(view.getId()) {
    	case R.id.usersCreateButton:
    		Intent create = new Intent(this, CreateUserActivity.class);
    		startActivity(create);
    		break;
    	case R.id.usersEditButton:
    		
    		break;
    	case R.id.usersDoneButton:
    		finish();
    		break;
    	}
    }
}
