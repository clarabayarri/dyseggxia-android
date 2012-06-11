package dyseggxia.domainModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import dyseggxia.activities.R;

public class SubstitutionProblem extends WordProblem {

private static String typeName = "substitution";
	
	private String insertedWrongLetter;
	
	public SubstitutionProblem(int level, int number, String word, int wordIndex, int endIndex) {
		super(level, number, word, wordIndex, endIndex);
	}
	
	public SubstitutionProblem(int level, int number, String word, int wordIndex, int endIndex, List<String> wrongAnswers) {
		this(level, number, word, wordIndex, endIndex);
		addAnswers(wrongAnswers);
	}

	@Override
	protected String createProblemFromCorrectWord() {
		return changeWordIndexLetterToCharacter(correctWord, insertedWrongLetter);
	}

	@Override
	public String getTypeName() {
		return typeName;
	}
	
	@Override
	public void addAnswers(List<String> answers) {
		this.answers = new ArrayList<String>(answers);
		int i = getRandomWrongAnswerIndex();
		this.insertedWrongLetter = answers.get(i);
		answers.remove(i);
		addCorrectAnswerToAnswers();
	}
	
	private int getRandomWrongAnswerIndex() {
		Random random = new Random();
		int randomIndex = random.nextInt(100);
		return randomIndex % answers.size();
	}

	@Override
	public int getLocalizedTypeName() {
		return R.string.substitution;
	}

	@Override
	public int getProblemTypeImageIdentifier() {
		return R.drawable.substitutionpenguin;
	}
	
}
