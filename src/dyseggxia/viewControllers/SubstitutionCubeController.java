package dyseggxia.viewControllers;

import android.view.MotionEvent;
import android.widget.LinearLayout;
import dyseggxia.activities.CubesActivity;
import dyseggxia.activities.R;
import dyseggxia.domainModel.SubstitutionProblem;
import dyseggxia.views.CubeAnswersLayoutView;
import dyseggxia.views.CubeWordLayoutView;

public class SubstitutionCubeController extends CubeController {

	private SubstitutionProblem problem;
	private CubeAnswersLayoutView answerLayout;

	
	public SubstitutionCubeController(CubesActivity context, SubstitutionProblem problem) {
		this.context = context;
		this.problem = problem;
	}
	
	@Override
	public void initializeInterface() {
		loadViews();
	}

	private void loadViews() {
		wordLayout = new CubeWordLayoutView(this,problem.getWord(),false);
		wordLayout.fillLayout((LinearLayout)context.findViewById(R.id.cubeWordLayout));
		answerLayout = new CubeAnswersLayoutView(this,problem.getAnswers(),true);
		answerLayout.fillLayout((LinearLayout)context.findViewById(R.id.cubeAnswerLayout));
		movingLayout = (LinearLayout)context.findViewById(R.id.movingLayout);
	}
	
	@Override
	protected void itemSelected(MotionEvent event) {
		wordLayout.checkForDropEvent(event);
	}
	
	@Override
	public void viewDroppedOnIndex(int index) {
		if(invisibleImage != null) {
			movingLayout.removeAllViews();
			String wrongWord = problem.getWord();
			String answer = invisibleImage.getText().toString();
			String givenAnswer = wrongWord.substring(0, index) + answer;
			if(index < wrongWord.length()-1) givenAnswer = givenAnswer + wrongWord.substring(index+1,wrongWord.length());
			if(problem.isCorrectAnswer(givenAnswer)) {
				wordLayout.setLetterInIndex(index,invisibleImage.getTextContents());
				success();
			}
			else {
				fail(problem.getAnswers().get(invisibleImage.getIndex()));
			}
		}
	}

}
