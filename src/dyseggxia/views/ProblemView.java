package dyseggxia.views;

import android.content.Context;
import android.widget.Button;
import dyseggxia.activities.R;
import dyseggxia.domainModel.Problem;

public class ProblemView extends Button{

	private Problem problem;
	
	public ProblemView(Problem problem, Context context) {
		super(context);
		this.problem = problem;
		loadInfo();
	}

	private void loadInfo() {
		this.setBackgroundResource(R.drawable.boxorange);
		this.setText(problem.getTypeName());
	}
	
	public Problem getProblem() {
		return this.problem;
	}
}
