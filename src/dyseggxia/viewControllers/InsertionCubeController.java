package dyseggxia.viewControllers;

import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
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
	public void initLayout() {
		loadViews();
	}
	
	private void loadViews() {
		this.view.setOrientation(LinearLayout.VERTICAL);
		TextView problemName = (TextView)context.findViewById(R.id.cubesProblemTypeLabel);
		problemName.setText(context.getText(R.string.insertion));
		
		addWordLayout();
		addAnswersLayout();
		
		wordLayout.setOnDragListener(answerLayout);
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
		
		//answerLayout.setOnDragListener(wordLayout);
	}
	
	@Override
	public void viewDroppedOnIndex(int index, String text) {
		String wrongWord = problem.getDisplayedText();
		String givenAnswer = wrongWord.replaceFirst(" ", text);
		if(problem.isCorrectAnswer(givenAnswer)) {
			//wordLayout.setLetterInIndex(index,invisibleImage.getTextContents());
			success();
		}
		else {
			fail(text);
		}
	}
}
