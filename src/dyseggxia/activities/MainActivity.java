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
        
        //tracker = GoogleAnalyticsTracker.getInstance();
        //tracker.startNewSession("UA-20274206-1", this);
        
        setContentView(R.layout.main);
    }
    
    public void onClick(View view) {
    	switch(view.getId()) {
    	case R.id.mainLogo:
    		//tracker.trackEvent("Navigation", "Click", "Main menu logo", 0);
    		Intent levelSelect = new Intent(this, LevelSelectActivity.class);
    		startActivity(levelSelect);
    		break;
    	}
    }
    
    @Override
    public void onResume() {
      //tracker.trackPageView("/main");
      super.onResume();
    }
    
    @Override
    public void onDestroy() {
      //tracker.stopSession();
      super.onDestroy();
    }
}