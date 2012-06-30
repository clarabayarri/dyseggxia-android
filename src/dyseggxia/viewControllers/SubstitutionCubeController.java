package dyseggxia.viewControllers;

import android.view.MotionEvent;
import android.widget.LinearLayout;
import android.widget.TextView;
import dyseggxia.activities.CubesActivity;
import dyseggxia.activities.R;
import dyseggxia.domainModel.SubstitutionProblem;
import dyseggxia.views.CubeAnswersLayoutView;
import dyseggxia.views.CubeWordLayoutView;
import dyseggxia.views.ProblemWordLayout;

public class SubstitutionCubeController extends GenericCubesProblemViewController {

	private SubstitutionProblem problem;
	private CubeAnswersLayoutView answerLayout;

	
	public SubstitutionCubeController(CubesActivity context, SubstitutionProblem problem) {
		this.context = context;
		this.problem = problem;
	}
	
	@Override
	public void initLayout() {
		loadViews();
	}

	private void loadViews() {
		TextView problemName = (TextView)context.findViewById(R.id.cubesProblemTypeLabel);
		problemName.setText(context.getText(R.string.substitution));
		wordLayout = new ProblemWordLayout(this,problem.getDisplayedText());
		this.view.setOrientation(LinearLayout.VERTICAL);
		this.view.addView(wordLayout);
		/*wordLayout.fillLayout((LinearLayout)context.findViewById(R.id.cubeWordLayout));
		answerLayout = new CubeAnswersLayoutView(this,problem.getAnswers(),true);
		answerLayout.fillLayout((LinearLayout)context.findViewById(R.id.cubeAnswerLayout));
		movingLayout = (LinearLayout)context.findViewById(R.id.movingLayout);*/
	}
	
	@Override
	protected void itemSelected(MotionEvent event) {
		//wordLayout.checkForDropEvent(event);
	}
	
	@Override
	public void viewDroppedOnIndex(int index) {
		if(invisibleImage != null) {
			movingLayout.removeAllViews();
			String wrongWord = problem.getDisplayedText();
			String answer = invisibleImage.getText().toString();
			String givenAnswer = wrongWord.substring(0, index) + answer;
			if(index < wrongWord.length()-1) givenAnswer = givenAnswer + wrongWord.substring(index+1,wrongWord.length());
			if(problem.isCorrectAnswer(givenAnswer)) {
				//wordLayout.setLetterInIndex(index,invisibleImage.getTextContents());
				success();
			}
			else {
				//fail(problem.getAnswers().get(invisibleImage.getIndex()));
			}
		}
	}

}
