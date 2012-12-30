package dyseggxia.viewControllers;

import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import dyseggxia.activities.CubesActivity;
import dyseggxia.activities.R;
import dyseggxia.views.ProblemWordLayout;

public abstract class GenericCubesProblemViewController {

	protected CubesActivity context;
	
	protected LinearLayout view;
	protected ProblemWordLayout wordLayout;
	protected DragHelper dragHelper;
	protected int intents;
	protected String wrongSolutions;
	
	public CubesActivity getContext() {
		return context;
	}
	
	public void bindView(View view) {
		this.view = (LinearLayout) view.findViewById(R.id.cubesProblemCentralView);
		
		View movingView = view.findViewById(R.id.movingView);
		dragHelper = new DragHelper(movingView);
		intents = 0;
		wrongSolutions = "";
	}
	
	public abstract int getInstructionsId();
	
	public abstract void initLayout();
	
	public void successWithSolution(String solution) {
		context.problemAccomplished(solution, intents, wrongSolutions);
	}
	
	public void failWithSolution(String solution) {
		wrongSolutions = wrongSolutions + solution + ", ";
		++intents;
		wordLayout.restoreOriginalWord();
	}
	
	
	// BORRAR MAYBE

	public void viewDroppedOnIndex(int index, String text) {
		
	}

	public boolean onTouchChild(MotionEvent event, View view2) {
		return dragHelper.onTouch(event, view2);
	}
}
