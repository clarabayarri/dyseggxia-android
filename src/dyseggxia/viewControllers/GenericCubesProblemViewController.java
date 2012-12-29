package dyseggxia.viewControllers;

import android.os.Handler;
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
	private Handler handler;
	protected DragHelper dragHelper;
	
	public CubesActivity getContext() {
		return context;
	}
	
	public void bindView(View view) {
		this.view = (LinearLayout) view.findViewById(R.id.cubesProblemCentralView);
		
		View movingView = view.findViewById(R.id.movingView);
		dragHelper = new DragHelper(movingView);
	}
	
	public abstract void initLayout();
	
	public void successWithSolution(String solution) {
		context.problemAccomplished();
	}
	
	public void fail() {
		wordLayout.restoreOriginalWord();
	}

	public void viewDroppedOnIndex(int index, String text) {
		
	}
	
	protected void success() {
		//wordLayout.animateCorrectAnswer();
		context.problemAccomplished();
		
		if(handler == null) handler = new Handler();
		handler.postDelayed(new Runnable() {
			public void run() {
				context.finish();
			}}, 1000);
	}
	
	protected void fail(String chosenAnswer) {
		context.problemFailed(chosenAnswer);
	}

	public boolean onTouchChild(MotionEvent event, View view2) {
		return dragHelper.onTouch(event, view2);
	}
}
