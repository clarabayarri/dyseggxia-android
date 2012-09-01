package dyseggxia.views;

import dyseggxia.activities.R;
import android.content.ClipData;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class WordCubeView extends Button {

	private String displayLetter;
	
	public WordCubeView(ProblemWordLayout parent, String letter) {
		super(parent.getDelegate().getContext());
		this.setBackgroundResource(R.drawable.cuboslettercube);
		this.displayLetter = letter;
		this.setText(letter);
		this.setTextSize(30.f);
		this.setTypeface(Typeface.DEFAULT_BOLD);
		this.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View view, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					ClipData dragData = ClipData.newPlainText(displayLetter, displayLetter);
				    View.DragShadowBuilder myShadow = new View.DragShadowBuilder(WordCubeView.this);
				    startDrag(dragData, myShadow, null, 0);
					return true;
				}
				return false;
			}
		});
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
