package dyseggxia.views;

import java.util.ArrayList;
import java.util.List;

import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import dyseggxia.activities.R;
import dyseggxia.viewControllers.GenericCubesProblemViewController;

public class ProblemAnswerLayout extends GenericDragDropLayout implements View.OnClickListener {

	private List<String> displayedAnswers;
	private List<AnswerCube> children;
	
	public ProblemAnswerLayout(GenericCubesProblemViewController delegate, List<String> word,
			boolean contentsShouldBeDraggable) {
		super(delegate.getContext());
		this.setOrientation(HORIZONTAL);
		this.setGravity(Gravity.CENTER_VERTICAL);
		this.displayedAnswers = word;
		setDelegate(delegate);
		setDraggable(contentsShouldBeDraggable);
	}
	
	public void initLayout() {
		answered = false;
		LayoutParams params = (LayoutParams) this.getLayoutParams();
		params.leftMargin = 30;
		params.rightMargin = 30;
		this.setLayoutParams(params);
		this.setBackgroundResource(R.drawable.cubesanswersbox);
		this.setPadding(15, 15, 15, 15);
		children = new ArrayList<AnswerCube>();
		for(int i = 0; i < displayedAnswers.size(); ++i) {
			String letter = displayedAnswers.get(i);
			AnswerCube letterView = new AnswerCube(getContext(), letter, false);
			View child = letterView.getView();
			this.addView(child);
			((LinearLayout.LayoutParams) child.getLayoutParams()).weight = 1.0f;
			children.add(letterView);
		}
	}

	public void onClick(View v) {
		if (isDraggable) {
			int index = this.indexOfChild(v);
			delegate.onClickAnswer(index, children.get(index).getDisplayedText());
		}
	}
}
