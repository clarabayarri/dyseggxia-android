package dyseggxia.activities;

import android.os.Bundle;
import android.view.View;

import com.google.android.apps.analytics.easytracking.TrackedActivity;

import dyseggxia.domainControllers.LevelController;
import dyseggxia.domainControllers.PreferencesController;
import dyseggxia.domainControllers.ProblemController;
import dyseggxia.domainModel.Level;
import dyseggxia.domainModel.Problem;
import dyseggxia.factories.ControllerFactory;
import dyseggxia.factories.ViewControllerFactory;
import dyseggxia.viewControllers.GenericCubesProblemViewController;

public class CubesActivity extends TrackedActivity {

	private GenericCubesProblemViewController viewController;
	private ProblemController problemController;
	private PreferencesController prefController;
	private Level level;
	private Problem problem;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cubesproblem);
		ControllerFactory factory = new ControllerFactory(this);
		LevelController levelController = factory.getLevelController();
		problemController = factory.getProblemController();
		prefController = factory.getPreferencesController();
		
		level = levelController.getLevel(prefController.getCurrentLevel(), 
				prefController.getCurrentLanguage());
	}
	
	@Override
	public void onStart() {
		super.onStart();
		loadProblem();
		viewController = ViewControllerFactory.getCorrectCubeController(this, problem);
		viewController.bindView(findViewById(R.id.cubesproblemmain));
		viewController.initLayout();
	}

	private void loadProblem() {
		try {
			problem = problemController.getRandomProblemForLevel(level);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void onClick(View view) {
		finish();
	}
	
	public void problemAccomplished() {
		finish();
	}
	
	public void problemFailed(String chosenAnswer) {
		
	}
	
}
