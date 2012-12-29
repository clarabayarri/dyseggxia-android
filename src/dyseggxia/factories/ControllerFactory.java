package dyseggxia.factories;

import android.content.Context;
import dyseggxia.domainControllers.LevelController;
import dyseggxia.domainControllers.PreferencesController;
import dyseggxia.domainControllers.ProblemController;
import dyseggxia.utilities.PreferencesAdapter;

public class ControllerFactory {
	
	private Context context;
	private ProblemController problemController;
	private LevelController levelController;
	private PreferencesController preferencesController;
	
	public ControllerFactory(Context context) {
		this.context = context;
	}

	public ProblemController getProblemController() {
		if(problemController == null) {
			problemController = new ProblemController(ProviderFactory.getInstance(context).getProblemProvider());
		}
		return problemController;
	}
	
	public LevelController getLevelController() {
		if(levelController == null) {
			levelController = new LevelController(ProviderFactory.getInstance(context).getLevelProvider());
		}
		return levelController;
	}
	
	public PreferencesController getPreferencesController() {
		if(preferencesController == null) {
			preferencesController = new PreferencesController(new PreferencesAdapter(context));
		}
		return preferencesController;
	}
}
