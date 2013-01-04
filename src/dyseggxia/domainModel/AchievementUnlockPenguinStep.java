package dyseggxia.domainModel;

import dyseggxia.activities.R;
import dyseggxia.domainControllers.CompletedProblemController;

public class AchievementUnlockPenguinStep extends GenericAchievement {

	private static final String ACHIEVEMENT_CODE = "com.cookiecloud.dyseggxia.unlockpenguin";
	private static final int FRAME_LENGTH = 150;
	
	private int step;
	
	public AchievementUnlockPenguinStep(CompletedProblemController controller, int step) {
		this.step = step;
		this.controller = controller;
		this.value = calculateValue();
		this.code = ACHIEVEMENT_CODE + step;
	}
	
	@Override
	public int animationImages() {
		if (step == 1) {
			return R.drawable.penguinunlock1;
		} else if (step == 2) {
			return R.drawable.penguinunlock2;
		} else if (step == 3) {
			return R.drawable.penguinunlock3;
		} else if (step == 4) {
			return R.drawable.penguinunlock4;
		} else if (step == 5) {
			return R.drawable.penguinunlock5;
		} else if (step == 6) {
			return R.drawable.penguinunlock6;
		}
		return 0;
	}

	@Override
	public double calculateValue() {
		return Math.min(MAX_VALUE, 
				Math.ceil(controller.getNumCompletedProblems() * (MAX_VALUE / (double) step)));
	}

	@Override
	public int animationDuration() {
		if (step == 1) return FRAME_LENGTH * 19;
		else if (step == 2) return FRAME_LENGTH * 20;
		else if (step == 3) return FRAME_LENGTH * 20;
		else if (step == 4) return FRAME_LENGTH * 23;
		else if (step == 5) return FRAME_LENGTH * 32;
		else if (step == 6) return FRAME_LENGTH * 33;
		return 0;
	}

}
