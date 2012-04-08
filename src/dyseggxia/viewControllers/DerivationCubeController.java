package dyseggxia.viewControllers;

import android.view.MotionEvent;
import android.widget.LinearLayout;
import dyseggxia.activities.CubesActivity;
import dyseggxia.activities.R;
import dyseggxia.domainModel.DerivationProblem;
import dyseggxia.views.CubeLayoutView;

public class DerivationCubeController extends CubeController {

	private DerivationProblem problem;
	private CubeLayoutView answerLayout;
	
	public DerivationCubeController(CubesActivity context, DerivationProblem problem) {
		this.context = context;
		this.problem = problem;
	}
	
	@Override
	public void initializeInterface() {
		loadViews();
	}
	
	private void loadViews() {
		wordLayout = new CubeLayoutView(this,problem.getWord(),0,false);
		wordLayout.fillLayout((LinearLayout)context.findViewById(R.id.cubeWordVerticalLayout));
		answerLayout = new CubeLayoutView(this,problem.getAnswers(),1,true);
		answerLayout.fillLayout((LinearLayout)context.findViewById(R.id.cubeAnswerVerticalLayout));
		movingLayout = (LinearLayout)context.findViewById(R.id.movingVerticalLayout);
	}

	@Override
	protected void itemSelected(MotionEvent event) {
		wordLayout.checkForDropEvent(event);
	}
	
	@Override
	public void viewDroppedOnIndex(int index) {
		if(invisibleImage != null) {
			movingLayout.removeAllViews();
			if(problem.isCorrectAnswer(index,invisibleImage.getIndex())) {
				hideAnswersAndShowCompleteWord();
			}
		}
	}
	
	private void hideAnswersAndShowCompleteWord() {
		answerLayout.hide();
		wordLayout.appendTermination(invisibleImage.getTextContents());
		success();
	}

}
