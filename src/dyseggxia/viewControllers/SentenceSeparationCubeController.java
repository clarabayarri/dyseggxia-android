package dyseggxia.viewControllers;

import java.util.ArrayList;
import java.util.List;

import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import dyseggxia.activities.CubesActivity;
import dyseggxia.activities.R;
import dyseggxia.domainModel.SentenceSeparationProblem;
import dyseggxia.views.CubeLayoutView;

public class SentenceSeparationCubeController extends CubeController {

	private SentenceSeparationProblem problem;
	private List<Integer> solutionsProposed = new ArrayList<Integer>();
	
	public SentenceSeparationCubeController(CubesActivity context, SentenceSeparationProblem problem) {
		this.context = context;
		this.problem = problem;
	}
	
	@Override
	public void initializeInterface() {
		loadViews();
	}
	
	private void loadViews() {
		wordLayout = new CubeLayoutView(this,problem.getWord(),0,true);
		wordLayout.fillLayout((LinearLayout)context.findViewById(R.id.cubeWordLayout));
		movingLayout = (LinearLayout)context.findViewById(R.id.movingLayout);
		context.findViewById(R.id.cubeAnswerLayout).setVisibility(View.GONE);
	}

	@Override
	protected void itemSelected(MotionEvent event) {
		movingLayout.removeAllViews();
		//add separated index to array
		if(problem.isCorrectAnswer(solutionsProposed)) {
			wordLayout.removeChild(invisibleImage);
			success();
		}
	}

}
