package dyseggxia.views;

import java.util.List;

import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import dyseggxia.activities.CubesActivity;
import dyseggxia.activities.R;
import dyseggxia.viewControllers.CubeController;

public abstract class CubeLayoutView {

	protected CubeController controller;
	protected LinearLayout layout;
	protected List<String> letters;
	
	private boolean draggable;
	
	protected int maxWidth;
	protected int maxHeight;
	protected int letterResourceDrawable;
	
	public CubeLayoutView(CubeController controller, boolean draggable) {
		this.controller = controller;
		this.draggable = draggable;
	}
	
	public int getLetterResourceDrawable() {
		return letterResourceDrawable;
	}
	
	public void fillLayout(LinearLayout layout) {
		this.layout = layout;
		layout.removeAllViews();
		initialize();
	}
	
	protected void initialize() {
		calculateSizes();
		for(int i = 0; i < letters.size(); ++i) {
			CubeLetterView newView = new CubeLetterView(this,i,letters.get(i),draggable);
			LayoutParams newParams = new LayoutParams(maxWidth, maxHeight, 1);
			newView.setLayoutParams(newParams);
			layout.addView(newView);
			newView.setWidth(maxWidth);
			newView.setHeight(maxHeight);
		}
	}
	
	protected void calculateSizes() {
		if(layout.getOrientation() == LinearLayout.VERTICAL) {
			maxHeight = Math.max(layout.getWidth()/letters.size(), (int)controller.getContext().getResources().getDimension(R.dimen.cube_height));
			System.out.println("MaxHeight: " + maxHeight);
			maxWidth = maxHeight*3;
		}
		else {
			maxWidth = Math.max(layout.getWidth()/letters.size(),(int)controller.getContext().getResources().getDimension(R.dimen.cube_height));
			maxHeight = maxWidth;
		}
	}

	public CubesActivity getContext() {
		return controller.getContext();
	}

	public void setLetterInIndex(int index, String letter) {
		for(int i = 0; i < layout.getChildCount(); ++i) {
			CubeLetterView view = (CubeLetterView)layout.getChildAt(i);
			if(view.getIndex() == index) {
				view.setLetterAndShow(letter);
				return;
			}
		}
	}

	public void animateCorrectAnswer() {
		//TODO: animacio
	}

	public void removeChild(CubeLetterView invisibleImage) {
		layout.removeView(invisibleImage);
	}
	
	public void checkForDropEvent(MotionEvent event) {
		CubeLetterView cubeView = getChildOnEventLocation(event);
		if(cubeView != null) controller.viewDroppedOnIndex(cubeView.getIndex());
	}

	private CubeLetterView getChildOnEventLocation(MotionEvent event) {
		int location[] = new int[2];
		float x = event.getRawX();
		float y = event.getRawY();
		for(int i = 0; i < layout.getChildCount(); ++i) {
			CubeLetterView child = (CubeLetterView)layout.getChildAt(i);
			child.getLocationOnScreen(location);
			int viewX = location[0];
		    int viewY = location[1];

		    //point is inside view bounds
		    if(( x > viewX && x < (viewX + child.getWidth())) &&
		            ( y > viewY && y < (viewY + child.getHeight()))){
		        return child;
		    }
		}
		return null;
	}

	public void hide() {
		layout.setVisibility(View.GONE);
	}

	public void makeAllVisible() {
		for(int i = 0; i < layout.getChildCount(); ++i) {
			View view = layout.getChildAt(i);
			view.setVisibility(View.VISIBLE);
		}
	}
	
	public String getDisplayedText() {
		String result = "";
		for(int i = 0; i < layout.getChildCount(); ++i) {
			CubeLetterView child = (CubeLetterView)layout.getChildAt(i);
			result += child.getTextContents();
		}
		return result;
	}
	
}
