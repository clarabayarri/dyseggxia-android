package dyseggxia.views;

import android.graphics.Typeface;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Button;
import dyseggxia.activities.R;

public class AnswerCubeView extends Button {

	private String displayLetter;
	private boolean shouldBeDraggable;
	private ProblemAnswerLayout parent;
	
	public AnswerCubeView(ProblemAnswerLayout parent, String letter, boolean shouldBeDraggable) {
		super(parent.getDelegate().getContext());
		this.setBackgroundResource(R.drawable.cubosficha);
		this.displayLetter = letter;
		this.setText(letter);
		this.setTag(letter);
		this.setTextSize(30.f);
		this.setTypeface(Typeface.DEFAULT_BOLD);
		this.shouldBeDraggable = shouldBeDraggable;
		this.parent = parent;
	}
	
	public String getDisplayedText() {
		return this.displayLetter;
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		Log.i("CLARA", "onTouch");
		if (shouldBeDraggable && event.getAction() == MotionEvent.ACTION_DOWN) {
			parent.onChildTouched(this);
			return true;
		}
		return false;
	}
}
