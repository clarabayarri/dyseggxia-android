package dyseggxia.activities;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import dyseggxia.domainControllers.LevelController;
import dyseggxia.domainControllers.ProblemController;
import dyseggxia.domainModel.Level;
import dyseggxia.domainModel.Problem;
import dyseggxia.factories.ControllerFactory;
import dyseggxia.views.ProblemView;

public class ProblemSelectActivity extends Activity implements OnClickListener {
	
	private LevelController levelController;
	private ProblemController problemController;
	private Level level;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.problemselect);
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
		TextView levelDescription = (TextView)findViewById(R.id.levelTitle);
		levelDescription.setText(level.getDescription());
	}

	private void loadProblems() {
		fillLayout();
	}
	
	private void fillLayout() {
		List<Problem> problems = problemController.getThreeRandomProblemsForLevel(level);
		ProblemView problemView1 = (ProblemView)findViewById(R.id.ProblemView1);
		problemView1.setProblem(problems.get(0));
		ProblemView problemView2 = (ProblemView)findViewById(R.id.ProblemView2);
		problemView2.setProblem(problems.get(1));
		ProblemView problemView3 = (ProblemView)findViewById(R.id.ProblemView3);
		problemView3.setProblem(problems.get(2));
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
