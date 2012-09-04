package dyseggxia.views;

import android.graphics.Typeface;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Button;
import dyseggxia.activities.R;

public class WordCubeView extends Button {

	private ProblemWordLayout parent;
	private String displayLetter;
	private boolean shouldBeDraggable;
	
	public WordCubeView(ProblemWordLayout parent, String letter, boolean shouldBeDraggable) {
		super(parent.getDelegate().getContext());
		this.setBackgroundResource(R.drawable.cuboslettercube);
		this.displayLetter = letter;
		this.setText(letter);
		this.setTag(letter);
		this.setTextSize(30.f);
		this.setTypeface(Typeface.DEFAULT_BOLD);
		this.shouldBeDraggable = shouldBeDraggable;
		this.parent = parent;
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
	
	public String getDisplayedText() {
		return this.displayLetter;
	}
	
	public void changeDisplayedText(String newText) {
	    this.displayLetter = newText;
	    this.setText(displayLetter);
	}

	public void hideBackground() {
	    this.setBackgroundResource(0);
	}
}
