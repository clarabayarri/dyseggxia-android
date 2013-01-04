package dyseggxia.factories;

import android.content.Context;
import dyseggxia.domainControllers.AchievementController;
import dyseggxia.domainControllers.CompletedProblemController;
import dyseggxia.domainControllers.LevelController;
import dyseggxia.domainControllers.PreferencesController;
import dyseggxia.domainControllers.ProblemController;
import dyseggxia.utilities.PreferencesAdapter;

public class ControllerFactory {
	
	private static ControllerFactory singletonInstance;
	
	private Context context;
	private ProblemController problemController;
	private LevelController levelController;
	private PreferencesController preferencesController;
	private CompletedProblemController completedProblemController;
	private AchievementController achievementController;
	
	private ControllerFactory(Context context) {
		this.context = context;
	}
	
	public static ControllerFactory getInstance(Context context) {
		if (singletonInstance == null) {
			singletonInstance = new ControllerFactory(context);
		}
		return singletonInstance;
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
	
	public CompletedProblemController getCompletedProblemController() {
		if(completedProblemController == null) {
			completedProblemController = new CompletedProblemController(ProviderFactory.getInstance(context).getCompletedProblemProvider());
		}
		return completedProblemController;
	}
	
	public AchievementController getAchievementController() {
		if(achievementController == null) {
			achievementController = new AchievementController(getCompletedProblemController());
		}
		return achievementController;
	}
}
