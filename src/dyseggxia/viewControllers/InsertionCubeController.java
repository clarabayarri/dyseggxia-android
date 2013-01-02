package dyseggxia.viewControllers;

import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import dyseggxia.activities.CubesActivity;
import dyseggxia.activities.R;
import dyseggxia.domainModel.InsertionProblem;
import dyseggxia.views.ProblemAnswerLayout;
import dyseggxia.views.ProblemWordLayout;

public class InsertionCubeController extends GenericCubesProblemViewController {

	private InsertionProblem problem;
	private ProblemAnswerLayout answerLayout;
	
	public InsertionCubeController(CubesActivity context, InsertionProblem problem) {
		this.context = context;
		this.problem = problem;
	}

	@Override
	public int getInstructionsId() {
		return R.string.insertion_desc;
	}
	
	@Override
	public void initLayout() {
		loadViews();
	}
	
	private void loadViews() {
		this.view.setOrientation(LinearLayout.VERTICAL);
		
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
		wordLayout.setLetterInIndex(index,text);
		answerLayout.invalidateTouch();
		check();
	}
	
	@Override
	public void onClickAnswer(int index, String text) {
		wordLayout.setLetterInSpace(text);
		answerLayout.invalidateTouch();
		check();
	}
	
	private void check() {
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
