package dyseggxia.utilities;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferencesAdapter {
	private SharedPreferences trackingPreferences;
	
	public PreferencesAdapter(Context context){
		trackingPreferences = context.getSharedPreferences("tracking", 0);
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
