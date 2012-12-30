package dyseggxia.viewControllers;

import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;
import dyseggxia.activities.CubesActivity;
import dyseggxia.activities.R;
import dyseggxia.domainModel.DerivationProblem;
import dyseggxia.views.ProblemAnswerLayout;
import dyseggxia.views.ProblemWordLayout;

public class DerivationCubeController extends GenericCubesProblemViewController {

	private DerivationProblem problem;
	private ProblemAnswerLayout answerLayout;
	
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
		String givenAnswer = wordLayout.getDisplayedText() + text;
		if(problem.isCorrectAnswer(givenAnswer)) {
			hideAnswersAndShowCompleteWord(givenAnswer);
		}
		else {
			//fail(problem.getAnswers().get(invisibleImage.getIndex()));
		}
	}
	
	private void hideAnswersAndShowCompleteWord(String solution) {
		//answerLayout.hide();
		//wordLayout.appendTermination(invisibleImage.getTextContents());
		successWithSolution(solution);
	}

}
