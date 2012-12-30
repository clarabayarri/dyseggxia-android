package dyseggxia.viewControllers;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import dyseggxia.activities.CubesActivity;
import dyseggxia.activities.R;
import dyseggxia.domainModel.DerivationProblem;
import dyseggxia.views.ProblemAnswerVerticalLayout;
import dyseggxia.views.ProblemWordLayout;

public class DerivationCubeController extends GenericCubesProblemViewController {

	private DerivationProblem problem;
	private ProblemAnswerVerticalLayout answerLayout;
	
	public DerivationCubeController(CubesActivity context, DerivationProblem problem) {
		this.context = context;
		this.problem = problem;
	}

	@Override
	public int getInstructionsId() {
		return R.string.derivation_desc;
	}
	
	@Override
	public void initLayout() {
		loadViews();
	}
	
	private void loadViews() {
		this.view.setOrientation(LinearLayout.HORIZONTAL);
		
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
		answerLayout = new ProblemAnswerVerticalLayout(this, problem.getDisplayAnswers(), true);
		this.view.addView(answerLayout);
		LayoutParams params = (LayoutParams) answerLayout.getLayoutParams();
		params.weight = 1.5f;
		params.setMargins(10, 10, 10, 10);
		answerLayout.setLayoutParams(params);
		answerLayout.initLayout();
	}
	
	@Override
	public void viewDroppedOnIndex(int index, String text) {
		answerLayout.setVisibility(View.GONE);
		wordLayout.appendText(text);
		check();
	}
	
	@Override
	public void onClickAnswer(int index, String text) {
		answerLayout.setVisibility(View.GONE);
		wordLayout.appendText(text);
		check();
	}
	
	private void check() {
		String givenAnswer = wordLayout.getDisplayedText();
		if(problem.isCorrectAnswer(givenAnswer)) {
			successWithSolution(givenAnswer);
		}
		else {
			failWithSolution(givenAnswer);
			answerLayout.setVisibility(View.VISIBLE);
			wordLayout.restoreOriginalWord();
		}
	}

}
