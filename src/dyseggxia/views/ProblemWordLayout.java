package dyseggxia.views;

import android.view.Gravity;
import android.widget.LinearLayout;
import dyseggxia.viewControllers.GenericCubesProblemViewController;

public class ProblemWordLayout extends LinearLayout {

	private GenericCubesProblemViewController delegate;
	private String displayWord;
	private boolean movable;
	
	public ProblemWordLayout(GenericCubesProblemViewController delegate, String word) {
		super(delegate.getContext());
		this.delegate = delegate;
		this.setOrientation(HORIZONTAL);
		this.setGravity(Gravity.CENTER_VERTICAL);
		this.displayWord = word;
	}
	
	public GenericCubesProblemViewController getDelegate() {
		return this.delegate;
	}
	
	public void initLayout() {
		for(int i = 0; i < displayWord.length(); ++i) {
			String letter = displayWord.substring(i, i+1);
			WordCubeView letterView = new WordCubeView(this,letter);
			this.addView(letterView);
			LayoutParams params = (LayoutParams) letterView.getLayoutParams();
			params.weight = 1;
			letterView.setLayoutParams(params);
		}
	}
	
	public void restoreOriginalWord() {
		this.removeAllViews();
		initLayout();
	}

}
