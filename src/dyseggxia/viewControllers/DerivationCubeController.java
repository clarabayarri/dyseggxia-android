package dyseggxia.viewControllers;

import android.widget.LinearLayout;
import android.widget.TextView;
import dyseggxia.activities.CubesActivity;
import dyseggxia.activities.R;
import dyseggxia.domainModel.DerivationProblem;
import dyseggxia.views.ProblemWordLayout;

public class DerivationCubeController extends GenericCubesProblemViewController {

	private DerivationProblem problem;
	
	public DerivationCubeController(CubesActivity context, DerivationProblem problem) {
		this.context = context;
		this.problem = problem;
	}
	
	@Override
	public void initLayout() {
		loadViews();
	}
	
	private void loadViews() {
		TextView problemName = (TextView)context.findViewById(R.id.cubesProblemTypeLabel);
		problemName.setText(context.getText(R.string.derivation));
		wordLayout = new ProblemWordLayout(this, problem.getDisplayedText(), false);
		this.view.setOrientation(LinearLayout.HORIZONTAL);
		this.view.addView(wordLayout);
		/*wordLayout.fillLayout((LinearLayout)context.findViewById(R.id.cubeWordVerticalLayout));
		wordLayout.addInvisibleSpace(problem.getDisplayedText().length());
		answerLayout = new CubeAnswersLayoutView(this,problem.getAnswers(),true);
		answerLayout.fillLayout((LinearLayout)context.findViewById(R.id.cubeAnswerVerticalLayout));
		movingLayout = (LinearLayout)context.findViewById(R.id.movingVerticalLayout);*/
	}
	
	@Override
	public void viewDroppedOnIndex(int index, String text) {
		String wrongWord = problem.getDisplayedText();
		String givenAnswer = wrongWord + text;
		if(problem.isCorrectAnswer(givenAnswer)) {
			hideAnswersAndShowCompleteWord();
		}
		else {
			//fail(problem.getAnswers().get(invisibleImage.getIndex()));
		}
	}
	
	private void hideAnswersAndShowCompleteWord() {
		//answerLayout.hide();
		//wordLayout.appendTermination(invisibleImage.getTextContents());
		success();
	}

}
