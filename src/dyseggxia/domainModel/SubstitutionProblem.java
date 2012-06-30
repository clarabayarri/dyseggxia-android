package dyseggxia.domainModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import dyseggxia.activities.R;

public class SubstitutionProblem extends WordProblem {

private static String typeName = "substitution";
	
	public SubstitutionProblem(int level, int number, String word, int wordIndex, int endIndex) {
		super(level, number, word, wordIndex, endIndex);
	}
	
	public SubstitutionProblem(int level, int number, String word, int wordIndex, int endIndex, List<String> wrongAnswers) {
		this(level, number, word, wordIndex, endIndex);
		this.answers = new ArrayList<String>(wrongAnswers);
	}

	@Override
	protected String generateProblem() {
		return changeWordRangeToContents(getRandomAnswer());
	}

	@Override
	public String getTypeName() {
		return typeName;
	}

	@Override
	public int getLocalizedTypeName() {
		return R.string.substitution;
	}

	@Override
	public int getProblemTypeImageIdentifier() {
		return R.drawable.substitutionpenguin;
	}

	@Override
	public List<String> getDisplayAnswers() {
		List<String> displayAnswers = new ArrayList<String>();
		Random random = new Random();
		int randomIndex = 0;
		for(String s : answers) {
			displayAnswers.add(randomIndex,s);
			randomIndex = (random.nextInt(100) % displayAnswers.size()+1);
		}
		displayAnswers.add(randomIndex, correctWordRangeAsAnswerString());
		return displayAnswers;
	}
	
}
