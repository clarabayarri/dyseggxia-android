package dyseggxia.domainModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SubstitutionProblem extends Problem {

private static String typeName = "substitution";
	
	private Character insertedWrongLetter;
	
	public SubstitutionProblem(int level, int number, String word, int wordIndex) {
		super(level,number,word,wordIndex);
	}
	
	public SubstitutionProblem(int level, int number, String word, int wordIndex, List<String> wrongAnswers) {
		this(level, number, word, wordIndex);
		addAnswers(wrongAnswers);
	}

	public boolean isCorrectAnswer(int wordIndex, int answerIndex) {
		return (wordIndex == this.wordIndex && answerIndex == this.answerIndex);
	}

	@Override
	protected List<String> createProblemFromCorrectWord() {
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
		this.insertedWrongLetter = answers.get(i).charAt(0);
		answers.remove(i);
		addCorrectAnswerToAnswers();
	}
	
	private int getRandomWrongAnswerIndex() {
		Random random = new Random();
		int randomIndex = random.nextInt(100);
		return randomIndex % answers.size();
	}
	
}
