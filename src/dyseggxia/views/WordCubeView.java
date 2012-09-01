package dyseggxia.views;

import dyseggxia.activities.R;
import android.graphics.Typeface;
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
