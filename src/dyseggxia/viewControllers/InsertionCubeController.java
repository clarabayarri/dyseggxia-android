package dyseggxia.viewControllers;

import android.view.MotionEvent;
import android.widget.LinearLayout;
import android.widget.TextView;
import dyseggxia.activities.CubesActivity;
import dyseggxia.activities.R;
import dyseggxia.domainModel.InsertionProblem;
import dyseggxia.views.CubeAnswersLayoutView;
import dyseggxia.views.CubeWordLayoutView;

public class InsertionCubeController extends CubeController {

	private InsertionProblem problem;
	private CubeAnswersLayoutView answerLayout;
	
	public InsertionCubeController(CubesActivity context, InsertionProblem problem) {
		this.context = context;
		this.problem = problem;
	}
	
	@Override
	public void initializeInterface() {
		loadViews();
	}
	
	private void loadViews() {
		TextView problemName = (TextView)context.findViewById(R.id.problemNameLabel);
		problemName.setText(context.getText(R.string.insertion));
		wordLayout = new CubeWordLayoutView(this,problem.getWord(),false);
		wordLayout.fillLayout((LinearLayout)context.findViewById(R.id.cubeWordLayout));
		answerLayout = new CubeAnswersLayoutView(this,problem.getAnswers(),true);
		answerLayout.fillLayout((LinearLayout)context.findViewById(R.id.cubeAnswerLayout));
		movingLayout = (LinearLayout)context.findViewById(R.id.movingLayout);
	}
	
	@Override
	protected void itemSelected(MotionEvent event){
		//wordLayout.checkForDropEvent(event);
		int index = problem.getWord().indexOf(" ");
		viewDroppedOnIndex(index);
		
	}
	
	@Override
	public void viewDroppedOnIndex(int index) {
		if(invisibleImage != null) {
			movingLayout.removeAllViews();
			String wrongWord = problem.getWord();
			String givenAnswer = wrongWord.replaceFirst(" ", invisibleImage.getText().toString());
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
