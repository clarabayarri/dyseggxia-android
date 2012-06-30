package dyseggxia.viewControllers;

import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import dyseggxia.activities.CubesActivity;
import dyseggxia.activities.R;
import dyseggxia.domainModel.OmissionProblem;
import dyseggxia.views.CubeWordLayoutView;
import dyseggxia.views.ProblemWordLayout;

public class OmissionCubeController extends GenericCubesProblemViewController {

	private OmissionProblem problem;
	
	public OmissionCubeController(CubesActivity context, OmissionProblem problem) {
		this.context = context;
		this.problem = problem;
	}
	
	@Override
	public void initLayout() {
		loadViews();
	}
	
	private void loadViews() {
		TextView problemName = (TextView)context.findViewById(R.id.cubesProblemTypeLabel);
		problemName.setText(context.getText(R.string.omission));
		wordLayout = new ProblemWordLayout(this,problem.getDisplayedText());
		this.view.setOrientation(LinearLayout.VERTICAL);
		this.view.addView(wordLayout);
		/*wordLayout.fillLayout((LinearLayout)context.findViewById(R.id.cubeWordLayout));
		movingLayout = (LinearLayout)context.findViewById(R.id.movingLayout);
		context.findViewById(R.id.cubeAnswerLayout).setVisibility(View.GONE);*/
	}

	@Override
	protected void itemSelected(MotionEvent event){
		movingLayout.removeAllViews();
		String wrongWord = problem.getDisplayedText();
		int index = invisibleImage.getIndex();
		String givenWord = wrongWord.substring(0, index);
		if(index < wrongWord.length()-1) givenWord = givenWord + wrongWord.substring(index+1, wrongWord.length());
		if(problem.isCorrectAnswer(givenWord)) {
			//wordLayout.removeChild(invisibleImage);
			success();
		}
		else fail(String.valueOf(invisibleImage.getIndex()));
	}

}
