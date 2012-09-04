package dyseggxia.viewControllers;

import android.os.Handler;
import android.widget.LinearLayout;
import dyseggxia.activities.CubesActivity;
import dyseggxia.views.ProblemWordLayout;

public abstract class GenericCubesProblemViewController {

	protected CubesActivity context;
	
	protected LinearLayout view;
	protected ProblemWordLayout wordLayout;
	private Handler handler;
	
	public CubesActivity getContext() {
		return context;
	}
	
	public void setView(LinearLayout view) {
		this.view = view;
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
			@Override
			public void run() {
				context.finish();
			}}, 1000);
	}
	
	protected void fail(String chosenAnswer) {
		context.problemFailed(chosenAnswer);
	}
}
