package dyseggxia.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.google.android.apps.analytics.easytracking.TrackedActivity;

import dyseggxia.domainControllers.ProblemController;
import dyseggxia.domainModel.Problem;
import dyseggxia.factories.ControllerFactory;
import dyseggxia.factories.ViewControllerFactory;
import dyseggxia.viewControllers.GenericCubesProblemViewController;

public class CubesActivity extends TrackedActivity {

	private GenericCubesProblemViewController viewController;
	private ProblemController controller;
	private Problem problem;
	private int levelNumber;
	//private UserDataController userDataController;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cubesproblem);
		ControllerFactory factory = new ControllerFactory(this);
		controller = factory.getProblemController();
		//userDataController = factory.getUserDataController();
	}
	
	@Override
	public void onStart() {
		super.onStart();
		loadProblem();
		viewController = ViewControllerFactory.getCorrectCubeController(this, problem);
		viewController.setView((LinearLayout)findViewById(R.id.cubesProblemCentralView));
		viewController.initLayout();
	}

	private void loadProblem() {
		levelNumber = this.getIntent().getIntExtra("levelNumber", 1);
		int problemNumber = this.getIntent().getIntExtra("problemNumber", 1);
		String problemType = this.getIntent().getStringExtra("problemType");
		try {
			problem = controller.getProblem(levelNumber, problemNumber, problemType);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void onClick(View view) {
		finish();
	}
	
	public void problemAccomplished() {
		//userDataController.trackData(problem, true, "", 0);
		finish();
	}
	
	public void problemFailed(String chosenAnswer) {
		//userDataController.trackData(problem, false, chosenAnswer, 0);
	}
	
}
