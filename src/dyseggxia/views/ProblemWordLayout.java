package dyseggxia.views;

import android.view.Gravity;
import android.view.View;
import dyseggxia.viewControllers.GenericCubesProblemViewController;

public class ProblemWordLayout extends GenericDragDropLayout {

	private String displayWord;
	
	public ProblemWordLayout(GenericCubesProblemViewController delegate, String word, 
			boolean contentsShouldBeDraggable) {
		super(delegate.getContext());
		this.setOrientation(HORIZONTAL);
		this.setGravity(Gravity.CENTER_VERTICAL);
		this.displayWord = word;
		setDelegate(delegate);
		setDraggable(contentsShouldBeDraggable);
	}
	
	public void initLayout() {
		int realWidth = this.getWidth();
		int realHeight = this.getHeight();
		int wordWidth = realWidth/displayWord.length();
		if(realHeight > wordWidth) {
			this.setPadding(0, (realHeight - wordWidth)/2, 0, (realHeight - wordWidth)/2);
		}
		
		for(int i = 0; i < displayWord.length(); ++i) {
			String letter = displayWord.substring(i, i+1);
			WordCube letterView = new WordCube(getContext(), letter);
			View child = letterView.getView();
			this.addView(child);
			LayoutParams params = (LayoutParams) child.getLayoutParams();
			params.weight = 1;
			
			child.setLayoutParams(params);
			int padding = (int)(child.getWidth() * 0.3);
			child.setPadding(0, padding, padding, 0);
		}
	}
	
	public void restoreOriginalWord() {
		this.removeAllViews();
		initLayout();
	}

	public void setLetterInIndex(int index, String text) {
		WordCube child = (WordCube) getChildAt(index).getTag();
		child.changeDisplayedText(text);
	}
}
