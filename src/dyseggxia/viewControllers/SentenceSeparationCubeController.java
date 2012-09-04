package dyseggxia.viewControllers;

import java.util.ArrayList;
import java.util.List;

import android.widget.LinearLayout;
import android.widget.TextView;
import dyseggxia.activities.CubesActivity;
import dyseggxia.activities.R;
import dyseggxia.domainModel.SentenceSeparationProblem;
import dyseggxia.views.ProblemWordLayout;

public class SentenceSeparationCubeController extends GenericCubesProblemViewController {

	private SentenceSeparationProblem problem;
	private List<Integer> solutionsProposed = new ArrayList<Integer>();
	//private float originalTouchX;
	
	public SentenceSeparationCubeController(CubesActivity context, SentenceSeparationProblem problem) {
		this.context = context;
		this.problem = problem;
	}
	
	@Override
	public void initLayout() {
		loadViews();
	}
	
	private void loadViews() {
		TextView problemName = (TextView)context.findViewById(R.id.cubesProblemTypeLabel);
		problemName.setText(context.getText(R.string.sentenceseparation));
		wordLayout = new ProblemWordLayout(this, problem.getDisplayedText(), false);
		this.view.setOrientation(LinearLayout.VERTICAL);
		this.view.addView(wordLayout);
		/*wordLayout.fillLayout((LinearLayout)context.findViewById(R.id.cubeWordLayout));
		movingLayout = (LinearLayout)context.findViewById(R.id.movingLayout);
		context.findViewById(R.id.cubeAnswerLayout).setVisibility(View.GONE);*/
	}
	
	private int getNumSpacesBefore(int index) {
		int count = 0;
		for(Integer answer : solutionsProposed) {
			if(answer < index) ++count;
		}
		return count;
	}

	@Override
	protected void fail(String chosenAnswer) {
		super.fail(chosenAnswer);
		solutionsProposed.clear();
		initLayout();
	}

}
