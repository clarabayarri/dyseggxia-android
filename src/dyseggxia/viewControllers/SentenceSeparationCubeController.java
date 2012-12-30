package dyseggxia.viewControllers;

import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import dyseggxia.activities.CubesActivity;
import dyseggxia.activities.R;
import dyseggxia.domainModel.SentenceSeparationProblem;
import dyseggxia.views.ProblemWordLayout;

public class SentenceSeparationCubeController extends GenericCubesProblemViewController {

	private SentenceSeparationProblem problem;
	//private float originalTouchX;
	
	public SentenceSeparationCubeController(CubesActivity context, SentenceSeparationProblem problem) {
		this.context = context;
		this.problem = problem;
	}

	@Override
	public int getInstructionsId() {
		return R.string.separation_desc;
	}
	
	@Override
	public void initLayout() {
		loadViews();
	}
	
	private void loadViews() {
		this.view.setOrientation(LinearLayout.VERTICAL);
		
		addWordLayout();
	}
	
	private void addWordLayout() {
		wordLayout = new ProblemWordLayout(this, problem.getDisplayedText(), false);
		this.view.addView(wordLayout, 0);
		LayoutParams params = (LayoutParams) wordLayout.getLayoutParams();
		params.weight = 1;
		wordLayout.setLayoutParams(params);
		wordLayout.initLayout();
	}

	/*@Override
	protected void fail(String chosenAnswer) {
		super.fail(chosenAnswer);
		solutionsProposed.clear();
		initLayout();
	}*/

}
