package dyseggxia.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import dyseggxia.domainControllers.PreferencesController;
import dyseggxia.utilities.PreferencesAdapter;

public class PlayActivity extends Activity implements OnClickListener {
	
	private PreferencesController prefController;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.play);
		prefController = new PreferencesController(new PreferencesAdapter(this));
	}
	
	@Override
	public void onResume() {
		super.onResume();
		loadInfo();
	}

	private void loadInfo() {
		loadLevel();
		// TODO: load saved penguin
		loadScore();
	}
	
	private void loadLevel() {
		int level = prefController.getCurrentLevel();
		Button levelButton = (Button) findViewById(R.id.playChangeLevelButton);
		if (level == 0) {
			levelButton.setText(R.string.level0);
			levelButton.setBackgroundResource(R.drawable.level0button);
		} else if (level == 1) {
			levelButton.setText(R.string.level1);
			levelButton.setBackgroundResource(R.drawable.level1button);
		} else {
			levelButton.setText(R.string.level2);
			levelButton.setBackgroundResource(R.drawable.level2button);
		}
	}
	
	private void loadScore() {
		TextView scoreLabel = (TextView) findViewById(R.id.playScoreLabel);
		scoreLabel.setText(String.valueOf(prefController.getCurrentScore()));
	}

	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.playStartButton:
			startProblem();
			break;
		case R.id.playBackButton:
			finish();
			break;
		case R.id.playChangeLevelButton:
			prefController.increaseCurrentLevel();
			loadInfo();
			break;
		}
		
	}

	private void startProblem() {
		Intent problemIntent = new Intent(this, CubesActivity.class);
		startActivity(problemIntent);
	}

}
