package dyseggxia.views;

import java.util.List;

import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import dyseggxia.activities.CubesActivity;
import dyseggxia.activities.R;
import dyseggxia.viewControllers.CubeController;

public class CubeLayoutView {

	private CubeController controller;
	private LinearLayout layout;
	private List<String> letters;
	private boolean draggable;
	private int position;
	
	private int maxWidth;
	private int maxHeight;
	
	private boolean vertical = false;
	
	public CubeLayoutView(CubeController controller, List<String> letters, int position, boolean draggable) {
		this.controller = controller;
		this.letters = letters;
		this.draggable = draggable;
		this.position = position;
	}
	
	public void fillLayout(LinearLayout layout) {
		this.layout = layout;
		if(layout.getOrientation() == LinearLayout.VERTICAL) {
			layout.getLayoutParams().height = LayoutParams.FILL_PARENT;
			vertical = true;
		}
		initialize();
	}
	
	private void initialize() {
		calculateSizes();
		for(int i = 0; i < letters.size(); ++i) {
			CubeLetterView newView = new CubeLetterView(this,i,letters.get(i),draggable);
			LayoutParams newParams = new LayoutParams(maxWidth, maxHeight, 1);
			newView.setLayoutParams(newParams);
			layout.addView(newView);
			newView.setWidth(maxWidth);
		}
		if(position == 1) initializeBox();
	}
	
	private void calculateSizes() {
		if(!vertical) {
			maxWidth = Math.max(layout.getWidth()/letters.size(),(int)controller.getContext().getResources().getDimension(R.dimen.cube_height));
			maxHeight = maxWidth;
			if(position == 0) maxHeight *= 1.3;
		}
		else {
			maxHeight = Math.max(layout.getHeight()/letters.size(), (int)controller.getContext().getResources().getDimension(R.dimen.cube_height));
			maxWidth = maxHeight;
		}
	}
	
	private void initializeBox() {
		int padding = (int)controller.getContext().getResources().getDimension(R.dimen.margin_cubes_box);
		int maxHeight = layout.getChildAt(0).getLayoutParams().height + 2*padding;
		int maxWidth = layout.getChildAt(0).getLayoutParams().width * letters.size() + 2*padding;
		//layout.setBackgroundResource(R.drawable.cubosbox);
		layout.getLayoutParams().height = maxHeight;
		layout.getLayoutParams().width = maxWidth;
		layout.setPadding(padding, padding, padding, padding);
	}

	public CubesActivity getContext() {
		return controller.getContext();
	}
	
	public int getPosition() {
		return position;
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
	
	public void appendTermination(String termination) {
		char[] contents = termination.toCharArray();
		for(int i = 0; i < contents.length; ++i) {
			CubeLetterView newView = new CubeLetterView(this,i,String.valueOf(contents[i]),false);
			LayoutParams newParams = new LayoutParams(maxWidth, maxHeight, 1);
			newView.setLayoutParams(newParams);
			layout.addView(newView);
			newView.setWidth(maxWidth);
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
}
