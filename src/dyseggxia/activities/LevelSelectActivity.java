package dyseggxia.activities;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import dyseggxia.domainControllers.LevelController;
import dyseggxia.domainModel.Level;
import dyseggxia.factories.ControllerFactory;
import dyseggxia.views.LevelView;

public class LevelSelectActivity extends Activity implements OnClickListener {

	private LevelController controller;
	private List<Level> levels;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.levelselect);
		ControllerFactory factory = new ControllerFactory(this);
		controller = factory.getLevelController();
		levels = controller.getAllLevels();
		loadLevels();
	}

	private void loadLevels() {
		LinearLayout levelsLayout = (LinearLayout)findViewById(R.id.levelsLayout);
		for(Level level : levels) {
			LevelView levelView = new LevelView(level, this);
			levelView.setOnClickListener(this);
			levelsLayout.addView(levelView);
		}
	}

	@Override
	public void onClick(View v) {
		LevelView view = (LevelView)v;
		startLevel(view.getLevel());
	}

	private void startLevel(Level level) {
		Intent levelIntent = new Intent(this, LevelActivity.class);
		levelIntent.putExtra("levelNumber", level.getNumber());
		startActivity(levelIntent);
	}
}
