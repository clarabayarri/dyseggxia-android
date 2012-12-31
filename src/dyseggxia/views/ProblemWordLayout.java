package dyseggxia.views;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;
import android.view.Gravity;
import android.view.View;
import dyseggxia.viewControllers.GenericCubesProblemViewController;

public class ProblemWordLayout extends GenericDragDropLayout {

	private List<String> originalWord;
	private List<String> displayWord;
	private List<WordCube> children;
	
	public ProblemWordLayout(GenericCubesProblemViewController delegate, List<String> word, 
			boolean contentsShouldBeDraggable) {
		super(delegate.getContext());
		this.setOrientation(HORIZONTAL);
		this.setGravity(Gravity.CENTER_VERTICAL);
		this.originalWord = word;
		this.displayWord = new ArrayList<String>(word);
		setDelegate(delegate);
		setDraggable(contentsShouldBeDraggable);
	}
	
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		if (w > 0 && h > 0) {
			int wordWidth = w/displayWord.size();
			if(h > w) {
				this.setPadding(0, (h - wordWidth)/2, 0, (h - wordWidth)/2);
			}
			
		}
	}
	
	public String getDisplayedText() {
		String result = "";
		for (int i = 0; i < children.size(); ++i) {
			result = result + children.get(i).getDisplayedText();
		}
		return result;
	}
	
	public void initLayout() {
		children = new ArrayList<WordCube>();
		
		for(int i = 0; i < originalWord.size(); ++i) {
			String letter = originalWord.get(i);
			addCube(i, letter);
		}
	}
	
	private void addCube(int index, String text) {
		WordCube letterView = new WordCube(getContext(), text);
		View child = letterView.getView();
		this.addView(child, index);
		LayoutParams params = (LayoutParams) child.getLayoutParams();
		params.weight = 1;
		
		child.setLayoutParams(params);
		int padding = (int)(child.getWidth() * 0.3);
		child.setPadding(0, padding, padding, 0);
		children.add(index, letterView);
	}
	
	public void restoreOriginalWord() {
		this.removeAllViews();
		initLayout();
	}

	public void setLetterInIndex(int index, String text) {
		WordCube child = children.get(index);
		child.changeDisplayedText(text);
	}
	
	public void setLetterInSpace(String text) {
		for (WordCube child : children) {
			if (child.getDisplayedText().equals(" ")) {
				child.changeDisplayedText(text);
				return;
			}
		}
	}
	
	public void removeCubeAt(int index) {
		this.removeViewAt(index);
		children.remove(index);
	}

	@Override
	public void onClick(View v) {
		if (isDraggable) {
			int index = this.indexOfChild(v);
			Log.i("CLARA", "click on index " + index);
			delegate.onClickAnswer(index, children.get(index).getDisplayedText());
		}
	}
	
	public void appendText(String text) {
		for (int i = 0; i < text.length(); ++i) {
			String letter = text.substring(i,i+1);
			addCube(this.getChildCount(), letter);
		}
	}
}
