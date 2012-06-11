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
	List<Problem> problems;
	
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
		TextView levelDescription = (TextView)findViewById(R.id.problemSelectTitle);
		levelDescription.setText(level.getDescription());
	}

	private void loadProblems() {
		fillLayout();
	}
	
	private void fillLayout() {
		problems = problemController.getThreeRandomProblemsForLevel(level);
		ProblemView problemView1 = (ProblemView)findViewById(R.id.ProblemView1);
		problemView1.setProblem(0);
		problemView1.setText(getResources().getString(problems.get(0).getLocalizedTypeName()));
		problemView1.setBackgroundResource(problems.get(0).getProblemTypeImageIdentifier());
		ProblemView problemView2 = (ProblemView)findViewById(R.id.ProblemView2);
		problemView2.setProblem(1);
		problemView2.setText(getResources().getString(problems.get(1).getLocalizedTypeName()));
		problemView2.setBackgroundResource(problems.get(1).getProblemTypeImageIdentifier());
		ProblemView problemView3 = (ProblemView)findViewById(R.id.ProblemView3);
		problemView3.setProblem(2);
		problemView3.setText(getResources().getString(problems.get(2).getLocalizedTypeName()));
		problemView3.setBackgroundResource(problems.get(2).getProblemTypeImageIdentifier());
	}

	@Override
	public void onClick(View v) {
		if (v instanceof ProblemView) {
			ProblemView view = (ProblemView)v;
			startProblem(problems.get(view.getProblem()));
		}
		else if(v.getId() == R.id.problemSelectBackButton) {
			finish();
		}
		
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
