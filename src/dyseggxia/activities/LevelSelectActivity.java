package dyseggxia.activities;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
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
	}
	
	@Override
	public void onStart() {
		super.onStart();
		levels = controller.getAllLevels();
		loadLevels();
	}

	private void loadLevels() {
		LevelView levelView1 = (LevelView)findViewById(R.id.LevelView1);
		levelView1.setLevel(0);
		LevelView levelView2 = (LevelView)findViewById(R.id.LevelView2);
		levelView2.setLevel(1);
		LevelView levelView3 = (LevelView)findViewById(R.id.LevelView3);
		levelView3.setLevel(2);
	}

	@Override
	public void onClick(View v) {
		if(v instanceof LevelView) {
			LevelView view = (LevelView)v;
			startLevel(levels.get(view.getLevel()));
		}
		else if(v.getId() == R.id.levelSelectBackButton) {
			finish();
		}
	}

	private void startLevel(Level level) {
		Intent levelIntent = new Intent(this, ProblemSelectActivity.class);
		levelIntent.putExtra("levelNumber", level.getNumber());
		startActivity(levelIntent);
	}
}
