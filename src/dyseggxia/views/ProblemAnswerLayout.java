package dyseggxia.views;

import java.util.List;

import android.view.DragEvent;
import android.view.Gravity;
import android.view.View;
import dyseggxia.activities.R;
import dyseggxia.viewControllers.GenericCubesProblemViewController;

public class ProblemAnswerLayout extends GenericDragDropLayout {

	private GenericCubesProblemViewController delegate;
	private List<String> displayedAnswers;
	
	public ProblemAnswerLayout(GenericCubesProblemViewController delegate, List<String> word) {
		super(delegate.getContext());
		this.delegate = delegate;
		this.setOrientation(HORIZONTAL);
		this.setGravity(Gravity.CENTER_VERTICAL);
		this.displayedAnswers = word;
	}
	
	public GenericCubesProblemViewController getDelegate() {
		return this.delegate;
	}
	
	public void initLayout() {
		this.setBackgroundResource(R.drawable.cubesanswersbox);
		this.setPadding(15, 15, 15, 15);
		for(int i = 0; i < displayedAnswers.size(); ++i) {
			String letter = displayedAnswers.get(i);
			AnswerCubeView letterView = new AnswerCubeView(this,letter);
			this.addView(letterView);
			LayoutParams params = (LayoutParams) letterView.getLayoutParams();
			params.weight = 1;
			params.setMargins(5, 5, 5, 5);
			
			letterView.setLayoutParams(params);
		}
	}

	@Override
	protected void onDrop(View view, DragEvent event) {
		// TODO Auto-generated method stub
		
	}
}
