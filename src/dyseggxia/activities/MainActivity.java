package dyseggxia.activities;

import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import dyseggxia.domainControllers.PreferencesController;
import dyseggxia.utilities.PreferencesAdapter;


public class MainActivity extends Activity {
	
	private PreferencesController prefController;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		prefController = new PreferencesController(new PreferencesAdapter(this));
    }
	
	@Override
	public void onResume() {
		super.onResume();
		loadInfo();
	}
    
    public void onClick(View view) {
    	switch(view.getId()) {
    	case R.id.menuplay:
    		Intent levelSelect = new Intent(this, PlayActivity.class);
    		startActivity(levelSelect);
    		break;
    	case R.id.menulanguage:
    		prefController.increaseCurrentLanguage();
    		loadInfo();
    		break;
    	case R.id.home_users_button:
    		Intent users = new Intent(this, UsersActivity.class);
    		startActivity(users);
    		break;
    	}
    }
    
    public void loadInfo() {
    	setLocale();
    }
    
    public void setLocale() {
    	String languageToLoad  = prefController.getCurrentLanguage();
        Locale locale = new Locale(languageToLoad); 
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, 
        		getBaseContext().getResources().getDisplayMetrics());
        refreshViews();
    }
    
    public void refreshViews() {
    	Button languageButton = (Button) findViewById(R.id.menulanguage);
    	languageButton.setText(R.string.spanish);
    	Button playButton = (Button) findViewById(R.id.menuplay);
    	playButton.setText(R.string.play);
    }
}