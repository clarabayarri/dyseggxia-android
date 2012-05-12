package dyseggxia.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends Activity {
    /** Called when the activity is first created. */
	
	//GoogleAnalyticsTracker tracker;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    
    public void onClick(View view) {
    	switch(view.getId()) {
    	case R.id.mainLayout:
    		Intent levelSelect = new Intent(this, LevelSelectActivity.class);
    		startActivity(levelSelect);
    		break;
    	case R.id.infoButton:
    		Intent infoMenu = new Intent(this, InfoActivity.class);
    		startActivity(infoMenu);
    		break;
    	}
    }

	@Override
    public void onResume() {
      super.onResume();
    }
    
    @Override
    public void onDestroy() {
      super.onDestroy();
    }
}