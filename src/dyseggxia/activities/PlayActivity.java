package dyseggxia.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import dyseggxia.domainControllers.AchievementController;
import dyseggxia.domainControllers.PreferencesController;
import dyseggxia.factories.ControllerFactory;

public class PlayActivity extends Activity implements OnClickListener {
	
	private PreferencesController prefController;
	private AchievementController achievementController;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.play);
		ControllerFactory factory = ControllerFactory.getInstance(this);
		prefController = factory.getPreferencesController();
		achievementController = factory.getAchievementController();
	}
	
	@Override
	public void onResume() {
		super.onResume();
		loadInfo();
	}

	private void loadInfo() {
		loadLevel();
		loadPenguin();
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
	
	private void loadPenguin() {
		ImageView penguinView = (ImageView) findViewById(R.id.playPenguinButton);
		if (achievementController.isAchievementUnlockedWithCode(
				AchievementController.AchievementIdentifierUnlockPenguin6)) {
			penguinView.setImageResource(R.drawable.huevo67);
		} else if (achievementController.isAchievementUnlockedWithCode(
				AchievementController.AchievementIdentifierUnlockPenguin5)) {
			penguinView.setImageResource(R.drawable.huevo50);
		} else if (achievementController.isAchievementUnlockedWithCode(
				AchievementController.AchievementIdentifierUnlockPenguin4)) {
			penguinView.setImageResource(R.drawable.huevo33b);
		} else if (achievementController.isAchievementUnlockedWithCode(
				AchievementController.AchievementIdentifierUnlockPenguin3)) {
			penguinView.setImageResource(R.drawable.huevo22);
		} else if (achievementController.isAchievementUnlockedWithCode(
				AchievementController.AchievementIdentifierUnlockPenguin2)) {
			penguinView.setImageResource(R.drawable.huevo15);
		} else if (achievementController.isAchievementUnlockedWithCode(
				AchievementController.AchievementIdentifierUnlockPenguin1)) {
			penguinView.setImageResource(R.drawable.huevo10);
		} else {
			penguinView.setImageResource(R.drawable.huevo1);
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
		case R.id.playPenguinButton:
			startCustomPingu();
			break;
		}
		
	}

	private void startProblem() {
		Intent problemIntent = new Intent(this, CubesActivity.class);
		startActivity(problemIntent);
	}
	
	private void startCustomPingu() {
		// TODO: tienda
	}

}
