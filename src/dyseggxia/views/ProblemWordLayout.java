package dyseggxia.views;

import android.view.DragEvent;
import android.view.Gravity;
import android.view.View;
import dyseggxia.viewControllers.GenericCubesProblemViewController;

public class ProblemWordLayout extends GenericDragDropLayout {

	private GenericCubesProblemViewController delegate;
	private String displayWord;
	
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
		int realWidth = this.getWidth();
		int realHeight = this.getHeight();
		int wordWidth = realWidth/displayWord.length();
		if(realHeight > wordWidth) {
			this.setPadding(0, (realHeight - wordWidth)/2, 0, (realHeight - wordWidth)/2);
		}
		
		for(int i = 0; i < displayWord.length(); ++i) {
			String letter = displayWord.substring(i, i+1);
			WordCubeView letterView = new WordCubeView(this,letter);
			this.addView(letterView);
			LayoutParams params = (LayoutParams) letterView.getLayoutParams();
			params.weight = 1;
			
			letterView.setLayoutParams(params);
			int padding = (int)(letterView.getWidth() * 0.3);
			letterView.setPadding(0, padding, padding, 0);
		}
	}
	
	public void restoreOriginalWord() {
		this.removeAllViews();
		initLayout();
	}

	@Override
	protected void onDrop(View view, DragEvent event) {
		// TODO Auto-generated method stub
		
	}
}
