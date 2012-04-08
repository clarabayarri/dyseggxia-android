package dyseggxia.viewControllers;

import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import dyseggxia.activities.CubesActivity;
import dyseggxia.activities.R;
import dyseggxia.domainModel.OmissionProblem;
import dyseggxia.views.CubeLayoutView;

public class OmissionCubeController extends CubeController {

	private OmissionProblem problem;
	
	public OmissionCubeController(CubesActivity context, OmissionProblem problem) {
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
	protected void itemSelected(MotionEvent event){
		movingLayout.removeAllViews();
		if(problem.isCorrectAnswer(invisibleImage.getIndex(),0)) {
			wordLayout.removeChild(invisibleImage);
			success();
		}
	}

}
