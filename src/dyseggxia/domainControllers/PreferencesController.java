package dyseggxia.domainControllers;

import dyseggxia.utilities.PreferencesAdapter;

public class PreferencesController {

	private PreferencesAdapter adapter;
	
	public PreferencesController(PreferencesAdapter adapter) {
		this.adapter = adapter;
	}
	
	public boolean isTrackingActivated() {
		return adapter.isTrackingActivated();
	}
	
	public void setTrackingActivated(boolean active) {
		adapter.setTrackingActivated(active);
	}
	
	public void setActiveUser(String username) {
		adapter.setActiveUser(username);
	}
    
	public String getActiveUsername(){
		return adapter.getActiveUsername();
	}
	
	public void setSecurityCode(String code) {
		adapter.setSecurityCode(code);
	}
	
	public String getSecurityCode() {
		return adapter.getSecurityCode();
	}
}
