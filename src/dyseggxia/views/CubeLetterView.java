package dyseggxia.views;

import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import dyseggxia.activities.R;

public class CubeLetterView extends Button {

	private int index;
	private CubeLayoutView layout;
	
	public CubeLetterView(CubeLayoutView layout, int index, String letter, boolean clickable) {
		super(layout.getContext());
		this.layout = layout;
		this.index = index;
		this.setBackgroundResource(layout.getLetterResourceDrawable());
		this.setText(letter);
		this.setTextAppearance(layout.getContext(), R.style.DyseggxiaProblemLetterAppearance);
		if(clickable) this.setOnTouchListener(layout.getContext());
		if(letter.equals(' ')) this.setVisibility(View.INVISIBLE);
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
	
	@Override
	public void setWidth(int f) {
		LayoutParams params = this.getLayoutParams();
		params.width = (int) f;
		int padding = (int)(f * 0.3);
		if(layout.getClass().equals(CubeWordLayoutView.class)) this.setPadding(0, padding, padding, 0);
		this.setLayoutParams(params);
	}
	
	public void setLetterAndShow(String letter) {
		this.setText(letter);
		this.setVisibility(View.VISIBLE);
	}

}
