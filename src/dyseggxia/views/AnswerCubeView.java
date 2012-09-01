package dyseggxia.views;

import dyseggxia.activities.R;
import android.graphics.Typeface;
import android.widget.Button;

public class AnswerCubeView extends Button {

	private String displayLetter;
	
	public AnswerCubeView(ProblemAnswerLayout parent, String letter) {
		super(parent.getDelegate().getContext());
		this.setBackgroundResource(R.drawable.cubosficha);
		this.displayLetter = letter;
		this.setText(letter);
		this.setTextSize(30.f);
		this.setTypeface(Typeface.DEFAULT_BOLD);
	}
	
	public String getDisplayedText() {
		return this.displayLetter;
	}
}
