package dyseggxia.activities;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import dyseggxia.domainControllers.LevelController;
import dyseggxia.domainControllers.ProblemController;
import dyseggxia.domainModel.Level;
import dyseggxia.domainModel.Problem;
import dyseggxia.factories.ControllerFactory;
import dyseggxia.views.ProblemView;

public class LevelActivity extends Activity implements OnClickListener {
	
	private LevelController levelController;
	private ProblemController problemController;
	private Level level;
	
	private LinearLayout problemsLayout;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.level);
		ControllerFactory factory = new ControllerFactory(this);
		levelController = factory.getLevelController();
		problemController = factory.getProblemController();
		loadLevel();
	}
	
	private void loadLevel() {
		int levelNumber = this.getIntent().getIntExtra("levelNumber", 1);
		level = levelController.getLevel(levelNumber);
		loadInfo();
		loadProblems();
	}

	private void loadInfo() {
		TextView levelTitle = (TextView)findViewById(R.id.levelTitle);
		levelTitle.setText("Level " + level.getNumber());
		TextView levelDescription = (TextView)findViewById(R.id.levelDescription);
		levelDescription.setText(level.getDescription());
	}

	private void loadProblems() {
		problemsLayout = (LinearLayout)findViewById(R.id.problemsLayout);
		fillLayout();
	}
	
	private void fillLayout() {
		problemsLayout.removeAllViews();
		List<Problem> problems = problemController.getThreeRandomProblemsForLevel(level);
		for(Problem problem : problems) {
			ProblemView problemView = new ProblemView(problem, this);
			problemView.setOnClickListener(this);
			problemsLayout.addView(problemView);
		}
	}

	@Override
	public void onClick(View v) {
		ProblemView view = (ProblemView)v;
		startProblem(view.getProblem());
	}

	private void startProblem(Problem problem) {
		Intent problemIntent = new Intent(this, CubesActivity.class);
		problemIntent.putExtra("levelNumber", level.getNumber());
		problemIntent.putExtra("problemType", problem.getTypeName());
		problemIntent.putExtra("problemNumber", problem.getNumber());
		startActivityForResult(problemIntent, 0);
	}
	
	@Override 
	public void onActivityResult(int requestCode, int resultCode, Intent data) {     
		super.onActivityResult(requestCode, resultCode, data); 
		fillLayout();
	}

}
