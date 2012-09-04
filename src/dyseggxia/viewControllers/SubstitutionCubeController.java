package dyseggxia.viewControllers;

import android.widget.LinearLayout;
import android.widget.TextView;
import dyseggxia.activities.CubesActivity;
import dyseggxia.activities.R;
import dyseggxia.domainModel.SubstitutionProblem;
import dyseggxia.views.ProblemWordLayout;

public class SubstitutionCubeController extends GenericCubesProblemViewController {

	private SubstitutionProblem problem;

	
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
		wordLayout = new ProblemWordLayout(this, problem.getDisplayedText(), true);
		this.view.setOrientation(LinearLayout.VERTICAL);
		this.view.addView(wordLayout);
		/*wordLayout.fillLayout((LinearLayout)context.findViewById(R.id.cubeWordLayout));
		answerLayout = new CubeAnswersLayoutView(this,problem.getAnswers(),true);
		answerLayout.fillLayout((LinearLayout)context.findViewById(R.id.cubeAnswerLayout));
		movingLayout = (LinearLayout)context.findViewById(R.id.movingLayout);*/
	}
	
	@Override
	public void viewDroppedOnIndex(int index, String text) {
		String wrongWord = problem.getDisplayedText();
		String givenAnswer = wrongWord.substring(0, index) + text;
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
