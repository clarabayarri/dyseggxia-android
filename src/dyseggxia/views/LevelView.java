package dyseggxia.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;
import dyseggxia.domainModel.Level;

public class LevelView extends Button {

	private Level level;
	
	public LevelView(Context context) {
		super(context);
	}
	
	public LevelView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	public void setLevel(Level level) {
		this.level = level;
		this.setText(String.valueOf(level.getDescription()));
		this.setHeight(this.getWidth());
	}
	
	public Level getLevel() {
		return this.level;
	}

}
