package dyseggxia.activities;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import dyseggxia.domainControllers.AchievementController;
import dyseggxia.domainControllers.CompletedProblemController;
import dyseggxia.domainControllers.LevelController;
import dyseggxia.domainControllers.PreferencesController;
import dyseggxia.domainControllers.ProblemController;
import dyseggxia.domainModel.Level;
import dyseggxia.domainModel.Problem;
import dyseggxia.factories.ControllerFactory;
import dyseggxia.factories.ViewControllerFactory;
import dyseggxia.viewControllers.CompleteDialogController;
import dyseggxia.viewControllers.GenericCubesProblemViewController;
import dyseggxia.viewControllers.howto.HowToViewControllerI;

public class CubesActivity extends Activity {

	private GenericCubesProblemViewController viewController;
	private ProblemController problemController;
	private PreferencesController prefController;
	private CompletedProblemController completedProblemController;
	private AchievementController achievementController;
	private Level level;
	private Problem problem;
	private HowToViewControllerI howToController;
	private Handler handler;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cubesproblem);
		ControllerFactory factory = ControllerFactory.getInstance(this);
		LevelController levelController = factory.getLevelController();
		problemController = factory.getProblemController();
		prefController = factory.getPreferencesController();
		completedProblemController = factory.getCompletedProblemController();
		achievementController = factory.getAchievementController();
		level = levelController.getLevel(prefController.getCurrentLevel(), 
				prefController.getCurrentLanguage());
		handler = new Handler();
	}
	
	@Override
	public void onStart() {
		super.onStart();
		loadProblem();
	}

	private void loadProblem() {
		try {
			problem = problemController.getRandomProblemForLevel(level);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		viewController = ViewControllerFactory.getCorrectCubeController(this, problem);
		viewController.bindView(findViewById(R.id.cubesproblemmain));
		viewController.initLayout();
		findViewById(R.id.cubesProblemBackButton).setVisibility(View.VISIBLE);
		TextView instructionLabel = (TextView) findViewById(R.id.cubesProblemTypeLabel);
		instructionLabel.setText(viewController.getInstructionsId());
	}
	
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.cubesProblemBackButton:
		case R.id.completedialogBackButton:
			finishPlaying();
			break;
		case R.id.completedialogContinueButton:
			findViewById(R.id.completeDialogView).setVisibility(View.GONE);
			continuePlaying();
			break;
		case R.id.cubesHowToButton:
			showHowTo();
			break;
		}
	}
	
	private void showHowTo() {
		if (howToController == null) {
			howToController = ViewControllerFactory.getCorrectHowToViewController(this, problem);
		}
		howToController.bindView((RelativeLayout) findViewById(R.id.cubesproblemmain));
		howToController.animate();
		handler.postDelayed(new Runnable() {

			public void run() {
				howToController.hide();
			}}, howToController.getDuration());
	}
	
	public void problemAccomplished(String solution, int intents, String wrongSolutions) {
		completedProblemController.saveCompletedProblem(problem, wrongSolutions, intents);
		
		int score = Math.max(0, level.getNumber() + 1 - intents);
		prefController.increaseScore(score);
		
		findViewById(R.id.cubesProblemBackButton).setVisibility(View.INVISIBLE);
		CompleteDialogController dialog = new CompleteDialogController(achievementController);
		dialog.setTextAndScore(solution, score);
		dialog.bindView(findViewById(R.id.cubesproblemmain));
		
		achievementController.checkForAchievementImprovement();
	}
	
	public void continuePlaying() {
		FrameLayout mainView = (FrameLayout) findViewById(R.id.completeDialogView);
		mainView.removeAllViews();
		loadProblem();
	}
	
	public void finishPlaying() {
		finish();
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		return viewController.onTouchEvent(ev);
	}
	
}
