package dyseggxia.utilities;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferencesAdapter {
	
	private static final String SCORE_LABEL = "score";
	private static final String LANGUAGE_LABEL = "language";
	private static final String[] LANGUAGES = {"es", "en"};
	private static final String LEVEL_LABEL = "level";
	private static final int NUM_LEVELS = 3;
	
	private SharedPreferences trackingPreferences;
	private SharedPreferences userPreferences;
	
	public PreferencesAdapter(Context context){
		trackingPreferences = context.getSharedPreferences("tracking", 0);
		userPreferences = context.getSharedPreferences("user", 0);
	}
	
	public int getCurrentScore() {
		return userPreferences.getInt(SCORE_LABEL, 0);
	}
	
	public void increaseScore(int value) {
		SharedPreferences.Editor prefEditor = userPreferences.edit();
		prefEditor.putInt(SCORE_LABEL, userPreferences.getInt(SCORE_LABEL, 0) + value);
		prefEditor.commit();
	}
	
	public void setScore(int value) {
		SharedPreferences.Editor prefEditor = userPreferences.edit();
		prefEditor.putInt(SCORE_LABEL, value);
		prefEditor.commit();
	}
	
	public String getCurrentLanguage() {
		return userPreferences.getString(LANGUAGE_LABEL, LANGUAGES[0]);
	}
	
	public void increaseCurrentLanguage() {
		String current = userPreferences.getString(LANGUAGE_LABEL, LANGUAGES[0]);
		for (int i = 0; i < LANGUAGES.length; ++i) {
			String lang = LANGUAGES[i];
			if (current.equals(lang)) {
				int index = (i+1) % LANGUAGES.length;
				SharedPreferences.Editor prefEditor = userPreferences.edit();
				prefEditor.putString(LANGUAGE_LABEL, LANGUAGES[index]);
				prefEditor.commit();
				return;
			}
		}
	}
	
	public int getCurrentLevel() {
		return userPreferences.getInt(LEVEL_LABEL, 0);
	}
	
	public void increaseCurrentLevel() {
		int newLevel = (userPreferences.getInt(LEVEL_LABEL, 0) + 1) % NUM_LEVELS;
		SharedPreferences.Editor prefEditor = userPreferences.edit();
		prefEditor.putInt(LEVEL_LABEL, newLevel);
		prefEditor.commit();
	}
	
	public boolean isTrackingActivated() {
		return trackingPreferences.getBoolean("active", false);
	}
	
	public void setTrackingActivated(boolean active) {
		SharedPreferences.Editor prefEditor = trackingPreferences.edit();
		prefEditor.putBoolean("active", active);
		prefEditor.commit();
	}
	
	public void setActiveUser(String username) {
		SharedPreferences.Editor prefEditor = trackingPreferences.edit();
		prefEditor.putString("activeUser", username);
		prefEditor.commit();
	}
    
	public String getActiveUsername(){
		return trackingPreferences.getString("activeUser", "");
	}
	
	public void setSecurityCode(String code) {
		SharedPreferences.Editor prefEditor = trackingPreferences.edit();
		prefEditor.putString("securityCode", code);
		prefEditor.commit();
	}
	
	public String getSecurityCode() {
		return trackingPreferences.getString("securityCode", "");
	}
}
