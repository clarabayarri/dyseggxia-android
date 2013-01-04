package dyseggxia.domainControllers;

import java.util.ArrayList;
import java.util.List;

import dyseggxia.domainModel.AchievementUnlockPenguinStep;
import dyseggxia.domainModel.GenericAchievement;

public class AchievementController {

	public static final int AchievementIdentifierUnlockPenguin1 = 0;
	public static final int AchievementIdentifierUnlockPenguin2 = 1;
	public static final int AchievementIdentifierUnlockPenguin3 = 2;
	public static final int AchievementIdentifierUnlockPenguin4 = 3;
	public static final int AchievementIdentifierUnlockPenguin5 = 4;
	public static final int AchievementIdentifierUnlockPenguin6 = 5;
	
	private List<GenericAchievement> allAchievements;
	
	public AchievementController(CompletedProblemController controller) {
		allAchievements = new ArrayList<GenericAchievement>();
		allAchievements.add(AchievementIdentifierUnlockPenguin1, new AchievementUnlockPenguinStep(controller, 1));
		allAchievements.add(AchievementIdentifierUnlockPenguin2, new AchievementUnlockPenguinStep(controller, 2));
		allAchievements.add(AchievementIdentifierUnlockPenguin3, new AchievementUnlockPenguinStep(controller, 3));
		allAchievements.add(AchievementIdentifierUnlockPenguin4, new AchievementUnlockPenguinStep(controller, 4));
		allAchievements.add(AchievementIdentifierUnlockPenguin5, new AchievementUnlockPenguinStep(controller, 5));
		allAchievements.add(AchievementIdentifierUnlockPenguin6, new AchievementUnlockPenguinStep(controller, 6));
	}
	
	public List<GenericAchievement> achievementsWithValueChange() {
		List<GenericAchievement> result = new ArrayList<GenericAchievement>();
		for (GenericAchievement achievement : allAchievements) {
			if (achievement.valueWillChange() && achievement.calculateValue() == GenericAchievement.MAX_VALUE) {
				result.add(achievement);
			}
		}
	    return result;

	}

	public void checkForAchievementImprovement() {
		for (GenericAchievement achievement : allAchievements) {
			achievement.updateValue();
		}
	}

	public boolean isAchievementUnlockedWithCode(int code) {
		return allAchievements.get(code).isComplete();
	}

	public GenericAchievement achievementWithCode(int code) {
		return allAchievements.get(code);
	}
}
