package dyseggxia.viewControllers;

import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;
import dyseggxia.activities.CubesActivity;
import dyseggxia.activities.R;
import dyseggxia.domainModel.SubstitutionProblem;
import dyseggxia.views.ProblemAnswerLayout;
import dyseggxia.views.ProblemWordLayout;

public class SubstitutionCubeController extends GenericCubesProblemViewController {

	private SubstitutionProblem problem;
	private ProblemAnswerLayout answerLayout;
	
	public SubstitutionCubeController(CubesActivity context, SubstitutionProblem problem) {
		this.context = context;
		this.problem = problem;
	}

	@Override
	public int getInstructionsId() {
		return R.string.substitution_desc;
	}
	
	@Override
	public void initLayout() {
		loadViews();
	}

	private void loadViews() {
		this.view.setOrientation(LinearLayout.VERTICAL);
		TextView problemName = (TextView)context.findViewById(R.id.cubesProblemTypeLabel);
		problemName.setText(context.getText(R.string.insertion));
		
		addWordLayout();
		addAnswersLayout();
		
		dragHelper.setDragElementListener(answerLayout);
		dragHelper.setDropElementListener(wordLayout);
	}
	
	private void addWordLayout() {
		wordLayout = new ProblemWordLayout(this, problem.getDisplayedText(), false);
		this.view.addView(wordLayout, 0);
		LayoutParams params = (LayoutParams) wordLayout.getLayoutParams();
		params.weight = 1;
		wordLayout.setLayoutParams(params);
		wordLayout.initLayout();
	}
	
	private void addAnswersLayout() {
		answerLayout = new ProblemAnswerLayout(this, problem.getDisplayAnswers(), true);
		this.view.addView(answerLayout);
		LayoutParams params = (LayoutParams) answerLayout.getLayoutParams();
		params.weight = 1.5f;
		params.setMargins(10, 10, 10, 10);
		answerLayout.setLayoutParams(params);
		answerLayout.initLayout();
	}
	
	@Override
	public void viewDroppedOnIndex(int index, String text) {
		wordLayout.setLetterInIndex(index, text);
		answerLayout.invalidateTouch();
		String givenAnswer = wordLayout.getDisplayedText();
		if(problem.isCorrectAnswer(givenAnswer)) {
			successWithSolution(givenAnswer);
		}
		else {
			failWithSolution(givenAnswer);
		}
	}
	
	@Override
	public void restore() {
		super.restore();
		answerLayout.acceptTouches();
	}

}
