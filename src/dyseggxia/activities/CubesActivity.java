package dyseggxia.activities;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

import com.google.android.apps.analytics.easytracking.TrackedActivity;

import dyseggxia.domainControllers.ProblemController;
import dyseggxia.domainControllers.UserDataController;
import dyseggxia.domainModel.Problem;
import dyseggxia.factories.ControllerFactory;
import dyseggxia.factories.ViewControllerFactory;
import dyseggxia.viewControllers.CubeController;

public class CubesActivity extends TrackedActivity implements OnTouchListener {

	private CubeController viewController;
	private ProblemController controller;
	private Problem problem;
	private int levelNumber;
	private UserDataController userDataController;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if(this.getIntent().getStringExtra("problemType").equals("derivation")) {
			setContentView(R.layout.cubesvertical);
		}
		else setContentView(R.layout.cubes);
		ControllerFactory factory = new ControllerFactory(this);
		controller = factory.getProblemController();
		userDataController = factory.getUserDataController();
	}
	
	@Override
	public void onStart() {
		super.onStart();
		loadProblem();
		viewController = ViewControllerFactory.getCorrectCubeController(this, problem);
		viewController.initializeInterface();
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

	@Override
	public boolean onTouch(View view, MotionEvent event) {
		return viewController.onTouch(view,event);
	}
	
	public void problemAccomplished() {
		userDataController.trackData(problem, true, "", 0);
	}
	
	public void problemFailed(String chosenAnswer) {
		userDataController.trackData(problem, false, chosenAnswer, 0);
	}
	
}
