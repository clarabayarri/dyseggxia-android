package dyseggxia.views;

import java.util.ArrayList;
import java.util.List;

import android.view.Gravity;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;
import dyseggxia.activities.R;
import dyseggxia.viewControllers.GenericCubesProblemViewController;

public class ProblemWordLayout extends GenericDragDropLayout {

	private static final int INITIAL_ANIMATION_DELAY = 300;
	private List<String> originalWord;
	private List<WordCube> children;
	
	public ProblemWordLayout(GenericCubesProblemViewController delegate, List<String> word, 
			boolean contentsShouldBeDraggable) {
		super(delegate.getContext());
		this.setOrientation(HORIZONTAL);
		this.setGravity(Gravity.CENTER_VERTICAL);
		this.originalWord = word;
		setDelegate(delegate);
		setDraggable(contentsShouldBeDraggable);
	}
	
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		if (w > 0 && h > 0) {
			int wordWidth = w/originalWord.size();
			if(h > w) {
				this.setPadding(0, (h - wordWidth)/2, 0, (h - wordWidth)/2);
			}
		}
		fixPadding();
	}
	
	public String getDisplayedText() {
		String result = "";
		for (int i = 0; i < children.size(); ++i) {
			result = result + children.get(i).getDisplayedText();
		}
		return result;
	}
	
	public void initLayout() {
		answered = false;
		this.removeAllViews();
		children = new ArrayList<WordCube>();
		
		for(int i = 0; i < originalWord.size(); ++i) {
			String letter = originalWord.get(i);
			addCube(i, letter);
		}
		
		fixPadding();
	}
	
	private void fixPadding() {
		int cubePadding = Math.max(20, (int) (0.2*getChildAt(0).getMeasuredWidth()));
		for (WordCube child : children) {
			child.setPadding(cubePadding);
		}
	}
	
	private void addCube(int index, String text) {
		WordCube letterView = new WordCube(getContext(), text);
		View child = letterView.getView();
		this.addView(child, index);
		LayoutParams params = (LayoutParams) child.getLayoutParams();
		params.weight = 1;
		if (originalWord.size() > 9) {
			TextView label = (TextView) child.findViewById(R.id.cube_contents);
			label.setTextSize(delegate.getContext().getResources().getDimension(R.dimen.DyseggxiaProblemLetterSmallestAppearanceTextSize));
		} else if (originalWord.size() > 7) {
			TextView label = (TextView) child.findViewById(R.id.cube_contents);
			label.setTextSize(delegate.getContext().getResources().getDimension(R.dimen.DyseggxiaProblemLetterSmallerAppearanceTextSize));
		}
		child.setLayoutParams(params);
		children.add(index, letterView);
	}
	
	public void restoreOriginalWord() {
		answered = false;
		for (int i = children.size() - 1; i >= originalWord.size(); --i) {
			removeCubeAt(i);
		}
		for(int i = 0; i < originalWord.size(); ++i) {
			String letter = originalWord.get(i);
			if (children.size() > i) {
				children.get(i).changeDisplayedText(letter);
				children.get(i).showBackground();
			} else {
				addCube(i, letter);
			}
		}
		fixPadding();
	}
	
	public void addSpaceInIndex(int index) {
		addCube(index, " ");
		children.get(index).hideBackground();
		fixPadding();
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
		children.remove(index);
		removeViewAt(index);
		fixPadding();
	}

	@Override
	public void onClick(View v) {
		if (isDraggable && !answered) {
			int index = this.indexOfChild(v);
			delegate.onClickAnswer(index, children.get(index).getDisplayedText());
		}
	}
	
	public void appendText(String text) {
		for (int i = 0; i < text.length(); ++i) {
			String letter = text.substring(i,i+1);
			addCube(this.getChildCount(), letter);
		}
		fixPadding();
	}
	
	public int animateSuccess() {
		answered = true;
		for (int i = 0; i < children.size(); ++i) {
			WordCube child = children.get(i);
			child.getView().startAnimation(getCustomSuccessAnimation(i*100));
		}
		return INITIAL_ANIMATION_DELAY + 700 + 100*(children.size() - 1);
	}
	
	private Animation getCustomSuccessAnimation(int delay) {
		int childHeight = getChildAt(0).getHeight();
		
		TranslateAnimation animation = new TranslateAnimation(0.0f, 0.0f, 0.0f, -(0.20f*childHeight));
		animation.setDuration(150);
		animation.setStartOffset(INITIAL_ANIMATION_DELAY + delay + 0);
		
		TranslateAnimation animation2 = new TranslateAnimation(0.0f, 0.0f, 0.0f, (0.30f*childHeight));
		animation2.setDuration(300);
		animation2.setStartOffset(INITIAL_ANIMATION_DELAY + delay + 150);
		
		TranslateAnimation animation3 = new TranslateAnimation(0.0f, 0.0f, 0.0f, -(0.16f*childHeight));
		animation3.setDuration(150);
		animation3.setStartOffset(INITIAL_ANIMATION_DELAY + delay + 450);
		
		TranslateAnimation animation4 = new TranslateAnimation(0.0f, 0.0f, 0.0f, (0.06f*childHeight));
		animation4.setDuration(100);
		animation4.setStartOffset(INITIAL_ANIMATION_DELAY + delay + 600);
		
		AnimationSet set = new AnimationSet(true);
		set.addAnimation(animation);
		set.addAnimation(animation2);
		set.addAnimation(animation3);
		set.addAnimation(animation4);
		set.setInterpolator(new AccelerateDecelerateInterpolator());
		return set;
	}
	
	public int animateFailByShakingWholeWord() {
		answered = true;
		for (int i = 0; i < children.size(); ++i) {
			WordCube child = children.get(i);
			child.getView().startAnimation(getCustomFailAnimation());
		}
		return INITIAL_ANIMATION_DELAY + 875;
	}
	
	public void animateFailByShakingIndex(int index) {
		answered = true;
		WordCube child = children.get(index);
		child.getView().startAnimation(getCustomFailAnimation());
	}
	
	private Animation getCustomFailAnimation() {
		int childWidth = getChildAt(0).getWidth();
		
		TranslateAnimation animation = new TranslateAnimation(0.0f, -(0.1f*childWidth), 0.0f, 0.0f);
		animation.setDuration(250);
		animation.setStartOffset(INITIAL_ANIMATION_DELAY + 0);
		
		TranslateAnimation animation2 = new TranslateAnimation(0.0f, (0.2f*childWidth), 0.0f, 0.0f);
		animation2.setDuration(250);
		animation2.setStartOffset(INITIAL_ANIMATION_DELAY + 250);
		
		TranslateAnimation animation3 = new TranslateAnimation(0.0f, -(0.2f*childWidth), 0.0f, 0.0f);
		animation3.setDuration(250);
		animation3.setStartOffset(INITIAL_ANIMATION_DELAY + 500);
		
		TranslateAnimation animation4 = new TranslateAnimation(0.0f, (0.1f*childWidth), 0.0f, 0.0f);
		animation4.setDuration(125);
		animation4.setStartOffset(INITIAL_ANIMATION_DELAY + 750);
		
		AnimationSet set = new AnimationSet(true);
		set.addAnimation(animation);
		set.addAnimation(animation2);
		set.addAnimation(animation3);
		set.addAnimation(animation4);
		set.setInterpolator(new AccelerateDecelerateInterpolator());
		return set;
	}
}
