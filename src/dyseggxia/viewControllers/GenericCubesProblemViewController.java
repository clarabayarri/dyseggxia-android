package dyseggxia.viewControllers;

import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import dyseggxia.activities.CubesActivity;
import dyseggxia.activities.R;
import dyseggxia.views.ProblemWordLayout;

public abstract class GenericCubesProblemViewController {

	private static final int delayExtra = 100;
	
	protected CubesActivity context;
	
	protected LinearLayout view;
	protected ProblemWordLayout wordLayout;
	protected DragHelper dragHelper;
	protected int intents;
	protected String wrongSolutions;
	protected String correctSolution;
	protected Handler handler;
	
	public CubesActivity getContext() {
		return context;
	}
	
	public void bindView(View view) {
		this.view = (LinearLayout) view.findViewById(R.id.cubesProblemCentralView);
		this.view.removeAllViews();
		View movingView = view.findViewById(R.id.movingView);
		dragHelper = new DragHelper(movingView);
		intents = 0;
		wrongSolutions = "";
		handler = new Handler();
	}
	
	public abstract int getInstructionsId();
	
	public abstract void initLayout();
	
	public void successWithSolution(String solution) {
		correctSolution = solution;
		int delay = wordLayout.animateSuccess();
		handler.postDelayed(new Runnable() {
			public void run() {
				context.problemAccomplished(correctSolution, intents, wrongSolutions);
			}}, delay + delayExtra);
	}
	
	public void failWithSolution(String solution) {
		wrongSolutions = wrongSolutions + solution + ", ";
		++intents;
		int delay = wordLayout.animateFailByShakingWholeWord();
		handler.postDelayed(new Runnable() {
			public void run() {
				restore();
			}}, delay + delayExtra);
	}
	
	public void restore() {
		wordLayout.restoreOriginalWord();
	}

	public void viewDroppedOnIndex(int index, String text) {
		
	}

	public boolean onTouchChild(MotionEvent event, View view2) {
		return dragHelper.onTouch(event, view2);
	}
	
	public void onClickAnswer(int index, String text) {
		
	}
	
	public void swipeOnIndex(int index) {
		
	}
	
	public boolean onTouchEvent(MotionEvent ev) {
		return false;
	}
}
