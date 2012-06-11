package dyseggxia.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;

public class LevelView extends Button {

	private int level;
	
	public LevelView(Context context) {
		super(context);
	}
	
	public LevelView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
	
	public int getLevel() {
		return this.level;
	}

}
