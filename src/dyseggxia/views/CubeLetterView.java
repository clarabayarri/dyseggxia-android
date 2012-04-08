package dyseggxia.views;

import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import dyseggxia.activities.R;

public class CubeLetterView extends Button {

	private static final int[] imageResources = {R.drawable.cuboslettercube,R.drawable.cubosficha};
	private int index;
	private CubeLayoutView layout;
	
	public CubeLetterView(CubeLayoutView layout, int index, String letter, boolean clickable) {
		super(layout.getContext());
		this.layout = layout;
		this.index = index;
		this.setBackgroundResource(imageResources[layout.getPosition()]);
		this.setText(letter);
		if(clickable) this.setOnTouchListener(layout.getContext());
		if(letter.equals(' ')) this.setVisibility(View.INVISIBLE);
	}
	
	public CubeLetterView(CubeLayoutView layout, String letter, boolean clickable) {
		super(layout.getContext());
		this.index = 0;
		this.setBackgroundResource(imageResources[layout.getPosition()]);
		this.setText(letter);
		if(clickable) this.setOnTouchListener(layout.getContext());
	}
	
	public int getIndex() {
		return index;
	}
	
	public CubeLayoutView getCubeLayout() {
		return this.layout;
	}
	
	public String getTextContents() {
		return this.getText().toString();
	}
	
	public void setWidth(float f) {
		LayoutParams params = this.getLayoutParams();
		params.width = (int) f;
		this.setLayoutParams(params);
	}
	
	public void setLetterAndShow(String letter) {
		this.setText(letter);
		this.setVisibility(View.VISIBLE);
	}

}
