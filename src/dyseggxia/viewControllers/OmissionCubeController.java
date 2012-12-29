package dyseggxia.viewControllers;

import android.widget.LinearLayout;
import android.widget.TextView;
import dyseggxia.activities.CubesActivity;
import dyseggxia.activities.R;
import dyseggxia.domainModel.OmissionProblem;
import dyseggxia.views.ProblemWordLayout;

public class OmissionCubeController extends GenericCubesProblemViewController {

	private OmissionProblem problem;
	
	public OmissionCubeController(CubesActivity context, OmissionProblem problem) {
		this.context = context;
		this.problem = problem;
	}
	
	@Override
	public void initLayout() {
		loadViews();
	}
	
	private void loadViews() {
		this.view.setOrientation(LinearLayout.VERTICAL);
		TextView problemName = (TextView)context.findViewById(R.id.cubesProblemTypeLabel);
		problemName.setText(context.getText(R.string.omission));
		
		addWordLayout();
		
		dragHelper.setDragElementListener(wordLayout);
		dragHelper.setDropElementListener(wordLayout);
	}
	
	private void addWordLayout() {
		wordLayout = new ProblemWordLayout(this, problem.getDisplayedText(), true);
		this.view.addView(wordLayout);
		wordLayout.initLayout();
	}

	@Override
	public void viewDroppedOnIndex(int index, String text) {
		String wrongWord = problem.getDisplayedText();
		String givenWord = wrongWord.substring(0, index);
		if(index < wrongWord.length()-1) givenWord = givenWord + wrongWord.substring(index+1, wrongWord.length());
		if(problem.isCorrectAnswer(givenWord)) {
			//wordLayout.removeChild(invisibleImage);
			success();
		}
		else fail(text);
	}

}
