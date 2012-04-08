package dyseggxia.views;

import android.content.Context;
import android.widget.Button;
import dyseggxia.activities.R;
import dyseggxia.domainModel.Level;

public class LevelView extends Button {

	private Level level;
	public LevelView(Level level, Context context) {
		super(context);
		this.level = level;
		loadInfo();
	}
	private void loadInfo() {
		this.setBackgroundResource(R.drawable.boxorange);
		this.setText(String.valueOf(level.getDescription()));
	}
	
	public Level getLevel() {
		return this.level;
	}

}
