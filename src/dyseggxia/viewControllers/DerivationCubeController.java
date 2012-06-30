package dyseggxia.viewControllers;

import android.view.MotionEvent;
import android.widget.LinearLayout;
import android.widget.TextView;
import dyseggxia.activities.CubesActivity;
import dyseggxia.activities.R;
import dyseggxia.domainModel.DerivationProblem;
import dyseggxia.views.CubeAnswersLayoutView;
import dyseggxia.views.CubeWordLayoutView;
import dyseggxia.views.ProblemWordLayout;

public class DerivationCubeController extends GenericCubesProblemViewController {

	private DerivationProblem problem;
	private CubeAnswersLayoutView answerLayout;
	
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
		wordLayout = new ProblemWordLayout(this,problem.getDisplayedText());
		this.view.setOrientation(LinearLayout.HORIZONTAL);
		this.view.addView(wordLayout);
		/*wordLayout.fillLayout((LinearLayout)context.findViewById(R.id.cubeWordVerticalLayout));
		wordLayout.addInvisibleSpace(problem.getDisplayedText().length());
		answerLayout = new CubeAnswersLayoutView(this,problem.getAnswers(),true);
		answerLayout.fillLayout((LinearLayout)context.findViewById(R.id.cubeAnswerVerticalLayout));
		movingLayout = (LinearLayout)context.findViewById(R.id.movingVerticalLayout);*/
	}

	@Override
	protected void itemSelected(MotionEvent event) {
		//wordLayout.checkForDropEvent(event);
		viewDroppedOnIndex(0);
	}
	
	@Override
	public void viewDroppedOnIndex(int index) {
		if(invisibleImage != null) {
			movingLayout.removeAllViews();
			String wrongWord = problem.getDisplayedText();
			String givenAnswer = wrongWord + invisibleImage.getText().toString();
			if(problem.isCorrectAnswer(givenAnswer)) {
				hideAnswersAndShowCompleteWord();
			}
			else {
				//fail(problem.getAnswers().get(invisibleImage.getIndex()));
			}
		}
	}
	
	private void hideAnswersAndShowCompleteWord() {
		answerLayout.hide();
		//wordLayout.appendTermination(invisibleImage.getTextContents());
		success();
	}

}
