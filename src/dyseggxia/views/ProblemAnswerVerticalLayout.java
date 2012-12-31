package dyseggxia.views;

import java.util.ArrayList;
import java.util.List;

import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import dyseggxia.activities.R;
import dyseggxia.viewControllers.GenericCubesProblemViewController;

public class ProblemAnswerVerticalLayout extends GenericDragDropLayout implements View.OnClickListener {

	private List<String> displayedAnswers;
	private List<AnswerCube> children;
	
	public ProblemAnswerVerticalLayout(GenericCubesProblemViewController delegate, List<String> word,
			boolean contentsShouldBeDraggable) {
		super(delegate.getContext());
		this.setOrientation(VERTICAL);
		this.setGravity(Gravity.CENTER_HORIZONTAL);
		this.displayedAnswers = word;
		setDelegate(delegate);
		setDraggable(contentsShouldBeDraggable);
	}
	
	public void initLayout() {
		LayoutParams params = (LayoutParams) this.getLayoutParams();
		this.setLayoutParams(params);
		this.setBackgroundResource(R.drawable.cubesanswersverticalbox);
		this.setPadding(15, 15, 15, 15);
		children = new ArrayList<AnswerCube>();
		for(int i = 0; i < displayedAnswers.size(); ++i) {
			String letter = displayedAnswers.get(i);
			AnswerCube letterView = new AnswerCube(getContext(), letter, true);
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
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (isDraggable) {
			int position = (int) (event.getY() / getChildAt(0).getMeasuredHeight());
			View child = getChildAt(position);
			return delegate.onTouchChild(event, child);
		}
		return false;
	}
}
