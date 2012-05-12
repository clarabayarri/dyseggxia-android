package dyseggxia.views;

import java.util.ArrayList;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import dyseggxia.activities.R;
import dyseggxia.viewControllers.CubeController;

public class CubeWordLayoutView extends CubeLayoutView {
	
	public CubeWordLayoutView(CubeController controller, String letters, boolean draggable) {
		super(controller, draggable);
		System.out.println(letters);
		char[] wordLetters = letters.toCharArray();
		this.letters = new ArrayList<String>();
		for(char c : wordLetters) this.letters.add(String.valueOf(c));
		this.letterResourceDrawable = R.drawable.cuboslettercube;
	}
	
	@Override
	protected void calculateSizes() {
		super.calculateSizes();
		this.maxHeight *= 1.3;
	}
	
	public void appendTermination(String termination) {
		layout.removeViewAt(layout.getChildCount()-1);
		char[] contents = termination.toCharArray();
		for(int i = 0; i < contents.length; ++i) {
			CubeLetterView newView = new CubeLetterView(this,i,String.valueOf(contents[i]),false);
			LayoutParams newParams = new LayoutParams(maxWidth, maxHeight, 1);
			newView.setLayoutParams(newParams);
			layout.addView(newView);
			newView.setWidth(maxWidth);
		}
	}

	public void moveRightLettersToLayout(int index, LinearLayout movingLayout) {
		CubeLetterView firstLetter = (CubeLetterView)movingLayout.getChildAt(0);
		int topMargin = ((LinearLayout.LayoutParams)firstLetter.getLayoutParams()).topMargin;
		for(int i = index+1; i < layout.getChildCount(); ++i) {
			CubeLetterView cubeView = (CubeLetterView)layout.getChildAt(i);
			cubeView.setVisibility(View.INVISIBLE);
			CubeLetterView copyView = new CubeLetterView(cubeView.getCubeLayout(),cubeView.getIndex(),cubeView.getTextContents(),true);
			LinearLayout.LayoutParams newParams = new LinearLayout.LayoutParams(cubeView.getLayoutParams().width, cubeView.getLayoutParams().height,1);
			newParams.topMargin = topMargin;
			copyView.setLayoutParams(newParams);
			movingLayout.addView(copyView);
		}
		addInvisibleSpace(index);
	}

	public void moveLeftLettersToLayout(int index, LinearLayout movingLayout) {
		for(int i = index-1; i < layout.getChildCount() && i >= 0; ++i) {
			CubeLetterView cubeView = (CubeLetterView)layout.getChildAt(i);
			cubeView.setVisibility(View.INVISIBLE);
			CubeLetterView copyView = new CubeLetterView(cubeView.getCubeLayout(),cubeView.getIndex(),cubeView.getTextContents(),true);
			LinearLayout.LayoutParams newParams = new LinearLayout.LayoutParams(cubeView.getLayoutParams().width, cubeView.getLayoutParams().height,1);
			copyView.setLayoutParams(newParams);
			movingLayout.addView(copyView,0);
		}
		addInvisibleSpace(index);
	}

	public void addSpace(int index) {
		CubeLetterView newView = new CubeLetterView(this,0," ",false);
		LayoutParams newParams = new LayoutParams(maxWidth, maxHeight, 1);
		newView.setLayoutParams(newParams);
		layout.addView(newView, index);
	}

	public void addInvisibleSpace(int index) {
		CubeLetterView cubeView = (CubeLetterView)layout.getChildAt(0);
		
		CubeLetterView newView = new CubeLetterView(this,0," ",false);
		LayoutParams newParams = new LayoutParams(cubeView.getLayoutParams().width, cubeView.getLayoutParams().height,1);
		newView.setLayoutParams(newParams);
		newView.setVisibility(View.INVISIBLE);
		layout.addView(newView, index);
	}
}
