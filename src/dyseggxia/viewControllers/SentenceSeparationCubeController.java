package dyseggxia.viewControllers;

import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import dyseggxia.activities.CubesActivity;
import dyseggxia.activities.R;
import dyseggxia.domainModel.SentenceSeparationProblem;
import dyseggxia.utilities.SwipeDetector;
import dyseggxia.views.ProblemWordLayout;

public class SentenceSeparationCubeController extends GenericCubesProblemViewController {

	private SentenceSeparationProblem problem;
	private int numSolutionsProposed;
	private GestureDetector gestureDetector;
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
		numSolutionsProposed = 0;
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
		gestureDetector = new GestureDetector(context, new SwipeDetector(wordLayout, wordLayout.getHeight()));
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		return gestureDetector.onTouchEvent(ev);
	}
	
	@Override
	public void swipeOnIndex(int index) {
		addSpaceAtIndex(index);
	}
	
	private void addSpaceAtIndex(int index) {
		wordLayout.addSpaceInIndex(index);
		++numSolutionsProposed;
		if (numSolutionsProposed < problem.getNumberOfSpacesInProblem()) {
			checkPartial();
		} else {
			checkFinal();
		}
	}
	
	private void checkPartial() {
		String givenAnswer = wordLayout.getDisplayedText();
		if(!problem.isCorrectPartialAnswer(givenAnswer)) {
			failWithSolution(givenAnswer);
		}
	}
	
	private void checkFinal() {
		String givenAnswer = wordLayout.getDisplayedText();
		if(problem.isCorrectAnswer(givenAnswer)) {
			successWithSolution(givenAnswer);
		}
		else {
			failWithSolution(givenAnswer);
		}
	}
	
	@Override
	public void restore() {
		super.restore();
		numSolutionsProposed = 0;
	}

}
