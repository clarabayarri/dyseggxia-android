package dyseggxia.domainControllers;

import android.content.Context;
import dyseggxia.domainModel.Problem;
import dyseggxia.factories.ControllerFactory;
import dyseggxia.factories.ProviderFactory;
import dyseggxia.providers.TrackingDataProvider;
import dyseggxia.utilities.UserDataTracker;

public class UserDataController {

	private UserDataTracker tracker;
	private TrackingDataProvider provider;
	private PreferencesController preferencesController;
	
	public UserDataController(Context context) {
		ControllerFactory factory = new ControllerFactory(context);
        preferencesController = factory.getPreferencesController();
        ProviderFactory pfactory = ProviderFactory.getInstance(context);
        provider = pfactory.getTrackingDataProvider();
		this.tracker = new UserDataTracker();
	}
	
	public void trackData(Problem problem, boolean correct, String answer, int time) {
		if(preferencesController.isTrackingActivated()) {
			String username = preferencesController.getActiveUsername();
			String securityCode = preferencesController.getSecurityCode();
			provider.trackData(problem, correct, answer, time, username);
			tracker.sendTrackingData(problem, correct, answer, time, username, securityCode);
		}
	}
}
