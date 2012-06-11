package dyseggxia.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;

public class ProblemView extends Button{

	private int problem;
	
	public ProblemView(Context context) {
		super(context);
	}
	
	public ProblemView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	public void setProblem(int problem) {
		this.problem = problem;
	}
	
	public int getProblem() {
		return this.problem;
	}
}
