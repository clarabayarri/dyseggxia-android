package dyseggxia.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;
import dyseggxia.domainModel.Problem;

public class ProblemView extends Button{

	private Problem problem;
	
	public ProblemView(Context context) {
		super(context);
	}
	
	public ProblemView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	public void setProblem(Problem problem) {
		this.problem = problem;
		this.setText(problem.getTypeName());
	}
	
	public Problem getProblem() {
		return this.problem;
	}
}
