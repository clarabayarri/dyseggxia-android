package dyseggxia.viewControllers;

import android.view.MotionEvent;
import android.widget.LinearLayout;
import dyseggxia.activities.CubesActivity;
import dyseggxia.activities.R;
import dyseggxia.domainModel.InsertionProblem;
import dyseggxia.views.CubeLayoutView;

public class InsertionCubeController extends CubeController {

	private InsertionProblem problem;
	private CubeLayoutView answerLayout;
	
	public InsertionCubeController(CubesActivity context, InsertionProblem problem) {
		this.context = context;
		this.problem = problem;
	}
	
	@Override
	public void initializeInterface() {
		loadViews();
	}
	
	private void loadViews() {
		wordLayout = new CubeLayoutView(this,problem.getWord(),0,false);
		wordLayout.fillLayout((LinearLayout)context.findViewById(R.id.cubeWordLayout));
		answerLayout = new CubeLayoutView(this,problem.getAnswers(),1,true);
		answerLayout.fillLayout((LinearLayout)context.findViewById(R.id.cubeAnswerLayout));
		movingLayout = (LinearLayout)context.findViewById(R.id.movingLayout);
	}
	
	@Override
	protected void itemSelected(MotionEvent event){
		wordLayout.checkForDropEvent(event);
	}
	
	@Override
	public void viewDroppedOnIndex(int index) {
		if(invisibleImage != null) {
			movingLayout.removeAllViews();
			if(problem.isCorrectAnswer(index,invisibleImage.getIndex())) {
				wordLayout.setLetterInIndex(index,invisibleImage.getTextContents());
				success();
			}
		}
	}
}
